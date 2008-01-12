package lps.bet.variabilidades.linhaIntegradaMgr;

import java.util.List;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.variabilidades.tiposDados.LinhaIntegrada;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LinhaIntegradaDAO extends HibernateDaoSupport implements ILinhaIntegradaMgt{
	
	ILinhaMgt interfaceLinhaMgt;

	public ILinhaMgt getInterfaceLinhaMgt() {
		return interfaceLinhaMgt;
	}

	public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
		this.interfaceLinhaMgt = interfaceLinhaMgt;
	}

	public void salvarLinhaIntegrada(LinhaIntegrada linhaIntegrada){
		getHibernateTemplate().saveOrUpdate(linhaIntegrada);
	}
	
	public void alterarLinhaIntegrada(LinhaIntegrada linhaIntegrada) {
		salvarLinhaIntegrada(linhaIntegrada);		
	}

	public List buscarLinhasIntegracao(){
		return getHibernateTemplate().loadAll(LinhaIntegrada.class);
	}
	
	public LinhaIntegrada buscarLinhaIntegrada(int linhaIntegradaID) {
		//Pode estar errado assim, ver se funciona!
		return (LinhaIntegrada) getHibernateTemplate().get(LinhaIntegrada.class, new Integer(linhaIntegradaID));		
	}
	
	public LinhaIntegrada buscarIntegracao(int integracaoID){
		return (LinhaIntegrada) getHibernateTemplate().get(LinhaIntegrada.class, new Integer(integracaoID));	
	}

	public boolean verificarLinhaIntegrada(Linha linhaViagem, Linha linhaOriginal){
		DetachedCriteria criteria = DetachedCriteria.forClass(LinhaIntegrada.class);
		criteria.add(Restrictions.eq("linhaOriginal", linhaOriginal));
		criteria.add(Restrictions.eq("linhaIntegrada", linhaViagem));
		List linhasIntegradas = getHibernateTemplate().findByCriteria(criteria);
		return !linhasIntegradas.isEmpty();			
	}
	
	public List buscarLinhasIntegradas(Linha linhaOriginal) {
		DetachedCriteria criteria = DetachedCriteria.forClass(LinhaIntegrada.class);
		criteria.add(Restrictions.eq("linhaOriginal", linhaOriginal));
		List LinhasIntegradas = getHibernateTemplate().findByCriteria(criteria);
		return null;
	}

	public void criarLinhaIntegrada(LinhaIntegrada linhaIntegrada) {
		salvarLinhaIntegrada(linhaIntegrada);		
	}
	
	public void removerLinhaIntegrada(LinhaIntegrada linhaIntegrada){
		getHibernateTemplate().delete(linhaIntegrada);
	}
	
	public void removerLinhaIntegrada(int integracaoID){
		LinhaIntegrada linhaIntegrada = (LinhaIntegrada) getHibernateTemplate().get(LinhaIntegrada.class, new Integer(integracaoID));
		removerLinhaIntegrada(linhaIntegrada);		
	}
}
