import javax.swing.JOptionPane;
import java.util.List;

// conecta la vista (PanelTurnos) con los datos del Turno (modelo)
public class TurnoControlador {

    private final PanelTurnos vista;
    private final TurnoDAO turnoDAO;

    public TurnoControlador(PanelTurnos vista) {
        this.vista = vista;
        this.turnoDAO = new TurnoDAO();
        inicializarEventos();
    }

    private void inicializarEventos() {
        if (vista.getBotonReservar() != null) {
            vista.getBotonReservar().addActionListener(e -> reservarTurno());
        }

        if (vista.getBotonModificar() != null) {
            vista.getBotonModificar().addActionListener(e -> modificarTurno());
        }

        if (vista.getBotonCancelar() != null) {
            vista.getBotonCancelar().addActionListener(e -> cancelarTurno());
        }

        if (vista.getTablaTurnos() != null) {
            vista.getTablaTurnos().getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting() && vista.getFilaSeleccionada() != -1) {
                    int fila = vista.getFilaSeleccionada();
                    String cliente = vista.getModeloTabla().getValueAt(fila, 1).toString();
                    String servicio = vista.getModeloTabla().getValueAt(fila, 2).toString();
                    String fecha = vista.getModeloTabla().getValueAt(fila, 3).toString();
                    String hora = vista.getModeloTabla().getValueAt(fila, 4).toString();
                    String estado = vista.getModeloTabla().getValueAt(fila, 5).toString();
                    vista.cargarFormulario(cliente, servicio, fecha, hora, estado);
                }
            });
        }
    }

    private void reservarTurno() {
        String cliente = vista.getClienteSeleccionado();
        String servicio = vista.getServicioSeleccionado();
        String fecha = vista.getFecha();
        String hora = vista.getHora();

        if (cliente.startsWith("--") || servicio.startsWith("--")) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Seleccioná un cliente y un servicio.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (fecha.isEmpty() || hora.isEmpty()) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Fecha y hora son obligatorias.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Turno nuevo = new Turno(0, cliente, servicio, fecha, hora, "Pendiente");
        turnoDAO.guardar(nuevo);

        refrescarTabla();
        vista.limpiarCampos();
        JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                "Turno reservado con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void modificarTurno() {
        int fila = vista.getFilaSeleccionada();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Seleccioná un turno de la tabla.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cliente = vista.getClienteSeleccionado();
        String servicio = vista.getServicioSeleccionado();
        String fecha = vista.getFecha();
        String hora = vista.getHora();
        String estado = vista.getEstadoSeleccionado();

        if (fecha.isEmpty() || hora.isEmpty()) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Fecha y hora son obligatorias.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) vista.getModeloTabla().getValueAt(fila, 0);
        Turno existente = turnoDAO.buscarPorId(id);

        if (existente != null) {
            if (!cliente.startsWith("--")) existente.setCliente(cliente);
            if (!servicio.startsWith("--")) existente.setServicio(servicio);
            existente.setFecha(fecha);
            existente.setHora(hora);
            existente.setEstado(estado);
        }

        refrescarTabla();
        vista.limpiarCampos();
    }

    private void cancelarTurno() {
        int fila = vista.getFilaSeleccionada();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Seleccioná un turno para cancelar.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) vista.getModeloTabla().getValueAt(fila, 0);
        Turno existente = turnoDAO.buscarPorId(id);

        if (existente != null) {
            existente.setEstado("Cancelado");
        }

        refrescarTabla();
        vista.limpiarCampos();
    }

    private void refrescarTabla() {
        if (vista.getModeloTabla() != null) {
            vista.getModeloTabla().setRowCount(0);
            List<Turno> lista = turnoDAO.obtenerTodos();
            for (Turno t : lista) {
                vista.getModeloTabla().addRow(
                        new Object[]{t.getId(), t.getCliente(), t.getServicio(),
                                t.getFecha(), t.getHora(), t.getEstado()});
            }
        }
    }
}
