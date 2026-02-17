package service;

import dao.autoDao.AutoDao;
import dao.autoDao.AutoDaoImp;
import dao.circuitoDao.CircuitoDao;
import dao.circuitoDao.CircuitoDaoImp;
import dao.escuderiaDao.EscuderiaDao;
import dao.escuderiaDao.EscuderiaDaoImp;
import dao.paisDao.PaisDao;
import dao.paisDao.PaisDaoImp;
import service.auto.AutoService;
import service.auto.AutoServiceImp;
import service.circuito.CircuitoService;
import service.circuito.CircuitoServiceImp;
import service.escuderia.EscuderiaService;
import service.escuderia.EscuderiaServiceImp;
import service.pais.PaisService;
import service.pais.PaisServiceImp;

public class ServiceManager {
    private final PaisService paisService;
    private final AutoService autoService;
    private final EscuderiaService escuderiaService;
    private final CircuitoService circuitoService;

    public ServiceManager() {
        // PaisDao
        PaisDao paisDAO = new PaisDaoImp();
        this.paisService = new PaisServiceImp(paisDAO);
        // AutoDao
        AutoDao autoDAO = new AutoDaoImp();
        this.autoService = new AutoServiceImp(autoDAO);
        // EscuderiaDao
        EscuderiaDao escuderiaDao = new EscuderiaDaoImp();
        this.escuderiaService = new EscuderiaServiceImp(escuderiaDao);
        // CircuitoDao
        CircuitoDao circuitoDAO = new CircuitoDaoImp();
        this.circuitoService = new CircuitoServiceImp(circuitoDAO);

    }

    public PaisService getPaisService() {
        return paisService;
    }

    public AutoService getAutoService() {
        return autoService;
    }
    public EscuderiaService getEscuderiaService() {
        return escuderiaService;
    }

    public CircuitoService getCircuitoService() {
        return circuitoService;
    }
}
