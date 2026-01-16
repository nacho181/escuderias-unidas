package vista.informes;

import vista.VentanaPrincipal;

import javax.swing.*;

public class OpcionSiete extends JPanel{
    private JTextField nombreCircPanel;
    private JButton consultarButton;
    private JButton volverButton;
    private JPanel opcion7Panel;

    public OpcionSiete(VentanaPrincipal ventanaPrincipal) {
        add(opcion7Panel);
        setSize(500,500);
        setVisible(true);
    }

    public JTextField getNombreCircPanel() {
        return nombreCircPanel;
    }

    public JButton getConsultarButton() {
        return consultarButton;
    }

    public JButton getVolverButton() {
        return volverButton;
    }
}
