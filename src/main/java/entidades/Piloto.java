package entidades;

public class Piloto extends Persona implements Comparable<Piloto>{
    private int numeroCompetencia;
    private int victorias;
    private int polePosition;
    private int vueltasRapidas;
    private int podios;
    private int puntosAcumulados;


    public Piloto(String dni, String nombre, String apellido, Pais pais, int numeroCompetencia, int victorias, int polePosition, int vueltasRapidas, int podios, int puntosAcumulados) {
        super(dni, nombre, apellido, pais);
        this.numeroCompetencia = numeroCompetencia;
        this.victorias = victorias;
        this.polePosition = polePosition;
        this.vueltasRapidas = vueltasRapidas;
        this.podios = podios;
        this.puntosAcumulados = puntosAcumulados;
    }

    public int getNumeroCompetencia() {
        return numeroCompetencia;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setNumeroCompetencia(int numeroCompetencia) {
        this.numeroCompetencia = numeroCompetencia;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getPolePosition() {
        return polePosition;
    }

    public void setPolePosition(int polePosition) {
        this.polePosition = polePosition;
    }

    public int getVueltasRapidas() {
        return vueltasRapidas;
    }

    public void setVueltasRapidas(int vueltasRapidas) {
        this.vueltasRapidas = vueltasRapidas;
    }

    public int getPodios() {
        return podios;
    }

    public void setPodios(int podios) {
        this.podios = podios;
    }

    public void agregarPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados += puntosAcumulados;
    }

    @Override
    public String toString() {
        return super.toString() + "Piloto{" +
                "numeroCompetencia=" + numeroCompetencia +
                ", victorias=" + victorias +
                ", polePosition=" + polePosition +
                ", vueltasRapidas=" + vueltasRapidas +
                ", podios=" + podios +
                ", puntosAcumulados=" + puntosAcumulados +
                '}';
    }

    @Override
    public int compareTo(Piloto o) {
        return this.puntosAcumulados -  o.puntosAcumulados;
    }
}
