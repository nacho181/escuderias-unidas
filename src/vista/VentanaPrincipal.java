package vista;

import vista.gestionEscuderia.*;
import vista.informes.*;
import vista.planificarCarrera.CircuitoSeleccionado;
import vista.planificarCarrera.RegistrarCircuito;
import vista.planificarCarrera.RegistrarAutoPilotos;
import vista.planificarCarrera.SeleccionarEscuderia;
import vista.registroGeneral.*;
import vista.registroResultados.RegistroResultados;
import vista.registroResultados.SeleccionarPosiciones;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Clase principal de la aplicación de gestión de carreras.
 * Controla la navegación entre las diferentes vistas mediante un CardLayout.
 * Extiende JFrame para representar la ventana principal del sistema.
 */
public class VentanaPrincipal extends JFrame {

    /** Layout que permite alternar entre los distintos paneles del sistema. */
    private CardLayout cardLayout;

    /** Contenedor principal que alberga los paneles asociados al CardLayout. */
    private JPanel contenedor;

    /** Panel principal del menú de inicio. */
    private Principal panelMenu;

    // Opción 1: Registro general

    private RegistroGeneral registroGeneral;
    private RegistroPaises registroPaises;
    private RegistroAuto registroAuto;
    private RegistrarEscuderia registrarEscuderia;
    private RegistroCircuito registroCircuito;
    private RegistroMecanico registroMecanico;
    private RegistroPiloto registroPiloto;

    // Opción 2: Gestión de escuderías

    private GestionEscuderia gestionEscuderia;
    private EscuderiaSeleccionada escuderiaSeleccionada;
    private RegistrarEscuderiaAuto registrarEscuderiaAuto;
    private RegistrarMecanicoEscuderia registrarMecanicoEscuderia;
    private RegistrarPilotoEscuderia registrarPilotoEscuderia;

    // Opción 3: Planificación de carreras

    private RegistrarCircuito planificarCarrera;
    private CircuitoSeleccionado circuitoSeleccionado;
    private RegistrarAutoPilotos registrarPilotos;
    private SeleccionarEscuderia seleccionarEscuderia;

    // Opción 4: Registro de resultados

    private RegistroResultados registroResultados;
    private SeleccionarPosiciones seleccionarPosiciones;

    // Opción 5: Informes

    private SeleccionInformes seleccionInformes;
    private OpcionUno opcionUno;
    private OpcionDos opcionDos;
    private OpcionTres opcionTres;
    private OpcionCuatro opcionCuatro;
    private OpcionCinco opcionCinco;
    private OpcionSeis opcionSeis;
    private OpcionSiete opcionSiete;

    /**
     * Constructor de la clase VentanaPrincipal.
     * Inicializa la ventana, configura el layout principal y registra los paneles
     * correspondientes a cada módulo de la aplicación.
     */
    public VentanaPrincipal() {
        setTitle("Gestión de Carreras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centra la ventana
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("formula1.png")));
        setIconImage(icono.getImage());

        // Inicialización del layout principal que permite alternar vistas
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        // Panel principal del menú
        panelMenu = new Principal(this);

        // Inicialización de paneles de la opción 1
        registroGeneral = new RegistroGeneral(this);
        registroPaises = new RegistroPaises(this);
        registroAuto = new RegistroAuto(this);
        registrarEscuderia = new RegistrarEscuderia(this);
        registroCircuito = new RegistroCircuito(this);
        registroMecanico = new RegistroMecanico(this);
        registroPiloto = new RegistroPiloto(this);

        // Inicialización de paneles de la opción 2
        gestionEscuderia = new GestionEscuderia(this);
        escuderiaSeleccionada = new EscuderiaSeleccionada(this);
        registrarEscuderiaAuto = new RegistrarEscuderiaAuto(this);
        registrarMecanicoEscuderia = new RegistrarMecanicoEscuderia(this);
        registrarPilotoEscuderia = new RegistrarPilotoEscuderia(this);

        // Inicialización de paneles de la opción 3
        planificarCarrera = new RegistrarCircuito(this);
        circuitoSeleccionado = new CircuitoSeleccionado(this);
        registrarPilotos = new RegistrarAutoPilotos(this);
        seleccionarEscuderia = new SeleccionarEscuderia(this);

        // Inicialización de paneles de la opción 4
        registroResultados = new RegistroResultados(this);
        seleccionarPosiciones = new SeleccionarPosiciones(this);

        // Inicialización de paneles de la opción 5
        seleccionInformes = new SeleccionInformes(this);
        opcionUno = new OpcionUno(this);
        opcionDos = new OpcionDos(this);
        opcionTres = new OpcionTres(this);
        opcionCuatro = new OpcionCuatro(this);
        opcionCinco = new OpcionCinco(this);
        opcionSeis = new OpcionSeis(this);
        opcionSiete = new OpcionSiete(this);

        // Registro de los paneles en el contenedor

        // Opción 1: Registro general
        contenedor.add(panelMenu, "menu");
        contenedor.add(registroGeneral, "registro");
        contenedor.add(registroPaises, "paises");
        contenedor.add(registroAuto, "autos");
        contenedor.add(registrarEscuderia, "escuderia");
        contenedor.add(registroCircuito, "circuito");
        contenedor.add(registroMecanico, "mecanico");
        contenedor.add(registroPiloto, "piloto");

        // Opción 2: Gestión de escudería
        contenedor.add(gestionEscuderia, "escuderiaGestion");
        contenedor.add(escuderiaSeleccionada, "escuderiaSeleccionada");
        contenedor.add(registrarPilotoEscuderia, "escuderiaPiloto");
        contenedor.add(registrarMecanicoEscuderia, "escuderiaMecanico");
        contenedor.add(registrarEscuderiaAuto, "escuderiaAuto");

        // Opción 3: Planificación de carrera
        contenedor.add(planificarCarrera, "planificarCarrera");
        contenedor.add(circuitoSeleccionado, "circuitoSeleccionado");
        contenedor.add(seleccionarEscuderia, "seleccionarEscuderia");
        contenedor.add(registrarPilotos, "registrarPilotos");

        // Opción 4: Registro de resultados
        contenedor.add(registroResultados, "registroResultados");
        contenedor.add(seleccionarPosiciones, "seleccionarPosiciones");

        // Opción 5: Informes
        contenedor.add(seleccionInformes, "seleccionInformes");
        contenedor.add(opcionUno, "opcionUno");
        contenedor.add(opcionDos, "opcionDos");
        contenedor.add(opcionTres, "opcionTres");
        contenedor.add(opcionCuatro, "opcionCuatro");
        contenedor.add(opcionCinco, "opcionCinco");
        contenedor.add(opcionSeis, "opcionSeis");
        contenedor.add(opcionSiete, "opcionSiete");

        // Se agrega el contenedor principal a la ventana
        add(contenedor);

        // Se muestra la ventana al usuario
        setVisible(true);
    }

    // Métodos de acceso (getters)

    public SeleccionInformes getSeleccionInformes() { return seleccionInformes; }

    public RegistrarCircuito getPlanificarCarrera() { return planificarCarrera; }

    public CircuitoSeleccionado getCircuitoSeleccionado() { return circuitoSeleccionado; }

    public RegistrarAutoPilotos getRegistrarAutoPilotos() { return registrarPilotos; }

    public SeleccionarEscuderia getSeleccionarEscuderia() { return seleccionarEscuderia; }

    public RegistroResultados getRegistroResultados() { return registroResultados; }

    public SeleccionarPosiciones getSeleccionarPosiciones() { return seleccionarPosiciones; }

    public OpcionUno getOpcionUno() { return opcionUno; }

    public OpcionDos getOpcionDos() { return opcionDos; }

    public OpcionTres getOpcionTres() { return opcionTres; }

    public OpcionCuatro getOpcionCuatro() { return opcionCuatro; }

    public OpcionCinco getOpcionCinco() { return opcionCinco; }

    public OpcionSeis getOpcionSeis() { return opcionSeis; }

    public OpcionSiete getOpcionSiete() { return opcionSiete; }

    public RegistroGeneral getRegistroGeneral() { return registroGeneral; }

    public RegistroPaises getRegistroPaises() { return registroPaises; }

    public Principal getPanelMenu() { return panelMenu; }

    public RegistroAuto getRegistroAuto() { return registroAuto; }

    public RegistrarEscuderia getRegistrarEscuderia() { return registrarEscuderia; }

    public RegistroCircuito getRegistroCircuito() { return registroCircuito; }

    public RegistroMecanico getRegistroMecanico() { return registroMecanico; }

    public RegistroPiloto getRegistroPiloto() { return registroPiloto; }

    public GestionEscuderia getGestionEscuderia() { return gestionEscuderia; }

    public EscuderiaSeleccionada getEscuderiaSeleccionada() { return escuderiaSeleccionada; }

    public RegistrarEscuderiaAuto getRegistrarEscuderiaAuto() { return registrarEscuderiaAuto; }

    public RegistrarMecanicoEscuderia getRegistrarMecanicoEscuderia() { return registrarMecanicoEscuderia; }

    public RegistrarPilotoEscuderia getRegistrarPilotoEscuderia() { return registrarPilotoEscuderia; }

    /**
     * Cambia la vista activa dentro del contenedor principal.
     * @param nombre identificador del panel que se desea mostrar.
     */
    public void mostrarPanel(String nombre) {
        cardLayout.show(contenedor, nombre);
    }

    /**
     * Metodo principal de ejecución.
     * Inicializa la interfaz gráfica en el hilo de eventos de Swing.
     * @param args argumentos de línea de comandos (no utilizados).
     */
}
