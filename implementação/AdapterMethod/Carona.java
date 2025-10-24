import java.util.ArrayList;
import java.util.List;

class Carona {
    private String origem;
    private String destino;
    private double valor;
    private List<Passageiro> passageiros = new ArrayList<>();

    public Carona(String origem, String destino, double valor) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
    }

    // Passageiro escolhe essa carona
    public void adicionarPassageiro(Passageiro passageiro) {
        passageiros.add(passageiro);
        System.out.println(passageiro.getNome() + " entrou na carona de " + origem + " para " + destino);
    }

    // Divide o pagamento entre os passageiros
    public void dividirPagamento(List<Pagamento> pagamentos) {
        double valorPorPessoa = valor / pagamentos.size();
        for (Pagamento p : pagamentos) {
            p.pagar(valorPorPessoa);
        }
    }

    public List<Passageiro> getPassageiros() {
        return passageiros;
    }
}
