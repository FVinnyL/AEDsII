
public class Usuario {
	public int codigo;
	public String login;
	public String senha;
	public char sexo;
	
	public Usuario() {
		codigo = -1;
		login = senha = "";
		sexo = 'N';
	}
	public Usuario(int codigo, String login, String senha, char sexo) {
		this.codigo = codigo;
		this.login = login;
		this.senha = senha;
		this.sexo = sexo;
	}
	
	public String ToString() {
		return "Cod: " + codigo + " | Login: "
				+ login + " | Senha: "
				+ senha + " | Sexo: "
				+ sexo;
	}
	
}
