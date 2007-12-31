package lps.bet.variabilidades.pagamentoCartaoMgr;

import java.util.Collection;
import java.util.List;

import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.tiposDados.Usuario;
import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TipoPassagPagtoCartaoDAO extends HibernateDaoSupport {
    String hqlBuscarUltimoTipoPassageiro;

    public void salvarTipoPassagPagtoCartao(TipoPassagPagtoCartao tipoPassageiro) {
        getHibernateTemplate().saveOrUpdate(tipoPassageiro);
    }

    public void registrarTipoPassagPagtoCartao(TipoPassagPagtoCartao tipoPagamento) {
        salvarTipoPassagPagtoCartao(tipoPagamento);
    }

    public TipoPassagPagtoCartao buscarTipoPassagPagtoCartao(int tipoID) {
        return (TipoPassagPagtoCartao) getHibernateTemplate().get(TipoPassagPagtoCartao.class, new Integer(tipoID));
    }
    
    public TipoPassagPagtoCartao buscarTipoPagtoPorTipoPassageiro(TipoPassageiro tipoPassageiro){
    	DetachedCriteria criteria = DetachedCriteria.forClass(TipoPassagPagtoCartao.class);
		criteria.add(Restrictions.eq("tipoPassageiro", tipoPassageiro));
		List tiposPagamentos = getHibernateTemplate().findByCriteria(criteria);
    	return (TipoPassagPagtoCartao) tiposPagamentos.get(0);
    }

    public int buscarUltimoTipoPassageiro() {
        //List passageiros = getHibernateTemplate().find(hqlBuscarUltimoTipoPassageiro);
        //return ((TipoPassagPagtoCartao) passageiros.get(0)).getTipoID();
        int ultimo;
        List passageiros = getHibernateTemplate().loadAll(TipoPassagPagtoCartao.class);

        if (passageiros.isEmpty()) {
            return 1;
        } else {
            ultimo = passageiros.size();
            return ((TipoPassagPagtoCartao) passageiros.get(ultimo - 1)).getTipoID();
        }
    }

    public String getHqlBuscarUltimoTipoPassageiro() {
        return hqlBuscarUltimoTipoPassageiro;
    }

    public void setHqlBuscarUltimoTipoPassageiro(
            String hqlBuscarUltimoTipoPassageiro) {
        this.hqlBuscarUltimoTipoPassageiro = hqlBuscarUltimoTipoPassageiro;
    }
    
    public Collection<TipoPassagPagtoCartao> buscarTodosTipos(){
    	return getHibernateTemplate().loadAll(TipoPassagPagtoCartao.class);
    }
}
