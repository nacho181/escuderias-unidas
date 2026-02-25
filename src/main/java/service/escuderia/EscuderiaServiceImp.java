package service.escuderia;

import dao.registroGeneral.escuderiaDao.EscuderiaDao;
import entidades.Escuderia;
import entidades.Pais;

import java.util.List;



public class EscuderiaServiceImp implements EscuderiaService {

    private final EscuderiaDao escuderiaDao;
    public EscuderiaServiceImp(EscuderiaDao escuderiaDao) {
        this.escuderiaDao = escuderiaDao;
    }

    @Override
    public Escuderia crearEscuderia(String nombre, Pais pais) {

        if (pais == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre/pais no puede estar vacío");
        }

        if (escuderiaDao.existByNombre(nombre)) {
            throw new IllegalArgumentException("La escudería ya existe");
        }

        Escuderia escuderia = new Escuderia(nombre, pais);
        escuderiaDao.save(escuderia);

        return escuderia;
    }

    @Override
    public Escuderia obtenerPorId(int id) {
        return escuderiaDao.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Escuderia no encontrada con id: " + id));
    }

    @Override
    public Escuderia obtenerPorNombre(String descripcion) {
        return escuderiaDao.findByNombre(descripcion)
                .orElseThrow(() ->
                        new IllegalArgumentException("Escuderia no encontrada con nombre: " + descripcion));
    }

    @Override
    public List<Escuderia> listarTodos() {
        return escuderiaDao.findAll();
    }
}
