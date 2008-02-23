package lps.bet.variabilidades.tiposDados;

import lps.bet.basico.tiposDados.TipoPassageiro;

public class TipoPassagLimPassagens {

	private int tipoID;
	private int limitePassagens;
	private TipoPassageiro tipoPassageiro;
		
	public int getTipoID() {
		return tipoID;
	}
	public void setTipoID(int tipoID) {
		this.tipoID = tipoID;
	}
	
	public int getLimitePassagens() {
		return limitePassagens;
	}
	public void setLimitePassagens(int limitePassagens) {
		this.limitePassagens = limitePassagens;
	}
	
	public TipoPassageiro getTipoPassageiro() {
		return tipoPassageiro;
	}
	public void setTipoPassageiro(TipoPassageiro tipoPassageiro) {
		this.tipoPassageiro = tipoPassageiro;
	}	
}
