package concreteProduct;

import product.INotificacaoProduct;

public class NotificacaoNovaMensagemConcreteProduct implements INotificacaoProduct {
    @Override
    public void enviar() {
        System.out.println("Enviando notificacao: Voce tem uma nova mensagem no chat!");
    }
}
