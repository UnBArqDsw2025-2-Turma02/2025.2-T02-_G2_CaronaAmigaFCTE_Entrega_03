package builder;

import java.util.List;
import java.util.Objects;

/**
 * Director do padrão Builder.
 * Encapsula "receitas" de construção para variações de Carona.
 * Não guarda dados do domínio. Apenas orquestra chamadas no Builder.
 */
public class CaronaDirector {

    private final CaronaBuilder builder;

    /**
     * Injeta o builder no construtor. Evita uso do Director sem configuração.
     */
    public CaronaDirector(CaronaBuilder builder) {
        this.builder = Objects.requireNonNull(builder, "builder é obrigatório");
    }

    /**
     * Receita "completa": rota, data/hora, preço e serviços.
     * Os parâmetros eliminam valores fixos e deixam o Director reutilizável.
     */
    public void construirCaronaCompleta(String origem,
                                        String destino,
                                        String data,
                                        String horario,
                                        double preco,
                                        List<String> servicos) {
        builder.reset()
               .buildRotas(origem, destino)
               .buildDataHora(data, horario)
               .buildPreco(preco)
               .buildServicosOpcionais(servicos);
    }

    /**
     * Receita "econômica": sem serviços, apenas os essenciais.
     */
    public void construirCaronaEconomica(String origem,
                                         String destino,
                                         String data,
                                         String horario,
                                         double preco) {
        builder.reset()
               .buildRotas(origem, destino)
               .buildDataHora(data, horario)
               .buildPreco(preco)
               .buildServicosOpcionais(List.of());
    }
}
