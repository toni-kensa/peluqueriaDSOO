import javax.swing.*;

<<<<<<< HEAD
public class Main {

    public static void main(String[] args) {
=======

public class Main {

    public static void main(String[] args) {

>>>>>>> master
        SwingUtilities.invokeLater(() -> {
            try {
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
<<<<<<< HEAD
                JOptionPane.showMessageDialog(null, "Error al iniciar: " + e.getMessage());
=======
                JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage());
>>>>>>> master
            }
        });
    }
}