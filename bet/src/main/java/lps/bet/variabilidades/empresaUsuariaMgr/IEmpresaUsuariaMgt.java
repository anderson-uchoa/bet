package lps.bet.variabilidades.empresaUsuariaMgr;

import java.util.Collection;
import java.util.List;

import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.variabilidades.tiposDados.EmpresaUsuaria;

public interface IEmpresaUsuariaMgt {

	public List buscarEmpresasUsuarias();
	public EmpresaUsuaria buscarEmpUsu(int empresaID);
	public void criarEmpresa(EmpresaUsuaria empresa);
	public void alterarEmpresa(EmpresaUsuaria empresa);
	public void removerEmpresa(EmpresaUsuaria empresa);
	
	public Collection<Passageiro> buscarPassageirosPorEmpresa(String nomeFantasia);
	
	public EmpresaUsuaria buscarEmpresaPorPassageiro(Passageiro passageiro);
	public EmpresaUsuaria buscarEmpresaUsuaria(String nomeFantasia);
	
	public void atribuirEmpresaAoPassageiro(EmpresaUsuaria empresaNova, Passageiro passageiro);
}

