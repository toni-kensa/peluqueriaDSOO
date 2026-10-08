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

        setContentPane(panelPrincipal);
        setSize(850, 550);
        setLocationRelativeTo(null);

        cardLayout = (CardLayout) panelContenido.getLayout();


        PanelCliente panelCliente = new PanelCliente();


        ClienteControlador clienteControlador = new ClienteControlador(panelCliente);


        JPanel panelServicios = crearPanelPlaceholder("Sección de Servicios");
        JPanel panelTurnos = crearPanelPlaceholder("Sección de Turnos");


        panelContenido.add(panelCliente.getPanelPrincipal(), "CLIENTES");
        panelContenido.add(panelServicios, "SERVICIOS");
        panelContenido.add(panelTurnos, "TURNOS");


        botonClientes.addActionListener(e -> cardLayout.show(panelContenido, "CLIENTES"));
        botonServicios.addActionListener(e -> cardLayout.show(panelContenido, "SERVICIOS"));
        botonTurnos.addActionListener(e -> cardLayout.show(panelContenido, "TURNOS"));
    }

    private JPanel crearPanelPlaceholder(String texto) {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label);
        return panel;
    }
}