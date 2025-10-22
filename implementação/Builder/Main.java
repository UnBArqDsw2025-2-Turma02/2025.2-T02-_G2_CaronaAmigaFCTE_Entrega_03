import java.util.List;
import builder.*;

/**
 * Ponto de entrada de demonstração do padrão Builder aplicado ao domínio "Carona".
 * Mantém a saída de console somente aqui, fora do domínio.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Cliente: Demonstração do padrão Builder para CaronaAmigaFCTE.");

        CaronaBuilder builder = new CaronaBuilderImpl();
        CaronaDirector director = new CaronaDirector(builder);

        director.construirCaronaCompleta(
                "Rodoviária do Plano Piloto",
                "UnB FCTE - Gama",
                "01/11/2025",
                "08:15",
                12.50,
                List.of("Ar-condicionado", "Bagageiro", "Parada no caminho")
        );
        Carona carona1 = builder.getResult();
        System.out.println(carona1); 

        director.construirCaronaEconomica(
                "Taguatinga Centro",
                "UnB FCTE - Gama",
                "02/11/2025",
                "13:00",
                5.00
        );
        Carona carona2 = builder.getResult();
        carona2.displayInfo(); 

        Carona caronaCustom = new CaronaBuilderImpl()
                .buildRotas("ParkShopping", "UnB FCTE - Gama")
                .buildDataHora("03/11/2025", "07:05")
                .buildPreco(10.00)
                .buildServicosOpcionais(List.of("Só ida", "Sem paradas"))
                .getResult();
        System.out.println(caronaCustom);
    }
}
