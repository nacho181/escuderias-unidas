package mvc.controlador;

import entidades.*;
import mvc.modelo.Modelo;
import mvc.vista.VentanaPrincipal;
import service.ServiceManager;

import javax.swing.*;

/**
 * Controlador responsable de manejar el registro general del sistema.
 * Implementa la lógica de control entre la main.java.mvc.vista (interfaz gráfica) y el main.java.mvc.modelo de datos.
 * Se encarga de registrar main.java.entidades como Países, Escuderías, Autos, Circuitos, Pilotos y Mecánicos.
 * <p>
 * Utiliza el patrón MVC, centralizando la comunicación entre capas.
 * Incluye validaciones de duplicados para evitar registros repetidos.
 */
public class ControladorRegistroGeneral {

    private final VentanaPrincipal vista;
    private final Modelo modelo; // si todavía lo usás
    private final ServiceManager services;


    public ControladorRegistroGeneral(Modelo modelo,
                                      VentanaPrincipal vista,
                                      ServiceManager serviceManager) {
        this.vista = vista;
        this.modelo = modelo;
        this.services = serviceManager;

        inicializarEventosRegistroGnral();
    }

    /**
     * Inicializa los eventos asociados a los botones del módulo de registro general.
     * Cada botón activa una main.java.mvc.vista o ejecuta un metodo de registro correspondiente.
     */
    private void inicializarEventosRegistroGnral() {
        // Registro de Países
        vista.getRegistroGeneral().getRegistrarPaisButton().addActionListener(e -> vista.mostrarPanel("paises"));
        vista.getRegistroPaises().getVolverButton().addActionListener(e -> { limpiarCamposPaises(); vista.mostrarPanel("registro");});
        vista.getRegistroPaises().getRegistrarButton().addActionListener(e -> registrarPaises());

        // Registro de Autos
        vista.getRegistroGeneral().getRegistrarAutoButton().addActionListener(e -> vista.mostrarPanel("autos"));
        vista.getRegistroAuto().getVolverButton().addActionListener(e -> { vista.mostrarPanel("registro"); limpiarCamposAuto();});
        vista.getRegistroAuto().getRegistrarButton().addActionListener(e -> { registrarAuto(); limpiarCamposAuto();});

        // Registro de Escuderías
        vista.getRegistroGeneral().getRegistrarEscuderiaButton().addActionListener(e -> vista.mostrarPanel("escuderia"));
        vista.getRegistrarEscuderia().getVolverButton().addActionListener(e -> { limpiarCamposEscuderia(); vista.mostrarPanel("registro");});
        vista.getRegistrarEscuderia().getRegistrarButton().addActionListener(e -> { registrarEscuderia(); limpiarCamposEscuderia();});

        // Registro de Circuitos
        vista.getRegistroGeneral().getRegistrarCircuitoButton().addActionListener(e -> vista.mostrarPanel("circuito"));
        vista.getRegistroCircuito().getVolverButton().addActionListener(e -> { vista.mostrarPanel("registro"); limpiarCamposCircuito();});
        vista.getRegistroCircuito().getRegistrarButton().addActionListener(e -> registrarCircuito());

        // Registro de Pilotos
        vista.getRegistroGeneral().getRegistrarPilotoButton().addActionListener(e -> vista.mostrarPanel("piloto"));
        vista.getRegistroPiloto().getVolverButton().addActionListener(e -> { vista.mostrarPanel("registro"); limpiarCamposPiloto();});
        vista.getRegistroPiloto().getRegistrarButton().addActionListener(e -> registrarPiloto());

        // Registro de Mecánicos
        vista.getRegistroGeneral().getRegistrarMecanicoButton().addActionListener(e -> vista.mostrarPanel("mecanico"));
        vista.getRegistroMecanico().getVolverButton().addActionListener(e -> { vista.mostrarPanel("registro"); limpiarCamposMecanico();});
        vista.getRegistroMecanico().getRegistrarButton().addActionListener(e -> registrarMecanico());
    }

    /**
     * Registra un nuevo país, verificando que no exista previamente.
     */
    private void registrarPaises() {

        String descripcion = vista.getRegistroPaises().getDescripcionPaisField().getText();

        if (descripcion.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }

        try {

            Pais pais = services.getPaisService().crearPais(descripcion);

            JOptionPane.showMessageDialog(vista,
                    "Registro guardado con éxito. ID generado: " + pais.getId());

            limpiarCamposPaises();

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(vista, e.getMessage());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error inesperado al registrar país");

        }
    }


    private void limpiarCamposPaises() {
        vista.getRegistroPaises().getDescripcionPaisField().setText("");
    }

    /**
     * Registra un nuevo auto, evitando duplicados por main.java.mvc.modelo.
     */
    private void registrarAuto() {
        String modeloAuto = vista.getRegistroAuto().getModeloField().getText();
        String motor = vista.getRegistroAuto().getMotorField().getText();

        if (modeloAuto.isBlank() || motor.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }

        try {

            Auto auto = services.getAutoService().crearAuto(modeloAuto, motor);
            JOptionPane.showMessageDialog(vista,
                    "Registro guardado con éxito. ID generado: " + auto.getId());

            limpiarCamposPaises();

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(vista, e.getMessage());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error inesperado al registrar auto");

        }
    }

    private void limpiarCamposAuto() {
        vista.getRegistroAuto().getModeloField().setText("");
        vista.getRegistroAuto().getMotorField().setText("");
    }

    /**
     * Registra una nueva escudería, validando país y evitando duplicados por nombre.
     */
    private void registrarEscuderia() {
        String nombreEscuderia = vista.getRegistrarEscuderia().getNombreEscuderiaField().getText();
        String nombrePaisEscuderia = vista.getRegistrarEscuderia().getIdEscuderiaField().getText();

        if(!Modelo.nombreInvalido(nombreEscuderia)){
            JOptionPane.showMessageDialog(null, "El nombre es solo puede contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nombrePaisEscuderia.isBlank() || nombreEscuderia.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }


        try {
            Pais pais = services.getPaisService().obtenerPorNombre(nombrePaisEscuderia);
            Escuderia escuderia = services.getEscuderiaService().crearEscuderia(nombreEscuderia, pais);
            JOptionPane.showMessageDialog(vista,
                    "Registro guardado con éxito. ID generado: " + escuderia.getId());

            limpiarCamposPaises();

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(vista, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error inesperado al registrar escudería");
        }


    }

    private void limpiarCamposEscuderia() {
        vista.getRegistrarEscuderia().getIdEscuderiaField().setText("");
        vista.getRegistrarEscuderia().getNombreEscuderiaField().setText("");
    }

    /**
     * Registra un nuevo circuito, validando país y nombre duplicado.
     */
    private void registrarCircuito() {
        String nombreCircuito = vista.getRegistroCircuito().getNombreField().getText();
        String longitudCircuito = vista.getRegistroCircuito().getLongitudField().getText();
        String nombrePais = vista.getRegistroCircuito().getPaisField().getText();

        if (nombreCircuito.isBlank() || longitudCircuito.isBlank() || nombrePais.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }

        try {
            if (!Modelo.nombreInvalido(nombreCircuito)) {
                JOptionPane.showMessageDialog(null, "El nombre del circuito solo puede contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int numeroLongitud = Integer.parseInt(longitudCircuito);
            Pais pais = services.getPaisService().obtenerPorNombre(nombrePais);

            if (pais == null) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar un país válido");
                return;
            }

            //  Verificar duplicado
            if (modelo.getModeloRegistro().comprobarCircuito(nombreCircuito)) {
                JOptionPane.showMessageDialog(vista, "El circuito ya está registrado.");
                return;
            }

            modelo.getModeloRegistro().agregarCircuitoRGral(new Circuito(nombreCircuito, numeroLongitud, pais));
            JOptionPane.showMessageDialog(vista, "Registro guardado con éxito");
            limpiarCamposCircuito();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar números válidos");
        }
    }

    private void limpiarCamposCircuito() {
        vista.getRegistroCircuito().getNombreField().setText("");
        vista.getRegistroCircuito().getLongitudField().setText("");
        vista.getRegistroCircuito().getPaisField().setText("");
    }

    /**
     * Registra un nuevo piloto, evitando duplicados por DNI.
     */
    private void registrarPiloto() {
        String dni = vista.getRegistroPiloto().getDniField().getText();
        String nombre = vista.getRegistroPiloto().getNombreField().getText();
        String apellido = vista.getRegistroPiloto().getApellidoField().getText();
        String nombrePais = vista.getRegistroPiloto().getIdPaisField().getText();
        String numCompe = vista.getRegistroPiloto().getNumeroCompeField().getText();
        String numVict = vista.getRegistroPiloto().getNumVictField().getText();
        String numPole = vista.getRegistroPiloto().getNumPoleField().getText();
        String numVuelt = vista.getRegistroPiloto().getNumVueltField().getText();
        String numPod = vista.getRegistroPiloto().getNumPodField().getText();
        String numPuntos = vista.getRegistroPiloto().getNumPuntosField().getText();

        if (dni.isBlank() || nombre.isBlank() || apellido.isBlank() || nombrePais.isBlank() ||
                numCompe.isBlank() || numVict.isBlank() || numPole.isBlank() ||
                numVuelt.isBlank() || numPod.isBlank() || numPuntos.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }

        try {
            if (!Modelo.nombreInvalido(nombre) || !Modelo.nombreInvalido(apellido)) {
                JOptionPane.showMessageDialog(null, "El nombre y apellido solo puede contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int numeroCompe = Integer.parseInt(numCompe);
            int numeroVict = Integer.parseInt(numVict);
            int numeroPole = Integer.parseInt(numPole);
            int numeroVuelt = Integer.parseInt(numVuelt);
            int numeroPod = Integer.parseInt(numPod);
            int numeroPuntos = Integer.parseInt(numPuntos);

            Pais pais = modelo.getModeloRegistro().comprobarPais(nombrePais);
            if (pais == null) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar un país válido");
                return;
            }

            // Verificar duplicado
            if (modelo.getModeloRegistro().comprobarPersonaDni(dni)){
                JOptionPane.showMessageDialog(vista, "Esta persona ya se encuentra registrada.");
                return;
            }

            modelo.getModeloRegistro().agregarPersonaGral(new Piloto(
                    dni, nombre, apellido, pais, numeroCompe, numeroVict,
                    numeroPole, numeroVuelt, numeroPod, numeroPuntos));
            JOptionPane.showMessageDialog(vista, "Registro guardado con éxito");
            limpiarCamposPiloto();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar números válidos");
        }
    }

    private void limpiarCamposPiloto() {
        vista.getRegistroPiloto().getNombreField().setText("");
        vista.getRegistroPiloto().getApellidoField().setText("");
        vista.getRegistroPiloto().getDniField().setText("");
        vista.getRegistroPiloto().getIdPaisField().setText("");
        vista.getRegistroPiloto().getNumeroCompeField().setText("");
        vista.getRegistroPiloto().getNumVictField().setText("");
        vista.getRegistroPiloto().getNumPoleField().setText("");
        vista.getRegistroPiloto().getNumVueltField().setText("");
        vista.getRegistroPiloto().getNumPodField().setText("");
        vista.getRegistroPiloto().getNumPuntosField().setText("");
    }

    /**
     * Registra un nuevo mecánico, evitando duplicados por DNI.
     */
    private void registrarMecanico() {
        String dni = vista.getRegistroMecanico().getDniField().getText();
        String nombre = vista.getRegistroMecanico().getNombreField().getText();
        String apellido = vista.getRegistroMecanico().getApellidoField().getText();
        String nombrePais = vista.getRegistroMecanico().getIdPaisField().getText();
        Especialidad especialidad = (Especialidad) vista.getRegistroMecanico().getEspecialidadComboBox().getSelectedItem();
        String aniosExp = vista.getRegistroMecanico().getAniosField().getText();

        if (dni.isBlank() || nombre.isBlank() || apellido.isBlank() || nombrePais.isBlank() || aniosExp.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }

        try {
            if (!Modelo.nombreInvalido(nombre) || !Modelo.nombreInvalido(apellido)) {
                JOptionPane.showMessageDialog(null, "El nombre y apellido solo puede contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int numeroAniosExp = Integer.parseInt(aniosExp);
            Pais pais = modelo.getModeloRegistro().comprobarPais(nombrePais);

            if (pais == null) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar un país válido");
                return;
            }

            // Verificar duplicado
            if (modelo.getModeloRegistro().comprobarPersonaDni(dni)){
                JOptionPane.showMessageDialog(vista, "Esta persona ya se encuentra registrada.");
                return;
            }

            modelo.getModeloRegistro().agregarPersonaGral(new Mecanico(
                    dni, nombre, apellido, pais, especialidad, numeroAniosExp));
            JOptionPane.showMessageDialog(vista, "Registro guardado con éxito");
            limpiarCamposMecanico();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar números válidos");
        }
    }

    public void limpiarCamposMecanico() {
        vista.getRegistroMecanico().getNombreField().setText("");
        vista.getRegistroMecanico().getApellidoField().setText("");
        vista.getRegistroMecanico().getDniField().setText("");
        vista.getRegistroMecanico().getIdPaisField().setText("");
        vista.getRegistroMecanico().getAniosField().setText("");
    }
}

