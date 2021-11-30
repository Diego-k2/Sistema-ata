import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Funcionario {

	private int codAta;
	
	
	public void setCodAta(int codAta) {
		this.codAta = codAta;
	}

	public void consultarAta() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();
		
		String sql = "SELECT titulo, data, horainicio, horatermino, pauta, emissor FROM ata WHERE codigo = ? AND visibilidade = 'publica' AND emitida = 'sim';";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, codAta);
		
		ResultSet resultado = stmt.executeQuery();
		
		List<Ata> ata = new ArrayList<>();
		
		while(resultado.next()) {
			String titulo = resultado.getString("titulo");
			String data = resultado.getString("Data");
			String inicio = resultado.getString("horainicio");
			String fim = resultado.getString("horatermino");
			String pauta = resultado.getString("pauta");
			
			ata.add(new Ata(titulo, data, inicio, fim, pauta));
		}
		
		for(Ata a: ata) {
			System.out.println("***" + "Titulo da reunião: " + a.getTitulo() + "***");
			System.out.println("***" + "Data da reunião: "+ a.getData() +  "* Hora de Inicio: " + a.getHoraInicio() + "* Hora do termino: " + a.getHoraTermino() + "***");
			System.out.println("***" + "Pautas tratadas: " + a.getPauta() + "***");
		}
		
		
	}
}
