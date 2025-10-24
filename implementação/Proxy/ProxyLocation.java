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

