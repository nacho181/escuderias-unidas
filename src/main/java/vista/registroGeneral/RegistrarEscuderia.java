package vista.registroGeneral;


import javax.swing.*;

public class RegistrarEscuderia extends JPanel {
    private JTextField nombreEscuderiaField;
    private JTextField idEscuderiaField;
    private JButton registrarButton;
    private JButton volverButton;
    private JPanel escuderiaPanel;

    public RegistrarEscuderia() {
        add(escuderiaPanel);
        setSize(500,500);
        setVisible(true);
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JTextField getIdEscuderiaField() {
        return idEscuderiaField;
    }

    public JTextField getNombreEscuderiaField() {
        return nombreEscuderiaField;
    }
}
