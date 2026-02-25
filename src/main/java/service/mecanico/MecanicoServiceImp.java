package service.mecanico;

import dao.registroGeneral.mecanicoDao.MecanicoDao;
import dao.registroGeneral.personaDao.PersonaDao;
import entidades.Especialidad;
import entidades.Mecanico;
import entidades.Pais;
import infraestructura.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MecanicoServiceImp implements MecanicoService {
    private final MecanicoDao mecanicoDao;
    private final PersonaDao personaDao;

    public MecanicoServiceImp(MecanicoDao mecanicoDao, PersonaDao personaDao) {
        this.mecanicoDao = mecanicoDao;
        this.personaDao = personaDao;
    }

    @Override
    public Mecanico crearMecanico(Pais pais, String dni, String nombre, String apellido, Especialidad especialidad, int aniosExperiencia) throws SQLException {
        Connection conn = null;
        try{
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            if (!mecanicoDao.existByDni(dni,conn)) {
                Mecanico mecanico = new Mecanico(dni, nombre, apellido, pais, especialidad,
                        aniosExperiencia);
                personaDao.save(mecanico, conn);
                mecanicoDao.save(mecanico, conn);
                return mecanico;
            } else {
                throw new IllegalArgumentException("El mecanico ya existe");
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Mecanico obtenerPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        try(Connection conn = DBConnection.getConnection()) {
            return mecanicoDao.findById(id, conn).orElseThrow(() ->
                    new IllegalArgumentException("Piloto no encontrado con id: " + id));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el piloto por ID", e);
        }
    }

    @Override
    public Mecanico obtenerPorDni(String dni) {
        return null;
    }

    @Override
    public List<Mecanico> listarTodos() {
        List<Mecanico> mecanicos;
        try (Connection conn = DBConnection.getConnection()) {
            mecanicos = mecanicoDao.findAll(conn);
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los pilotos", e);
        }
        return mecanicos;
    }
}
