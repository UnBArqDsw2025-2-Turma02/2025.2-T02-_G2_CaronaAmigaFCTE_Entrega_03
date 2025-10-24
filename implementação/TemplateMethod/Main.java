import java.util.*;
import java.time.*;

class Usuario {
    int id;
    String nome;
    String email;
    public Usuario(int id, String nome, String email) {
        this.id = id; this.nome = nome; this.email = email;
    }
}

class Motorista extends Usuario {
    public Motorista(int id, String nome, String email) { super(id, nome, email); };
    void confirmarPassageiro(Passageiro p) {
        System.out.println("Motorista " + this.nome + " confirmou passageiro " + p.nome);
    }
}

class Passageiro extends Usuario {
    public Passageiro(int id, String nome, String email) { super(id, nome, email); }
    Pagamento pagarCarona(Carona carona, String metodo) {
        Pagamento pagamento = new Pagamento(0, 0.0, LocalDate.now(), LocalTime.now(), metodo, "CRIADO");
        PagamentoTemplate processador = switch (metodo.toLowerCase()) {
            case "pix" -> new PixPagamento();
            case "cartao" -> new CartaoCreditoPagamento();
            case "dinheiro" -> new DinheiroPagamento();
            default -> throw new IllegalArgumentException("Método não suportado: " + metodo);
        };
        processador.processarPagamento(pagamento, carona, this);
        return pagamento;
    }
}

class Carona {
    String origem, destino;
    LocalDate data;
    LocalTime horario;
    double precoBase;
    Motorista motorista;
    List<Passageiro> passageiros = new ArrayList<>();

    public Carona(String origem, String destino, LocalDate data, LocalTime horario, double precoBase, Motorista m) {
        this.origem = origem; this.destino = destino; this.data = data; this.horario = horario;
        this.precoBase = precoBase; this.motorista = m;
    }

    boolean adicionarPassageiro(Passageiro p) { return passageiros.add(p); }
}

class Pagamento {
    int idPagamento;
    double valor;
    LocalDate data;
    LocalTime horario;
    String metodo;
    String status;

    public Pagamento(int idPagamento, double valor, LocalDate data, LocalTime horario, String metodo, String status) {
        this.idPagamento = idPagamento; this.valor = valor; this.data = data;
        this.horario = horario; this.metodo = metodo; this.status = status;
    }
    void confirmar() { this.status = "CONFIRMADO"; }
    void falhar(String motivo) { this.status = "FALHOU: " + motivo; }
}

abstract class PagamentoTemplate {

    public final void processarPagamento(Pagamento pagamento, Carona carona, Usuario pagador) {
        try {
            validarEntrada(pagamento, carona, pagador);
            double base = calcularValorBase(carona);
            double comTaxas = calcularTaxas(base);
            double finalComDescontos = aplicarDescontos(comTaxas, pagador);
            debitar(pagamento, pagador, finalComDescontos);
            registrar(pagamento, carona, finalComDescontos);
            notificar(pagador, resumoSucesso(pagamento, finalComDescontos));
        } catch (Exception e) {
            pagamento.falhar(e.getMessage());
            notificar(pagador, "Pagamento não concluído: " + e.getMessage());
        }
    }

    protected void validarEntrada(Pagamento pagamento, Carona carona, Usuario pagador) {
        if (carona == null) throw new IllegalArgumentException("Carona inválida.");
        if (pagador == null) throw new IllegalArgumentException("Pagador inválido.");
        if (pagamento == null) throw new IllegalArgumentException("Pagamento inválido.");
    }

    protected double calcularValorBase(Carona carona) {
        return carona.precoBase;
    }

    protected abstract double calcularTaxas(double valorBase);
    protected double aplicarDescontos(double valor, Usuario pagador) { return valor; }
    protected abstract void debitar(Pagamento pagamento, Usuario pagador, double valorFinal) throws Exception;

    protected void registrar(Pagamento pagamento, Carona carona, double valorFinal) {
        pagamento.valor = valorFinal;
        pagamento.confirmar();
        System.out.printf(Locale.ROOT, "Registrado pagamento de R$ %.2f via %s%n", valorFinal, pagamento.metodo);
    }

    protected void notificar(Usuario pagador, String mensagem) {
        System.out.println("Notificação para " + pagador.nome + ": " + mensagem);
    }

    protected String resumoSucesso(Pagamento p, double valor) {
        return "Pagamento " + p.metodo + " confirmado. Valor final: R$ " + String.format(Locale.ROOT, "%.2f", valor);
    }
}

class PixPagamento extends PagamentoTemplate {
    protected double calcularTaxas(double valorBase) {
        return valorBase + 0.01;
    }
    protected void debitar(Pagamento pagamento, Usuario pagador, double valorFinal) {
        pagamento.metodo = "PIX";
        System.out.printf(Locale.ROOT, "Debitado via Pix: R$ %.2f do usuário %s%n", valorFinal, pagador.nome);
    }
}

class CartaoCreditoPagamento extends PagamentoTemplate {
    protected double calcularTaxas(double valorBase) {
        return (valorBase * 1.0299) + 0.40;
    }
    protected double aplicarDescontos(double valor, Usuario pagador) {
        return valor * 0.98;
    }
    protected void debitar(Pagamento pagamento, Usuario pagador, double valorFinal) throws Exception {
        boolean aprovado = valorFinal < 1000;
        pagamento.metodo = "CARTAO";
        if (!aprovado) throw new Exception("Transação negada pela operadora.");
        System.out.printf(Locale.ROOT, "Autorizado no cartão: R$ %.2f para %s%n", valorFinal, pagador.nome);
    }
}

class DinheiroPagamento extends PagamentoTemplate {
    protected double calcularTaxas(double valorBase) {
        return valorBase;
    }
    protected void debitar(Pagamento pagamento, Usuario pagador, double valorFinal) {
        pagamento.metodo = "DINHEIRO";
        System.out.printf(Locale.ROOT, "Pagamento em dinheiro combinado: R$ %.2f%n", valorFinal);
    }
    protected void registrar(Pagamento pagamento, Carona carona, double valorFinal) {
        pagamento.valor = valorFinal;
        pagamento.status = "AGUARDANDO_RECEBIMENTO";
        System.out.println("Registro de pagamento em dinheiro (aguardando confirmação do motorista).");
    }
}

public class Main {
    public static void main(String[] args) {
        Motorista m = new Motorista(1, "Ana Motorista", "ana@gmail.com");
        Carona carona = new Carona("UnB", "Asa Sul", LocalDate.now(), LocalTime.of(18, 0), 25.00, m);
        Passageiro p = new Passageiro(2, "Lucas", "Lucas@gmail.com");

        Pagamento pgPix = p.pagarCarona(carona, "pix");
        Pagamento pgCartao = p.pagarCarona(carona, "cartao");
        Pagamento pgDinheiro = p.pagarCarona(carona, "dinheiro");

        System.out.println("Status PIX: " + pgPix.status);
        System.out.println("Status CARTAO: " + pgCartao.status);
        System.out.println("Status DINHEIRO: " + pgDinheiro.status);
    }
}
