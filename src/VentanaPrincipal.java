import javax.swing.*;
import java.awt.*;

<<<<<<< HEAD
=======

>>>>>>> master
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

<<<<<<< HEAD
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
=======

        PanelCliente panelCliente = new PanelCliente();


        ClienteControlador clienteControlador = new ClienteControlador(panelCliente);


        JPanel panelServicios = crearPanelPlaceholder("Sección de Servicios");
        JPanel panelTurnos = crearPanelPlaceholder("Sección de Turnos");


        panelContenido.add(panelCliente.getPanelPrincipal(), "CLIENTES");
        panelContenido.add(panelServicios, "SERVICIOS");
        panelContenido.add(panelTurnos, "TURNOS");


>>>>>>> master
        botonClientes.addActionListener(e -> cardLayout.show(panelContenido, "CLIENTES"));
        botonServicios.addActionListener(e -> cardLayout.show(panelContenido, "SERVICIOS"));
        botonTurnos.addActionListener(e -> cardLayout.show(panelContenido, "TURNOS"));
    }
<<<<<<< HEAD
}
=======

    private JPanel crearPanelPlaceholder(String texto) {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label);
        return panel;
    }
}
>>>>>>> master
