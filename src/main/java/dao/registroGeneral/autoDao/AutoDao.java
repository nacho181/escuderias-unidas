package dao.registroGeneral.autoDao;

import entidades.Auto;

import java.util.List;
import java.util.Optional;

public interface AutoDao {
    void save(Auto auto);

    Optional<Auto> findById(int id);

    Optional<Auto> findByModelo(String modelo);

    List<Auto> findAll();

    boolean existsByModelo(String nombre);
}
