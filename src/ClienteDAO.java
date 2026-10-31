import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements DAO<Cliente, Long> {

    private Connection obtenerConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/peluqueria?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String usuario = "root";
        String password = "root";
        return DriverManager.getConnection(url, usuario, password);
    }

    @Override
    public void guardar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, apellido, contacto, categoria, promociones) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getContacto());
            pstmt.setString(4, cliente.getCategoria());
            pstmt.setBoolean(5, cliente.isPromociones());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente buscarPorId(Long id) {
        String sql = "SELECT id, nombre, apellido, contacto, categoria, promociones FROM clientes WHERE id = ?";
        Cliente cliente = null;

        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getLong("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("contacto"),
                            rs.getString("categoria"),
                            rs.getBoolean("promociones")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public List<Cliente> obtenerTodos() {
        String sql = "SELECT id, nombre, apellido, contacto, categoria, promociones FROM clientes";
        List<Cliente> lista = new ArrayList<>();

        try (Connection conn = obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("contacto"),
                        rs.getString("categoria"),
                        rs.getBoolean("promociones")
                );
                lista.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, contacto = ?, categoria = ?, promociones = ? WHERE id = ?";

        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getContacto());
            pstmt.setString(4, cliente.getCategoria());
            pstmt.setBoolean(5, cliente.isPromociones());
            pstmt.setLong(6, cliente.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}