package lps.bet.variabilidades.limitePassagensMgr;

import java.util.Collection;
import java.util.List;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.variabilidades.tiposDados.CartaoLimPassagens;
import lps.bet.variabilidades.tiposDados.TipoPassagLimPassagens;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CartaoLimPassagensDAO extends HibernateDaoSupport{

	   public void salvarCartaoLimPassagens(CartaoLimPassagens cartaoPassagens) {
	        getHibernateTemplate().saveOrUpdate(cartaoPassagens);
	    }

	    public void registrarCartaoLimPassagens(CartaoLimPassagens cartaoPassagens) {
	        salvarCartaoLimPassagens(cartaoPassagens);
	    }

	    public CartaoLimPassagens buscarCartaoLimPassagens(int cartaoID) {
	        return (CartaoLimPassagens) getHibernateTemplate().get(CartaoLimPassagens.class, new Integer(cartaoID));
	    }
	    
	    public CartaoLimPassagens buscarQtdPassagensPorCartao(Cartao cartao){
	    	DetachedCriteria criteria = DetachedCriteria.forClass(CartaoLimPassagens.class);
			criteria.add(Restrictions.eq("cartao", cartao));
			List qtdPassagens = getHibernateTemplate().findByCriteria(criteria);
	    	return (CartaoLimPassagens) qtdPassagens.get(0);
	    }

	    public Collection<CartaoLimPassagens> buscarTodosCartoes(){
	    	return getHibernateTemplate().loadAll(CartaoLimPassagens.class);
	    }	
}
