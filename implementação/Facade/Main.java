import entity.Motorista;
import entity.CaronaDetalhes;
import entity.Carona;
import facade.CaronaFCTE_FacadeImpl;
import service.CaronaService;
import service.NotificacaoService;
import service.ChatService;

public class Main {
    public static void main(String[] args) {

        // 1. Instanciação dos Subsistemas (Serviços)
        CaronaService caronaService = new CaronaService();
        NotificacaoService notificacaoService = new NotificacaoService();
        ChatService chatService = new ChatService();

        // 2. Criação do Facade (Injetando os Subsistemas)
        CaronaFCTE_FacadeImpl facade = new CaronaFCTE_FacadeImpl(caronaService, notificacaoService, chatService);

        // 3. O Cliente (Motorista) e os Detalhes da Carona
        Motorista mauricio = new Motorista("M-222007021", "Mauricio Ferreira");
        CaronaDetalhes detalhes = new CaronaDetalhes("Rodoviária do Plano Piloto", "UnB FCTE - Gama", 2, 8.00);

        // 4. O Cliente interage APENAS com a Fachada
        System.out.println("Cliente (Motorista): Vou publicar uma carona.");
    Carona caronaPublicada = facade.oferecerNovaCarona(mauricio.id, detalhes);

        if (caronaPublicada != null) {
            System.out.println("\nCliente (Motorista): Carona criada com sucesso!");
            System.out.println("Detalhes: " + caronaPublicada.origem + " -> " + caronaPublicada.destino + " (ID: " + caronaPublicada.id + ")");
        } else {
            System.out.println("\nCliente (Motorista): Falha ao criar a carona.");
        }
    }
}