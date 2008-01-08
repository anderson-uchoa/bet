package lps.bet.basico.linhaMgr;

import java.util.List;

import lps.bet.basico.dadosRelatorios.DadosRelatorioCorrida;
import lps.bet.basico.tiposDados.Corrida;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Validador;

public class LinhaMgr implements IAtualizarCorrida, ILinhaMgt, IRegistrarArrecadacao{
    
    LinhaDAO linhaDAO;
    CorridaDAO corridaDAO;
    ValidadorDAO validadorDAO;
        
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
    public Linha buscarLinhaAtualValidador(int validadorID) {
    	Corrida corrida = corridaDAO.buscarCorridaAtualOnibus(validadorID);
    	System.out.println("Corrida ID: " + corrida.getCorridaID());
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
    
    public boolean verificarPermissaoViagem(int validadorID){
    	return corridaDAO.verificarPermissaoViagem(validadorID);
    }

    //Métodos delegados para o OnibusDAO:
	public Validador buscarValidador(int validadorID) {
		return validadorDAO.buscarValidador(validadorID);
	}
	public List buscarValidadores() {
		return validadorDAO.buscarValidadores();
	} 
    public void criarValidador(Validador validador) {
    	validadorDAO.criarValidador(validador);
    }
    public void alterarValidador(Validador validador) {
    	validadorDAO.alterarValidador(validador);
    }
    public void removerValidador(int validadorID) {
    	validadorDAO.removerValidador(validadorID);
    }

    //Método da interface IAtualizarCorrida
    public String atualizarCorrida(int validadorID){
        
        Validador onibus = validadorDAO.buscarValidador(validadorID);
        
        if (onibus.isEmCorrida()){
            Corrida corrida = corridaDAO.buscarCorridaAtualOnibus(validadorID);       
            validadorDAO.alterarEmCorrida(onibus);
            String resposta = corridaDAO.encerrarCorrida(corrida);
            
            return resposta;
        }
        else{
        	List corridas = corridaDAO.buscarCorridasPrevistas(validadorID);
            Corrida corrida = (Corrida) corridas.get(0);
            validadorDAO.alterarEmCorrida(onibus);
            String resposta = corridaDAO.iniciarCorrida(corrida);
            
            return resposta;
        }
    }
    
    //Método da interface IRegistrarArrencadacao
    public void registrarArrecadacao(int validadorID, float valor){
    	corridaDAO.registrarArrecadacao(validadorID, valor);
    }
           
    public void registrarCredito(int validadorID, float valor) {
		corridaDAO.registrarCredito(validadorID, valor);		
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
	public ValidadorDAO getValidadorDAO() {
		return validadorDAO;
	}
	public void setValidadorDAO(ValidadorDAO validadorDAO) {
		this.validadorDAO = validadorDAO;
	}
    
}
