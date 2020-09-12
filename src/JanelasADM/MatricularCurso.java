package JanelasADM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
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

import ConexaoSql.ConexaoMysql;
import DAO.*;
import Model.Instituicao;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MatricularCurso extends JFrame {

	private JPanel contentPane;
	private JLabel textInstituicao;
	private JTextField textIdInst;
	private JTextField textCurso;
	private JTable table;
	private JTextField textBuscar;

	public MatricularCurso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(5, 5, 474, 0);
		contentPane.add(horizontalBox);

		JLabel lblInstituio = new JLabel("Institui\u00E7\u00E3o:");
		lblInstituio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInstituio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInstituio.setBounds(5, 323, 95, 21);
		contentPane.add(lblInstituio);

		textInstituicao = new JLabel();
		textInstituicao.setBounds(110, 326, 364, 21);
		contentPane.add(textInstituicao);

		JLabel lblIdInst = new JLabel("IdInst:");
		lblIdInst.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIdInst.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdInst.setBounds(5, 291, 95, 21);
		contentPane.add(lblIdInst);

		textIdInst = new JTextField();
		textIdInst.setColumns(10);
		textIdInst.setBounds(110, 291, 106, 21);
		contentPane.add(textIdInst);

		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCurso.setBounds(5, 358, 95, 21);
		contentPane.add(lblCurso);

		textCurso = new JTextField();
		textCurso.setColumns(10);
		textCurso.setBounds(110, 361, 364, 21);
		contentPane.add(textCurso);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarCurso();				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(226, 427, 119, 23);
		contentPane.add(btnNewButton);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(355, 427, 119, 23);
		contentPane.add(btnVoltar);
		//-----------------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 46, 364, 235);
		contentPane.add(scrollPane);

		setTableInstituicao(new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {      
				return false;
			}
		});

		getTableInstituicao().setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Instituicao", "Cidade", "Estado"
				}
				));
		scrollPane.setViewportView(table);

		//getTableInstituicao().getTableHeader().setResizingAllowed(false); // manter table travada
		getTableInstituicao().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.

		listarInstituicao();

		//-----------------------------------------------------------------
		JLabel lblInstituicoes = new JLabel("Institui\u00E7\u00F5es:");
		lblInstituicoes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInstituicoes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInstituicoes.setBounds(5, 47, 95, 21);
		contentPane.add(lblInstituicoes);

		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teste = textIdInst.getText();
                if (!teste.equals("")) {
                Verificar verificar = new Verificar();
                verificar.setIdInst(Integer.parseInt(textIdInst.getText()));
                verificar.verificarInstituicao();
                textInstituicao.setText(verificar.getNomeInst());
                } else {
                    JOptionPane.showMessageDialog(null, "Inserir Id");
                }
			}
		});
		btnVerificar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVerificar.setBounds(226, 290, 119, 23);
		contentPane.add(btnVerificar);

		JLabel lblBuscarPorInstituicoes = new JLabel("Buscar nome Institui\u00E7\u00E3o:");
		lblBuscarPorInstituicoes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBuscarPorInstituicoes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBuscarPorInstituicoes.setBounds(5, 14, 185, 21);
		contentPane.add(lblBuscarPorInstituicoes);

		textBuscar = new JTextField();
		textBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				setTableInstituicao(new JTable() {
					public boolean isCellEditable(int rowIndex, int colIndex) {      
						return false;
					}
				});

				getTableInstituicao().setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Id", "Instituicao", "Cidade", "Estado"
						}
						));
				scrollPane.setViewportView(table);

				//getTableInstituicao().getTableHeader().setResizingAllowed(false); // manter table travada
				getTableInstituicao().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.

				listarPesquisa(textBuscar.getText());
			}
		});

		textBuscar.setColumns(10);
		textBuscar.setBounds(200, 13, 274, 21);
		contentPane.add(textBuscar);
	}

	public void cadastrarCurso() {
		ConexaoMysql conn = new ConexaoMysql();

		if(podeAdicionar()) {
			try {
				conn.conectarMySQL();
				String idInst = textIdInst.getText();
				String curso = textCurso.getText();

				String query = "INSERT INTO curso (Curso, IdInst) VALUES (?,?)";

				PreparedStatement preparedStatemente = conn.connection.prepareStatement(query);
				preparedStatemente.setString(1, curso);
				preparedStatemente.setString(2, idInst);

				preparedStatemente.executeUpdate();

				conn.FecharConexao();

				JOptionPane.showMessageDialog(null, "Curso adcionado com sucesso");
				textBuscar.setText("");
				textCurso.setText("");
				textIdInst.setText("");
				textInstituicao.setText("");


			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro Inexperado: "+e);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Curso ja existente na instituição");
		}

	}

	public boolean podeAdicionar() {
		boolean testeSeTem = false;

		

		try {
			String testeIdInst = textIdInst.getText();
			String testeCurso = textCurso.getText();
			
			ConexaoMysql conn = new ConexaoMysql();
			conn.conectarMySQL();
			String query = "SELECT* FROM curso WHERE curso = ? and IdInst = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, testeIdInst);
			preparedStatement.setString(2, testeCurso);

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

	public void setTableInstituicao(JTable table) {
		this.table = table;
	}

	public JTable getTableInstituicao() {
		return table;
	}

	public void listarInstituicao() {
		Listas dao = new Listas();
		List<Instituicao> lista = dao.listarInstituicao();

		DefaultTableModel dados = (DefaultTableModel) getTableInstituicao().getModel();

		dados.setNumRows(0);
		for (Instituicao a: lista) {
			dados.addRow(new Object[] {
					a.getIdInst(),
					a.getInstituicao(),
					a.getCidade(),
					a.getEstado()					
			});

		}
	}
	public void listarPesquisa(String texto) {
		BuscarPor dao = new BuscarPor();
		List<Instituicao> lista = dao.buscarInstituicao(texto);

		DefaultTableModel dados = (DefaultTableModel) getTableInstituicao().getModel();
		dados.setNumRows(0);
		for (Instituicao a: lista) {
			dados.addRow(new Object[] {
				a.getIdInst(),
				a.getInstituicao(),
				a.getCidade(),
				a.getEstado()
			});
		}
	}
}
