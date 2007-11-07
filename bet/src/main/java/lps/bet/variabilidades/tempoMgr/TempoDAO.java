package lps.bet.variabilidades.tempoMgr;

import java.util.List;

import lps.bet.variabilidades.tiposDados.SistViarioUrbanoTempo;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TempoDAO extends HibernateDaoSupport implements ITempoMgt{

	public void salvarTempo(SistViarioUrbanoTempo tempo) {
		getHibernateTemplate().saveOrUpdate(tempo);
    }
	
	public void alterarTempo(int tempoMaxIntegracao) {
		SistViarioUrbanoTempo tempo = buscarSistViarioUrbanoTempo();
		tempo.setTempoMaxIntegracao(tempoMaxIntegracao);
		salvarTempo(tempo);		
	}

	public void criarTempo(int tempoMaxIntegracao) {
		if (buscarSistViarioUrbanoTempo() == null){
			SistViarioUrbanoTempo tempo = new SistViarioUrbanoTempo();
			tempo.setTempoMaxIntegracao(tempoMaxIntegracao);
			salvarTempo(tempo);
		}
	}
	
	public SistViarioUrbanoTempo buscarSistViarioUrbanoTempo(int tempoMaxIntegracao){
		DetachedCriteria criterios = DetachedCriteria.forClass(SistViarioUrbanoTempo.class);
		criterios.add(Restrictions.eq("tempoMaxIntegracao", tempoMaxIntegracao));
		List tempos = getHibernateTemplate().findByCriteria(criterios);
		SistViarioUrbanoTempo tempo = (SistViarioUrbanoTempo) tempos.get(0); 
		
		return tempo;
	}

	public SistViarioUrbanoTempo buscarSistViarioUrbanoTempo() {
		List tempos = getHibernateTemplate().loadAll(SistViarioUrbanoTempo.class);
		return (SistViarioUrbanoTempo) tempos.get(0);
	}
	
	public int buscarTempo() {
		List tempos = getHibernateTemplate().loadAll(SistViarioUrbanoTempo.class);
		SistViarioUrbanoTempo tempo = (SistViarioUrbanoTempo) tempos.get(0);
		return tempo.getTempoMaxIntegracao();
	}	
}
