package builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação concreta do Builder.
 * Acumula dados em campos próprios e instancia o produto apenas em getResult().
 * Este fluxo facilita validação e imutabilidade.
 */
public class CaronaBuilderImpl implements CaronaBuilder {

    // Estado interno temporário do processo de construção
    private String origem;
    private String destino;
    private String data;
    private String horario;
    private Double preco; // Wrapper para detectar se foi definido
    private List<String> servicos;

    public CaronaBuilderImpl() {
        reset();
    }

    @Override
    public CaronaBuilder reset() {
        // Reinicia todo o estado acumulado
        this.origem = null;
        this.destino = null;
        this.data = null;
        this.horario = null;
        this.preco = null;
        this.servicos = new ArrayList<>();
        return this;
    }

    @Override
    public CaronaBuilder buildRotas(String origem, String destino) {
        // Validação mínima antecipada para feedback precoce a quem usa o builder
        if (origem == null || origem.isBlank()) {
            throw new IllegalArgumentException("origem é obrigatória");
        }
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("destino é obrigatório");
        }
        this.origem = origem;
        this.destino = destino;
        return this;
    }

    @Override
    public CaronaBuilder buildDataHora(String data, String horario) {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("data é obrigatória");
        }
        if (horario == null || horario.isBlank()) {
            throw new IllegalArgumentException("horario é obrigatório");
        }
        this.data = data;
        this.horario = horario;
        return this;
    }

    @Override
    public CaronaBuilder buildPreco(double preco) {
        if (preco < 0.0) {
            throw new IllegalArgumentException("preço não pode ser negativo");
        }
        this.preco = preco;
        return this;
    }

    @Override
    public CaronaBuilder buildServicosOpcionais(List<String> servicos) {
        this.servicos.clear();
        if (servicos != null) {
            for (String s : servicos) {
                if (s != null && !s.isBlank()) {
                    this.servicos.add(s);
                }
            }
        }
        return this;
    }

    @Override
    public Carona getResult() {
        if (origem == null) throw new IllegalStateException("origem não definida");
        if (destino == null) throw new IllegalStateException("destino não definido");
        if (data == null) throw new IllegalStateException("data não definida");
        if (horario == null) throw new IllegalStateException("horario não definido");
        if (preco == null) throw new IllegalStateException("preço não definido");

        Carona produto = new Carona(
                origem,
                destino,
                data,
                horario,
                preco,
                new ArrayList<>(servicos) // cópia para isolamento
        );

        reset();
        return produto;
    }
}
