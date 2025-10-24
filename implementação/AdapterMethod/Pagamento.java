// Target
public interface Pagamento {
    void setDados(Passageiro passageiro); // recebe os dados do passageiro
    void pagar(double valor);
}