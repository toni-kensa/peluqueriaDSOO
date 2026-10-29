import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private final List<Cliente> listaClientes = new ArrayList<>();
    private int contadorId = 1;

    public void guardar(Cliente cliente) {
        cliente.setId(contadorId++);
        listaClientes.add(cliente);
    }

    public List<Cliente> obtenerTodos() {
        return new ArrayList<>(listaClientes);
    }

    public void eliminar(int id) {
        listaClientes.removeIf(c -> c.getId() == id);
    }

    public Cliente buscarPorId(int id) {
        for (Cliente c : listaClientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}