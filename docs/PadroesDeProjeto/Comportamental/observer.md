# Observer

## Introdução

O **padrão Observer** é um dos padrões comportamentais do catálogo GoF. Ele tem como objetivo **definir uma dependência um-para-muitos entre objetos**, de modo que quando um objeto muda de estado, todos os seus dependentes sejam automaticamente notificados e atualizados.  

Segundo o [Refactoring.Guru](https://refactoring.guru/design-patterns/observer):

> “O Observer é um padrão de projeto que permite que um objeto notifique outros objetos sobre mudanças em seu estado, sem precisar saber quem são esses objetos.”

Esse padrão é especialmente útil em sistemas **reativos ou baseados em eventos**, nos quais é importante que várias partes do sistema respondam automaticamente a uma alteração ocorrida em outro componente. Ele promove um **baixo acoplamento** entre os objetos, já que o sujeito (objeto observado) não precisa conhecer os detalhes de quem está recebendo as notificações.  

Entre seus principais benefícios estão a **facilidade de extensão do sistema** com novos observadores sem alterar o código existente, a **automação na propagação de atualizações** entre os componentes, e a **redução da dependência direta** entre as classes. Além disso, o padrão melhora a **organização e manutenção do código**, especialmente em contextos onde várias partes do sistema precisam reagir de forma sincronizada a determinados eventos.

Alguns usos comuns são:

Um cenário típico seria um **sistema de notícias**, no qual diversos assinantes (observadores) recebem automaticamente novas atualizações quando uma notícia é publicada.  
Outro exemplo é uma **interface gráfica**, onde elementos visuais são atualizados automaticamente quando os dados do modelo sofrem alterações.

## Metodologia

A aplicação do padrão Observer foi realizada para permitir a comunicação automática e desacoplada entre objetos. Foi modelado o diagrama dos Observers no draw.io, e implementadas as classes em Java para testar notificações automáticas. O padrão facilitou a manutenção e a expansão do sistema, permitindo adicionar novos observadores sem alterar o núcleo do código.

## Modelagem

<div style="width: 640px; height: 480px; margin: 10px; position: relative;"><iframe allowfullscreen frameborder="0" style="width:640px; height:480px" src="https://lucid.app/documents/embedded/333c5f74-69f6-46af-91c1-6553a98fccf9" id="fpp2A.M_aM5B"></iframe></div>

<p align="center"><b>Fonte: </b>Autoria de <a href="https://github.com/mandicrz"> Amanda Cruz</a>, <a href="https://github.com/tutzs"> Arthur Sousa</a>, <a href="https://github.com/caua08"> Cauã Araujo</a></p>

## Código
### Observer 

#### AvaliacaoService.java
```java
package observer;

public class AvaliacaoService implements CaronaObserver {
    @Override
    public void atualizar(Carona carona, Status anterior, Status atual) {
        if (atual == Status.FINALIZADA) {
            System.out.println("[AvaliacaoService] Carona " + carona.getIdCarona()
                    + " finalizada. Acionando módulo de avaliação de motorista e passageiros.");
        } else {
            System.out.println("[AvaliacaoService] Carona " + carona.getIdCarona()
                    + " alterada para " + atual + ". Nenhuma ação de avaliação necessária.");
        }
    }
}
```

#### Carona.java
```java
package observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Carona {
    private final int idCarona;
    private final String origem;
    private final String destino;

    // Estado padronizado com enum
    private Status status;

    private final List<CaronaObserver> observers = new CopyOnWriteArrayList<>();

    public Carona(int idCarona, String origem, String destino) {
        this.idCarona = idCarona;
        this.origem = origem;
        this.destino = destino;
        this.status = Status.PENDENTE; // estado inicial seguro
    }

    public void adicionarObserver(CaronaObserver o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
        }
    }

    public void removerObserver(CaronaObserver o) {
        observers.remove(o);
    }

    private void notificarObservers(Status anterior, Status atual) {
        for (CaronaObserver o : observers) {
            o.atualizar(this, anterior, atual);
        }
    }

    public void setStatus(Status novoStatus) {
        if (novoStatus == null)
            return;
        if (this.status == novoStatus)
            return; // idempotência: evita notificações redundantes

        Status anterior = this.status;
        this.status = novoStatus;
        System.out.println(">>> Carona " + idCarona + ": " + anterior + " -> " + novoStatus);
        notificarObservers(anterior, novoStatus);
    }

    public int getIdCarona() {
        return idCarona;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public Status getStatus() {
        return status;
    }
}
```

#### Carona.Observer.java
```java
package observer;

public interface CaronaObserver {
    void atualizar(Carona carona, Status anterior, Status atual);
}
```

#### NotificacaoService.java
```java
package observer;

public class NotificacaoService implements CaronaObserver {
    @Override
    public void atualizar(Carona carona, Status anterior, Status atual) {
        System.out.println("[NotificacaoService] Carona " + carona.getIdCarona()
                + " mudou de " + anterior + " para " + atual
                + " (de " + carona.getOrigem() + " para " + carona.getDestino() + ")");
    }
}
```

#### Status.java
```java
package observer;

public enum Status {
    PENDENTE,
    CONFIRMADA,
    FINALIZADA,
    ATRASADA
}
``` 
<p align="center"><b>Fonte: </b>Autoria de <a href="https://github.com/mandicrz"> Amanda Cruz</a>, <a href="https://github.com/tutzs"> Arthur Sousa</a>, <a href="https://github.com/caua08"> Cauã Araujo</a></p>

## Conclusão 

O padrão Observer foi implementado de forma eficaz, permitindo uma comunicação reativa e desacoplada entre os objetos do sistema. Sua aplicação possibilitou que múltiplos componentes fossem notificados automaticamente sempre que o estado de um objeto principal fosse alterado, eliminando dependências diretas e aumentando a flexibilidade da arquitetura. Com isso, o sistema passou a apresentar maior coesão e extensibilidade, uma vez que novos observadores podem ser adicionados ou removidos sem necessidade de alterações nas demais classes. Essa característica reforça o princípio de baixo acoplamento, essencial em sistemas orientados a eventos.

## Referências 

> [Refactoring.Guru – Padrão Observer](https://refactoring.guru/design-patterns/observer)

## Histórico de Versões

| Versão | Data       | Descrição                                                                                             | Autor                                          | Revisor |
| :----: | ---------- | ----------------------------------------------------------------------------------------------------- | ---------------------------------------------- | ------- |
|  `1.0` | 20/10/2025 | Criação do tópico de Introdução. |  [Maria Eduarda](https://github.com/pyramidsf)  | [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) |
|  `1.1` | 23/10/2025 | Adicionar código na documentação. |  [Maria Eduarda](https://github.com/pyramidsf) [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) | |
|  `1.2` | 23/10/2025 | Adicionar metodologia e concluão. |  [Maria Eduarda](https://github.com/pyramidsf) [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) | |
|  `1.3` | 23/10/2025 | Adicionar modelagem. |  [Maria Eduarda](https://github.com/pyramidsf) [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) | |
