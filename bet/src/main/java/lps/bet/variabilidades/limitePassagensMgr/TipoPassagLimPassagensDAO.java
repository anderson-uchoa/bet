package lps.bet.variabilidades.limitePassagensMgr;

import java.util.Collection;
import java.util.List;

import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.variabilidades.tiposDados.TipoPassagLimPassagens;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class TipoPassagLimPassagensDAO extends HibernateDaoSupport {
	
	   public void salvarTipoPassagLimPassagens(TipoPassagLimPassagens limPassagens) {
	        getHibernateTemplate().saveOrUpdate(limPassagens);
	    }

	    public void registrarTipoPassagLimPassagens(TipoPassagLimPassagens limPassagens) {
	        salvarTipoPassagLimPassagens(limPassagens);
	    }

	    public TipoPassagLimPassagens buscarTipoPassagLimPassagens(int tipoID) {
	        return (TipoPassagLimPassagens) getHibernateTemplate().get(TipoPassagLimPassagens.class, new Integer(tipoID));
	    }
	    
	    public TipoPassagLimPassagens buscarLimPassagensPorTipo(TipoPassageiro tipoPassageiro){
	    	DetachedCriteria criteria = DetachedCriteria.forClass(TipoPassagLimPassagens.class);
	    	Criterion passageiroIgual = Restrictions.eq("tipoPassageiro.tipoID", tipoPassageiro.getTipoID());
			criteria.add(passageiroIgual);
			List limPassagens = getHibernateTemplate().findByCriteria(criteria);
	    	return (TipoPassagLimPassagens) limPassagens.get(0);
	    }

	    public Collection<TipoPassagLimPassagens> buscarTodosTipos(){
	    	return getHibernateTemplate().loadAll(TipoPassagLimPassagens.class);
	    }
	
}
