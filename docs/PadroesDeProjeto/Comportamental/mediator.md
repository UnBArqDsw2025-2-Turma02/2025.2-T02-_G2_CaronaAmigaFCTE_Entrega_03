# Mediator

## Introdução

O **padrão Mediator** é um dos padrões comportamentais do catálogo GoF. Ele tem como objetivo **reduzir o acoplamento direto entre os objetos**, centralizando a comunicação em um único objeto mediador.  

Segundo o [Refactoring.Guru](https://refactoring.guru/design-patterns/mediator):

> “O Mediator é um padrão de projeto que reduz as dependências caóticas entre objetos. O padrão restringe as comunicações diretas entre objetos e os força a colaborar apenas através de um mediador.”

Esse padrão é especialmente útil em sistemas onde **muitos objetos precisam interagir entre si**, pois evita a criação de múltiplas dependências cruzadas, tornando o código mais **organizado, flexível e fácil de manter**.  
Entre seus principais benefícios estão a **redução do acoplamento** entre classes que precisam se comunicar, a **centralização da lógica de interação** em um único ponto, a **facilidade de manutenção e evolução do sistema**, e a **melhoria na reutilização de componentes**, já que eles passam a ser menos dependentes uns dos outros.

Alguns usos comuns são:

Um cenário típico seria um **sistema de chat**, no qual o mediador gerencia a comunicação entre os usuários.  
Em vez de cada usuário enviar mensagens diretamente para todos os outros, eles se comunicam através do mediador, que **coordena e direciona** as mensagens de forma centralizada.

---

## Metodologia

**Para o desenvolvimento do Mediator, foram adotadas as seguintes etapas metodológicas:**

1. Foram elaborados um diagrama de casos de uso e um diagrama de classes que serviram como base para estruturar os elementos envolvidos no padrão, suas relações e hierarquias. 

2. Como embasamento teórico, foram consultados os materiais disponibilizados pela professora e o site Refactoring Guru, que auxiliaram na compreensão dos princípios, funcionamento e aplicações do padrão Mediator.

3. O diagrama foi elaborado na ferramenta draw.io, proporcionando uma visualização clara da estrutura dos objetos, de seus relacionamentos e da hierarquia estabelecida conforme os princípios da UML.

4. Foram realizadas duas versões do diagrama. Uma antes e uma depois de uma revisão realizada pela equipe. As duas versões estão presentes na documentação. 

## Modelagem

<iframe frameborder="0" style="width:100%;height:600px" src="PadroesDeProjeto\assets\mediator3.drawio.html" allowtransparency="true" dark=0></iframe>

<p align="center"><b>Fonte: </b>Autoria de <a href="https://github.com/caio-venancio">Caio Venâncio</a>, <a href="https://github.com/pyramidsf">Maria Eduarda</a>, <a href="ttps://github.com/CaioMelo25">Caio Melo</a> e <a href="https://github.com/pedro-hsf">Pedro Henrique</a></p>



<details>
<summary>Primeira versão do Mediator</summary>
<img src="PadroesDeProjeto\assets\mediator3_primeira_versao.drawio.png" alt="Primeira versão do diagrama Proxy" style="width:65%; border-radius:8px; margin-top:10px;">
</details>

---

## Desenvolvimento 

O diagrama representa como o padrão Mediator estrutura a interação entre os tipos de usuários Driver, Passenger e Admin, evitando dependências diretas entre eles. As ações específicas de cada papel são mediadas pelo ConcreteMediator, que coordena a comunicação e simplifica o gerenciamento do sistema. Essa organização favorece a manutenção e a extensibilidade da aplicação.

---

## Código 

Para rodar Mediator:
1. Entre em implementação/Mediator. 
2. Rode javac .java mediator\.java mediator\model\*.java. 
3. Rode java Main. 

--- 


### Mediator
#### Mediator.java
```java
package mediator;

public interface Mediator {
    void notify(Component sender, String event);
}
```
#### Admin.java
```java
package mediator;

import mediator.model.Report;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Component {
    private final List<Report> managedReports = new ArrayList<>();
    private final List<Component> suspendedUsers = new ArrayList<>();
    private final List<String> privileges = new ArrayList<>();

    public Admin(String id, String name, String email) {
        super(id, name, email);
        privileges.add("REPORT_MODERATION");
        privileges.add("USER_SUSPEND");
    }

    public void approveReport(Report report) {
        managedReports.add(report);
        if (mediator != null) mediator.notify(this, "reportApproved");
    }

    public void rejectReport(Report report) {
        managedReports.add(report);
        if (mediator != null) mediator.notify(this, "reportRejected");
    }

    public void suspendUser(Component user) {
        suspendedUsers.add(user);
        user.deactivate();
        if (mediator != null) mediator.notify(this, "userSuspended");
    }
}
```
#### Component.java
```java
package mediator;

public abstract class Component {
    protected Mediator mediator;
    protected String id;
    protected String name;
    protected String email;
    protected boolean active = true;

    public Component(String id, String name, String email) {
        this.id = id; this.name = name; this.email = email;
    }

    public void setMediator(Mediator mediator) { this.mediator = mediator; }

    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; }

    public String getId() { return id; }
    public String getName() { return name; }

    public void action() { /* default no-op */ }

    @Override public String toString() {
        return getClass().getSimpleName()+"{id='"+id+"', name='"+name+"'}";
    }
}
```
#### ConcreteMediator.java
```java
package mediator;

import mediator.model.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConcreteMediator implements Mediator {

    private Passenger passenger;
    private Driver driver;
    private Admin admin;

    private final List<Report> reports = new ArrayList<>();
    private final List<Component> suspendedUsers = new ArrayList<>();

    public void wire(Passenger p, Driver d, Admin a) {
        this.passenger = p; this.driver = d; this.admin = a;
        p.setMediator(this); d.setMediator(this); a.setMediator(this);
    }

    @Override
    public void notify(Component sender, String event) {
        System.out.printf("[Mediator] sender=%s event=%s%n", sender, event);

        switch (event) {
            case "reportCreated" -> {
            }
            case "commentCreated" -> {
            }
            case "likeCreated" -> {
            }
            case "reportApproved" -> {
                Optional<Report> any = reports.stream().filter(r -> r.getStatus() == Report.Status.PENDING).findFirst();
                any.ifPresent(r -> {
                    r.setStatus(Report.Status.APPROVED);
                    admin.suspendUser(r.getTarget());
                });
            }
            case "reportRejected" -> {
                reports.stream()
                        .filter(r -> r.getStatus() == Report.Status.PENDING)
                        .findFirst()
                        .ifPresent(r -> r.setStatus(Report.Status.REJECTED));
            }
            default -> {
                if (event.startsWith("passengerRated:")) {
                    int stars = Integer.parseInt(event.split(":")[1]);
                    passenger.updateRating(Math.max(1.0, Math.min(5.0, stars)));
                }
            }
        }
    }

    public Report processReport(Report report) {
        reports.add(report);
        System.out.println("[Mediator] Report recebido: " + report);
        return report;
    }

    public void approveReport(Report report) {
        report.setStatus(Report.Status.APPROVED);
        admin.approveReport(report);
        suspendedUsers.add(report.getTarget());
    }

    public void rejectReport(Report report) {
        report.setStatus(Report.Status.REJECTED);
        admin.rejectReport(report);
    }

    public List<Report> getReports() { return reports; }
    public List<Component> getSuspendedUsers() { return suspendedUsers; }
}
```
#### Driver.java
```java
package mediator;

import mediator.model.Report;

import java.util.ArrayList;
import java.util.List;

public class Driver extends Component {
    private double rating = 5.0;
    private int totalTrips = 0;
    private final List<Passenger> passengersRated = new ArrayList<>();

    public Driver(String id, String name, String email) { super(id, name, email); }

    public void createReport(Driver driver, String reason) {
        if (mediator != null) mediator.notify(this, "reportCreated");
    }

    public void ratePassenger(Passenger passenger, int stars) {
        passengersRated.add(passenger);
        if (mediator != null) mediator.notify(this, "passengerRated:" + stars);
    }

    public void incrementTrips() { totalTrips++; }

    public void updateRating(double newAvg) { this.rating = newAvg; }

    @Override public String toString() {
        return super.toString()+"{rating="+rating+", trips="+totalTrips+"}";
    }
}
```
#### Passenger.java
```java

package mediator;

import mediator.model.Comment;
import mediator.model.Report;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends Component {
    private final List<Report> reports = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();
    private int likes = 0;
    private double rating = 5.0;

    public Passenger(String id, String name, String email) {
        super(id, name, email);
    }

    public void createReport(Driver driver, String reason) {
        Report report = new Report(this, driver, reason);
        reports.add(report);
        if (mediator != null) mediator.notify(this, "reportCreated");
    }

    public void createComment(String text) {
        comments.add(new Comment(text));
        if (mediator != null) mediator.notify(this, "commentCreated");
    }

    public void createLike(Driver driver) {
        likes++;
        if (mediator != null) mediator.notify(this, "likeCreated");
    }

    public void updateRating(double newAvg) { this.rating = newAvg; }

    @Override public String toString() {
        return super.toString()+"{rating="+rating+", likes="+likes+"}";
    }
}
```



## Conclusão 
A aplicação do padrão Mediator na CaronaAmiga foi fundamental para diminuir o acoplamento entre os componentes da interface. Sua adoção proporcionou uma comunicação centralizada e eficiente, resultando em um sistema mais claro, escalável e de fácil manutenção. Com isso, futuras modificações ou expansões podem ser realizadas de forma mais simples e segura, contribuindo para a qualidade geral do projeto.

---

## Referências

> [Refactoring.Guru – Padrão Mediator](https://refactoring.guru/design-patterns/mediator)

---

## Histórico de Versões


| Versão | Data       | Descrição                                                                                             | Autor                                          | Revisor |
| :----: | ---------- | ----------------------------------------------------------------------------------------------------- | ---------------------------------------------- | ------- |
|  `1.0` | 20/10/2025 | Criação do tópico de Introdução. |  [Maria Eduarda](https://github.com/pyramidsf)  | [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) |
|  `1.1` | 22/10/2025 | Criação dos tópicos de Metodologia, Desenvolvimento e Conclusão. |  [Maria Eduarda](https://github.com/pyramidsf) [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) | |
|  `1.2` | 23/10/2025 | Adicionar modelagem e primeira versão. |  [Maria Eduarda](https://github.com/pyramidsf) [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) | |
|  `1.3` | 23/10/2025 | Adicionar código na documentação e mudanças no tópico da metodologia. |  [Maria Eduarda](https://github.com/pyramidsf) [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) | |
