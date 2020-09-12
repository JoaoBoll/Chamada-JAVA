package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConexaoSql.ConexaoMysql;
import Model.*;

public class Listas {

	public List<Aluno> listarAlunos() {

		List<Aluno> lista = new ArrayList<>();
		ConexaoMysql conn = new ConexaoMysql();
		try {
			conn.conectarMySQL();
			String query = "SELECT aluno.matricula, aluno.nome, aluno.nascimento, aluno.cpf, aluno.idCurso, curso.curso, instituicao.instituicao, instituicao.cidade, instituicao.estado FROM aluno, curso, instituicao WHERE aluno.IdCurso = curso.IdCurso and curso.IdInst = instituicao.IdInst";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setMatricula(rs.getInt("Matricula"));
				aluno.setNome(rs.getString("Nome"));
				aluno.setNascimento(rs.getString("Nascimento"));
				aluno.setCPF(rs.getString("CPF"));
				aluno.setIdCurso(rs.getInt("idCurso"));
				aluno.setCurso(rs.getString("Curso"));
				aluno.setInstituicao(rs.getString("Instituicao"));
				aluno.setCidade(rs.getString("Cidade"));
				aluno.setEstado(rs.getString("Estado"));

				lista.add(aluno);
			}
			conn.FecharConexao();
			return lista;

		} catch (Exception e) {
			return null;
		}

	}

	public List<Professor> listarProfessor() {

		List<Professor> lista = new ArrayList<>();
		ConexaoMysql conn = new ConexaoMysql();
		try {
			conn.conectarMySQL();
			String resultado = "";
			String query = "SELECT professor.idProf, professor.nome, professor.CPF, professor.Nascimento, professor.idCurso, curso.curso, professor.graduacao, professor.login, professor.senha, instituicao.idInst, instituicao.instituicao, instituicao.Cidade, instituicao.Estado FROM curso, instituicao, professor WHERE professor.IdCurso = curso.Idcurso and curso.IdInst = instituicao.idInst";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
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
			return null;
		}

	}

	public List<Instituicao> listarInstituicao() {

		List<Instituicao> lista = new ArrayList<>();
		ConexaoMysql conn = new ConexaoMysql();
		try {
			conn.conectarMySQL();
			String query = "Select* from instituicao";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Instituicao instituicao = new Instituicao();
				instituicao.setIdInst(rs.getInt("IdInst"));
				instituicao.setInstituicao(rs.getString("Instituicao"));
				instituicao.setCidade(rs.getString("Cidade"));
				instituicao.setEstado(rs.getString("Estado"));

				lista.add(instituicao);
			}
			conn.FecharConexao();
			return lista;

		} catch (Exception e) {
			return null;
		}

	}
	
	public List<Curso>listarCursos() {
		List<Curso> lista = new ArrayList<>();
		ConexaoMysql conn = new ConexaoMysql();
		
		try {
			conn.conectarMySQL();
			String query = "SELECT idCurso, curso, curso.IdInst, Instituicao, cidade, estado FROM curso, instituicao WHERE curso.idInst = instituicao.idInst";
			PreparedStatement preparedStatement = conn.connection.prepareStatement(query);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Curso list = new Curso();
				list.setIdCurso(rs.getInt("IdCurso"));
				list.setCurso(rs.getString("Curso"));
				list.setIdInst(rs.getInt("IdInst"));
				list.setInstituicao(rs.getString("Instituicao"));
				list.setCidade(rs.getString("Cidade"));
				list.setEstado(rs.getString("Estado"));
				
				lista.add(list);
			}
			
			conn.FecharConexao();
					
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}

	
}
