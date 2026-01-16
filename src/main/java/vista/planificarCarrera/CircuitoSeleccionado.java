package vista.planificarCarrera;

import vista.VentanaPrincipal;

import javax.swing.*;

public class CircuitoSeleccionado extends JPanel{
    private JTextField fechaField;
    private JTextField numVueltField;
    private JTextField horaCarrera;
    private JTextField totCarreraField;
    private JButton volverButton;
    private JButton continuarConElRegistroButton;
    private JPanel circSeleccPanel;

    public CircuitoSeleccionado(VentanaPrincipal ventanaPrincipal){
        setSize(500,500);
        setVisible(true);
        add(circSeleccPanel);
    }

    public JTextField getFechaField() {
        return fechaField;
    }

    public JTextField getNumVueltField() {
        return numVueltField;
    }

    public JTextField getHoraCarrera() {
        return horaCarrera;
    }

    public JTextField getTotCarreraField() {
        return totCarreraField;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public JButton getContinuarConElRegistroButton() {
        return continuarConElRegistroButton;
    }
}
