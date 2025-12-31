import controlador.ControladorPrincipal;
import modelo.Modelo;
import vista.VentanaPrincipal;

import javax.swing.*;

/**
 * Clase principal del sistema de gestión de carreras.
 * <p>
 * Esta clase actúa como punto de entrada de la aplicación.
 * Su función principal es inicializar los componentes fundamentales del programa:
 * el modelo, la vista principal y el controlador.
 * </p>
 *
 * <p>
 * A través de esta clase se establece la comunicación inicial entre las capas
 * Modelo-Vista-Controlador (MVC), garantizando que la interfaz gráfica se
 * cargue correctamente y que el controlador pueda manejar los eventos del usuario.
 * </p>
 */
public class Programa {

    /**
     * Metodo principal que inicia el programa.
     * <p>
     * Crea las instancias del modelo, la vista y el controlador,
     * y muestra la ventana principal al usuario.
     * </p>
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Se crea la ventana principal (Vista)
            VentanaPrincipal ventana = new VentanaPrincipal();
            // Se instancia el modelo que contiene la lógica y datos del sistema
            Modelo modelo = new Modelo();
            // Se crea el controlador principal que conecta la vista con el modelo
            new ControladorPrincipal(modelo, ventana);
            // Finalmente, se hace visible la ventana para el usuario
            ventana.setVisible(true);
        });
    }
}
