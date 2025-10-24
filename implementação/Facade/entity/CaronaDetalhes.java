package entity;

public class CaronaDetalhes {
    public String origem;
    public String destino;
    public int vagas;
    public double preco;

    public CaronaDetalhes(String origem, String destino, int vagas, double preco) {
        this.origem = origem;
        this.destino = destino;
        this.vagas = vagas;
        this.preco = preco;
    }
}