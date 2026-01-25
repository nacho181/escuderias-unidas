package vista.gestionEscuderia;

import javax.swing.*;

public class EscuderiaSeleccionada extends JPanel {
    private JButton registrarPilotoButton;
    private JButton registrarMecanicoButton;
    private JButton registrarAutoButton;
    private JButton volverButton;
    private JPanel escuderiaSeleccionadaPanel;

    public EscuderiaSeleccionada() {
        add(escuderiaSeleccionadaPanel);
        setSize(500,500);
        setVisible(true);
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getRegistrarAutoButton() {
        return registrarAutoButton;
    }

    public JButton getRegistrarMecanicoButton() {
        return registrarMecanicoButton;
    }

    public JButton getRegistrarPilotoButton() {
        return registrarPilotoButton;
    }
}
