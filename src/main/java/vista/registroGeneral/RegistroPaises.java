package vista.registroGeneral;

import javax.swing.*;

public class RegistroPaises extends JPanel{
    private JPanel registroPaisesPanel;
    private JLabel registroPaisesLabel;
    private JTextField idPaisField;
    private JTextField descripcionPaisField;
    private JButton registrarButton;
    private JButton volverButton;

    public RegistroPaises() {
        add(registroPaisesPanel);
        setVisible(true);
        setSize(500,500);

    }



    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }

    public JTextField getIdPaisField() {
        return idPaisField;
    }

    public JTextField getDescripcionPaisField() {
        return descripcionPaisField;
    }
}
