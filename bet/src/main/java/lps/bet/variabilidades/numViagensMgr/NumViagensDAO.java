package lps.bet.variabilidades.numViagensMgr;

import java.util.List;

import lps.bet.variabilidades.tiposDados.SistViarioUrbanoNumViagens;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class NumViagensDAO  extends HibernateDaoSupport implements INumViagensMgt {

	public void salvarNumViagens(SistViarioUrbanoNumViagens numViagens) {
		getHibernateTemplate().saveOrUpdate(numViagens);
    }
	
	public void alterarMaxNumViagens(int numViagens) {
		SistViarioUrbanoNumViagens numeroViagens = buscarSistViarioUrbanoNumViagens();
		numeroViagens.setNumViagens(numViagens);
		salvarNumViagens(numeroViagens);		
	}

	public int buscarMaxNumViagens() {
		List numerosViagens = getHibernateTemplate().loadAll(SistViarioUrbanoNumViagens.class);
		SistViarioUrbanoNumViagens numeroViagens = (SistViarioUrbanoNumViagens) numerosViagens.get(0);
		return numeroViagens.getNumViagens();
	}
	
	public SistViarioUrbanoNumViagens buscarSistViarioUrbanoNumViagens() {
		List numerosViagens = getHibernateTemplate().loadAll(SistViarioUrbanoNumViagens.class);
		return (SistViarioUrbanoNumViagens) numerosViagens.get(0);
	}

	public void criarMaxNumViagens(int numViagens) {
		if (buscarSistViarioUrbanoNumViagens() == null){
			SistViarioUrbanoNumViagens numeroViagens = new SistViarioUrbanoNumViagens();
			numeroViagens.setNumViagens(numViagens);
			salvarNumViagens(numeroViagens);
		}
	}	
}
