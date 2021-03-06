
package lps.bet.basico.tiposDados ;

import java.util.Calendar;

public class Corrida {

	private int corridaID;
	private int numCorridaDia;
	private Calendar horaSaidaPrevista;
	private Calendar horaChegadaPrevista;
	private Calendar horaSaida;
	private Calendar horaChegada;
	private boolean saida;
	private float arrecadacao;
	private boolean encerrado;
	private float credito;
	private int qtdPassageiros;
	private Linha linha;
	private Validador validador;
	
	public float getArrecadacao() {
		return arrecadacao;
	}
	public void setArrecadacao(float arrecadacao) {
		this.arrecadacao = arrecadacao;
	}
	
	public int getCorridaID() {
		return corridaID;
	}
	public void setCorridaID(int corridaID) {
		this.corridaID = corridaID;
	}
	
	public boolean isEncerrado() {
		return encerrado;
	}
	public void setEncerrado(boolean encerrado) {
		this.encerrado = encerrado;
	}
	
	public Calendar getHoraChegada() {
		return horaChegada;
	}
	public void setHoraChegada(Calendar horaChegada) {
		this.horaChegada = horaChegada;
	}
	
	public Calendar getHoraChegadaPrevista() {
		return horaChegadaPrevista;
	}
	public void setHoraChegadaPrevista(Calendar horaChegadaPrevista) {
		this.horaChegadaPrevista = horaChegadaPrevista;
	}
	
	public Calendar getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(Calendar horaSaida) {
		this.horaSaida = horaSaida;
	}
	
	public Calendar getHoraSaidaPrevista() {
		return horaSaidaPrevista;
	}
	public void setHoraSaidaPrevista(Calendar horaSaidaPrevista) {
		this.horaSaidaPrevista = horaSaidaPrevista;
	}
	
	public Linha getLinha() {
		return linha;
	}
	public void setLinha(Linha linha) {
		this.linha = linha;
	}
	
	public int getNumCorridaDia() {
		return numCorridaDia;
	}
	public void setNumCorridaDia(int numCorridaDia) {
		this.numCorridaDia = numCorridaDia;
	}
	
	public Validador getValidador() {
		return validador;
	}
	public void setValidador(Validador validador) {
		this.validador = validador;
	}
	
	public boolean isSaida() {
		return saida;
	}
	public void setSaida(boolean saida) {
		this.saida = saida;
	}

	public float getCredito() {
		return credito;
	}
	public void setCredito(float credito) {
		this.credito = credito;
	}

	public int getQtdPassageiros() {
		return qtdPassageiros;
	}
	public void setQtdPassageiros(int qtdPassageiros) {
		this.qtdPassageiros = qtdPassageiros;
	}	
} 