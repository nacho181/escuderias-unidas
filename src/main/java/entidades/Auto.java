package entidades;

import java.util.ArrayList;

public class Auto {
    private boolean asignado;
    private String modelo;
    private String motor;
    private ArrayList<Piloto> pilotos;

    public Auto() {
    }

    public String getModelo() {
        return modelo;
    }
    public Auto(String modelo, String motor) {
        this.modelo = modelo;
        this.motor = motor;
        asignado = false;
    }

    public boolean getAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }

    public String getMotor() {
        return motor;
    }

    @Override
    public String toString() {
        return "Modelo : " + modelo + "\n" +
                "Motor  : " + motor + "\n";
    }

}
