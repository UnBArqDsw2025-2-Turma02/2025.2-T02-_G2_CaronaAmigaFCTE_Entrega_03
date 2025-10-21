package builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Produto do padrão Builder.
 * A classe é essencialmente imutável após construída.
 * Não expõe setters públicos para evitar estados inválidos depois da criação.
 */
public final class Carona {
    private final String origem;
    private final String destino;
    private final String data;     // Mantido como String por simplicidade didática
    private final String horario;  // idem
    private final double preco;
    private final List<String> servicosOpcionais; // Lista defensivamente copiada

    /**
     * Construtor é package-private para só ser chamado pelo Builder.
     * Recebe todos os campos prontos e realiza validações finais.
     */
    Carona(String origem,
           String destino,
           String data,
           String horario,
           double preco,
           List<String> servicosOpcionais) {

        // Validações mínimas de invariantes do domínio
        if (origem == null || origem.isBlank()) {
            throw new IllegalArgumentException("origem é obrigatória");
        }
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("destino é obrigatório");
        }
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("data é obrigatória");
        }
        if (horario == null || horario.isBlank()) {
            throw new IllegalArgumentException("horario é obrigatório");
        }
        if (preco < 0.0) {
            throw new IllegalArgumentException("preço não pode ser negativo");
        }

        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.horario = horario;
        this.preco = preco;

        // Cópia defensiva para garantir imutabilidade externa
        List<String> copia = new ArrayList<>();
        if (servicosOpcionais != null) {
            for (String s : servicosOpcionais) {
                if (s != null && !s.isBlank()) {
                    copia.add(s);
                }
            }
        }
        this.servicosOpcionais = Collections.unmodifiableList(copia);
    }

    // Getters somente-leitura
    public String getOrigem() { return origem; }
    public String getDestino() { return destino; }
    public String getData() { return data; }
    public String getHorario() { return horario; }
    public double getPreco() { return preco; }

    /**
     * Retorna uma visão imutável da lista de serviços.
     * Não permite alterar o conteúdo de fora.
     */
    public List<String> getServicosOpcionais() { return servicosOpcionais; }

    /**
     * Representação textual padronizada do objeto.
     * Preferível a fazer I/O dentro do domínio.
     */
    @Override
    public String toString() {
        String servicos = servicosOpcionais.isEmpty()
                ? "Nenhum"
                : String.join(", ", servicosOpcionais);
        return "\n--- DETALHES DA CARONA ---\n" +
               "   Rota: " + origem + " -> " + destino + "\n" +
               "   Data/Hora: " + data + " às " + horario + "\n" +
               "   Preço por passageiro: R$ " + String.format("%.2f", preco) + "\n" +
               "   Serviços Opcionais: " + servicos + "\n" +
               "--------------------------";
    }

    public void displayInfo() {
        System.out.println(this.toString());
    }
}
