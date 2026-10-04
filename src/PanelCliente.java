import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelCliente {

    private JPanel panelPrincipal;
    private JTextField nombreTX;
    private JTextField apellidoTX;
    private JTextField contactoTX;

    private JButton botonGuardarCliente;
    private JButton botonModificar;
    private JButton botonEliminar;

    private JLabel cliente;
    private JLabel nombre;
    private JLabel apellido;
    private JLabel contacto;

    private JTable tablaClientes;
    private JScrollPane tabla_clientes;
    private JPanel panel_botones;
    private DefaultTableModel modeloTabla;

    public PanelCliente() {
        configurarTabla();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    private void configurarTabla() {
        if (tablaClientes != null) {
            tablaClientes.setBackground(Color.WHITE);
            if (tabla_clientes != null) {
                tabla_clientes.getViewport().setBackground(Color.WHITE);
            }

            modeloTabla = new DefaultTableModel(
                    new Object[]{"ID", "Nombre", "Apellido", "Contacto"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tablaClientes.setModel(modeloTabla);
        }
    }

    public String getNombre() {
        return nombreTX != null ? nombreTX.getText().trim() : "";
    }

    public String getApellido() {
        return apellidoTX != null ? apellidoTX.getText().trim() : "";
    }

    public String getContacto() {
        return contactoTX != null ? contactoTX.getText().trim() : "";
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTablaClientes() {
        return tablaClientes;
    }

    public int getFilaSeleccionada() {
        return tablaClientes != null ? tablaClientes.getSelectedRow() : -1;
    }

    public JButton getBotonGuardarCliente() {
        return botonGuardarCliente;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public void cargarFormulario(String nombre, String apellido, String contacto) {
        if (nombreTX != null) nombreTX.setText(nombre);
        if (apellidoTX != null) apellidoTX.setText(apellido);
        if (contactoTX != null) contactoTX.setText(contacto);
    }

    public void limpiarCampos() {
        if (nombreTX != null) nombreTX.setText("");
        if (apellidoTX != null) apellidoTX.setText("");
        if (contactoTX != null) contactoTX.setText("");
    }
}
