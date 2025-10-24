import observer.Carona;
import observer.Status;
import observer.NotificacaoService;
import observer.AvaliacaoService;

public class Main {
    public static void main(String[] args) {
        Carona carona1 = new Carona(101, "Rodoviária", "UnB");

        NotificacaoService notificacao = new NotificacaoService();
        AvaliacaoService avaliacao = new AvaliacaoService();

        carona1.adicionarObserver(notificacao);
        carona1.adicionarObserver(avaliacao);

        System.out.println("\n-- Confirmação --");
        carona1.setStatus(Status.CONFIRMADA);

        System.out.println("\n-- Finalização --");
        carona1.setStatus(Status.FINALIZADA);

        System.out.println("\n-- Removendo NotificacaoService --");
        carona1.removerObserver(notificacao);

        System.out.println("\n-- Atraso após remoção de um observer --");
        carona1.setStatus(Status.ATRASADA);

        System.out.println("\n-- Repetindo o mesmo estado (não notifica) --");
        carona1.setStatus(Status.ATRASADA);
    }
}
