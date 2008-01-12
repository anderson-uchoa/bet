package lps.bet.basico.linhaMgr;

import java.util.Collection;
import java.util.List;

import lps.bet.basico.dadosRelatorios.DadosRelatorioCorrida;
import lps.bet.basico.tiposDados.Corrida;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Onibus;
import lps.bet.basico.tiposDados.Validador;

public interface ILinhaMgt {
    
    public Linha buscarLinhaAtualValidador(int validadorID); 
    public List buscarLinhas();
    public Linha buscarLinha(int linhaID);
    public Linha buscarLinha(String nomeLinha);
	public void criarLinha(Linha linha);	 
	public void alterarLinha(Linha linha);	 
	public void removerLinha(int linhaID);	 

	public List buscarCorridas();
	public List buscarCorridas(DadosRelatorioCorrida dadosRelatorioCorrida);
//	public Collection<Corrida> buscarCorridasDoDia();
	public Corrida buscarCorrida(int corridaID);
	public void criarCorrida(Corrida corrida);
	public void alterarCorrida(Corrida corrida);
	public void removerCorrida(int corridaID);
	public boolean verificarPermissaoViagem(int validadorID);

	public List buscarValidadores();
	public Collection<Validador> buscarValidadoresNaoEmUso();
	public Validador buscarValidador(int validadorID);	
	public void criarValidador(Validador validador);
	public void alterarValidador(Validador validador);	 
	public void removerValidador(int validadorID);
	
	public List buscarTodosOnibus();
	public Onibus buscarOnibus(int busID);
	public void criarOnibus(Onibus onibus);
	public void alterarOnibus(Onibus onibus);	 
	public void removerOnibus(int busID);
}
