package vista.planificarCarrera;

import vista.VentanaPrincipal;

import javax.swing.*;

public class RegistrarAutoPilotos extends JPanel{
    private JTextField dniField;
    private JTextField modeloField;
    private JButton volverButton;
    private JButton registrarButton;
    private JPanel RegPilPanel;

    public RegistrarAutoPilotos(VentanaPrincipal ventanaPrincipal) {
        setSize(500,500);
        setVisible(true);
        add(RegPilPanel);
    }

    public JTextField getDniField() {
        return dniField;
    }

    public JTextField getModeloField() {
        return modeloField;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }
}
