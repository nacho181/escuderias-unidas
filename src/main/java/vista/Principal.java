package vista;

import javax.swing.*;

public class Principal extends JPanel {
    private JPanel panelPrincipal;
    private JButton gestionarEscuderiaButton;
    private JButton registroGeneralButton;
    private JButton planificarCarreraButton;
    private JButton registrarResultadoButton;
    private JButton informeGeneralesButton;
    private JLabel tituloPrincipalLabel;

    public Principal() {
        setVisible(true);
        setSize(500, 500);
        add(panelPrincipal);
    }

    public JButton getGestionarEscuderiaButton() {
        return gestionarEscuderiaButton;
    }

    public JButton getRegistroGeneralButton() {
        return registroGeneralButton;
    }

    public JButton getPlanificarCarreraButton() {
        return planificarCarreraButton;
    }

    public JButton getRegistrarResultadoButton() {
        return registrarResultadoButton;
    }

    public JButton getInformeGeneralesButton() {
        return informeGeneralesButton;
    }
}
