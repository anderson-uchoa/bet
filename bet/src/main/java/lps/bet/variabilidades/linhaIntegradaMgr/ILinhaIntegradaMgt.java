package lps.bet.variabilidades.linhaIntegradaMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Linha;
import lps.bet.variabilidades.tiposDados.LinhaIntegrada;

public interface ILinhaIntegradaMgt {

	public List buscarLinhasIntegracao();
	public List buscarLinhasIntegradas(Linha linhaOriginal);
	public LinhaIntegrada buscarIntegracao(int integracaoID);
	public LinhaIntegrada buscarLinhaIntegrada(int linhaIntegradaID);
	public void criarLinhaIntegrada(LinhaIntegrada linhaIntegrada);
	public void alterarLinhaIntegrada(LinhaIntegrada linhaIntegrada);
	public void removerLinhaIntegrada(LinhaIntegrada linhaIntegrada);
	public void removerLinhaIntegrada(int integracaoID);
	
	public boolean verificarLinhaIntegrada(Linha linhaViagem, Linha linhaOriginal);
}
