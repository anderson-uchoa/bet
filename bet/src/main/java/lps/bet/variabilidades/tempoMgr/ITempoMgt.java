package lps.bet.variabilidades.tempoMgr;


public interface ITempoMgt {

	public int buscarTempo();
	public void criarTempo(int tempoMaxIntegracao);
	public void alterarTempo(int tempoMaxIntegracao);	
}
