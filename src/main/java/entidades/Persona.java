package entidades;

public abstract class Persona {
    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private Pais pais;

    public Persona() {
    }

    public Persona(String dni, String nombre, String apellido, Pais pais) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "DNI     : " + dni + "\n" +
                "Nombre  : " + nombre + "\n" +
                "Apellido: " + apellido + "\n" +
                "País    : " + pais + "\n";
    }

}
