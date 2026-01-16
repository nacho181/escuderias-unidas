package modelo;

import entidades.AutoPiloto;
import entidades.Escuderia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ModeloPlanificarCarrera {
    private Modelo modelo;
    private LocalDate fecha;
    private int vueltas;
    private LocalTime hora;
    private int totCarreras;
    private Escuderia escuderiaSelecc;
    private String circuito;
    private ArrayList<AutoPiloto> carrera;

    public ModeloPlanificarCarrera(Modelo modelo) {
        this.modelo = modelo;
        fecha =  null;
        vueltas = 0;
        hora = null;
        totCarreras = 0;
        circuito = null;
        carrera = new ArrayList<>();
    }


    public ArrayList<AutoPiloto> getCarrera() {
        return carrera;
    }

    public void reiniciarCarrera() {
        this.carrera = new ArrayList<>();
    }


    public void agregarAutoPiloto(AutoPiloto autoPiloto) {
        carrera.add(autoPiloto);
    }

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setTotCarreras(int totCarreras) {
        this.totCarreras = totCarreras;
    }

    public void setEscuderiaSelecc(Escuderia escuderiaSelecc) {
        this.escuderiaSelecc = escuderiaSelecc;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getVueltas() {
        return vueltas;
    }

    public LocalTime getHora() {
        return hora;
    }

    public int getTotCarreras() {
        return totCarreras;
    }

    public Escuderia getEscuderiaSelecc() {
        return escuderiaSelecc;
    }
}
