package lps.bet.basico.passageiroMgr;

import java.util.Collection;
import java.util.List;

import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.tiposDados.Usuario;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PassageiroDAO extends HibernateDaoSupport implements IPassageiroMgt{

	String hqlBuscarPassageiroPorCPF;
	String hqlBuscarPassageiroPorNome;
	
	public String getHqlBuscarPassageiroPorCPF() {
		return hqlBuscarPassageiroPorCPF;
	}

	public void setHqlBuscarPassageiroPorCPF(String hqlBuscarPassageiroPorCPF) {
		this.hqlBuscarPassageiroPorCPF = hqlBuscarPassageiroPorCPF;
	}
			
	public String getHqlBuscarPassageiroPorNome() {
		return hqlBuscarPassageiroPorNome;
	}

	public void setHqlBuscarPassageiroPorNome(String hqlBuscarPassageiroPorNome) {
		this.hqlBuscarPassageiroPorNome = hqlBuscarPassageiroPorNome;
	}

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
		return (Passageiro) getHibernateTemplate().get(Passageiro.class, new Integer(passageiroID));
	}
	
    public Passageiro buscarPassageiro(String nomePassageiro){
		List passageiros = getHibernateTemplate().findByNamedParam(hqlBuscarPassageiroPorNome, "nomePassageiro", nomePassageiro);
    	return (Passageiro) passageiros.get(0);    	
    }
	
	public Passageiro buscarPassageiro(long cpf){
		List passageiros = getHibernateTemplate().find(hqlBuscarPassageiroPorCPF, new Long(cpf));
		if (passageiros.isEmpty()) return null;
		return (Passageiro) passageiros.get(0);
	}
	
	public void removerPassageiro(Passageiro passageiro){
		getHibernateTemplate().delete(passageiro);
	}
	
	public boolean existePassageiro(long cpf){
		List passageiros = getHibernateTemplate().find(hqlBuscarPassageiroPorCPF, new Long(cpf));
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
