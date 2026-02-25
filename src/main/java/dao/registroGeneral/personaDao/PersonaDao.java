package dao.registroGeneral.personaDao;

import entidades.Persona;

import java.sql.Connection;

public interface PersonaDao {
    void save(Persona persona, Connection conn);

}
