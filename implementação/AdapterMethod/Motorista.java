class Motorista extends Usuario {
    public Motorista(String nome) { super(nome); }
    public Carona criarCarona(String origem, String destino, double valor) {
        System.out.println(this.nome + " criou uma carona de " + origem + " para " + destino + " por R$" + valor);
        return new Carona(origem, destino, valor);
    }
}