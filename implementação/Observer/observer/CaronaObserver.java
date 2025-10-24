package observer;

public interface CaronaObserver {
    void atualizar(Carona carona, Status anterior, Status atual);
}
