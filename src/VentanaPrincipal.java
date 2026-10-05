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

        // Módulo de Clientes (MVC)
        PanelCliente panelCliente = new PanelCliente();
        ClienteControlador clienteControlador = new ClienteControlador(panelCliente);

        // Módulo de Servicios (MVC)
        PanelServicios panelServicios = new PanelServicios();
        ServicioControlador servicioControlador = new ServicioControlador(panelServicios);

        // Módulo de Turnos (MVC)
        PanelTurnos panelTurnos = new PanelTurnos();
        TurnoControlador turnoControlador = new TurnoControlador(panelTurnos);

        // Cargar las vistas en el CardLayout
        panelContenido.add(panelCliente.getPanelPrincipal(), "CLIENTES");
        panelContenido.add(panelServicios.getPanelPrincipal(), "SERVICIOS");
        panelContenido.add(panelTurnos.getPanelPrincipal(), "TURNOS");

        // Navegación
        botonClientes.addActionListener(e -> cardLayout.show(panelContenido, "CLIENTES"));
        botonServicios.addActionListener(e -> cardLayout.show(panelContenido, "SERVICIOS"));
        botonTurnos.addActionListener(e -> cardLayout.show(panelContenido, "TURNOS"));
    }
}
