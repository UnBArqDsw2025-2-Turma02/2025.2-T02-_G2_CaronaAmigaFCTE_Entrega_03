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

