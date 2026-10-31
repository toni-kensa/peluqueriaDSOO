import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexionBD {

    private static final String URL = valor("PELU_DB_URL",
            "jdbc:mysql://localhost:3306/peluqueria?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
    private static final String USUARIO = valor("PELU_DB_USER", "root");
    private static final String CLAVE = valor("PELU_DB_PASS", "");

    private ConexionBD() {
    }

    private static String valor(String variable, String porDefecto) {
        String v = System.getenv(variable);
        return (v == null || v.isEmpty()) ? porDefecto : v;
    }

    public static Connection obtener() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
