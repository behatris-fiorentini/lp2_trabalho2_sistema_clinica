package Control;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.Paciente;
import Util.Conexao;

public class PacienteDao {

Conexao conexao = new Conexao();
	

	public void Salvar(Paciente obj){
		conexao.conexao();
		
		try {
			PreparedStatement ps = conexao.con.prepareStatement
					("INSERT INTO paciente (nome, idade, data_nascimento, cpf, rg, "
							+ "endereco, cidade, bairro, numero_residencia, telefone, celular) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps.setString(1, obj.getNome());
			ps.setInt(2, obj.getIdade());
			ps.setString(3, obj.getDataNascimento());
			ps.setString(4, obj.getCpf());
			ps.setString(5, obj.getRg());
			ps.setString(6, obj.getEndereco());
			ps.setString(7, obj.getCidade());
			ps.setString(8, obj.getBairro());
			ps.setInt(9, obj.getNumeroResidencia());
			ps.setString(10, obj.getTelefone());
			ps.setString(11, obj.getCelular());

			ps.execute();

			JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro SQL: "+e);
		}
	}


	public Paciente BuscaPaciente(Paciente obj) throws SQLException{
		conexao.conexao();
		conexao.executeSQL("SELECT * FROM paciente WHERE nome LIKE '%"+obj.getPesquisa()+"%'");
		//pega o primeiro resultado
		try{
			conexao.resultset.first();
			obj.setCod_paciente(conexao.resultset.getInt("cod_paciente"));
			obj.setNome(conexao.resultset.getString("nome"));
			obj.setIdade(conexao.resultset.getInt("idade"));
			obj.setDataNascimento(conexao.resultset.getString("data_nascimento"));
			obj.setCpf(conexao.resultset.getString("cpf"));
			obj.setRg(conexao.resultset.getString("rg"));
			obj.setEndereco(conexao.resultset.getString("endereco"));
			obj.setCidade(conexao.resultset.getString("cidade"));
			obj.setBairro(conexao.resultset.getString("bairro"));
			obj.setNumeroResidencia(conexao.resultset.getInt("numero_residencia"));
			obj.setTelefone(conexao.resultset.getString("telefone"));
			obj.setCelular(conexao.resultset.getString("celular"));
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Nenhum Resultado Encontrado.");
		}
		return obj;
	}



	public void Editar(Paciente obj){
		
		conexao.conexao();
		PreparedStatement ps;
		try {
			ps = conexao.con.prepareStatement("UPDATE paciente SET nome=?, idade=?, data_nascimento=?, cpf=?, rg=?, "
							+ "endereco=?, cidade=?, bairro=?, numero_residencia=?, "
							+ "telefone=?, celular=? WHERE cod_paciente=?");
			
			ps.setString(1, obj.getNome());
			ps.setInt(2, obj.getIdade());
			ps.setString(3, obj.getDataNascimento());
			ps.setString(4, obj.getCpf());
			ps.setString(5, obj.getRg());
			ps.setString(6, obj.getEndereco());
			ps.setString(7, obj.getCidade());
			ps.setString(8, obj.getBairro());
			ps.setInt(9, obj.getNumeroResidencia());
			ps.setString(10, obj.getTelefone());
			ps.setString(11, obj.getCelular());
			ps.setInt(12, obj.getCod_paciente());
			
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Editado com Sucesso.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Editar.\n"+e);
			System.out.println(e.getSQLState()+"\n"+e.getCause());
		}

	}
	
}
