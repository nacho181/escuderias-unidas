package service.piloto;

import entidades.Circuito;
import entidades.Pais;
import entidades.Piloto;

import java.sql.SQLException;
import java.util.List;

public interface PilotoService {

    Piloto crearPiloto(Pais pais, String dni, String nombre, String apellido, int numeroCompetencia,
                       int victorias, int vueltasRapidas, int polePosition, int podios,
                       int puntosAcumulados) throws SQLException;

    Piloto obtenerPorId(int id);

    Piloto obtenerPorDni(String dni);

    List<Piloto> listarTodos();
}
