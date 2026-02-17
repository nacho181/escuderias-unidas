package dao.escuderiaDao;

import dao.autoDao.AutoDao;
import entidades.Auto;
import entidades.Escuderia;
import entidades.Pais;
import infraestructura.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EscuderiaDaoImp implements EscuderiaDao {

    @Override
    public void save(Escuderia escuderia) {
        String sql = "INSERT INTO escuderia (nombre, id_pais) VALUES (?, ?)";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, escuderia.getNombre());
            stmt.setInt(2, escuderia.getPais().getIdPais());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                escuderia.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la escudería", e);
        }
    }

    @Override
    public Optional<Escuderia> findById(int id) {
        String sql = """
        SELECT e.id, e.nombre,
               p.id AS pais_id, p.descripcion
        FROM escuderia e
        JOIN pais p ON e.id_pais = p.id
        WHERE e.id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Pais pais = new Pais(rs.getInt("pais_id"),
                        rs.getString("descripcion"));

                Escuderia escuderia = new Escuderia(
                        rs.getString("nombre"),
                        pais
                );

                return Optional.of(escuderia);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar escudería", e);
        }
    }

    @Override
    public Optional<Escuderia> findByNombre(String nombre) {
        String sql = """
        SELECT e.id, e.nombre,
               p.id AS pais_id, p.descripcion
        FROM escuderia e
        JOIN pais p ON e.id_pais = p.id
        WHERE e.nombre = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Pais pais = new Pais(rs.getInt("pais_id"),
                        rs.getString("descripcion"));

                Escuderia escuderia = new Escuderia(
                        rs.getString("nombre"),
                        pais
                );

                return Optional.of(escuderia);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar escudería", e);
        }
    }

    @Override
    public List<Escuderia> findAll() {
        String sql = """
        SELECT e.id, e.nombre,
               p.id AS pais_id, p.descripcion
        FROM escuderia e
        JOIN pais p ON e.id_pais = p.id;
        """;
        List<Escuderia> escuderias = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pais pais = new Pais(rs.getInt("pais_id"),rs.getString("descripcion"));

                Escuderia escuderia = new Escuderia(
                        rs.getString("nombre"),
                        pais);
                escuderias.add(escuderia);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener autos", e);
        }

        return escuderias;
    }

    @Override
    public boolean existByNombre(String nombre) {
        String sql = "SELECT 1 FROM escuderia WHERE nombre = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia de la escuderia", e);
        }
    }
}
