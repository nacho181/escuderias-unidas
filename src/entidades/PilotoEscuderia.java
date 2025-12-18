package entidades;

import java.time.LocalDate;

public class PilotoEscuderia{
    private Escuderia escuderia;
    private Piloto piloto;
    private LocalDate desdeFecha;
    private LocalDate hastaFecha;

    public PilotoEscuderia() {
    }

    public PilotoEscuderia(Escuderia escuderia, LocalDate desdeFecha, Piloto piloto, LocalDate hastaFecha) {
        this.escuderia = escuderia;
        this.desdeFecha = desdeFecha;
        this.piloto = piloto;
        this.hastaFecha = hastaFecha;
    }


    public Piloto getPiloto() {
        return piloto;
    }

    public LocalDate getDesdeFecha() {
        return desdeFecha;
    }

    public LocalDate getHastaFecha() {
        return hastaFecha;
    }
}
