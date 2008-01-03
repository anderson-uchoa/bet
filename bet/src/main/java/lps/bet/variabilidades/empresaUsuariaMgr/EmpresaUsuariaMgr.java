package lps.bet.variabilidades.empresaUsuariaMgr;

import java.util.List;
import lps.bet.variabilidades.tiposDados.EmpresaUsuaria;

public class EmpresaUsuariaMgr implements IEmpresaUsuariaMgr{
	
	EmpresaUsuariaDAO empresaUsuariaDAO;
	
	public List buscarEmpUsu(){
		
		return empresaUsuariaDAO.getEmpresa();
		
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

	public EmpresaUsuariaDAO getEmpresaUsuariaDAO() {
		return empresaUsuariaDAO;
	}

	public void setEmpresaUsuariaDAO(EmpresaUsuariaDAO empresaUsuariaDAO) {
		this.empresaUsuariaDAO = empresaUsuariaDAO;
	}
	
}
