package dao.registroGeneral.mecanicoDao;

import entidades.Mecanico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MecanicoDao {
    void save(Mecanico mecanico, Connection conn) throws SQLException;

    Optional<Mecanico> findById(int id, Connection conn) throws SQLException;

    Optional<Mecanico> findByNombre(String nombre, Connection conn) throws SQLException;

    List<Mecanico> findAll(Connection conn) throws SQLException;

    boolean existByDni(String dni, Connection conn) throws SQLException;
}
