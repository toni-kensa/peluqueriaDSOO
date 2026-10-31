import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAO implements DAO<Turno, Long> {

    private Connection obtenerConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/peluqueria?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String usuario = "root";
        String password = "root";
        return DriverManager.getConnection(url, usuario, password);
    }

    @Override
    public void guardar(Turno turno) {
        String sql = "INSERT INTO turnos (cliente_id, servicio_id, fecha, hora, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, turno.getClienteId());
            pstmt.setLong(2, turno.getServicioId());
            pstmt.setString(3, turno.getFecha());
            pstmt.setString(4, turno.getHora());
            pstmt.setString(5, turno.getEstado());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    turno.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Turno buscarPorId(Long id) {
        String sql = "SELECT id, cliente_id, servicio_id, fecha, hora, estado FROM turnos WHERE id = ?";
        Turno turno = null;

        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    turno = new Turno(
                            rs.getLong("id"),
                            rs.getLong("cliente_id"),
                            rs.getLong("servicio_id"),
                            rs.getString("fecha"),
                            rs.getString("hora"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turno;
    }

    @Override
    public List<Turno> obtenerTodos() {
        String sql = "SELECT id, cliente_id, servicio_id, fecha, hora, estado FROM turnos";
        List<Turno> lista = new ArrayList<>();

        try (Connection conn = obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Turno turno = new Turno(
                        rs.getLong("id"),
                        rs.getLong("cliente_id"),
                        rs.getLong("servicio_id"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("estado")
                );
                lista.add(turno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(Turno turno) {
        String sql = "UPDATE turnos SET cliente_id = ?, servicio_id = ?, fecha = ?, hora = ?, estado = ? WHERE id = ?";

        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, turno.getClienteId());
            pstmt.setLong(2, turno.getServicioId());
            pstmt.setString(3, turno.getFecha());
            pstmt.setString(4, turno.getHora());
            pstmt.setString(5, turno.getEstado());
            pstmt.setLong(6, turno.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        String sql = "DELETE FROM turnos WHERE id = ?";

        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}