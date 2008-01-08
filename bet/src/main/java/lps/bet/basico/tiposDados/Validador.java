package lps.bet.basico.tiposDados;

public class Validador {

	private int validadorID;
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
	public int getValidadorID() {
		return validadorID;
	}
	public void setValidadorID(int validadorID) {
		this.validadorID = validadorID;
	}

}