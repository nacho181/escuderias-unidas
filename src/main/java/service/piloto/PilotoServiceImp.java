package service.piloto;

import dao.registroGeneral.personaDao.PersonaDao;
import dao.registroGeneral.pilotoDao.PilotoDao;
import entidades.Pais;
import entidades.Piloto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import infraestructura.DBConnection;

public class PilotoServiceImp implements PilotoService {
    private PilotoDao pilotoDAO;
    private PersonaDao personaDAO;

    public PilotoServiceImp(PilotoDao pilotoDAO, PersonaDao personaDAO) {
        this.pilotoDAO = pilotoDAO;
        this.personaDAO = personaDAO;
    }

    @Override
    public Piloto crearPiloto(Pais pais, String dni, String nombre, String apellido, int numeroCompetencia,
                              int victorias, int vueltasRapidas, int polePosition, int podios,
                              int puntosAcumulados) throws SQLException {
        Connection conn = null;
            try{
                conn = DBConnection.getConnection();
                conn.setAutoCommit(false);
                if (!pilotoDAO.existByDni(dni,conn)) {
                    Piloto piloto = new Piloto(dni, nombre, apellido, pais, numeroCompetencia,
                            victorias, polePosition,vueltasRapidas, podios, puntosAcumulados);
                    personaDAO.save(piloto, conn);
                    pilotoDAO.save(piloto, conn);
                    return piloto;
                } else {
                    throw new IllegalArgumentException("El piloto ya existe");
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
    public Piloto obtenerPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        try(Connection conn = DBConnection.getConnection()) {
            return pilotoDAO.findById(id, conn).orElseThrow(() ->
                    new IllegalArgumentException("Piloto no encontrado con id: " + id));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el piloto por ID", e);
        }
    }

    @Override
    public Piloto obtenerPorDni(String dni) {
        if (dni.isBlank()) {
            throw new IllegalArgumentException("Rellene el DNI porfavor");
        }
        try(Connection conn = DBConnection.getConnection()) {
            return pilotoDAO.findByDni(dni, conn).orElseThrow(() ->
                    new IllegalArgumentException("Piloto no encontrado con DNI: " + dni));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el piloto con dni = " + dni , e);
        }
    }

    @Override
    public List<Piloto> listarTodos() {
        List<Piloto> pilotos = new ArrayList<Piloto>();
        try (Connection conn = DBConnection.getConnection()) {
            pilotos = pilotoDAO.findAll(conn);
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los pilotos", e);
        }
        return pilotos;
    }
}
