
package lps.bet.variabilidades.tiposDados ;

import lps.bet.basico.tiposDados.TipoPassageiro;

public class TipoPassageiroCombinacaoRestrita extends lps.bet.basico.tiposDados.TipoPassageiro {
 
	private java.util.Collection tiposIncompativeis;

	public void setTiposIncompativeis(java.util.Collection tiposIncompativeis) {
		this.tiposIncompativeis = tiposIncompativeis;
	}
	
	public java.util.Collection getTiposIncompativeis() {
		return tiposIncompativeis;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if(obj == null)
			return false;
		
		TipoPassageiro tipo = (TipoPassageiro) obj;
		return (this.getTipoID() == tipo.getTipoID());
		
	}
		
	
}