package observer;

public class NotificacaoService implements CaronaObserver {
    @Override
    public void atualizar(Carona carona, Status anterior, Status atual) {
        System.out.println("[NotificacaoService] Carona " + carona.getIdCarona()
                + " mudou de " + anterior + " para " + atual
                + " (de " + carona.getOrigem() + " para " + carona.getDestino() + ")");
    }
}
