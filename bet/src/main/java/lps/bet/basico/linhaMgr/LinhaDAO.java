package lps.bet.basico.linhaMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Corrida;
import lps.bet.basico.tiposDados.Linha;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LinhaDAO extends HibernateDaoSupport{ 
    
	String hqlBuscarLinhaCorrida;
	String hqlBuscarLinhaPorNome;
	
	public String getHqlBuscarLinhaPorNome() {
		return hqlBuscarLinhaPorNome;
	}

	public void setHqlBuscarLinhaPorNome(String hqlBuscarLinhaPorNome) {
		this.hqlBuscarLinhaPorNome = hqlBuscarLinhaPorNome;
	}

	public String getHqlBuscarLinhaCorrida() {
		return hqlBuscarLinhaCorrida;
	}

	public void setHqlBuscarLinhaCorrida(String hqlBuscarLinhaCorrida) {
		this.hqlBuscarLinhaCorrida = hqlBuscarLinhaCorrida;
	}

	public void salvarLinha(Linha linha) {
    	getHibernateTemplate().saveOrUpdate(linha);
    }    
        
    public void removerLinha(Linha linha){
    	getHibernateTemplate().delete(linha);
    }
    
    public void removerLinha(int linhaID){
    	Linha linha = buscarLinha(linhaID);
    	getHibernateTemplate().delete(linha);
    }
    
    public List buscarLinhas(){
    	return getHibernateTemplate().loadAll(Linha.class);
    }
    
    public Linha buscarLinha(int linhaID){
    	return (Linha) getHibernateTemplate().get(Linha.class, new Integer(linhaID));
    }
    
    public Linha buscarLinha(String nomeLinha){
		List linhas = getHibernateTemplate().findByNamedParam(hqlBuscarLinhaPorNome, "nomeLinha", nomeLinha);
    	return (Linha) linhas.get(0);    	
    }
    
    public Linha buscarLinhaCorrida(Corrida corrida) {
    	List linhas = getHibernateTemplate().find(hqlBuscarLinhaCorrida, new Integer(corrida.getCorridaID()));
    	return (Linha) linhas.get(0);
    }

    public void criarLinha(Linha linha){
    	salvarLinha(linha);
    }
    
    public void alterarLinha(Linha linha){
    	salvarLinha(linha);
    }
            
}
