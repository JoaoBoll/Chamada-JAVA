package Model;

public class Curso extends Instituicao{

	private int idCurso;
	private String curso;
	private int idInst;
	
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public int getIdInst() {
		return idInst;
	}
	public void setIdInst(int idInst) {
		this.idInst = idInst;
	}
	
}
