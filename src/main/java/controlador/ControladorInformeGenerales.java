package controlador;

import entidades.*;
import modelo.Modelo;
import vista.VentanaPrincipal;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Controlador encargado de manejar la generación de los distintos informes del sistema.
 * Gestiona las acciones de los botones, la validación de entradas y la carga de tablas
 * con la información obtenida del main.java.modelo.
 */
public class ControladorInformeGenerales {
    private final Modelo modelo;
    private final VentanaPrincipal vista;

    // Constructor
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

            String inicioStr = vista.getOpcionUno().getPrimerFechaField().getText();
            String finStr = vista.getOpcionUno().getSegundaFechaField().getText();
            LocalDate inicio;
            LocalDate fin;


            if (inicioStr.isEmpty() || finStr.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar ambas fechas.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try{
                inicio = LocalDate.parse(inicioStr, Modelo.formatter);
                fin = LocalDate.parse(finStr, Modelo.formatter);
            }catch (Exception unused){
                JOptionPane.showMessageDialog(
                        null,                      // o null
                        "Alguna de la dos fechas estan fuera de formato, recuerde de usar el formato (AAAAMMDD).",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (inicio.isAfter(fin)) {
            JOptionPane.showMessageDialog(vista, "La segunda fecha debe ser posterior a la primera.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
            }

            ArrayList<Carrera> carreras = modelo.getModeloInformeGenerales().obtenerCarrerasEntreFechas(inicio, fin);
            var tabla = vista.getOpcionUno().getModeloTabla();
            tabla.setRowCount(0);

            for (Carrera carrera : carreras) {
                List<ResultadoCarrera> resultados = carrera.getResultados();

                for (int i = 0; i < resultados.size(); i++) {
                    ResultadoCarrera ap = resultados.get(i);
                    if (ap != null) {
                        tabla.addRow(new Object[]{
                                carrera.getFechaRealizacion(),
                                carrera.getCircuito().getNombre(),
                                carrera.getNumeroVueltas(),
                                carrera.getHoraRealizacion(),
                                ap.getAutoPiloto().getPiloto().getApellido(),
                                i + 1
                        });
                    }
                }
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

            ArrayList<Piloto> pilotos = new ArrayList<>();
            for(Persona persona : modelo.getRegistroGeneral().getPersonas()){
                if(persona instanceof Piloto p){
                    pilotos.add(p);
                }
            }

            if (pilotos.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "No hay pilotos registrados.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Ordenar de mayor a menor por puntos
            Collections.sort(pilotos);

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





        var tabla = vista.getOpcionTres().getModeloTabla();
        tabla.setRowCount(0);

        if (modelo.getModeloInformeGenerales().obtenerPodio(dni).isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Este piloto no tiene podios registrados.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Carrera reg : modelo.getModeloInformeGenerales().obtenerPodio(dni)) {
            tabla.addRow(new Object[]{
                    (reg.consultarPodioPiloto(dni) + 1),
                    reg.consultarPiloto(dni).getNombre(),
                    reg.consultarPiloto(dni).getApellido(),
                    reg.getFechaRealizacion(),
                    reg.getCircuito().getNombre()
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
        Escuderia escuderia = modelo.getModeloRegistro().comprobarEscuderia(nombreEscuderia);

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
        ArrayList<Carrera> carrerasEncontradas = modelo.getModeloInformeGenerales().obtenerCarreraAuto(autosEscuderia);


        // Cargar tabla
        int pos = 1;
        for (Carrera reg : carrerasEncontradas) {
            if (reg == null) {
                continue;
            }
            for (ResultadoCarrera ap : reg.getResultados()) {
                // Evitar NPE si ap es null o si no tiene auto
                if (ap == null || ap.getAutoPiloto().getAuto() == null) {
                    continue;
                }
                Auto auto = ap.getAutoPiloto().getAuto();
                if (autosEscuderia.contains(auto)) {
                    tabla.addRow(new Object[]{
                            pos++,
                            auto.getModelo(),
                            auto.getMotor(),
                            reg.getFechaRealizacion(),
                            reg.getCircuito().getNombre()
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

            if (modelo.getModeloRegistro().comprobarEscuderia(nombreEscuderia) != null) {
                mecanicos.addAll(modelo.getModeloRegistro().comprobarEscuderia(nombreEscuderia).getMecanicos());
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




        if (modelo.getModeloInformeGenerales().contadorPilCirc(dni,circuito) == 0) {
            JOptionPane.showMessageDialog(vista, "Este piloto no ha corrido en ese circuito.");
        } else {
            JOptionPane.showMessageDialog(vista, "El piloto ha corrido un total de " + modelo.getModeloInformeGenerales().contadorPilCirc(dni,circuito) + " veces en ese circuito.");
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



        if (modelo.getModeloInformeGenerales().contadorCarrCirc(circuito) == 0) {
            JOptionPane.showMessageDialog(vista, "No se ha disputado ninguna carrera en ese circuito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Se han disputado un total de " + modelo.getModeloInformeGenerales().contadorCarrCirc(circuito) + " carreras en ese circuito.");
        }

        limpiarCamposOpcion7();
    }

    private void limpiarCamposOpcion7() {
        vista.getOpcionSiete().getNombreCircPanel().setText("");
    }
}
