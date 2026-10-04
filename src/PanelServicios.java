import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelServicios {

    private JPanel panelPrincipal;
    private JTextField nombreTX;
    private JTextField duracionTX;
    private JTextField precioTX;

    private JButton botonGuardarServicio;
    private JButton botonModificar;
    private JButton botonEliminar;

    private JLabel servicio;
    private JLabel nombre;
    private JLabel duracion;
    private JLabel precio;

    private JTable tablaServicios;
    private JScrollPane tabla_servicios;
    private JPanel panel_botones;
    private DefaultTableModel modeloTabla;

    public PanelServicios() {
        configurarTabla();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    private void configurarTabla() {
        if (tablaServicios != null) {
            tablaServicios.setBackground(Color.WHITE);
            if (tabla_servicios != null) {
                tabla_servicios.getViewport().setBackground(Color.WHITE);
            }

            modeloTabla = new DefaultTableModel(
                    new Object[]{"ID", "Nombre", "Duración", "Precio"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tablaServicios.setModel(modeloTabla);
        }
    }
}
