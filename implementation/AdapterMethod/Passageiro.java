class Passageiro extends Usuario {
    private String chavePix;
    private String numeroCartao;

    public Passageiro(String nome, String chavePix, String numeroCartao) {
        super(nome);
        this.chavePix = chavePix;
        this.numeroCartao = numeroCartao;
    }

    public String getChavePix() { return chavePix; }
    public String getNumeroCartao() { return numeroCartao; }

    // Escolhe uma carona para entrar
    public void escolherCarona(Carona carona) {
        carona.adicionarPassageiro(this);
    }

    // Escolhe método de pagamento
    public Pagamento escolherPagamento(Pagamento pagamento) {
        pagamento.setDados(this);
        return pagamento;
    }
}
