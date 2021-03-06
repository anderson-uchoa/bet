package lps.bet.basico.linhaMgr;

import java.util.Calendar;
import java.util.List;

import lps.bet.basico.dadosRelatorios.DadosRelatorioCorrida;
import lps.bet.basico.tiposDados.Corrida;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CorridaDAO extends HibernateDaoSupport{
	
	String hqlBuscarCorridasPrevistas;
	String hqlBuscarCorridaAtualValidador;
	
	public String getHqlBuscarCorridasPrevistas() {
		return hqlBuscarCorridasPrevistas;
	}

	public void setHqlBuscarCorridasPrevistas(String hqlBuscarProximaCorridaPrevista) {
		this.hqlBuscarCorridasPrevistas = hqlBuscarProximaCorridaPrevista;
	}

	public String getHqlBuscarCorridaAtualValidador() {
		return hqlBuscarCorridaAtualValidador;
	}

	public void setHqlBuscarCorridaAtualValidador(String hqlBuscarCorridaAtualValidador) {
		this.hqlBuscarCorridaAtualValidador = hqlBuscarCorridaAtualValidador;
	}	
	
	// -------------------------------------------------------------------------
	// M�todos de neg�cio
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
    
    public List buscarCorridas(DadosRelatorioCorrida dados){

    	DetachedCriteria criteriosCorridas = DetachedCriteria.forClass(Corrida.class);

    	if (dados.getCorridaID()!= 0){
    		criteriosCorridas.add(Restrictions.eq("corridaID", dados.getCorridaID()));	
    	}
    	if (dados.getValidador() != null){
    		criteriosCorridas.add(Restrictions.eq("validador", dados.getValidador()));	
    	}
    	if (dados.getLinha() != null){
    		criteriosCorridas.add(Restrictions.eq("linha", dados.getLinha()));	
    	}
    	
    	criteriosCorridas.add(Restrictions.eq("saida", dados.isSaida()));
		criteriosCorridas.add(Restrictions.eq("encerrado", dados.isEncerrado()));
    	
		if ((dados.getInicioDtCorrida() != null) || (dados.getFimDtCorrida() != null)) {
			criteriosCorridas.add(Restrictions.between("horaSaidaPrevista",
					dados.getInicioDtCorrida(), dados.getFimDtCorrida()));
		}
		if ((dados.getMinPassageiros() != 0) || (dados.getMaxPassageiros() != 0)) {
			criteriosCorridas.add(Restrictions.between("qtdPassageiros", dados
					.getMinPassageiros(), dados.getMaxPassageiros()));
		}
		if ((dados.getMinArrecadacao() != 0) || (dados.getMaxArrecadacao() != 0)) {
			criteriosCorridas.add(Restrictions.between("arrecadacao",
					new Float(dados.getMinArrecadacao()), new Float(dados
							.getMaxArrecadacao())));
		}
		if ((dados.getMinCredito() != 0) || (dados.getMaxCredito() != 0)) {
			criteriosCorridas.add(Restrictions.between("credito", new Float(
					dados.getMinCredito()), new Float(dados.getMaxCredito())));
		}
		return getHibernateTemplate().findByCriteria(criteriosCorridas);
    }
    
//    public Collection<Corrida> buscarCorridasDoDia(){
//    	DetachedCriteria criteriosCorridas = DetachedCriteria.forClass(Corrida.class);
//    	criteriosCorridas.add(Restrictions.eq("saida", dados.isSaida()));
//		criteriosCorridas.add(Restrictions.eq("encerrado", dados.isEncerrado()));
//		if ((dados.getMinCredito() != 0) || (dados.getMaxCredito() != 0)) {
//			criteriosCorridas.add(Restrictions.between("credito", new Float(
//					dados.getMinCredito()), new Float(dados.getMaxCredito())));
//		}
//		return getHibernateTemplate().findByCriteria(criteriosCorridas);
//    }
    
    public List buscarCorridasPrevistas(int validadorID) {
    	return getHibernateTemplate().find(hqlBuscarCorridasPrevistas, new Integer(validadorID));     	
    }
    
    public Corrida buscarCorridaAtualOnibus(int validadorID){
     	List corridas = getHibernateTemplate().find(hqlBuscarCorridaAtualValidador, new Integer(validadorID));
    	return (Corrida) corridas.get(0);
    }
    
    public boolean verificarPermissaoViagem(int validadorID){
    	List corridas = getHibernateTemplate().find(hqlBuscarCorridaAtualValidador, new Integer(validadorID));
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
    	corrida.setHoraSaida(Calendar.getInstance());
        corrida.setSaida(true);
        salvarCorrida(corrida);
        return "CO-I";
    }
    
    public void registrarArrecadacao(int validadorID, float valor){
    	Corrida corrida = buscarCorridaAtualOnibus(validadorID);
    	corrida.setArrecadacao(corrida.getArrecadacao()+ valor);
    	
    	//Incrementar em uma unidade a qtd de passageiros para a corrida:
    	corrida.setQtdPassageiros(corrida.getQtdPassageiros() + 1);
    	
    	salvarCorrida(corrida);
    }
    
    public void registrarCredito(int validadorID, float valor){
    	Corrida corrida = buscarCorridaAtualOnibus(validadorID);
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
