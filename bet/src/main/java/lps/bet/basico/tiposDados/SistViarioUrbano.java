package lps.bet.basico.tiposDados ;

public class SistViarioUrbano {

	private String nomeCidade;
	private int viacaoID;
	private EmpresaViaria empresaViaria;
	private java.util.Collection tarifas;
	private java.util.Collection linhas;

	public String getNomeCidade () {
		return nomeCidade;
	} 
	public void setNomeCidade (String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public int getViacaoID () {
		return viacaoID;
	} 
	public void setViacaoID (int viacaoID) {
		this.viacaoID = viacaoID;
	}

	public EmpresaViaria getEmpresaViaria() {
		return empresaViaria;
	}
	public void setEmpresaViaria(EmpresaViaria empresaViaria) {
		this.empresaViaria = empresaViaria;
	}

	public java.util.Collection getTarifas() {
		return this.tarifas;
	}

	public void setTarifas(java.util.Collection tarifas) {
		this.tarifas = tarifas;
	}
	public java.util.Collection getLinhas() {
		return this.linhas;
	}

	public void setLinhas(java.util.Collection linhas) {
		this.linhas = linhas;
	}

}