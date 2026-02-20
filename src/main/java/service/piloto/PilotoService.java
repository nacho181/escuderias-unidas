package service.piloto;

import entidades.Circuito;
import entidades.Pais;
import entidades.Piloto;

import java.util.List;

public interface PilotoService {
    Piloto crearPiloto(String nombre, int longitud, Pais pais);

    Piloto obtenerPorId(int id);

    Piloto obtenerPorDni(String nombre);

    List<Piloto> listarTodos();
}
