package lps.bet.variabilidades.empresaUsuariaMgr;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import lps.bet.variabilidades.tiposDados.EmpresaUsuaria;

public class EmpresaUsuariaDAO extends HibernateDaoSupport{

	public void salvarEmpresaUsuaria(EmpresaUsuaria empresa) {		
		getHibernateTemplate().saveOrUpdate(empresa);
		
    }
	
	public List getEmpresa(){		
		List empresasUsuarias = getHibernateTemplate().loadAll(EmpresaUsuaria.class);
		return empresasUsuarias;
		
	}
	
	public EmpresaUsuaria getEmpresa(int empresaID){		
		return (EmpresaUsuaria) getHibernateTemplate().get(EmpresaUsuaria.class, new Integer(empresaID));
	}
	
	public void inserirEmpresa(EmpresaUsuaria empresa){
		salvarEmpresaUsuaria(empresa);		
	}
	
	public void atualizarEmpresa(EmpresaUsuaria empresa){
		salvarEmpresaUsuaria(empresa);	
		
	}
	
	public void apagarEmpresa(EmpresaUsuaria empresa){		
		getHibernateTemplate().delete(empresa);		
	}
	
}
