package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import ConexaoSql.ConexaoMysql;

public class Verificar {

	private int idInst;
	private int IdCurso;
	private String nomeCurso;
	private String nomeInst;
	private String Cidade;
	private String Estado;
	private int idProf;
	private String nomeProf;
	private String CpfProf;
	private String nascimentoProf;
	private String emailProf;
	private String senhaProf;
	private String graduacaoProf;
	private int matricula;
	private String nomeAluno;
	private String CpfAluno;
	private String nascimentoAluno;
	private String loginAluno;
	private String senhaAluno;
	
	
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getCpfAluno() {
		return CpfAluno;
	}
	public void setCpfAluno(String cpfAluno) {
		CpfAluno = cpfAluno;
	}
	public String getNascimentoAluno() {
		return nascimentoAluno;
	}
	public void setNascimentoAluno(String nascimentoAluno) {
		this.nascimentoAluno = nascimentoAluno;
	}
	public String getLoginAluno() {
		return loginAluno;
	}
	public void setLoginAluno(String loginAluno) {
		this.loginAluno = loginAluno;
	}
	public String getSenhaAluno() {
		return senhaAluno;
	}
	public void setSenhaAluno(String senhaAluno) {
		this.senhaAluno = senhaAluno;
	}
	public int getIdProf() {
		return idProf;
	}
	public void setIdProf(int idProf) {
		this.idProf = idProf;
	}
	public String getNomeProf() {
		return nomeProf;
	}
	public void setNomeProf(String nomeProf) {
		this.nomeProf = nomeProf;
	}
	public String getCpfProf() {
		return CpfProf;
	}
	public void setCpfProf(String cpfProf) {
		CpfProf = cpfProf;
	}
	public String getNascimentoProf() {
		return nascimentoProf;
	}
	public void setNascimentoProf(String nascimentoProf) {
		this.nascimentoProf = nascimentoProf;
	}
	public String getEmailProf() {
		return emailProf;
	}
	public void setEmailProf(String emailProf) {
		this.emailProf = emailProf;
	}
	public String getSenhaProf() {
		return senhaProf;
	}
	public void setSenhaProf(String senhaProf) {
		this.senhaProf = senhaProf;
	}
	public String getGraduacaoProf() {
		return graduacaoProf;
	}
	public void setGraduacaoProf(String graduacaoProf) {
		this.graduacaoProf = graduacaoProf;
	}
	public String getCidade() {
		return Cidade;
	}
	public void setCidade(String cidade) {
		Cidade = cidade;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getNomeInst() {
		return nomeInst;
	}
	public void setNomeInst(String nomeInst) {
		this.nomeInst = nomeInst;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public int getIdInst() {
		return idInst;
	}
	public int getIdCurso() {
		return IdCurso;
	}
	public void setIdCurso(int idCurso) {
		IdCurso = idCurso;
	}
	public void setIdInst(int idInst) {
		this.idInst = idInst;
	}
	public void verificarInstituicao() {
		ConexaoMysql conn = new ConexaoMysql();
		String nomeInst = "";
		String cidade = "";
		String estado = "";
		try {
			int instituicao = this.idInst;
			
			conn.conectarMySQL();
			
			String query = "SELECT* FROM instituicao WHERE IdInst = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setInt(1, instituicao);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
			nomeInst = rs.getString("Instituicao");
			cidade = rs.getString("Cidade");
			estado = rs.getString("Estado");
			}
			conn.FecharConexao();
			
			this.nomeInst = nomeInst;
			this.Cidade = cidade;
			this.Estado = estado;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verificarCurso() {
		ConexaoMysql conn = new ConexaoMysql();
		String nomeCurso = "";
		int idInstituicao = 0;
		String nomeInst = "";
		String cidade = "";
		String estado = "";
		try {
			int curso = this.IdCurso;
			
			
			conn.conectarMySQL();
			
			String query = "SELECT curso, instituicao.idInst, Instituicao, cidade, estado FROM curso, instituicao WHERE curso.IdInst = instituicao.IdInst and curso.IdCurso = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setInt(1, curso);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
			nomeCurso = rs.getString("Curso");
			idInstituicao = rs.getInt("idInst");
			nomeInst = rs.getString("Instituicao");
			cidade = rs.getString("Cidade");
			estado = rs.getString("Estado");
			
			
			}
			
			conn.FecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.nomeCurso = nomeCurso;
		this.idInst = idInstituicao;
		this.nomeInst = nomeInst;
		this.Cidade = cidade;
		this.Estado = estado;
	}
	
	public void verificarProfessor() {
		ConexaoMysql conn = new ConexaoMysql();
		
		try {
			int idprof = this.idProf;	
			
			conn.conectarMySQL();
			
			String query = "SELECT professor.idprof, professor.nome, professor.CPF, professor.nascimento, professor.idCurso, professor.graduacao, professor.login, professor.senha, curso.curso, curso.idInst, instituicao.instituicao, instituicao.cidade, instituicao.estado FROM professor, curso, instituicao WHERE professor.IdCurso = curso.IdCurso and curso.IdInst = instituicao.IdInst and professor.IdProf = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setInt(1, idprof);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
			idProf = rs.getInt("IdProf");
			nomeProf = rs.getString("nome");
			nascimentoProf = rs.getString("nascimento");
			IdCurso = rs.getInt("IdCurso");
			CpfProf = rs.getString("CPF");
			graduacaoProf = rs.getString("Graduacao");
			nomeCurso = rs.getString("Curso");
			emailProf = rs.getString("login");
			idInst = rs.getInt("IdInst");
			nomeInst = rs.getString("Instituicao");
			Cidade = rs.getString("Cidade");
			Estado = rs.getString("Estado");
			senhaProf = rs.getString("senha");
			
			}
			
			conn.FecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void verificarAluno() {
		ConexaoMysql conn = new ConexaoMysql();
		
		try {
			int matricula = this.matricula;	
			
			conn.conectarMySQL();
			
			String query = "SELECT aluno.matricula, aluno.nome, aluno.nascimento, aluno.cpf, aluno.login, aluno.senha, aluno.idCurso, curso.curso, instituicao.instituicao, instituicao.cidade, instituicao.estado FROM aluno, curso, instituicao WHERE aluno.IdCurso = curso.IdCurso and curso.IdInst = instituicao.IdInst and aluno.matricula = ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setInt(1, matricula);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
			matricula = rs.getInt("matricula");
			nomeAluno = rs.getString("nome");
			nascimentoAluno = rs.getString("nascimento");
			CpfAluno = rs.getString("CPF");
			IdCurso = rs.getInt("IdCurso");
			nomeCurso = rs.getString("Curso");
			nomeInst = rs.getString("Instituicao");
			Cidade = rs.getString("Cidade");
			Estado = rs.getString("Estado");
			loginAluno = rs.getString("login");
			senhaAluno = rs.getString("senha");
			
			}
			
			conn.FecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
}
