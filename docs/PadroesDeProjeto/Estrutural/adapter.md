# Adapter

## Introdução

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

## Rastreabilidade

O Padrão Adapter está relacionado aos artefatos de Modelagem Estática do projeto, conforme a rastreabilidade abaixo:

| Artefato UML | Padrão | Função no Projeto / Rastreabilidade |
| :--- | :--- | :--- |
| **[Diagrama de Classes](https://unbarqdsw2025-2-turma02.github.io/2025.2-T02-_G2_CaronaAmigaFCTE_Entrega_02/#/Modelagem/2.1.ModelagemEstatica)** | Adapter | O padrão Adapter é aplicado na **Interface de Pagamento**. O Diagrama de Classes Unificado mostra a classe `Pagamento`, que é a interface alvo (Target) que os adaptadores (`PixAdapter` e `CartaoAdapter`) implementam para permitir que o cliente use métodos de pagamento incompatíveis. |

## Modelagem

<div style="width: 100%; height: 600px; margin: 10px; position: relative;"><iframe allowfullscreen frameborder="0" style="width:100%; height:600px" src="https://lucid.app/documents/embedded/887e2308-dd02-40e3-a0e7-73316f2226d8" id="Rwn2DSu_3.28"></iframe></div>

<p align="center"><b>Fonte: </b>Autoria de <a href="https://github.com/kalebmacedo"> Kaleb Macedo</a>, <a href="https://github.com/LucasMF1"> Lucas Monteiro</a>, <a href="https://github.com/bolzanMGB"> Othavio Bolzan</a></p>

## Código

### Interface alvo (Target)

```java
// Target
public interface Pagamento {
	void setDados(Passageiro passageiro); // recebe os dados do passageiro
	void pagar(double valor);
}
```

### Adaptees (APIs existentes)

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

<p align="center"><b>Fonte: </b>Autoria de <a href="https://github.com/kalebmacedo"> Kaleb Macedo</a>, <a href="https://github.com/LucasMF1"> Lucas Monteiro</a>, <a href="https://github.com/bolzanMGB"> Othavio Bolzan</a></p>


## Conclusão

O Adapter é uma solução prática para integrar APIs incompatíveis sem a necessidade de modificar o código legado. Ele favorece a reutilização e o desacoplamento, permitindo que o cliente trabalhe contra uma interface uniforme (Target) enquanto os Adaptees mantêm suas implementações próprias. Contudo, a introdução de adaptadores adiciona camadas de delegação — mantenha-os simples, bem documentados e com responsabilidade única para evitar complexidade desnecessária.

No projeto, `PixAdapter` e `CartaoAdapter` mostram como encapsular diferentes mecanismos de pagamento por trás da interface `Pagamento`, simplificando o uso pelo cliente.

##  Referências 

> Refactoring.Guru - Padrão Adapter: https://refactoring.guru/pt-br/design-patterns/adapter. 

> Slides da Prof.ª Milene – Aula GoFs Estruturais UnB (2025).

##  Histórico de Versões

| Versão | Data       | Descrição                              | Autor                                                 | Revisor                                               |
| :----: | ---------- | ---------------------------            | ----------------------------------------------------- | ----------------------------------------------------- |
| `1.0`  | 16/10/2025 | Criação do documento                   |  [Arthur](https://github.com/Tutzs)                   |                                                       |
| `1.1`  | 20/10/2025 | Início da documentação sobre o Adapter |  [Amanda](https://github.com/mandicrz)                   |                                                       | 
| `1.2`  | 22/10/2025 | Adição da modelagem Adapter e reescrita de alguns textos |  [Amanda](https://github.com/mandicrz) |                                                       |  
| `1.3`  | 23/10/2025 | Adição dos códigos na documentação  |  [Arthur](https://github.com/Tutzs)                   |                                                       | 
| `1.4`  | 23/10/2025 | Adição da conclusão e revisão final |  [Cauã](https://github.com/caua08)                   			 |                                                       | 