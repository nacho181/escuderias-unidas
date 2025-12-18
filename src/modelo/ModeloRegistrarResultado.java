package modelo;

import entidades.AutoPiloto;
import entidades.Circuito;

import java.time.LocalDate;
import java.util.ArrayList;

public class ModeloRegistrarResultado {
    private Modelo modelo;

    private LocalDate fecha;
    private Circuito circuito;

    public ModeloRegistrarResultado(Modelo modelo) {
        this.modelo = modelo;
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


