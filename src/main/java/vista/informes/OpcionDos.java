package vista.informes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OpcionDos extends JPanel{
    private JButton consultarButton;
    private JButton volverButton;
    private JTable table1;
    private JPanel opcionDosPanel;
    private final DefaultTableModel modeloTabla;

    public OpcionDos() {
        add(opcionDosPanel);
        setVisible(true);
        setSize(500,500);
        String[] columnas = {"Posición", "Nombre", "Apellido", "DNI", "Puntos"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        table1.setModel(modeloTabla);
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JButton getConsultarButton() {
        return consultarButton;
    }
}
