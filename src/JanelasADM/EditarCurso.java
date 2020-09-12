package JanelasADM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConexaoSql.ConexaoMysql;
import DAO.BuscarPor;
import DAO.Listas;
import DAO.Verificar;
import Model.Curso;
import Model.Instituicao;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class EditarCurso extends JFrame {

	private JPanel contentPane;
	private JTextField textIdCurso;
	private JTextField textCurso;
	private JTextField textBuscar;
	private JTable table;
	JLabel lblInstCurso;
	private int IdInst;

	public EditarCurso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(10, 11, 474, 0);
		contentPane.add(horizontalBox);

		JLabel lblInstituio = new JLabel("Institui\u00E7\u00E3o:");
		lblInstituio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInstituio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInstituio.setBounds(10, 398, 95, 21);
		contentPane.add(lblInstituio);

		JLabel lblId = new JLabel("Id:");
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblId.setBounds(10, 340, 95, 21);
		contentPane.add(lblId);

		textIdCurso = new JTextField();
		textIdCurso.setColumns(10);
		textIdCurso.setBounds(115, 340, 86, 21);
		contentPane.add(textIdCurso);

		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCurso.setBounds(10, 369, 95, 21);
		contentPane.add(lblCurso);

		textCurso = new JTextField();
		textCurso.setColumns(10);
		textCurso.setBounds(115, 372, 364, 21);
		contentPane.add(textCurso);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Verificar verificar = new Verificar();
				verificar.setIdCurso(Integer.parseInt(textIdCurso.getText()));
				verificar.verificarCurso();
				IdInst = verificar.getIdInst();
				atualizarDados();
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnConfirmar.setBounds(211, 433, 119, 23);
		contentPane.add(btnConfirmar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(358, 433, 119, 23);
		contentPane.add(btnVoltar);

		JLabel lblBuscarCurso = new JLabel("Buscar Curso:");
		lblBuscarCurso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBuscarCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBuscarCurso.setBounds(10, 11, 103, 21);
		contentPane.add(lblBuscarCurso);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 464, 283);
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
				"Id", "Curso", "Institui\u00E7\u00E3o", "Cidade", "Estado"
			}
		));
		scrollPane.setViewportView(table);

		getTableCurso().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.

		listarCurso();
		
		lblInstCurso = new JLabel("");
		lblInstCurso.setHorizontalAlignment(SwingConstants.LEFT);
		lblInstCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInstCurso.setBounds(115, 398, 362, 21);
		contentPane.add(lblInstCurso);

		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teste = textIdCurso.getText();
				if (!teste.equals("")) {
				Verificar verificar = new Verificar();
				verificar.setIdCurso(Integer.parseInt(textIdCurso.getText()));
				verificar.verificarCurso();
				textCurso.setText(verificar.getNomeCurso());
				lblInstCurso.setText(verificar.getNomeInst());
				} else {
					JOptionPane.showMessageDialog(null, "Insira ID");
				}
			}
		});
		btnVerificar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVerificar.setBounds(211, 340, 119, 23);
		contentPane.add(btnVerificar);
		
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
								"Id", "Curso", "Instituição", "Cidade", "Estado"
						}
						));
				
				scrollPane.setViewportView(table);

				getTableCurso().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.

				listarPesquisa(textBuscar.getText());
			}
		});
		textBuscar.setColumns(10);
		textBuscar.setBounds(123, 11, 351, 21);
		contentPane.add(textBuscar);
		
		
	}

	public void setTableCurso(JTable table) {
		this.table = table;
	}

	public JTable getTableCurso() {
		return table;
	}

	public void listarCurso() {
		Listas dao = new Listas();
		List<Curso> lista = dao.listarCursos();

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
	
	public void atualizarDados() {
		ConexaoMysql conn = new ConexaoMysql();

		if (podeAdicionar()) {

			try {
				conn.conectarMySQL();
				
				String curso = textCurso.getText();
				int id = Integer.parseInt(textIdCurso.getText());

				String query = "Update curso set curso = ? WHERE idCurso = ?";
				PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
				preparedStatement.setString(1, curso);
				preparedStatement.setInt(2, id);
				
				preparedStatement.executeUpdate();
				
				conn.FecharConexao();
				
				textBuscar.setText("");
				textCurso.setText("");
				textIdCurso.setText("");
				lblInstCurso.setText("");
				
				JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso!");
				
				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);
						
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Instituição já existente.");
		}
	}
	
	public boolean podeAdicionar() {
		boolean testeSeTem = false;



		try {
			String testeCurso = textCurso.getText();
			int idinstituicao = this.IdInst;
			
			ConexaoMysql conn = new ConexaoMysql();
			conn.conectarMySQL();
			String query = "SELECT* FROM curso WHERE curso = ? and IdInst = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, testeCurso);
			preparedStatement.setInt(2, idinstituicao);


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
