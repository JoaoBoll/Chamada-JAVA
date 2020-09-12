package JanelasEstudantes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JanelasADM.ADM;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Estudante extends JFrame {

	private JPanel contentPane;

	public Estudante() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.
		
		JButton btnNotasAluno = new JButton("Minhas Notas");
		btnNotasAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNotasAluno.setBounds(356, 11, 118, 23);
		contentPane.add(btnNotasAluno);
		
		JButton btnHorario = new JButton("Horarios");
		btnHorario.setBounds(356, 79, 118, 23);
		contentPane.add(btnHorario);
		
		JButton btnFaltas = new JButton("Faltas");
		btnFaltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFaltas.setBounds(356, 113, 118, 23);
		contentPane.add(btnFaltas);
		
		JButton btnPresença = new JButton("Presen\u00E7a");
		btnPresença.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPresença.setBounds(356, 45, 118, 23);
		contentPane.add(btnPresença);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginEstudante estudante = new loginEstudante();
				estudante.setVisible(true);
				setVisible(false);}
		});
		btnVoltar.setBounds(356, 427, 118, 23);
		contentPane.add(btnVoltar);
	}

}
