package vista.informes;

import vista.VentanaPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OpcionCinco extends JPanel{
    private JButton consultarButton;
    private JButton volverButton;
    private JTable table1;
    private JPanel opcion5Panel;
    private JTextField nombreField;
    private DefaultTableModel modeloTabla;

    public OpcionCinco(VentanaPrincipal ventanaPrincipal){
        add(opcion5Panel);
        setSize(500,500);
        setVisible(true);
        String[] columnas = {"DNI","Nombre", "Apellido", "Especialidad", "Años"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        table1.setModel(modeloTabla);
    }

    public JButton getConsultarButton() {
        return consultarButton;
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
}
