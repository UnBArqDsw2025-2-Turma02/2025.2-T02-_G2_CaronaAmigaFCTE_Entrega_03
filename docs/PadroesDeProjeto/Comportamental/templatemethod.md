# Template Method

## Introdução

O **padrão Template Method** é um dos **padrões comportamentais** do catálogo GoF e tem como propósito **definir o esqueleto de um algoritmo em uma superclasse, permitindo que subclasses implementem ou alterem passos específicos do algoritmo sem modificar sua estrutura global**.

Segundo o Refactoring.Guru:

> “Template Method is a behavioral design pattern that defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.” 

Esse padrão é especialmente útil em sistemas onde existem diversas classes que compartilham **algoritmos comuns** com **variações específicas**. Ele permite que você **centre e padronize a estrutura do algoritmo** em uma classe base, enquanto subclasses cuidam das particularidades. Assim, o código fica mais **organizado**, **mais fácil de manter** e com **menor duplicação**.

Entre seus principais benefícios estão a **eliminação de código duplicado** por meio da elevação de trechos comuns para a superclasse, a **facilidade de extensão e adaptação** das etapas variantes sem mexer na estrutura principal do algoritmo, e o **controle centralizado** sobre o fluxo global do processo, tornando a manutenção e evolução mais seguras.

Alguns usos comuns são:  
Um cenário típico seria um sistema de mineração de dados que suporta múltiplos formatos (DOC, CSV, PDF) — a estrutura de processamento (abrir arquivo, extrair dados, analisar, gerar relatório) é a mesma, mas cada formato exige implementação específica em certas etapas.
Outro exemplo é um motor de IA para jogo onde várias “raças” compartilham o mesmo algoritmo de turno (coletar recursos, construir estruturas, atacar), mas cada raça possui variações customizadas nas suas etapas concretas. 

---

## Metodologia
O padrão Template Method foi aplicado para definir a estrutura geral de um algoritmo, permitindo que subclasses implementassem apenas etapas específicas conforme suas necessidades. Essa abordagem garantiu reutilização de código, padronização de processos e maior controle sobre a sequência de execução das operações. Com a centralização do fluxo principal na superclasse, o sistema se tornou mais organizado e consistente, facilitando futuras manutenções e evitando duplicação de lógica.

---

## Modelagem

<p align="center">
  <img src="PadroesDeProjeto\assets\templateMethod.png" alt="Diagrama do Template Method" width="1000"/>
</p>

<p align="center"><b>Fonte: </b>Autoria de <a href="https://github.com/kalebmacedo">Kaleb Macedo</a>, <a href="https://github.com/LucasMF1">Lucas Monteiro Freitas</a> e <a href="https://github.com/bolzanMGB">Othavio Bolzan</a></p>


---

## Código 

#### Classe abstrata do Template Method
```java
abstract class PagamentoTemplate {

    public final void processarPagamento(Pagamento pagamento, Carona carona, Usuario pagador) {
        try {
            validarEntrada(pagamento, carona, pagador);
            double base = calcularValorBase(carona);
            double comTaxas = calcularTaxas(base);
            double finalComDescontos = aplicarDescontos(comTaxas, pagador);
            debitar(pagamento, pagador, finalComDescontos);
            registrar(pagamento, carona, finalComDescontos);
            notificar(pagador, resumoSucesso(pagamento, finalComDescontos));
        } catch (Exception e) {
            pagamento.falhar(e.getMessage());
            notificar(pagador, "Pagamento não concluído: " + e.getMessage());
        }
    }

    protected void validarEntrada(Pagamento pagamento, Carona carona, Usuario pagador) { ... }

    protected double calcularValorBase(Carona carona) { ... }

    protected abstract double calcularTaxas(double valorBase);
    protected double aplicarDescontos(double valor, Usuario pagador) { return valor; }
    protected abstract void debitar(Pagamento pagamento, Usuario pagador, double valorFinal) throws Exception;

    protected void registrar(Pagamento pagamento, Carona carona, double valorFinal) { ... }
    protected void notificar(Usuario pagador, String mensagem) { ... }
    protected String resumoSucesso(Pagamento p, double valor) { ... }
}
```
#### Subclasses concretas 
```java
class PixPagamento extends PagamentoTemplate {
    protected double calcularTaxas(double valorBase) { return valorBase + 0.01; }
    protected void debitar(Pagamento pagamento, Usuario pagador, double valorFinal) {
        pagamento.metodo = "PIX";
        System.out.printf(Locale.ROOT, "Debitado via Pix: R$ %.2f do usuário %s%n", valorFinal, pagador.nome);
    }
}
```

```java
class CartaoCreditoPagamento extends PagamentoTemplate {
    protected double calcularTaxas(double valorBase) { return (valorBase * 1.0299) + 0.40; }
    protected double aplicarDescontos(double valor, Usuario pagador) { return valor * 0.98; }
    protected void debitar(Pagamento pagamento, Usuario pagador, double valorFinal) throws Exception {
        boolean aprovado = valorFinal < 1000;
        pagamento.metodo = "CARTAO";
        if (!aprovado) throw new Exception("Transação negada pela operadora.");
        System.out.printf(Locale.ROOT, "Autorizado no cartão: R$ %.2f para %s%n", valorFinal, pagador.nome);
    }
}
```
```java
class DinheiroPagamento extends PagamentoTemplate {
    protected double calcularTaxas(double valorBase) { return valorBase; }
    protected void debitar(Pagamento pagamento, Usuario pagador, double valorFinal) {
        pagamento.metodo = "DINHEIRO";
        System.out.printf(Locale.ROOT, "Pagamento em dinheiro combinado: R$ %.2f%n", valorFinal);
    }
    protected void registrar(Pagamento pagamento, Carona carona, double valorFinal) {
        pagamento.valor = valorFinal;
        pagamento.status = "AGUARDANDO_RECEBIMENTO";
        System.out.println("Registro de pagamento em dinheiro (aguardando confirmação do motorista).");
    }
}
```

#### Uso na prática 
```java
public class Main {
    public static void main(String[] args) {
        Motorista m = new Motorista(1, "Ana Motorista", "ana@gmail.com");
        Carona carona = new Carona("UnB", "Asa Sul", LocalDate.now(), LocalTime.of(18, 0), 25.00, m);
        Passageiro p = new Passageiro(2, "Lucas", "Lucas@gmail.com");

        Pagamento pgPix = p.pagarCarona(carona, "pix");
        Pagamento pgCartao = p.pagarCarona(carona, "cartao");
        Pagamento pgDinheiro = p.pagarCarona(carona, "dinheiro");

        System.out.println("Status PIX: " + pgPix.status);
        System.out.println("Status CARTAO: " + pgCartao.status);
        System.out.println("Status DINHEIRO: " + pgDinheiro.status);
    }
}
```


---

## Conclusão 

O padrão Template Method foi aplicado para definir a estrutura geral de um algoritmo, permitindo que subclasses implementassem apenas etapas específicas conforme suas necessidades. Essa abordagem garantiu reutilização de código, padronização de processos e maior controle sobre a sequência de execução das operações. Com a centralização do fluxo principal na superclasse, o sistema se tornou mais organizado e consistente, facilitando futuras manutenções e evitando duplicação de lógica.

---

## Referências

> [Refactoring.Guru – Padrão Template Method](https://refactoring.guru/design-patterns/template-method) 

---

| Versão | Data       | Descrição                                                                                             | Autor                                          | Revisor |
| :----: | ---------- | ----------------------------------------------------------------------------------------------------- | ---------------------------------------------- | ------- |
|  `1.0` | 20/10/2025 | Criação do tópico de Introdução. |  [Maria Eduarda](https://github.com/pyramidsf)  | [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) |
|  `1.1` | 20/10/2025 | Consertar detalhe gramatical. |  [Maria Eduarda](https://github.com/pyramidsf)  |  |
|  `1.2` | 23/10/2025 |Finalização da documentação. |  [Maria Eduarda](https://github.com/pyramidsf), [Caio Venâncio](https://github.com/caio-venancio), [Pedro Henrique](https://github.com/pedro-hsf), [Caio Melo](https://github.com/CaioMelo25) |  |