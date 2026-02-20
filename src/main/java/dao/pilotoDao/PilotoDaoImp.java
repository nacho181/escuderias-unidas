package dao.pilotoDao;

import entidades.Pais;
import entidades.Piloto;
import infraestructura.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PilotoDaoImp implements PilotoDao {

    @Override
    public void save(Piloto piloto, Connection conn) {
        String sql = """
                INSERT INTO piloto (numero_competencia, victorias, pole_position,vueltas_rapidas, podios, puntos_acumulados)
                VALUES (?, ?, ?, ?, ?, ?)
                """;


        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, piloto.getNumeroCompetencia());
            stmt.setInt(2, piloto.getVictorias());
            stmt.setInt(3, piloto.getPolePosition());
            stmt.setInt(4, piloto.getVueltasRapidas());
            stmt.setInt(5, piloto.getPodios());
            stmt.setInt(6, piloto.getPuntosAcumulados());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                piloto.setIdPersona(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el piloto", e);
        }
    }

    @Override
    public Optional<Piloto> findById(int id, Connection conn) {
        String sql = """
    SELECT
        pi.id_persona,
        pi.anios_experiencia,
        pi.especialidad,
        pe.dni,
        pe.nombre,
        pe.apellido,
        pa.id AS pais_id,
        pa.descripcion
    FROM piloto pi
    JOIN persona pe ON pi.id_persona = pe.id
    JOIN pais pa ON pe.id_pais = pa.id
    WHERE pi.id_persona = ?
""";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Pais pais = new Pais(
                        rs.getInt("pais_id"),
                        rs.getString("descripcion")
                );

                Piloto piloto = new Piloto(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        pais,
                        rs.getInt("numero_competencia"),
                        rs.getInt("victorias"),
                        rs.getInt("pole_position"),
                        rs.getInt("vueltas_rapidas"),
                        rs.getInt("podios"),
                        rs.getInt("puntos_acumulados")
                );

                return Optional.of(piloto);
            }

            return Optional.empty();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error al buscar el piloto", e);
        }
    }

    @Override
    public List<Piloto> findAll(Connection conn) {
        String sql= """
        SELECT pi.id_persona,
                pi.numero_competencia,
                pi.victorias,
                pi.pole_position,
                pi.vueltas_rapidas,
                pi.podios,
                pi.puntos_acumulados,
                pe.dni,
                pe.nombre,
                pe.apellido,
                pa.id AS pais_id,
                pa.descripcion
        FROM piloto pi
        JOIN persona pe ON pi.id_persona = pe.id
        JOIN pais pa ON pe.id_pais = pa.id
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql);){
            List<Piloto> pilotos = new ArrayList<>();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pais pais = new Pais(
                        rs.getInt("pais_id"),
                        rs.getString("descripcion")
                );

                Piloto piloto = new Piloto(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        pais,
                        rs.getInt("numero_competencia"),
                        rs.getInt("victorias"),
                        rs.getInt("pole_position"),
                        rs.getInt("vueltas_rapidas"),
                        rs.getInt("podios"),
                        rs.getInt("puntos_acumulados")
                );

                pilotos.add(piloto);
            }
            return pilotos;
        }catch (SQLException e) {
            throw new RuntimeException("Error al obtener los pilotos", e);
        }
    }
}
