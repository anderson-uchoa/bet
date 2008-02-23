package lps.bet.basico.passageiroMgr;

import java.util.Collection;
import java.util.List;

import lps.bet.basico.tiposDados.Passageiro;

public interface IPassageiroMgt {
	
	public List buscarPassageiros();
	
	public Passageiro buscarPassageiro(long cpf);
	public Passageiro buscarPassageiro(String nomePassageiro);	
	public Passageiro buscarPassageiroPorID(int passageiroID);
	
	public boolean existePassageiro(long cpf);
	
	public void removerPassageiro(long cpf);	
	public void removerPassageiroPorID(int passageiroID);
	
	public Collection buscarCartoesPorPassageiro(long cpf);
	public int buscarQtdCartoesPorPassageiro(int passageiroID);
	
	public void criarPassageiro(Passageiro passageiro);
	
	public void alterarPassageiro(Passageiro passageiro);	
	
}
