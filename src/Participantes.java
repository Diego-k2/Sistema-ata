import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Participantes {

	private String nome;
	private int cpf;
	private int matricula;
	private String setor;
	private String email;
	private String nomeEmpresa;
	private int codAta;
	
	public Participantes() {}
	
	public Participantes(String nome, int cpf, int matricula, String setor, int codAta) throws SQLException { //participante interno 
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.setor = setor;
		this.codAta = codAta;
		
		cadastrarParticipanteIN();
	}
	

	public Participantes(String nome, String nomeEmpresa, String email, int codAta) throws SQLException { //cadastrar participante externo
		this.nome = nome;
		this.nomeEmpresa = nomeEmpresa;
		this.email = email;
		this.codAta = codAta;
		
		cadastrarParticipanteEX();
	}
	
	
	public void setCodAta(int codAta) {
		this.codAta = codAta;
	}

	
	private void cadastrarParticipanteIN() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();
		
		String sql = "INSERT INTO participanteInternos (nome, cpf, matricula, setor, codata_fk) VALUES (?,?,?,?,?);";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, nome);
		stmt.setInt(2, cpf);
		stmt.setInt(3, matricula);
		stmt.setString(4, setor);
		stmt.setInt(5, codAta);
		
		stmt.execute();
		
	}
	
	private void cadastrarParticipanteEX() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();
		
			String sql = "INSERT INTO participanteExterno (nome, empresa, email, codata_fk) VALUES (?, ?, ?, ?);";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, nomeEmpresa);
			stmt.setString(3, email);
			stmt.setInt(4, codAta);
			
			stmt.execute();	
	}

	public void consultarAta() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();
		
		String sql = "SELECT titulo, data, horainicio, horatermino, pauta, emissor FROM ata WHERE codigo = ? AND emitida = 'sim';";
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











