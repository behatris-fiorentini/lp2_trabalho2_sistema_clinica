package Control;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.Auxiliar;
import Util.Conexao;

public class AuxiliarDao {



	Conexao conexao = new Conexao();
	

	public void Salvar(Auxiliar obj){
		conexao.conexao();
		try {
			PreparedStatement ps = conexao.con.prepareStatement
					("INSERT INTO auxiliar (nome, especializacao, registro,"
							+ "endereco, telefone, contratacao, demissao) "
							+ "VALUES (?, "
							+ "?, ?, ?, ?, ?, ?)");

			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getEspecialidade());
			ps.setString(3, obj.getRegistro());
			ps.setString(4, obj.getEndereco());
			ps.setString(5, obj.getTelefone());
			ps.setString(6, obj.getDataContrato());
			ps.setString(7, obj.getDataDemissao());

			ps.execute();

			JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro SQL: "+e);
		}
	}


	public Auxiliar BuscaAuxiliar(Auxiliar obj) throws SQLException{
		conexao.conexao();
		conexao.executeSQL("SELECT * FROM auxiliar WHERE nome LIKE '%"+obj.getPesquisa()+"%'");
		//pega o primeiro resultado
		try{
			conexao.resultset.first();
			obj.setCod_auxiliar(conexao.resultset.getInt("cod_auxiliar"));
			obj.setNome(conexao.resultset.getString("nome"));
			obj.setEspecialidade(conexao.resultset.getString("especializacao"));
			obj.setRegistro(conexao.resultset.getString("registro"));
			obj.setEndereco(conexao.resultset.getString("endereco"));
			obj.setTelefone(conexao.resultset.getString("telefone"));
			obj.setDataContrato(conexao.resultset.getString("contratacao"));
			obj.setDataDemissao(conexao.resultset.getString("demissao"));

		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Nenhum Resultado Encontrado.");
		}
		return obj;
	}



	public void Editar(Auxiliar obj){
		
		conexao.conexao();
		PreparedStatement ps;
		try {
			ps = conexao.con.prepareStatement("UPDATE auxiliar SET nome=?,especializacao=?,registro=?,endereco=?,telefone=?,"
					+ "contratacao=?,demissao=? WHERE cod_auxiliar=?");
			
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getEspecialidade());
			ps.setString(3, obj.getRegistro());
			ps.setString(4, obj.getEndereco());
			ps.setString(5, obj.getTelefone());
			ps.setString(6, obj.getDataContrato());
			ps.setString(7, obj.getDataDemissao());
			ps.setInt(8, obj.getCod_auxiliar());
			
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Editado com Sucesso.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Editar.\n"+e);
			System.out.println(e.getSQLState()+"\n"+e.getCause());
		}

	}


}
