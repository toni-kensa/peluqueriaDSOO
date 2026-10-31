import java.util.List;

public interface DAO<T, ID> {

    void guardar(T entidad);

    void actualizar(T entidad);

    void eliminar(ID id);

    T buscarPorId(ID id);

    List<T> obtenerTodos();
}
