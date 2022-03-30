public class Principal {

	public static void main(String[] args) {		
		DAO dao = new DAO();		
		dao.Conectar();
		
		int command = 0;
		do {
			ListCommands();
			command = MyIO.readInt();			
			switch(command) {
			case 1:
				ListUsers(dao.GetUsuarios());
				break;
			case 2:
				dao.InserirUsuario(CreateUser());
				break;
			case 3:
				System.out.println("Codigo: ");
				dao.ExcluirUsuario(MyIO.readInt());
				break;
			case 4:
				dao.AtualizarUsuario(CreateUser());
			}
		}
		while(command != 5);
	}
	
	private static void ListUsers(Usuario[] usuarios) {
		for(int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].ToString());
		}
	}
	
	private static Usuario CreateUser() {
		System.out.println("Codigo: ");
		int codigo =  MyIO.readInt();;
		System.out.println("Login: ");
		String login = MyIO.readLine();
		System.out.println("Senha: ");
		String senha = MyIO.readLine();
		System.out.println("Sexo: ");
		String sexo = MyIO.readLine();
		
		Usuario user = new Usuario(codigo, login, senha, sexo.charAt(0));
		System.out.println(user.ToString());
		return user;
	}
	
	private static void ListCommands() {
		System.out.println("- - - - - - - ");
		System.out.println("1- Listar");
		System.out.println("2- Inserir");
		System.out.println("3- Excluir");
		System.out.println("4- Atualizar");
		System.out.println("5- Sair");
		System.out.println("- - - - - - - ");
	}
}
