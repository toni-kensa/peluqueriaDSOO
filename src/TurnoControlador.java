import javax.swing.*;
import java.util.List;

public class TurnoControlador {

    private final PanelTurnos vista;
    private final TurnoDAO turnoDAO;
    private final ClienteDAO clienteDAO;
    private final ServicioDAO servicioDAO;

    public TurnoControlador(PanelTurnos vista) {
        this.vista = vista;
        this.turnoDAO = new TurnoDAO();
        this.clienteDAO = new ClienteDAO();
        this.servicioDAO = new ServicioDAO();

        poblarCombos();
        inicializarEventos();
        refrescarTabla();
    }

    private void poblarCombos() {
        if (vista.getComboCliente() != null) {
            vista.getComboCliente().removeAllItems();
            vista.getComboCliente().addItem("-- Seleccionar cliente --");
            List<Cliente> clientes = clienteDAO.obtenerTodos();
            for (Cliente c : clientes) {
                vista.getComboCliente().addItem("ID: " + c.getId() + " - " + c.getNombre() + " " + c.getApellido());
            }
        }

        if (vista.getComboServicio() != null) {
            vista.getComboServicio().removeAllItems();
            vista.getComboServicio().addItem("-- Seleccionar servicio --");
            List<Servicio> servicios = servicioDAO.obtenerTodos();
            for (Servicio s : servicios) {
                vista.getComboServicio().addItem("ID: " + s.getId() + " - " + s.getNombre() + " ($" + s.getPrecio() + ")");
            }
        }
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
                    long clienteId = Long.parseLong(vista.getModeloTabla().getValueAt(fila, 1).toString());
                    long servicioId = Long.parseLong(vista.getModeloTabla().getValueAt(fila, 2).toString());
                    String fecha = vista.getModeloTabla().getValueAt(fila, 3).toString();
                    String hora = vista.getModeloTabla().getValueAt(fila, 4).toString();
                    String estado = vista.getModeloTabla().getValueAt(fila, 5).toString();
                    vista.cargarFormulario(clienteId, servicioId, fecha, hora, estado);
                }
            });
        }
    }

    private void reservarTurno() {
        long clienteId = vista.getClienteIdSeleccionado();
        long servicioId = vista.getServicioIdSeleccionado();
        String fecha = vista.getFecha();
        String hora = vista.getHora();

        if (clienteId <= 0 || servicioId <= 0) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Por favor, seleccioná un cliente y un servicio válidos de la lista.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (fecha.isEmpty() || hora.isEmpty()) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Fecha y hora son obligatorias.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Turno nuevo = new Turno(0L, clienteId, servicioId, fecha, hora, "Pendiente");
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

        long clienteId = vista.getClienteIdSeleccionado();
        long servicioId = vista.getServicioIdSeleccionado();
        String fecha = vista.getFecha();
        String hora = vista.getHora();
        String estado = vista.getEstadoSeleccionado();

        if (fecha.isEmpty() || hora.isEmpty()) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Fecha y hora son obligatorias.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        long id = Long.parseLong(vista.getModeloTabla().getValueAt(fila, 0).toString());
        Turno existente = turnoDAO.buscarPorId(id);

        if (existente != null) {
            if (clienteId > 0) existente.setClienteId(clienteId);
            if (servicioId > 0) existente.setServicioId(servicioId);
            existente.setFecha(fecha);
            existente.setHora(hora);
            existente.setEstado(estado);
            turnoDAO.actualizar(existente);
        }

        refrescarTabla();
        vista.limpiarCampos();
        JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                "Turno modificado con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cancelarTurno() {
        int fila = vista.getFilaSeleccionada();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                    "Seleccioná un turno para cancelar.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        long id = Long.parseLong(vista.getModeloTabla().getValueAt(fila, 0).toString());
        Turno existente = turnoDAO.buscarPorId(id);

        if (existente != null) {
            existente.setEstado("Cancelado");
            turnoDAO.actualizar(existente);
        }

        refrescarTabla();
        vista.limpiarCampos();
        JOptionPane.showMessageDialog(vista.getPanelPrincipal(),
                "Turno cancelado con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void refrescarTabla() {
        poblarCombos();
        if (vista.getModeloTabla() != null) {
            vista.getModeloTabla().setRowCount(0);
            List<Turno> lista = turnoDAO.obtenerTodos();
            for (Turno t : lista) {
                vista.getModeloTabla().addRow(
                        new Object[]{t.getId(), t.getClienteId(), t.getServicioId(),
                                t.getFecha(), t.getHora(), t.getEstado()});
            }
        }
    }
}