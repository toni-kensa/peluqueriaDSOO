import java.util.ArrayList;
import java.util.List;

public class TurnoDAO {
    private final List<Turno> listaTurnos = new ArrayList<>();
    private int contadorId = 1;

    public void guardar(Turno turno) {
        turno.setId(contadorId++);
        listaTurnos.add(turno);
    }

    public List<Turno> obtenerTodos() {
        return new ArrayList<>(listaTurnos);
    }

    public Turno buscarPorId(int id) {
        for (Turno t : listaTurnos) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
