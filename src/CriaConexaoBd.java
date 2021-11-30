import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CriaConexaoBd {

	public static Connection abrirConexao() {
		
		try {
			
			final String url = "jdbc:postgresql://localhost:5432/SistemaDeAtas";
			final String user = "postgres";
			final String senha = "9475267380";
			
			return DriverManager.getConnection(url, user, senha);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
}
