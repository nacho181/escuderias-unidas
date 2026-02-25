package dao.registroGeneral.circuitoDao;

import entidades.Circuito;

import java.util.List;
import java.util.Optional;

public interface CircuitoDao {
    void save(Circuito circuito);

    Optional<Circuito> findById(int id);

    Optional<Circuito> findByNombre(String nombre);

    List<Circuito> findAll();

    boolean existByNombre(String nombre);
}
