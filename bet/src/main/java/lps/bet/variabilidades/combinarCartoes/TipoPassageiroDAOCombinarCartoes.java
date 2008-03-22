package lps.bet.variabilidades.combinarCartoes;

import java.util.Iterator;
import java.util.List;

import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.variabilidades.tiposDados.TipoPassageiroCombinacaoRestrita;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TipoPassageiroDAOCombinarCartoes extends lps.bet.basico.cartaoMgr.TipoPassageiroDAO {


	public List buscarTiposPermitidos(List tiposJaAdquiridos) {
		List tiposPermitidos = buscarTodosTipos();
		tiposPermitidos.removeAll(tiposJaAdquiridos);

		for (Iterator it = tiposJaAdquiridos.iterator(); it.hasNext();) {
			TipoPassageiroCombinacaoRestrita tipo = (TipoPassageiroCombinacaoRestrita)downCast((TipoPassageiro) it.next());
			tiposPermitidos.removeAll(tipo.getTiposIncompativeis());
		}

		return tiposPermitidos;
	}
	

	
	public TipoPassageiroCombinacaoRestrita downCast(TipoPassageiro tipo){
		return (TipoPassageiroCombinacaoRestrita)buscarTipoPassageiro(tipo.getTipoID());
	}
	
	public List buscarTodosTipos() {
		return getHibernateTemplate().loadAll(TipoPassageiroCombinacaoRestrita.class);
	}
	
	
	public TipoPassageiro buscarTipoPassageiro(int tipoID) {
		TipoPassageiroCombinacaoRestrita tipo = (TipoPassageiroCombinacaoRestrita)getHibernateTemplate().get(
				TipoPassageiroCombinacaoRestrita.class, new Integer(tipoID));
		System.out.println( "<--" + 
		Integer.toString(((TipoPassageiroCombinacaoRestrita)tipo).getTiposIncompativeis().size()) + "-->");		
		return (TipoPassageiro) tipo; 
	}

	public List buscarTiposPassageiros() {
		return getHibernateTemplate().loadAll(TipoPassageiroCombinacaoRestrita.class);
	}	
	
	public void criarTipoPassageiro(TipoPassageiro tipo) {
		salvarTipoPassageiro((TipoPassageiroCombinacaoRestrita)tipo);
	}

	public void alterarTipoPassageiro(TipoPassageiro tipo) {
		salvarTipoPassageiro((TipoPassageiroCombinacaoRestrita)tipo);
	}
	
	public void salvarTipoPassageiro(TipoPassageiroCombinacaoRestrita tipo) {
		getHibernateTemplate().saveOrUpdate(tipo);
	}	
	

}
