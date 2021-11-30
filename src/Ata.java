import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Ata {

	private int codigo;
	private String titulo;
	private String data;
	private String horaInicio;
	private String horaTermino;
	private String pauta;
	private String visibilidade;
	private String estado = "em criação";
	private Emissor emissor;
	private String emitida;
	private List <Participantes> participantes;
	
	public Ata(int codigo, String titulo, String data, String horaInicio, String visibilidade, Emissor emissor) throws SQLException {
		this.codigo = codigo;
		this.titulo = titulo;
		this.data = data;
		this.horaInicio = horaInicio;
		this.visibilidade = visibilidade;
		this.emissor = emissor;
		
		inserirDadosDeIden();
	};

	public Ata(String titulo, String data, String horainicio, String horatermino, String pauta) {
		this.titulo = titulo;
		this.data = data;
		this.horaInicio = horainicio;
		this.horaTermino = horatermino;
		this.pauta = pauta;
	}
	
	public Ata(){};

	public String getTitulo() {
		return titulo;
	}

	public String getData() {
		return data;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraTermino() {
		return horaTermino;
	}

	public String getPauta() {
		return pauta;
	}


	public void setEstado(int codigo, String estado) throws SQLException {
		this.estado = estado;
		this.codigo = codigo;
		alterarEstado();
	}

	public void setHoraTermino(String horaTermino) throws SQLException {
		this.horaTermino = horaTermino;
		inserirHoraDeTermino();
	}

	public void setParticipantes(List<Participantes> participantes2) {
		this.participantes = participantes2;
	}
	
	public List<Participantes> getParticipantes() {
		return participantes;
	}

	public void setDados(int codigo, String pauta) throws SQLException {
		this.pauta = pauta;
		this.codigo = codigo;
		inserirDados();
	}
	public void setAlterar(int codigo, String pauta) throws SQLException {
		this.pauta = pauta;
		this.codigo = codigo;
		alterarDados();
	}

	
	private void inserirDadosDeIden() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();

		String sql = "UPDATE ata SET titulo = ?, data = ?, horaInicio = ?, visibilidade = ? WHERE codigo = ?;";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, titulo);
		stmt.setString(2, data);
		stmt.setString(3, horaInicio);
		stmt.setString(4, visibilidade.toLowerCase());
		stmt.setInt(5, codigo);
		
		stmt.execute();
		
		if (!estado.equalsIgnoreCase("concluida")) {
			emitida = "nao";
			emitir();
		}
		
	};
	
	private void inserirHoraDeTermino() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();
		
		String sql = "UPDATE ata SET horaTermino = (?) WHERE codigo = (?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, horaTermino);
		stmt.setInt(2, codigo);
		
		stmt.execute();
	}

	public void alterarEstado() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();
		
		String sql = "UPDATE ata SET estado = (?) WHERE codigo = (?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, estado.toLowerCase());
		stmt.setInt(2, codigo);
		
		stmt.execute();
		
		if(estado.equalsIgnoreCase("concluida")){
			emitida = "sim";
			emitir();
		} else if (!estado.equalsIgnoreCase("concluida")) {
			emitida = "nao";
			emitir();
		}
		
	}
	
	private void inserirDados() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();
		
		String sql = "UPDATE ata SET pauta = (?) WHERE codigo = (?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, pauta);
		stmt.setInt(2, codigo);
		
		stmt.execute();
	}
	
	private void alterarDados() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();
		
		String sql = "UPDATE ata SET pauta = (?) WHERE codigo = (?) AND estado LIKE 'em revisão'";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, pauta);
		stmt.setInt(2, codigo);
		
		stmt.execute();
	}
	
	private void emitir() throws SQLException {
		Connection conexao = CriaConexaoBd.abrirConexao();
		
		String sql = "UPDATE ata SET emitida = (?) WHERE codigo = (?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, emitida);
		stmt.setInt(2, codigo);
		
		stmt.execute();
	}	
	
	public String toString() {
		return "Titulo: " + titulo + ", Data: " + data + ", Incio: " + horaInicio + ", Termino: " + horaTermino + ", Emissor: " + emissor;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
