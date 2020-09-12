package JanelasADM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
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
import DAO.*;
import Model.Curso;
import Model.Instituicao;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

public class MatricularAluno extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCurso;
	private JLabel lblEmail;
	private JTextField textEmail;
	private JLabel lblSenha;
	private JTextField textSenha;
	private JTextField textNasc;
	private JTextField textCPF;
	private JTextField textNomeCurso;
	private JButton btnVerificar;
	private JScrollPane scrollPane;
	private JTable table;
	private int definirIdInstituicao;	private JLabel lblBuscarCurso;
	private JTextField textBuscar;

	public MatricularAluno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNome.setBounds(10, 243, 93, 21);
		contentPane.add(lblNome);

		JLabel lblNascimento = new JLabel("Nascimento:");
		lblNascimento.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNascimento.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNascimento.setBounds(10, 273, 93, 21);
		contentPane.add(lblNascimento);

		JLabel lblCurso = new JLabel("Id Curso:");
		lblCurso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCurso.setBounds(10, 303, 93, 21);
		contentPane.add(lblCurso);

		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textNome.setColumns(10);
		textNome.setBounds(113, 243, 361, 23);
		contentPane.add(textNome);

		textCurso = new JTextField();
		textCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textCurso.setColumns(10);
		textCurso.setBounds(113, 303, 67, 23);
		contentPane.add(textCurso);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCpf.setBounds(10, 333, 93, 21);
		contentPane.add(lblCpf);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(385, 427, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnCadastrarAluno = new JButton("Cadastrar");
		btnCadastrarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matricularAluno();
			}
		});
		btnCadastrarAluno.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCadastrarAluno.setBounds(186, 427, 111, 23);
		contentPane.add(btnCadastrarAluno);

		lblEmail = new JLabel("E-Mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(10, 363, 93, 21);
		contentPane.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textEmail.setColumns(10);
		textEmail.setBounds(113, 363, 361, 23);
		contentPane.add(textEmail);

		lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSenha.setBounds(10, 393, 93, 21);
		contentPane.add(lblSenha);

		textSenha = new JTextField();
		textSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textSenha.setColumns(10);
		textSenha.setBounds(113, 393, 361, 23);
		contentPane.add(textSenha);

		try {
			textNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		textNasc.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textNasc.setColumns(10);
		textNasc.setBounds(113, 273, 361, 23);
		contentPane.add(textNasc);

		try {
			textCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
			textCPF.setFont(new Font("Tahoma", Font.PLAIN, 17));
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "CPF incorreto");
			e1.printStackTrace();
		}
		textCPF.setColumns(10);
		textCPF.setBounds(113, 333, 361, 23);
		contentPane.add(textCPF);

		textNomeCurso = new JTextField();
		textNomeCurso.setFont(new Font("Tahoma",Font.PLAIN, 15));
		textNomeCurso.setColumns(10);
		textNomeCurso.setBounds(307, 303, 167, 23);
		contentPane.add(textNomeCurso);

		btnVerificar = new JButton("Verificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teste = textCurso.getText();
				if (!teste.equals("")) {
				Verificar curso = new Verificar();
				curso.setIdCurso(Integer.parseInt(textCurso.getText()));
				curso.verificarCurso();
				definirIdInstituicao = curso.getIdInst();
				textNomeCurso.setText(curso.getNomeCurso());
				} else {
					JOptionPane.showMessageDialog(null, "Inserir ID");
				}
			}
		});
		btnVerificar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVerificar.setBounds(190, 303, 107, 23);
		contentPane.add(btnVerificar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 464, 189);
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
						"Id", "Curso", "Instituicao", "Cidade", "Estado"
				}
				));
		scrollPane.setViewportView(table);

		lblBuscarCurso = new JLabel("Buscar nome cursos:");
		lblBuscarCurso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBuscarCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBuscarCurso.setBounds(10, 11, 155, 21);
		contentPane.add(lblBuscarCurso);

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
								"Id", "Instituicao", "Cidade", "Estado"
						}
						));
				scrollPane.setViewportView(table);

				//getTableCurso().getTableHeader().setResizingAllowed(false); // manter table travada
				getTableCurso().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.

				listarPesquisa(textBuscar.getText());
			}
		});		

		textBuscar.setColumns(10);
		textBuscar.setBounds(175, 11, 299, 23);
		contentPane.add(textBuscar);

		//getTableCurso().getTableHeader().setResizingAllowed(false); // manter table travada
		getTableCurso().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.

		listarCursos();		

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
					a.getInstituicao(),
					a.getCidade(),
					a.getEstado(),
			});
		}

	}

	public void matricularAluno() {
		ConexaoMysql conn = new ConexaoMysql();
		

		if(podeAdicionar())	{
			try {
				String nome = textNome.getText();
				String nascimento = textNasc.getText();
				String cpf = textCPF.getText();
				String idCurso = textCurso.getText();
				int idInst = this.definirIdInstituicao;
				String email = textEmail.getText();
				String senha = textSenha.getText();
				conn.conectarMySQL();

				String query = "INSERT INTO aluno (nome,CPF, Nascimento, IdCurso, IdInst, login, senha) VALUES (?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
				preparedStatement.setString(1, nome);
				preparedStatement.setString(2, cpf);
				preparedStatement.setString(3, nascimento);
				preparedStatement.setInt(4,Integer.parseInt(idCurso));
				preparedStatement.setInt(5, idInst);
				preparedStatement.setString(6, email);
				preparedStatement.setString(7, senha);

				preparedStatement.executeUpdate();

				conn.FecharConexao();
				textNasc.setText("");
				textBuscar.setText("");
				textCPF.setText("");
				textCurso.setText("");
				textEmail.setText("");
				textSenha.setText("");
				textNome.setText("");
				textNomeCurso.setText("");
				
				JOptionPane.showMessageDialog(null, "Aluno matriculado");
				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);
				
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Falha ao matricular!\nVerifique os dados selecionados");
			}
		} else {
			JOptionPane.showMessageDialog(null, "CPF já regitrado.");
		}
	}
	public boolean podeAdicionar() {
		boolean testeSeTem = false;

		String testeCPF = textCPF.getText();

		try {

			ConexaoMysql conn = new ConexaoMysql();
			conn.conectarMySQL();
			String query = "SELECT* FROM aluno WHERE CPF = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, testeCPF);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				testeSeTem = false;
			} else {
				testeSeTem = true;
			}

			conn.FecharConexao();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return testeSeTem;
	}

}
