package lps.bet.basico.tiposDados;

import java.util.Calendar;

public class ItemRelatorioTrafego {

	public Calendar dataRelatorio;
	public float arrecadacao;
	public float credito;
	public int qtdPassageiros;
	public UsoValidador usoValidador;
	
	public static enum UsoValidador {TERMINAL, ONIBUS}

	public Calendar getDataRelatorio() {
		return dataRelatorio;
	}

	public void setDataRelatorio(Calendar dataRelatorio) {
		this.dataRelatorio = dataRelatorio;
	}

	public float getArrecadacao() {
		return arrecadacao;
	}

	public void setArrecadacao(float arrecadacao) {
		this.arrecadacao = arrecadacao;
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

	public UsoValidador getUsoValidador() {
		return usoValidador;
	}

	public void setUsoValidador(UsoValidador usoValidador) {
		this.usoValidador = usoValidador;
	}	
}
