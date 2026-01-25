package entidades;

import java.util.ArrayList;

public class Registro {

    private final ArrayList<Persona> personas;
    private final ArrayList<Auto> autos;
    private final ArrayList<Escuderia> escuderias;
    private final ArrayList<Circuito> circuitos;
    private final ArrayList<Pais> paises;
    private final ArrayList<Carrera> carreras;
    private final ArrayList<PilotoEscuderia> pilotoEscuderias;

    public Registro() {
        personas = new ArrayList<>();
        autos = new ArrayList<>();
        escuderias = new ArrayList<>();
        circuitos = new ArrayList<>();
        paises = new ArrayList<>();
        carreras = new ArrayList<>();
        pilotoEscuderias = new ArrayList<>();
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public ArrayList<Auto> getAutos() {
        return autos;
    }

    public ArrayList<Pais> getPaises() {
        return paises;
    }

    public ArrayList<Circuito> getCircuitos() {
        return circuitos;
    }

    public ArrayList<Escuderia> getEscuderias() {
        return escuderias;
    }

    public ArrayList<PilotoEscuderia> getPilotoEscuderias() {
        return pilotoEscuderias;
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }


    public void agregarPersona(Persona p) {
        personas.add(p);
    }
    public void agregarAuto(Auto a) {
        autos.add(a);
    }
    public void agregarEscuderia(Escuderia e) {
        escuderias.add(e);
    }
    public void agregarCircuito(Circuito c) {
        circuitos.add(c);
    }
    public void agregarPais(Pais p) {
        paises.add(p);
    }
    public void agregarPilotoEscuderias(PilotoEscuderia p) {
        pilotoEscuderias.add(p);
    }
    public void agregarCarrera(Carrera c) {
        carreras.add(c);
    }



}
