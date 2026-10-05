import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;


public class TurnoControlador {

    private PanelTurnos vista;
    private List<Turno> listaTurnos;
    private int contadorId = 1;

    public TurnoControlador(PanelTurnos vista) {
        this.vista = vista;
        this.listaTurnos = new ArrayList<>();
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

        Turno nuevo = new Turno(contadorId++, cliente, servicio, fecha, hora, "Pendiente");
        listaTurnos.add(nuevo);

        actualizarTablaVisual();
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

        for (Turno t : listaTurnos) {
            if (t.getId() == id) {
                if (!cliente.startsWith("--")) t.setCliente(cliente);
                if (!servicio.startsWith("--")) t.setServicio(servicio);
                t.setFecha(fecha);
                t.setHora(hora);
                t.setEstado(estado);
                break;
            }
        }

        actualizarTablaVisual();
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
        for (Turno t : listaTurnos) {
            if (t.getId() == id) {
                t.setEstado("Cancelado");
                break;
            }
        }

        actualizarTablaVisual();
        vista.limpiarCampos();
    }

    private void actualizarTablaVisual() {
        vista.getModeloTabla().setRowCount(0);
        for (Turno t : listaTurnos) {
            vista.getModeloTabla().addRow(
                    new Object[]{t.getId(), t.getCliente(), t.getServicio(),
                            t.getFecha(), t.getHora(), t.getEstado()});
        }
    }
}
