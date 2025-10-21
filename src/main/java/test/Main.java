package test;

import concreteCreator.GeradorDeNotificacaoConcreteCreator;
import creator.NotificacaoFactoryCreator;

public final class Main {
    private Main() {
        // utility class
    }

    public static void main(String[] args) {
        NotificacaoFactoryCreator factory = new GeradorDeNotificacaoConcreteCreator();
        factory.dispararEnvio("SOLICITACAO_CARONA");
        factory.dispararEnvio("CONFIRMACAO_CARONA");
        factory.dispararEnvio("NOVA_MENSAGEM");
    }
}
