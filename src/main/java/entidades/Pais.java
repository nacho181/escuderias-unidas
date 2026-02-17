package entidades;

public class Pais {
    private int idPais;
    private String descripcion;

    public Pais() {
    }

    public Pais(int idPais, String descripcion) {
        this.idPais = idPais;
        this.descripcion = descripcion;
    }

    public Pais(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "País : " + descripcion + "\n";
    }

    public void setId(int id) {
        this.idPais = id;
    }

    public int getId() {
        return idPais;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
