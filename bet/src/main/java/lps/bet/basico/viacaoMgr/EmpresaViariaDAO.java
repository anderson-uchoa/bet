package lps.bet.basico.viacaoMgr;

import java.util.List;

import lps.bet.basico.tiposDados.EmpresaViaria;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EmpresaViariaDAO extends HibernateDaoSupport{
	
	public void salvarEmpresaViaria(EmpresaViaria empresa) {
		getHibernateTemplate().saveOrUpdate(empresa);
    }

//	public EmpresaViaria buscarEmpresaViaria(){
//		List empresasViarias = getHibernateTemplate().loadAll(EmpresaViaria.class);
//		if (empresasViarias.isEmpty()){ 
//			return null;
//		}
//		else {
//			return (EmpresaViaria) empresasViarias.get(0);
//		}
//	}

	public EmpresaViaria buscarEmpresaViaria(){
		try{
			List empresasViarias = getHibernateTemplate().loadAll(EmpresaViaria.class);
			return (EmpresaViaria) empresasViarias.get(0);
		} catch (IndexOutOfBoundsException e){
			return null;
		}
	}
	
	public EmpresaViaria buscarEmpresaViaria(int empresaID){
		return (EmpresaViaria) getHibernateTemplate().get(EmpresaViaria.class, new Integer(empresaID));
	}
	
	public void removerEmpresaViaria(EmpresaViaria empresa){
		getHibernateTemplate().delete(empresa);
	}
	
}
