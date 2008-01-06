package lps.bet.variabilidades.empresaUsuariaMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.variabilidades.tiposDados.EmpresaUsuaria;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EmpresaUsuariaDAO extends HibernateDaoSupport{
	
	String hqlBuscarEmpresaPorPassageiro;	

	public void setHqlBuscarEmpresaPorPassageiro(String hqlBuscarEmpresaPorPassageiro) {
		this.hqlBuscarEmpresaPorPassageiro = hqlBuscarEmpresaPorPassageiro;
	}

	public void salvarEmpresaUsuaria(EmpresaUsuaria empresa) {		
		getHibernateTemplate().saveOrUpdate(empresa);
		
    }
	
	public List buscarEmpresas(){		
		return getHibernateTemplate().loadAll(EmpresaUsuaria.class);
		
	}
	
	public EmpresaUsuaria getEmpresa(int empresaID){		
		return (EmpresaUsuaria) getHibernateTemplate().get(EmpresaUsuaria.class, new Integer(empresaID));
	}
	
	public EmpresaUsuaria buscarEmpresa(String nomeFantasia){
		DetachedCriteria empresaUsuariaPorNome = DetachedCriteria.forClass(EmpresaUsuaria.class);
        empresaUsuariaPorNome.add(Restrictions.eq("nomeFantasia", nomeFantasia));
        List empresas = getHibernateTemplate().findByCriteria(empresaUsuariaPorNome);
    	return (EmpresaUsuaria) empresas.get(0);  
	}
	
	public EmpresaUsuaria buscarEmpresaUsuariaPorCNPJ(String cnpj){
		DetachedCriteria empresaUsuariaPorCNPJ = DetachedCriteria.forClass(EmpresaUsuaria.class);
        empresaUsuariaPorCNPJ.add(Restrictions.eq("cnpj", cnpj));
        List empresas = getHibernateTemplate().findByCriteria(empresaUsuariaPorCNPJ);
    	return (EmpresaUsuaria) empresas.get(0);
	}
	
	public EmpresaUsuaria buscarEmpresaPorPassageiro(Passageiro passageiro){
		List empresas = getHibernateTemplate().find(hqlBuscarEmpresaPorPassageiro, passageiro);
		if (empresas.isEmpty()) 
			return null;
		else 
			return (EmpresaUsuaria) empresas.get(0);	
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
