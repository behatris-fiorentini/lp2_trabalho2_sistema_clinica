package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Util.Conexao;
import Util.TabelaModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import Control.PacienteDao;
import Model.Paciente;

public class PacienteView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtIdade;
	private JTextField txtDataNascimento;
	private JTextField txtCpf;
	private JTextField txtRg;
	private JTextField txtEndereco;
	private JTextField txtCidade;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private static JTable table_paciente_1;


	private final JTextField textField_2 = new JTextField();
	private JTextField txtPesquisa;
	private JTextField txtTelefone;
	private JTextField txtCelular;
	private JTextField txtAtivo;
	private JTextField txtCodigo;
	


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacienteView frame = new PacienteView();
					frame.setVisible(true);

					Conexao conexaoBanco;

					conexaoBanco = new Conexao();
					conexaoBanco.conexao();

					//chama
					conexaoBanco.executeSQL("select * "
							+ "from paciente");
					table_paciente_1.setModel(new TabelaModel(conexaoBanco.resultset));
					table_paciente_1.repaint(table_paciente_1.getRowCount());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public PacienteView() {
		setTitle("Gerenciamento de Pacientes");
		textField_2.setColumns(10);
		setBounds(100, 100, 869, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Formul\u00E1rio:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 45, 833, 181);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 26, 46, 14);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setToolTipText("Preencha com o nome do paciente");
		txtNome.setBounds(51, 23, 304, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(365, 26, 46, 14);
		panel.add(lblIdade);

		txtIdade = new JTextField();
		txtIdade.setToolTipText("Preencha com a idade do paciente");
		txtIdade.setBounds(409, 23, 52, 20);
		panel.add(txtIdade);
		txtIdade.setColumns(10);

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(471, 26, 168, 14);
		panel.add(lblDataDeNascimento);

		txtDataNascimento = new JTextField();
		txtDataNascimento.setToolTipText("Preencha com  a data de nascimento do paciente");
		txtDataNascimento.setBounds(589, 23, 77, 20);
		panel.add(txtDataNascimento);
		txtDataNascimento.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 66, 46, 14);
		panel.add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setToolTipText("Preencha com o CPF do paciente");
		txtCpf.setBounds(51, 63, 116, 20);
		panel.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblRg = new JLabel("RG.:");
		lblRg.setBounds(190, 66, 46, 14);
		panel.add(lblRg);

		txtRg = new JTextField();
		txtRg.setToolTipText("Preencha com o RG do paciente");
		txtRg.setBounds(220, 63, 141, 20);
		panel.add(txtRg);
		txtRg.setColumns(10);

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(387, 66, 52, 14);
		panel.add(lblEndereo);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(449, 63, 217, 20);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 106, 86, 14);
		panel.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(51, 103, 203, 20);
		panel.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(272, 106, 46, 14);
		panel.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(315, 103, 191, 20);
		panel.add(txtBairro);
		txtBairro.setColumns(10);

		txtNumero = new JTextField();
		txtNumero.setBounds(620, 103, 46, 20);
		panel.add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNmero = new JLabel("N\u00BA. Resid\u00EAncia:");
		lblNmero.setBounds(534, 106, 116, 14);
		panel.add(lblNmero);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setToolTipText("Clique para cadastrar um novo cliente");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(!txtCodigo.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Comando Invalido.");
				}
				//tratando dados: nenhum campo, exceto o de demissão, podem ser nulo.
				else if(txtNome.getText().equals("")|| txtCelular.equals("")||
						txtNumero.getText().equals("")|| txtEndereco.getText().equals("")||
						txtTelefone.getText().equals("")|| txtCidade.getText().equals("") || txtCpf.getText().equals("")||
						txtDataNascimento.getText().equals("")||txtIdade.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Campo Obrigatório Não Preenchido.");

					//se nenhum dado obrigatório estiver nulo, o cadastro é realizado
				}else{
					Paciente obj = new Paciente();
					PacienteDao dao = new PacienteDao();
					
					obj.setNome(txtNome.getText());
					obj.setIdade(Integer.parseInt(txtIdade.getText()));
					obj.setDataNascimento(txtDataNascimento.getText());
					obj.setCpf(txtCpf.getText());
					obj.setRg(txtRg.getText());
					obj.setEndereco(txtEndereco.getText());
					obj.setCidade(txtCidade.getText());
					obj.setBairro(txtBairro.getText());
					obj.setNumeroResidencia(Integer.parseInt(txtNumero.getText()));
					obj.setTelefone(txtTelefone.getText());
					obj.setCelular(txtCelular.getText());

					dao.Salvar(obj);

					//limpa campos após cadastro
					txtNome.setText("");
					txtIdade.setText("");
					txtDataNascimento.setText("");
					txtEndereco.setText("");
					txtTelefone.setText("");
					txtCpf.setText("");
					txtCidade.setText("");
					txtBairro.setText("");
					txtNumero.setText("");
					txtCelular.setText("");
					txtRg.setText("");
					

					Conexao conexaoBanco;
					try{
						conexaoBanco = new Conexao();
						conexaoBanco.conexao();

						//chama
						conexaoBanco.executeSQL("select nome, idade, cidade, telefone, celular "
								+ "from paciente");
						table_paciente_1.setModel(new TabelaModel(conexaoBanco.resultset));
						table_paciente_1.repaint(table_paciente_1.getRowCount());
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "ERRO: "+e);
					}


				}

			}
		});				
				
		btnCadastrar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\salve.png"));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrar.setBounds(676, 39, 147, 41);
		panel.add(btnCadastrar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setToolTipText("Clique para editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Paciente obj = new Paciente();
				PacienteDao dao = new PacienteDao();
				
				obj.setNome(txtNome.getText());
				obj.setIdade(Integer.parseInt(txtIdade.getText()));
				obj.setDataNascimento(txtDataNascimento.getText());
				obj.setCpf(txtCpf.getText());
				obj.setRg(txtRg.getText());
				obj.setEndereco(txtEndereco.getText());
				obj.setCidade(txtCidade.getText());
				obj.setBairro(txtBairro.getText());
				obj.setNumeroResidencia(Integer.parseInt(txtNumero.getText()));
				obj.setTelefone(txtTelefone.getText());
				obj.setCelular(txtCelular.getText());

				dao.Editar(obj);

				//limpa campos após cadastro
				txtNome.setText("");
				txtIdade.setText("");
				txtDataNascimento.setText("");
				txtEndereco.setText("");
				txtTelefone.setText("");
				txtCpf.setText("");
				txtCidade.setText("");
				txtBairro.setText("");
				txtNumero.setText("");
				txtCelular.setText("");
				txtRg.setText("");
				
				Conexao conexaoBanco;
				try{
					conexaoBanco = new Conexao();
					conexaoBanco.conexao();

					//chama
					conexaoBanco.executeSQL("select nome, idade, cidade, telefone, celular "
							+ "from paciente");
					table_paciente_1.setModel(new TabelaModel(conexaoBanco.resultset));
					table_paciente_1.repaint(table_paciente_1.getRowCount());
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "ERRO: "+e);
				}
				
			}
		});
		btnEditar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\alterar-senha.gif"));
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEditar.setBounds(676, 92, 147, 41);
		panel.add(btnEditar);

	

		
	
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 150, 116, 14);
		panel.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setToolTipText("Ex.: 3729-1325");
		txtTelefone.setBounds(63, 147, 161, 20);
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(250, 150, 46, 14);
		panel.add(lblCelular);

		txtCelular = new JTextField();
		txtCelular.setToolTipText("Ex.: 027 995412325");
		txtCelular.setBounds(312, 147, 160, 20);
		panel.add(txtCelular);
		txtCelular.setColumns(10);

		JLabel lblAtivo = new JLabel("Ativo:");
		lblAtivo.setBounds(492, 150, 46, 14);
		panel.add(lblAtivo);

		txtAtivo = new JTextField();
		txtAtivo.setToolTipText("Preencha com SIM, se o paciente ainda utiliza os servi\u00E7os da clinica, e com N\u00C3O, se n\u00E3o utilizar mais");
		txtAtivo.setBounds(534, 147, 132, 20);
		panel.add(txtAtivo);
		txtAtivo.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 267, 833, 162);
		contentPane.add(scrollPane);

		table_paciente_1 = new JTable();
		table_paciente_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"nome", "idade", "nasc.", "cpf", "rg", "end", "cidade", "bairro", "cel", "tel", "num"
			}
		));
		table_paciente_1.getColumnModel().getColumn(1).setPreferredWidth(36);
		table_paciente_1.getColumnModel().getColumn(2).setPreferredWidth(38);
		table_paciente_1.getColumnModel().getColumn(3).setPreferredWidth(28);
		table_paciente_1.getColumnModel().getColumn(4).setPreferredWidth(39);
		table_paciente_1.getColumnModel().getColumn(8).setPreferredWidth(60);
		table_paciente_1.getColumnModel().getColumn(9).setPreferredWidth(52);
		table_paciente_1.getColumnModel().getColumn(10).setPreferredWidth(32);
		
		table_paciente_1 = new JTable();
		table_paciente_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int linha_selecionada = table_paciente_1.getSelectedRow();//pegar a linha selecionada
				txtCodigo.setText(table_paciente_1.getValueAt(linha_selecionada, 0).toString());
				txtNome.setText(table_paciente_1.getValueAt(linha_selecionada, 1).toString());
				txtIdade.setText(table_paciente_1.getValueAt(linha_selecionada, 2).toString());
				txtDataNascimento.setText(table_paciente_1.getValueAt(linha_selecionada, 3).toString());
				txtCpf.setText(table_paciente_1.getValueAt(linha_selecionada, 4).toString());
				txtRg.setText(table_paciente_1.getValueAt(linha_selecionada, 5).toString());
				txtEndereco.setText(table_paciente_1.getValueAt(linha_selecionada, 6).toString());
				txtCidade.setText(table_paciente_1.getValueAt(linha_selecionada, 7).toString());
				txtBairro.setText(table_paciente_1.getValueAt(linha_selecionada, 8).toString());
				txtNumero.setText(table_paciente_1.getValueAt(linha_selecionada, 9).toString());
				txtTelefone.setText(table_paciente_1.getValueAt(linha_selecionada, 10).toString());
				txtCelular.setText(table_paciente_1.getValueAt(linha_selecionada, 11).toString());
				

			}
		});

		scrollPane.setColumnHeaderView(table_paciente_1);

		JLabel lblPesquisa = new JLabel("Pesquisa:");
		lblPesquisa.setBounds(10, 237, 54, 14);
		contentPane.add(lblPesquisa);

		txtPesquisa = new JTextField();
		txtPesquisa.setToolTipText("Busque o paciente que deseja");
		txtPesquisa.setBounds(62, 234, 205, 20);
		contentPane.add(txtPesquisa);
		txtPesquisa.setColumns(10);

		JButton button = new JButton("");
		button.setToolTipText("Clique aqui para realizar sua busca");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					PacienteDao dao = new PacienteDao();
					Paciente beans = new Paciente();
					beans.setPesquisa(txtPesquisa.getText());
					Paciente model = dao.BuscaPaciente(beans);
					txtNome.setText(model.getNome());
					txtIdade.setText(String.valueOf(model.getIdade()));
					txtDataNascimento.setText(model.getDataNascimento());
					txtEndereco.setText(model.getEndereco());
					txtTelefone.setText(model.getTelefone());
					txtCpf.setText(model.getCpf());
					txtCidade.setText(model.getCidade());
					txtBairro.setText(model.getBairro());
					txtNumero.setText(String.valueOf(model.getNumeroResidencia()));
					txtCelular.setText(model.getCelular());
					txtCodigo.setText(String.valueOf(model.getCod_paciente()));

					
					Conexao conexaoBanco;

					conexaoBanco = new Conexao();
					conexaoBanco.conexao();

					//chama
					conexaoBanco.executeSQL("SELECT * FROM paciente WHERE nome LIKE '%"+beans.getPesquisa()+"%'");
					table_paciente_1.setModel(new TabelaModel(conexaoBanco.resultset));
					table_paciente_1.repaint(table_paciente_1.getRowCount());
				
					btnEditar.setEnabled(true);
					//btnExcluir.setEnabled(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}		
				
				
				
				
			}
		});
		button.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\iconePesquisar.gif"));
		button.setBounds(276, 233, 25, 23);
		contentPane.add(button);

		txtCodigo = new JTextField();
		txtCodigo.setToolTipText("C\u00F3digo");
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(730, 234, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblGerenciamentoDePacientes = new JLabel("Gerenciamento de Pacientes");
		lblGerenciamentoDePacientes.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblGerenciamentoDePacientes.setBounds(231, 11, 403, 31);
		contentPane.add(lblGerenciamentoDePacientes);
		
		JLabel lblCd = new JLabel("C\u00F3d.:");
		lblCd.setBounds(674, 237, 46, 14);
		contentPane.add(lblCd);
	}
}
