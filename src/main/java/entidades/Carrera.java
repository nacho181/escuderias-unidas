package entidades;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Carrera {
    private int id;
    private LocalDate fechaRealizacion;
    private  LocalTime horaRealizacion;
    private  Circuito circuito;
    private  int numeroVueltas;
    private EstadoCarrera estado;

    private List<ParticipacionCarrera> participantes;
    private List<ResultadoCarrera> resultados;

    public Carrera() {
        fechaRealizacion = null;
        numeroVueltas = 0;
        horaRealizacion = null;
        circuito = null;
        participantes = new ArrayList<>();
        resultados = new ArrayList<>();
        estado = EstadoCarrera.PROGRAMADA;
    }

    public Carrera(LocalDate fechaRealizacion, LocalTime horaRealizacion, Circuito circuito, int totalCarrerasCorridas, int numeroVueltas) {
        this.fechaRealizacion = fechaRealizacion;
        this.horaRealizacion = horaRealizacion;
        this.circuito = circuito;
        this.numeroVueltas = numeroVueltas;
        estado = EstadoCarrera.PROGRAMADA;
    }

    public Carrera(LocalDate fechaRealizacion, int numeroVueltas, LocalTime horaRealizacion, int totalCarrerasCorridas, Circuito circuito, ArrayList<ParticipacionCarrera> autoPilotos) {
        this.fechaRealizacion = fechaRealizacion;
        this.numeroVueltas = numeroVueltas;
        this.horaRealizacion = horaRealizacion;
        this.circuito = circuito;
        estado = EstadoCarrera.PROGRAMADA;
        this.participantes = autoPilotos;
        this.resultados = new ArrayList<>();
    }

    public Carrera(Carrera carrera) {
        this.fechaRealizacion = carrera.getFechaRealizacion();
        this.horaRealizacion = carrera.getHoraRealizacion();
        this.circuito = carrera.getCircuito();

        this.numeroVueltas = carrera.getNumeroVueltas();
        this.estado = carrera.getEstado();
        this.participantes = new ArrayList<>();
        this.resultados = new ArrayList<>();
    }

    public void agregarResultado(ArrayList<ResultadoCarrera> resultados) {
        this.resultados = resultados;
    }
    public List<ParticipacionCarrera> getParticipantes() {
        return participantes;
    }

    public List<ResultadoCarrera> getResultados() {
        return resultados;
    }

    public Circuito getCircuito() {
        return circuito;
    }


    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setHoraRealizacion(LocalTime horaRealizacion) {
        this.horaRealizacion = horaRealizacion;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public void setNumeroVueltas(int numeroVueltas) {
        this.numeroVueltas = numeroVueltas;
    }

    public int getNumeroVueltas() {
        return numeroVueltas;
    }

    public LocalTime getHoraRealizacion() {
        return horaRealizacion;
    }

    public Piloto consultarPiloto(String dni) {
        for(ResultadoCarrera p : resultados) {
            if (p == null || p.getAutoPiloto() == null) {
                continue; // pasa a la siguiente posición
            }
            if(dni.equals(p.getAutoPiloto().getPiloto().getDni())) {
                return p.getAutoPiloto().getPiloto();
            }
        }
        return null;
    }
    public int consultarPodioPiloto(String dni){
        for(ResultadoCarrera p : resultados) {
            if (p == null || p.getAutoPiloto() == null) {
                continue; // pasa a la siguiente posición
            }
            if(dni.equals(p.getAutoPiloto().getPiloto().getDni())){
                return resultados.indexOf(p);
            }
        }
        return -1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEstado(EstadoCarrera estado) {
        this.estado = estado;
    }

    public void setParticipantes(List<ParticipacionCarrera> participantes) {
        this.participantes = participantes;
    }

    public EstadoCarrera getEstado() {
        return estado;
    }

    public void agregarParticipantes(ArrayList<ParticipacionCarrera> participante) {
        this.participantes = participante;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "fechaRealizacion=" + fechaRealizacion +
                ", horaRealizacion=" + horaRealizacion +
                ", circuito=" + circuito +
                ", numeroVueltas=" + numeroVueltas +
                ", estado=" + estado +
                ", participantes=" + participantes +
                ", resultados=" + resultados +
                '}';
    }
}
