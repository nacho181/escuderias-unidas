package service.escuderia;

import entidades.Escuderia;
import entidades.Pais;

import java.util.List;

public interface EscuderiaService {
    Escuderia crearEscuderia(String nombre, Pais pais);

    Escuderia obtenerPorId(int id);

    Escuderia obtenerPorNombre(String nombre);

    List<Escuderia> listarTodos();
}
