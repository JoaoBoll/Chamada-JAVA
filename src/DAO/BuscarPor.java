package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ConexaoSql.ConexaoMysql;
import Model.*;

public class BuscarPor {
	
	private JFrame table;
		
	public List<Instituicao> buscarInstituicao(String string) {
		
		List<Instituicao> lista = new ArrayList<>();
		ConexaoMysql conn = new ConexaoMysql();
		
		try {
			conn.conectarMySQL();
			String query = "SELECT * FROM instituicao WHERE instituicao LIKE ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+string+"%");
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
				Instituicao inst = new Instituicao();
				inst.setIdInst(rs.getInt("IdInst"));
				inst.setInstituicao(rs.getString("Instituicao"));
				inst.setCidade(rs.getString("Cidade"));
				inst.setEstado(rs.getString("Estado"));
				
				lista.add(inst);
			}
			conn.FecharConexao();
			return lista;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Curso> buscarCurso(String string) {
		List<Curso> lista = new ArrayList<Curso>();
		ConexaoMysql conn = new ConexaoMysql();
		
		try {
			conn.conectarMySQL();
			String query = "SELECT IdCurso, Curso, curso.idInst, instituicao, Cidade, Estado FROM curso, instituicao WHERE curso.idInst = instituicao.idInst and curso LIKE ?";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+string+"%");
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
				Curso curso = new Curso();
				curso.setIdCurso(rs.getInt("IdCurso"));
				curso.setCurso(rs.getString("Curso"));
				curso.setIdInst(rs.getInt("IdInst"));
				curso.setInstituicao(rs.getString("Instituicao"));
				curso.setCidade(rs.getString("Cidade"));
				curso.setEstado(rs.getString("Estado"));
				
				lista.add(curso);
			}
			conn.FecharConexao();
			return lista;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public List<Professor> buscarProfessor(String string) {
		List<Professor> lista = new ArrayList<Professor>();
		ConexaoMysql conn = new ConexaoMysql();
		
		try {
			conn.conectarMySQL();
			String query = "SELECT professor.idProf, professor.nome, professor.CPF, professor.Nascimento, professor.idCurso, curso.curso, professor.graduacao, professor.login, professor.senha, instituicao.idInst, instituicao.instituicao, instituicao.Cidade, instituicao.Estado FROM curso, instituicao, professor WHERE professor.IdCurso = curso.Idcurso and curso.IdInst = instituicao.idInst and professor.nome LIKE ?";
			
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+string+"%");
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
				Professor prof = new Professor();
				prof.setIdProf(rs.getInt("idProf"));
				prof.setNome(rs.getString("nome"));
				prof.setCpf(rs.getString("CPF"));
				prof.setNascimento(rs.getString("Nascimento"));
				prof.setIdCurso(rs.getInt("IdCurso"));
				prof.setCurso(rs.getString("Curso"));
				prof.setGraduacao(rs.getString("Graduacao"));
				prof.setLogin(rs.getString("Login"));
				prof.setSenha(rs.getString("Senha"));
				prof.setIdInst(rs.getInt("IdInst"));
				prof.setInstituicao(rs.getString("Instituicao"));
				prof.setCidade(rs.getString("Cidade"));
				prof.setEstado(rs.getString("Estado"));
				
				lista.add(prof);
			}
			conn.FecharConexao();
			return lista;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public List<Aluno> buscarAluno(String string) {
		List<Aluno> lista = new ArrayList<Aluno>();
		ConexaoMysql conn = new ConexaoMysql();
		
		try {
			conn.conectarMySQL();
			String query = "SELECT aluno.matricula, aluno.nome, aluno.nascimento, aluno.cpf, aluno.login, aluno.senha, aluno.IdCurso, curso.curso, instituicao.instituicao, instituicao.cidade, instituicao.estado FROM aluno, curso, instituicao WHERE aluno.idCurso = curso.IdCurso and curso.IdInst = instituicao.IdInst and aluno.nome LIKE ?";
			
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+string+"%");
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setMatricula(rs.getInt("Matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setCPF(rs.getString("CPF"));
				aluno.setNascimento(rs.getString("Nascimento"));
				aluno.setLogin(rs.getString("login"));
				aluno.setSenha(rs.getString("Senha"));
				aluno.setIdCurso(rs.getInt("IdCurso"));
				aluno.setCurso(rs.getString("Curso"));
				aluno.setInstituicao(rs.getString("Instituicao"));
				aluno.setCidade(rs.getString("Cidade"));
				aluno.setEstado(rs.getString("Estado"));
				
				lista.add(aluno);
			}
			conn.FecharConexao();
			return lista;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
