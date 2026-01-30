package controlador;

import entidades.*;
import modelo.Modelo;
import vista.VentanaPrincipal;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;



/**
 * Controlador encargado del proceso de registro de resultados de una carrera.
 * Gestiona las interacciones entre la main.java.vista, el main.java.modelo y la validación de datos.
 */
public class ControladorRegistrarResultado {

    private final Modelo modelo;
    private final VentanaPrincipal vista;

    // Constructor
    public ControladorRegistrarResultado(Modelo modelo, VentanaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        inicializarEventos();
    }

    // Eventos principales
    private void inicializarEventos() {
        vista.getRegistroResultados().getEnviarButton().addActionListener(e -> enviarFecha());
        vista.getRegistroResultados().getVolverButton().addActionListener(e -> {limpiarCamposRegistroResultado();modelo.getModeloRegistrarResultado().setFecha(null);vista.mostrarPanel("menu");});

        vista.getSeleccionarPosiciones().getRegistrarButton().addActionListener(e -> registrarPilotos());
        vista.getSeleccionarPosiciones().getVolverButton().addActionListener(e -> {vista.getSeleccionarPosiciones().limpiarTabla();modelo.getModeloRegistrarResultado().setFecha(null);vista.mostrarPanel("registroResultados");});
    }

    // Enviar fecha
    /**
     * Busca la carrera asociada a la fecha ingresada y carga sus pilotos en la tabla.
     */
    private void enviarFecha() {
        String fechaStr = vista.getRegistroResultados().getFechaField().getText();
        LocalDate fecha;

        try{
            fecha = LocalDate.parse(fechaStr, Modelo.formatter);
        }catch (Exception unused){
            JOptionPane.showMessageDialog(
                    null,                      // o null
                    "La fecha esta fuera de formato, recuerde de usar el formato (AAAAMMDD).",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        Carrera carrera = modelo.buscarCarrera(fecha);
        if (carrera == null) {
            limpiarCamposRegistroResultado();
            JOptionPane.showMessageDialog(vista, "Carrera no encontrada");
            return;
        }

        if ((EstadoCarrera.FINALIZADA).equals(carrera.getEstado())){
            limpiarCamposRegistroResultado();
            JOptionPane.showMessageDialog(vista, "Esta carrera ya fue registrada.");
            return;
        }

        // Carga los participantes de la carrera en la tabla
        vista.getSeleccionarPosiciones().cargarPilotos(carrera.getParticipantes());
        modelo.getModeloRegistrarResultado().setFecha(fecha);
        modelo.getModeloRegistrarResultado().setCircuito(carrera.getCircuito());
        limpiarCamposRegistroResultado();
        vista.mostrarPanel("seleccionarPosiciones");
    }

    private void limpiarCamposRegistroResultado() {
        vista.getRegistroResultados().getFechaField().setText("");
    }

    // Registrar Pilotos
    /**
     * Procesa los resultados cargados en la tabla, valida posiciones duplicadas
     * y genera el registro de la carrera finalizada.
     */
    private void registrarPilotos() {
        ArrayList<Object[]> filas = vista.getSeleccionarPosiciones().obtenerDatosTabla();

        if (filas == null || filas.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No hay pilotos cargados en la tabla.");
            return;
        }

        // Validar posiciones duplicadas
        if (modelo.getModeloRegistrarResultado().verificarPosDuplicadas(filas)) {
            JOptionPane.showMessageDialog(
                    vista,
                    "Existe una posición repetida.\nVerifique que cada piloto tenga una posición distinta.",
                    "Error de validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Procesar pilotos
        LocalDate fecha = modelo.getModeloRegistrarResultado().getFecha();



        // Finalizar registro
        Carrera base = modelo.buscarCarrera(fecha);
        if (base == null) {
            JOptionPane.showMessageDialog(vista, "Error interno: carrera base no encontrada.");
            return;
        }

        modelo.buscarCarrera(fecha).agregarResultado(modelo.getModeloRegistrarResultado().procesarPilotos(filas));
        // Actualizar estado de la carrera
        modelo.buscarCarrera(fecha).setEstado(EstadoCarrera.FINALIZADA);
        // Limpiar datos y volver al menú
        modelo.getModeloRegistrarResultado().setFecha(null);
        modelo.getModeloRegistrarResultado().setCircuito(null);
        vista.getSeleccionarPosiciones().limpiarTabla();

        JOptionPane.showMessageDialog(vista, "Carrera registrada correctamente.");
        vista.mostrarPanel("menu");
    }
}

