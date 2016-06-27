package Control;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.Usuario;
import Util.Conexao;

public class UsuarioDao {

	
Conexao conexao = new Conexao();
	

	public void Salvar(Usuario obj){
		conexao.conexao();
		try {
			PreparedStatement ps = conexao.con.prepareStatement
					("INSERT INTO medico (nome, senha, tipo) "
							+ "VALUES (?, ?, ?)");

			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getSenha());
			ps.setString(3, obj.getPermissao());


			ps.execute();

			JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro SQL: "+e);
		}
	}


	public void Editar(Usuario obj){
		
		conexao.conexao();
		PreparedStatement ps;
		try {
			ps = conexao.con.prepareStatement("UPDATE usuario SET nome=?, senha=?, tipo=? WHERE cod_usuario=?");
			
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getSenha());
			ps.setString(3, obj.getPermissao());
			ps.setInt(4, obj.getId_login());
			
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Editado com Sucesso.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Editar.\n"+e);
			
		}

	}


}
