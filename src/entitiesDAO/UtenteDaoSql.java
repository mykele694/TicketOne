package entitiesDAO;


import connection.ConnectionHandler;
import dao.UtenteDao;
import entities.Utente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UtenteDaoSql implements UtenteDao {
    @Override
    public boolean insert(Utente entity){
        String query = "INSERT INTO utente (nome, cognome,email) VALUES (?, ?,?);";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query);)
        {
            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getCognome());
            ps.setString(3, entity.getEmail());
            int insertedCount = ps.executeUpdate();

            return insertedCount > 0;
        }
        catch (SQLException e){
            System.out.println("Errore nell'inserimento");
            return false;
        }

    }

    @Override
    public boolean update(Utente entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM utente WHERE utente.id_utente=?;";

        try(ConnectionHandler ch = ConnectionHandler.getInstance();
            PreparedStatement ps = ch.getPreparedStatement(query);)
        {
            ps.setInt(1,id);
            int removedCount = ps.executeUpdate();
            return removedCount>0;
        }
    }

    @Override
    public Optional<Utente> get(int id) throws SQLException {
        Optional<Utente> user = Optional.empty();

        String query = "SELECT * FROM utente WHERE utente.id_utente = ?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) user = Optional.of(Utente.fromResultSet(rs));
        }

        return user;
    }

    @Override
    public List<Utente> getAll() throws SQLException {
        List<Utente> users = new ArrayList<>();

        String query = "SELECT * FROM utente;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next()) users.add(Utente.fromResultSet(rs));
        }

        return users;
    }


    @Override
    public List<Utente> getByLastName(String lastName) throws SQLException {
        List<Utente> users = new ArrayList<>();

        String query = "SELECT * FROM utente WHERE utente.cognome=?;";

        try (ConnectionHandler ch = ConnectionHandler.getInstance();
             PreparedStatement ps = ch.getPreparedStatement(query);)
        {
            ps.setString(1,lastName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) users.add(Utente.fromResultSet(rs));
        }

        return users;
    }
}
