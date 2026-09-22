import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelCliente {

    private JPanel panelPrincipal;
    private JTextField nombreTX;
    private JTextField apellidoTX;
    private JTextField contactoTX;

    private JButton botonGuardarCliente;
    private JButton botonModificar;
    private JButton botonEliminar;

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

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    private void configurarTabla() {
        if (tablaClientes != null) {
            tablaClientes.setBackground(Color.WHITE);
            if (tabla_clientes != null) {
                tabla_clientes.getViewport().setBackground(Color.WHITE);
            }

            // Configuramos los encabezados para que la tabla sea visible en la maqueta
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
}