package dao.autoDao;

import entidades.Auto;
import entidades.Pais;
import infraestructura.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutoDaoImp implements AutoDao{

    @Override
    public void save(Auto auto) {
        String sql = "INSERT INTO auto (modelo, motor) VALUES (?, ?)";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, auto.getModelo());
            stmt.setString(2, auto.getMotor());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                auto.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar auto", e);
        }
    }

    @Override
    public Optional<Auto> findById(int id) {
        String sql = "SELECT * FROM auto WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Auto auto = new Auto(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getString("motor")
                );
                return Optional.of(auto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar auto por ID", e);
        }

        return Optional.empty();
    }
    @Override
    public Optional<Auto> findByModelo(String modelo) {
        String sql = "SELECT * FROM auto WHERE modelo = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, modelo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Auto auto = new Auto(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getString("motor")
                );
                return Optional.of(auto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar auto por modelo", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Auto> findAll() {
        String sql = "SELECT * FROM auto";
        List<Auto> autos = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Auto auto = new Auto(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getString("descripcion")
                );
                autos.add(auto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener autos", e);
        }

        return autos;
    }

    @Override
    public boolean existsByModelo(String modelo) {
        String sql = "SELECT 1 FROM auto WHERE modelo = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, modelo);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia del auto", e);
        }
    }
}
