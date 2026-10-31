import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClienteControlador {

    private final PanelCliente vista;
    private final ClienteDAO clienteDAO;

    public ClienteControlador(PanelCliente vista) {
        this.vista = vista;
        this.clienteDAO = new ClienteDAO();

        poblarComboCategoria();
        inicializarEventos();
        refrescarTabla();
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
                    String categoria = vista.getModeloTabla().getValueAt(fila, 4).toString();
                    boolean promociones = "Sí".equals(vista.getModeloTabla().getValueAt(fila, 5));
                    vista.cargarFormulario(nombre, apellido, contacto, categoria, promociones);
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

        String categoria = (vista.getComboCategoria() != null && vista.getComboCategoria().getSelectedItem() != null)
                ? (String) vista.getComboCategoria().getSelectedItem()
                : "Nuevo";

        boolean aceptaPromos = (vista.getPromociones() != null) && vista.getPromociones().isSelected();

        Cliente nuevo = new Cliente(0L, nombre, apellido, contacto, categoria, aceptaPromos);
        clienteDAO.guardar(nuevo);

        refrescarTabla();
        vista.limpiarCampos();

        JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                "Cliente guardado con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void modificarCliente() {
        int filaSeleccionada = vista.getFilaSeleccionada();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Seleccioná un cliente de la tabla para modificar.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        String contacto = vista.getContacto();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Nombre y Apellido son obligatorios.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String categoria = (vista.getComboCategoria() != null && vista.getComboCategoria().getSelectedItem() != null)
                ? (String) vista.getComboCategoria().getSelectedItem()
                : "Nuevo";

        boolean aceptaPromos = (vista.getPromociones() != null) && vista.getPromociones().isSelected();

        long idCliente = Long.parseLong(vista.getModeloTabla().getValueAt(filaSeleccionada, 0).toString());
        Cliente existente = clienteDAO.buscarPorId(idCliente);

        if (existente != null) {
            existente.setNombre(nombre);
            existente.setApellido(apellido);
            existente.setContacto(contacto);
            existente.setCategoria(categoria);
            existente.setPromociones(aceptaPromos);
            clienteDAO.actualizar(existente);
        }

        refrescarTabla();
        vista.limpiarCampos();
        JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                "Cliente modificado con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarCliente() {
        int filaSeleccionada = vista.getFilaSeleccionada();
        if (filaSeleccionada >= 0) {
            long idCliente = Long.parseLong(vista.getModeloTabla().getValueAt(filaSeleccionada, 0).toString());
            clienteDAO.eliminar(idCliente);
            refrescarTabla();
            vista.limpiarCampos();
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Cliente eliminado con éxito.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Seleccioná un cliente de la tabla para eliminar.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refrescarTabla() {
        DefaultTableModel model = vista.getModeloTabla();
        if (model != null) {
            model.setRowCount(0);
            List<Cliente> lista = clienteDAO.obtenerTodos();
            for (Cliente c : lista) {
                model.addRow(new Object[]{
                        c.getId(),
                        c.getNombre(),
                        c.getApellido(),
                        c.getContacto(),
                        c.getCategoria(),
                        c.isPromociones() ? "Sí" : "No"
                });
            }
        }
    }
}