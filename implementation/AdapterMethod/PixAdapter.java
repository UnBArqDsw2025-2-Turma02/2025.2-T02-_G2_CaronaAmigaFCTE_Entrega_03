// Adapter

class PixAdapter implements Pagamento {
    private PagamentoPix pix;
    private Passageiro passageiro;

    public PixAdapter(PagamentoPix pix) {
        this.pix = pix;
    }

    @Override
    public void setDados(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    @Override
    public void pagar(double valor) {
        pix.pagarComPix(passageiro.getChavePix(), valor);
    }
}