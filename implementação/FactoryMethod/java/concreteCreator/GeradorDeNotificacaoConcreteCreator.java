package concreteCreator;

import concreteProduct.NotificacaoConfirmacaoCaronaConcreteProduct;
import concreteProduct.NotificacaoNovaMensagemConcreteProduct;
import concreteProduct.NotificacaoSolicitacaoCaronaConcreteProduct;
import creator.NotificacaoFactoryCreator;
import product.INotificacaoProduct;


public class GeradorDeNotificacaoConcreteCreator extends NotificacaoFactoryCreator {

    @Override
    protected INotificacaoProduct criarNotificacao(String tipo) {
        if (tipo == null || tipo.isEmpty()) {
            return null;
        }
        switch (tipo.toUpperCase()) {
            case "SOLICITACAO_CARONA":
                return new NotificacaoSolicitacaoCaronaConcreteProduct();
            case "CONFIRMACAO_CARONA":
                return new NotificacaoConfirmacaoCaronaConcreteProduct();
            case "NOVA_MENSAGEM":
                return new NotificacaoNovaMensagemConcreteProduct();
            default:
                System.err.println("Aviso: Tipo de notificacao desconhecido: " + tipo);
                return null;
        }
    }
}
