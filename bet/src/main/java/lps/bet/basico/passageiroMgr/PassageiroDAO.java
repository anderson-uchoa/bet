package lps.bet.basico.passageiroMgr;

import lps.bet.basico.tiposDados.Passageiro;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Collection;
import java.util.List;

public class PassageiroDAO extends HibernateDaoSupport implements IPassageiroMgt{

	public void criarPassageiro(Passageiro passageiro){
		salvarPassageiro(passageiro);
	}
	
	public void alterarPassageiro(Passageiro passageiro){
		salvarPassageiro(passageiro);
	}

	public void salvarPassageiro(Passageiro passageiro) {
		getHibernateTemplate().saveOrUpdate(passageiro);
    }

	public Passageiro buscarPassageiroPorID(int passageiroID){
		return (Passageiro) getHibernateTemplate().get(Passageiro.class, passageiroID);
	}
	
    public Passageiro buscarPassageiro(String nomePassageiro){
        DetachedCriteria passageiroPorNome = DetachedCriteria.forClass(Passageiro.class);
        passageiroPorNome.add(Restrictions.eq("nomePassageiro", nomePassageiro));
        List passageiros = getHibernateTemplate().findByCriteria(passageiroPorNome);
    	return (Passageiro) passageiros.get(0);    	
    }
	
	public Passageiro buscarPassageiro(long cpf){
        DetachedCriteria passageiroPorCpf = DetachedCriteria.forClass(Passageiro.class);
        passageiroPorCpf.add(Restrictions.eq("cpf", cpf));
        List passageiros = getHibernateTemplate().findByCriteria(passageiroPorCpf);
        if (passageiros.isEmpty()) return null;
		return (Passageiro) passageiros.get(0);
	}
	
	public void removerPassageiro(Passageiro passageiro){
		getHibernateTemplate().delete(passageiro);
	}
	
	public boolean existePassageiro(long cpf){
        DetachedCriteria passageiroPorCpf = DetachedCriteria.forClass(Passageiro.class);
        passageiroPorCpf.add(Restrictions.eq("cpf", cpf));
		List passageiros = getHibernateTemplate().findByCriteria(passageiroPorCpf);
		return (!passageiros.isEmpty());
	}

	public void removerPassageiro(long cpf) {
		Passageiro passageiro = buscarPassageiro(cpf);
		removerPassageiro(passageiro);
	}
	
	public void removerPassageiroPorID(int passageiroID) {
		Passageiro passageiro = buscarPassageiroPorID(passageiroID);
		removerPassageiro(passageiro);
	}

	public Collection buscarCartoesPorPassageiro(long cpf) {
		Passageiro passageiro = buscarPassageiro(cpf);
		return passageiro.getCartoes();
	}

	public List buscarPassageiros() {
		return getHibernateTemplate().loadAll(Passageiro.class);
	}	
	
}
