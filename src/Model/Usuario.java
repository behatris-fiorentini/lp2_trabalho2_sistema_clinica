package Model;

public class Usuario {

	private int id_login;
	private String nome;
	private String senha;
	private String permissao;
	

	public int getId_login() {
		return id_login;
	}
	public void setId_login(int id_login) {
		this.id_login = id_login;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPermissao() {
		return permissao;
	}
	public void setPermissao(Object object) {
		this.permissao = (String) object;
	}
	
}
