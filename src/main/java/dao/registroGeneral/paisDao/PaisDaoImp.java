package dao.registroGeneral.paisDao;

import infraestructura.DBConnection;
import entidades.Pais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaisDaoImp implements PaisDao {

    @Override
    public void save(Pais pais) {
        String sql = "INSERT INTO pais (descripcion) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, pais.getDescripcion());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                pais.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar país", e);
        }
    }

    @Override
    public Optional<Pais> findById(int id) {
        String sql = "SELECT * FROM pais WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pais pais = new Pais(
                        rs.getInt("id"),
                        rs.getString("descripcion")
                );
                return Optional.of(pais);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar país por ID", e);
        }

        return Optional.empty();
    }
    @Override
    public Optional<Pais> findByDescripcion(String descripcion) {
        String sql = "SELECT * FROM pais WHERE descripcion = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, descripcion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pais pais = new Pais(
                        rs.getInt("id"),
                        rs.getString("descripcion")
                );
                return Optional.of(pais);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar país por descripción", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Pais> findAll() {
        String sql = "SELECT * FROM pais";
        List<Pais> paises = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pais pais = new Pais(
                        rs.getInt("id"),
                        rs.getString("descripcion")
                );
                paises.add(pais);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener países", e);
        }

        return paises;
    }

    @Override
    public boolean existsByDescripcion(String descripcion) {
        String sql = "SELECT 1 FROM pais WHERE descripcion = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, descripcion);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia del país", e);
        }
    }
}

