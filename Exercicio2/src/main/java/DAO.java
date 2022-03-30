import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean Conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localHost";
		String myDatabase = "Teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + myDatabase;
		String username = "postgres";
		String password = "2810";
		boolean status = false;
		
		try {
			Class.forName(driverName);
				conexao = DriverManager.getConnection(url,username,password);
				status = conexao == null;
		}
		catch(ClassNotFoundException exc){
			System.err.println("Conexão não efetuada por erro no Driver: " + exc.getMessage());			
		}
		catch(SQLException exc) {
			System.err.println("Conexão não efetuada por erro no SQL: " + exc.getMessage());		
		}
		
		return status;
	}
	
	public boolean Fechar() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;			
		}
		catch(SQLException exc) {
			System.err.println("Conexão não fechada por erro no SQL: " + exc.getMessage());
		}
		
		return status;		
	}
	
	public boolean InserirUsuario(Usuario usuario) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO usuario (codigo, login, senha, sexo) VALUES (" + usuario.codigo + ", '" + usuario.login + "', '" + usuario.senha + "', '" + usuario.sexo + "');");
			st.close();
			status = true;
		}
		catch(SQLException exc) {
			System.out.print(exc.getMessage());
			throw new RuntimeException();
		}
		
		return status;
	}
	
	public boolean AtualizarUsuario(Usuario usuario) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE usuario SET login = '" + usuario.login + "', senha = '" + usuario.senha + "', sexo = '" + usuario.sexo + "' "
					+ "WHERE codigo = " + usuario.codigo;
			st.executeUpdate(sql);
			st.close();
			status = true;
		}
		catch(SQLException exc) {
			System.out.print(exc.getMessage());
			throw new RuntimeException(exc);
		}		
		
		return status;
	}
	
	public boolean ExcluirUsuario(int codigo) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE codigo = '" + codigo + "'");
			st.close();
			status = true;
		}
		catch(SQLException exc) {
			throw new RuntimeException();
		}				
		return status;
	}
	
	public Usuario[] GetUsuarios() {
		Usuario[] usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuario");
			if(rs.next()) {
				rs.last();
				usuarios = new Usuario[rs.getRow()];
				rs.beforeFirst();
				
				for(int i = 0;rs.next(); i++) {
					usuarios[i] = new Usuario(rs.getInt("codigo"), rs.getString("login"),rs.getString("senha"), rs.getString("sexo").charAt(0));
				}
			}
		}
		catch(Exception exc) {
			System.err.println(exc.getMessage());
		}			
		return usuarios;
	}
	
	
}
