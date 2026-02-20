package service.piloto;

import dao.pilotoDao.PilotoDao;
import entidades.Pais;
import entidades.Piloto;

import java.util.List;

public class PilotoServiceImp implements PilotoService {
    private PilotoDao pilotoDAO;

    public PilotoServiceImp(PilotoDao pilotoDAO) {
        this.pilotoDAO = pilotoDAO;
    }

    @Override
    public Piloto crearPiloto(String nombre, int longitud, Pais pais) {

    }

    @Override
    public Piloto obtenerPorId(int id) {
        return null;
    }

    @Override
    public Piloto obtenerPorDni(String nombre) {
        return null;
    }

    @Override
    public List<Piloto> listarTodos() {
        return List.of();
    }
}
