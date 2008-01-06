package lps.bet.variabilidades.empresaUsuariaMgr;

import java.util.Collection;
import java.util.List;

import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.variabilidades.tiposDados.EmpresaUsuaria;

public class EmpresaUsuariaMgr implements IEmpresaUsuariaMgt{
	
	EmpresaUsuariaDAO empresaUsuariaDAO;
	
	public List buscarEmpresasUsuarias(){		
		return empresaUsuariaDAO.buscarEmpresas();	
	}
	
	public EmpresaUsuaria buscarEmpUsu(int empresaID){		
		return empresaUsuariaDAO.getEmpresa(empresaID);		
	}
	
	public void criarEmpresa(EmpresaUsuaria empresa){		
		empresaUsuariaDAO.inserirEmpresa(empresa);		
	}
	
	public void alterarEmpresa(EmpresaUsuaria empresa){		
		empresaUsuariaDAO.atualizarEmpresa(empresa);		
	}
	
	public void removerEmpresa(EmpresaUsuaria empresa){		
		empresaUsuariaDAO.apagarEmpresa(empresa);		
	}	

	public Collection<Passageiro> buscarPassageirosPorEmpresa(String nomeFantasia) {
		EmpresaUsuaria empresaUsuaria = empresaUsuariaDAO.buscarEmpresa(nomeFantasia);		
		return empresaUsuaria.getPassageiros();		
	}
	
	public EmpresaUsuaria buscarEmpresaPorPassageiro(Passageiro passageiro){
		return empresaUsuariaDAO.buscarEmpresaPorPassageiro(passageiro);
	}
	
	public void atribuirEmpresaAoPassageiro(EmpresaUsuaria empresaNova, Passageiro passageiro){
		EmpresaUsuaria empresaAtual = buscarEmpresaPorPassageiro(passageiro);
				
		if ((empresaAtual != null) && (!empresaAtual.equals(empresaNova))){
			empresaAtual.removerPassageiro(passageiro);
			empresaUsuariaDAO.salvarEmpresaUsuaria(empresaAtual);
		}
		
		if ((empresaNova != null)&&(!empresaNova.equals(empresaAtual))){
				empresaNova.adicionarPassageiro(passageiro);
				empresaUsuariaDAO.salvarEmpresaUsuaria(empresaNova);
		}
	}
	
	public EmpresaUsuaria buscarEmpresaUsuaria(String nomeFantasia){
		return empresaUsuariaDAO.buscarEmpresa(nomeFantasia);
	}
	
	public EmpresaUsuaria buscarEmpresaUsuariaPorCNPJ(String cnpj) {
		return empresaUsuariaDAO.buscarEmpresaUsuariaPorCNPJ(cnpj);
	}

	public EmpresaUsuariaDAO getEmpresaUsuariaDAO() {
		return empresaUsuariaDAO;
	}

	public void setEmpresaUsuariaDAO(EmpresaUsuariaDAO empresaUsuariaDAO) {
		this.empresaUsuariaDAO = empresaUsuariaDAO;
	}
	
}
