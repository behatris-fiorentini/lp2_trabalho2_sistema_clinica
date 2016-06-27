package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexao {

	String driver= "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/sysclinica" ;
	String usuario = "root";
	String senha = "";

	public Connection con;
	public Statement statement;
	public ResultSet resultset;
	
	
	public boolean conexao(){
		boolean result = true;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, usuario, senha);
		}catch(ClassNotFoundException Driver){
			JOptionPane.showMessageDialog(null, "Driver não encontrado:"+Driver);
			result = false;
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Erro:"+e);
		}
		return result;
	}

	
	public void desconecta(){
		@SuppressWarnings("unused")
		boolean result = true;

		try{
			con.close();
			JOptionPane.showMessageDialog(null, "Banco de Dados Fechado");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Erro: "+e);
			result = false;
		}
	}


	public void executeSQL(String sql){

		try{
			statement = con.createStatement(resultset.TYPE_SCROLL_INSENSITIVE, resultset.CONCUR_READ_ONLY);
			resultset = statement.executeQuery(sql);
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Não foi possivel executar o comando SQL, "+e.getMessage()+""
					+ "O SQL passado foi "+sql);
		}
	}
}