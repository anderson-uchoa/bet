package lps.bet.basico.linhaMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Validador;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ValidadorDAO extends HibernateDaoSupport{ 
    
    public void salvarValidador(Validador validador) {
    	getHibernateTemplate().saveOrUpdate(validador);
    }
    
    public void removerValidador(int validadorID){
    	Validador validador = buscarValidador(validadorID);
    	removerValidador(validador);
    }
    
    public void removerValidador(Validador validador){
    	getHibernateTemplate().delete(validador);
    }
    
    public List buscarValidadores(){
    	return getHibernateTemplate().loadAll(Validador.class);
    }
    
    public Validador buscarValidador(int validadorID) {    	
    	return (Validador) getHibernateTemplate().get(Validador.class, new Integer(validadorID));
    }
    
    public void alterarEmCorrida(Validador validador){
    	if (validador.isEmCorrida()) 
    		validador.setEmCorrida(false);
    	else 
    		validador.setEmCorrida(true);
    	salvarValidador(validador);
    }
  
    public void criarValidador(Validador validador) {
    	salvarValidador(validador);
    }
    
    public void alterarValidador(Validador validador){
    	salvarValidador(validador);
    }
    
}
