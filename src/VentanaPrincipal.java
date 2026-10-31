import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JPanel panelPrincipal;
    private JPanel panelContenido;
    private JButton botonClientes;
    private JButton botonServicios;
    private JButton botonTurnos;

    private CardLayout cardLayout;

    public VentanaPrincipal() {
        setTitle("Peluquería Santini - Sistema de Gestión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (panelPrincipal != null) {
            setContentPane(panelPrincipal);
        } else {
            panelPrincipal = new JPanel(new BorderLayout());
            setContentPane(panelPrincipal);
        }

        setSize(850, 550);
        setLocationRelativeTo(null);

        if (panelContenido == null) {
            panelContenido = new JPanel(new CardLayout());
            panelPrincipal.add(panelContenido, BorderLayout.CENTER);
        }

        cardLayout = (CardLayout) panelContenido.getLayout();

        PanelCliente panelCliente = new PanelCliente();
        ClienteControlador clienteControlador = new ClienteControlador(panelCliente);
        agregarAlContenido(panelCliente.getPanelPrincipal(), "CLIENTES", "Sección de Clientes");

        try {
            PanelServicios panelServicios = new PanelServicios();
            ServicioControlador servicioControlador = new ServicioControlador(panelServicios);
            agregarAlContenido(panelServicios.getPanelPrincipal(), "SERVICIOS", "Sección de Servicios");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo cargar la sección de Servicios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            agregarAlContenido(null, "SERVICIOS", "Sección de Servicios (En desarrollo)");
        }

        try {
            PanelTurnos panelTurnos = new PanelTurnos();
            TurnoControlador turnoControlador = new TurnoControlador(panelTurnos);
            agregarAlContenido(panelTurnos.getPanelPrincipal(), "TURNOS", "Sección de Turnos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo cargar la sección de Turnos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            agregarAlContenido(null, "TURNOS", "Sección de Turnos (En desarrollo)");
        }

        if (botonClientes != null) botonClientes.addActionListener(e -> cardLayout.show(panelContenido, "CLIENTES"));
        if (botonServicios != null) botonServicios.addActionListener(e -> cardLayout.show(panelContenido, "SERVICIOS"));
        if (botonTurnos != null) botonTurnos.addActionListener(e -> cardLayout.show(panelContenido, "TURNOS"));
    }

    private void agregarAlContenido(JPanel panel, String nombreCard, String textoFallback) {
        if (panel != null) {
            panelContenido.add(panel, nombreCard);
        } else {
            JPanel placeholder = new JPanel(new GridBagLayout());
            JLabel label = new JLabel(textoFallback);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            placeholder.add(label);
            panelContenido.add(placeholder, nombreCard);
        }
    }
}