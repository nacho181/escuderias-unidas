package entidades;

public class Circuito {
    private int id;
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

    public int getLongitud() {
        return longitud;
    }

    public Pais getPais() {
        return pais;
    }

    public void setId(int id) {
        this.id = id;
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
