package concreteProduct;

import product.INotificacaoProduct;

public class NotificacaoSolicitacaoCaronaConcreteProduct implements INotificacaoProduct {
    @Override
    public void enviar() {
        System.out.println("Enviando notificacao: Novo passageiro solicitou um lugar na sua carona!");
    }
}
