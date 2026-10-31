import javax.swing.JOptionPane;
import java.util.List;

public class ServicioControlador {

    private final PanelServicios vista;
    private final ServicioDAO servicioDAO;

    public ServicioControlador(PanelServicios vista) {
        this.vista = vista;
        this.servicioDAO = new ServicioDAO();
        inicializarEventos();
    }

    private void inicializarEventos() {
        if (vista.getBotonGuardarServicio() != null) {
            vista.getBotonGuardarServicio().addActionListener(e -> guardarServicio());
        }

        if (vista.getBotonModificar() != null) {
            vista.getBotonModificar().addActionListener(e -> modificarServicio());
        }

        if (vista.getBotonEliminar() != null) {
            vista.getBotonEliminar().addActionListener(e -> eliminarServicio());
        }

        if (vista.getTablaServicios() != null) {
            vista.getTablaServicios().getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting() && vista.getFilaSeleccionada() != -1) {
                    int fila = vista.getFilaSeleccionada();
                    String nombre = vista.getModeloTabla().getValueAt(fila, 1).toString();
                    String duracion = vista.getModeloTabla().getValueAt(fila, 2).toString();
                    String precio = vista.getModeloTabla().getValueAt(fila, 3).toString();
                    vista.cargarFormulario(nombre, duracion, precio);
                }
            });
        }
    }

    private void guardarServicio() {
        String nombre = vista.getNombre();
        String duracionStr = vista.getDuracion();
        String precioStr = vista.getPrecio();

        if (nombre.isEmpty() || duracionStr.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Nombre, duración y precio son obligatorios.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int duracion;
        double precio;
        try {
            duracion = Integer.parseInt(duracionStr);
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Duración debe ser un número entero y precio un número decimal.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Servicio nuevo = new Servicio(0, nombre, duracion, precio);
        servicioDAO.guardar(nuevo);

        refrescarTabla();
        vista.limpiarCampos();
        JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                "Servicio guardado con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void modificarServicio() {
        int fila = vista.getFilaSeleccionada();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Seleccioná un servicio de la tabla.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = vista.getNombre();
        String duracionStr = vista.getDuracion();
        String precioStr = vista.getPrecio();

        if (nombre.isEmpty() || duracionStr.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Nombre, duración y precio son obligatorios.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int duracion;
        double precio;
        try {
            duracion = Integer.parseInt(duracionStr);
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Duración debe ser un número entero y precio un número decimal.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) vista.getModeloTabla().getValueAt(fila, 0);
        Servicio existente = servicioDAO.buscarPorId(id);

        if (existente != null) {
            existente.setNombre(nombre);
            existente.setDuracion(duracion);
            existente.setPrecio(precio);
        }

        refrescarTabla();
        vista.limpiarCampos();
    }

    private void eliminarServicio() {
        int fila = vista.getFilaSeleccionada();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Seleccioná un servicio para eliminar.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) vista.getModeloTabla().getValueAt(fila, 0);
        servicioDAO.eliminar(id);

        refrescarTabla();
        vista.limpiarCampos();
    }

    private void refrescarTabla() {
        if (vista.getModeloTabla() != null) {
            vista.getModeloTabla().setRowCount(0);
            List<Servicio> lista = servicioDAO.obtenerTodos();
            for (Servicio s : lista) {
                vista.getModeloTabla().addRow(
                        new Object[]{s.getId(), s.getNombre(), s.getDuracion(), s.getPrecio()});
            }
        }
    }
}
