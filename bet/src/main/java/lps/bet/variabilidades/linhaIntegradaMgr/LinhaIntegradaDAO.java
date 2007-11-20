package lps.bet.variabilidades.linhaIntegradaMgr;

import java.util.List;

import lps.bet.basico.linhaMgr.ILinhaMgt;
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

	public LinhaIntegrada buscarLinhaIntegrada(int linhaIntegradaID) {
		return (LinhaIntegrada) getHibernateTemplate().get(LinhaIntegrada.class, new Integer(linhaIntegradaID));		
	}

	public boolean verificarLinhaIntegrada(int linhaViagemID, int linhaOriginalID){
		DetachedCriteria criteria = DetachedCriteria.forClass(LinhaIntegrada.class);
		criteria.add(Restrictions.eq("linhaOriginalID", linhaOriginalID));
		criteria.add(Restrictions.eq("linhaIntegradaID", linhaViagemID));
		List linhasIntegradas = getHibernateTemplate().findByCriteria(criteria);
		return !linhasIntegradas.isEmpty();			
	}
	
	public List buscarLinhasIntegradas(int linhaOriginalID) {
		DetachedCriteria criteria = DetachedCriteria.forClass(LinhaIntegrada.class);
		criteria.add(Restrictions.eq("linhaOriginalID", linhaOriginalID));
		List LinhasIntegradas = getHibernateTemplate().findByCriteria(criteria);
		return null;
	}

	public void criarLinhaIntegrada(LinhaIntegrada linhaIntegrada) {
		salvarLinhaIntegrada(linhaIntegrada);		
	}
	
	public void removerLinhaIntegrada(LinhaIntegrada linhaIntegrada){
		getHibernateTemplate().delete(linhaIntegrada);
	}
}
