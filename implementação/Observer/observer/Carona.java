package observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Carona {
    private final int idCarona;
    private final String origem;
    private final String destino;

    // Estado padronizado com enum
    private Status status;

    private final List<CaronaObserver> observers = new CopyOnWriteArrayList<>();

    public Carona(int idCarona, String origem, String destino) {
        this.idCarona = idCarona;
        this.origem = origem;
        this.destino = destino;
        this.status = Status.PENDENTE; // estado inicial seguro
    }

    public void adicionarObserver(CaronaObserver o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
        }
    }

    public void removerObserver(CaronaObserver o) {
        observers.remove(o);
    }

    private void notificarObservers(Status anterior, Status atual) {
        for (CaronaObserver o : observers) {
            o.atualizar(this, anterior, atual);
        }
    }

    public void setStatus(Status novoStatus) {
        if (novoStatus == null)
            return;
        if (this.status == novoStatus)
            return; // idempotência: evita notificações redundantes

        Status anterior = this.status;
        this.status = novoStatus;
        System.out.println(">>> Carona " + idCarona + ": " + anterior + " -> " + novoStatus);
        notificarObservers(anterior, novoStatus);
    }

    public int getIdCarona() {
        return idCarona;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public Status getStatus() {
        return status;
    }
}
