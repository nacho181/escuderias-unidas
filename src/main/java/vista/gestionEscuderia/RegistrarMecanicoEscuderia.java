package vista.gestionEscuderia;

import javax.swing.*;

public class RegistrarMecanicoEscuderia extends JPanel{
    private JTextField dniField;
    private JButton volverButton;
    private JButton registrarButton;
    private JPanel registrarMecanicoEscuderiaPanel;

    public RegistrarMecanicoEscuderia() {
        add(registrarMecanicoEscuderiaPanel);
        setSize(500,500);
        setVisible(true);

    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JTextField getDniField() {
        return dniField;
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }
}
