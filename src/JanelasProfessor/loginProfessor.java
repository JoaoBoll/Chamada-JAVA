package JanelasProfessor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexaoSql.ConexaoMysql;
import JanelasProfessor.*;
import View.telaPrincipal;
import Model.*;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.print.attribute.standard.PagesPerMinute;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class loginProfessor extends JFrame {

	private JPanel contentPane;
	private JTextField txtLoginProf;
	private JPasswordField txtSenhaProf;
	private int IdCurso;
	private int IdProf;

	public int getIdCurso() {
		return IdCurso;
	}
	public loginProfessor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.
		
		txtLoginProf = new JTextField();
		txtLoginProf.setColumns(10);
		txtLoginProf.setBounds(76, 110, 398, 23);
		contentPane.add(txtLoginProf);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLogin.setBounds(16, 108, 50, 21);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSenha.setBounds(10, 147, 56, 21);
		contentPane.add(lblSenha);
		
		txtSenhaProf = new JPasswordField();
		txtSenhaProf.setColumns(10);
		txtSenhaProf.setBounds(76, 149, 398, 23);
		contentPane.add(txtSenhaProf);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(ConectarMySql()) {
					MenuProfessor professor = new MenuProfessor();
					professor.setVisible(true);
					setVisible(false);
				} else {
				JOptionPane.showMessageDialog(null, "Login ou senha incorretos");
				}
				
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEntrar.setBounds(175, 183, 133, 23);
		contentPane.add(btnEntrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaPrincipal inicio = new telaPrincipal();
				inicio.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(357, 312, 88, 23);
		contentPane.add(btnVoltar);
	}
	public boolean ConectarMySql() {
		boolean loginEfetuado = false;
		String login = txtLoginProf.getText();
		String senha = new String (txtSenhaProf.getPassword());
		
		ConexaoMysql conn = new ConexaoMysql();
		try {
			conn.conectarMySQL();
			
			String query = "SELECT * FROM professor WHERE login = ? AND senha = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, senha);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()){
				loginEfetuado = true;
				
			}else {
				loginEfetuado = false;
			}
			
			conn.FecharConexao();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		setarIdProf();
		return loginEfetuado;
		
		
	}
	
	public void setarIdProf() {
		ConexaoMysql conn = new ConexaoMysql();
		String login = txtLoginProf.getText();
		String senha = new String (txtSenhaProf.getPassword());
		
		try {
			conn.conectarMySQL();
			
			String query = "SELECT idprof, idcurso FROM professor WHERE login = ? and senha = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, senha);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			int idprof = 0;
			int idcurso = 0;
			while (rs.next()) {
				
				idprof = rs.getInt("IdProf");
				idcurso = rs.getInt("idcurso");
				
				System.out.println(idprof);
				System.out.println(idcurso);
				MenuProfessor menuProfessor = new MenuProfessor();
				menuProfessor.setIdcurso(idcurso);
				
			}
			
			if (idprof != 0) {
			Professor prof = new Professor();
			prof.setIdCurso(idcurso);
			
			} else {
				JOptionPane.showMessageDialog(null, "Erro de matrícula");
			}
			conn.FecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
}
