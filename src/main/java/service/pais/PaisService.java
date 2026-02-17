package service.pais;

import entidades.Pais;
import java.util.List;

public interface PaisService {

    Pais crearPais(String nombre);

    Pais obtenerPorId(int id);

    Pais obtenerPorNombre(String nombre);

    List<Pais> listarTodos();
}

