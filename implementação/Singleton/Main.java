public class Main {

    public static void main(String[] args) {
        System.out.println("--- Teste do Padrão Singleton ---");
        System.out.println("Tentando obter a primeira instância do gerenciador...");

        GerenciadorDeConfiguracao config1 = GerenciadorDeConfiguracao.getInstance();

        System.out.println("URL do Banco de Dados (da primeira instância): " + config1.getUrlBancoDeDados());
        System.out.println("\nTentando obter a segunda instância do gerenciador...");

        GerenciadorDeConfiguracao config2 = GerenciadorDeConfiguracao.getInstance();

        System.out.println("API Key de Mapas (da segunda instância): " + config2.getApiKeyMapas());

        System.out.println("\nVerificando se as instâncias são as mesmas...");
        if (config1 == config2) {
            System.out.println("Sucesso! As duas variáveis contêm a mesma e única instância.");
        } else {
            System.out.println("Falha! O padrão Singleton não foi implementado corretamente.");
        }
    }
}