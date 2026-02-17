package dao.escuderiaDao;

import entidades.Auto;
import entidades.Escuderia;
import entidades.Pais;

import java.util.List;
import java.util.Optional;

public interface EscuderiaDao {
    void save(Escuderia escuderia);

    Optional<Escuderia> findById(int id);

    Optional<Escuderia> findByNombre(String nombre);

    List<Escuderia> findAll();

    boolean existByNombre(String nombre);
}
