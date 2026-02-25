package service.circuito;

import dao.registroGeneral.circuitoDao.CircuitoDao;
import entidades.Circuito;
import entidades.Pais;
import java.util.List;

public class CircuitoServiceImp implements CircuitoService {
    private final CircuitoDao circuitoDAO;

    public CircuitoServiceImp(CircuitoDao circuitoDAO) {
        this.circuitoDAO = circuitoDAO;
    }

    @Override
    public Circuito crearCircuito(String nombre, int longitud, Pais pais) {
        if (nombre.isBlank() ||  pais == null) {
            throw new IllegalArgumentException("El nombre del circuito o pais no pueden estar vacios");
        }

        if (circuitoDAO.existByNombre(nombre)) {
            throw new IllegalArgumentException("El circuito ya existe");
        }

        Circuito circuito = new Circuito(nombre, longitud, pais);
        circuitoDAO.save(circuito);

        return circuito;
    }

    @Override
    public Circuito obtenerPorId(int id) {
        return circuitoDAO.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Circuito no encontrado con id: " + id));
    }

    @Override
    public Circuito obtenerPorNombre(String nombre) {
        return circuitoDAO.findByNombre(nombre).orElseThrow(() ->
                new IllegalArgumentException("Circuito no encontrado con nombre: " + nombre));
    }

    @Override
    public List<Circuito> listarTodos() {
        return circuitoDAO.findAll();
    }
}
