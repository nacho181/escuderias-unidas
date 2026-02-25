package service;

import dao.registroGeneral.autoDao.AutoDao;
import dao.registroGeneral.autoDao.AutoDaoImp;
import dao.registroGeneral.circuitoDao.CircuitoDao;
import dao.registroGeneral.circuitoDao.CircuitoDaoImp;
import dao.registroGeneral.escuderiaDao.EscuderiaDao;
import dao.registroGeneral.escuderiaDao.EscuderiaDaoImp;
import dao.registroGeneral.mecanicoDao.MecanicoDao;
import dao.registroGeneral.mecanicoDao.MecanicoDaoImp;
import dao.registroGeneral.paisDao.PaisDao;
import dao.registroGeneral.paisDao.PaisDaoImp;
import dao.registroGeneral.personaDao.PersonaDao;
import dao.registroGeneral.personaDao.PersonaDaoImp;
import dao.registroGeneral.pilotoDao.PilotoDao;
import dao.registroGeneral.pilotoDao.PilotoDaoImp;
import service.auto.AutoService;
import service.auto.AutoServiceImp;
import service.circuito.CircuitoService;
import service.circuito.CircuitoServiceImp;
import service.escuderia.EscuderiaService;
import service.escuderia.EscuderiaServiceImp;
import service.mecanico.MecanicoService;
import service.mecanico.MecanicoServiceImp;
import service.pais.PaisService;
import service.pais.PaisServiceImp;
import service.piloto.PilotoService;
import service.piloto.PilotoServiceImp;

public class ServiceManager {
    private final PaisService paisService;
    private final AutoService autoService;
    private final EscuderiaService escuderiaService;
    private final CircuitoService circuitoService;
    private final PilotoService pilotoService;
    private final MecanicoService mecanicoService;

    public ServiceManager() {
        PilotoDao pilotoDao = new PilotoDaoImp();
        PersonaDao personaDao = new PersonaDaoImp();
        this.pilotoService = new PilotoServiceImp(pilotoDao, personaDao);

        MecanicoDao mecanicoDao = new MecanicoDaoImp();
        this.mecanicoService = new MecanicoServiceImp(mecanicoDao, personaDao);
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

    public PilotoService getPilotoService() {
        return pilotoService;
    }

    public MecanicoService getMecanicoService() {
        return mecanicoService;
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
