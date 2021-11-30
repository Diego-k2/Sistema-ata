
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interface {

	public static void main(String[] args) throws SQLException {
		
		String usuario;	
		
	
		Scanner teclado = new Scanner(System.in);
		
		System.out.print("Digite o Usuario: ");
		usuario = teclado.next();
				

		if(usuario.equalsIgnoreCase("administrador")) {
	
			Administrador adm = new Administrador();
			System.out.println("Coloque o codigo do que voce quer fazer: ");
			System.out.println("1 - Relat�rio emiss�o por funcionario");
			System.out.println("2 - Relat�rio emiss�o por setor");
			System.out.println("3 - Consulta tempo de reuni�o");
			
			int admop = teclado.nextInt();
			System.out.print("Defina o metodo de saida dos dados: ");
			adm.setMetodoDeSaida(teclado.next());
			
			switch (admop) {
			case 1:
				System.out.println("");
				adm.consultarAtasPorFuncionario();
				break;
			case 2:
				System.out.println("");
				adm.consultaAtasPorSetor();
				break;
			case 3:
				System.out.println("");
				adm.consultarDura��oDasReunioes();
				break;			
			default: 	
				System.out.println("");
				System.out.println("Op��o invalida");
			}
			
		} else if(usuario.equalsIgnoreCase("emissor")){ 
			
			System.out.println("Digite oque voc� deseja fazer:");
			System.out.println("1 - Criar nova ata");
			System.out.println("2 - Para efetuar altera��es na ata");
			System.out.println("3 - Para efetuar altera��o no estado da ata");
			
			int emiop = teclado.nextInt();
			if(emiop == 1) {
				System.out.print("Entre com seu nome: ");
				String nome = teclado.next();
				System.out.print("Entre com seu cpf: ");
				int cpf = teclado.nextInt();
				System.out.print("Entre com sua matricula: ");
				int matricula = teclado.nextInt();
				System.out.print("Entre com seu setor: ");
				String setor = teclado.next();
				System.out.print("Entre com o codigo da reuni�o: ");
				int codigo = teclado.nextInt();
				
				Emissor emissor = new Emissor(nome, cpf, matricula, setor, codigo);
				
				
				System.out.print("Digite o titulo da reuni�o: ");
				String titulo = teclado.next();
				System.out.print("Digite a data da reuni�o: ");
				String data = teclado.next();
				System.out.print("Digite a hora de inicio da reuni�o: ");
				String hora = teclado.next();
				System.out.print("Digite o padr�o de visibilidade: ");
				String visi = teclado.next();
				
				Ata ata = new Ata(codigo, titulo, data, hora, visi, emissor);
				
				int parti = 0;
				while (parti != 3) {
					System.out.println("Digite 1 para participantes internos; ");
					System.out.println("Digite 2 para participantes externos; ");
					System.out.println("Digite 3 finalizar partipante; ");
					parti = teclado.nextInt();
					
					List<Participantes> participantes = new ArrayList<>();
					
					if(parti == 1) {
						System.out.print("Digite o nome do participante: ");
						String nomePar = teclado.next();
						System.out.print("Digite o cpf do participante: ");
						int cpfP = teclado.nextInt();
						System.out.print("Digite a matricula do participante: ");
						int matriculaP = teclado.nextInt();
						System.out.print("Digite o setor do participante: ");
						String setorP = teclado.next();
						participantes.add(new Participantes(nomePar, cpfP, matriculaP, setorP, codigo));	
					} 
					else if (parti == 2) {
						System.out.print("Digite o nome do participante: ");
						String nomePar = teclado.next();
						System.out.print("Digite o nome da empresa do participante: ");
						String nomeEmpre = teclado.next();
						System.out.print("Digite o email do participante (n�o obrigat�rio): ");
						String email = teclado.next();
						
						participantes.add(new Participantes(nomePar, nomeEmpre, email, codigo));
					}
			
				}
					
				@SuppressWarnings("resource")
				Scanner teclado2 = new Scanner(System.in);
				System.out.println("Digite as pautas aqui: ");
				String pauta = teclado2.nextLine();
				ata.setDados(codigo, pauta);
				
				
				
				System.out.println("Digite a hora do termino da reuni�o: ");
				String horaT = teclado.next();
				ata.setHoraTermino(horaT);
				ata.setEstado(codigo, "em revis�o");
				
				System.out.println("Ata finalizada com sucesso !!");
				
			} else if (emiop == 2) {
				Ata a = new Ata();
	
				System.out.println("Digite o codigo da ata que deseja alterar: ");
				int codigo = teclado.nextInt();
				System.out.println("Digite as altera��es que voc� deseja: ");
				String pauta = teclado.next();
				a.setAlterar(codigo, pauta);
				
			} else if (emiop == 3) {
				Ata a = new Ata();
				
				System.out.println("Digite o codigo da ata que voce deseja alterar o estado:");
				int codigo = teclado.nextInt();
				
				System.out.println("Digite 1 para 'em processo de conclus�o'");
				System.out.println("Digite 2 para 'concluida'");
				int estado = teclado.nextInt();
				if(estado == 1) {
					a.setEstado(codigo, "em processo de conclus�o");
				} else if (estado == 2) {
					a.setEstado(codigo, "concluida");
				}	
			}
			
		}else if(usuario.equalsIgnoreCase("participantes")){ 
			Participantes participante = new Participantes();
			System.out.println("Digite o c�digo da ata que voc� participou e deseja vizulizar: ");
			participante.setCodAta(teclado.nextInt());
			participante.consultarAta();
		}
		else if(usuario.equalsIgnoreCase("funcionario")){ 
			Funcionario funcionario = new Funcionario();
			System.out.println("Digite o c�digo da ata que voc� deseja vizulizar: ");
			funcionario.setCodAta(teclado.nextInt());
			funcionario.consultarAta();
		}
		else{
			
			System.out.println("Usuario n�o cadastrado");
		} 
		
		teclado.close();
		
		
	}
	
	
}



