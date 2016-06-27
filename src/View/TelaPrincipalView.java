package View;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Util.Conexao;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipalView extends JFrame {
	
	private static final long serialVersionUID = 1L;

	Conexao conecta = new Conexao();
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalView frame = new TelaPrincipalView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaPrincipalView() {
		
		conecta.conexao();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 369);
		contentPane = new JPanel();
		contentPane.setForeground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Rod", Font.PLAIN, 12));
		menuBar.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		menuBar.setBounds(0, 0, 704, 21);
		contentPane.add(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		mnCadastro.setForeground(Color.BLACK);
		mnCadastro.setFont(new Font("Rod", Font.PLAIN, 13));
		menuBar.add(mnCadastro);
		
		//chama a tela de medicos
		JMenuItem mntmMdico = new JMenuItem("M\u00E9dico");
		mntmMdico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MedicoView medico = new MedicoView();
				medico.setVisible(true);
			}
		});
		
		mntmMdico.setForeground(Color.GRAY);
		mntmMdico.setFont(new Font("Rod", Font.PLAIN, 13));
		mnCadastro.add(mntmMdico);
		
		//chama a tela de pacientes
		JMenuItem mntmPaciente = new JMenuItem("Paciente");
		mntmPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PacienteView paciente = new PacienteView();
				paciente.setVisible(true);
				
			}
		});
		mntmPaciente.setForeground(Color.GRAY);
		mntmPaciente.setFont(new Font("Rod", Font.PLAIN, 13));
		mnCadastro.add(mntmPaciente);
		
		//chama a tela de auxiliares
		JMenuItem mntmNewMenuItem = new JMenuItem("Auxiliar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AuxiliarView auxiliar = new AuxiliarView();
				auxiliar.setVisible(true);
			}
		});
		
		mntmNewMenuItem.setForeground(Color.GRAY);
		mntmNewMenuItem.setFont(new Font("Rod", Font.PLAIN, 13));
		mnCadastro.add(mntmNewMenuItem);
		
		//chama a tela de cadastro de usuarios
		JMenuItem mntmUsurio = new JMenuItem("Usu\u00E1rio");
		mntmUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			UsuarioView usuario = new UsuarioView();
			usuario.setVisible(true);
			}
		});
		
		
		mntmUsurio.setForeground(Color.GRAY);
		mntmUsurio.setFont(new Font("Rod", Font.PLAIN, 13));
		mnCadastro.add(mntmUsurio);
		
		JLabel lblSysclinica = new JLabel("SysClinica");
		lblSysclinica.setFont(new Font("AR DELANEY", Font.BOLD, 37));
		lblSysclinica.setBounds(212, 134, 222, 58);
		contentPane.add(lblSysclinica);
		
		JLabel lblSejaBemvindo = new JLabel("Seja Bem-Vindo");
		lblSejaBemvindo.setFont(new Font("Rod", Font.BOLD, 13));
		lblSejaBemvindo.setBounds(289, 182, 162, 21);
		contentPane.add(lblSejaBemvindo);
		
		JLabel lblImagemFundo = new JLabel("");
		lblImagemFundo.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 1.0\\telaFundo.jpg"));
		lblImagemFundo.setBounds(-10, 0, 446, 353);
		contentPane.add(lblImagemFundo);
		
		//DANDO FUNÇÃO AO BOTÃO MÉDICO DA TELA PRINCIPAL
		JButton btnMedico = new JButton("");
		btnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MedicoView medico = new MedicoView();
				medico.setVisible(true);
				
			}
		});
		btnMedico.setToolTipText("Cadastre M\u00E9dicos");
		btnMedico.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\medico.png"));
		btnMedico.setBounds(444, 43, 122, 129);
		contentPane.add(btnMedico);
		
		//DANDO FUNÇÃO AO BOTÃO AUXILIAR DA TELA PRINCIPAL
		JButton btnAuxiliar = new JButton("");
		btnAuxiliar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AuxiliarView auxiliar = new AuxiliarView();
				auxiliar.setVisible(true);
			}
		});
		btnAuxiliar.setToolTipText("Cadastre Auxiliares");
		btnAuxiliar.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\auxiliar.png"));
		btnAuxiliar.setBounds(576, 44, 122, 128);
		contentPane.add(btnAuxiliar);
		
		//DA FUNÇÃO AO BOTÃO CLIENTE DA TELA PRINCIPAL
		JButton btnPaciente = new JButton("");
		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PacienteView cliente = new PacienteView();
				cliente.setVisible(true);
			}
		});
		
		btnPaciente.setToolTipText("Cadastre Pacientes");
		btnPaciente.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\paciente2.png"));
		btnPaciente.setBounds(444, 194, 122, 129);
		contentPane.add(btnPaciente);
		
		//CHAMA TELA DE CONSULTA
		JButton btnAgenda = new JButton("");
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaView consulta = new ConsultaView();
				consulta.setVisible(true);
			}
		});
		
		btnAgenda.setToolTipText("Cadastre Consultas, Agenda");
		btnAgenda.setIcon(new ImageIcon("C:\\Users\\Behatris Fiorentini\\Desktop\\workspaceMapeamento\\SysPedido Beta 2.0\\consulta.png"));
		btnAgenda.setBounds(576, 194, 122, 129);
		contentPane.add(btnAgenda);
		
		JLabel lblMdico = new JLabel("M\u00E9dico");
		lblMdico.setBounds(485, 25, 46, 21);
		contentPane.add(lblMdico);
		
		JLabel lblAuxiliar = new JLabel("Auxiliar");
		lblAuxiliar.setBounds(620, 25, 46, 21);
		contentPane.add(lblAuxiliar);
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setBounds(485, 175, 46, 21);
		contentPane.add(lblPaciente);
		
		JLabel lblAgenda = new JLabel("Agenda");
		lblAgenda.setBounds(620, 178, 46, 14);
		contentPane.add(lblAgenda);
	}
}
