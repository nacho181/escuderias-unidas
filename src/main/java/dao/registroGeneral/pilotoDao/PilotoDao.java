package dao.registroGeneral.pilotoDao;

import entidades.Piloto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PilotoDao {
    void save(Piloto piloto, Connection conn) throws SQLException;

    Optional<Piloto> findById(int id, Connection conn) throws SQLException;

    List<Piloto> findAll(Connection conn) throws SQLException;

    boolean existByDni(String dni, Connection conn) throws SQLException;

    Optional<Piloto> findByDni(String dni, Connection conn) throws SQLException;
}
