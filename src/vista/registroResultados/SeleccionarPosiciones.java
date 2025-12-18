package vista.registroResultados;

import entidades.AutoPiloto;
import entidades.Piloto;
import entidades.Posicion;
import vista.VentanaPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Vista que permite registrar las posiciones de los pilotos en una carrera.
 * <p>
 * Presenta una tabla con los pilotos y sus autos, donde el usuario puede asignar
 * manualmente la posición obtenida en la carrera. A partir de la posición seleccionada,
 * el sistema calcula automáticamente los puntos correspondientes.
 * </p>
 */
public class SeleccionarPosiciones extends JPanel {

    private JPanel seleccPosPanel;
    private JTable tablaPilotos;
    private JButton volverButton;
    private JButton registrarButton;
    private JScrollPane JScrollPanel;
    private DefaultTableModel modeloTabla;


    /**
     * Constructor que inicializa la vista y su tabla.
     *
     * @param ventanaPrincipal referencia a la ventana principal del sistema.
     */
    public SeleccionarPosiciones(VentanaPrincipal ventanaPrincipal) {
        add(seleccPosPanel);
        setVisible(true);
        setSize(500, 500);
        initTabla();
    }


    /**
     * Inicializa la tabla principal con sus columnas y editores.
     * <p>
     * Solo la columna de "Posición" es editable. Al seleccionar una posición,
     * se calculan automáticamente los puntos en la columna final.
     * </p>
     */
    private void initTabla() {
        modeloTabla = new DefaultTableModel(
                new Object[]{"DNI", "Apellido", "Auto", "Posición", "Puntos"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Solo la columna "Posición" es editable
            }
        };

        tablaPilotos.setModel(modeloTabla);

        // Editor tipo combo box para elegir posición
        JComboBox<Integer> combo = new JComboBox<>();
        for (int i = 1; i <= 20; i++) combo.addItem(i);

        tablaPilotos.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(combo));

        // Al seleccionar una posición, se calculan los puntos automáticos
        combo.addActionListener(e -> {
            int fila = tablaPilotos.getSelectedRow();
            if (fila >= 0 && combo.getSelectedItem() != null) {
                int pos = (int) combo.getSelectedItem();
                modeloTabla.setValueAt(Posicion.getPuntos(pos), fila, 4);
            }
        });

        // Configuración del scroll
        JScrollPane scroll = new JScrollPane(tablaPilotos);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll);
    }


    /**
     * Carga en la tabla los pilotos participantes de la carrera.
     * <p>
     * Se limpia la tabla antes de agregar los nuevos registros.
     * </p>
     *
     * @param pilotos lista de objetos {@link AutoPiloto} con los datos a mostrar.
     */
    public void cargarPilotos(List<AutoPiloto> pilotos) {
        modeloTabla.setRowCount(0); // Limpiar antes de cargar

        for (AutoPiloto autoPiloto : pilotos) {
            Piloto p = autoPiloto.getPiloto();
            modeloTabla.addRow(new Object[]{
                    p.getDni(),                         // Columna 0: DNI
                    p.getApellido(),                    // Columna 1: Apellido
                    autoPiloto.getAuto().getModelo(),   // Columna 2: Modelo del auto
                    null,                               // Columna 3: Posición (editable)
                    0                                   // Columna 4: Puntos iniciales
            });
        }
    }


    /**
     * Devuelve todos los datos cargados en la tabla.
     *
     * @return lista con los valores de cada fila en formato {@code Object[]}.
     */
    public ArrayList<Object[]> obtenerDatosTabla() {
        ArrayList<Object[]> filas = new ArrayList<>();

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            Object dni = modeloTabla.getValueAt(i, 0);
            Object apellido = modeloTabla.getValueAt(i, 1);
            Object auto = modeloTabla.getValueAt(i, 2);
            Object posicion = modeloTabla.getValueAt(i, 3);
            Object puntos = modeloTabla.getValueAt(i, 4);

            filas.add(new Object[]{dni, apellido, auto, posicion, puntos});
        }
        return filas;
    }


    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }

    public JButton getVolverButton() {
        return volverButton;
    }
    public JTable getTablaPilotos() {
        return tablaPilotos;
    }
}
