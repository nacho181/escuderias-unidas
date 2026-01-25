package entidades;

import java.time.LocalDate;

public class AutoPiloto {
    private final LocalDate fechaAsignacion;
    private final Piloto piloto;
    private final Auto auto;


    public Piloto getPiloto() {
        return piloto;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public AutoPiloto(LocalDate fechaAsignacion, Piloto piloto, Auto auto) {
        this.fechaAsignacion = fechaAsignacion;
        this.piloto = piloto;
        this.auto = auto;
    }

    public Auto getAuto() {
        return auto;
    }

    @Override
    public String toString() {
        return "AutoPiloto{" +
                "fechaAsignacion='" + fechaAsignacion + '\'' +
                ", piloto=" + piloto +
                ", auto=" + auto +
                '}';
    }
}



