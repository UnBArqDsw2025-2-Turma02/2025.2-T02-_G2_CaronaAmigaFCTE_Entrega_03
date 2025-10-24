package concreteProduct;

import product.INotificacaoProduct;

public class NotificacaoConfirmacaoCaronaConcreteProduct implements INotificacaoProduct {
    @Override
    public void enviar() {
        System.out.println("Enviando notificacao: O motorista confirmou a sua carona. Boa viagem!");
    }
}
