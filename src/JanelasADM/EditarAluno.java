package JanelasADM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import ConexaoSql.ConexaoMysql;
import DAO.BuscarPor;
import DAO.Listas;
import DAO.Verificar;
import Model.Aluno;
import Model.Professor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditarAluno extends JFrame {

	private int idInst;
	private JPanel contentPane;
	private JLabel textInstituicao;
	private JTextField textCPF;
	private JTextField textEmail;
	private JTextField textSenha;
	private JTextField textNome;
	private JTextField textMatricula;
	private JTextField textNascimento;
	private JTextField textIdCurso;
	private JLabel textCurso;
	private JLabel textCidade;
	private JTextField textBuscarNome;
	private JTable table;
	private JLabel textEstado;

	public EditarAluno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.

		JButton btnNewButton_3 = new JButton("Confirmar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarAluno();
			}
		});
		btnNewButton_3.setBounds(524, 433, 120, 23);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(btnNewButton_3);

		JButton btnNewButton_2 = new JButton("Voltar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(654, 433, 120, 23);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(btnNewButton_2);

		textInstituicao = new JLabel();
		textInstituicao.setBounds(407, 400, 105, 23);
		textInstituicao.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(textInstituicao);

		try {
			textCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textCPF.setBounds(310, 340, 129, 23);
		textCPF.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textCPF.setColumns(10);
		getContentPane().add(textCPF);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(383, 310, 107, 23);
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblSenha);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(383, 280, 107, 23);
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(500, 280, 274, 23);
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textEmail.setColumns(10);
		getContentPane().add(textEmail);

		textSenha = new JTextField();
		textSenha.setBounds(500, 310, 274, 23);
		textSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textSenha.setColumns(10);
		getContentPane().add(textSenha);

		JButton btnNewButton = new JButton("Verificar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teste = textMatricula.getText();
				if (!teste.equals("")) {
					Verificar verificar = new Verificar();
					verificar.setMatricula(Integer.parseInt(textMatricula.getText()));
					verificar.verificarAluno();
					textNome.setText(verificar.getNomeAluno());
					textNascimento.setText(verificar.getNascimentoAluno());
					textCPF.setText(verificar.getCpfAluno());
					textIdCurso.setText(Integer.toString(verificar.getIdCurso()));
					textCurso.setText(verificar.getNomeCurso());
					textInstituicao.setText(verificar.getNomeInst());
					textCidade.setText(verificar.getCidade());
					textEstado.setText(verificar.getEstado());
					textEmail.setText(verificar.getLoginAluno());
					textSenha.setText(verificar.getSenhaAluno());
					
				} else {
					JOptionPane.showMessageDialog(null, "Insira Matrícula");
				}
			}
		});
		btnNewButton.setBounds(266, 280, 107, 23);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(btnNewButton);

		textNome = new JTextField();
		textNome.setBounds(127, 310, 246, 23);
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textNome.setColumns(10);
		getContentPane().add(textNome);

		textMatricula = new JTextField();
		textMatricula.setBounds(127, 280, 129, 23);
		textMatricula.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textMatricula.setColumns(10);
		getContentPane().add(textMatricula);

		JLabel lblMatricula = new JLabel("Matr\u00EDcula");
		lblMatricula.setBounds(10, 280, 107, 23);
		lblMatricula.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblMatricula);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 310, 107, 23);
		lblNome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblNome);

		JLabel lblNascimento = new JLabel("Nascimento:");
		lblNascimento.setBounds(10, 340, 107, 23);
		lblNascimento.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNascimento.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblNascimento);

		try {
			textNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textNascimento.setBounds(127, 340, 129, 23);
		textNascimento.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textNascimento.setColumns(10);
		getContentPane().add(textNascimento);

		JLabel lblIdCurso = new JLabel("IdCurso");
		lblIdCurso.setBounds(10, 370, 107, 23);
		lblIdCurso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIdCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblIdCurso);

		textIdCurso = new JTextField();
		textIdCurso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Verificar verificar = new Verificar();
				verificar.setIdCurso(Integer.parseInt(textIdCurso.getText()));
				verificar.verificarCurso();
				textCurso.setText(verificar.getNomeCurso());
				textInstituicao.setText(verificar.getNomeInst());
				textCidade.setText(verificar.getCidade());
				textEstado.setText(verificar.getEstado());
			}
		});
		textIdCurso.setBounds(127, 370, 129, 23);
		textIdCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textIdCurso.setColumns(10);
		getContentPane().add(textIdCurso);

		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(10, 400, 107, 23);
		lblCurso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblCurso);

		textCurso = new JLabel();
		textCurso.setBounds(127, 400, 182, 23);
		textCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(textCurso);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 430, 107, 23);
		lblCidade.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblCidade);

		textCidade = new JLabel();
		textCidade.setBounds(127, 430, 129, 23);
		textCidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(textCidade);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(266, 430, 84, 23);
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblEstado);

		JLabel lblNewLabel = new JLabel("Institui\u00E7\u00E3o:");
		lblNewLabel.setBounds(319, 400, 84, 23);
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblNewLabel);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(266, 340, 34, 23);
		lblCpf.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblCpf);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 764, 225);
		contentPane.add(scrollPane);

		setTableAluno(new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {      
				return false;
			}
		});

		getTableAluno().setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Matricula", "Nome", "CPF", "Nascimento","Id", "Curso", "Instituição", "Cidade", "Estado"
				}
				));
		scrollPane.setViewportView(table);

		getTableAluno().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.
		getTableAluno().getColumnModel().getColumn(0).setPreferredWidth(5);
		getTableAluno().getColumnModel().getColumn(4).setPreferredWidth(5);
		
		
		
		listarAlunos();

		textBuscarNome = new JTextField();
		textBuscarNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				setTableAluno(new JTable() {
					public boolean isCellEditable(int rowIndex, int colIndex) {      
						return false;
					}
				});

				getTableAluno().setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Matricula", "Nome", "CPF", "Nascimento","Id", "Curso", "Instituição", "Cidade", "Estado"
						}
						));
				scrollPane.setViewportView(table);

				getTableAluno().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.
				getTableAluno().getColumnModel().getColumn(0).setPreferredWidth(5);

				listarPesquisa(textBuscarNome.getText());
			}
		});
		textBuscarNome.setBounds(127, 11, 246, 23);
		textBuscarNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textBuscarNome.setColumns(10);
		getContentPane().add(textBuscarNome);

		JLabel lblBuscarNome = new JLabel("Buscar Nome:");
		lblBuscarNome.setBounds(10, 11, 107, 23);
		lblBuscarNome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBuscarNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(lblBuscarNome);



		textEstado = new JLabel();
		textEstado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textEstado.setBounds(360, 430, 152, 23);
		contentPane.add(textEstado);

		JButton btnNewButton_3_1 = new JButton("Ver Cursos");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerCursos cursos = new VerCursos();
				cursos.setVisible(true);
			}
		});
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_3_1.setBounds(266, 370, 120, 23);
		contentPane.add(btnNewButton_3_1);


	}

	public void setTableAluno(JTable table) {
		this.table = table;
	}

	public JTable getTableAluno() {
		return table;
	}

	public void listarAlunos() {
		Listas dao = new Listas();
		List<Aluno> lista = dao.listarAlunos();

		DefaultTableModel dados = (DefaultTableModel) getTableAluno().getModel();

		dados.setNumRows(0);
		for (Aluno a: lista) {
			dados.addRow(new Object[] {
					a.getMatricula(),
					a.getNome(),
					a.getCPF(),
					a.getNascimento(),
					a.getIdCurso(),
					a.getCurso(),
					a.getInstituicao(),
					a.getCidade(),
					a.getEstado()
			});

		}
	}
	public void listarPesquisa(String texto) {
		BuscarPor dao = new BuscarPor();
		List<Aluno> lista = dao.buscarAluno(texto);

		DefaultTableModel dados = (DefaultTableModel) getTableAluno().getModel();
		dados.setNumRows(0);
		for (Aluno a: lista) {
			dados.addRow(new Object[] {
					a.getMatricula(),
					a.getNome(),
					a.getCPF(),
					a.getNascimento(),
					a.getIdCurso(),
					a.getCurso(),
					a.getInstituicao(),
					a.getCidade(),
					a.getEstado()
			});
		}
	}
	public  void editarAluno() {
		ConexaoMysql conn = new ConexaoMysql();

		if (podeAdicionar()) {

			try {
				int idAluno = Integer.parseInt(textMatricula.getText());
				String nome = textNome.getText();
				String CPF = textCPF.getText();
				String nascimento = textNascimento.getText();
				int idCurso = Integer.parseInt(textIdCurso.getText());
				String login = textEmail.getText();
				String senha = textSenha.getText();

				conn.conectarMySQL();

				String query = "UPDATE aluno SET Nome = ?, CPF = ?, Nascimento = ?, IdCurso = ?, Login = ?, Senha = ? WHERE matricula = ?";
				PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
				preparedStatement.setString(1, nome);
				preparedStatement.setString(2,CPF );
				preparedStatement.setString(3, nascimento);
				preparedStatement.setInt(4, idCurso);
				preparedStatement.setString(5, login);
				preparedStatement.setString(6, senha);
				preparedStatement.setInt(7, idAluno);

				preparedStatement.executeUpdate();

				conn.FecharConexao();

				textMatricula.setText("");
				textNome.setText("");
				textCPF.setText("");
				textNascimento.setText("");
				textIdCurso.setText("");
				textEmail.setText("");
				textSenha.setText("");
				textCurso.setText("");
				textInstituicao.setText("");
				textCidade.setText("");
				textEstado.setText("");

				JOptionPane.showMessageDialog(null, "Aluno editado com sucesso!");

				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro!\nVerifique se todos os campos estão prenchidos.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao alterar CPF");
		}
	}

	public boolean podeAdicionar() {
		boolean testeSeTem = false;

		try {
			String CPF = textCPF.getText();
			int matricula = Integer.parseInt(textMatricula.getText());

			ConexaoMysql conn = new ConexaoMysql();
			conn.conectarMySQL();
			String query = "SELECT COUNT(*) AS total FROM aluno WHERE CPF = ? and matricula != ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, CPF);
			preparedStatement.setInt(2, matricula);

			ResultSet rs = preparedStatement.executeQuery();

			int test = 0;
			if (rs.first()) {
				test = rs.getInt("total");
			}
			
			if (test == 0) {
				testeSeTem = true;
			} else {
				testeSeTem = false;
			}

			conn.FecharConexao();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return testeSeTem;
	}
}
