import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Motorista motorista = new Motorista("João");
        Passageiro p1 = new Passageiro("Maria", null, "1234-5678-9012-3456");
        Passageiro p2 = new Passageiro("Carlos", "carlos@pix.com", null);

        // Motorista cria a carona
        Carona carona = motorista.criarCarona("A", "B", 100.0);

        // Passageiros escolhem a carona
        p1.escolherCarona(carona);
        p2.escolherCarona(carona);

        // Passageiros escolhem pagamento
        Pagamento pg1 = p1.escolherPagamento(new CartaoAdapter(new PagamentoCartao()));
        Pagamento pg2 = p2.escolherPagamento(new PixAdapter(new PagamentoPix()));

        // Dividir pagamento entre os passageiros da carona
        carona.dividirPagamento(Arrays.asList(pg1, pg2));
    }
}
