package controlador;

import modelo.Modelo;
import vista.VentanaPrincipal;

public class ControladorPrincipal {

    private Modelo modelo;
    private final VentanaPrincipal vista;
    private ControladorRegistroGeneral controladorRegistroGeneral;
    private ControladorGestionEscuderia controladorGestionEscuderia;
    private ControladorPlanificarCarrera controladorPlanificarCarrera;
    private ControladorRegistrarResultado controladorRegistrarResultado;
    private ControladorInformeGenerales controladorInformeGenerales;

    public ControladorPrincipal(Modelo modelo,VentanaPrincipal vista) {
        this.vista = vista;
        this.modelo = modelo;
        inicializarEventos();
        controladorRegistroGeneral = new ControladorRegistroGeneral(modelo, vista);
        controladorGestionEscuderia = new ControladorGestionEscuderia(modelo, vista);
        controladorPlanificarCarrera = new ControladorPlanificarCarrera(modelo, vista);
        controladorRegistrarResultado = new ControladorRegistrarResultado(modelo, vista);
        controladorInformeGenerales = new ControladorInformeGenerales(modelo, vista);


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
