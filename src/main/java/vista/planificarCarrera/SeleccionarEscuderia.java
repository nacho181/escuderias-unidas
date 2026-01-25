package vista.planificarCarrera;

import javax.swing.*;

public class SeleccionarEscuderia extends JPanel{
    private JTextField nombreEscuField;
    private JButton volverButton;
    private JButton enviarButton;
    private JPanel escuSeleccPanel;

    public SeleccionarEscuderia() {
        setSize(500,500);
        setVisible(true);
        add(escuSeleccPanel);
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getEnviarButton() {
        return enviarButton;
    }

    public JTextField getNombreEscuField() {
        return nombreEscuField;
    }
}
