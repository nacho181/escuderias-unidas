package dao.circuitoDao;

import entidades.Circuito;
import entidades.Escuderia;
import entidades.Pais;
import infraestructura.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircuitoDaoImp implements CircuitoDao {

    @Override
    public void save(Circuito circuit) {
        String sql = "INSERT INTO circuito (nombre, longitud, id_pais) VALUES (?, ?, ?)";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, circuit.getNombre());
            stmt.setInt(2, circuit.getLongitud());
            stmt.setInt(3, circuit.getPais().getIdPais());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                circuit.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la escudería", e);
        }
    }

    @Override
    public Optional<Circuito> findById(int id) {
        String sql = """
        SELECT c.id, c.nombre, c.longitud,
               p.id AS pais_id, p.descripcion
        FROM circuito c
        JOIN pais p ON c.id_pais = p.id
        WHERE c.id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Pais pais = new Pais(rs.getInt("pais_id"),
                        rs.getString("descripcion"));

                Circuito circuito = new Circuito(
                        rs.getString("nombre"),
                        rs.getInt("longitud"),
                        pais
                );

                return Optional.of(circuito);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar escudería", e);
        }
    }

    @Override
    public Optional<Circuito> findByNombre(String nombre) {
        String sql = """
        SELECT c.id, c.nombre, c.longitud,
               p.id AS pais_id, p.descripcion
        FROM circuito c
        JOIN pais p ON c.id_pais = p.id
        WHERE c.nombre = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Pais pais = new Pais(rs.getInt("pais_id"),
                        rs.getString("descripcion"));

                Circuito circuito = new Circuito(
                        rs.getString("nombre"),
                        rs.getInt("longitud"),
                        pais
                );

                return Optional.of(circuito);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar escudería", e);
        }
    }

    @Override
    public List<Circuito> findAll() {
        String sql = """
        SELECT c.id, c.nombre, c.longitud,
               p.id AS pais_id, p.descripcion
        FROM circuito c
        JOIN pais p ON c.id_pais = p.id;
        """;
        List<Circuito> circuitos = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pais pais = new Pais(rs.getInt("pais_id"),rs.getString("descripcion"));

                Circuito circuito = new Circuito(
                        rs.getString("nombre"),
                        rs.getInt("longitud"),
                        pais);
                circuitos.add(circuito);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener autos", e);
        }

        return circuitos;
    }

    @Override
    public boolean existByNombre(String nombre) {
        String sql = "SELECT 1 FROM circuito WHERE nombre = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia del circuito", e);
        }
    }
}
