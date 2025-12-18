package entidades;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;

public class Carrera {
    private final LocalDate fechaRealizacion;
    private final int numeroVueltas;
    private final LocalTime horaRealizacion;
    private final Circuito circuito;
    private final ArrayList<AutoPiloto> autoPilotos;
    private final ArrayList<AutoPiloto> posiciones;
    private int totalCarrerasCorridas;


    public Carrera(LocalDate fechaRealizacion, int numeroVueltas, LocalTime horaRealizacion, int totalCarrerasCorridas, Circuito circuito,ArrayList<AutoPiloto> autoPilotos) {
        this.fechaRealizacion = fechaRealizacion;
        this.numeroVueltas = numeroVueltas;
        this.horaRealizacion = horaRealizacion;
        this.totalCarrerasCorridas = totalCarrerasCorridas;
        this.circuito = circuito;
        this.autoPilotos = autoPilotos;
        posiciones = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            posiciones.add(null);
        }
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void setTotalCarrerasCorridas(int totalCarrerasCorridas) {
        this.totalCarrerasCorridas = totalCarrerasCorridas;
    }
    public int getTotalCarrerasCorridas() {
        return totalCarrerasCorridas;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public ArrayList<AutoPiloto> getAutoPilotos() {
        return autoPilotos;
    }

    public int getNumeroVueltas() {
        return numeroVueltas;
    }

    public LocalTime getHoraRealizacion() {
        return horaRealizacion;
    }


    @Override
    public String toString() {
        return "Carrera{" +
                "fechaRealizacion='" + fechaRealizacion + '\'' +
                ", numeroVueltas=" + numeroVueltas +
                ", horaRealizacion='" + horaRealizacion + '\'' +
                ", circuito=" + circuito +
                ", autoPilotos=" + autoPilotos +
                ", posiciones=" + posiciones +
                ", totalCarrerasCorridas=" + totalCarrerasCorridas +
                '}';
    }
}
