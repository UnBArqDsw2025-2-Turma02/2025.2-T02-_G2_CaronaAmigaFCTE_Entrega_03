public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando demonstração do Proxy Location Service");

        
        LocationService realService = new GoogleGeoService();

        
        LocationService proxy = new ProxyLocation(realService);

        System.out.println("\n--- Teste de Geocode ---");
        System.out.println("1. Geocode Esplanada: " + proxy.geocode("Esplanada dos Ministérios, Brasília"));
        Thread.sleep(100); 

        System.out.println("2. Geocode Esplanada (cache): " + proxy.geocode("Esplanada dos Ministérios, Brasília"));
        Thread.sleep(100);

        System.out.println("3. Geocode Praça: " + proxy.geocode("Praça dos Três Poderes, Brasília"));
        Thread.sleep(100);

        System.out.println("4. Geocode Torre: " + proxy.geocode("Torre de TV de Brasília"));
        Thread.sleep(100);

        System.out.println("\n--- Aguardando 65 segundos para resetar o Rate Limiter ---");
        Thread.sleep(65000); 

        System.out.println("\n--- Teste de Geocode após reset do Rate Limiter ---");
        System.out.println("5. Geocode Esplanada (após reset): " + proxy.geocode("Esplanada dos Ministérios, Brasília"));
        Thread.sleep(100);

        System.out.println("\n--- Teste de Reverse Geocode ---");
        System.out.println("1. Reverse -15.799722,-47.864722: " + proxy.reverse("-15.799722,-47.864722"));
        Thread.sleep(100);

        System.out.println("2. Reverse -15.799722,-47.864722 (cache): " + proxy.reverse("-15.799722,-47.864722"));
        Thread.sleep(100);

        System.out.println("3. Reverse -15.799778,-47.863889: " + proxy.reverse("-15.799778,-47.863889"));
        Thread.sleep(100);

        System.out.println("\n--- Aguardando 65 segundos para resetar o Rate Limiter ---");
        Thread.sleep(65000);

        System.out.println("\n--- Teste de Rota ---");
        
        String rota1 = "Esplanada dos Ministérios, Brasília_Praça dos Três Poderes, Brasília";
        String rota2 = "Esplanada dos Ministérios, Brasília_Torre de TV de Brasília";

        System.out.println("1. Rota Esplanada-Praça: " + proxy.route(rota1));
        Thread.sleep(100);

        System.out.println("2. Rota Esplanada-Praça (cache): " + proxy.route(rota1));
        Thread.sleep(100);

        System.out.println("3. Rota Esplanada-Torre: " + proxy.route(rota2));
        Thread.sleep(100);

        System.out.println("\nDemonstração concluída.");
    }
}

