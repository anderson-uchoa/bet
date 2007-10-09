
package lps.bet.basico.tiposDados ;

public class Funcionario extends Usuario{

	private String nomeFuncionario;
	private java.util.Calendar dtAdmissao;
	private EmpresaViaria empresaViaria;
	private Cargo cargo;
	
	public EmpresaViaria getEmpresaViaria() {
		return empresaViaria;
	}
	public void setEmpresaViaria(EmpresaViaria empresaViaria) {
		this.empresaViaria = empresaViaria;
	}
	
	public String getNomeFuncionario () {
		return nomeFuncionario;
	} 
	public void setNomeFuncionario (String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public java.util.Calendar getDtAdmissao () {
		return dtAdmissao;
	} 
	public void setDtAdmissao (java.util.Calendar dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public Cargo getCargo() {
		return this.cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}