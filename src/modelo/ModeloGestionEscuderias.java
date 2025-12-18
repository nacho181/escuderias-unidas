package modelo;

import entidades.Escuderia;

public class ModeloGestionEscuderias {
    private Modelo modelo;
    private Escuderia escuderiaSeleccionada;

    public ModeloGestionEscuderias(Modelo modelo) {
        this.modelo = modelo;
        escuderiaSeleccionada = null;
    }

    public Escuderia getEscuderiaSeleccionada() {
        return escuderiaSeleccionada;
    }

    public void setEscuderiaSeleccionada(Escuderia escuderiaSeleccionada) {
        this.escuderiaSeleccionada = escuderiaSeleccionada;
    }
}
