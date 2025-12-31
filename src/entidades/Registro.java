package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Registro {

    private ArrayList<Persona> personas;
    private ArrayList<Auto> autos;
    private ArrayList<Escuderia> escuderias;
    private ArrayList<Circuito> circuitos;
    private ArrayList<Pais> paises;
    private Carrera carrera;
    private ArrayList<AutoPiloto> autoPilotos;
    private ArrayList<RegistroCarrera> registroCarrera;
    private ArrayList<AutoPiloto> podio;
    private ArrayList<Carrera> carreras;
    private ArrayList<PilotoEscuderia> pilotoEscuderias;

    public Registro() {
        personas = new ArrayList<>();
        autos = new ArrayList<>();
        escuderias = new ArrayList<>();
        circuitos = new ArrayList<>();
        paises = new ArrayList<>();
        podio = new ArrayList<>();
        carreras = new ArrayList<>();
        registroCarrera = new ArrayList<>();
        pilotoEscuderias = new ArrayList<>();
        autoPilotos = new ArrayList<>();
    }

    public Registro(Carrera carrera, ArrayList<AutoPiloto> posiciones, ArrayList<AutoPiloto> podio) {
        this.carrera = carrera;
        carrera.setTotalCarrerasCorridas(carrera.getTotalCarrerasCorridas() + 1);
        this.podio = podio;
        carreras.add(carrera);
    }

    public void informeFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
        for(Carrera carrera: carreras){
            LocalDate numAux = carrera.getFechaRealizacion();
            if(numAux.isAfter(fechaInicial) && numAux.isBefore(fechaFinal)){
                System.out.println(carrera);
            }
        }
    }


    public ArrayList<RegistroCarrera> getRegistroCarrera() {
        return registroCarrera;
    }

    public ArrayList<AutoPiloto> getAutoPilotos() {
        return autoPilotos;
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
    public void agregarRegistroCarrera(RegistroCarrera r) {
        registroCarrera.add(r);
    }
    public void agregarCarrera(Carrera c) {
        carreras.add(c);
    }



}
