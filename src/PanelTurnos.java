import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelTurnos extends JPanel {

    private JPanel panelPrincipal;
    private JComboBox<String> comboCliente;
    private JComboBox<String> comboServicio;
    private JTextField fechaTX;
    private JTextField horaTX;
    private JComboBox<String> comboEstado;

    private JButton botonReservar;
    private JButton botonModificar;
    private JButton botonCancelar;

    private JLabel turno;
    private JLabel cliente;
    private JLabel servicio;
    private JLabel fecha;
    private JLabel hora;
    private JLabel estado;

    private JTable tablaTurnos;
    private JScrollPane tabla_turnos;
    private JPanel panel_botones;
    private DefaultTableModel modeloTabla;

    public PanelTurnos() {
        this.panelPrincipal = this;
        if (getLayout() == null || getLayout() instanceof FlowLayout) {
            setLayout(new BorderLayout());
        }
        configurarComboBoxes();
        configurarTabla();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    private void configurarComboBoxes() {
        if (comboEstado != null) {
            comboEstado.removeAllItems();
            comboEstado.addItem("Pendiente");
            comboEstado.addItem("Confirmado");
            comboEstado.addItem("Cancelado");
            comboEstado.addItem("Realizado");
        }
    }

    private void configurarTabla() {
        if (tablaTurnos == null) {
            tablaTurnos = new JTable();
        }
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "ID Cliente", "ID Servicio", "Fecha", "Hora", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaTurnos.setModel(modeloTabla);
        if (tabla_turnos != null) {
            tabla_turnos.setViewportView(tablaTurnos);
            tabla_turnos.getViewport().setBackground(Color.WHITE);
        }
    }

    public JComboBox<String> getComboCliente() {
        return comboCliente;
    }

    public JComboBox<String> getComboServicio() {
        return comboServicio;
    }

    public long getClienteIdSeleccionado() {
        try {
            if (comboCliente != null && comboCliente.getSelectedIndex() > 0) {
                String val = comboCliente.getSelectedItem().toString();
                // El formato es "ID: X - Nombre", extraemos la segunda parte (el número exacto)
                String[] partes = val.split(" ");
                if (partes.length > 1) {
                    return Long.parseLong(partes[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public long getServicioIdSeleccionado() {
        try {
            if (comboServicio != null && comboServicio.getSelectedIndex() > 0) {
                String val = comboServicio.getSelectedItem().toString();
                // El formato es "ID: X - Nombre...", extraemos la segunda parte (el número exacto)
                String[] partes = val.split(" ");
                if (partes.length > 1) {
                    return Long.parseLong(partes[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public String getFecha() {
        return fechaTX != null ? fechaTX.getText().trim() : "";
    }

    public String getHora() {
        return horaTX != null ? horaTX.getText().trim() : "";
    }

    public String getEstadoSeleccionado() {
        if (comboEstado == null || comboEstado.getSelectedItem() == null) return "Pendiente";
        return comboEstado.getSelectedItem().toString();
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTable getTablaTurnos() {
        return tablaTurnos;
    }

    public int getFilaSeleccionada() {
        return tablaTurnos != null ? tablaTurnos.getSelectedRow() : -1;
    }

    public JButton getBotonReservar() {
        return botonReservar;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public JButton getBotonCancelar() {
        return botonCancelar;
    }

    public void cargarFormulario(long clienteId, long servicioId, String fecha, String hora, String estado) {
        if (fechaTX != null) fechaTX.setText(fecha);
        if (horaTX != null) horaTX.setText(hora);
        if (comboEstado != null) comboEstado.setSelectedItem(estado);
    }

    public void limpiarCampos() {
        if (comboCliente != null && comboCliente.getItemCount() > 0) comboCliente.setSelectedIndex(0);
        if (comboServicio != null && comboServicio.getItemCount() > 0) comboServicio.setSelectedIndex(0);
        if (fechaTX != null) fechaTX.setText("");
        if (horaTX != null) horaTX.setText("");
        if (comboEstado != null) comboEstado.setSelectedIndex(0);
    }
}