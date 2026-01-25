package modelo;

import entidades.Escuderia;
import entidades.Mecanico;
import entidades.Persona;

public class ModeloGestionEscuderias {
    private final Modelo modelo;
    private Escuderia escuderiaSeleccionada;

    public ModeloGestionEscuderias(Modelo modelo) {
        this.modelo = modelo;
        escuderiaSeleccionada = null;
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
