package dao.personaDao;

import entidades.Persona;
import infraestructura.DBConnection;

import java.sql.*;

public class PersonaDaoImp implements PersonaDao {

    @Override
    public void save(Persona persona) {
        String sql = "INSERT INTO persona (id_pais, dni, nombre, apellido ) VALUES ( ?, ?, ?, ?)";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, persona.getPais().getIdPais());
            stmt.setString(2, persona.getDni());
            stmt.setString(3, persona.getNombre());
            stmt.setString(4, persona.getApellido());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                persona.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el mecanico", e);
        }
    }
}
