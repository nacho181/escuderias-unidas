package modelo;

import entidades.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public static final DateTimeFormatter HORA_INPUT = DateTimeFormatter.ofPattern("HHmm");
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String REGEX_NOMBRE = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$";

    /**
     * Constructor que inicializa el main.java.modelo central y sus submodelos.
     * También crea las listas vacías de carreras y resultados.
     */
    public Modelo() {
        registroGeneral = new Registro();
        modeloRegistro = new ModeloRegistroGral(this);
        modeloGestionEscuderias = new ModeloGestionEscuderias(this);
        modeloPlanificarCarrera = new ModeloPlanificarCarrera(this);
        modeloRegistrarResultado = new ModeloRegistrarResultado(this);
        modeloInformeGenerales = new ModeloInformeGenerales(this);
    }


    /** Agrega una nueva carrera planificada. */
    public void agregarCarrera(Carrera c) {
        registroGeneral.agregarCarrera(c);
    }

    /** Busca una carrera por su fecha. */
    public Carrera buscarCarrera(LocalDate fecha) {
        for (Carrera c : registroGeneral.getCarreras()) {
            if (fecha.equals(c.getFechaRealizacion())) {
                return c;
            }
        }
        return null;
    }

    /** Busca una escudería por nombre. */
    public Escuderia buscarEscuderia(String nombre) {
        for (Escuderia e : registroGeneral.getEscuderias()) {
            if (nombre.equals(e.getNombre())) {
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

    /** Busca un auto por su main.java.modelo. */
    public Auto buscarAuto(String modelo) {
        for (Auto a : registroGeneral.getAutos()) {
            if (modelo.equals( a.getModelo())) {
                return a;
            }
        }
        return null;
    }


    public static boolean nombreInvalido(String nombre) {
        return nombre != null && nombre.matches(REGEX_NOMBRE);
    }


    // Getters

    public ModeloRegistroGral getModeloRegistro() {
        return modeloRegistro;
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
