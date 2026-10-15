import javax.swing.*;
import javax.swing.table.DefaultTableModel;
<<<<<<< HEAD
import java.awt.*;

public class PanelCliente {

    private JPanel panelPrincipal;
    private JTextField nombreTX;
    private JTextField apellidoTX;
    private JTextField contactoTX;

=======

public class PanelCliente {
    // 1. Paneles contenedores vinculados en el .form
    private JPanel panelPrincipal;
    private JPanel panel_botones;
    private JScrollPane tabla_clientes;

    // 2. Etiquetas (JLabel) vinculadas en el .form
    private JLabel clientes;
    private JLabel nombre;
    private JLabel apellido;
    private JLabel contacto;
    private JLabel Categoria;

    // 3. Campos de texto e interacción
    private JTextField nombreTX;
    private JTextField apellidoTX;
    private JTextField contactoTX;
    private JComboBox<String> comboCategoria;
    private JCheckBox promociones;

    // 4. Botones
>>>>>>> master
    private JButton botonGuardarCliente;
    private JButton botonModificar;
    private JButton botonEliminar;

<<<<<<< HEAD
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

=======
    // 5. Tabla
    private JTable tablaClientes;

    // Constructor que asegura la configuración del DefaultTableModel no editable (Opción 2)
    public PanelCliente() {
        inicializarModeloTabla();
    }

    private void inicializarModeloTabla() {
        if (tablaClientes == null) {
            tablaClientes = new JTable();
        }

        // Asignamos un DefaultTableModel anulando isCellEditable
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Apellido", "Contacto", "Categoría", "Promociones"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desactiva la edición directa de celdas en la grilla
            }
        };
        tablaClientes.setModel(model);
    }

    // --- MÉTODOS DE ACCESO (GETTERS) Y LÓGICA DE VISTA ---

>>>>>>> master
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

<<<<<<< HEAD
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

=======
>>>>>>> master
    public String getNombre() {
        return nombreTX != null ? nombreTX.getText().trim() : "";
    }

    public String getApellido() {
        return apellidoTX != null ? apellidoTX.getText().trim() : "";
    }

    public String getContacto() {
        return contactoTX != null ? contactoTX.getText().trim() : "";
    }

<<<<<<< HEAD
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
=======
    public JComboBox<String> getComboCategoria() { return comboCategoria; }
    public JCheckBox getPromociones() { return promociones; }
    public JButton getBotonGuardarCliente() { return botonGuardarCliente; }
    public JButton getBotonModificar() { return botonModificar; }
    public JButton getBotonEliminar() { return botonEliminar; }
    public JTable getTablaClientes() { return tablaClientes; }

    public DefaultTableModel getModeloTabla() {
        if (tablaClientes != null && tablaClientes.getModel() instanceof DefaultTableModel) {
            return (DefaultTableModel) tablaClientes.getModel();
        }
        return null;
>>>>>>> master
    }

    public void limpiarCampos() {
        if (nombreTX != null) nombreTX.setText("");
        if (apellidoTX != null) apellidoTX.setText("");
        if (contactoTX != null) contactoTX.setText("");
<<<<<<< HEAD
    }
}
=======

        if (comboCategoria != null && comboCategoria.getItemCount() > 0) {
            comboCategoria.setSelectedIndex(0);
        }

        if (promociones != null) {
            promociones.setSelected(false);
        }
    }
}
>>>>>>> master
