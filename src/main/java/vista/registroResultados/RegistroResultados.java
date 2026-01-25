package vista.registroResultados;

import javax.swing.*;

public class RegistroResultados extends JPanel {
    private JTextField fechaField;
    private JButton volverButton;
    private JButton enviarButton;
    private JPanel regResultPanel;

    public RegistroResultados() {
        setSize(500, 500);
        setVisible(true);
        add(regResultPanel);
    }



    public JTextField getFechaField() {
        return fechaField;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getEnviarButton() {
        return enviarButton;
    }
}
