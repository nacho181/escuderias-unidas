package vista.gestionEscuderia;

import vista.VentanaPrincipal;

import javax.swing.*;

public class RegistrarEscuderiaAuto extends JPanel{
    private JTextField modeloField;
    private JButton volverButton;
    private JButton registrarButton;
    private JPanel registrarEscuderiaAutoPanel;

    public RegistrarEscuderiaAuto(VentanaPrincipal ventanaPrincipal){
        setVisible(true);
        setSize(500, 500);
        add(registrarEscuderiaAutoPanel);
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JTextField getModeloField() {
        return modeloField;
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }
}
