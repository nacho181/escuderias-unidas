package controlador;


import entidades.PilotoEscuderia;
import modelo.Modelo;
import vista.VentanaPrincipal;
import javax.swing.*;
import java.time.LocalDate;



/**
 * Controlador encargado de gestionar las acciones del módulo de gestión de escuderías.
 * Permite seleccionar una escudería existente, registrar pilotos, mecánicos y autos dentro de ella.
 * Incluye validaciones para evitar duplicados y verificar consistencia de fechas.
 */
public class ControladorGestionEscuderia {
    private final VentanaPrincipal vista;
    private final Modelo modelo;

    /**
     * Constructor del main.java.controlador.
     * @param modelo instancia del main.java.modelo principal.
     * @param vista  instancia de la ventana principal.
     */
    public ControladorGestionEscuderia(Modelo modelo, VentanaPrincipal vista) {
        this.vista = vista;
        this.modelo = modelo;
        inicializarEventos();
    }

    /**
     * Inicializa los eventos de todos los botones vinculados con la gestión de escuderías.
     */
    private void inicializarEventos() {
        // Selección de escudería
        vista.getGestionEscuderia().getEnviarButton().addActionListener(unused -> seleccionarEscuderia());
        vista.getGestionEscuderia().getVolverButton().addActionListener(unused -> {vista.mostrarPanel("menu");modelo.getModeloGestionEscuderias().setEscuderiaSeleccionada(null);
        });
        // Volver desde "Escudería Seleccionada"
        vista.getEscuderiaSeleccionada().getVolverButton().addActionListener(unused -> {vista.mostrarPanel("escuderiaGestion");modelo.getModeloGestionEscuderias().setEscuderiaSeleccionada(null);
            limpiarCampoEscSelecc();
        });
        // Registrar Piloto
        vista.getEscuderiaSeleccionada().getRegistrarPilotoButton().addActionListener(unused -> vista.mostrarPanel("escuderiaPiloto"));
        vista.getRegistrarPilotoEscuderia().getRegistrarButton().addActionListener(unused -> registrarPiloto());
        vista.getRegistrarPilotoEscuderia().getVolverButton().addActionListener(unused -> {vista.mostrarPanel("escuderiaSeleccionada");limpiarCampoPiloto();});

        // Registrar Mecánico
        vista.getEscuderiaSeleccionada().getRegistrarMecanicoButton().addActionListener(unused -> vista.mostrarPanel("escuderiaMecanico"));
        vista.getRegistrarMecanicoEscuderia().getRegistrarButton().addActionListener(unused -> registrarMecanico());
        vista.getRegistrarMecanicoEscuderia().getVolverButton().addActionListener(unused -> {vista.mostrarPanel("escuderiaSeleccionada");limpiarCampoMecanico();});

        // Registrar Auto
        vista.getEscuderiaSeleccionada().getRegistrarAutoButton().addActionListener(unused -> vista.mostrarPanel("escuderiaAuto"));
        vista.getRegistrarEscuderiaAuto().getRegistrarButton().addActionListener(unused -> registrarAuto());
        vista.getRegistrarEscuderiaAuto().getVolverButton().addActionListener(unused -> {vista.mostrarPanel("escuderiaSeleccionada");limpiarCampoAuto();});
    }


    /**
     * Permite seleccionar una escudería existente para gestionarla.
     * Si la escudería no existe, se notifica al usuario.
     */
    private void seleccionarEscuderia() {
        String nombreEscuderia = vista.getGestionEscuderia().getNombreField().getText();

        if (nombreEscuderia == null || nombreEscuderia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
            return;
        }

        if (modelo.buscarEscuderia(nombreEscuderia) != null) {
            modelo.getModeloGestionEscuderias().setEscuderiaSeleccionada(modelo.buscarEscuderia(nombreEscuderia));vista.mostrarPanel("escuderiaSeleccionada");
        } else {
            JOptionPane.showMessageDialog(null, "La escudería no existe");
        }
    }

    /**
     * Limpia el campo de texto utilizado para ingresar el nombre de la escudería.
     */
    private void limpiarCampoEscSelecc() {
        vista.getGestionEscuderia().getNombreField().setText("");
    }

    /**
     * Registra un piloto dentro de la escudería seleccionada, validando:
     * - Campos vacíos
     * - Fechas coherentes
     * - Que el piloto exista
     * - Que no esté contratado en el mismo rango de fechas
     * - Que no esté ya agregado en la escudería
     */
    private void registrarPiloto() {
        String dniPiloto = vista.getRegistrarPilotoEscuderia().getDniField().getText();
        String fechaInicioStr = vista.getRegistrarPilotoEscuderia().getAnioEntradaField().getText();
        LocalDate fechaInicio;
        String fechaFinalStr = vista.getRegistrarPilotoEscuderia().getAnioFinField().getText();
        LocalDate fechaFinal;

        if (dniPiloto == null || fechaInicioStr.isBlank() || fechaFinalStr.isBlank() || dniPiloto.isBlank()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
            return;
        }

        try{
            fechaInicio = LocalDate.parse(fechaInicioStr, Modelo.formatter);
            fechaFinal = LocalDate.parse(fechaFinalStr, Modelo.formatter);
        }catch (Exception unused){
            JOptionPane.showMessageDialog(
                    null,
                    "Alguna de las dos fechas están fuera de formato, recuerde de usar el formato (AAAAMMDD).",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }


        if (fechaInicio.isAfter(fechaFinal)) {
            JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser mayor o igual que la de finalización.");
            return;
        }
        if (fechaInicio.equals(fechaFinal)) {
            JOptionPane.showMessageDialog(null, "Debe ingresar dos fechas diferentes.");
            return;
        }
        if (modelo.buscarPiloto(dniPiloto) == null) {
            JOptionPane.showMessageDialog(null, "El piloto no está registrado en el sistema.");
            return;
        }

        // Verificar si ya está agregado a esta escudería
        for (PilotoEscuderia p : modelo.getModeloGestionEscuderias().getEscuderiaSeleccionada().getPilotos()) {
            if (p.getPiloto().getDni().equals(dniPiloto)) {
                JOptionPane.showMessageDialog(null, "El piloto ya pertenece a esta escudería.");
                return;
            }
        }

        // Verificar si está ocupado en otro contrato solapado
        for (PilotoEscuderia p : modelo.getRegistroGeneral().getPilotoEscuderias()) {
            if (p.getPiloto().getDni().equals(dniPiloto)) {
                LocalDate existenteInicio = p.getDesdeFecha();
                LocalDate existenteFin = p.getHastaFecha();

                boolean solapan =
                        (fechaInicio.isBefore(existenteInicio) && fechaFinal.isAfter(existenteFin))
                                || (fechaInicio.isBefore(existenteInicio) && fechaFinal.isAfter(existenteInicio) && fechaFinal.isBefore(existenteFin))
                                || (fechaInicio.isAfter(existenteInicio) && fechaFinal.isBefore(existenteFin))
                                || (fechaInicio.isAfter(existenteInicio) && fechaInicio.isBefore(existenteFin) && fechaFinal.isAfter(existenteFin));

                if (solapan) {
                    JOptionPane.showMessageDialog(null, "El piloto ya tiene un contrato activo en ese rango de fechas.");
                    return;
                }
            }
        }

        // Registrar piloto
        PilotoEscuderia nuevoPiloto = new PilotoEscuderia(modelo.getModeloGestionEscuderias().getEscuderiaSeleccionada(), fechaInicio,modelo.buscarPiloto(dniPiloto), fechaFinal);

        modelo.getRegistroGeneral().agregarPilotoEscuderias(nuevoPiloto);
        modelo.getModeloGestionEscuderias().getEscuderiaSeleccionada().agregarPilotoEscuderia(nuevoPiloto);
        JOptionPane.showMessageDialog(null, "Piloto agregado correctamente.");
        limpiarCampoPiloto();
    }

    private void limpiarCampoPiloto() {
        vista.getRegistrarPilotoEscuderia().getAnioEntradaField().setText("");
        vista.getRegistrarPilotoEscuderia().getAnioFinField().setText("");
        vista.getRegistrarPilotoEscuderia().getDniField().setText("");
    }

    /**
     * Registra un mecánico en la escudería actual, verificando:
     * - Que exista el mecánico en el sistema
     * - Que no esté duplicado dentro de la misma escudería
     */
    private void registrarMecanico() {
        String dni = vista.getRegistrarMecanicoEscuderia().getDniField().getText();

        if (dni == null || dni.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
            return;
        }

        if (modelo.buscarMecanico(dni) == null) {
            JOptionPane.showMessageDialog(null, "El mecánico no existe en el sistema.");
            return;
        }

        // Verificar duplicado
        for (var m : modelo.getModeloGestionEscuderias().getEscuderiaSeleccionada().getMecanicos()) {
            if (m.getDni().equals(dni)) {
                JOptionPane.showMessageDialog(null, "El mecánico ya pertenece a esta escudería.");
                return;
            }
        }

        // Registrar
        modelo.getModeloGestionEscuderias().getEscuderiaSeleccionada().agregarMecanico(modelo.buscarMecanico(dni));
        JOptionPane.showMessageDialog(null, "Mecánico agregado correctamente.");
        limpiarCampoMecanico();
    }

    private void limpiarCampoMecanico() {
        vista.getRegistrarMecanicoEscuderia().getDniField().setText("");
    }

    /**
     * Registra un auto en la escudería actual, validando:
     * - Que exista el main.java.modelo de auto
     * - Que no esté duplicado dentro de la escudería
     */
    private void registrarAuto() {
        String modeloAuto = vista.getRegistrarEscuderiaAuto().getModeloField().getText();

        if (modeloAuto == null || modeloAuto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
            return;
        }

        if (modelo.buscarAuto(modeloAuto) == null) {
            JOptionPane.showMessageDialog(null, "El auto no existe en el sistema.");
            return;
        }

        if(modelo.buscarAuto(modeloAuto).getAsignado()) {
            JOptionPane.showMessageDialog(null, "El auto ya esta asignado a una escudería.");
            return;
        }


        // Registrar
        modelo.getModeloGestionEscuderias().getEscuderiaSeleccionada().agregarAuto(modelo.buscarAuto(modeloAuto));
        JOptionPane.showMessageDialog(null, "Auto agregado correctamente.");
        modelo.buscarAuto(modeloAuto).setAsignado(true);
        limpiarCampoAuto();
    }

    private void limpiarCampoAuto() {
        vista.getRegistrarEscuderiaAuto().getModeloField().setText("");
    }
}
