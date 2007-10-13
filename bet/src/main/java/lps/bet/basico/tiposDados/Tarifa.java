package lps.bet.basico.tiposDados;

import java.util.Calendar;

public class Tarifa {
 
	private String nomeTarifa;
	private float valorTarifa;
	private Calendar dtAtualizacao;
	private int tarifaID;
	private SistViarioUrbano sistViarioUrbano;

	public String getNomeTarifa () {
		return nomeTarifa;
	} 
	public void setNomeTarifa (String nomeTarifa) {
		this.nomeTarifa = nomeTarifa;
	}

	public float getValorTarifa () {
		return valorTarifa;
	} 
	public void setValorTarifa (float valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

	public Calendar getDtAtualizacao () {
		return dtAtualizacao;
	} 
	public void setDtAtualizacao (Calendar dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public int getTarifaID () {
		return tarifaID;
	} 
	public void setTarifaID (int tarifaID) {
		this.tarifaID = tarifaID;
	}

	public SistViarioUrbano getSistViarioUrbano() {
		return this.sistViarioUrbano;
	}
	public void setSistViarioUrbano(SistViarioUrbano sistViarioUrbano) {
		this.sistViarioUrbano = sistViarioUrbano;
	}
}


