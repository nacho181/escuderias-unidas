package vista.registroGeneral;

import vista.VentanaPrincipal;

import javax.swing.*;

public class RegistroCircuito extends JPanel{
    private JTextField nombreField;
    private JTextField longitudField;
    private JTextField paisField;
    private JButton registrarButton;
    private JButton volverButton;
    private JPanel registroCircuitoPanel;

    public RegistroCircuito(VentanaPrincipal ventanaPrincipal) {
        setVisible(true);
        add(registroCircuitoPanel);
        setSize(500, 500);

    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JTextField getLongitudField() {
        return longitudField;
    }

    public JTextField getNombreField() {
        return nombreField;
    }

    public JTextField getPaisField() {
        return paisField;
    }
}
