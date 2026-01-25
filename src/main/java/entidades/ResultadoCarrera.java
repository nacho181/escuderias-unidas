package entidades;

public class ResultadoCarrera {
    private final int posicion;
    private final AutoPiloto autoPiloto;
    private int puntos;

    public ResultadoCarrera(int posicion,AutoPiloto autoPiloto , int puntos) {
        this.posicion = posicion;
        this.autoPiloto = autoPiloto;
        this.puntos = puntos;
    }

    public AutoPiloto getAutoPiloto() {
        return autoPiloto;
    }

    public int getPosicion() {
        return posicion;
    }
}

