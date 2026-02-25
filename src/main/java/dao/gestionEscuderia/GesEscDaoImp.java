package dao.gestionEscuderia;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GesEscDaoImp implements GesEscDao {
    @Override
    public boolean existPilotoByDniDate(String dni, String fecha, String fecha2, Connection conn) {
        String sql = """
                SELECT 1 FROM piloto_escuderia pe
                JOIN piloto p ON pe.id_piloto = p.id_persona
                JOIN persona per ON p.id_persona = per.id
                WHERE per.dni = ?
                AND pe.fecha_inicio <= ?
                AND pe.fecha_fin >= ?
                """;

    }

    @Override
    public boolean existAutoByModelo(String modelo) {
        return false;
    }

    @Override
    public void savePiloto() {

    }

    @Override
    public void saveMecanico() {

    }

    @Override
    public void saveAuto() {

    }
}
