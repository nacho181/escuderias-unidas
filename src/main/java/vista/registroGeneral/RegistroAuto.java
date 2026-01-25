package vista.registroGeneral;

import javax.swing.*;

public class RegistroAuto extends JPanel{
    private JTextField modeloField;
    private JTextField motorField;
    private JButton registrarButton;
    private JButton volverButton;
    private JPanel panelAuto;

    public RegistroAuto(){
        setVisible(true);
        add(panelAuto);
        setSize(500, 500);
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JTextField getModeloField() {
        return modeloField;
    }

    public JTextField getMotorField() {
        return motorField;
    }
}
