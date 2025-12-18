package controlador;

import entidades.*;
import modelo.Modelo;
import vista.VentanaPrincipal;

import javax.swing.*;

/**
 * Controlador responsable de manejar el registro general del sistema.
 * Implementa la lógica de control entre la vista (interfaz gráfica) y el modelo de datos.
 * Se encarga de registrar entidades como Países, Escuderías, Autos, Circuitos, Pilotos y Mecánicos.
 * <p>
 * Utiliza el patrón MVC, centralizando la comunicación entre capas.
 * Incluye validaciones de duplicados para evitar registros repetidos.
 */
public class ControladorRegistroGeneral {
    private final VentanaPrincipal vista;
    private final Modelo modelo;

    public ControladorRegistroGeneral(Modelo modelo, VentanaPrincipal vista) {
        this.vista = vista;
        this.modelo = modelo;
        inicializarEventosRegistroGnral();
    }

    /**
     * Inicializa los eventos asociados a los botones del módulo de registro general.
     * Cada botón activa una vista o ejecuta un metodo de registro correspondiente.
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
        String id = vista.getRegistroPaises().getIdPaisField().getText();
        String descripcion = vista.getRegistroPaises().getDescripcionPaisField().getText();

        if (id.isBlank() || descripcion.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }

        try {
            int numero = Integer.parseInt(id);

            // Verificar duplicado
            for (Pais p : modelo.getRegistroGeneral().getPaises()) {
                if (p.getIdPais() == numero || p.getDescripcion().equalsIgnoreCase(descripcion)) {
                    JOptionPane.showMessageDialog(vista, "El país ya está registrado.");
                    return;
                }
            }

            modelo.getModeloRegistro().agregarPaisRGral(new Pais(numero, descripcion));
            JOptionPane.showMessageDialog(vista, "Registro guardado con éxito");
            limpiarCamposPaises();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar un número válido");
        }
    }

    private void limpiarCamposPaises() {
        vista.getRegistroPaises().getIdPaisField().setText("");
        vista.getRegistroPaises().getDescripcionPaisField().setText("");
    }

    /**
     * Registra un nuevo auto, evitando duplicados por modelo.
     */
    private void registrarAuto() {
        String modeloAuto = vista.getRegistroAuto().getModeloField().getText();
        String motor = vista.getRegistroAuto().getMotorField().getText();

        if (modeloAuto.isBlank() || motor.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }

        // Verificar duplicado
        for (Auto a : modelo.getRegistroGeneral().getAutos()) {
            if (a.getModelo().equalsIgnoreCase(modeloAuto)) {
                JOptionPane.showMessageDialog(vista, "El auto ya está registrado.");
                return;
            }
        }

        modelo.getModeloRegistro().agregarAutoRGral(new Auto(modeloAuto, motor));
        JOptionPane.showMessageDialog(vista, "Registro guardado con éxito");
        limpiarCamposAuto();
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
        String idPaisEscuderia = vista.getRegistrarEscuderia().getIdEscuderiaField().getText();

        if (idPaisEscuderia.isBlank() || nombreEscuderia.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }

        try {
            int numero = Integer.parseInt(idPaisEscuderia);
            Pais pais = modelo.comprobarPais(numero);

            if (pais == null) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar un país válido");
                return;
            }

            //  Verificar duplicado
            for (Escuderia e : modelo.getRegistroGeneral().getEscuderias()) {
                if (e.getNombre().equalsIgnoreCase(nombreEscuderia)) {
                    JOptionPane.showMessageDialog(vista, "La escudería ya está registrada.");
                    return;
                }
            }

            modelo.getModeloRegistro().agregarEscuderiaRGral(new Escuderia(nombreEscuderia, pais));
            JOptionPane.showMessageDialog(vista, "Registro guardado con éxito");
            limpiarCamposEscuderia();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar un número válido");
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
        String idPaisCircuito = vista.getRegistroCircuito().getPaisField().getText();

        if (nombreCircuito.isBlank() || longitudCircuito.isBlank() || idPaisCircuito.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }

        try {
            int numeroLongitud = Integer.parseInt(longitudCircuito);
            int numeroPais = Integer.parseInt(idPaisCircuito);
            Pais pais = modelo.comprobarPais(numeroPais);

            if (pais == null) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar un país válido");
                return;
            }

            //  Verificar duplicado
            for (Circuito c : modelo.getRegistroGeneral().getCircuitos()) {
                if (c.getNombre().equalsIgnoreCase(nombreCircuito)) {
                    JOptionPane.showMessageDialog(vista, "El circuito ya está registrado.");
                    return;
                }
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
        String idPais = vista.getRegistroPiloto().getIdPaisField().getText();
        String numCompe = vista.getRegistroPiloto().getNumeroCompeField().getText();
        String numVict = vista.getRegistroPiloto().getNumVictField().getText();
        String numPole = vista.getRegistroPiloto().getNumPoleField().getText();
        String numVuelt = vista.getRegistroPiloto().getNumVueltField().getText();
        String numPod = vista.getRegistroPiloto().getNumPodField().getText();
        String numPuntos = vista.getRegistroPiloto().getNumPuntosField().getText();

        if (dni.isBlank() || nombre.isBlank() || apellido.isBlank() || idPais.isBlank() ||
                numCompe.isBlank() || numVict.isBlank() || numPole.isBlank() ||
                numVuelt.isBlank() || numPod.isBlank() || numPuntos.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }

        try {
            int numeroPais = Integer.parseInt(idPais);
            int numeroCompe = Integer.parseInt(numCompe);
            int numeroVict = Integer.parseInt(numVict);
            int numeroPole = Integer.parseInt(numPole);
            int numeroVuelt = Integer.parseInt(numVuelt);
            int numeroPod = Integer.parseInt(numPod);
            int numeroPuntos = Integer.parseInt(numPuntos);

            Pais pais = modelo.comprobarPais(numeroPais);
            if (pais == null) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar un país válido");
                return;
            }

            // Verificar duplicado
            for (Piloto p : modelo.getRegistroGeneral().getPiloto()) {
                if (p.getDni().equals(dni)) {
                    JOptionPane.showMessageDialog(vista, "El piloto ya está registrado.");
                    return;
                }
            }

            modelo.getModeloRegistro().agregarPilotoRGral(new Piloto(
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
        String idPais = vista.getRegistroMecanico().getIdPaisField().getText();
        Especialidad especialidad = (Especialidad) vista.getRegistroMecanico().getEspecialidadComboBox().getSelectedItem();
        String aniosExp = vista.getRegistroMecanico().getAniosField().getText();

        if (dni.isBlank() || nombre.isBlank() || apellido.isBlank() || idPais.isBlank() || aniosExp.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos");
            return;
        }

        try {
            int numeroPais = Integer.parseInt(idPais);
            int numeroAniosExp = Integer.parseInt(aniosExp);
            Pais pais = modelo.comprobarPais(numeroPais);

            if (pais == null) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar un país válido");
                return;
            }

            // Verificar duplicado
            for (Mecanico m : modelo.getRegistroGeneral().getMecanicos()) {
                if (m.getDni().equals(dni)) {
                    JOptionPane.showMessageDialog(vista, "El mecánico ya está registrado.");
                    return;
                }
            }

            modelo.getModeloRegistro().agregarMecanicoRGral(new Mecanico(
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

