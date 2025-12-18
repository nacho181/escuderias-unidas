package vista.informes;

import vista.VentanaPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OpcionCuatro extends JPanel{
    private JTextField nombreField;
    private JButton volverButton;
    private JButton enviarButton;
    private JTable table1;
    private JPanel opcionCuatroPanel;
    private DefaultTableModel modeloTabla;

    public OpcionCuatro(VentanaPrincipal ventanaPrincipal){
        add(opcionCuatroPanel);
        setSize(500,500);
        setVisible(true);
        String[] columnas = {"Posición", "Modelo", "Motor", "Fecha", "Circuito"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        table1.setModel(modeloTabla);
    }

    public JTextField getNombreField() {
        return nombreField;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getEnviarButton() {
        return enviarButton;
    }
}
