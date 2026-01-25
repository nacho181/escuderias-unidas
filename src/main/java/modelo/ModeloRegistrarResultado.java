package modelo;

import entidades.*;
import java.time.LocalDate;

public class ModeloRegistrarResultado {
    private final Modelo modelo;
    private LocalDate fecha;
    private Circuito circuito;

    public ModeloRegistrarResultado(Modelo modelo) {
        this.modelo = modelo;
    }
    /** Suma puntos al piloto con el DNI indicado. */
    public void agregarPuntaje(String dni, int puntaje) {
        for (Persona persona : modelo.getRegistroGeneral().getPersonas()) {
            if (persona instanceof Piloto p && persona.getDni().equals(dni)) {
                p.agregarPuntosAcumulados(puntaje);
            }
        }
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public Circuito getCircuito() {
        return circuito;
    }
    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }
}


