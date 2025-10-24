// Adapter
class CartaoAdapter implements Pagamento {
    private PagamentoCartao cartao;
    private Passageiro passageiro;

    public CartaoAdapter(PagamentoCartao cartao) {
        this.cartao = cartao;
    }

    @Override
    public void setDados(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    @Override
    public void pagar(double valor) {
        cartao.pagarComCartao(passageiro.getNumeroCartao(), valor);
    }
}