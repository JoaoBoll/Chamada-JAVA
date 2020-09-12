package JanelasADM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import View.telaPrincipal;
import View.telaPrincipal;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ADM extends JFrame {

	private JPanel contentPane;

	public ADM() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInscreverAluno = new JButton("Matricular Aluno");
		btnInscreverAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatricularAluno matricularAluno = new MatricularAluno();
				matricularAluno.setVisible(true);
				setVisible(false);
			}
		});
		btnInscreverAluno.setBounds(10, 57, 176, 23);
		contentPane.add(btnInscreverAluno);
		
		JButton btnMatricularProf = new JButton("Matricular Professor");
		btnMatricularProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatricularProfessor matricularProfessor = new MatricularProfessor();
				matricularProfessor.setVisible(true);
				setVisible(false);
			}
		});
		btnMatricularProf.setBounds(10, 91, 176, 23);
		contentPane.add(btnMatricularProf);
		
		JButton btnEditarMatricula = new JButton("Editar Aluno");
		btnEditarMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarAluno editAluno = new EditarAluno();
				editAluno.setVisible(true);
				setVisible(false);
			}
		});
		btnEditarMatricula.setBounds(298, 57, 176, 23);
		contentPane.add(btnEditarMatricula);
		
		JButton btnAlterarCurso = new JButton("Alterar Dados de Curso");
		btnAlterarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarCurso curso = new EditarCurso();
				curso.setVisible(true);
				setVisible(false);
			}
		});
		btnAlterarCurso.setBounds(298, 125, 176, 23);
		contentPane.add(btnAlterarCurso);
		
		JButton btnAdicionarCurso = new JButton("Adicionar Curso");
		btnAdicionarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatricularCurso addCurso = new MatricularCurso();
				addCurso.setVisible(true);
				setVisible(false);
			}
		});
		btnAdicionarCurso.setBounds(10, 125, 176, 23);
		contentPane.add(btnAdicionarCurso);
		
		JButton btnCriarInstituição = new JButton("Adicionar Institui\u00E7\u00E3o");
		btnCriarInstituição.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatricularInstituicao addInst = new MatricularInstituicao();
				addInst.setVisible(true);
				setVisible(false);
			}
		});
		btnCriarInstituição.setBounds(10, 159, 176, 23);
		contentPane.add(btnCriarInstituição);
		
		JButton btnAlterarInstituicao = new JButton("Alterar Dados da Institui\u00E7\u00E3o");
		btnAlterarInstituicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarInstituicao editar = new EditarInstituicao();
				editar.setVisible(true);
				setVisible(false);
			}
		});
		btnAlterarInstituicao.setBounds(298, 159, 176, 23);
		contentPane.add(btnAlterarInstituicao);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaPrincipal inicio = new telaPrincipal();
				inicio.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(385, 427, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Cadastrar");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 23, 176, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblAlterarDadosNo = new JLabel("Alterar Dados no Sistema");
		lblAlterarDadosNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterarDadosNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlterarDadosNo.setBounds(298, 23, 176, 23);
		contentPane.add(lblAlterarDadosNo);
		
		JButton btnNewButton_1 = new JButton("Editar Professor");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarProfessor editProf = new EditarProfessor();
				editProf.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(298, 91, 176, 23);
		contentPane.add(btnNewButton_1);
		
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.
		
	}
}
