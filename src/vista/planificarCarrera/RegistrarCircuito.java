package vista.planificarCarrera;

import vista.VentanaPrincipal;

import javax.swing.*;

public class RegistrarCircuito extends JPanel {
    private JTextField circuitoField;
    private JButton volverButton;
    private JButton registrarButton;
    private JPanel planCarreraPanel;

    public RegistrarCircuito(VentanaPrincipal ventanaPrincipal) {
        setSize(500,500);
        setVisible(true);
        add(planCarreraPanel);
    }



    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }

    public JTextField getCircuitoField() {
        return circuitoField;
    }
}
