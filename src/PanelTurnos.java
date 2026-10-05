import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelTurnos {

    private JPanel panelPrincipal;
    private JComboBox comboCliente;
    private JComboBox comboServicio;
    private JTextField fechaTX;
    private JTextField horaTX;
    private JComboBox comboEstado;

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
        configurarComboBoxes();
        configurarTabla();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    private void configurarComboBoxes() {
        if (comboCliente != null) {
            comboCliente.removeAllItems();
            comboCliente.addItem("-- Seleccionar cliente --");
            comboCliente.addItem("Cliente 1");
            comboCliente.addItem("Cliente 2");
            comboCliente.addItem("Cliente 3");
        }

        if (comboServicio != null) {
            comboServicio.removeAllItems();
            comboServicio.addItem("-- Seleccionar servicio --");
            comboServicio.addItem("Servicio 1");
            comboServicio.addItem("Servicio 2");
            comboServicio.addItem("Servicio 3");
        }

        if (comboEstado != null) {
            comboEstado.removeAllItems();
            comboEstado.addItem("Pendiente");
            comboEstado.addItem("Confirmado");
            comboEstado.addItem("Cancelado");
            comboEstado.addItem("Realizado");
        }
    }

    private void configurarTabla() {
        if (tablaTurnos != null) {
            tablaTurnos.setBackground(Color.WHITE);
            if (tabla_turnos != null) {
                tabla_turnos.getViewport().setBackground(Color.WHITE);
            }

            modeloTabla = new DefaultTableModel(
                    new Object[]{"ID", "Cliente", "Servicio", "Fecha", "Hora", "Estado"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tablaTurnos.setModel(modeloTabla);
        }
    }

    public String getClienteSeleccionado() {
        return comboCliente != null ? comboCliente.getSelectedItem().toString() : "";
    }

    public String getServicioSeleccionado() {
        return comboServicio != null ? comboServicio.getSelectedItem().toString() : "";
    }

    public String getFecha() {
        return fechaTX != null ? fechaTX.getText().trim() : "";
    }

    public String getHora() {
        return horaTX != null ? horaTX.getText().trim() : "";
    }

    public String getEstadoSeleccionado() {
        return comboEstado != null ? comboEstado.getSelectedItem().toString() : "";
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

    public void cargarFormulario(String cliente, String servicio, String fecha, String hora, String estado) {
        if (comboCliente != null) comboCliente.setSelectedItem(cliente);
        if (comboServicio != null) comboServicio.setSelectedItem(servicio);
        if (fechaTX != null) fechaTX.setText(fecha);
        if (horaTX != null) horaTX.setText(hora);
        if (comboEstado != null) comboEstado.setSelectedItem(estado);
    }

    public void limpiarCampos() {
        if (comboCliente != null) comboCliente.setSelectedIndex(0);
        if (comboServicio != null) comboServicio.setSelectedIndex(0);
        if (fechaTX != null) fechaTX.setText("");
        if (horaTX != null) horaTX.setText("");
        if (comboEstado != null) comboEstado.setSelectedIndex(0);
    }
}
