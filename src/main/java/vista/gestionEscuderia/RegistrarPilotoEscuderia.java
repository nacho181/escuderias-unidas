package vista.gestionEscuderia;

import vista.VentanaPrincipal;

import javax.swing.*;

public class RegistrarPilotoEscuderia extends JPanel{
    private JTextField dniField;
    private JTextField anioEntradaField;
    private JTextField anioFinField;
    private JButton volverButton;
    private JButton registrarButton;
    private JPanel registrarPilotoEscuderiaPanel;

    public RegistrarPilotoEscuderia(VentanaPrincipal ventanaPrincipal) {
        setSize(500,500);
        setVisible(true);
        add(registrarPilotoEscuderiaPanel);
    }

    public JTextField getDniField() {
        return dniField;
    }

    public JTextField getAnioEntradaField() {
        return anioEntradaField;
    }

    public JTextField getAnioFinField() {
        return anioFinField;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }
}
