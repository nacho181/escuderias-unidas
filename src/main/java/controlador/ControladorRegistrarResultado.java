package controlador;

import entidades.*;
import modelo.Modelo;
import vista.VentanaPrincipal;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


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
        vista.getRegistroResultados().getVolverButton().addActionListener(e -> {
            limpiarCamposRegistroResultado();
            modelo.getModeloRegistrarResultado().setFecha(null);
            vista.mostrarPanel("menu");});

        vista.getSeleccionarPosiciones().getRegistrarButton().addActionListener(e -> registrarPilotos());
        vista.getSeleccionarPosiciones().getVolverButton().addActionListener(e -> {
            vista.getSeleccionarPosiciones().limpiarTabla();
            modelo.getModeloRegistrarResultado().setFecha(null);
            vista.mostrarPanel("registroResultados");});
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
        for (int i = 0; i < filas.size(); i++) {
            Object valor1 = filas.get(i)[3];
            if (!(valor1 instanceof Integer)) continue;
            int pos1 = (Integer) valor1;

            for (int j = i + 1; j < filas.size(); j++) {
                Object valor2 = filas.get(j)[3];
                if (!(valor2 instanceof Integer)) continue;
                int pos2 = (Integer) valor2;

                if (pos1 == pos2) {
                    JOptionPane.showMessageDialog(
                            vista,
                            "La posición " + pos1 + " está repetida.\nVerifique que cada piloto tenga una posición distinta.",
                            "Error de validación",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
            }
        }

        // Procesar pilotos
        LocalDate fecha = modelo.getModeloRegistrarResultado().getFecha();
        ArrayList<ResultadoCarrera> resultados = new ArrayList<>(Collections.nCopies(20, null));

        for (Object[] fila : filas) {
            String dni = (String) fila[0];
            String autoModelo = (String) fila[2];
            Object valorPosicion = fila[3];

            // Determinar posición y puntos
            Posicion posicion = null;
            int puntos = 0;

            if (valorPosicion instanceof Integer) {
                int posNum = (Integer) valorPosicion;
                if (posNum >= 1 && posNum <= Posicion.values().length) {
                    posicion = Posicion.values()[posNum - 1];
                    puntos = posicion.getPuntos();
                }
            } else if (valorPosicion instanceof Posicion) {
                posicion = (Posicion) valorPosicion;
                puntos = posicion.getPuntos();
            }

            // Si no se puede determinar posición, saltar la fila
            if (posicion == null) continue;

            // Buscar main.java.entidades
            Piloto piloto = modelo.buscarPiloto(dni);
            Auto auto = modelo.buscarAuto(autoModelo);

            if (piloto == null) {
                JOptionPane.showMessageDialog(vista, "No se encontró el piloto con DNI: " + dni);
                return;
            }

            // Crear relación Auto-Piloto
            int posicionIndex = posicion.ordinal();
            resultados.add(new ResultadoCarrera(posicionIndex, new AutoPiloto(fecha, piloto, auto), puntos));

            // Actualizar estadísticas del piloto
            modelo.getModeloRegistrarResultado().agregarPuntaje(dni, puntos);

            int pos = posicion.ordinal() + 1;
            if (pos == 1) {
                modelo.buscarPiloto(dni).setVictorias(piloto.getVictorias() + 1);
                modelo.buscarPiloto(dni).setPodios(piloto.getPodios() + 1);
            } else if (pos == 2 || pos == 3) {
                modelo.buscarPiloto(dni).setPodios(piloto.getPodios() + 1);
            }
        }

        // Finalizar registro
        Carrera base = modelo.buscarCarrera(fecha);
        if (base == null) {
            JOptionPane.showMessageDialog(vista, "Error interno: carrera base no encontrada.");
            return;
        }

        modelo.buscarCarrera(fecha).agregarResultado(resultados);
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
