package vista.informes;

import javax.swing.*;

public class SeleccionInformes extends JPanel{
    private JButton opcion1Button;
    private JButton opcion2Button;
    private JButton opcion3Button;
    private JButton opcion4Button;
    private JButton opcion5Button;
    private JButton opcion6Button;
    private JButton opcion7Button;
    private JButton volverButton;
    private JPanel seleccionInfPanel;

    public SeleccionInformes() {
        add(seleccionInfPanel);
        setVisible(true);
        setSize(500,500);
    }

    public JButton getOpcion1Button() {
        return opcion1Button;
    }

    public JButton getOpcion2Button() {
        return opcion2Button;
    }

    public JButton getOpcion3Button() {
        return opcion3Button;
    }

    public JButton getOpcion4Button() {
        return opcion4Button;
    }

    public JButton getOpcion5Button() {
        return opcion5Button;
    }

    public JButton getOpcion6Button() {
        return opcion6Button;
    }

    public JButton getOpcion7Button() {
        return opcion7Button;
    }

    public JButton getVolverButton() {
        return volverButton;
    }
}
