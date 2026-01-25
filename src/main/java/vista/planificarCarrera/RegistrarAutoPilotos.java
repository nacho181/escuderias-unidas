package vista.planificarCarrera;

import javax.swing.*;

public class RegistrarAutoPilotos extends JPanel{
    private JTextField dniField;
    private JTextField modeloField;
    private JButton volverButton;
    private JButton registrarButton;
    private JPanel RegPilPanel;

    public RegistrarAutoPilotos() {
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
