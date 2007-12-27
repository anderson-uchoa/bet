package lps.bet.basico.cartaoMgr;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Viagem;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Calendar;
import java.util.List;

public class ViagemDAO extends HibernateDaoSupport {

    public Viagem buscarViagem(int viagemID) {
        return (Viagem) getHibernateTemplate().get(Viagem.class, viagemID);
    }

    public void salvarViagem(Viagem viagem) {
        getHibernateTemplate().saveOrUpdate(viagem);
    }

    public void criarViagem(Viagem viagem) {
        salvarViagem(viagem);
    }

    public void alterarViagem(Viagem viagem) {
        salvarViagem(viagem);
    }

    public void removerViagem(Viagem viagem) {
        getHibernateTemplate().delete(viagem);
    }

    public void removerViagem(int viagemID) {
        Viagem viagem = buscarViagem(viagemID);
        removerViagem(viagem);
    }

    public void registrarViagem(Cartao cartao, Linha linha) {

        Viagem viagem = new Viagem();
        viagem.setHora(Calendar.getInstance());
        viagem.setLinha(linha);
        viagem.setCartao(cartao);
        viagem.setNumViagens(0);
        salvarViagem(viagem);
        System.out.println("Viagem registrada.");

    }

    public List buscarViagensPorCartao(int cartaoID) {
        DetachedCriteria viagensPorCartao = DetachedCriteria.forClass(Viagem.class);
        viagensPorCartao.add(Restrictions.eq("cartaoID", cartaoID));
        return getHibernateTemplate().findByCriteria(viagensPorCartao);
    }

    public Viagem buscarUltimaViagem(Cartao cartao) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Viagem.class);
        criteria.add(Restrictions.eq("cartao", cartao));
        criteria.addOrder(Order.desc("hora"));
        List viagens = getHibernateTemplate().findByCriteria(criteria);
        if (viagens.isEmpty()) {
            return null;
        } else {
            return (Viagem) viagens.get(0);

        }
    }

    public List buscarViagens() {
        return getHibernateTemplate().loadAll(Viagem.class);
    }
}
