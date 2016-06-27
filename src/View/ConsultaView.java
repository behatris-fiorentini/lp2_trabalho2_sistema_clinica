package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.ConsultaDao;
import Control.MedicoDao;
import Control.PacienteDao;
import Model.Consulta;
import Model.Medico;
import Model.Paciente;
import Util.Conexao;
import Util.TabelaModel;

import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/*cod_consulta,paciente, procedimento, dia, mes, ano, hora, valor, pagamento, statusPagamento,
				descricaoPagamento, statusConsulta, valorTotal*/

public class ConsultaView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeCliente;
	private JTextField txtDia;
	private JTextField txtHora;
	private JTextField txtValorProce;
	private JTextField txtStatusPagamento;
	private JTextField txtDescricao;
	private JTextField txtStatusConsulta;
	private JTextField txtValorTotal;
	private JTextField txtPesquisarConsulta;
	private JTextField txtProcedimento;
	private JTextField txtMes;
	private JTextField txtAno;
	private JTextField txtTipoPagamento;
	private JTextField txtPesquNomeMedico;
	private JTextField txtCodigoConsulta;
	private static JTable table_pa;
	private static JTable table_med;
	private static JTable table_con;




	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Conexao conexaoBanco;
				try {
					ConsultaView frame = new ConsultaView();
					frame.setVisible(true);

					conexaoBanco = new Conexao();
					conexaoBanco.conexao();

					//CHAMA TABELA DE PACIENTES
					conexaoBanco.executeSQL("select cod_paciente, nome "
							+ "from paciente");
					table_pa.setModel(new TabelaModel(conexaoBanco.resultset));
					table_pa.repaint(table_pa.getRowCount());

					//CHAMA TABELA DE MÉDICOS
					conexaoBanco.executeSQL("select cod_medico, nome, especializacao "
							+ "from medico");
					table_med.setModel(new TabelaModel(conexaoBanco.resultset));
					table_med.repaint(table_med.getRowCount());

					//CHAMA A TABELA CONSULTA

					conexaoBanco.executeSQL("select * "
							+ "from consulta");
					table_con.setModel(new TabelaModel(conexaoBanco.resultset));
					table_con.repaint(table_con.getRowCount());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaView() {
		setTitle("Gerenciamento de Consultas");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 199);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblOmLabel = new JLabel("Nome:");
		lblOmLabel.setBounds(21, 21, 46, 14);
		panel.add(lblOmLabel);

		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(61, 18, 281, 20);
		panel.add(txtNomeCliente);
		txtNomeCliente.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Escolha o Cliente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(10, 75, 394, 113);
		panel.add(scrollPane);

		//CONFIGURAÇÕES DA TABELA DE PACIENTES ////////////////////////////////////
		table_pa = new JTable();
		table_pa.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3digo", "Nome"
				}
				));
		//insere dados na tebela da view
		table_pa.getColumnModel().getColumn(0).setPreferredWidth(15);
		table_pa.getColumnModel().getColumn(0).setPreferredWidth(70);

		table_pa = new JTable();
		table_pa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha_selecionada = table_pa.getSelectedRow();//pegar a linha selecionada
				txtNomeCliente.setText(table_pa.getValueAt(linha_selecionada, 1).toString());
			}
		});

		scrollPane.setColumnHeaderView(table_pa);	
		//////////////////////////////////////////////////////////////////////////////////


		JButton buttonPesquiCliente = new JButton("");
		buttonPesquiCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Paciente beans = new Paciente();
					PacienteDao dao = new PacienteDao();
					beans.setPesquisa(txtNomeCliente.getText());
					Paciente model = dao.BuscaPaciente(beans);

					txtNomeCliente.setText(String.valueOf(model.getNome()));

					Conexao conexaoBanco;
					conexaoBanco = new Conexao();
					conexaoBanco.conexao();

					//CHAMA TABELA DE PACIENTES
					conexaoBanco.executeSQL("SELECT * FROM paciente WHERE nome LIKE '%"+beans.getPesquisa()+"%'");
					table_pa.setModel(new TabelaModel(conexaoBanco.resultset));
					table_pa.repaint(table_pa.getRowCount());

					//btnExcluir.setEnabled(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}				
			}
		});



		buttonPesquiCliente.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\iconePesquisar.gif"));
		buttonPesquiCliente.setBounds(349, 17, 26, 23);
		panel.add(buttonPesquiCliente);

		JButton btnCarregarTabela = new JButton("Carregar Tabela");
		btnCarregarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Conexao conexaoBanco;
					conexaoBanco = new Conexao();
					conexaoBanco.conexao();

					//CHAMA TABELA DE PACIENTES
					conexaoBanco.executeSQL("select cod_paciente, nome "
							+ "from paciente");
					table_pa.setModel(new TabelaModel(conexaoBanco.resultset));
					table_pa.repaint(table_pa.getRowCount());
				}catch(Exception e3){
					System.out.println(e3);
				}
			}
		});
		btnCarregarTabela.setBounds(96, 49, 220, 20);
		panel.add(btnCarregarTabela);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Detalhes:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 221, 414, 96);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblProcedimento = new JLabel("Procedimento:");
		lblProcedimento.setBounds(10, 24, 85, 14);
		panel_1.add(lblProcedimento);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(10, 59, 46, 14);
		panel_1.add(lblData);

		txtDia = new JTextField();
		txtDia.setBounds(47, 56, 31, 20);
		panel_1.add(txtDia);
		txtDia.setColumns(10);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(172, 59, 46, 14);
		panel_1.add(lblHora);

		txtHora = new JTextField();
		txtHora.setBounds(213, 56, 77, 20);
		panel_1.add(txtHora);
		txtHora.setColumns(10);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(300, 59, 46, 14);
		panel_1.add(lblValor);

		txtValorProce = new JTextField();
		txtValorProce.setBounds(340, 56, 64, 20);
		panel_1.add(txtValorProce);
		txtValorProce.setColumns(10);

		txtProcedimento = new JTextField();
		txtProcedimento.setToolTipText("Ex.: Consulta, Raio x, Eletro");
		txtProcedimento.setBounds(108, 21, 296, 20);
		panel_1.add(txtProcedimento);
		txtProcedimento.setColumns(10);

		txtMes = new JTextField();
		txtMes.setBounds(82, 56, 31, 20);
		panel_1.add(txtMes);
		txtMes.setColumns(10);

		txtAno = new JTextField();
		txtAno.setBounds(116, 56, 46, 20);
		panel_1.add(txtAno);
		txtAno.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Pagamento:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 328, 414, 101);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento:");
		lblFormaDePagamento.setBounds(10, 30, 148, 14);
		panel_2.add(lblFormaDePagamento);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 70, 46, 14);
		panel_2.add(lblStatus);

		txtStatusPagamento = new JTextField();
		txtStatusPagamento.setBounds(51, 67, 139, 20);
		panel_2.add(txtStatusPagamento);
		txtStatusPagamento.setColumns(10);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(200, 73, 70, 14);
		panel_2.add(lblDescrio);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(254, 70, 150, 20);
		panel_2.add(txtDescricao);
		txtDescricao.setColumns(10);

		txtTipoPagamento = new JTextField();
		txtTipoPagamento.setBounds(149, 27, 255, 20);
		panel_2.add(txtTipoPagamento);
		txtTipoPagamento.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panel_3.setBounds(10, 440, 414, 81);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblStatusConsulta = new JLabel("Status Consulta:");
		lblStatusConsulta.setBounds(10, 24, 92, 14);
		panel_3.add(lblStatusConsulta);

		txtStatusConsulta = new JTextField();
		txtStatusConsulta.setBounds(110, 21, 134, 20);
		panel_3.add(txtStatusConsulta);
		txtStatusConsulta.setColumns(10);

		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setBounds(254, 24, 71, 14);
		panel_3.add(lblValorTotal);

		txtValorTotal = new JTextField();
		txtValorTotal.setBounds(318, 21, 86, 20);
		panel_3.add(txtValorTotal);
		txtValorTotal.setColumns(10);

		txtCodigoConsulta = new JTextField();
		txtCodigoConsulta.setEditable(false);
		txtCodigoConsulta.setBounds(209, 50, 86, 20);
		panel_3.add(txtCodigoConsulta);
		txtCodigoConsulta.setColumns(10);

		JLabel lblCdigoConsulta = new JLabel("C\u00F3digo Consulta:");
		lblCdigoConsulta.setBounds(107, 52, 92, 14);
		panel_3.add(lblCdigoConsulta);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Consulta obj = new Consulta();
				ConsultaDao dao = new ConsultaDao();

				obj.setPaciente(txtNomeCliente.getText());
				obj.setProcedimento(txtProcedimento.getText());
				obj.setDia(Integer.parseInt(txtDia.getText()));
				obj.setMes(Integer.parseInt(txtMes.getText()));
				obj.setAno(Integer.parseInt(txtAno.getText()));
				obj.setHora(txtHora.getText());
				obj.setValor(Float.parseFloat(txtValorProce.getText()));
				obj.setPagamento(txtTipoPagamento.getText());
				obj.setStatusPagamento(txtStatusPagamento.getText());
				obj.setDescricaoPagamento(txtDescricao.getText());
				obj.setStatusConsulta(txtStatusConsulta.getText());
				obj.setValorTotal(Float.parseFloat(txtValorTotal.getText()));
				obj.setMedico(txtPesquNomeMedico.getText());
				obj.setCod_consulta(Integer.parseInt(txtCodigoConsulta.getText()));

				dao.Editar(obj);


				//limpa campos após cadastro
				txtNomeCliente.setText("");
				txtProcedimento.setText("");
				txtDia.setText("");
				txtMes.setText("");
				txtAno.setText("");
				txtHora.setText("");
				txtValorProce.setText("");
				txtTipoPagamento.setText("");
				txtStatusPagamento.setText("");
				txtStatusConsulta.setText("");
				txtValorTotal.setText("");
				txtPesquNomeMedico.setText("");
				txtCodigoConsulta.setText("");
				txtDescricao.setText("");


				Conexao conexaoBanco;
				try{
					conexaoBanco = new Conexao();
					conexaoBanco.conexao();

					conexaoBanco.executeSQL("select * "
							+ "from consulta");
					table_con.setModel(new TabelaModel(conexaoBanco.resultset));
					table_con.repaint(table_con.getRowCount());

				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "ERRO: "+e);
				}
			}
		});
		btnEditar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\alterar-senha.gif"));
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEditar.setBounds(151, 532, 126, 37);
		contentPane.add(btnEditar);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(!txtCodigoConsulta.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Comando Invalido.");
				}
				//tratando dados: nenhum campo, exceto o de demissão, podem ser nulo.
				else if(txtNomeCliente.getText().equals("")|| txtProcedimento.getText().equals("")||
						txtDia.getText().equals("")|| txtMes.getText().equals("")||
						txtAno.getText().equals("")|| txtValorProce.getText().equals("") ||
						txtHora.getText().equals("") || txtTipoPagamento.getText().equals("") ||
						txtValorTotal.getText().equals("") || txtDescricao.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Campo Obrigatório Não Preenchido.");

					//se nenhum dado obrigatório estiver nulo, o cadastro é realizado
				}else{
					Consulta obj = new Consulta();
					ConsultaDao dao = new ConsultaDao();

					obj.setPaciente(txtNomeCliente.getText());
					obj.setProcedimento(txtProcedimento.getText());
					obj.setDia(Integer.parseInt(txtDia.getText()));
					obj.setMes(Integer.parseInt(txtMes.getText()));
					obj.setAno(Integer.parseInt(txtAno.getText()));
					obj.setHora(txtHora.getText());
					obj.setValor(Float.parseFloat(txtValorProce.getText()));
					obj.setPagamento(txtTipoPagamento.getText());
					obj.setStatusPagamento(txtStatusPagamento.getText());
					obj.setDescricaoPagamento(txtDescricao.getText());
					obj.setStatusConsulta(txtStatusConsulta.getText());
					obj.setValorTotal(Float.parseFloat(txtValorTotal.getText()));
					obj.setMedico(txtPesquNomeMedico.getText());

					dao.Salvar(obj);


					//limpa campos após cadastro
					txtNomeCliente.setText("");
					txtProcedimento.setText("");
					txtDia.setText("");
					txtMes.setText("");
					txtAno.setText("");
					txtHora.setText("");
					txtValorProce.setText("");
					txtTipoPagamento.setText("");
					txtStatusPagamento.setText("");
					txtStatusConsulta.setText("");
					txtValorTotal.setText("");
					txtPesquNomeMedico.setText("");
					txtCodigoConsulta.setText("");
					txtDescricao.setText("");
					


					Conexao conexaoBanco;
					try{
						conexaoBanco = new Conexao();
						conexaoBanco.conexao();


						conexaoBanco.executeSQL("select * "
								+ "from consulta");
						table_con.setModel(new TabelaModel(conexaoBanco.resultset));
						table_con.repaint(table_con.getRowCount());

					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "ERRO: "+e);
					}


				}

			}
		});


		btnCadastrar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\salve.png"));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrar.setBounds(10, 532, 135, 37);
		contentPane.add(btnCadastrar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(434, 331, 395, 238);
		contentPane.add(scrollPane_1);

		table_con = new JTable();
		table_con.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Cod.", "paciente", "procedimento", "dia", "mes", "ano", "hora", "valor", "pagamento", "sta.pagamento", "dsc_pagamento,", "status_consulta", "Val.Total", "Med."
				}
				));
		table_con.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_con.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_con.getColumnModel().getColumn(2).setPreferredWidth(50);
		table_con.getColumnModel().getColumn(3).setPreferredWidth(20);
		table_con.getColumnModel().getColumn(4).setPreferredWidth(20);
		table_con.getColumnModel().getColumn(5).setPreferredWidth(25);
		table_con.getColumnModel().getColumn(6).setPreferredWidth(30);
		table_con.getColumnModel().getColumn(7).setPreferredWidth(30);
		table_con.getColumnModel().getColumn(8).setPreferredWidth(30);
		table_con.getColumnModel().getColumn(9).setPreferredWidth(40);
		table_con.getColumnModel().getColumn(10).setPreferredWidth(40);
		table_con.getColumnModel().getColumn(11).setPreferredWidth(40);
		table_con.getColumnModel().getColumn(12).setPreferredWidth(40);
		table_con.getColumnModel().getColumn(13).setPreferredWidth(50);

		table_con = new JTable();
		table_con.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha_selecionada = table_con.getSelectedRow();//pegar a linha selecionada

				txtCodigoConsulta.setText(table_con.getValueAt(linha_selecionada, 0).toString());
				txtNomeCliente.setText(table_con.getValueAt(linha_selecionada, 1).toString());
				txtProcedimento.setText(table_con.getValueAt(linha_selecionada, 2).toString());
				txtDia.setText(table_con.getValueAt(linha_selecionada, 3).toString());
				txtMes.setText(table_con.getValueAt(linha_selecionada, 4).toString());
				txtAno.setText(table_con.getValueAt(linha_selecionada, 5).toString());
				txtHora.setText(table_con.getValueAt(linha_selecionada, 6).toString());
				txtValorProce.setText(table_con.getValueAt(linha_selecionada, 7).toString());
				txtTipoPagamento.setText(table_con.getValueAt(linha_selecionada, 8).toString());
				txtStatusPagamento.setText(table_con.getValueAt(linha_selecionada, 9).toString());
				txtDescricao.setText(table_con.getValueAt(linha_selecionada, 10).toString());
				txtStatusConsulta.setText(table_con.getValueAt(linha_selecionada, 11).toString());
				txtValorTotal.setText(table_con.getValueAt(linha_selecionada, 12).toString());
				txtPesquNomeMedico.setText(table_con.getValueAt(linha_selecionada, 13).toString());

			}
		});

		scrollPane_1.setColumnHeaderView(table_con);

		///////////////////////////////////////////////////////////



		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(435, 266, 60, 14);
		contentPane.add(lblPesquisar);

		txtPesquisarConsulta = new JTextField();
		txtPesquisarConsulta.setToolTipText("Digite o nome do paciente");
		txtPesquisarConsulta.setBounds(494, 263, 297, 20);
		contentPane.add(txtPesquisarConsulta);
		txtPesquisarConsulta.setColumns(10);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					Consulta beans = new Consulta();
					ConsultaDao dao = new ConsultaDao();
					beans.setPesquisa(txtNomeCliente.getText());
					Consulta model = dao.BuscaConsulta(beans);


					txtNomeCliente.setText(String.valueOf(model.getPaciente()));
					txtProcedimento.setText(String.valueOf(model.getProcedimento()));
					txtDia.setText(String.valueOf(model.getDia()));
					txtMes.setText(String.valueOf(model.getMes()));
					txtAno.setText(String.valueOf(model.getAno()));
					txtHora.setText(String.valueOf(model.getHora()));
					txtValorProce.setText(String.valueOf(model.getValor()));
					txtTipoPagamento.setText(String.valueOf(model.getPagamento()));
					txtStatusPagamento.setText(String.valueOf(model.getStatusPagamento()));
					txtDescricao.setText(String.valueOf(model.getDescricaoPagamento()));
					txtStatusConsulta.setText(String.valueOf(model.getStatusConsulta()));
					txtValorTotal.setText(String.valueOf(model.getValorTotal()));
					txtPesquNomeMedico.setText(String.valueOf(model.getMedico()));
					txtCodigoConsulta.setText(String.valueOf(model.getCod_consulta()));

					Conexao conexaoBanco;
					conexaoBanco = new Conexao();
					conexaoBanco.conexao();

					//CHAMA A TABELA CONSULTA

					conexaoBanco.executeSQL("SELECT * FROM auxiliar WHERE nome LIKE '%"+beans.getPesquisa()+"%'");
					table_con.setModel(new TabelaModel(conexaoBanco.resultset));
					table_con.repaint(table_con.getRowCount());


				} catch (SQLException e1) {
					e1.printStackTrace();
				}	


			}
		});
		btnPesquisar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\iconePesquisar.gif"));
		btnPesquisar.setBounds(801, 262, 28, 23);
		contentPane.add(btnPesquisar);

		JButton btnTodos = new JButton("Carregar Tabela de Consultas");
		btnTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
				Conexao conexaoBanco;
				conexaoBanco = new Conexao();
				conexaoBanco.conexao();

				conexaoBanco.executeSQL("select * "
						+ "from consulta");
				table_con.setModel(new TabelaModel(conexaoBanco.resultset));
				table_con.repaint(table_con.getRowCount());
				}catch(Exception e5){
					System.out.println(e5);
				}
				
			}
		});
		btnTodos.setToolTipText("Busca todas as consultas");
		btnTodos.setBounds(504, 294, 235, 23);
		contentPane.add(btnTodos);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Escolha o M\u00E9dico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(445, 11, 384, 245);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		txtPesquNomeMedico = new JTextField();
		txtPesquNomeMedico.setBounds(49, 26, 282, 20);
		panel_4.add(txtPesquNomeMedico);
		txtPesquNomeMedico.setColumns(10);

		JLabel lblBusca = new JLabel("Nome:");
		lblBusca.setBounds(10, 29, 46, 14);
		panel_4.add(lblBusca);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Medico beans = new Medico();
					MedicoDao dao = new MedicoDao();
					beans.setPesquisa(txtPesquNomeMedico.getText());
					Medico model = dao.BuscaMedico(beans);

					Conexao conexaoBanco;
					conexaoBanco = new Conexao();
					conexaoBanco.conexao();
					txtPesquNomeMedico.setText(String.valueOf(model.getNome()));
					//CHAMA TABELA DE MÉDICOS
					conexaoBanco.executeSQL("SELECT * FROM medico WHERE nome LIKE '%"+beans.getPesquisa()+"%'");
					table_med.setModel(new TabelaModel(conexaoBanco.resultset));
					table_med.repaint(table_med.getRowCount());

					//btnExcluir.setEnabled(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	

			}
		});
		button.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\iconePesquisar.gif"));
		button.setBounds(342, 23, 21, 23);
		panel_4.add(button);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 82, 364, 152);
		panel_4.add(scrollPane_2);

		table_med = new JTable();
		table_med.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Cod.", "Nome", "Especialidade"
				}
				));
		table_med.getColumnModel().getColumn(0).setPreferredWidth(35);
		table_med.getColumnModel().getColumn(1).setPreferredWidth(70);
		table_med.getColumnModel().getColumn(2).setPreferredWidth(70);


		table_med = new JTable();
		table_med.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha_selecionada = table_med.getSelectedRow();//pegar a linha selecionada
				txtPesquNomeMedico.setText(table_med.getValueAt(linha_selecionada, 1).toString());
			}
		});

		scrollPane_2.setColumnHeaderView(table_med);

		JButton btnCarregarTabelaDe = new JButton("Carregar Tabela de M\u00E9dicos");
		btnCarregarTabelaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
				Conexao conexaoBanco;
				conexaoBanco = new Conexao();
				conexaoBanco.conexao();

				//CHAMA TABELA DE MÉDICOS
				conexaoBanco.executeSQL("select cod_medico, nome, especializacao "
						+ "from medico");
				table_med.setModel(new TabelaModel(conexaoBanco.resultset));
				table_med.repaint(table_med.getRowCount());
				}catch(Exception e4){
					System.out.println(e4);
				}
				
			}
		});
		btnCarregarTabelaDe.setBounds(99, 57, 190, 20);
		panel_4.add(btnCarregarTabelaDe);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resposta = 0;
				
				Consulta consulta = new Consulta();
				ConsultaDao dao = new ConsultaDao();
				resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja Realmente Excluir?");
				if(txtCodigoConsulta.equals("")){
					JOptionPane.showMessageDialog(null, "Comando Inválido");
				}else /*if(resposta == JOptionPane.YES_NO_OPTION)*/{
					consulta.setCod_consulta(Integer.parseInt(txtCodigoConsulta.getText()));
					dao.Excluir(consulta);	

					//limpa campos após cadastro
					txtNomeCliente.setText("");
					txtProcedimento.setText("");
					txtDia.setText("");
					txtMes.setText("");
					txtAno.setText("");
					txtHora.setText("");
					txtValorProce.setText("");
					txtTipoPagamento.setText("");
					txtStatusPagamento.setText("");
					txtStatusConsulta.setText("");
					txtValorTotal.setText("");
					txtPesquNomeMedico.setText("");
					txtCodigoConsulta.setText("");
					txtDescricao.setText("");
					
					Conexao conexaoBanco;
					try{
						conexaoBanco = new Conexao();
						conexaoBanco.conexao();

						conexaoBanco.executeSQL("select * "
								+ "from consulta");
						table_con.setModel(new TabelaModel(conexaoBanco.resultset));
						table_con.repaint(table_con.getRowCount());

					}catch(Exception e4){
						JOptionPane.showMessageDialog(null, "ERRO: "+e4);
					}
				}
				
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExcluir.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\Botao-Excluir.png"));
		btnExcluir.setBounds(289, 532, 135, 37);
		contentPane.add(btnExcluir);
	}
}
