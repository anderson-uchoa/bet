package lps.bet.basico.relatorioMgr;

import java.util.Calendar;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import lps.bet.basico.tiposDados.RelatorioTrafego;

public class RelatorioDAO extends HibernateDaoSupport implements IRelatorioMgt {
	
	String hqlRelatorioTrafegoData;
	
	public RelatorioTrafego construirRelatorioTrafego(Calendar dataRelatorio) {
		getHibernateTemplate().find(hqlRelatorioTrafegoData, new Integer(validadorID));
		return null;
	}

	
	
	
}
