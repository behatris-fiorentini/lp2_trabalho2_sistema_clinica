package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField passSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setTitle("SysClinica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 197);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(166, 28, 46, 21);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSenha.setBounds(166, 60, 46, 14);
		contentPane.add(lblSenha);
		
		passSenha = new JPasswordField();
		passSenha.setBounds(222, 58, 198, 20);
		contentPane.add(passSenha);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(222, 29, 198, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		//DANDO AÇÃO AO BOTÃO ENTRAR
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//chama a tela principal
				
				if(txtLogin.getText().equals("admin")&& passSenha.getText().equals("admin")){
					TelaPrincipalView tpv = new TelaPrincipalView();
					tpv.setVisible(true);
					
					//fecha a tela anterior
					dispose();
				}else{
					JOptionPane.showMessageDialog(null,"Senha ou Login Incorretos");
				}
				
			}
		});
		
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEntrar.setBounds(331, 94, 89, 32);
		contentPane.add(btnEntrar);
		
		JLabel lblTexto1 = new JLabel("Entre com seu login");
		lblTexto1.setForeground(Color.WHITE);
		lblTexto1.setBounds(21, 66, 116, 14);
		contentPane.add(lblTexto1);
		
		JLabel lblSysclinica = new JLabel("SysClinica");
		lblSysclinica.setForeground(Color.WHITE);
		lblSysclinica.setFont(new Font("AR DELANEY", Font.PLAIN, 21));
		lblSysclinica.setBounds(21, 28, 135, 27);
		contentPane.add(lblSysclinica);
		
		JLabel lblTexto3 = new JLabel("sistema.");
		lblTexto3.setForeground(Color.WHITE);
		lblTexto3.setBounds(21, 99, 46, 14);
		contentPane.add(lblTexto3);
		
		JLabel lblTexto2 = new JLabel("e senha para acessar o");
		lblTexto2.setForeground(Color.WHITE);
		lblTexto2.setBounds(21, 83, 116, 14);
		contentPane.add(lblTexto2);
		
		JLabel lblImagemFundo = new JLabel("");
		lblImagemFundo.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\TelaFundoLogin.jpg"));
		lblImagemFundo.setBounds(0, 0, 449, 158);
		contentPane.add(lblImagemFundo);
	}
}
