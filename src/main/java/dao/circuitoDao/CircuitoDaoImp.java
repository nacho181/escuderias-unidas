package dao.circuitoDao;

import entidades.Circuito;
import entidades.Escuderia;
import infraestructura.DBConnection;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CircuitoDaoImp implements CircuitoDao {

    @Override
    public void save(Circuito circuito) {
        String sql = "INSERT INTO circuito (nombre, longitud, id_pais) VALUES (?, ?, ?)";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, circuito.getNombre());
            stmt.setInt(2, circuito.getLongitud());
            stmt.setInt(3, circuito.getPais().getIdPais());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                circuito.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la escudería", e);
        }
    }

    @Override
    public Optional<Circuito> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Circuito> findByNombre(String nombre) {
        return Optional.empty();
    }

    @Override
    public List<Circuito> findAll() {
        return List.of();
    }

    @Override
    public boolean existByNombre(String nombre) {
        return false;
    }
}
