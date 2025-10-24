package facade;
import entity.Carona;
import entity.CaronaDetalhes;

public interface FacadeCaronaFCTE {
    // Método de alto nível que orquestra a criação de uma carona completa
    public Carona oferecerNovaCarona(String motoristaId, CaronaDetalhes detalhes);
}

