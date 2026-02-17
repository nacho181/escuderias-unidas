package dao.paisDao;

import entidades.Pais;
import java.util.List;
import java.util.Optional;

public interface PaisDao {

    void save(Pais pais);

    Optional<Pais> findById(int id);

    Optional<Pais> findByDescripcion(String nombre);

    List<Pais> findAll();

    boolean existsByDescripcion(String nombre);
}

