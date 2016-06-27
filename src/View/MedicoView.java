package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.MedicoDao;
import Model.Medico;
import Util.Conexao;
import Util.TabelaModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MedicoView extends JFrame {

	private static final long serialVersionUID = 1295957673129516374L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCrm;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JTextField txtContratacao;
	private JTextField txtDemissao;
	private static JTable table_medico;
	private JTextField txtPesquisa;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicoView frame = new MedicoView();
					frame.setVisible(true);
					Conexao conexaoBanco;

					conexaoBanco = new Conexao();
					conexaoBanco.conexao();

					//chama produto
					conexaoBanco.executeSQL("select * "
							+ "from medico");
					table_medico.setModel(new TabelaModel(conexaoBanco.resultset));
					table_medico.repaint(table_medico.getRowCount());
				}catch(Exception e){
					System.out.println("ERRO: "+e);
				}
			}
		});
	}



	MedicoDao dao = new MedicoDao();
	Conexao conexao = new Conexao();
	private JTextField txtEspecialidade;
	private JTextField txtCodigo;

	public MedicoView() {
		
		
		try {
			Conexao conexaoBanco;

			conexaoBanco = new Conexao();
			conexaoBanco.conexao();

			//chama produto
			conexaoBanco.executeSQL("select * "
					+ "from medico");
			table_medico.setModel(new TabelaModel(conexaoBanco.resultset));
			table_medico.repaint(table_medico.getRowCount());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "ERRO: "+e);
		}

		setTitle("Gerenciamento de M\u00E9dicos");
		setBounds(100, 100, 837, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Formul\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(9, 76, 802, 141);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 24, 46, 14);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setToolTipText("Digite o nome do m\u00E9dico");
		txtNome.setBounds(48, 21, 251, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setBounds(311, 24, 119, 14);
		panel.add(lblEspecialidade);

		JLabel lblCrm = new JLabel("CRM:");
		lblCrm.setBounds(10, 64, 46, 14);
		panel.add(lblCrm);

		txtCrm = new JTextField();
		txtCrm.setToolTipText("Digite o n\u00FAmero do CRM do m\u00E9dico");
		txtCrm.setBounds(48, 61, 163, 20);
		panel.add(txtCrm);
		txtCrm.setColumns(10);

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(224, 64, 75, 14);
		panel.add(lblEndereo);

		txtEndereco = new JTextField();
		txtEndereco.setToolTipText("Digite o endere\u00E7o de residencia do m\u00E9dico");
		txtEndereco.setBounds(302, 61, 315, 20);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblTelefone = new JLabel("Tel.:");
		lblTelefone.setBounds(10, 100, 46, 14);
		panel.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setToolTipText("Digite um telefone para contato \r\nExemplo: 027 99999-9999 ou \r\n027 3729-9999");
		txtTelefone.setBounds(48, 97, 163, 20);
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblContratao = new JLabel("Contrata\u00E7\u00E3o:");
		lblContratao.setBounds(224, 100, 75, 14);
		panel.add(lblContratao);

		JLabel lblDemisso = new JLabel("Demiss\u00E3o:");
		lblDemisso.setBounds(441, 100, 67, 14);
		panel.add(lblDemisso);

		txtContratacao = new JTextField();
		txtContratacao.setToolTipText("Digite a data em que o m\u00E9dico foi contratado");
		txtContratacao.setBounds(302, 97, 113, 20);
		panel.add(txtContratacao);
		txtContratacao.setColumns(10);

		txtDemissao = new JTextField();
		txtDemissao.setToolTipText("Digite a data que o m\u00E9dico se desvinculou da clinica");
		txtDemissao.setBounds(503, 97, 114, 20);
		panel.add(txtDemissao);
		txtDemissao.setColumns(10);





		//DANDO FUNCIONALIDADE AO BOTÃO DE SALVAR
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(!txtCodigo.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Comando Invalido.");
				}
				//tratando dados: nenhum campo, exceto o de demissão, podem ser nulo.
				else if(txtNome.getText().equals("")|| txtEspecialidade.getText().equals("")||
						txtCrm.getText().equals("")|| txtEndereco.getText().equals("")||
						txtTelefone.getText().equals("")|| txtContratacao.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Campo Obrigatório Não Preenchido.");

					//se nenhum dado obrigatório estiver nulo, o cadastro é realizado
				}else{
					Medico beans = new Medico();
					beans.setNome(txtNome.getText());
					beans.setEspecialidade(txtEspecialidade.getText());
					beans.setCrm(txtCrm.getText());
					beans.setEndereco(txtEndereco.getText());
					beans.setTelefone(txtTelefone.getText());
					beans.setDataContrato(txtContratacao.getText());
					beans.setDataDemissao(txtDemissao.getText());

					dao.Salvar(beans);

					//limpa campos após cadastro
					txtNome.setText("");
					txtEspecialidade.setText("");
					txtCrm.setText("");
					txtEndereco.setText("");
					txtTelefone.setText("");
					txtContratacao.setText("");
					txtDemissao.setText("");

					Conexao conexaoBanco;
					try{
						conexaoBanco = new Conexao();
						conexaoBanco.conexao();

						//chama produto
						conexaoBanco.executeSQL("select * "
								+ "from medico");
						table_medico.setModel(new TabelaModel(conexaoBanco.resultset));
						table_medico.repaint(table_medico.getRowCount());
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "ERRO: "+e);
					}


				}

			}
		});

		btnCadastrar.setToolTipText("Bot\u00E3o de Cadastro");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\salve.png"));
		btnCadastrar.setBounds(637, 22, 145, 41);
		panel.add(btnCadastrar);


		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Medico beans = new Medico();
				MedicoDao dao1 = new MedicoDao();

				beans.setCod_medido(Integer.parseInt(txtCodigo.getText()));
				beans.setNome(txtNome.getText());
				beans.setEspecialidade(txtEspecialidade.getText());
				beans.setCrm(txtCrm.getText());
				beans.setEndereco(txtEndereco.getText());
				beans.setTelefone(txtTelefone.getText());
				beans.setDataContrato(txtContratacao.getText());
				beans.setDataDemissao(txtDemissao.getText());

				dao1.Editar(beans);

				txtNome.setText("");
				txtEspecialidade.setText("");
				txtCrm.setText("");
				txtEndereco.setText("");
				txtTelefone.setText("");
				txtContratacao.setText("");
				txtDemissao.setText("");
				txtCodigo.setText("");
				txtPesquisa.setText("");


				Conexao conexaoBanco;
				try{
					conexaoBanco = new Conexao();
					conexaoBanco.conexao();

					//chama produto
					conexaoBanco.executeSQL("select * "
							+ "from medico");
					table_medico.setModel(new TabelaModel(conexaoBanco.resultset));
					table_medico.repaint(table_medico.getRowCount());
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "ERRO: "+e);
				}
			}
		});
		btnEditar.setToolTipText("Altera as Informa\u00E7\u00F5es");
		btnEditar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\alterar-senha.gif"));
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEditar.setBounds(637, 73, 145, 41);
		panel.add(btnEditar);

		txtEspecialidade = new JTextField();
		txtEspecialidade.setBounds(394, 21, 223, 20);
		panel.add(txtEspecialidade);
		txtEspecialidade.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 286, 801, 234);
		contentPane.add(scrollPane);

		table_medico = new JTable();
		table_medico.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Cod", "Nome", "Especializacao", "Crm", "Endereco", "Telefone", "Contratacao", "Demissao"
				}
				));

		table_medico.getColumnModel().getColumn(0).setPreferredWidth(15);
		table_medico.getColumnModel().getColumn(1).setPreferredWidth(76);
		table_medico.getColumnModel().getColumn(2).setPreferredWidth(15);
		table_medico.getColumnModel().getColumn(3).setPreferredWidth(32);
		table_medico.getColumnModel().getColumn(4).setMinWidth(10);
		table_medico.getColumnModel().getColumn(5).setPreferredWidth(15);
		table_medico.getColumnModel().getColumn(6).setPreferredWidth(36);
		table_medico.getColumnModel().getColumn(7).setPreferredWidth(36);


		table_medico = new JTable();
		table_medico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha_selecionada = table_medico.getSelectedRow();//pegar a linha selecionada
				txtCodigo.setText(table_medico.getValueAt(linha_selecionada, 0).toString());
				txtNome.setText(table_medico.getValueAt(linha_selecionada, 1).toString());
				txtEspecialidade.setText(table_medico.getValueAt(linha_selecionada, 2).toString());
				txtCrm.setText(table_medico.getValueAt(linha_selecionada, 3).toString());
				txtEndereco.setText(table_medico.getValueAt(linha_selecionada, 4).toString());
				txtTelefone.setText(table_medico.getValueAt(linha_selecionada, 5).toString());
				txtContratacao.setText(table_medico.getValueAt(linha_selecionada, 6).toString());
				txtDemissao.setText(table_medico.getValueAt(linha_selecionada, 7).toString());

			}
		});



		scrollPane.setColumnHeaderView(table_medico);

		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(106, 239, 244, 20);
		contentPane.add(txtPesquisa);
		txtPesquisa.setColumns(10);

		JLabel lblPesquisa = new JLabel("Pesquisa:");
		lblPesquisa.setBounds(32, 242, 110, 14);
		contentPane.add(lblPesquisa);

		//DA FUNÇÃO AO BOTÃO PESQUISAR
		JButton btnPesquisa = new JButton("");
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Medico beans = new Medico();
					beans.setPesquisa(txtPesquisa.getText());
					Medico model = dao.BuscaMedico(beans);
					
					txtNome.setText(model.getNome());
					txtCrm.setText(model.getCrm());
					txtEndereco.setText(model.getEndereco());
					txtEspecialidade.setText(model.getEspecialidade());
					txtTelefone.setText(model.getTelefone());
					txtContratacao.setText(model.getDataContrato());
					txtDemissao.setText(model.getDataDemissao());
					txtCodigo.setText(String.valueOf(model.getCod_medido()));
					
				
						Conexao conexaoBanco;

						conexaoBanco = new Conexao();
						conexaoBanco.conexao();

						//chama produto
						conexaoBanco.executeSQL("SELECT * FROM medico WHERE nome LIKE '%"+beans.getPesquisa()+"%'");
						table_medico.setModel(new TabelaModel(conexaoBanco.resultset));
						table_medico.repaint(table_medico.getRowCount());
					
					btnEditar.setEnabled(true);
					//btnExcluir.setEnabled(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		});
		btnPesquisa.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\iconePesquisar.gif"));
		btnPesquisa.setBounds(367, 236, 27, 23);
		contentPane.add(btnPesquisa);
		
				txtCodigo = new JTextField();
				txtCodigo.setBounds(713, 239, 86, 20);
				contentPane.add(txtCodigo);
				txtCodigo.setColumns(10);
				txtCodigo.setEditable(false);
				
				JLabel lblCod = new JLabel("Cod.:");
				lblCod.setBounds(675, 242, 46, 14);
				contentPane.add(lblCod);
				
				JLabel lblCliqueParaEditar = new JLabel("Clique para editar.");
				lblCliqueParaEditar.setBounds(354, 523, 268, 14);
				contentPane.add(lblCliqueParaEditar);
				
				JLabel lblGerenciamentoDeMdicos = new JLabel("Gerenciamento de M\u00E9dicos");
				lblGerenciamentoDeMdicos.setFont(new Font("Tahoma", Font.BOLD, 25));
				lblGerenciamentoDeMdicos.setBounds(227, 24, 430, 29);
				contentPane.add(lblGerenciamentoDeMdicos);
				
				JButton btnCarregarListaDe = new JButton("Carregar Lista de M\u00E9dicos");
				btnCarregarListaDe.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try{
							Conexao conexaoBanco;
							conexaoBanco = new Conexao();
							conexaoBanco.conexao();

							//chama produto
							conexaoBanco.executeSQL("select * "
									+ "from medico");
							table_medico.setModel(new TabelaModel(conexaoBanco.resultset));
							table_medico.repaint(table_medico.getRowCount());
						}catch(Exception e2){
							System.out.println(e2);
						}
						
						
					}
				});
				btnCarregarListaDe.setBounds(429, 238, 228, 23);
				contentPane.add(btnCarregarListaDe);
	}
}
