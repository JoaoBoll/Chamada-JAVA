package JanelasADM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class loginADM extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textLoginADM;
	private JPasswordField passwordField;

	public loginADM() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLogin.setBounds(10, 147, 50, 21);
		contentPane.add(lblLogin);
		
		textLoginADM = new JTextField();
		textLoginADM.setColumns(10);
		textLoginADM.setBounds(65, 149, 409, 23);
		contentPane.add(textLoginADM);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSenha.setBounds(10, 188, 56, 21);
		contentPane.add(lblSenha);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (login()) {
					ADM admin = new ADM();
					admin.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null,"Falha no Login");
					passwordField.setText("");
				}
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLogin.setBounds(201, 222, 88, 23);
		contentPane.add(btnLogin);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaPrincipal inicio = new telaPrincipal();
				inicio.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(386, 427, 88, 23);
		contentPane.add(btnVoltar);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(65, 188, 409, 21);
		contentPane.add(passwordField);
		
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.
		
	}
	
	public boolean login() {
		boolean loginEfetuado = false;
		
		String login = "";
		String senha = "";
		login = textLoginADM.getText();
		senha = new String(passwordField.getPassword()); //pegar password
		
		try {
			ConexaoSql.ConexaoMysql conn = new ConexaoSql.ConexaoMysql();
			conn.conectarMySQL();
			String query = "select * from contaadm WHERE login = ? and senha = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, senha);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {

				loginEfetuado = true;
				
			}else {
				loginEfetuado = false;
			}


			conn.FecharConexao();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Erro!!");
		}
		return loginEfetuado;
	}

}
