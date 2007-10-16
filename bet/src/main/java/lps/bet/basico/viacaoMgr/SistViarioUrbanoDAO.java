package lps.bet.basico.viacaoMgr;

import java.util.List;

import lps.bet.basico.tiposDados.SistViarioUrbano;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class SistViarioUrbanoDAO extends HibernateDaoSupport{
	
	public void salvarSistViarioUrbano(SistViarioUrbano sistema) {
		getHibernateTemplate().saveOrUpdate(sistema);
    }

	public SistViarioUrbano buscarSistViarioUrbano(){
		try {
			List sistemas = getHibernateTemplate().loadAll(SistViarioUrbano.class);
			return (SistViarioUrbano) sistemas.get(0);			
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public SistViarioUrbano buscarSistViarioUrbano(int viacaoID){
		return (SistViarioUrbano) getHibernateTemplate().get(SistViarioUrbano.class, new Integer(viacaoID));
	}
	
	public void removerSistViarioUrbano(SistViarioUrbano sistema){
		getHibernateTemplate().delete(sistema);
	}

}
