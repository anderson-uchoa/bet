package lps.bet.variabilidades.terminalMgr;

import java.util.List;
import lps.bet.variabilidades.tiposDados.Terminal;

public interface ITerminalMgr {

	public List buscarTerminal();
	public Terminal buscarTerminal(String nomeTerminal);
	public Terminal buscarTerminal(int terminalID);
	public void criarTerminal(Terminal terminal);
	public void alterarTerminal(Terminal terminal);
	public void removerTerminal(Terminal terminal);
	
}
