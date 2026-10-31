import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {
    private final List<Servicio> listaServicios = new ArrayList<>();
    private int contadorId = 1;

    public void guardar(Servicio servicio) {
        servicio.setId(contadorId++);
        listaServicios.add(servicio);
    }

    public List<Servicio> obtenerTodos() {
        return new ArrayList<>(listaServicios);
    }

    public void eliminar(int id) {
        listaServicios.removeIf(s -> s.getId() == id);
    }

    public Servicio buscarPorId(int id) {
        for (Servicio s : listaServicios) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}
