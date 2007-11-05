package lps.bet.basico.dadosRelatorios;

import java.util.Calendar;

import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Onibus;

public class DadosRelatorioCorrida {

	private int corridaID;	
	private Onibus onibus;	
	private Linha linha;
	private boolean saida;
	private boolean encerrado;
	private Calendar inicioDtCorrida;
	private Calendar fimDtCorrida;
	private int minPassageiros;
	private int maxPassageiros;	
	private float minArrecadacao;
	private float maxArrecadacao;	
	private float minCredito;
	private float maxCredito;
	
	public int getCorridaID() {
		return corridaID;
	}
	public void setCorridaID(int corridaID) {
		this.corridaID = corridaID;
	}
	

		
	public Onibus getOnibus() {
		return onibus;
	}
	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}
	public Linha getLinha() {
		return linha;
	}
	public void setLinha(Linha linha) {
		this.linha = linha;
	}
	public boolean isSaida() {
		return saida;
	}
	public void setSaida(boolean saida) {
		this.saida = saida;
	}
	public boolean isEncerrado() {
		return encerrado;
	}
	public void setEncerrado(boolean encerrado) {
		this.encerrado = encerrado;
	}
	
	public Calendar getInicioDtCorrida() {
		return inicioDtCorrida;
	}
	public void setInicioDtCorrida(Calendar inicioDtCorrida) {
		this.inicioDtCorrida = inicioDtCorrida;
	}
	
	public Calendar getFimDtCorrida() {
		return fimDtCorrida;
	}
	public void setFimDtCorrida(Calendar fimDtCorrida) {
		this.fimDtCorrida = fimDtCorrida;
	}
	
	public int getMinPassageiros() {
		return minPassageiros;
	}
	public void setMinPassageiros(int minPassageiros) {
		this.minPassageiros = minPassageiros;
	}
	
	public int getMaxPassageiros() {
		return maxPassageiros;
	}
	public void setMaxPassageiros(int maxPassageiros) {
		this.maxPassageiros = maxPassageiros;
	}
	
	public float getMinArrecadacao() {
		return minArrecadacao;
	}
	public void setMinArrecadacao(float minArrecadacao) {
		this.minArrecadacao = minArrecadacao;
	}
	
	public float getMaxArrecadacao() {
		return maxArrecadacao;
	}
	public void setMaxArrecadacao(float maxArrecadacao) {
		this.maxArrecadacao = maxArrecadacao;
	}
	
	public float getMinCredito() {
		return minCredito;
	}
	public void setMinCredito(float minCredito) {
		this.minCredito = minCredito;
	}
	
	public float getMaxCredito() {
		return maxCredito;
	}
	public void setMaxCredito(float maxCredito) {
		this.maxCredito = maxCredito;
	}		
}
