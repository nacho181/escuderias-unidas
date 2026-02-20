package dao.mecanicoDao;

import entidades.Mecanico;

import java.util.List;
import java.util.Optional;

public interface MecanicoDao {
    void save(Mecanico mecanico);

    Optional<Mecanico> findById(int id);

    Optional<Mecanico> findByNombre(String nombre);

    List<Mecanico> findAll();

    boolean existByNombre(String nombre);
}
