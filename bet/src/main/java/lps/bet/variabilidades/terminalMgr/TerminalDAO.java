package lps.bet.variabilidades.terminalMgr;

import java.util.List;

import lps.bet.variabilidades.tiposDados.Terminal;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TerminalDAO extends HibernateDaoSupport{
	
	public List getTerminal(){		
		List terminais = getHibernateTemplate().loadAll(Terminal.class);
		return terminais;		
	}
	
	public Terminal getTerminal(String nomeTerminal){
		
		DetachedCriteria criterios = DetachedCriteria.forClass(Terminal.class);
		criterios.add(Restrictions.eq("nomeTerminal", nomeTerminal));
		List terminais = getHibernateTemplate().findByCriteria(criterios);
		if(terminais.size()==0){
			return null;
		}
		return (Terminal)terminais.get(0);						
	}
	
	public Terminal getTerminal(int terminalID){		
		return (Terminal) getHibernateTemplate().get(Terminal.class, terminalID);						
	}
	
	public void salvarTerminal(Terminal terminal){		
		getHibernateTemplate().saveOrUpdate(terminal);		
	}
	
	public void inserirTerminal(Terminal terminal){
		
		if (getTerminal(terminal.getNomeTerminal()) == null){
			Terminal terminalI = new Terminal();
			
			terminalI.setNomeTerminal(terminal.getNomeTerminal());
			salvarTerminal(terminalI);
		}
		
	}
	
	public void atualizarTerminal(Terminal terminal){		
		Terminal terminalA = getTerminal(terminal.getTerminalID());
		terminalA.setNomeTerminal(terminal.getNomeTerminal());
		salvarTerminal(terminalA);			
	}
	
	public void apagarTerminal(Terminal terminal){		
		getHibernateTemplate().delete(terminal);
	}

}
