package service;
import entity.Carona;
import entity.CaronaDetalhes;

public class CaronaService {

    public boolean ValidarMotorista(String motoristaId) {
        System.out.println("-> CaronaService: Validando motorista ID " + motoristaId + "...");
        // Checa se o motorista está cadastrado e tem veículo válido.
        return true; 
    }

    public Carona CriarCarona(String motoristaId, CaronaDetalhes detalhes) {
        System.out.println("-> CaronaService: Criando carona de " + detalhes.origem + " para " + detalhes.destino + ".");
        // Persiste a carona no banco de dados e gera um ID.
        String novoId = "CRN-" + (System.currentTimeMillis() % 1000);
        return new Carona(novoId, detalhes.origem, detalhes.destino);
    }
}



