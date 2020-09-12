package JanelasProfessor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.PreparedQuery;

import ConexaoSql.ConexaoMysql;
import Model.Professor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MenuProfessor extends JFrame {
	Professor login = new Professor();
	private JPanel contentPane;
	private int IdProf;
	private int IdCurso = login.getIdCurso();
	
	}

	public void setIdProf(int idprof) {
		this.IdProf = idprof;
	}

	public void setIdcurso(int idcurso) {
		this.IdCurso = idcurso;
	}

	public MenuProfessor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAcompanharChamada = new JButton("Acompanhar chamada");
		btnAcompanharChamada.setBounds(98, 45, 164, 23);
		contentPane.add(btnAcompanharChamada);

		JButton btnFinalizarChamada = new JButton("Finalizar chamada");
		btnFinalizarChamada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharChamada();
			}
		});
		btnFinalizarChamada.setBounds(98, 110, 164, 23);
		contentPane.add(btnFinalizarChamada);

		JButton btnLiberarChamada = new JButton("Liberar chamada");
		btnLiberarChamada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				liberarChamada();
			}
		});
		btnLiberarChamada.setBounds(98, 11, 164, 23);
		contentPane.add(btnLiberarChamada);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(349, 370, 89, 23);
		contentPane.add(btnVoltar);
	}
	public void liberarChamada() {
		ConexaoMysql conn = new ConexaoMysql();
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		try {
			System.out.println(IdProf);
			conn.conectarMySQL();
			String True = "true";
			String query = "INSERT INTO chamadaprof (dia, idcurso, testboolean) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, data);
			preparedStatement.setInt(2, IdProf);
			preparedStatement.setString(3, True);
			
			preparedStatement.executeQuery();
			
			conn.FecharConexao();
			
			JOptionPane.showMessageDialog(null, "Lista de presença liberada"); 

		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}

	public void fecharChamada() {
		ConexaoMysql conn = new ConexaoMysql();
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);

		if (testefechar()) {
			try {
				conn.conectarMySQL();
				String False = "false";
				String query = "UPDATE chamadaprof  set testboolean = ? Where dia = ? and idcurso = ?";
				PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
				preparedStatement.setString(2, data);
				preparedStatement.setInt(3, IdCurso);
				preparedStatement.setString(1, False);
				
				preparedStatement.executeUpdate();

				conn.FecharConexao();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Chamada ainda não iniciada");
		}

			JOptionPane.showConfirmDialog(null, "lista de presença fechada"); 
		}

		public boolean testefechar() {
			ConexaoMysql conn = new ConexaoMysql();
			boolean teste = false;
			Date dataHoraAtual = new Date();
			String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
			try {
				
				conn.conectarMySQL();
				String query = "Select count(*) as total from chamadaprof where dia = ? and idcurso = ?";
				PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
				preparedStatement.setString(1, data);
				preparedStatement.setInt(2, login.getIdCurso());
				ResultSet rs = preparedStatement.executeQuery();

				int count = 0;

				if (rs.first()) {
					count = rs.getInt("total");
				}

				if (count == 1) {
					teste = true;
				} else {
					teste = false;
				}
				 conn.FecharConexao();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return teste;
		}
	}
