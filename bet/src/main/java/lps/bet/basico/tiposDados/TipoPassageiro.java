
package lps.bet.basico.tiposDados ;

public class TipoPassageiro {
 
	private String nomeTipo;
	private String descricaoTipo;
	private String formaPgtoPassagem;
	private int desconto;
	private int tipoID;
	private java.util.Collection cartoes;
	private java.util.Collection tiposIncompativeis;

	public String getNomeTipo () {
		return nomeTipo;
	} 
	public void setNomeTipo (String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}

	public String getDescricaoTipo () {
		return descricaoTipo;
	} 
	public void setDescricaoTipo (String descricaoTipo) {
		this.descricaoTipo = descricaoTipo;
	}

	public String getFormaPgtoPassagem () {
		return formaPgtoPassagem;
	} 
	public void setFormaPgtoPassagem (String formaPgtoPassagem) {
		this.formaPgtoPassagem = formaPgtoPassagem;
	}

	public int getDesconto() {
		return desconto;
	}
	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}

	public int getTipoID () {
		return tipoID;
	} 
	public void setTipoID (int tipoID) {
		this.tipoID = tipoID;
	}

	public java.util.Collection getCartoes() {
		return this.cartoes;
	}

	public void setCartoes(java.util.Collection cartoes) {
		this.cartoes = cartoes;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		
		TipoPassageiro tipo = (TipoPassageiro) obj;
		return (this.tipoID == tipo.tipoID);		
	}	

	public int hashCode() {
		return tipoID;
	}
	
	public void setTiposIncompativeis(java.util.Collection tiposIncompativeis) {
		this.tiposIncompativeis = tiposIncompativeis;
	}
	
	public java.util.Collection getTiposIncompativeis() {
		return tiposIncompativeis;
	}
	
}