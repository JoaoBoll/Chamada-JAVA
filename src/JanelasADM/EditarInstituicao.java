package JanelasADM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.MysqlConnection;

import ConexaoSql.ConexaoMysql;
import DAO.BuscarPor;
import DAO.Listas;
import DAO.Verificar;
import Model.Instituicao;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditarInstituicao extends JFrame {

	private JPanel contentPane;
	private JTextField textIdInst;
	private JTextField textInstituicao;
	private JTextField textCidade;
	private JTextField textEstado;
	private JTable table;
	private JTextField textBuscar;

	public EditarInstituicao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //Colocar tela no centro--- Editar o setBounds para setSize.

		JLabel lblIdInstituicao = new JLabel("ID:");
		lblIdInstituicao.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIdInstituicao.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdInstituicao.setBounds(10, 296, 95, 21);
		contentPane.add(lblIdInstituicao);

		textIdInst = new JTextField();
		textIdInst.setColumns(10);
		textIdInst.setBounds(115, 299, 75, 21);
		contentPane.add(textIdInst);

		JLabel lblInstituio_1 = new JLabel("Institui\u00E7\u00E3o:");
		lblInstituio_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInstituio_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInstituio_1.setBounds(10, 328, 95, 21);
		contentPane.add(lblInstituio_1);

		textInstituicao = new JTextField();
		textInstituicao.setColumns(10);
		textInstituicao.setBounds(115, 331, 359, 21);
		contentPane.add(textInstituicao);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCidade.setBounds(10, 360, 95, 21);
		contentPane.add(lblCidade);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEstado.setBounds(10, 392, 95, 21);
		contentPane.add(lblEstado);

		textCidade = new JTextField();
		textCidade.setColumns(10);
		textCidade.setBounds(115, 363, 359, 21);
		contentPane.add(textCidade);

		textEstado = new JTextField();
		textEstado.setColumns(10);
		textEstado.setBounds(115, 395, 359, 21);
		contentPane.add(textEstado);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarDados();
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAlterar.setBounds(200, 427, 107, 23);
		contentPane.add(btnAlterar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(367, 427, 107, 23);
		contentPane.add(btnVoltar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 464, 249);
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

		
		getTableInstituicao().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.

		listarInstituicao();

		JLabel lblIdInstituicao_1 = new JLabel("Buscar Institui\u00E7\u00E3o:");
		lblIdInstituicao_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIdInstituicao_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdInstituicao_1.setBounds(10, 11, 139, 23);
		contentPane.add(lblIdInstituicao_1);

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

				
				getTableInstituicao().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.

				listarPesquisa(textBuscar.getText());
			}
		});
		textBuscar.setColumns(10);
		textBuscar.setBounds(151, 11, 323, 23);
		contentPane.add(textBuscar);

		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teste = textIdInst.getText();
				if (!teste.equals("")) {
				Verificar verificar = new Verificar();
				verificar.setIdInst(Integer.parseInt(textIdInst.getText()));
				verificar.verificarInstituicao();
				textInstituicao.setText(verificar.getNomeInst());
				textCidade.setText(verificar.getCidade());
				textEstado.setText(verificar.getEstado());
				} else {
					JOptionPane.showMessageDialog(null, "Inserir Id");
				}
			}
		});
		btnVerificar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVerificar.setBounds(200, 298, 107, 23);
		contentPane.add(btnVerificar);
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
	public void atualizarDados() {
		ConexaoMysql conn = new ConexaoMysql();

		if (podeAdicionar()) {

			try {
				conn.conectarMySQL();
				
				int id = Integer.parseInt(textIdInst.getText());
				String nome = textInstituicao.getText();
				String cidade = textCidade.getText();
				String estado = textEstado.getText();

				String query = "Update instituicao set Instituicao = ?, cidade = ?, estado = ? WHERE idInst = ?";
				PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
				preparedStatement.setString(1, nome);
				preparedStatement.setString(2, cidade);
				preparedStatement.setString(3, estado);
				preparedStatement.setInt(4, id);
				
				preparedStatement.executeUpdate();
				
				conn.FecharConexao();
				
				textIdInst.setText("");
				textInstituicao.setText("");
				textCidade.setText("");
				textEstado.setText("");
				
				JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso!");
				
				ADM admin = new ADM();
				admin.setVisible(true);
				setVisible(false);
						
			} catch (Exception e) {
				
			}
		} else {
			JOptionPane.showMessageDialog(null, "Instituição já existente.");
		}
	}
	
	public boolean podeAdicionar() {
		boolean testeSeTem = false;



		try {
			String testeInst = textInstituicao.getText();
			String testeCidade = textCidade.getText();
			String testeEstado = textEstado.getText();			

			ConexaoMysql conn = new ConexaoMysql();
			conn.conectarMySQL();
			String query = "SELECT* FROM instituicao WHERE Instituicao = ? and Cidade = ? and Estado = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, testeInst);
			preparedStatement.setString(2, testeCidade);
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
