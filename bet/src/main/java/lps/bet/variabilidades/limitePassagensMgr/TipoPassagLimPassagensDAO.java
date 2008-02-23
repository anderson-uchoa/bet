package lps.bet.variabilidades.limitePassagensMgr;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.variabilidades.tiposDados.TipoPassagLimPassagens;
import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;


public class TipoPassagLimPassagensDAO extends HibernateDaoSupport implements ILimitePassagensMgt{
	
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
			criteria.add(Restrictions.eq("tipoPassageiro", tipoPassageiro));
			List limPassagens = getHibernateTemplate().findByCriteria(criteria);
	    	return (TipoPassagLimPassagens) limPassagens.get(0);
	    }

	    public Collection<TipoPassagLimPassagens> buscarTodosTipos(){
	    	return getHibernateTemplate().loadAll(TipoPassagLimPassagens.class);
	    }
	
}
