package modelo;

import entidades.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ModeloPlanificarCarrera {
    private final Modelo modelo;
    private final Carrera carrera;
    private Escuderia escuderiaSelecc;
    private ArrayList<ParticipacionCarrera> pilotosAux;

    public ModeloPlanificarCarrera(Modelo modelo) {
        this.modelo = modelo;
        carrera = new Carrera();
        pilotosAux = new ArrayList<>();
    }

    public ResultadoPlanificacion registrarPilotoAuto(String dniPiloto, LocalDate fecha, PilotoEscuderia pilotoEscuderia, Auto autoSeleccionado) {

        if (pilotoEscuderia == null) {
            return ResultadoPlanificacion.PILOTO_SIN_CONTRATO;
        }

        if (autoSeleccionado == null) {
            return ResultadoPlanificacion.AUTO_NO_PERTENECE;
        }

        if (verificarDuplicados(pilotoEscuderia, autoSeleccionado)) {
            return ResultadoPlanificacion.DUPLICADO_EN_CARRERA;
        }

        // registrar realmente
        return ResultadoPlanificacion.OK;
    }


    /** Busca un piloto asociado a una escudería por DNI. */
    public PilotoEscuderia buscarPilotEscu(Escuderia escuderia, String dni, LocalDate fecha) {
        for (Escuderia e : modelo.getRegistroGeneral().getEscuderias()) {
            if (escuderia.equals(e)) {
                for (PilotoEscuderia p : e.getPilotos()) {
                    if (p.getPiloto().getDni().equals(dni) && fecha.isAfter(p.getDesdeFecha()) &&
                            fecha.isBefore(p.getHastaFecha())) {
                        return p;
                    }
                }
            }
        }
        return null;
    }

    /** Busca un circuito por nombre. */
    public Circuito buscarCircuito(String nombre) {
        for (Circuito c : modelo.getRegistroGeneral().getCircuitos()) {
            if (nombre.equals(c.getNombre())) {
                return c;
            }
        }
        return null;
    }

    public boolean verificarDuplicados(PilotoEscuderia pilotoEscuderia, Auto autoSeleccionado) {
        for (ParticipacionCarrera ap : pilotosAux) {
            if (ap.getAutoPiloto().getFechaAsignacion().equals(carrera.getFechaRealizacion()) &&
                    (ap.getAutoPiloto().getPiloto().equals(pilotoEscuderia.getPiloto()) || ap.getAutoPiloto().getAuto().equals(autoSeleccionado))) {
                return true;
            }
        }
        return false;
    }

    public Auto verificarAuto(String modelo) {
        Auto autoSeleccionado = null;
        for (Auto auto : escuderiaSelecc.getAutos()) {
            if (auto.getModelo().equalsIgnoreCase(modelo)) {
                autoSeleccionado = auto;
                return autoSeleccionado;
            }
        }
        return autoSeleccionado;
    }

    public ArrayList<ParticipacionCarrera> getPilotosAux() {
        return pilotosAux;
    }

    public void reiniciarCarrera() {
        this.carrera.setHoraRealizacion(null);
        this.carrera.setNumeroVueltas(0);
        this.carrera.setCircuito(null);
        this.carrera.setFechaRealizacion(null);
        escuderiaSelecc = null;
        this.pilotosAux = new ArrayList<>();
        carrera.setParticipantes(new ArrayList<>());
    }


    public void agregarPilotosAux(ParticipacionCarrera autoPiloto) {
        pilotosAux.add(autoPiloto);
    }

    public void setEscuderiaSelecc(Escuderia escuderiaSelecc) {
        this.escuderiaSelecc = escuderiaSelecc;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public Escuderia getEscuderiaSelecc() {
        return escuderiaSelecc;
    }
}
