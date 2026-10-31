import java.util.List;

public interface DAO<T, ID> {
    void guardar(T entidad);
    T buscarPorId(ID id);
    List<T> obtenerTodos();
    void actualizar(T entidad);
    void eliminar(ID id);
}