package lps.bet.basico.cartaoMgr;

import java.util.Iterator;
import java.util.List;

import lps.bet.basico.tiposDados.TipoPassageiro;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TipoPassageiroDAO extends HibernateDaoSupport {

	String hqlBuscarTipoPassagPorCartao;
	String hqlBuscarTipoPorNome;
	private Boolean combinarCartoes = false;

	public String getHqlBuscarTipoPassagPorCartao() {
		return hqlBuscarTipoPassagPorCartao;
	}

	public void setHqlBuscarTipoPassagPorCartao(
			String hqlBuscarTipoPassagPorCartao) {
		this.hqlBuscarTipoPassagPorCartao = hqlBuscarTipoPassagPorCartao;
	}

	public String getHqlBuscarTipoPorNome() {
		return hqlBuscarTipoPorNome;
	}

	public void setHqlBuscarTipoPorNome(String hqlBuscarTipoPorNome) {
		this.hqlBuscarTipoPorNome = hqlBuscarTipoPorNome;
	}

	public void salvarTipoPassageiro(TipoPassageiro tipo) {
		getHibernateTemplate().saveOrUpdate(tipo);
	}

	public void criarTipoPassageiro(TipoPassageiro tipo) {
		salvarTipoPassageiro(tipo);
	}

	public void alterarTipoPassageiro(TipoPassageiro tipo) {
		salvarTipoPassageiro(tipo);
	}

	public TipoPassageiro buscarTipoPassageiro(int tipoID) {
		return (TipoPassageiro) getHibernateTemplate().get(
				TipoPassageiro.class, new Integer(tipoID));
	}

	public List buscarTiposPassageiros() {
		return getHibernateTemplate().loadAll(TipoPassageiro.class);
	}

	public TipoPassageiro buscarTipoPassageiro(String nomeTipo) {
		List tipos = getHibernateTemplate().findByNamedParam(
				hqlBuscarTipoPorNome, "nomeTipo", nomeTipo);
		return (TipoPassageiro) tipos.get(0);
	}

	public void removerTipoPassageiro(TipoPassageiro tipo) {
		getHibernateTemplate().delete(tipo);
	}

	public TipoPassageiro buscarTipoPassagPorCartao(int cartaoID) {
		List tipos = getHibernateTemplate().find(hqlBuscarTipoPassagPorCartao,
				new Integer(cartaoID));
		return (TipoPassageiro) tipos.get(0);
	}

	public List buscarTodosTipos() {
		return getHibernateTemplate().loadAll(TipoPassageiro.class);
	}

	public List buscarTiposPermitidos(List tiposJaAdquiridos) {
		// List todosTipos = buscarTodosTipos();
		// List tiposPermitidos = new ArrayList();

		List tiposPermitidos = buscarTodosTipos();
		tiposPermitidos.removeAll(tiposJaAdquiridos);

		if (getCombinarCartoes())
		for (Iterator it = tiposJaAdquiridos.iterator(); it.hasNext();) {
			TipoPassageiro tipo = (TipoPassageiro) it.next();
			tiposPermitidos.removeAll(tipo.getTiposIncompativeis());
		}

		// for (Iterator it = todosTipos.iterator(); it.hasNext();) {
		// TipoPassageiro tipo = (TipoPassageiro) it.next();
		// if (!tiposJaAdquiridos.contains(tipo)){
		// tiposPermitidos.add(tipo);
		// }
		// }

		return tiposPermitidos;
	}

	public void removerTipoPassageiro(int tipoID) {
		TipoPassageiro tipo = buscarTipoPassageiro(tipoID);
		removerTipoPassageiro(tipo);
	}

	public void setCombinarCartoes(Boolean combinarCartoes) {
		this.combinarCartoes = combinarCartoes;
	}

	public Boolean getCombinarCartoes() {
		return combinarCartoes;
	}

}
