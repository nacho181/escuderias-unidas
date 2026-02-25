package mvc.controlador;

import mvc.modelo.Modelo;
import mvc.vista.VentanaPrincipal;
import service.ServiceManager;

public class ControladorPrincipal {

    private final VentanaPrincipal vista;
    private final ServiceManager services;

    public ControladorPrincipal(Modelo modelo,VentanaPrincipal vista) {
        this.vista = vista;
        inicializarEventos();
        this.services = new ServiceManager();
        new ControladorRegistroGeneral(modelo, vista, services);
        new ControladorGestionEscuderia(modelo, vista, services);
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
