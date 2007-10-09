
package lps.bet.basico.tiposDados;

public class Cargo {

	private int cargoID;
	private String nomeCargo;
	private java.util.Collection funcionarios;

	public String getNomeCargo () {
		return nomeCargo;
	} 
	public void setNomeCargo (String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public int getCargoID () {
		return cargoID;
	} 
	public void setCargoID (int cargoID) {
		this.cargoID = cargoID;
	}

	public java.util.Collection getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(java.util.Collection funcionarios) {
		this.funcionarios = funcionarios;
	}

}