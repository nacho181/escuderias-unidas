package entidades;

public class Auto {
    private int id;
    private boolean asignado;
    private String modelo;
    private String motor;

    public Auto() {
    }

    public Auto(String modelo, String motor) {
        this.modelo = modelo;
        this.motor = motor;
        asignado = false;
    }

    public String getModelo() {
        return modelo;
    }
    public Auto(int id, String modelo, String motor) {
        this.id = id;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
