package vista.registroGeneral;
import vista.VentanaPrincipal;
import javax.swing.*;

public class RegistroGeneral extends JPanel {
    private JPanel panelRegistroGeneral;
    private JButton registrarPaisButton;
    private JButton registrarAutoButton;
    private JButton registrarEscuderiaButton;
    private JButton registrarCircuitoButton;
    private JButton volverButton;
    private JLabel warningLabel;
    private JLabel registroGnralLabel;
    private JButton registrarMecanicoButton;
    private JButton registrarPilotoButton;

    public RegistroGeneral(VentanaPrincipal ventanaPrincipal) {
        add(panelRegistroGeneral);
        setVisible(true);
        setSize(500, 500);

    }

    public JButton getRegistrarPaisButton() {
        return registrarPaisButton;
    }

    public JButton getRegistrarAutoButton() {
        return registrarAutoButton;
    }

    public JButton getRegistrarEscuderiaButton() {
        return registrarEscuderiaButton;
    }

    public JButton getRegistrarCircuitoButton() {
        return registrarCircuitoButton;
    }


    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getRegistrarPilotoButton() {
        return registrarPilotoButton;
    }

    public JButton getRegistrarMecanicoButton() {
        return registrarMecanicoButton;
    }
}
