package dao.registroGeneral.mecanicoDao;

import entidades.Especialidad;
import entidades.Mecanico;
import entidades.Pais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MecanicoDaoImp implements MecanicoDao {
    @Override
    public void save(Mecanico mecanico, Connection conn) {
        String sql = "INSERT INTO mecanico (id_persona, anios_experiencia, especialidad ) VALUES (?, ?, ?)";


        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, mecanico.getId());
            stmt.setInt(2, mecanico.getAniosExperiencia());
            stmt.setString(3, mecanico.getEspecialidad().name());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                mecanico.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el mecanico", e);
        }
    }

    @Override
    public Optional<Mecanico> findById(int id, Connection conn) {
        String sql = """
    SELECT 
        m.id_persona,
        m.anios_experiencia,
        m.especialidad,
        pe.dni,
        pe.nombre,
        pe.apellido,
        pa.id AS pais_id,
        pa.descripcion
    FROM mecanico m
    JOIN persona pe ON m.id_persona = pe.id
    JOIN pais pa ON pe.id_pais = pa.id
    WHERE m.id_persona = ?
""";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Pais pais = new Pais(
                        rs.getInt("pais_id"),
                        rs.getString("descripcion")
                );

                Mecanico mecanico = new Mecanico(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        pais,
                        Especialidad.valueOf(rs.getString("especialidad")),
                        rs.getInt("anios_experiencia")
                );

                return Optional.of(mecanico);
            }

            return Optional.empty();
        }
            catch (SQLException e) {
                throw new RuntimeException("Error al buscar escudería", e);
            }
        }



    @Override
    public Optional<Mecanico> findByNombre(String nombre, Connection conn) {
        return Optional.empty();
    }

    @Override
    public List<Mecanico> findAll(Connection conn) {
        String sql = """
                SELECT
                m.id_persona,
                m.anios_experiencia,
                m.especialidad,
                pe.dni,
                pe.nombre,
                pe.apellido,
                pa.id AS pais_id,
                pa.descripcion
                FROM mecanico m
                JOIN persona pe ON m.id_persona = pe.id
                JOIN pais pa ON pe.id_pais = pa.id
                """;

        List<Mecanico> mecanicos = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pais pais = new Pais(
                        rs.getInt("pais_id"),
                        rs.getString("descripcion")
                );

                Mecanico mecanico = new Mecanico(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        pais,
                        Especialidad.valueOf(rs.getString("especialidad")),
                        rs.getInt("anios_experiencia")
                );
                mecanicos.add(mecanico);
            }

            return mecanicos;
        } catch (SQLException e) {
        throw new RuntimeException("Error al obtener mecanicos", e);
    }
    }

    @Override
    public boolean existByDni(String dni, Connection conn) {
        String sql = """
        SELECT 1
        FROM mecanico m
        JOIN persona per ON m.id_persona = per.id
        WHERE per.dni = ?
        LIMIT 1;
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia del mecanico", e);
        }
    }
}
