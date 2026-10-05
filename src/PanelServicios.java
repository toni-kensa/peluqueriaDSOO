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
                    new Object[]{"ID", "Nombre", "Duración (min)", "Precio"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tablaServicios.setModel(modeloTabla);
        }
    }

    public String getNombre() {
        return nombreTX != null ? nombreTX.getText().trim() : "";
    }

    public String getDuracion() {
        return duracionTX != null ? duracionTX.getText().trim() : "";
    }

    public String getPrecio() {
        return precioTX != null ? precioTX.getText().trim() : "";
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTablaServicios() {
        return tablaServicios;
    }

    public int getFilaSeleccionada() {
        return tablaServicios != null ? tablaServicios.getSelectedRow() : -1;
    }

    public JButton getBotonGuardarServicio() {
        return botonGuardarServicio;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public void cargarFormulario(String nombre, String duracion, String precio) {
        if (nombreTX != null) nombreTX.setText(nombre);
        if (duracionTX != null) duracionTX.setText(duracion);
        if (precioTX != null) precioTX.setText(precio);
    }

    public void limpiarCampos() {
        if (nombreTX != null) nombreTX.setText("");
        if (duracionTX != null) duracionTX.setText("");
        if (precioTX != null) precioTX.setText("");
    }
}
