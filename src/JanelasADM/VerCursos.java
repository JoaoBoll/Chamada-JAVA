package JanelasADM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.BuscarPor;
import DAO.Listas;
import Model.Curso;

import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerCursos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	public VerCursos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(385, 427, 89, 23);
		contentPane.add(btnVoltar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 464, 376);
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
		
		JLabel lblBuscarCurso = new JLabel("Buscar nome cursos:");
		lblBuscarCurso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBuscarCurso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBuscarCurso.setBounds(10, 11, 155, 21);
		contentPane.add(lblBuscarCurso);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
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
								"Id","Cuso", "Instituicao", "Cidade", "Estado"
						}
						));
				scrollPane.setViewportView(table);

				//getTableCurso().getTableHeader().setResizingAllowed(false); // manter table travada
				getTableCurso().getTableHeader().setReorderingAllowed(false); // não reodernar coluna na tabela.

				listarPesquisa(textField.getText());
			}
		});
		textField.setColumns(10);
		textField.setBounds(175, 11, 299, 23);
		contentPane.add(textField);
		
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
}
