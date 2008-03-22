package lps.bet.variabilidades.tiposDados;

import java.util.Collection;

import lps.bet.basico.tiposDados.Validador;

public class Terminal {

	private String nomeTerminal;
	private int terminalID;
	private Collection<Validador> validadores;
	
	public String getNomeTerminal() {
		return nomeTerminal;
	}
	public void setNomeTerminal(String nomeTerminal) {
		this.nomeTerminal = nomeTerminal;
	}
	
	public int getTerminalID() {
		return terminalID;
	}
	public void setTerminalID(int terminalID) {
		this.terminalID = terminalID;
	}
	
	public Collection<Validador> getValidadores() {
		return validadores;
	}
	public void setValidadores(Collection<Validador> validadores) {
		this.validadores = validadores;
	}	
	
	public void adicionarValidador(Validador validador){
		validadores.add(validador);
	}
	
	public void removerValidador(Validador validador){
		validadores.remove(validador);
	}
	
}
