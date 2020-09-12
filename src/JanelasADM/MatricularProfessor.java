package JanelasADM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import ConexaoSql.ConexaoMysql;
import DAO.BuscarPor;
import DAO.Listas;
import DAO.Verificar;
import Model.Curso;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

public class MatricularProfessor extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCurso;
	private JLabel lblEmail;
	private JTextField textEmail;
	private JTextField textSenha;
	private JLabel lblSenha;
	private JTextField textCPF;
	private JTextField textNascimento;
	private JTextField textIdCurso;
	private JTextField textInstCurso;
	private JTextField textBuscar;
	private JTable table;
	private JComboBox comGraduacao;

	public MatricularProfessor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setBounds(385, 427, 89, 23);
		contentPane.add(btnVoltar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNome.setBounds(10, 268, 53, 21);
		contentPane.add(lblNome);

		textNome = new JTextField();
		textNome.setColumns(10);
		textNome.setBounds(73, 268, 401, 23);
		contentPane.add(textNome);

		JLabel lblNascimento = new JLabel("Nascimento:");
		lblNascimento.setHorizontalAlignment(SwingConstants.LEFT);
		lblNascimento.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNascimento.setBounds(10, 293, 93, 21);
		contentPane.add(lblNascimento);

		JLabel lblCurso = new JLabel("Id Curso:");
		lblCurso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCurso.setBounds(10, 318, 68, 21);
		contentPane.add(lblCurso);

		textCurso = new JTextField();
		textCurso.setColumns(10);
		textCurso.setBounds(279, 318, 195, 23);
		contentPane.add(textCurso);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCpf.setBounds(234, 293, 40, 21);
		contentPane.add(lblCpf);

		JButton btnCadastrarAluno = new JButton("Cadastrar");
		btnCadastrarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatricularProfessor();
			}
		});
		btnCadastrarAluno.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCadastrarAluno.setBounds(186, 427, 111, 23);
		contentPane.add(btnCadastrarAluno);

		JLabel lblGraduacao = new JLabel("Gradua\u00E7\u00E3o:");
		lblGraduacao.setHorizontalAlignment(SwingConstants.LEFT);
		lblGraduacao.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblGraduacao.setBounds(10, 343, 85, 21);
		contentPane.add(lblGraduacao);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(10, 368, 53, 21);
		contentPane.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(73, 368, 401, 23);
		contentPane.add(textEmail);

		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(73, 393, 401, 23);
		contentPane.add(textSenha);

		lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSenha.setBounds(10, 393, 53, 21);
		contentPane.add(lblSenha);

		try {
			textCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		textCPF.setColumns(10);
		textCPF.setBounds(279, 293, 195, 23);
		contentPane.add(textCPF);

		this.comGraduacao = new JComboBox();
		comGraduacao.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comGraduacao.setModel(new DefaultComboBoxModel(new String[] {"<Selecionar>", "Graduado", "P\u00F3s-Graduado", "Mestrado", "Doutorado"}));
		comGraduacao.setBounds(105, 343, 166, 23);
		contentPane.add(comGraduacao);

		try {
			textNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textNascimento.setColumns(10);
		textNascimento.setBounds(113, 293, 119, 23);
		contentPane.add(textNascimento);

		textIdCurso = new JTextField();
		textIdCurso.setColumns(10);
		textIdCurso.setBounds(88, 318, 67, 23);
		contentPane.add(textIdCurso);

		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teste = textIdCurso.getText();
				if (!teste.equals("")) {
					Verificar verificar = new Verificar();
					verificar.setIdCurso(Integer.parseInt(textIdCurso.getText()));
					verificar.verificarCurso();
					textCurso.setText(verificar.getNomeCurso());
					textInstCurso.setText(verificar.getNomeInst());
				} else {
					JOptionPane.showMessageDialog(null, "Inserir Id");
				}
			}
		});
		btnVerificar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVerificar.setBounds(165, 318, 106, 23);
		contentPane.add(btnVerificar);

		textInstCurso = new JTextField();
		textInstCurso.setColumns(10);
		textInstCurso.setBounds(279, 343, 195, 23);
		contentPane.add(textInstCurso);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 463, 223);
		contentPane.add(scrollPane);

		setTableCurso(new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {      
				return false;
			}
		});

		getTableCurso().setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Curso", "IdInst", "Institui\u00E7\u00E3o", "Cidade", "Estado"
				}
				));
		scrollPane.setViewportView(getTableCurso());

		getTableCurso().getTableHeader().setResizingAllowed(false); // manter table travada

		listarCursos();

		JLabel lblBuscarPorCursos = new JLabel("Buscar por cursos:");
		lblBuscarPorCursos.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuscarPorCursos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBuscarPorCursos.setBounds(10, 11, 145, 21);
		contentPane.add(lblBuscarPorCursos);

		textBuscar = new JTextField();
		textBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				setTableCurso(new JTable() {
					public boolean isCellEditable(int rowIndex, int colIndex) {      
						return false;
					}
				});

				getTableCurso().setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Id", "Curso", "IdInst", "Instituição", "Cidade", "Estado"
						}
						));
				scrollPane.setViewportView(table);

				getTableCurso().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.

				listarPesquisa(textBuscar.getText());
			}
		});
		textBuscar.setColumns(10);
		textBuscar.setBounds(149, 11, 325, 23);
		contentPane.add(textBuscar);
	}
	public void setTableCurso(JTable table) {
		this.table = table;
	}

	public JTable getTableCurso() {
		return table;
	}

	public void listarCursos() {
		Listas dao = new Listas();
		List<Curso> lista = dao.listarCursos();

		DefaultTableModel dados = (DefaultTableModel) getTableCurso().getModel();

		dados.setNumRows(0);
		for (Curso a: lista) {
			dados.addRow(new Object [] {
					a.getIdCurso(),
					a.getCurso(),
					a.getIdInst(),
					a.getInstituicao(),
					a.getCidade(),
					a.getEstado(),
			});
		}
	}

	public void listarPesquisa(String texto) {
		BuscarPor dao = new BuscarPor();
		List<Curso> lista = dao.buscarCurso(texto);

		DefaultTableModel dados = (DefaultTableModel) getTableCurso().getModel();
		dados.setNumRows(0);
		for (Curso a: lista) {
			dados.addRow(new Object[] {
					a.getIdCurso(),
					a.getCurso(),
					a.getIdInst(),
					a.getInstituicao(),
					a.getCidade(),
					a.getEstado(),
			});
		}

	}

	public boolean testeGraduacao() {
		boolean teste = false;

		String graduacao = (String)comGraduacao.getSelectedItem();

		if (graduacao.equals("<Selecionar>")) {
			teste = true;
		} else {
			teste = false;
		}
		return teste;
	}

	public  void MatricularProfessor() {
		ConexaoMysql conn = new ConexaoMysql();

		if (!testeGraduacao()) {
			if (podeAdicionar()) {

				try {
					String nome = textNome.getText();
					String CPF = textCPF.getText();
					String nascimento = textNascimento.getText();
					int idCurso = Integer.parseInt(textIdCurso.getText());
					String graduacao = (String)comGraduacao.getSelectedItem();
					String login = textEmail.getText();
					String senha = textSenha.getText();

					conn.conectarMySQL();

					String query = "INSERT INTO professor (Nome, CPF, Nascimento, IdCurso, Graduacao, Login, Senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
					preparedStatement.setString(1, nome);
					preparedStatement.setString(2,CPF );
					preparedStatement.setString(3, nascimento);
					preparedStatement.setInt(4, idCurso);
					preparedStatement.setString(5, graduacao);
					preparedStatement.setString(6, login);
					preparedStatement.setString(7, senha);

					preparedStatement.executeUpdate();

					conn.FecharConexao();

					textNome.setText("");
					textCPF.setText("");
					textNascimento.setText("");
					textIdCurso.setText("");
					comGraduacao.setSelectedItem("<Selecione>");
					textEmail.setText("");
					textSenha.setText("");
					textCurso.setText("");
					textInstCurso.setText("");

					JOptionPane.showMessageDialog(null, "Professor Matriculado com Sucesso");

					ADM admin = new ADM();
					admin.setVisible(true);
					setVisible(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro!\nVerifique se todos os campos estão prenchidos.");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione o nivel de graduação");
		}
	}

	public boolean podeAdicionar() {
		boolean testeSeTem = false;



		try {
			String CPF = textCPF.getText();

			ConexaoMysql conn = new ConexaoMysql();
			conn.conectarMySQL();
			String query = "SELECT* FROM professor WHERE CPF = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, CPF);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				testeSeTem = false;
			} else {
				testeSeTem = true;
			}

			conn.FecharConexao();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Informe CPF");
		}

		return testeSeTem;
	}
}
