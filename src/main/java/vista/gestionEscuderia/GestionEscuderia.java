package vista.gestionEscuderia;

import javax.swing.*;

public class GestionEscuderia extends JPanel{
    private JTextField nombreField;
    private JButton enviarButton;
    private JButton volverButton;
    private JPanel gestionEscuderiaPanel;

    public GestionEscuderia() {
        setVisible(true);
        setSize(500, 500);
        add(gestionEscuderiaPanel);
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getEnviarButton() {
        return enviarButton;
    }

    public JTextField getNombreField() {
        return nombreField;
    }
}
