package modelo;

import entidades.Escuderia;
import entidades.Mecanico;
import entidades.Persona;
import entidades.PilotoEscuderia;


import java.time.LocalDate;

public class ModeloGestionEscuderias {
    private final Modelo modelo;
    private Escuderia escuderiaSeleccionada;

    public ModeloGestionEscuderias(Modelo modelo) {
        this.modelo = modelo;
        escuderiaSeleccionada = null;
    }

    public boolean pilotoSolapado(String dni, LocalDate fechaInicio, LocalDate fechaFinal) {
        boolean solapan = false;
        for (PilotoEscuderia p : modelo.getRegistroGeneral().getPilotoEscuderias()) {
            if (p.getPiloto().getDni().equals(dni)) {
                LocalDate existenteInicio = p.getDesdeFecha();
                LocalDate existenteFin = p.getHastaFecha();
                solapan =
                        (fechaInicio.isBefore(existenteInicio) && fechaFinal.isAfter(existenteFin))
                                || (fechaInicio.isBefore(existenteInicio) && fechaFinal.isAfter(existenteInicio) && fechaFinal.isBefore(existenteFin))
                                || (fechaInicio.isAfter(existenteInicio) && fechaFinal.isBefore(existenteFin))
                                || (fechaInicio.isAfter(existenteInicio) && fechaInicio.isBefore(existenteFin) && fechaFinal.isAfter(existenteFin));
            }
        }
        return solapan;
    }

    public boolean duplicadoMecanico(String dni) {
        for (Mecanico m : escuderiaSeleccionada.getMecanicos()) {
            if (m.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    /** Busca un mecánico por DNI. */
    public Mecanico buscarMecanico(String dni) {
        for (Persona persona : modelo.getRegistroGeneral().getPersonas()) {
            if (persona instanceof Mecanico m && persona.getDni().equals(dni)) {
                return m;
            }
        }
        return null;
    }

    public Escuderia getEscuderiaSeleccionada() {
        return escuderiaSeleccionada;
    }

    public void setEscuderiaSeleccionada(Escuderia escuderiaSeleccionada) {
        this.escuderiaSeleccionada = escuderiaSeleccionada;
    }
}
