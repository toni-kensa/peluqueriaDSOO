import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO implements DAO<Servicio, Long> {

    // Método auxiliar para obtener la conexión (ajustá la URL, usuario y contraseña según tu configuración)
    private Connection obtenerConexion() throws SQLException {
        // Ejemplo típico con MySQL / MariaDB o SQLite
        String url = "jdbc:mysql://localhost:3306/peluqueria"; 
        String usuario = "root";
        String password = "";
return DriverManager.getConnection(url, usuario, password);
}

    @Override
    public void guardar(Servicio servicio) {
        String sql = "INSERT INTO servicios (nombre, duracion, precio) VALUES (?, ?, ?)";
        
        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, servicio.getNombre());
            pstmt.setInt(2, servicio.getDuracion());
            pstmt.setDouble(3, servicio.getPrecio());
            
            pstmt.executeUpdate();

// Recuperar el ID generado automáticamente por la base de datos
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    servicio.setId(generatedKeys.getInt(1));
}
            }
        } catch (SQLException e) {
            e.printStackTrace();
}
    }

    @Override
    public Servicio buscarPorId(Long id) {
        String sql = "SELECT id, nombre, duracion, precio FROM servicios WHERE id = ?";
        Servicio servicio = null;

        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    servicio = new Servicio(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("duracion"),
                        rs.getDouble("precio")
                    );
}
            }
        } catch (SQLException e) {
            e.printStackTrace();
}
        return servicio;
}

    @Override
    public List<Servicio> obtenerTodos() {
        String sql = "SELECT id, nombre, duracion, precio FROM servicios";
        List<Servicio> lista = new ArrayList<>();

        try (Connection conn = obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Servicio servicio = new Servicio(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("duracion"),
                    rs.getDouble("precio")
                );
                lista.add(servicio);
}
        } catch (SQLException e) {
            e.printStackTrace();
}
        return lista;
}

    @Override
    public actualizar(Servicio servicio) {
        String sql = "UPDATE servicios SET nombre = ?, duracion = ?, precio = ? WHERE id = ?";

        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, servicio.getNombre());
            pstmt.setInt(2, servicio.getDuracion());
            pstmt.setDouble(3, servicio.getPrecio());
            pstmt.setLong(4, servicio.getId());
            
            pstmt.executeUpdate();
} catch (SQLException e) {
            e.printStackTrace();
}
    }

    @Override
    public void eliminar(Long id) {
        String sql = "DELETE FROM servicios WHERE id = ?";

        try (Connection conn = obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
} catch (SQLException e) {
            e.printStackTrace();
}
    }
}