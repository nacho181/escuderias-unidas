package dao.registroGeneral.personaDao;

import entidades.Persona;

import java.sql.*;

public class PersonaDaoImp implements PersonaDao {

    @Override
    public void save(Persona persona, Connection conn) {
        String sql = "INSERT INTO persona (id_pais, dni, nombre, apellido ) VALUES ( ?, ?, ?, ?)";


        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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
            throw new RuntimeException("Error al guardar la persona", e);
        }
    }
}
