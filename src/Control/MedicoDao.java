package Control;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.Medico;
import Util.Conexao;

public class MedicoDao {

	
	

	Conexao conexao = new Conexao();
	

	public void Salvar(Medico medMod){
		conexao.conexao();
		try {
			PreparedStatement ps = conexao.con.prepareStatement
					("INSERT INTO medico (nome, especializacao, crm,"
							+ "endereco, telefone, contratacao, demissao) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)");

			ps.setString(1, medMod.getNome());
			ps.setString(2, medMod.getEspecialidade());
			ps.setString(3, medMod.getCrm());
			ps.setString(4, medMod.getEndereco());
			ps.setString(5, medMod.getTelefone());
			ps.setString(6, medMod.getDataContrato());
			ps.setString(7, medMod.getDataDemissao());

			ps.execute();

			JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro SQL: "+e);
		}
	}


	public Medico BuscaMedico(Medico beans) throws SQLException{
		conexao.conexao();
		conexao.executeSQL("SELECT * FROM medico WHERE nome LIKE '%"+beans.getPesquisa()+"%'");
		//pega o primeiro resultado
		try{
			conexao.resultset.first();
			beans.setCod_medido(conexao.resultset.getInt("cod_medico"));
			beans.setNome(conexao.resultset.getString("nome"));
			beans.setEspecialidade(conexao.resultset.getString("especializacao"));
			beans.setCrm(conexao.resultset.getString("crm"));
			beans.setEndereco(conexao.resultset.getString("endereco"));
			beans.setTelefone(conexao.resultset.getString("telefone"));
			beans.setDataContrato(conexao.resultset.getString("contratacao"));
			beans.setDataDemissao(conexao.resultset.getString("demissao"));

		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Nenhum Resultado Encontrado.");
		}
		return beans;
	}



	public void Editar(Medico medMod){
		
		conexao.conexao();
		PreparedStatement ps;
		try {
			ps = conexao.con.prepareStatement("UPDATE medico SET nome=?,especializacao=?,crm=?,endereco=?,telefone=?,"
					+ "contratacao=?,demissao=? WHERE cod_medico=?");
			
			ps.setString(1, medMod.getNome());
			ps.setString(2, medMod.getEspecialidade());
			ps.setString(3, medMod.getCrm());
			ps.setString(4, medMod.getEndereco());
			ps.setString(5, medMod.getTelefone());
			ps.setString(6, medMod.getDataContrato());
			ps.setString(7, medMod.getDataDemissao());
			ps.setInt(8, medMod.getCod_medido());
			
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Editado com Sucesso.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Editar.\n"+e);
			System.out.println(e.getSQLState()+"\n"+e.getCause());
		}

	}


}
