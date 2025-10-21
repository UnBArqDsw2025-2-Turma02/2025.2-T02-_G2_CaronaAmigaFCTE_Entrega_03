package creator;

import product.INotificacaoProduct;

public abstract class NotificacaoFactoryCreator {

    protected abstract INotificacaoProduct criarNotificacao(String tipo);

    public void dispararEnvio(String tipo) {
        INotificacaoProduct notificacao = criarNotificacao(tipo);
        if (notificacao != null) {
            notificacao.enviar();
        } else {
            System.err.println("Nao foi possivel criar a notificacao para o tipo: " + tipo);
        }
    }
}
