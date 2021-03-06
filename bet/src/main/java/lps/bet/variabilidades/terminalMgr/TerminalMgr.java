package lps.bet.variabilidades.terminalMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Validador;
import lps.bet.variabilidades.tiposDados.Terminal;

public class TerminalMgr implements ITerminalMgt{

	TerminalDAO terminalDAO;
	
	public List buscarTerminal(){
		return terminalDAO.getTerminal();		
	}
	
	public Terminal buscarTerminal(String nomeTerminal){		
		return terminalDAO.getTerminal(nomeTerminal);		
	}
	
	public Terminal buscarTerminal(int terminalID){		
		return terminalDAO.getTerminal(terminalID);		
	}
	
	public void criarTerminal(Terminal terminal){		
		terminalDAO.inserirTerminal(terminal);		
	}
	
	public void alterarTerminal(Terminal terminal){		
		terminalDAO.atualizarTerminal(terminal);		
	}
	
	public void removerTerminal(Terminal terminal){
		terminalDAO.apagarTerminal(terminal);
	}

	public void atribuirValidadorAoTerminal(Terminal terminal, Validador validador){
		terminal.adicionarValidador(validador);	
		terminalDAO.salvarTerminal(terminal);
	}
	
	public TerminalDAO getTerminalDAO() {
		return terminalDAO;
	}

	public void setTerminalDAO(TerminalDAO terminalDAO) {
		this.terminalDAO = terminalDAO;
	}
	
	
	
}
