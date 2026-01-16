package vista.informes;

import vista.VentanaPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OpcionUno extends JPanel{
    private JTextField primerFechaField;
    private JTextField segundaFechaField;
    private JTable table1;
    private JButton enviarButton;
    private JButton volverButton;
    private JPanel opcio1Panel;
    private DefaultTableModel modeloTabla;

    public OpcionUno(VentanaPrincipal ventanaPrincipal){
        setVisible(true);
        setSize(500,500);
        add(opcio1Panel);
        String[] columnas = {"Fecha", "Circuito", "Vueltas", "Hora", "Piloto", "Posición"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        table1.setModel(modeloTabla);
    }

    public JTable getTable1() {
        return table1;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTextField getPrimerFechaField() {
        return primerFechaField;
    }

    public JTextField getSegundaFechaField() {
        return segundaFechaField;
    }

    public JButton getEnviarButton() {
        return enviarButton;
    }

    public JButton getVolverButton() {
        return volverButton;
    }
}
