package lps.bet.variabilidades.empresaUsuariaMgr;

import java.util.List;
import lps.bet.variabilidades.tiposDados.EmpresaUsuaria;

public interface IEmpresaUsuariaMgr {

	public List buscarEmpUsu();
	public EmpresaUsuaria buscarEmpUsu(int empresaID);
	public void criarEmpresa(EmpresaUsuaria empresa);
	public void alterarEmpresa(EmpresaUsuaria empresa);
	public void removerEmpresa(EmpresaUsuaria empresa);
		
}

