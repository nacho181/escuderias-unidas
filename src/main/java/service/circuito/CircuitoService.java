package service.circuito;

import entidades.Auto;
import entidades.Circuito;
import entidades.Escuderia;
import entidades.Pais;

import java.util.List;
import java.util.Optional;

public interface CircuitoService {

    Circuito crearCircuito(String nombre, int longitud, Pais pais);

    Circuito obtenerPorId(int id);

    Circuito obtenerPorNombre(String nombre);

    List<Circuito> listarTodos();
}
