package service.auto;

import entidades.Auto;
import entidades.Pais;

import java.util.List;

public interface AutoService {
    Auto crearAuto(String modelo, String motor);

    Auto obtenerPorId(int id);

    Auto obtenerPorModelo(String modelo);

    List<Auto> listarTodos();
}
