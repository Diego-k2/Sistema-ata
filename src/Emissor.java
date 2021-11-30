import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Emissor {

	private String nome;
	private int cpf;
	private int matricula;
	private String setor;
	private int codAta;
	private int conta;
	
	
	public Emissor(String nome, String setor, int conta) {
		this.nome = nome;
		this.setor = setor;
		this.conta = conta;
	};
	
	public Emissor(String setor, int conta) {
		this.setor = setor;
		this.conta = conta;
	}
	
	public Emissor(String nome, int cpf, int matricula, String setor, int codAta) throws SQLException {
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.setor = setor;
		this.codAta = codAta;
		
		identificarEmissor();
	}
	
	
	public String getNome() {
		return nome;
	}

	public String getSetor() {
		return setor;
	}

	public int getConta() {
		return conta;
	}

	public int getCodAta() {
		return codAta;
	}

	public void setCodAta(int codAta) {
		this.codAta = codAta;
	}
	

	private void identificarEmissor() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();

		String sql1 = "INSERT INTO ata (codigo, emissor) VALUES (?, ?);";
		PreparedStatement stmt1 = conexao.prepareStatement(sql1);
		stmt1.setInt(1, codAta);
		stmt1.setString(2, nome);
		
		stmt1.execute();
		
		String sql = "INSERT INTO emissor (nome, cpf, matricula, setor, ata_fk) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, nome);
		stmt.setInt(2, cpf);
		stmt.setInt(3, matricula);
		stmt.setString(4, setor);
		stmt.setInt(5, codAta);
		
		stmt.execute();
		
	}
	
	public String toString() {
		return nome;
	}
	
	
}
