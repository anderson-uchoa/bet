package lps.bet.basico.tiposDados ;

import java.util.Collection;


public class Linha {

	private int linhaID;
	private String nomeLinha;
	private String pontoSaida;
	private String pontoChegada;
	private Collection corridas;
	private SistViarioUrbano sistViarioUrbano;
	private Collection viagems;
	
	public Linha(){
	}
	
	public Collection getCorridas() {
		return corridas;
	}
	public void setCorridas(Collection corridas) {
		this.corridas = corridas;
	}
	public int getLinhaID() {
		return linhaID;
	}
	public void setLinhaID(int linhaID) {
		this.linhaID = linhaID;
	}
	public String getNomeLinha() {
		return nomeLinha;
	}
	public void setNomeLinha(String nomeLinha) {
		this.nomeLinha = nomeLinha;
	}
	public String getPontoChegada() {
		return pontoChegada;
	}
	public void setPontoChegada(String pontoChegada) {
		this.pontoChegada = pontoChegada;
	}
	public String getPontoSaida() {
		return pontoSaida;
	}
	public void setPontoSaida(String pontoSaida) {
		this.pontoSaida = pontoSaida;
	}

	public Collection getViagems() {
		return this.viagems;
	}

	public void setViagems(Collection viagems) {
		this.viagems = viagems;
	}

	public SistViarioUrbano getSistViarioUrbano() {
		return this.sistViarioUrbano;
	}
	public void setSistViarioUrbano(SistViarioUrbano sistViarioUrbano) {
		this.sistViarioUrbano = sistViarioUrbano;
	}

}