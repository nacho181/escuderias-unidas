package vista.informes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OpcionTres extends JPanel{
    private JTextField dniField;
    private JTable table1;
    private JButton volverButton;
    private JButton enviarButton;
    private JPanel opcionTresPanel;
    private final DefaultTableModel modeloTabla;

    public OpcionTres() {
        add(opcionTresPanel);
        setVisible(true);
        setSize(500, 500);
        String[] columnas = {"Posición", "Nombre", "Apellido", "Fecha", "Circuito"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        table1.setModel(modeloTabla);
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTextField getDniField() {
        return dniField;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getEnviarButton() {
        return enviarButton;
    }
}
