package Control;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.Consulta;
import Util.Conexao;

public class ConsultaDao {
Conexao conexao = new Conexao();
	




	public void Salvar(Consulta obj){
		conexao.conexao();
		try {
			PreparedStatement ps = conexao.con.prepareStatement
					("INSERT INTO consulta (paciente, procedimento, "
							+ "dia, mes, ano, hora, valor, pagamento, status_pagamento,"
							+ "descricao_pagamento, status_consulta, valor_total, medico) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps.setString(1, obj.getPaciente());
			ps.setString(2, obj.getProcedimento());
			ps.setInt(3, obj.getDia());
			ps.setInt(4, obj.getMes());
			ps.setInt(5, obj.getAno());
			ps.setString(6, obj.getHora());
			ps.setFloat(7, obj.getValor());
			ps.setString(8, obj.getPagamento());
			ps.setString(9, obj.getStatusPagamento());
			ps.setString(10, obj.getDescricaoPagamento());
			ps.setString(11, obj.getStatusConsulta());
			ps.setFloat(12, obj.getValorTotal());
			ps.setString(13, obj.getMedico());


			ps.execute();

			JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro SQL: "+e);
		}
	}


	public Consulta BuscaConsulta(Consulta obj) throws SQLException{
		conexao.conexao();
		conexao.executeSQL("SELECT * FROM consulta WHERE nome LIKE '%"+obj.getPesquisa()+"%'");
		//pega o primeiro resultado
		try{
			
			conexao.resultset.first();
			
			obj.setCod_consulta(conexao.resultset.getInt("cod_consulta"));
			obj.setPaciente(conexao.resultset.getString("paciente"));
			obj.setProcedimento(conexao.resultset.getString("procedimento"));
			obj.setDia(conexao.resultset.getInt("dia"));
			obj.setMes(conexao.resultset.getInt("mes"));
			obj.setAno(conexao.resultset.getInt("ano"));
			obj.setHora(conexao.resultset.getString("hora"));
			obj.setValor(conexao.resultset.getFloat("valor"));
			obj.setPagamento(conexao.resultset.getString("pagamento"));
			obj.setStatusPagamento(conexao.resultset.getString("status_pagamento"));
			obj.setDescricaoPagamento(conexao.resultset.getString("descricao_pagamento"));
			obj.setStatusConsulta(conexao.resultset.getString("status_consulta"));
			obj.setValorTotal(conexao.resultset.getFloat("valor_total"));
			obj.setMedico(conexao.resultset.getString("medico"));

		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Nenhum Resultado Encontrado.");
		}
		return obj;
	}



	public void Editar(Consulta obj){
		
		conexao.conexao();
		PreparedStatement ps;
		try {
			ps = conexao.con.prepareStatement("UPDATE consulta SET paciente=?, procedimento=?, "
							+ "dia=?, mes=?, ano=?, hora=?, valor=?, pagamento=?, status_pagamento=?,"
							+ "descricao_pagamento=?, status_consulta=?, valor_total=?, medico=? WHERE cod_consulta=?");
			
			ps.setString(1, obj.getPaciente());
			ps.setString(2, obj.getProcedimento());
			ps.setInt(3, obj.getDia());
			ps.setInt(4, obj.getMes());
			ps.setInt(5, obj.getAno());
			ps.setString(6, obj.getHora());
			ps.setFloat(7, obj.getValor());
			ps.setString(8, obj.getPagamento());
			ps.setString(9, obj.getStatusPagamento());
			ps.setString(10, obj.getDescricaoPagamento());
			ps.setString(11, obj.getStatusConsulta());
			ps.setFloat(12, obj.getValorTotal());
			ps.setString(13, obj.getMedico());
			ps.setInt(14, obj.getCod_consulta());
			
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Editado com Sucesso.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Editar.\n"+e);
			System.out.println(e.getSQLState()+"\n"+e.getCause());
		}

	}

	public void Excluir(Consulta obj){
		conexao.conexao();
		PreparedStatement ps;
		try {
			ps = conexao.con.prepareStatement("DELETE FROM consulta WHERE cod_consulta=?");
			
			ps.setInt(1, obj.getCod_consulta());
			ps.execute();
			
			JOptionPane.showMessageDialog(null, "Excluido com Sucesso..");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro ao Excluir.");
		}
		
	}

}

