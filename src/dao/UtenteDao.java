package dao;

import entities.Utente;

import java.sql.SQLException;
import java.util.List;
public interface UtenteDao extends Dao<Utente> {
    List<Utente> getByLastName(String lastName) throws SQLException;
}
