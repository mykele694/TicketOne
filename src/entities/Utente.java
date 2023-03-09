package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Utente {
    private int id_utente;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private String email;

    public Utente(int id_utente, String nome, String cognome, String indirizzo, String telefono, String email) {
        this.id_utente = id_utente;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.email = email;
    }
    public Utente(String nome,String cognome,String email){
        this.nome=nome;
        this.cognome=cognome;
        this.email=email;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }
    public static Utente fromResultSet(ResultSet rs) throws SQLException {
        return new Utente(rs.getInt("id_utente"),
                rs.getString("nome"),
                rs.getString("cognome"),
                rs.getString("indirizzo"),
                rs.getString("telefono"),
                rs.getString("email")
        );
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id_utente=" + id_utente +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                "}\n";
    }
}
