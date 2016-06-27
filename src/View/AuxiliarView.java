package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.AuxiliarDao;
import Model.Auxiliar;
import Util.Conexao;
import Util.TabelaModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class AuxiliarView extends JFrame {

	private static final long serialVersionUID = -4211217570331661490L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtRegistro;
	private JTextField txtDataContratacao;
	private JTextField txtDemissao;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JTextField txtPesquisa;
	private JTextField txtEspecialidade;
	private JTextField txtCodigo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuxiliarView frame = new AuxiliarView();
					frame.setVisible(true);

					Conexao conexaoBanco;
					conexaoBanco = new Conexao();
					conexaoBanco.conexao();
					
					conexaoBanco.executeSQL("select * "
							+ "from auxiliar");
					table_aux.setModel(new TabelaModel(conexaoBanco.resultset));
					table_aux.repaint(table_aux.getRowCount());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	AuxiliarDao dao1 = new AuxiliarDao();
	Auxiliar auxiliar = new Auxiliar();
	Auxiliar obj = new Auxiliar();
	AuxiliarDao dao = new AuxiliarDao();
	private static JTable table_aux;
	
	public AuxiliarView() {

		setBounds(100, 100, 901, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Formul\u00E1rio:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 70, 871, 147);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 32, 46, 14);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setToolTipText("Digite o nome do Auxiliar");
		txtNome.setBounds(85, 29, 274, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setBounds(380, 32, 97, 14);
		panel.add(lblEspecialidade);

		JLabel lblRegistro = new JLabel("Registro:");
		lblRegistro.setBounds(10, 73, 111, 14);
		panel.add(lblRegistro);

		txtRegistro = new JTextField();
		txtRegistro.setToolTipText("Informe o n\u00FAmero de registro profissional dele, se possuir");
		txtRegistro.setBounds(85, 70, 127, 20);
		panel.add(txtRegistro);
		txtRegistro.setColumns(10);

		JLabel lblDataContratao = new JLabel("Data Contrata\u00E7\u00E3o:");
		lblDataContratao.setBounds(244, 73, 157, 14);
		panel.add(lblDataContratao);

		txtDataContratacao = new JTextField();
		txtDataContratacao.setToolTipText("Digite a data de contrata\u00E7\u00E3o do Auxiliar\r\n");
		txtDataContratacao.setBounds(373, 70, 104, 20);
		panel.add(txtDataContratacao);
		txtDataContratacao.setColumns(10);

		JLabel lblDemio = new JLabel("Demiss\u00E3o:");
		lblDemio.setBounds(513, 73, 84, 14);
		panel.add(lblDemio);

		txtDemissao = new JTextField();
		txtDemissao.setToolTipText("Digite a data de demiss\u00E3o, caso ocorrido");
		txtDemissao.setBounds(591, 70, 111, 20);
		panel.add(txtDemissao);
		txtDemissao.setColumns(10);

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(10, 111, 84, 14);
		panel.add(lblEndereo);

		txtEndereco = new JTextField();
		txtEndereco.setToolTipText("Digite o endere\u00E7o em que o Auxiliar reside");
		txtEndereco.setBounds(84, 108, 375, 20);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(478, 111, 157, 14);
		panel.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setToolTipText("Digite o telefone para contato do Auxiliar");
		txtTelefone.setBounds(534, 108, 168, 20);
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(!txtCodigo.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Comando Invalido.");
				}
				//tratando dados: nenhum campo, exceto o de demissão, podem ser nulo.
				else if(txtNome.getText().equals("")|| txtEspecialidade.getText().equals("")||
						txtRegistro.getText().equals("")|| txtEndereco.getText().equals("")||
						txtTelefone.getText().equals("")|| txtDataContratacao.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Campo Obrigatório Não Preenchido.");

					//se nenhum dado obrigatório estiver nulo, o cadastro é realizado
				}else{
					obj.setNome(txtNome.getText());
					obj.setEspecialidade(txtEspecialidade.getText());
					obj.setRegistro(txtRegistro.getText());
					obj.setEndereco(txtEndereco.getText());
					obj.setTelefone(txtTelefone.getText());
					obj.setDataContrato(txtDataContratacao.getText());
					obj.setDataDemissao(txtDemissao.getText());

					dao.Salvar(obj);

					txtNome.setText("");
					txtEspecialidade.setText("");
					txtRegistro.setText("");
					txtEndereco.setText("");
					txtTelefone.setText("");
					txtDataContratacao.setText("");
					txtDemissao.setText("");
					txtCodigo.setText("");
					txtPesquisa.setText("");

					Conexao conexaoBanco;
					try{
						conexaoBanco = new Conexao();
						conexaoBanco.conexao();

						conexaoBanco.executeSQL("select * "
								+ "from auxiliar");
						table_aux.setModel(new TabelaModel(conexaoBanco.resultset));
						table_aux.repaint(table_aux.getRowCount());
						
						
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Erro ao Recarregar Tabela.\n"+e);
					}

				}
			}
		});

		btnCadastrar.setToolTipText("Clique para cadastrar auxiliar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\salve.png"));
		btnCadastrar.setBounds(728, 32, 134, 41);
		panel.add(btnCadastrar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(txtNome.getText().equals("")|| txtEspecialidade.getText().equals("")||
						txtRegistro.getText().equals("")|| txtEndereco.getText().equals("")||
						txtTelefone.getText().equals("")|| txtDataContratacao.getText().equals("") ||
						txtCodigo.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "Edição Invalida");	
				}else{
				

				auxiliar.setCod_auxiliar(Integer.parseInt(txtCodigo.getText()));
				auxiliar.setNome(txtNome.getText());
				auxiliar.setEspecialidade(txtEspecialidade.getText());
				auxiliar.setRegistro(txtRegistro.getText());
				auxiliar.setEndereco(txtEndereco.getText());
				auxiliar.setTelefone(txtTelefone.getText());
				auxiliar.setDataContrato(txtDataContratacao.getText());
				auxiliar.setDataDemissao(txtDemissao.getText());

				dao1.Editar(auxiliar);

				txtNome.setText("");
				txtEspecialidade.setText("");
				txtRegistro.setText("");
				txtEndereco.setText("");
				txtTelefone.setText("");
				txtDataContratacao.setText("");
				txtDemissao.setText("");
				txtCodigo.setText("");
				txtPesquisa.setText("");

				Conexao conexaoBanco;
				try{
					conexaoBanco = new Conexao();
					conexaoBanco.conexao();

					conexaoBanco.executeSQL("select * "
							+ "from auxiliar");
					table_aux.setModel(new TabelaModel(conexaoBanco.resultset));
					table_aux.repaint(table_aux.getRowCount());
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Erro ao Recarregar Tabela.\n"+ex);
				}
				}
			}
		});
		btnEditar.setToolTipText("Clique caso queira modificar os dados do auxiliar");
		btnEditar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\alterar-senha.gif"));
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEditar.setBounds(728, 84, 134, 41);
		panel.add(btnEditar);

		txtEspecialidade = new JTextField();
		txtEspecialidade.setBounds(476, 29, 226, 20);
		panel.add(txtEspecialidade);
		txtEspecialidade.setColumns(10);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(31, 228, 76, 14);
		contentPane.add(lblPesquisar);

		txtPesquisa = new JTextField();
		txtPesquisa.setToolTipText("Digite o auxiliar que deseja buscar");
		txtPesquisa.setBounds(94, 225, 260, 20);
		contentPane.add(txtPesquisa);
		txtPesquisa.setColumns(10);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Auxiliar obj = new Auxiliar();
					AuxiliarDao dao = new AuxiliarDao();
					obj.setPesquisa(txtPesquisa.getText());
					
					Auxiliar model = dao.BuscaAuxiliar(obj);
					txtNome.setText(model.getNome());
					txtRegistro.setText(model.getRegistro());
					txtEndereco.setText(model.getEndereco());
					txtEspecialidade.setText(model.getEspecialidade());
					txtTelefone.setText(model.getTelefone());
					txtDataContratacao.setText(model.getDataContrato());
					txtDemissao.setText(model.getDataDemissao());
					txtCodigo.setText(String.valueOf(model.getCod_auxiliar()));

					Conexao conexaoBanco;
					conexaoBanco = new Conexao();
					conexaoBanco.conexao();
					
					conexaoBanco.executeSQL("SELECT * FROM auxiliar WHERE nome LIKE '%"+obj.getPesquisa()+"%'");
					table_aux.setModel(new TabelaModel(conexaoBanco.resultset));
					table_aux.repaint(table_aux.getRowCount());
					
					
					
					btnEditar.setEnabled(true);
					//btnExcluir.setEnabled(true);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro: "+e1);;
				}
				
			}
		});
		button.setToolTipText("Clique para buscar auxiliar");
		button.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\iconePesquisar.gif"));
		button.setBounds(364, 224, 24, 23);
		contentPane.add(button);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Tabela contendo informa\u00E7\u00F5es dos Auxiliares");
		scrollPane.setBounds(10, 258, 871, 191);
		contentPane.add(scrollPane);
		
		table_aux = new JTable();
		table_aux.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cod.", "nome", "especializacao", "registro", "endereco", "telefone", "contratacao", "demissao"
			}
		));
		
		table_aux.getColumnModel().getColumn(0).setPreferredWidth(35);
		table_aux.getColumnModel().getColumn(1).setPreferredWidth(35);
		table_aux.getColumnModel().getColumn(2).setPreferredWidth(35);
		table_aux.getColumnModel().getColumn(3).setPreferredWidth(35);
		table_aux.getColumnModel().getColumn(4).setPreferredWidth(35);
		table_aux.getColumnModel().getColumn(5).setPreferredWidth(35);
		table_aux.getColumnModel().getColumn(6).setPreferredWidth(35);
		table_aux.getColumnModel().getColumn(7).setPreferredWidth(35);
		
		table_aux = new JTable();
		table_aux.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha_selecionada = table_aux.getSelectedRow();//pegar a linha selecionada
				txtCodigo.setText(table_aux.getValueAt(linha_selecionada, 0).toString());
				txtNome.setText(table_aux.getValueAt(linha_selecionada, 1).toString());
				txtEspecialidade.setText(table_aux.getValueAt(linha_selecionada, 2).toString());
				txtRegistro.setText(table_aux.getValueAt(linha_selecionada, 3).toString());
				txtEndereco.setText(table_aux.getValueAt(linha_selecionada, 4).toString());
				txtTelefone.setText(table_aux.getValueAt(linha_selecionada, 5).toString());
				txtDataContratacao.setText(table_aux.getValueAt(linha_selecionada, 6).toString());
				txtDemissao.setText(table_aux.getValueAt(linha_selecionada, 7).toString());

			}
		});
		
		
		
		scrollPane.setColumnHeaderView(table_aux);

		txtCodigo = new JTextField();
		txtCodigo.setToolTipText("C\u00F3digo do Auxiliar");
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(774, 222, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCd = new JLabel("C\u00F3d.:");
		lblCd.setBounds(739, 228, 46, 14);
		contentPane.add(lblCd);
		
		JLabel lblGerenciamentoDeAuxiliares = new JLabel("Gerenciamento de Auxiliares");
		lblGerenciamentoDeAuxiliares.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblGerenciamentoDeAuxiliares.setBounds(262, 27, 493, 29);
		contentPane.add(lblGerenciamentoDeAuxiliares);
		
		JButton btnCarregarTabela = new JButton("Ver Lista de Auxiliares");
		btnCarregarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try{
					Conexao conexaoBanco;
					conexaoBanco = new Conexao();
					conexaoBanco.conexao();
					
					conexaoBanco.executeSQL("select * "
							+ "from auxiliar");
					table_aux.setModel(new TabelaModel(conexaoBanco.resultset));
					table_aux.repaint(table_aux.getRowCount());
					
				}catch(Exception e1){
					System.out.println(e1);
				}
				
				
			}
		});
		btnCarregarTabela.setBounds(504, 224, 146, 23);
		contentPane.add(btnCarregarTabela);
	}
}




