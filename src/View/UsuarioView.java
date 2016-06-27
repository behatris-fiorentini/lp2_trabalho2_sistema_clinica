package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.MedicoDao;
import Control.UsuarioDao;
import Model.Medico;
import Model.Usuario;
import Util.Conexao;
import Util.TabelaModel;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UsuarioView extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField passwordField;
	private static JTable table_usuario;
	private JTextField txtCodigo;
	private JTextField txtPermissao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioView frame = new UsuarioView();
					frame.setVisible(true);

					Conexao conexaoBanco;
					conexaoBanco = new Conexao();

					conexaoBanco.conexao();
					conexaoBanco.executeSQL("select cod_usuario, nome, tipo "
							+ "from usuario");
					table_usuario.setModel(new TabelaModel(conexaoBanco.resultset));
					table_usuario.repaint(table_usuario.getRowCount());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UsuarioView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 65, 46, 14);
		contentPane.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 101, 46, 14);
		contentPane.add(lblSenha);

		JLabel lblPermisso = new JLabel("Permiss\u00E3o:");
		lblPermisso.setBounds(10, 141, 60, 14);
		contentPane.add(lblPermisso);

		txtLogin = new JTextField();
		txtLogin.setBounds(76, 62, 322, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(76, 98, 322, 20);
		contentPane.add(passwordField);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Usuario beans = new Usuario();
				UsuarioDao dao1 = new UsuarioDao();

				
				if(txtLogin.getText().equals("") || passwordField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Todos Campos são Obrigatórios.");
				
					
				}else{
					beans.setId_login(Integer.parseInt(txtCodigo.getText()));
					beans.setNome((txtLogin.getText()));
					beans.setSenha(passwordField.getText());
					beans.setPermissao(txtPermissao.getText());

					dao1.Salvar(beans);

					txtLogin.setText("");
					passwordField.setText("");
					txtCodigo.setText("");

					Conexao conexaoBanco;
					conexaoBanco = new Conexao();
					try{
						conexaoBanco.conexao();
						conexaoBanco.executeSQL("select cod_usuario, nome, tipo "
								+ "from usuario");
						table_usuario.setModel(new TabelaModel(conexaoBanco.resultset));
						table_usuario.repaint(table_usuario.getRowCount());
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Erro carregar tabela: "+e);
					}
				}	
			}
		});
		btnCadastrar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\salve.png"));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrar.setBounds(422, 9, 151, 47);
		contentPane.add(btnCadastrar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Usuario beans = new Usuario();
				UsuarioDao dao1 = new UsuarioDao();

				beans.setId_login(Integer.parseInt(txtCodigo.getText()));
				beans.setNome((txtLogin.getText()));
				beans.setSenha(passwordField.getText());
				beans.setPermissao(txtPermissao.getText());

				dao1.Editar(beans);

				txtLogin.setText("");
				passwordField.setText("");
				txtCodigo.setText("");

				Conexao conexaoBanco;
				conexaoBanco = new Conexao();
				try{
					conexaoBanco.conexao();
					conexaoBanco.executeSQL("select cod_usuario, nome, tipo "
							+ "from usuario");
					table_usuario.setModel(new TabelaModel(conexaoBanco.resultset));
					table_usuario.repaint(table_usuario.getRowCount());
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Erro carregar tabela: "+e);
				}

			}
		});
		btnEditar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\alterar-senha.gif"));
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEditar.setBounds(422, 62, 151, 47);
		contentPane.add(btnEditar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 195, 563, 179);
		contentPane.add(scrollPane);

		table_usuario = new JTable();
		table_usuario.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3d.", "Nome", "Tipo"
				}
				));


		table_usuario.getColumnModel().getColumn(0).setPreferredWidth(15);
		table_usuario.getColumnModel().getColumn(1).setPreferredWidth(76);
		table_usuario.getColumnModel().getColumn(2).setPreferredWidth(15);


		table_usuario = new JTable();
		table_usuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha_selecionada = table_usuario.getSelectedRow();//pegar a linha selecionada
				txtLogin.setText(table_usuario.getValueAt(linha_selecionada, 0).toString());
				passwordField.setText(table_usuario.getValueAt(linha_selecionada, 1).toString());
				txtPermissao.setText(table_usuario.getValueAt(linha_selecionada, 1).toString());;
			}
		});

		scrollPane.setColumnHeaderView(table_usuario);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\Botao-Excluir.png"));
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExcluir.setBounds(422, 115, 149, 45);
		contentPane.add(btnExcluir);

		JLabel lblGerenciamentoDeUsurios = new JLabel("Gerenciamento de Usu\u00E1rios");
		lblGerenciamentoDeUsurios.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblGerenciamentoDeUsurios.setBounds(32, 13, 366, 29);
		contentPane.add(lblGerenciamentoDeUsurios);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(312, 138, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCd = new JLabel("C\u00F3d.:");
		lblCd.setBounds(269, 141, 46, 14);
		contentPane.add(lblCd);
		
		txtPermissao = new JTextField();
		txtPermissao.setToolTipText("Administrador ou comum");
		txtPermissao.setBounds(75, 138, 179, 20);
		contentPane.add(txtPermissao);
		txtPermissao.setColumns(10);
	}
}
