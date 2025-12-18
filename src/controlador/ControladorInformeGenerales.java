package controlador;

import entidades.*;
import modelo.Modelo;
import vista.VentanaPrincipal;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static controlador.ControladorPrincipal.formatter;

/**
 * Controlador encargado de manejar la generación de los distintos informes del sistema.
 * Gestiona las acciones de los botones, la validación de entradas y la carga de tablas
 * con la información obtenida del modelo.
 */
public class ControladorInformeGenerales {

    private final Modelo modelo;
    private final VentanaPrincipal vista;

    // Contructor
    public ControladorInformeGenerales(Modelo modelo, VentanaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        inicializarEventos();
    }

    // Eventos principales
    /**
     * Inicializa los listeners de todos los botones de selección y las opciones de informe.
     */
    private void inicializarEventos() {
        // Botones del panel principal de selección
        vista.getSeleccionInformes().getOpcion1Button().addActionListener(e -> vista.mostrarPanel("opcionUno"));
        vista.getSeleccionInformes().getOpcion2Button().addActionListener(e -> vista.mostrarPanel("opcionDos"));
        vista.getSeleccionInformes().getOpcion3Button().addActionListener(e -> vista.mostrarPanel("opcionTres"));
        vista.getSeleccionInformes().getOpcion4Button().addActionListener(e -> vista.mostrarPanel("opcionCuatro"));
        vista.getSeleccionInformes().getOpcion5Button().addActionListener(e -> vista.mostrarPanel("opcionCinco"));
        vista.getSeleccionInformes().getOpcion6Button().addActionListener(e -> vista.mostrarPanel("opcionSeis"));
        vista.getSeleccionInformes().getOpcion7Button().addActionListener(e -> vista.mostrarPanel("opcionSiete"));
        vista.getSeleccionInformes().getVolverButton().addActionListener(e -> vista.mostrarPanel("menu"));

        // Eventos de cada informe individual
        vista.getOpcionUno().getEnviarButton().addActionListener(e -> opcion1());
        vista.getOpcionUno().getVolverButton().addActionListener(e -> {limpiarCamposOpcion1();vista.mostrarPanel("seleccionInformes");});

        vista.getOpcionDos().getConsultarButton().addActionListener(e -> opcion2());
        vista.getOpcionDos().getVolverButton().addActionListener(e -> {limpiarCamposOpcion2();vista.mostrarPanel("seleccionInformes");});

        vista.getOpcionTres().getEnviarButton().addActionListener(e -> opcion3());
        vista.getOpcionTres().getVolverButton().addActionListener(e -> {limpiarCamposOpcion3();vista.mostrarPanel("seleccionInformes");});

        vista.getOpcionCuatro().getEnviarButton().addActionListener(e -> opcion4());
        vista.getOpcionCuatro().getVolverButton().addActionListener(e -> {limpiarCamposOpcion4();vista.mostrarPanel("seleccionInformes");});

        vista.getOpcionCinco().getConsultarButton().addActionListener(e -> opcion5());
        vista.getOpcionCinco().getVolverButton().addActionListener(e -> {limpiarCamposOpcion5();vista.mostrarPanel("seleccionInformes");});

        vista.getOpcionSeis().getConsultarButton().addActionListener(e -> opcion6());
        vista.getOpcionSeis().getVolverButton().addActionListener(e -> {limpiarCamposOpcion6();vista.mostrarPanel("seleccionInformes");});

        vista.getOpcionSiete().getConsultarButton().addActionListener(e -> opcion7());
        vista.getOpcionSiete().getVolverButton().addActionListener(e -> {limpiarCamposOpcion7();vista.mostrarPanel("seleccionInformes");});
    }


    /**
     * Genera un informe de carreras dentro de un rango de fechas.
     */
    private void opcion1() {
        try {
            String inicioStr = vista.getOpcionUno().getPrimerFechaField().getText();
            String finStr = vista.getOpcionUno().getSegundaFechaField().getText();
            LocalDate inicio = LocalDate.parse(inicioStr, formatter);
            LocalDate fin = LocalDate.parse(finStr, formatter);

            if (inicioStr.isEmpty() || finStr.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar ambas fechas.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (inicio.isAfter(fin)) {
                JOptionPane.showMessageDialog(vista, "La segunda fecha debe ser posterior a la primera.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ArrayList<RegistroCarrera> carreras = modelo.obtenerCarrerasEntreFechas(inicio, fin);
            var tabla = vista.getOpcionUno().getModeloTabla();
            tabla.setRowCount(0);

            for (RegistroCarrera registro : carreras) {
                Carrera carrera = registro.getCarrera();
                ArrayList<AutoPiloto> resultados = registro.getPosiciones();

                for (int i = 0; i < resultados.size(); i++) {
                    AutoPiloto ap = resultados.get(i);
                    if (ap != null) {
                        tabla.addRow(new Object[]{
                                carrera.getFechaRealizacion(),
                                carrera.getCircuito().getNombre(),
                                carrera.getNumeroVueltas(),
                                carrera.getHoraRealizacion(),
                                ap.getPiloto().getApellido(),
                                i + 1
                        });
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al generar informe: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCamposOpcion1() {
        vista.getOpcionUno().getPrimerFechaField().setText("");
        vista.getOpcionUno().getSegundaFechaField().setText("");
        vista.getOpcionUno().getModeloTabla().setRowCount(0);
    }

    /**
     * Muestra el ranking de pilotos ordenado por puntos acumulados.
     */
    private void opcion2() {
        try {
            var tabla = vista.getOpcionDos().getModeloTabla();
            tabla.setRowCount(0);

            ArrayList<Piloto> pilotos = modelo.getRegistroGeneral().getPiloto();
            if (pilotos.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "No hay pilotos registrados.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Ordenar de mayor a menor por puntos
            for (int i = 0; i < pilotos.size() - 1; i++) {
                for (int j = i + 1; j < pilotos.size(); j++) {
                    if (pilotos.get(i).getPuntosAcumulados() < pilotos.get(j).getPuntosAcumulados()) {
                        Piloto temp = pilotos.get(i);
                        pilotos.set(i, pilotos.get(j));
                        pilotos.set(j, temp);
                    }
                }
            }

            int pos = 1;
            for (Piloto p : pilotos) {
                tabla.addRow(new Object[]{pos++, p.getNombre(), p.getApellido(), p.getDni(), p.getPuntosAcumulados()});
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al generar informe: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCamposOpcion2() {
        vista.getOpcionDos().getModeloTabla().setRowCount(0);
    }

    /**
     * Muestra todas las carreras en las que un piloto logró un podio.
     */
    private void opcion3() {
        String dni = vista.getOpcionTres().getDniField().getText();
        ArrayList<RegistroCarrera> carreras = new ArrayList<>();

        for (RegistroCarrera registro : modelo.getCarrerasResultados()) {
            if (registro == null) continue;
            ArrayList<AutoPiloto> posiciones = registro.getPosiciones();
            if (posiciones == null || posiciones.size() < 3) {
                continue;
            }
            for (int i = 0; i < 3; i++) {
                AutoPiloto ap = posiciones.get(i);
                if (ap == null || ap.getPiloto() == null) {
                    continue;
                }
                Piloto pilotoBuscado = modelo.buscarPiloto(dni);
                if (ap.getPiloto().equals(pilotoBuscado)) {
                    carreras.add(registro);
                    break;
                }
            }
        }


        var tabla = vista.getOpcionTres().getModeloTabla();
        tabla.setRowCount(0);

        if (carreras.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Este piloto no tiene podios registrados.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (RegistroCarrera reg : carreras) {
            tabla.addRow(new Object[]{
                    (reg.consultarPodioPiloto(dni) + 1),
                    reg.conultarPiloto(dni).getNombre(),
                    reg.conultarPiloto(dni).getApellido(),
                    reg.getFecha(),
                    reg.getCarrera().getCircuito().getNombre()
            });
        }
    }

    private void limpiarCamposOpcion3() {
        vista.getOpcionTres().getDniField().setText("");
        vista.getOpcionTres().getModeloTabla().setRowCount(0);
    }


    /**
     * Muestra las carreras donde participaron autos de una escudería determinada.
     */
    private void opcion4() {
        String nombreEscuderia = vista.getOpcionCuatro().getNombreField().getText().trim();
        var tabla = vista.getOpcionCuatro().getModeloTabla();
        tabla.setRowCount(0);

        if (nombreEscuderia.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar el nombre de la escudería.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Buscar escudería
        Escuderia escuderia = null;
        for (Escuderia e : modelo.getRegistroGeneral().getEscuderias()) {
            if (e.getNombre().equalsIgnoreCase(nombreEscuderia)) {
                escuderia = e;
                break;
            }
        }

        if (escuderia == null) {
            JOptionPane.showMessageDialog(vista, "No se encontró la escudería ingresada.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ArrayList<Auto> autosEscuderia = escuderia.getAutos();
        if (autosEscuderia.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "La escudería no tiene autos registrados.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Buscar carreras donde participaron esos autos
        ArrayList<RegistroCarrera> carrerasEncontradas = new ArrayList<>();

        for (RegistroCarrera reg : modelo.getCarrerasResultados()) {
            for (AutoPiloto ap : reg.getPosiciones()) {
                // Validación para evitar NullPointerException
                if (ap == null || ap.getAuto() == null) {
                    continue; // salta a la siguiente posición
                }

                // Si pasa la validación, sigue la lógica normal
                if (autosEscuderia.contains(ap.getAuto()) && !carrerasEncontradas.contains(reg)) {
                    carrerasEncontradas.add(reg);
                }
            }
        }


        // Cargar tabla
        int pos = 1;
        for (RegistroCarrera reg : carrerasEncontradas) {
            if (reg == null || reg.getCarrera() == null) {
                continue;
            }
            Carrera c = reg.getCarrera();
            for (AutoPiloto ap : reg.getPosiciones()) {
                // Evitar NPE si ap es null o si no tiene auto
                if (ap == null || ap.getAuto() == null) {
                    continue;
                }
                Auto auto = ap.getAuto();
                if (autosEscuderia.contains(auto)) {
                    tabla.addRow(new Object[]{
                            pos++,
                            auto.getModelo(),
                            auto.getMotor(),
                            c.getFechaRealizacion(),
                            c.getCircuito().getNombre()
                    });
                }
            }
        }


        if (tabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(vista, "No se encontraron carreras para los autos de esta escudería.");
        }
    }

    private void limpiarCamposOpcion4() {
        vista.getOpcionCuatro().getNombreField().setText("");
        vista.getOpcionCuatro().getModeloTabla().setRowCount(0);
    }


    /**
     * Muestra los mecánicos de una escudería.
     */
    private void opcion5() {
        try {
            var tabla = vista.getOpcionCinco().getModeloTabla();
            tabla.setRowCount(0);

            String nombreEscuderia = vista.getOpcionCinco().getNombreField().getText().trim();
            ArrayList<Mecanico> mecanicos = new ArrayList<>();

            for (Escuderia e : modelo.getRegistroGeneral().getEscuderias()) {
                if (e.getNombre().equalsIgnoreCase(nombreEscuderia)) {
                    mecanicos.addAll(e.getMecanicos());
                }
            }

            if (mecanicos.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "No se encontraron mecánicos para esta escudería.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (Mecanico m : mecanicos) {
                tabla.addRow(new Object[]{
                        m.getDni(),
                        m.getNombre(),
                        m.getApellido(),
                        m.getEspecialidad(),
                        m.getAniosExperiencia()
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al generar informe: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCamposOpcion5() {
        vista.getOpcionCinco().getModeloTabla().setRowCount(0);
        vista.getOpcionCinco().getNombreField().setText("");
    }


    /**
     * Cuenta cuántas veces un piloto compitió en un circuito específico.
     */
    private void opcion6() {
        String dni = vista.getOpcionSeis().getDniField().getText().trim();
        String circuito = vista.getOpcionSeis().getNombreCircField().getText().trim();

        int contador = 0;

        for (RegistroCarrera reg : modelo.getCarrerasResultados()) {
            if (reg.getCarrera().getCircuito().getNombre().equalsIgnoreCase(circuito)) {
                for (AutoPiloto ap : reg.getPosiciones()) {
                    // Validaciones para evitar NullPointerException
                    if (ap == null || ap.getPiloto() == null) {
                        continue; // pasa a la siguiente posición
                    }

                    if (ap.getPiloto().getDni().equalsIgnoreCase(dni)) {
                        contador++;
                    }
                }
            }
        }


        if (contador == 0) {
            JOptionPane.showMessageDialog(vista, "Este piloto no ha corrido en ese circuito.");
        } else {
            JOptionPane.showMessageDialog(vista, "El piloto ha corrido un total de " + contador + " veces en ese circuito.");
        }

        limpiarCamposOpcion6();
    }

    private void limpiarCamposOpcion6() {
        vista.getOpcionSeis().getDniField().setText("");
        vista.getOpcionSeis().getNombreCircField().setText("");
    }


    /**
     * Informa cuántas carreras se disputaron en un circuito específico.
     */
    private void opcion7() {
        String circuito = vista.getOpcionSiete().getNombreCircPanel().getText().trim();
        int contador = 0;

        for (RegistroCarrera reg : modelo.getCarrerasResultados()) {
            if (reg.getCarrera().getCircuito().getNombre().equalsIgnoreCase(circuito)) {
                contador++;
            }
        }

        if (contador == 0) {
            JOptionPane.showMessageDialog(vista, "No se ha disputado ninguna carrera en ese circuito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Se han disputado un total de " + contador + " carreras en ese circuito.");
        }

        limpiarCamposOpcion7();
    }

    private void limpiarCamposOpcion7() {
        vista.getOpcionSiete().getNombreCircPanel().setText("");
    }
}
