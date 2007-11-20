package lps.bet.variabilidades.linhaIntegradaMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Linha;
import lps.bet.variabilidades.tiposDados.LinhaIntegrada;

public interface ILinhaIntegradaMgt {

	public List buscarLinhasIntegradas(int linhaOriginalID);
	public LinhaIntegrada buscarLinhaIntegrada(int linhaIntegradaID);
	public void criarLinhaIntegrada(LinhaIntegrada linhaIntegrada);
	public void alterarLinhaIntegrada(LinhaIntegrada linhaIntegrada);
	public void removerLinhaIntegrada(LinhaIntegrada linhaIntegrada);
	
	public boolean verificarLinhaIntegrada(int linhaViagemID, int linhaOriginalID);
}
