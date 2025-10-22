package test;

import concreteCreator.GeradorDeNotificacaoConcreteCreator;
import creator.NotificacaoFactoryCreator;

public final class FactoryMethod {
    private FactoryMethod() {
        // utility class
    }

    public static void main(String[] args) {
        NotificacaoFactoryCreator factory = new GeradorDeNotificacaoConcreteCreator();
        factory.dispararEnvio("SOLICITACAO_CARONA");
        factory.dispararEnvio("CONFIRMACAO_CARONA");
        factory.dispararEnvio("NOVA_MENSAGEM");
    }
}
