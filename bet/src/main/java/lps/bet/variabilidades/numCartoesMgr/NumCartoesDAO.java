package lps.bet.variabilidades.numCartoesMgr;

import java.util.List;

import lps.bet.variabilidades.tiposDados.SistViarioUrbanoNumCartoes;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class NumCartoesDAO extends HibernateDaoSupport implements INumCartoesMgt {

	public void salvarNumeroCartoes(SistViarioUrbanoNumCartoes numCartoes) {
		getHibernateTemplate().saveOrUpdate(numCartoes);
    }
	
	public void alterarLimiteCartoes(int limiteCartoes) {
		SistViarioUrbanoNumCartoes numCartoes = buscarSistViarioUrbanoNumCartoes();
		numCartoes.setLimiteCartoes(limiteCartoes);
		salvarNumeroCartoes(numCartoes);		
	}

	public int buscarLimiteCartoes() {
		List listaNumeroCartoes = getHibernateTemplate().loadAll(SistViarioUrbanoNumCartoes.class);
		SistViarioUrbanoNumCartoes numeroCartoes = (SistViarioUrbanoNumCartoes) listaNumeroCartoes.get(0);
		return numeroCartoes .getLimiteCartoes();
	}
	
	public SistViarioUrbanoNumCartoes buscarSistViarioUrbanoNumCartoes() {
		List listaNumCartoes = getHibernateTemplate().loadAll(SistViarioUrbanoNumCartoes.class);
		return (SistViarioUrbanoNumCartoes) listaNumCartoes.get(0);
	}

	public void criarLimiteCartoes(int limiteCartoes) {
		if (buscarSistViarioUrbanoNumCartoes() == null){
			SistViarioUrbanoNumCartoes numCartoes = new SistViarioUrbanoNumCartoes();
			numCartoes.setLimiteCartoes(limiteCartoes);
			salvarNumeroCartoes(numCartoes);
		}
	}	
	
}
