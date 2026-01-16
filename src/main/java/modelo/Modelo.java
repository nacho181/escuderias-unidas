package modelo;

import entidades.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * Clase principal del main.java.modelo que centraliza la gestión de datos del sistema.
 * <p>
 * Se encarga de mantener las listas principales (carreras, resultados,
 * escuderías, pilotos, etc.) y de coordinar las operaciones entre los distintos
 * submodelos del programa.
 * </p>
 */
public class Modelo {
    private final Registro registroGeneral;
    private final ModeloRegistroGral modeloRegistro;
    private final ModeloGestionEscuderias modeloGestionEscuderias;
    private final ModeloPlanificarCarrera modeloPlanificarCarrera;
    private final ModeloRegistrarResultado modeloRegistrarResultado;
    private final ModeloInformeGenerales modeloInformeGenerales;
    private final ArrayList<Carrera> carreras;
    private final ArrayList<RegistroCarrera> carrerasResultados;
    public static final DateTimeFormatter HORA_INPUT = DateTimeFormatter.ofPattern("HHmm");
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String REGEX_NOMBRE = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$";

    /**
     * Constructor que inicializa el main.java.modelo central y sus submodelos.
     * También crea las listas vacías de carreras y resultados.
     */
    public Modelo() {
        registroGeneral = new Registro();
        modeloRegistro = new ModeloRegistroGral(registroGeneral);
        modeloGestionEscuderias = new ModeloGestionEscuderias(this);
        modeloPlanificarCarrera = new ModeloPlanificarCarrera(this);
        modeloRegistrarResultado = new ModeloRegistrarResultado(this);
        modeloInformeGenerales = new ModeloInformeGenerales(this);
        carrerasResultados = new ArrayList<>();
        carreras = new ArrayList<>();
    }

    /**
     * Devuelve las carreras que se realizaron entre dos fechas dadas.
     * @param fechaInicio fecha inicial del rango.
     * @param fechaFinal fecha final del rango.
     * @return lista de carreras dentro del rango.
     */
    public ArrayList<RegistroCarrera> obtenerCarrerasEntreFechas(LocalDate fechaInicio, LocalDate fechaFinal) {
        ArrayList<RegistroCarrera> carrerasEntreFechas = new ArrayList<>();

        if (carrerasResultados == null) {
            return carrerasEntreFechas;
        }

        for (RegistroCarrera r : carrerasResultados) {
            LocalDate fecha = r.getCarrera().getFechaRealizacion();
            if (fecha != null) {
                if (!fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFinal)) {
                    carrerasEntreFechas.add(r);
                }
            }
        }
        return carrerasEntreFechas;
    }


    /** Agrega una nueva carrera planificada. */
    public void agregarCarrera(Carrera c) {
        carreras.add(c);
    }

    /** Agrega una carrera finalizada al registro de resultados. */
    public void agregarCarrerasResultado(RegistroCarrera carrera) {
        carrerasResultados.add(carrera);
    }

    /** Suma puntos al piloto con el DNI indicado. */
    public void agregarPuntaje(String dni, int puntaje) {
        for (Persona persona : registroGeneral.getPersonas()) {
            if (persona instanceof Piloto p && persona.getDni().equals(dni)) {
                p.agregarPuntosAcumulados(puntaje);
            }
        }
    }

    /** Busca una carrera por su fecha. */
    public Carrera buscarCarrera(LocalDate fecha) {
        for (Carrera c : carreras) {
            if (c.getFechaRealizacion().equals(fecha)) {
                return c;
            }
        }
        return null;
    }

    /** Busca una escudería por nombre. */
    public Escuderia buscarEscuderia(String nombre) {
        for (Escuderia e : registroGeneral.getEscuderias()) {
            if (e.getNombre().equals(nombre)) {
                return e;
            }
        }
        return null;
    }

    /** Busca un piloto por DNI. */
    public Piloto buscarPiloto(String dni) {
        for (Persona persona : registroGeneral.getPersonas()) {
            if (persona instanceof Piloto p && persona.getDni().equals(dni)) {
                return p;
            }
        }
        return null;
    }

    /** Busca un circuito por nombre. */
    public Circuito buscarCircuito(String nombre) {
        for (Circuito c : registroGeneral.getCircuitos()) {
            if (c.getNombre().equals(nombre)) {
                return c;
            }
        }
        return null;
    }

    /** Busca un piloto asociado a una escudería por DNI. */
    public PilotoEscuderia buscarPilotEscu(Escuderia escuderia, String dni) {
        for (Escuderia e : registroGeneral.getEscuderias()) {
            if (e.equals(escuderia)) {
                for (PilotoEscuderia p : e.getPilotos()) {
                    if (p.getPiloto().getDni().equals(dni)) {
                        return p;
                    }
                }
            }
        }
        return null;
    }

    /** Busca un mecánico por DNI. */
    public Mecanico buscarMecanico(String dni) {
        for (Persona persona : registroGeneral.getPersonas()) {
            if (persona instanceof Mecanico m && persona.getDni().equals(dni)) {
                return m;
            }
        }
        return null;
    }

    /** Busca un auto por su main.java.modelo. */
    public Auto buscarAuto(String modelo) {
        for (Auto a : registroGeneral.getAutos()) {
            if (a.getModelo().equals(modelo)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Comprueba si un piloto ya tiene un auto asignado en una fecha determinada.
     * @return true si el piloto tiene un auto asignado en esa fecha.
     */
    public boolean comprobarAutoPiloto(String dni, LocalDate fecha) {
        for (AutoPiloto a : registroGeneral.getAutoPilotos()) {
            if (a.getPiloto().getDni().equals(dni) && a.getFechaAsignacion().equals(fecha)) {
                return true;
            }
        }
        return false;
    }

    /** Comprueba si existe un país con el ID indicado. */
    public Pais comprobarPais(int id) {
        for (Pais p : registroGeneral.getPaises()) {
            if (p.getIdPais() == id) {
                return p;
            }
        }
        return null;
    }

    public static boolean nombreValido(String nombre) {
        return nombre != null && nombre.matches(REGEX_NOMBRE);
    }


    // Getters

    public ModeloRegistroGral getModeloRegistro() {
        return modeloRegistro;
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public ArrayList<RegistroCarrera> getCarrerasResultados() {
        return carrerasResultados;
    }

    public Registro getRegistroGeneral() {
        return registroGeneral;
    }

    public ModeloPlanificarCarrera getModeloPlanificarCarrera() {
        return modeloPlanificarCarrera;
    }

    public ModeloInformeGenerales getModeloInformeGenerales() {
        return modeloInformeGenerales;
    }

    public ModeloGestionEscuderias getModeloGestionEscuderias() {
        return modeloGestionEscuderias;
    }

    public ModeloRegistrarResultado getModeloRegistrarResultado() {
        return modeloRegistrarResultado;
    }
}
