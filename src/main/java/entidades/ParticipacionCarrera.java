package entidades;

public class ParticipacionCarrera {
    private final Carrera carrera;
    private final AutoPiloto autoPiloto;

    public ParticipacionCarrera(Carrera carrera, AutoPiloto autoPiloto) {
        this.carrera = carrera;
        this.autoPiloto = autoPiloto;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public AutoPiloto getAutoPiloto() {
        return autoPiloto;
    }

    @Override
    public String toString() {
        return "ParticipacionCarrera{" +
                "autoPiloto=" + autoPiloto +
                '}';
    }
}

