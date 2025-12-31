package controlador;

import entidades.*;
import modelo.Modelo;
import vista.VentanaPrincipal;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static modelo.Modelo.HORA_INPUT;
import static modelo.Modelo.formatter;

/**
 * Controlador encargado de la planificación de carreras.
 * Gestiona la selección de circuitos, escuderías, y el registro de autos y pilotos participantes.
 * Incluye validaciones de datos, verificación de contratos vigentes y detección de duplicados.
 */
public class ControladorPlanificarCarrera {
    private final VentanaPrincipal vista;
    private final Modelo modelo;

    /**
     * Constructor del controlador de planificación de carreras.
     * @param modelo instancia del modelo principal.
     * @param vista  instancia de la ventana principal.
     */
    public ControladorPlanificarCarrera(Modelo modelo, VentanaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        inicializarEventos();
    }

    /**
     * Inicializa los eventos asociados a los botones de las vistas utilizadas en la planificación de carreras.
     */
    private void inicializarEventos() {
        // Registro de circuito
        vista.getPlanificarCarrera().getRegistrarButton().addActionListener(e -> registrarCircuito());
        vista.getPlanificarCarrera().getVolverButton().addActionListener(e -> {limpiarCamposRegistrarCircuito();vista.mostrarPanel("menu");modelo.getModeloPlanificarCarrera().setCircuito(null); });

        // Configuración de datos de carrera
        vista.getCircuitoSeleccionado().getContinuarConElRegistroButton().addActionListener(e -> circuitoSeleccionado());
        vista.getCircuitoSeleccionado().getVolverButton().addActionListener(e -> {limpiarCampoCircSelecc();vista.mostrarPanel("planificarCarrera");});

        // Selección de escudería
        vista.getSeleccionarEscuderia().getEnviarButton().addActionListener(e -> seleccionarEscuderia());
        vista.getSeleccionarEscuderia().getVolverButton().addActionListener(e -> volverSeleccionarEscuderia());

        // Registro de autos y pilotos
        vista.getRegistrarAutoPilotos().getRegistrarButton().addActionListener(e -> registrarAutoPilotos());
        vista.getRegistrarAutoPilotos().getVolverButton().addActionListener(e -> {limpiarRegistrarAutoPilotos(); vista.mostrarPanel("seleccionarEscuderia");});
    }

    /**
     * Verifica si el circuito ingresado existe y continúa con el registro.
     * Si no se encuentra, se notifica al usuario.
     */
    private void registrarCircuito() {
        String nombreCircuito = vista.getPlanificarCarrera().getCircuitoField().getText();
        Circuito auxCircuito = modelo.buscarCircuito(nombreCircuito);

        if (auxCircuito != null) {
            modelo.getModeloPlanificarCarrera().setCircuito(auxCircuito.getNombre());
            limpiarCamposRegistrarCircuito();
            vista.mostrarPanel("circuitoSeleccionado");
        } else {
            limpiarCamposRegistrarCircuito();
            JOptionPane.showMessageDialog(null, "Circuito no encontrado.");
        }
    }

    /**
     * Limpia el campo de texto del nombre de circuito.
     */
    private void limpiarCamposRegistrarCircuito() {
        vista.getPlanificarCarrera().getCircuitoField().setText("");
    }

    /**
     * Guarda los datos generales de la carrera (fecha, vueltas, hora y total de carreras)
     * y pasa al panel de selección de escudería.
     */
    private void circuitoSeleccionado() {
        String fechaStr = vista.getCircuitoSeleccionado().getFechaField().getText();
        String totCarreras = vista.getCircuitoSeleccionado().getTotCarreraField().getText();
        String numVueltas = vista.getCircuitoSeleccionado().getNumVueltField().getText();
        String horaCarreraStr = vista.getCircuitoSeleccionado().getHoraCarrera().getText();
        LocalTime horaCarrera = null;
        LocalDate fecha = null;

        if(fechaStr.isBlank() || totCarreras.isBlank() || numVueltas.isBlank() || horaCarreraStr.isBlank()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{
            horaCarrera = LocalTime.parse(horaCarreraStr, HORA_INPUT);
            fecha = LocalDate.parse(fechaStr, formatter);
        }catch (Exception _){
            JOptionPane.showMessageDialog(
                    null,                      // o null
                    "La fecha o la hora están fuera de formato, recuerde de usar el formato (AAAAMMDD) y (HHmm).",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if(modelo.buscarCarrera(fecha) != null) {
            JOptionPane.showMessageDialog(null, "Ya existe una carrera en esa fecha.", "ERROR", JOptionPane.ERROR_MESSAGE);
        return;
        }

        try {
            int numeroVueltas = Integer.parseInt(numVueltas);
            int totalCarreras = Integer.parseInt(totCarreras);

            modelo.getModeloPlanificarCarrera().setFecha(fecha);
            modelo.getModeloPlanificarCarrera().setHora(horaCarrera);
            modelo.getModeloPlanificarCarrera().setVueltas(numeroVueltas);
            modelo.getModeloPlanificarCarrera().setTotCarreras(totalCarreras);

            limpiarCampoCircSelecc();
            vista.mostrarPanel("seleccionarEscuderia");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar números válidos.");
        }
    }

    /**
     * Limpia los campos del formulario de datos del circuito seleccionado.
     */
    private void limpiarCampoCircSelecc() {
        vista.getCircuitoSeleccionado().getHoraCarrera().setText("");
        vista.getCircuitoSeleccionado().getFechaField().setText("");
        vista.getCircuitoSeleccionado().getNumVueltField().setText("");
        vista.getCircuitoSeleccionado().getTotCarreraField().setText("");
    }

    /**
     * Permite seleccionar una escudería existente para asignar sus pilotos y autos a la carrera.
     */
    private void seleccionarEscuderia() {
        String nombreEscuderia = vista.getSeleccionarEscuderia().getNombreEscuField().getText();

        if (modelo.buscarEscuderia(nombreEscuderia) != null) {
            modelo.getModeloPlanificarCarrera().setEscuderiaSelecc(modelo.buscarEscuderia(nombreEscuderia));
            limpiarSeleccionarEscuderia();
            vista.mostrarPanel("registrarPilotos");
        } else {
            JOptionPane.showMessageDialog(null, "Escudería no registrada.");
            limpiarSeleccionarEscuderia();
        }
    }

    /**
     * Limpia el campo de texto de selección de escudería.
     */
    private void limpiarSeleccionarEscuderia() {
        vista.getSeleccionarEscuderia().getNombreEscuField().setText("");
    }

    /**
     * Permite regresar a la pantalla anterior confirmando con el usuario.
     * Si se acepta, se limpian los datos temporales de la carrera en planificación.
     */
    private void volverSeleccionarEscuderia() {
        int opcion = JOptionPane.showConfirmDialog(
                vista,
                "¿Estás seguro de que querés volver? Se perderán los datos ingresados.",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            modelo.getModeloPlanificarCarrera().setTotCarreras(0);
            modelo.getModeloPlanificarCarrera().setHora(null);
            modelo.getModeloPlanificarCarrera().setVueltas(0);
            modelo.getModeloPlanificarCarrera().setFecha(null);
            modelo.getModeloPlanificarCarrera().setEscuderiaSelecc(null);
            modelo.getModeloPlanificarCarrera().setCircuito(null);
            limpiarSeleccionarEscuderia();
            vista.mostrarPanel("planificarCarrera");
        }
    }

    /**
     * Registra la participación de un piloto con un auto en la carrera actual,
     * validando los siguientes aspectos:
     * - Que los campos no estén vacíos
     * - Que el piloto y el auto existan
     * - Que el piloto esté contratado y disponible para la fecha de la carrera
     * - Que no haya duplicados de piloto o auto en la misma carrera
     */
    private void registrarAutoPilotos() {
        String dniPiloto = vista.getRegistrarAutoPilotos().getDniField().getText();
        String modeloAuto = vista.getRegistrarAutoPilotos().getModeloField().getText();
        int numVueltas = modelo.getModeloPlanificarCarrera().getVueltas();
        LocalTime hora = modelo.getModeloPlanificarCarrera().getHora();
        int totalCarreras = modelo.getModeloPlanificarCarrera().getTotCarreras();
        Circuito circuito = modelo.buscarCircuito(modelo.getModeloPlanificarCarrera().getCircuito());
        // Parseo de la fecha a LocalDate
        LocalDate fecha = modelo.getModeloPlanificarCarrera().getFecha();


        if (dniPiloto.isBlank() || modeloAuto.isBlank()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            limpiarRegistrarAutoPilotos();
            return;
        }

        // Variables auxiliares
        Auto autoSeleccionado = null;
        boolean autoEncontrado = false;
        boolean pilotoEncontrado = false;
        boolean pilotoContratado = false;
        boolean pilotoOcupado = false;
        boolean duplicado = false;

        // Buscar piloto dentro de la escudería seleccionada
        PilotoEscuderia pilotoEscuderia = modelo.buscarPilotEscu(modelo.getModeloPlanificarCarrera().getEscuderiaSelecc(), dniPiloto);

        // Buscar auto dentro de la escudería seleccionada
        for (Auto auto : modelo.getModeloPlanificarCarrera().getEscuderiaSelecc().getAutos()) {
            if (auto.getModelo().equalsIgnoreCase(modeloAuto)) {
                autoSeleccionado = auto;
                autoEncontrado = true;
                break;
            }
        }

        // Validaciones sobre el piloto
        if (pilotoEscuderia != null) {
            pilotoEncontrado = true;

            // Verificar contrato vigente
            if (fecha.isAfter(pilotoEscuderia.getDesdeFecha()) &&
                    fecha.isBefore(pilotoEscuderia.getHastaFecha())) {
                pilotoContratado = true;
                pilotoOcupado = modelo.comprobarAutoPiloto(dniPiloto, fecha);
            }

            // Verificar duplicado en la misma carrera
            for (AutoPiloto ap : modelo.getModeloPlanificarCarrera().getCarrera()) {
                if (ap.getFechaAsignacion().equals(fecha) &&
                        (ap.getPiloto().equals(pilotoEscuderia.getPiloto()) || ap.getAuto().equals(autoSeleccionado))) {
                    duplicado = true;
                    break;
                }
            }
        }

        // Mensajes de error específicos
        if (duplicado) {
            JOptionPane.showMessageDialog(null, "El piloto o auto ya fue registrado en esta carrera.");
            limpiarRegistrarAutoPilotos();
            return;
        }
        if (!pilotoEncontrado) {
            JOptionPane.showMessageDialog(null, "El piloto no pertenece a esta escudería.");
            limpiarRegistrarAutoPilotos();
            return;
        }
        if (!autoEncontrado) {
            JOptionPane.showMessageDialog(null, "El auto no pertenece a esta escudería.");
            limpiarRegistrarAutoPilotos();
            return;
        }
        if (!pilotoContratado) {
            JOptionPane.showMessageDialog(null, "El contrato del piloto no está vigente para esta fecha.");
            limpiarRegistrarAutoPilotos();
            return;
        }
        if (pilotoOcupado) {
            JOptionPane.showMessageDialog(null, "El piloto ya está asignado a otra carrera en esa fecha.");
            limpiarRegistrarAutoPilotos();
            return;
        }

        // Registrar AutoPiloto válido
        modelo.getModeloPlanificarCarrera().agregarAutoPiloto(new AutoPiloto(fecha, pilotoEscuderia.getPiloto(), autoSeleccionado));
        ArrayList<AutoPiloto> autosPilotos = modelo.getModeloPlanificarCarrera().getCarrera();

        // Si es el primer registro, se crea la carrera completa
        if (modelo.getModeloPlanificarCarrera().getCarrera().size() == 1) {
            modelo.agregarCarrera(new Carrera(fecha, numVueltas, hora, totalCarreras, circuito, autosPilotos));
            modelo.getModeloPlanificarCarrera().reiniciarCarrera();
            modelo.getModeloPlanificarCarrera().setTotCarreras(0);
            modelo.getModeloPlanificarCarrera().setHora(null);
            modelo.getModeloPlanificarCarrera().setVueltas(0);
            modelo.getModeloPlanificarCarrera().setCircuito(null);
            modelo.getModeloPlanificarCarrera().setFecha(null);
            modelo.getModeloPlanificarCarrera().setEscuderiaSelecc(null);
            JOptionPane.showMessageDialog(null, "Carrera registrada exitosamente. Retornando al menú principal.");
            vista.mostrarPanel("menu");
        } else {
            JOptionPane.showMessageDialog(null, "Piloto y auto agregados correctamente. Continúe con el siguiente registro.");
        }

        limpiarRegistrarAutoPilotos();
    }

    /**
     * Limpia los campos del formulario de registro de Auto-Piloto.
     */
    private void limpiarRegistrarAutoPilotos() {
        vista.getRegistrarAutoPilotos().getDniField().setText("");
        vista.getRegistrarAutoPilotos().getModeloField().setText("");
    }
}

