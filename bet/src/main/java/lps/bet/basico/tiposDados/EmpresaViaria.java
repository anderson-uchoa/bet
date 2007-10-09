
package lps.bet.basico.tiposDados ;

public class EmpresaViaria {

	private String nomeFantasia;
	private int empresaID;
	private java.util.Collection funcionarios;

	public String getNomeFantasia () {
		return nomeFantasia;
	} 
	public void setNomeFantasia (String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public int getEmpresaID () {
		return empresaID;
	} 
	public void setEmpresaID (int empresaID) {
		this.empresaID = empresaID;
	}

	public java.util.Collection getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(java.util.Collection funcionarios) {
		this.funcionarios = funcionarios;
	}

}