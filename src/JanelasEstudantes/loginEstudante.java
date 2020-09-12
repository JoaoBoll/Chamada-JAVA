package JanelasEstudantes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexaoSql.ConexaoMysql;
import JanelasEstudantes.*;
import View.telaPrincipal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class loginEstudante extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	public loginEstudante() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.
		
		JLabel lblLogin = new JLabel("E-mail:");
		lblLogin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLogin.setBounds(10, 133, 56, 21);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSenha.setBounds(10, 172, 56, 21);
		contentPane.add(lblSenha);
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(76, 135, 398, 23);
		contentPane.add(txtLogin);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ConectarMySql()) {
					Estudante estudante = new Estudante();
					estudante.setVisible(true);
					setVisible(false);
				} else {
				JOptionPane.showMessageDialog(null, "Login ou senha incorretos");
				}
				
				
				
				
				}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLogin.setBounds(198, 208, 88, 23);
		contentPane.add(btnLogin);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaPrincipal telaPrincipal = new telaPrincipal();
				telaPrincipal.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(371, 332, 89, 23);
		contentPane.add(btnVoltar);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(76, 175, 398, 20);
		contentPane.add(txtSenha);
	}
	public boolean ConectarMySql() {
		boolean loginEfetuado = false;
		String login = txtLogin.getText();
		String senha = new String (txtSenha.getPassword());
		
		ConexaoMysql conectarMySql = new ConexaoMysql();
		try {
			conectarMySql.conectarMySQL();
			String query = "SELECT * FROM aluno WHERE login = ? AND senha = ?";
			PreparedStatement preparedStatement = conectarMySql.connection.prepareStatement(query);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, senha);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()){
				loginEfetuado = true;
				
			}else {
				loginEfetuado = false;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return loginEfetuado;
		
		
	}
}