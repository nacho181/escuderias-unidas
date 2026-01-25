package vista.informes;

import javax.swing.*;

public class OpcionSeis extends JPanel{
    private JTextField nombreCircField;
    private JTextField dniField;
    private JButton volverButton;
    private JButton consultarButton;
    private JPanel opcion6Panel;

    public OpcionSeis(){
        add(opcion6Panel);
        setVisible(true);
        setSize(500, 500);
    }

    public JTextField getDniField() {
        return dniField;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getConsultarButton() {
        return consultarButton;
    }

    public JTextField getNombreCircField() {
        return nombreCircField;
    }
}
