package builder;

import java.util.List;

/**
 * Interface do Builder.
 * Métodos retornam o próprio builder para permitir encadeamento fluente.
 */
public interface CaronaBuilder {
    CaronaBuilder reset();
    CaronaBuilder buildRotas(String origem, String destino);
    CaronaBuilder buildDataHora(String data, String horario);
    CaronaBuilder buildPreco(double preco);
    CaronaBuilder buildServicosOpcionais(List<String> servicos);
    Carona getResult();
}
