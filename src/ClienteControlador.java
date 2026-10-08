import java.util.ArrayList;
import java.util.List;

public class ClienteControlador {
    private final PanelCliente vista;
    private final List<Cliente> listaClientes;
    private int contadorId = 1;

    public ClienteControlador(PanelCliente vista) {
        this.vista = vista;
        this.listaClientes = new ArrayList<>();

        poblarComboCategoria();
        configurarTabla();
        inicializarEventos();
    }

    private void poblarComboCategoria() {
        if (vista.getComboCategoria() != null) {
            vista.getComboCategoria().removeAllItems();
            vista.getComboCategoria().addItem("Nuevo");
            vista.getComboCategoria().addItem("Habitual");
            vista.getComboCategoria().addItem("Frecuente");
            vista.getComboCategoria().addItem("VIP");
        }
    }

    private void configurarTabla() {
        if (vista.getModeloTabla() != null) {
            vista.getModeloTabla().setColumnIdentifiers(new String[]{
                    "ID", "Nombre", "Apellido", "Contacto", "Categoría", "Promociones"
            });
        }
    }

    private void inicializarEventos() {
        if (vista.getBotonGuardarCliente() != null) {
            vista.getBotonGuardarCliente().addActionListener(e -> guardarCliente());
        }
        if (vista.getBotonEliminar() != null) {
            vista.getBotonEliminar().addActionListener(e -> eliminarCliente());
        }
    }

    private void guardarCliente() {
        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        String contacto = vista.getContacto();

        String categoria = (vista.getComboCategoria() != null && vista.getComboCategoria().getSelectedItem() != null)
                ? (String) vista.getComboCategoria().getSelectedItem()
                : "Nuevo";

        // Consulta del estado actual del JCheckBox con isSelected() (PDF 3)
        boolean aceptaPromos = (vista.getPromociones() != null) && vista.getPromociones().isSelected();

        Cliente nuevo = new Cliente(contadorId++, nombre, apellido, contacto, categoria, aceptaPromos);
        listaClientes.add(nuevo);

        refrescarTabla();
        vista.limpiarCampos();
    }

    private void eliminarCliente() {
        int filaSeleccionada = vista.getTablaClientes().getSelectedRow();
        if (filaSeleccionada >= 0) {
            listaClientes.remove(filaSeleccionada);
            refrescarTabla();
            vista.limpiarCampos();
        }
    }

    private void refrescarTabla() {
        if (vista.getModeloTabla() != null) {
            vista.getModeloTabla().setRowCount(0);
            for (Cliente c : listaClientes) {
                vista.getModeloTabla().addRow(new Object[]{
                        c.getId(),
                        c.getNombre(),
                        c.getApellido(),
                        c.getContacto(),
                        c.getCategoria(),
                        c.isPromociones() ? "Sí" : "No" // Formateo legible para la grilla
                });
            }
        }
    }
}