package vista.registroGeneral;

import entidades.Especialidad;

import javax.swing.*;

public class RegistroMecanico extends JPanel{
    private JPanel registroMecanicoPanel;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField idPaisField;
    private JComboBox<Especialidad> especialidadComboBox;
    private JTextField aniosField;
    private JButton registrarButton;
    private JButton volverButton;
    private JTextField dniField;

    public RegistroMecanico() {
        setVisible(true);
        add(registroMecanicoPanel);
        setSize(500, 500);
        inicializarComboBox();
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }

    public JTextField getDniField() {
        return dniField;
    }

    public JTextField getAniosField() {
        return aniosField;
    }

    public JTextField getIdPaisField() {
        return idPaisField;
    }

    public JTextField getApellidoField() {
        return apellidoField;
    }

    public JTextField getNombreField() {
        return nombreField;
    }

    public JComboBox<Especialidad> getEspecialidadComboBox() {
        return especialidadComboBox;
    }
    private void inicializarComboBox() {
        DefaultComboBoxModel<Especialidad> model = new DefaultComboBoxModel<>(Especialidad.values());
        especialidadComboBox.setModel(model);
    }

}
