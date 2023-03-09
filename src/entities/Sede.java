package entities;

public class Sede {
    private int id_sede;
    private String nome;
    private String indirizzo;
    private String comune;

    public int getId_sede() {
        return id_sede;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getComune() {
        return comune;
    }

    public Sede(String nome, String indirizzo, String comune) {
        this.id_sede = id_sede;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.comune = comune;
    }
}
