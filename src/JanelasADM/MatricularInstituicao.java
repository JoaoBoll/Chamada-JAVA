package JanelasADM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexaoSql.ConexaoMysql;
import DAO.Listas;
import Model.Instituicao;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class MatricularInstituicao extends JFrame {

	private JPanel contentPane;
	private JTextField textNomeInst;
	private JTextField textCidade;
	private JTextField textEstado;

	public MatricularInstituicao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.

		JLabel lblInstituio_1 = new JLabel("Institui\u00E7\u00E3o:");
		lblInstituio_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInstituio_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInstituio_1.setBounds(10, 130, 95, 21);
		contentPane.add(lblInstituio_1);

		textNomeInst = new JTextField();
		textNomeInst.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textNomeInst.setColumns(10);
		textNomeInst.setBounds(115, 133, 359, 21);
		contentPane.add(textNomeInst);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCidade.setBounds(10, 162, 95, 21);
		contentPane.add(lblCidade);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEstado.setBounds(10, 194, 95, 21);
		contentPane.add(lblEstado);

		textCidade = new JTextField();
		textCidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textCidade.setColumns(10);
		textCidade.setBounds(115, 165, 359, 21);
		contentPane.add(textCidade);

		textEstado = new JTextField();
		textEstado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textEstado.setColumns(10);
		textEstado.setBounds(115, 197, 359, 21);
		contentPane.add(textEstado);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matricularInstituicao();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(188, 229, 107, 23);
		contentPane.add(btnNewButton);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(367, 427, 107, 23);
		contentPane.add(btnVoltar);
	}

	public void matricularInstituicao() {

		ConexaoMysql conn = new ConexaoMysql();

		if (podeAdicionar()) {
			try{
				
				conn.conectarMySQL();
				
				String instituicao = textNomeInst.getText();
				String cidade = textCidade.getText();
				String estado = textEstado.getText();

				String query = "INSERT INTO instituicao (instituicao, Cidade, Estado) VALUES(?,?,?)";

				PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
				preparedStatement.setString(1, instituicao);
				preparedStatement.setString(2, cidade);
				preparedStatement.setString(3, estado);

				preparedStatement.executeUpdate();

				conn.FecharConexao();
				
				JOptionPane.showMessageDialog(null, "Instituição adicionada com sucesso!");
				textNomeInst.setText("");
				textCidade.setText("");
				textEstado.setText("");
				
				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado: " + e);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Instituição já existe!");
		}

	}

	public boolean podeAdicionar() {
		boolean testeSeTem = false;

		
		try {
			String testeInst = textNomeInst.getText();
			String testeCid = textCidade.getText();
			String testeEstado = textEstado.getText();

			
			ConexaoMysql conn = new ConexaoMysql();
			conn.conectarMySQL();
			String query = "SELECT* FROM instituicao WHERE instituicao = ? and Cidade = ? and Estado = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, testeInst);
			preparedStatement.setString(2, testeCid);
			preparedStatement.setString(3, testeEstado);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				testeSeTem = false;
			} else {
				testeSeTem = true;
			}

			conn.FecharConexao();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado");
		}

		return testeSeTem;
	}

}



