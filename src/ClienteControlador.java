import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

// conecta la vista (PanelCliente) con los datos del Cliente (modelo)
public class ClienteControlador {

    private PanelCliente vista;
    private List<Cliente> listaClientes; // simula una BD
    private int contadorId = 1;

    public ClienteControlador(PanelCliente vista) {
        this.vista = vista;
        this.listaClientes = new ArrayList<>();
        inicializarEventos();
    }

    private void inicializarEventos() {
        if (vista.getBotonGuardarCliente() != null) {
            vista.getBotonGuardarCliente().addActionListener(e -> guardarCliente());
        }

        if (vista.getBotonModificar() != null) {
            vista.getBotonModificar().addActionListener(e -> modificarCliente());
        }

        if (vista.getBotonEliminar() != null) {
            vista.getBotonEliminar().addActionListener(e -> eliminarCliente());
        }

        if (vista.getTablaClientes() != null) {
            vista.getTablaClientes().getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting() && vista.getFilaSeleccionada() != -1) {
                    int fila = vista.getFilaSeleccionada();
                    String nombre = vista.getModeloTabla().getValueAt(fila, 1).toString();
                    String apellido = vista.getModeloTabla().getValueAt(fila, 2).toString();
                    String contacto = vista.getModeloTabla().getValueAt(fila, 3).toString();
                    vista.cargarFormulario(nombre, apellido, contacto);
                }
            });
        }
    }

    private void guardarCliente() {
        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        String contacto = vista.getContacto();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Nombre y Apellido son obligatorios.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente nuevo = new Cliente(contadorId++, nombre, apellido, contacto);
        listaClientes.add(nuevo);

        actualizarTablaVisual();
        vista.limpiarCampos();
        JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                "Cliente guardado con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void modificarCliente() {
        int fila = vista.getFilaSeleccionada();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Seleccioná un cliente de la tabla.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) vista.getModeloTabla().getValueAt(fila, 0);

        for (Cliente c : listaClientes) {
            if (c.getId() == id) {
                c.setNombre(vista.getNombre());
                c.setApellido(vista.getApellido());
                c.setContacto(vista.getContacto());
                break;
            }
        }

        actualizarTablaVisual();
        vista.limpiarCampos();
    }

    private void eliminarCliente() {
        int fila = vista.getFilaSeleccionada();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Seleccioná un cliente para eliminar.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) vista.getModeloTabla().getValueAt(fila, 0);
        listaClientes.removeIf(c -> c.getId() == id);

        actualizarTablaVisual();
        vista.limpiarCampos();
    }

    private void actualizarTablaVisual() {
        vista.getModeloTabla().setRowCount(0); // Limpiar filas de la tabla
        for (Cliente c : listaClientes) {
            vista.getModeloTabla().addRow(
                    new Object[]{c.getId(), c.getNombre(), c.getApellido(), c.getContacto()});
        }
    }
}
