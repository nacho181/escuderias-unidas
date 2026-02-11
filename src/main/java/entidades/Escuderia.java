package entidades;

import java.util.ArrayList;

public class Escuderia {
    private final String nombre;
    private final ArrayList<Auto> autos;
    private final ArrayList<PilotoEscuderia> pilotos;
    private final ArrayList<Mecanico> mecanicos;
    private final Pais pais;


    public Escuderia(String nombre, Pais pais) {
        this.nombre = nombre;
        this.pais = pais;
        autos = new ArrayList<>();
        pilotos = new ArrayList<>();
        mecanicos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<PilotoEscuderia> getPilotos() {
        return pilotos;
    }

    public ArrayList<Auto> getAutos() {
        return autos;
    }

    public ArrayList<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public void agregarAuto(Auto auto) {
        autos.add(auto);
    }

    public void agregarPilotoEscuderia(PilotoEscuderia piloto) {
        pilotos.add(piloto);
    }

    public void agregarMecanico(Mecanico mecanico) {
        mecanicos.add(mecanico);
    }


}
