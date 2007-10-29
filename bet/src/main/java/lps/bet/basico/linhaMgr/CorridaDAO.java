package lps.bet.basico.linhaMgr;

import java.util.Calendar;
import java.util.List;

import lps.bet.basico.tiposDados.Corrida;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CorridaDAO extends HibernateDaoSupport{
	
	String hqlBuscarCorridasPrevistas;
	String hqlBuscarCorridaAtualOnibus;
	
	public String getHqlBuscarCorridasPrevistas() {
		return hqlBuscarCorridasPrevistas;
	}

	public void setHqlBuscarCorridasPrevistas(String hqlBuscarProximaCorridaPrevista) {
		this.hqlBuscarCorridasPrevistas = hqlBuscarProximaCorridaPrevista;
	}

	public String getHqlBuscarCorridaAtualOnibus() {
		return hqlBuscarCorridaAtualOnibus;
	}

	public void setHqlBuscarCorridaAtualOnibus(String hqlBuscarCorridaAtualOnibus) {
		this.hqlBuscarCorridaAtualOnibus = hqlBuscarCorridaAtualOnibus;
	}	
	
	// -------------------------------------------------------------------------
	// Métodos de negócio
	public void salvarCorrida(Corrida corrida) {
		getHibernateTemplate().saveOrUpdate(corrida);
    }
	
	public void criarCorrida(Corrida corrida){
		salvarCorrida(corrida);
	}
	
	public void alterarCorrida(Corrida corrida){
		salvarCorrida(corrida);
	}
    
    public Corrida buscarCorrida(int corridaID){
    	return (Corrida)getHibernateTemplate().get(Corrida.class, new Integer(corridaID));
    }
    
    public List buscarCorridas(){
    	return getHibernateTemplate().loadAll(Corrida.class);
    }
    
    public List buscarCorridasPrevistas(int onibusID) {
    	return getHibernateTemplate().find(hqlBuscarCorridasPrevistas, new Integer(onibusID));     	
    }
    
    public Corrida buscarCorridaAtualOnibus(int onibusID){
     	List corridas = getHibernateTemplate().find(hqlBuscarCorridaAtualOnibus, new Integer(onibusID));
    	return (Corrida) corridas.get(0);
    }
    
    public boolean verificarPermissaoViagem(int onibusID){
    	List corridas = getHibernateTemplate().find(hqlBuscarCorridaAtualOnibus, new Integer(onibusID));
    	if (corridas.get(0) == null){
    		return false;
    	}
    	else return true;
    }
    
    public String encerrarCorrida(Corrida corrida){
        corrida.setHoraChegada(Calendar.getInstance());
        corrida.setSaida(false);
        corrida.setEncerrado(true);
        salvarCorrida(corrida);
        return "CO-E";
    }
    
    public String iniciarCorrida(Corrida corrida){
        //corrida.setHoraSaida(new Time(System.currentTimeMillis()));
    	corrida.setHoraSaida(Calendar.getInstance());
        corrida.setSaida(true);
        salvarCorrida(corrida);
        return "CO-I";
    }
    
    public void registrarArrecadacao(int onibusID, float valor){
    	Corrida corrida = buscarCorridaAtualOnibus(onibusID);
    	corrida.setArrecadacao(corrida.getArrecadacao()+ valor);
    	
    	//Incrementar em uma unidade a qtd de passageiros para a corrida:
    	corrida.setQtdPassageiros(corrida.getQtdPassageiros() + 1);
    	
    	salvarCorrida(corrida);
    }
    
    public void registrarCredito(int onibusID, float valor){
    	Corrida corrida = buscarCorridaAtualOnibus(onibusID);
    	corrida.setCredito(corrida.getCredito()+ valor);
    	
    	//Incrementar em uma unidade a qtd de passageiros para a corrida:
    	corrida.setQtdPassageiros(corrida.getQtdPassageiros() + 1);
    	
    	salvarCorrida(corrida);
    }
    
    public void removerCorrida(int corridaID){
    	Corrida corrida = buscarCorrida(corridaID);
    	getHibernateTemplate().delete(corrida);
    }
}
