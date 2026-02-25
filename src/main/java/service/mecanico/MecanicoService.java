package service.mecanico;

import entidades.Especialidad;
import entidades.Mecanico;
import entidades.Pais;
import java.sql.SQLException;
import java.util.List;

public interface MecanicoService {
    Mecanico crearMecanico(Pais pais, String dni, String nombre, String apellido, Especialidad especialidad, int aniosExperiencia) throws SQLException;

    Mecanico obtenerPorId(int id);

    Mecanico obtenerPorDni(String dni);

    List<Mecanico> listarTodos();
}
