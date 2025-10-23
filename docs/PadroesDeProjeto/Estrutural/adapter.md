# Adapter

## Definição
O padrão Adapter (também conhecido como Adaptador ou Wrapper) é um dos padrões estruturais do catálogo de Padrões de Projeto que tem o propósito de permitir que objetos com interfaces incompatíveis colaborem entre si.

O padrão Adapter cria uma classe intermediária que atua como um tradutor entre o código cliente e uma classe de serviço existente que possui uma "interface estranha".
> Segundo o Refactoring.Guru:
>
> "O Adapter é um objeto especial que converte a interface de um objeto para que outro objeto possa entendê-lo", facilitando a colaboração entre objetos com interfaces diferentes. O adaptador encobre um dos objetos para esconder a complexidade da conversão que acontece nos bastidores.
> 
> Fonte : [Refactoring.Guru](https://refactoring.guru/pt-br/design-patterns/adapter)

O Adapter é especialmente útil quando você precisa usar uma classe útil, geralmente de terceiros ou código legado, que você não pode (ou não deve) modificar, mas que tem uma interface incompatível com o resto do seu código.

Seus principais benefícios são:

- Permitir a Reutilização: Permite que o cliente utilize uma classe de serviço existente que, de outra forma, seria inutilizável devido à incompatibilidade de interface.
- Aderência a Princípios: Ajuda a separar a lógica de conversão de interface ou de dados da lógica primária de negócio (Princípio de responsabilidade única).
- Flexibilidade (Princípio aberto/fechado): Você pode introduzir novos tipos de adaptadores no programa sem quebrar o código cliente existente, desde que o cliente trabalhe através da interface do cliente.
- Tradução de Dados: Pode converter dados em vários formatos (como XML para JSON) ou converter unidades (como metros para unidades imperiais).

Um cenário análogo seria a adaptação de tomadas elétricas em uma viagem internacional. Se o seu plugue brasileiro não cabe na tomada europeia, você usa um adaptador de tomada que converte o estilo do plugue para o estilo que a tomada espera. Em termos de programação, um cenário típico é uma aplicação de monitoramento do mercado de ações que baixa dados em XML, mas precisa usar um adaptador XML-para-JSON para que uma biblioteca de análise de terceiros possa processar esses dados. O adaptador recebe a chamada do cliente, traduz os dados XML para JSON, e então passa a chamada para os métodos apropriados do objeto de análise encoberto.

## Metodologia

## Modelagem

## Código

### Interface alvo (Target)
`Pagamento` — interface uniforme que o cliente usa para processar pagamentos.

```java
// Target
public interface Pagamento {
	void setDados(Passageiro passageiro); // recebe os dados do passageiro
	void pagar(double valor);
}
```

### Adaptees (APIs existentes)
`PagamentoPix` e `PagamentoCartao` representam APIs de pagamento já existentes com interfaces distintas.

```java
// Adaptees
class PagamentoPix {
	public void pagarComPix(String chavePix, double valor) {
		System.out.println("Pagando R$" + valor + " via Pix: " + chavePix);
	}
}

class PagamentoCartao {
	public void pagarComCartao(String numeroCartao, double valor) {
		System.out.println("Pagando R$" + valor + " com Cartão: " + numeroCartao);
	}
}
```

### Adapters
`PixAdapter` e `CartaoAdapter` adaptam as APIs legadas para a interface `Pagamento` usada pelo cliente.

```java
// Adapter
class PixAdapter implements Pagamento {
	private PagamentoPix pix;
	private Passageiro passageiro;

	public PixAdapter(PagamentoPix pix) {
		this.pix = pix;
	}

	@Override
	public void setDados(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	@Override
	public void pagar(double valor) {
		pix.pagarComPix(passageiro.getChavePix(), valor);
	}
}

// Adapter
class CartaoAdapter implements Pagamento {
	private PagamentoCartao cartao;
	private Passageiro passageiro;

	public CartaoAdapter(PagamentoCartao cartao) {
		this.cartao = cartao;
	}

	@Override
	public void setDados(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	@Override
	public void pagar(double valor) {
		cartao.pagarComCartao(passageiro.getNumeroCartao(), valor);
	}
}
```

## Conclusão


##  Referências 

> Refactoring.Guru - Padrão Adapter: https://refactoring.guru/pt-br/design-patterns/adapter. 

> Slides da Prof.ª Milene – Aula GoFs Estruturais UnB (2025).

##  Histórico de Versões
| Versão | Data       | Descrição                             | Autor                                                 | Revisor                                               |
| :----: | ---------- | ---------------------------           | ----------------------------------------------------- | ----------------------------------------------------- |
| `1.0`  | 16/10/2025 | Criação do documento                  |  [Arthur](https://github.com/Tutzs)                   |                                                       | 