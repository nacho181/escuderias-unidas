package entidades;

public class Circuito {
    private String nombre;
    private int longitud;
    private Pais pais;

    public Circuito() {
    }
    public Circuito(String nombre, int longitud, Pais pais) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Circuito{" +
                "nombre='" + nombre + '\'' +
                ", longitud=" + longitud +
                ", pais=" + pais +
                '}';
    }
}
