# Proxy

## Introdução

O padrão Proxy faz parte dos padrões estruturais e fornece um representante para outro objeto, atuando como camada intermediária que controla o acesso ao objeto real. Essa abstração permite adicionar comportamentos como validação, cache ou registro de chamadas sem alterar a implementação original.

É especialmente útil quando a criação ou uso do objeto real é custoso — por exemplo, quando envolve acesso a banco de dados, leitura de arquivos ou comunicação remota — ou quando é necessário garantir condições antes de permitir a interação.

Na prática, o Proxy implementa a mesma interface do objeto verdadeiro, de modo que o cliente o utiliza de forma transparente, sem perceber a presença dessa camada adicional.

Usos comuns:
- Protection Proxy: impõe verificações de permissão antes do acesso;
- Virtual Proxy: evita criar objetos pesados até que sejam realmente necessários (lazy initialization);
- Logging Proxy: grava chamadas e parâmetros para auditoria ou depuração;
- Caching Proxy: armazena resultados de operações dispendiosas para reutilização;
- Remote Proxy: oferece uma representação local de objetos que residem em outro processo ou máquina.

## Metodologia


## Modelagem

<iframe frameborder="0" style="width:100%;height:600px" src="PadroesDeProjeto\assets\proxy3.drawio.html" allowtransparency="true" dark=0></iframe>

<details>
<summary>Primeira versão do Proxy</summary>
<img src="PadroesDeProjeto\assets\proxy3_primeira_versao.drawio.png" alt="Primeira versão do diagrama Proxy" style="width:75%; border-radius:8px; margin-top:10px;">
</details>

## Código

#### LocationService.java

```java
public interface LocationService {
	String geocode(String endereco);
	String reverse(String latLon);
	String route(String origDest);
}
```

#### GoogleGeoService.java

```java
import java.util.HashMap;
import java.util.Map;

public class GoogleGeoService implements LocationService {

	private Map<String, String> geocodeData;
	private Map<String, String> reverseData;
	private Map<String, String> routeData;

	public GoogleGeoService() {
        
		geocodeData = new HashMap<>();
		geocodeData.put("Esplanada dos Ministérios, Brasília", "-15.799722,-47.864722");
		geocodeData.put("Praça dos Três Poderes, Brasília", "-15.799778,-47.863889");
		geocodeData.put("Torre de TV de Brasília", "-15.7865,-47.8867");

		reverseData = new HashMap<>();
		reverseData.put("-15.799722,-47.864722", "Esplanada dos Ministérios");
		reverseData.put("-15.799778,-47.863889", "Praça dos Três Poderes");
		reverseData.put("-15.7865,-47.8867", "Torre de TV de Brasília");

		routeData = new HashMap<>();
		routeData.put("Esplanada dos Ministérios, Brasília_Praça dos Três Poderes, Brasília", "Rota: 5 min, 1.2 km");
		routeData.put("Esplanada dos Ministérios, Brasília_Torre de TV de Brasília", "Rota: 10 min, 4.5 km");
	}

	@Override
	public String geocode(String endereco) {
		System.out.println("GoogleGeoService: Buscando geocode para: " + endereco);
		try {
			Thread.sleep(500); 
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return geocodeData.getOrDefault(endereco, "Coordenadas não encontradas");
	}

	@Override
	public String reverse(String latLon) {
		System.out.println("GoogleGeoService: Buscando reverse geocode para: " + latLon);
		try {
			Thread.sleep(500); 
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return reverseData.getOrDefault(latLon, "Endereço não encontrado");
	}

	@Override
	public String route(String origDest) {
		System.out.println("GoogleGeoService: Buscando rota para: " + origDest);
		try {
			Thread.sleep(1000); 
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return routeData.getOrDefault(origDest, "Rota não encontrada");
	}
}
```

#### ProxyLocation.java

```java
import java.util.HashMap;
import java.util.Map;

public class ProxyLocation implements LocationService {
	private LocationService realService;
	private Map<String, String> cache = new HashMap<>();
	private long lastRequestTime = 0;
	private int requestCount = 0;
	private final int MAX_REQUESTS_PER_MINUTE = 2;
	private final long ONE_MINUTE_IN_MILLIS = 60 * 1000;

	public ProxyLocation(LocationService realService) {
		this.realService = realService;
	}

	private boolean checkAccess() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastRequestTime > ONE_MINUTE_IN_MILLIS) {
			lastRequestTime = currentTime;
			requestCount = 0;
		}

		if (requestCount < MAX_REQUESTS_PER_MINUTE) {
			requestCount++;
			System.out.println("ProxyLocation: Acesso permitido. Requisições na janela atual: " + requestCount);
			return true;
		} else {
			System.out.println("ProxyLocation: Acesso negado. Limite de requisições excedido.");
			return false;
		}
	}

	@Override
	public String geocode(String endereco) {
		if (cache.containsKey("geocode_" + endereco)) {
			System.out.println("ProxyLocation: Retornando geocode do cache para: " + endereco);
			return cache.get("geocode_" + endereco);
		}

		if (checkAccess()) {
			String result = realService.geocode(endereco);
			cache.put("geocode_" + endereco, result);
			return result;
		}
		return "Acesso negado ou erro ao geocodificar.";
	}

	@Override
	public String reverse(String latLon) {
		if (cache.containsKey("reverse_" + latLon)) {
			System.out.println("ProxyLocation: Retornando reverse geocode do cache para: " + latLon);
			return cache.get("reverse_" + latLon);
		}

		if (checkAccess()) {
			String result = realService.reverse(latLon);
			cache.put("reverse_" + latLon, result);
			return result;
		}
		return "Acesso negado ou erro ao reverter geocodificação.";
	}

	@Override
	public String route(String origDest) {
		if (cache.containsKey("route_" + origDest)) {
			System.out.println("ProxyLocation: Retornando rota do cache para: " + origDest);
			return cache.get("route_" + origDest);
		}

		if (checkAccess()) {
			String result = realService.route(origDest);
			cache.put("route_" + origDest, result);
			return result;
		}
		return "Acesso negado ou erro ao rotear.";
	}
}
```

## Conclusão

## Referências 

> REFRACTORING.GURU. Proxy. [S. l.], [s. d.]. Disponível em: https://refactoring.guru/design-patterns/proxy. 

> SOURCEMAKING. Proxy. [S. l.], [s. d.]. Disponível em: https://sourcemaking.com/design_patterns/proxy. 

##  Histórico de Versões
| Versão | Data       | Descrição                             | Autor                                                 | Revisor                                               |
| :----: | ---------- | ---------------------------           | ----------------------------------------------------- | ----------------------------------------------------- |
| `1.0`  | 16/10/2025 | Criação do documento                  |  [Arthur](https://github.com/Tutzs)                   |                                                       | 