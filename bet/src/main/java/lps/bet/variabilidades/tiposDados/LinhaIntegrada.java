package lps.bet.variabilidades.tiposDados;

import lps.bet.basico.tiposDados.Linha;

public class LinhaIntegrada {

	private int integracaoID;
	private Linha linhaIntegrada;
	private Linha linhaOriginal;
		
	public int getIntegracaoID() {
		return integracaoID;
	}

	public void setIntegracaoID(int integracaoID) {
		this.integracaoID = integracaoID;
	}

	public Linha getLinhaIntegrada() {
		return linhaIntegrada;
	}

	public void setLinhaIntegrada(Linha linhaIntegrada) {
		this.linhaIntegrada = linhaIntegrada;
	}

	public Linha getLinhaOriginal() {
		return linhaOriginal;
	}

	public void setLinhaOriginal(Linha linhaOriginal) {
		this.linhaOriginal = linhaOriginal;
	}		
}
