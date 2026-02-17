

package service.pais;

import dao.paisDao.PaisDao;
import entidades.Pais;

import java.util.List;

public class PaisServiceImp implements PaisService {

    private final PaisDao paisDAO;

    public PaisServiceImp(PaisDao paisDAO) {
        this.paisDAO = paisDAO;
    }

    @Override
    public Pais crearPais(String descripcion) {

        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (paisDAO.existsByDescripcion(descripcion)) {
            throw new IllegalArgumentException("El país ya existe");
        }

        Pais pais = new Pais(descripcion);
        paisDAO.save(pais);

        return pais;
    }

    @Override
    public Pais obtenerPorId(int id) {
        return paisDAO.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pais no encontrado con id: " + id));
    }

    @Override
    public Pais obtenerPorNombre(String descripcion) {
        return paisDAO.findByDescripcion(descripcion)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pais no encontrado con nombre: " + descripcion));
    }

    @Override
    public List<Pais> listarTodos() {
        return paisDAO.findAll();
    }
}

