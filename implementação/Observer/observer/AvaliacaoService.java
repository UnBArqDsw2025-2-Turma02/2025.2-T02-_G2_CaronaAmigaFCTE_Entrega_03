package observer;

public class AvaliacaoService implements CaronaObserver {
    @Override
    public void atualizar(Carona carona, Status anterior, Status atual) {
        if (atual == Status.FINALIZADA) {
            System.out.println("[AvaliacaoService] Carona " + carona.getIdCarona()
                    + " finalizada. Acionando módulo de avaliação de motorista e passageiros.");
        } else {
            System.out.println("[AvaliacaoService] Carona " + carona.getIdCarona()
                    + " alterada para " + atual + ". Nenhuma ação de avaliação necessária.");
        }
    }
}
