import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Administrador {

	private String metodoDeSaida;
	
	public void setMetodoDeSaida(String metodoDeSaida) {
		this.metodoDeSaida = metodoDeSaida;
	}
	

	public void consultaAtasPorSetor() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();
		
			Statement stmt = conexao.createStatement();
			
			String sql = "SELECT * FROM porSetor;";
			
			ResultSet resultado = stmt.executeQuery(sql);
			
			List<Emissor> emissor = new ArrayList<>();
			
			while(resultado.next()) {
				String setor = resultado.getString("setor");
				int conta = resultado.getInt("total");
				emissor.add(new Emissor(setor, conta));
				
			}
			
			for(Emissor e: emissor) {
				System.out.println( "|"+ "Total de atas: " + e.getConta() + ", Setor " + e.getSetor() + "|");
			}
		System.out.println("***" + metodoDeSaida + "***");
	}
	
	public void consultarAtasPorFuncionario() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();
		
		Statement stmt = conexao.createStatement();
		
		String sql = "SELECT * FROM porFunc;";
		
		ResultSet resultado = stmt.executeQuery(sql);
		
		List<Emissor> emissor = new ArrayList<>();
		
		while(resultado.next()) {
			String nome = resultado.getString("nome");
			String setor = resultado.getString("setor");
			int conta = resultado.getInt("total");
			emissor.add(new Emissor(nome, setor, conta));
			
		}
		
		for(Emissor e: emissor) {
			System.out.println( "|"+ "Total de atas: " + e.getConta() + ", Emissor: " + e.getNome() + ", Setor do emissor: " + e.getSetor() + "|");
		}
		System.out.println("***" + metodoDeSaida + "****");
		
	}
	
	public String consultarDuraçãoDasReunioes() {
		//TODO fazer busca do tempo de reuniões 
		return null;
	}
	
}
