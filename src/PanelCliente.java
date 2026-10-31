import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelCliente {

    private JPanel panelPrincipal;
    private JPanel panel_botones;
    private JScrollPane tabla_clientes;

    private JLabel clientes;
    private JLabel nombre;
    private JLabel apellido;
    private JLabel contacto;
    private JLabel Categoria;

    private JTextField nombreTX;
    private JTextField apellidoTX;
    private JTextField contactoTX;
    private JComboBox<String> comboCategoria;
    private JCheckBox promociones;

    private JButton botonGuardarCliente;
    private JButton botonModificar;
    private JButton botonEliminar;

    private JTable tablaClientes;

    public PanelCliente() {
        inicializarModeloTabla();
    }

    private void inicializarModeloTabla() {
        if (tablaClientes == null) {
            tablaClientes = new JTable();
        }

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Apellido", "Contacto", "Categoría", "Promociones"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaClientes.setModel(model);

        if (tabla_clientes != null) {
            tabla_clientes.getViewport().setBackground(Color.WHITE);
        }
    }

    public JPanel getPanelPrincipal() {
        if (panelPrincipal == null) {
            panelPrincipal = new JPanel();
        }
        return panelPrincipal;
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

    public JComboBox<String> getComboCategoria() { return comboCategoria; }
    public JCheckBox getPromociones() { return promociones; }
    public JButton getBotonGuardarCliente() { return botonGuardarCliente; }
    public JButton getBotonModificar() { return botonModificar; }
    public JButton getBotonEliminar() { return botonEliminar; }
    public JTable getTablaClientes() { return tablaClientes; }

    public int getFilaSeleccionada() {
        return tablaClientes != null ? tablaClientes.getSelectedRow() : -1;
    }

    public DefaultTableModel getModeloTabla() {
        if (tablaClientes != null && tablaClientes.getModel() instanceof DefaultTableModel) {
            return (DefaultTableModel) tablaClientes.getModel();
        }
        return null;
    }

    public void cargarFormulario(String nombre, String apellido, String contacto, String categoria, boolean promociones) {
        if (nombreTX != null) nombreTX.setText(nombre);
        if (apellidoTX != null) apellidoTX.setText(apellido);
        if (contactoTX != null) contactoTX.setText(contacto);
        if (comboCategoria != null) comboCategoria.setSelectedItem(categoria);
        if (this.promociones != null) this.promociones.setSelected(promociones);
    }

    public void limpiarCampos() {
        if (nombreTX != null) nombreTX.setText("");
        if (apellidoTX != null) apellidoTX.setText("");
        if (contactoTX != null) contactoTX.setText("");

        if (comboCategoria != null && comboCategoria.getItemCount() > 0) {
            comboCategoria.setSelectedIndex(0);
        }

        if (promociones != null) {
            promociones.setSelected(false);
        }
    }
}