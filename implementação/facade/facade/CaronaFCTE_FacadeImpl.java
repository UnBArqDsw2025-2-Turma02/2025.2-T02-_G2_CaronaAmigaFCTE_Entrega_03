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
    public Carona OferecerNovaCarona(String motoristaId, CaronaDetalhes detalhes) {
        System.out.println("\n--- [FACADE] Iniciando processo de Oferta de Carona ---");

        // 1. Validação e Criação da Carona
        if (!caronaService.ValidarMotorista(motoristaId)) {
            System.out.println("!!! [ERRO] Validação do motorista falhou. Carona não criada.");
            return null;
        }
        
        Carona novaCarona = caronaService.CriarCarona(motoristaId, detalhes);
        
        // 2. Criação do Chat (operação de subsistema)
        Chat novoChat = chatService.CriarNovoChat(novaCarona);

        // 3. Notificação do Motorista e do Sistema (operação de subsistema)
        notificacaoService.EnviarNotificacaoSucesso("Motorista", novaCarona);
        notificacaoService.EnviarAlertaPassageiros(novaCarona);

        System.out.println("--- [FACADE] Carona Publicada. ID do Chat: " + novoChat.id + " ---");
        return novaCarona;
    }
}