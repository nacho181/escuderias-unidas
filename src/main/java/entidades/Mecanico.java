package entidades;

public class Mecanico extends Persona{
    private Especialidad especialidad;
    private int aniosExperiencia;


    public Mecanico(String dni, String nombre, String apellido,Pais pais, Especialidad especialidad, int aniosExperiencia) {
        super(dni, nombre, apellido, pais);
        this.especialidad = especialidad;
        this.aniosExperiencia = aniosExperiencia;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "especialidad=" + especialidad +
                ", aniosExperiencia=" + aniosExperiencia +
                '}';
    }
}
