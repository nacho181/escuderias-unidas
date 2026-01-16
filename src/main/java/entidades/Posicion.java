package entidades;

public enum Posicion {
    PRIMERO(25),
    SEGUNDO(18),
    TERCERO(15),
    CUARTO(12),
    QUINTO(10),
    SEXTO(8),
    SEPTIMO(6),
    OCTAVO(4),
    NOVENO(2),
    DECIMO(1),
    UNDECIMO(0),
    DUODECIMO(0),
    DECIMOTERCERO(0),
    DECIMOCUARTO(0),
    DECIMOQUINTO(0),
    DECIMOSEXTO(0),
    DECIMOSEPTIMO(0),
    DECIMOCTAVO(0),
    DECIMONOVENO(0),
    VIGESIMO(0);

    private final int puntos;

    Posicion(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public static int getPuntos(int posicion) {
        if (posicion < 1 || posicion > values().length) {
            return 0;
        }
        return values()[posicion - 1].getPuntos();
    }
}
