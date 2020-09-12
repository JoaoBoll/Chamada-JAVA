package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JanelasADM.loginADM;
import JanelasEstudantes.loginEstudante;
import JanelasProfessor.loginProfessor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;

	public telaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.
		
		lblNewLabel = new JLabel("Bem Vindo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(167, 34, 149, 75);
		contentPane.add(lblNewLabel);
		
		JButton btnLoginAluno = new JButton("Login de Aluno");
		btnLoginAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginEstudante loginEstudante = new loginEstudante();
				loginEstudante.setVisible(true);
				setVisible(false);
			}
		});
		btnLoginAluno.setBounds(167, 168, 149, 23);
		contentPane.add(btnLoginAluno);
		
		JButton btnProfessor = new JButton("Login de Professor");
		btnProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginProfessor loginProfessor = new loginProfessor();
				loginProfessor.setVisible(true);
				setVisible(false);
			}
		});
		btnProfessor.setBounds(167, 233, 149, 23);
		contentPane.add(btnProfessor);
		
		JButton btnAdmin = new JButton("Login de Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginADM loginADM = new loginADM();
				loginADM.setVisible(true);
				setVisible(false);
			}
		});
		btnAdmin.setBounds(167, 297, 149, 23);
		contentPane.add(btnAdmin);
	}
}
