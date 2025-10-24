public class GerenciadorDeConfiguracao {
    private static GerenciadorDeConfiguracao instance;

    private String apiKeyMapas;
    private String urlBancoDeDados;

    private GerenciadorDeConfiguracao() {
        System.out.println("Inicializando configurações do sistema...");
        this.apiKeyMapas = "SUA_API_KEY_AQUI_12345";
        this.urlBancoDeDados = "jdbc:mysql://localhost:3306/caronaamigafcte";
    }

    public static synchronized GerenciadorDeConfiguracao getInstance() {
        if (instance == null) {
            instance = new GerenciadorDeConfiguracao();
        }
        return instance;
    }

    public String getApiKeyMapas() {
        return apiKeyMapas;
    }

    public String getUrlBancoDeDados() {
        return urlBancoDeDados;
    }
}