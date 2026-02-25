package service.auto;

import dao.registroGeneral.autoDao.AutoDao;
import entidades.Auto;

import java.util.List;

public class AutoServiceImp implements AutoService {
    private final AutoDao autoDAO;

    public AutoServiceImp(AutoDao autoDAO) {
        this.autoDAO = autoDAO;
    }

    @Override
    public Auto crearAuto(String modelo, String motor) {
        if (modelo == null || modelo.isBlank() || motor == null || motor.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (autoDAO.existsByModelo(modelo)) {
            throw new IllegalArgumentException("El auto ya existe");
        }

        Auto auto = new Auto(modelo, motor);
        autoDAO.save(auto);

        return auto;
    }

    @Override
    public Auto obtenerPorId(int id) {
        return autoDAO.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Auto no encontrado con id: " + id));
    }

    @Override
    public Auto obtenerPorModelo(String modelo) {
        return autoDAO.findByModelo(modelo)
                .orElseThrow(() ->
                        new IllegalArgumentException("Auto no encontrado con modelo: " + modelo));
    }

    @Override
    public List<Auto> listarTodos() {
        return autoDAO.findAll();
    }
}
