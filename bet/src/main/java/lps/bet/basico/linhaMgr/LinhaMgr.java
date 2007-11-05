package lps.bet.basico.linhaMgr;

import java.util.List;

import lps.bet.basico.dadosRelatorios.DadosRelatorioCorrida;
import lps.bet.basico.tiposDados.Corrida;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Onibus;

public class LinhaMgr implements IAtualizarCorrida, ILinhaMgt, IRegistrarArrecadacao{
    
    LinhaDAO linhaDAO;
    CorridaDAO corridaDAO;
    OnibusDAO onibusDAO;
        
    public LinhaMgr() {         
    }

    //Métodos da interface ILinhaMgt delegados para o LinhaDAO
    public List buscarLinhas(){
    	return linhaDAO.buscarLinhas();
    }
    public Linha buscarLinha(int linhaID){
    	return linhaDAO.buscarLinha(linhaID);
    }
    public Linha buscarLinha(String nomeLinha){
    	return linhaDAO.buscarLinha(nomeLinha);
    }
    public Linha buscarLinhaAtualOnibus(int onibusID) {
    	Corrida corrida = corridaDAO.buscarCorridaAtualOnibus(onibusID);
    	return linhaDAO.buscarLinhaCorrida(corrida);
    }
    public void criarLinha(Linha linha) {
        linhaDAO.criarLinha(linha);   
    }
    public void alterarLinha(Linha linha) {
    	linhaDAO.alterarLinha(linha);
    }
    public void removerLinha(int linhaID) {
    	linhaDAO.removerLinha(linhaID);
    }

    //Métodos da interface ILinhaMgt delegados para o CorridaDAO
    public List buscarCorridas(){
    	return corridaDAO.buscarCorridas();
    }
    
    public List buscarCorridas(DadosRelatorioCorrida dadosRelatorioCorrida) {
		return corridaDAO.buscarCorridas(dadosRelatorioCorrida);
	}

	public Corrida buscarCorrida(int corridaID) {
    	return corridaDAO.buscarCorrida(corridaID);
    }
    public void criarCorrida(Corrida corrida) {
    	corridaDAO.criarCorrida(corrida);
    }
    public void alterarCorrida(Corrida corrida) {
    	corridaDAO.alterarCorrida(corrida);
    }    
    public void removerCorrida(int corridaID) {
        corridaDAO.removerCorrida(corridaID);    
    }
    
    public boolean verificarPermissaoViagem(int onibusID){
    	return corridaDAO.verificarPermissaoViagem(onibusID);
    }

    //Métodos delegados para o OnibusDAO:
	public Onibus buscarOnibus(int onibusID) {
		return onibusDAO.buscarOnibus(onibusID);
	}
	public List buscarTodosOnibus() {
		return onibusDAO.buscarTodosOnibus();
	} 
    public void criarOnibus(Onibus onibus) {
    	onibusDAO.criarOnibus(onibus);
    }
    public void alterarOnibus(Onibus onibus) {
    	onibusDAO.alterarOnibus(onibus);
    }
    public void removerOnibus(int onibusID) {
    	onibusDAO.removerOnibus(onibusID);
    }

    //Método da interface IAtualizarCorrida
    public String atualizarCorrida(int onibusID){
        
        Onibus onibus = onibusDAO.buscarOnibus(onibusID);
        
        if (onibus.isEmCorrida()){
            Corrida corrida = corridaDAO.buscarCorridaAtualOnibus(onibusID);       
            onibusDAO.alterarEmCorrida(onibus);
            String resposta = corridaDAO.encerrarCorrida(corrida);
            
            return resposta;
        }
        else{
        	List corridas = corridaDAO.buscarCorridasPrevistas(onibusID);
            Corrida corrida = (Corrida) corridas.get(0);
            onibusDAO.alterarEmCorrida(onibus);
            String resposta = corridaDAO.iniciarCorrida(corrida);
            
            return resposta;
        }
    }
    
    //Método da interface IRegistrarArrencadacao
    public void registrarArrecadacao(int onibusID, float valor){
    	corridaDAO.registrarArrecadacao(onibusID, valor);
    }
           
    public void registrarCredito(int onibusID, float valor) {
		corridaDAO.registrarCredito(onibusID, valor);		
	}

	//Getters e Setters    
	public CorridaDAO getCorridaDAO() {
		return corridaDAO;
	}
	public void setCorridaDAO(CorridaDAO corridaDAO) {
		this.corridaDAO = corridaDAO;
	}
	public LinhaDAO getLinhaDAO() {
		return linhaDAO;
	}
	public void setLinhaDAO(LinhaDAO linhaDAO) {
		this.linhaDAO = linhaDAO;
	}
	public OnibusDAO getOnibusDAO() {
		return onibusDAO;
	}
	public void setOnibusDAO(OnibusDAO onibusDAO) {
		this.onibusDAO = onibusDAO;
	}
    
}
