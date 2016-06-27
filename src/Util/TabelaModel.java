package Util;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.mysql.jdbc.ResultSetMetaData;
/**
 * @author Behatris.Fiorentini
 **/
		
public class TabelaModel extends AbstractTableModel{

	private static final long serialVersionUID = 372453063924534354L;
	private int colunas, linhas;
	private ResultSet resultset;
	private ResultSetMetaData rsMetaData;//consigo pegar e retornar todos os campos


	public TabelaModel(ResultSet resultset) throws SQLException{
		rsMetaData = (ResultSetMetaData) resultset.getMetaData();
		this.resultset = resultset;
		//vai pro ultimo registro
		resultset.last();
		//pega o numero da linha em que se encontra
		linhas = resultset.getRow();		
		//acionar alterações de estrutura no JTable
		fireTableStructureChanged();
		
	}
	/**
	 * @author Behatris.Fiorentini
	 **/
			
	public String getColumnName(int column){
		try{
			return rsMetaData.getColumnName(column+1);
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
			return"";
		}
	}

	@Override
	public int getColumnCount() {
		colunas = 0;
		try{
			colunas = rsMetaData.getColumnCount();
		}catch(SQLException e){
			System.out.println(e);
		}
		return colunas;
	}

	@Override
	public int getRowCount() {
		return linhas;
	}
	/**
	 * @author Behatris.Fiorentini
	 **/
			
	//povoar o JTable
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		try{
			resultset.absolute(rowIndex+1);
			return resultset.getObject(columnIndex+1);
		}catch(SQLException e){
			System.out.println(e);
			return "";
		}
		
	}

}
