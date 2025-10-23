# Facade 

## Definição

O padrão Facade é um dos padrões estruturais do catálogo GoF que cria uma interface de nível mais alto para um subsistema complexo, tornando-o mais fácil de usar pela camada cliente.

> Segundo o Refactoring.Guru:
> 
> "O Facade fornece uma interface simplificada para um subsistema complexo", permitindo que clientes interajam com funcionalidades agrupadas sem conhecer os detalhes internos.
>
> Fonte : [Refactoring.Guru](https://refactoring.guru/pt-br/design-patterns/facade)

Esse padrão é especialmente útil quando o sistema possui diversos módulos que precisam cooperar. Seus principais benefícios são:

- Tornar o código do cliente mais limpo e legível;
- Esconder detalhes de implementação do subsistema;
- Facilitar manutenção e evolução do sistema.

Um cenário típico seria uma plataforma educacional em que a fachada encapsula a lógica de iniciar um módulo, registrar respostas e atualizar o progresso do aluno em uma única chamada.

## Metodologia

## Modelagem

<div style="width: 100%; height: 600px; margin: 10px; position: relative;"><iframe allowfullscreen frameborder="0" style="width:640px; height:480px" src="https://lucid.app/documents/embedded/b1670c4a-bc86-47a6-8fd0-64f53f09ba71" id="gck2YP.hdzHg"></iframe></div>

## Código

#### Carona.java

```java
package entity;

public class Carona {
	public final String id;
	public final String origem;
	public final String destino;

	public Carona(String id, String origem, String destino) {
		this.id = id;
		this.origem = origem;
		this.destino = destino;
	}
}
```

#### CaronaDetalhes.java

```java
package entity;

public class CaronaDetalhes {
	public String origem;
	public String destino;
	public int vagas;
	public double preco;

	public CaronaDetalhes(String origem, String destino, int vagas, double preco) {
		this.origem = origem;
		this.destino = destino;
		this.vagas = vagas;
		this.preco = preco;
	}
}
```

#### Chat.java

```java
package entity;

public class Chat {
	public String id;

	public Chat(String id) {
		this.id = id;
	}
}
```

#### Motorista.java

```java
package entity;

public class Motorista {
	public String id;
	public String nome;

	public Motorista(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}
}
```

#### CaronaFCTE_FacadeImpl.java

```java
package facade;
import entity.Carona;
import entity.CaronaDetalhes;
import entity.Chat;
import service.CaronaService;
import service.NotificacaoService;
import service.ChatService;

public class CaronaFCTE_FacadeImpl implements FacadeCaronaFCTE {

	// Referências aos Subsistemas
	private final CaronaService caronaService;
	private final NotificacaoService notificacaoService;
	private final ChatService chatService;

	// Construtor Facade (Injeta as dependências dos serviços)
	public CaronaFCTE_FacadeImpl(CaronaService caronaService, NotificacaoService notificacaoService, ChatService chatService) {
		this.caronaService = caronaService;
		this.notificacaoService = notificacaoService;
		this.chatService = chatService;
	}

	// Método Facade: Orquestra a operação complexa
	@Override
	public Carona oferecerNovaCarona(String motoristaId, CaronaDetalhes detalhes) {
		System.out.println("\n--- [FACADE] Iniciando processo de Oferta de Carona ---");

		// 1. Validação e Criação da Carona
		if (!caronaService.validarMotorista(motoristaId)) {
			System.out.println("!!! [ERRO] Validação do motorista falhou. Carona não criada.");
			return null;
		}
        
		Carona novaCarona = caronaService.criarCarona(motoristaId, detalhes);
        
		// 2. Criação do Chat (operação de subsistema)
		Chat novoChat = chatService.criarNovoChat(novaCarona);

		// 3. Notificação do Motorista e do Sistema (operação de subsistema)
		notificacaoService.enviarNotificacaoSucesso("Motorista", novaCarona);
		notificacaoService.enviarAlertaPassageiros(novaCarona);

		System.out.println("--- [FACADE] Carona Publicada. ID do Chat: " + novoChat.id + " ---");
		return novaCarona;
	}
}
```

#### FacadeCaronaFCTE.java

```java
package facade;
import entity.Carona;
import entity.CaronaDetalhes;

public interface FacadeCaronaFCTE {
	// Método de alto nível que orquestra a criação de uma carona completa
	public Carona oferecerNovaCarona(String motoristaId, CaronaDetalhes detalhes);
}
```

#### CaronaService.java

```java
package service;
import entity.Carona;
import entity.CaronaDetalhes;

public class CaronaService {

	public boolean validarMotorista(String motoristaId) {
		System.out.println("-> CaronaService: Validando motorista ID " + motoristaId + "...");
		// Checa se o motorista está cadastrado e tem veículo válido.
		return true; 
	}

	public Carona criarCarona(String motoristaId, CaronaDetalhes detalhes) {
		System.out.println("-> CaronaService: Criando carona de " + detalhes.origem + " para " + detalhes.destino + ".");
		// Persiste a carona no banco de dados e gera um ID.
		String novoId = "CRN-" + (System.currentTimeMillis() % 1000);
		return new Carona(novoId, detalhes.origem, detalhes.destino);
	}
}
```

#### ChatService.java

```java
package service;

import entity.Carona;
import entity.Chat;

public class ChatService {
    
	public Chat criarNovoChat(Carona carona) {
		System.out.println("-> ChatService: Criando sala de chat para a Carona " + carona.id + ".");
		// Inicializa a sala de chat para o motorista e futuros passageiros.
		return new Chat("CHAT-" + carona.id);
	}
}
```

#### NotificacaoService.java

```java
package service;

import entity.Carona;

public class NotificacaoService {
    
	public void enviarNotificacaoSucesso(String destinatario, Carona carona) {
		System.out.println("-> NotificacaoService: Enviando notificação para " + destinatario + ".");
		System.out.println("   [ALERTA] Carona " + carona.id + " publicada com sucesso.");
	}
    
	public void enviarAlertaPassageiros(Carona carona) {
		System.out.println("-> NotificacaoService: Alertando passageiros sobre nova carona disponível de " + carona.origem + ".");
	}
}
```

### Vídeo


##  Referências 

> Refactoring.Guru - Padrão Facade: https://refactoring.guru/pt-br/design-patterns/facade. 

> Slides da Prof.ª Milene – Aula GoFs Estruturais UnB (2025).

> Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.

##  Histórico de Versões
| Versão | Data       | Descrição                             | Autor                                                 | Revisor                                               |
| :----: | ---------- | ---------------------------           | ----------------------------------------------------- | ----------------------------------------------------- |
| `1.0`  | 16/10/2025 | Criação do documento                  |  [Arthur](https://github.com/Tutzs)                   |                                                       | 