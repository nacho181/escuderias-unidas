package vista.registroGeneral;

import javax.swing.*;

public class RegistroPiloto extends JPanel {
    private JPanel registroPilotoPanel;
    private JTextField dniField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField idPaisField;
    private JTextField numeroCompeField;
    private JTextField numVictField;
    private JTextField numPoleField;
    private JTextField numVueltField;
    private JTextField numPodField;
    private JTextField numPuntosField;
    private JButton volverButton;
    private JButton registrarButton;

    public RegistroPiloto() {
        setVisible(true);
        add(registroPilotoPanel);
        setSize(500, 500);
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JTextField getDniField() {
        return dniField;
    }

    public JTextField getNombreField() {
        return nombreField;
    }

    public JTextField getApellidoField() {
        return apellidoField;
    }

    public JTextField getIdPaisField() {
        return idPaisField;
    }

    public JTextField getNumeroCompeField() {
        return numeroCompeField;
    }

    public JTextField getNumVictField() {
        return numVictField;
    }

    public JTextField getNumPoleField() {
        return numPoleField;
    }

    public JTextField getNumPodField() {
        return numPodField;
    }

    public JTextField getNumPuntosField() {
        return numPuntosField;
    }

    public JTextField getNumVueltField() {
        return numVueltField;
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }
}
