
import connection.ConnectionHandler;
import dao.UtenteDao;
import entities.Sede;
import entities.Utente;
import entitiesDAO.UtenteDaoSql;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
      /* //Avvio connessione
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/teatro","postgres","admin"); //L'url è sempre lo stesso per i database in locale, cambia il nome del db
        //creo Statement
        Statement statement= connection.createStatement();
        //posso lanciare query e salvare il risultato
        ResultSet result=statement.executeQuery("select nome,cognome from utente");///questo è esattamente un codice sql
        //leggo i dati
        while(result.next()){
            String nome=result.getString("nome");
            String cognome=result.getString("cognome");
            System.out.println(nome+" "+cognome);
        }
        Sede posto=new Sede("Scala","via vattelapesca","Milano");
        addSede(posto,connection);
        statement.close();
        connection.close();
        ConnectionHandler conn= ConnectionHandler.getInstance();
        */
        UtenteDao u = new UtenteDaoSql();
        System.out.println(u.getAll());
        System.out.println(u.get(2));
        u.insert(new Utente("Mercedes","Acconciaioco","mass@galao.it"));
        System.out.println(u.getAll());
        u.delete(4);
        System.out.println(u.getAll());
    }
    public static void addSede(Sede sede, Connection connection)throws SQLException{
        PreparedStatement prepstat=connection.prepareStatement("INSERT INTO sede(nome,indirizzo,comune) VALUES(?,?,?)");

        prepstat.setString(1,sede.getNome());
        prepstat.setString(2, sede.getIndirizzo());
        prepstat.setString(3,sede.getComune());
        prepstat.executeUpdate();
        prepstat.close();
    }
}