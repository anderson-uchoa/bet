package lps.bet.basico.linhaMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Onibus;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class OnibusDAO extends HibernateDaoSupport{ 
    
    public void salvarOnibus(Onibus onibus) {
    	getHibernateTemplate().saveOrUpdate(onibus);
    }
    
    public void removerOnibus(int onibusID){
    	Onibus onibus = buscarOnibus(onibusID);
    	removerOnibus(onibus);
    }
    
    public void removerOnibus(Onibus onibus){
    	getHibernateTemplate().delete(onibus);
    }
    
    public List buscarTodosOnibus(){
    	return getHibernateTemplate().loadAll(Onibus.class);
    }
    
    public Onibus buscarOnibus(int onibusID) {    	
    	return (Onibus) getHibernateTemplate().get(Onibus.class, new Integer(onibusID));
    }
    
    public void alterarEmCorrida(Onibus onibus){
    	if (onibus.isEmCorrida()) 
    		onibus.setEmCorrida(false);
    	else 
    		onibus.setEmCorrida(true);
    	salvarOnibus(onibus);
    }
  
    public void criarOnibus(Onibus onibus) {
    	salvarOnibus(onibus);
    }
    
    public void alterarOnibus(Onibus onibus){
    	salvarOnibus(onibus);
    }
    
}
