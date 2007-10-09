package lps.bet.basico.tiposDados ;

public class Onibus {

	private int onibusID;
	private boolean emCorrida = false;
	private java.util.Collection corridas;

	public java.util.Collection getCorridas() {
		return corridas;
	}
	public void setCorridas(java.util.Collection corridas) {
		this.corridas = corridas;
	}
	public boolean isEmCorrida() {
		return emCorrida;
	}
	public void setEmCorrida(boolean emCorrida) {
		this.emCorrida = emCorrida;
	}
	public int getOnibusID() {
		return onibusID;
	}
	public void setOnibusID(int onibusID) {
		this.onibusID = onibusID;
	}

}