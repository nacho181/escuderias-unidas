package controlador;

import modelo.Modelo;
import modelo.ModeloPlanificarCarrera;
import vista.VentanaPrincipal;

public class ControladorPrincipal {

    private final VentanaPrincipal vista;

    public ControladorPrincipal(Modelo modelo,VentanaPrincipal vista) {
        this.vista = vista;
        inicializarEventos();
        new ControladorRegistroGeneral(modelo, vista);
        new ControladorGestionEscuderia(modelo, vista);
        new ControladorPlanificarCarrera(modelo, vista);
        new ControladorRegistrarResultado(modelo, vista);
        new ControladorInformeGenerales(modelo, vista);
    }

    private void inicializarEventos() {
        vista.getPanelMenu().getRegistroGeneralButton().addActionListener(e -> vista.mostrarPanel("registro"));
        vista.getRegistroGeneral().getVolverButton().addActionListener(e -> vista.mostrarPanel("menu"));
        vista.getPanelMenu().getGestionarEscuderiaButton().addActionListener(e -> vista.mostrarPanel("escuderiaGestion"));
        vista.getGestionEscuderia().getVolverButton().addActionListener(e -> vista.mostrarPanel("menu"));
        vista.getPanelMenu().getPlanificarCarreraButton().addActionListener(e -> vista.mostrarPanel("planificarCarrera"));
        vista.getPlanificarCarrera().getVolverButton().addActionListener(e -> vista.mostrarPanel("menu"));
        vista.getPanelMenu().getRegistrarResultadoButton().addActionListener(e -> vista.mostrarPanel("registroResultados"));
        vista.getRegistroResultados().getVolverButton().addActionListener(e -> vista.mostrarPanel("menu"));
        vista.getPanelMenu().getInformeGeneralesButton().addActionListener(e -> vista.mostrarPanel("seleccionInformes"));
        vista.getSeleccionInformes().getVolverButton().addActionListener(e -> vista.mostrarPanel("menu"));
    }
    //


}
