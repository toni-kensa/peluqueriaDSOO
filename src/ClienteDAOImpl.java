import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    private static final String INSERT =
            "INSERT INTO clientes (nombre, apellido, contacto, categoria, promociones) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE clientes SET nombre = ?, apellido = ?, contacto = ?, categoria = ?, promociones = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM clientes WHERE id = ?";
    private static final String SELECT_ID = "SELECT * FROM clientes WHERE id = ?";
    private static final String SELECT_TODOS = "SELECT * FROM clientes ORDER BY id";

    @Override
    public void guardar(Cliente cliente) {
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getContacto());
            ps.setString(4, cliente.getCategoria());
            ps.setBoolean(5, cliente.isPromociones());
            ps.executeUpdate();
            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()) {
                    cliente.setId(claves.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("No se pudo guardar el cliente", e);
        }
    }

    @Override
    public void actualizar(Cliente cliente) {
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(UPDATE)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getContacto());
            ps.setString(4, cliente.getCategoria());
            ps.setBoolean(5, cliente.isPromociones());
            ps.setInt(6, cliente.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("No se pudo actualizar el cliente", e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("No se pudo eliminar el cliente (puede tener turnos asociados)", e);
        }
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(SELECT_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        } catch (SQLException e) {
            throw new DAOException("No se pudo buscar el cliente", e);
        }
    }

    @Override
    public List<Cliente> obtenerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(SELECT_TODOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clientes.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("No se pudieron obtener los clientes", e);
        }
        return clientes;
    }

    private Cliente mapear(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("contacto"),
                rs.getString("categoria"),
                rs.getBoolean("promociones"));
    }
}
