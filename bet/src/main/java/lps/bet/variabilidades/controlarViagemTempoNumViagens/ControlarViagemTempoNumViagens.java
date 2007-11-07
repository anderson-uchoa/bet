package lps.bet.variabilidades.controlarViagemTempoNumViagens;

import java.util.Calendar;

import lps.bet.basico.cartaoMgr.ICartaoMgt;
import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.linhaMgr.IRegistrarArrecadacao;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Tarifa;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.tiposDados.Viagem;
import lps.bet.basico.viacaoMgr.IViacaoMgt;
import lps.bet.interfaces.IProcessarViagem;
import lps.bet.interfaces.IRegistrarViagem;
import lps.bet.variabilidades.viacaoNumViagensMgr.IObterNumViagens;
import lps.bet.variabilidades.viacaoTempoMgr.IObterTempo;

public class ControlarViagemTempoNumViagens implements IProcessarViagem {
	
	IRegistrarViagem interfaceRegistrarViagem;
	IRegistrarArrecadacao interfaceRegistrarArrecadacao;
	ILinhaMgt interfaceLinhaMgt;
	IViacaoMgt interfaceViacaoMgt;
	ICartaoMgt interfaceCartaoMgt;
    
	//VARIABILIDADE de TEMPO de INTEGRAÇÃO:
	IObterTempo interfaceObterTempo;	
	long tempoDecorrido;
	
	//VARIABILIDADE de NUMERO DE VIAGENS máximas de INTEGRAÇÃO:
	IObterNumViagens interfaceObterNumViagens;	
	int numViagem;
	
    public String processarViagem(int cartaoID, int onibusID){
        String estado = "IS-OK";
        float valor=0;

        //1. Verificar se viagem pode ser feita:
        boolean viagemPermitida = interfaceLinhaMgt.verificarPermissaoViagem(onibusID);
        if (!viagemPermitida){
        	estado = "V-NOK";
        	return estado; //Viagem não permitida, pois não há corrida aberta
        }
        
        //2. Buscar a linha do ônibus naquele momento:
        Linha linha = interfaceLinhaMgt.buscarLinhaAtualOnibus(onibusID);
        
        //3. Buscar o tipo de passageiro:
        TipoPassageiro tipo = interfaceCartaoMgt.buscarTipoPassagPorCartao(cartaoID);

    	//*************** VARIABILIDADE - TEMPO*****************:
        
        Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);
        
        //Verificar se está dentro do tempo para realizar integração
        int tempoMaxIntegracao = interfaceObterTempo.obterTempo();
        //De início considera-se que não haverá integração, então o tempo passou do que poderia ser:
        tempoDecorrido = tempoMaxIntegracao+1;
        
        //*************** VARIABILIDADE - NUMERO DE VIAGENS*****************:
		//Verificar se está dentro do número permitido de viagens para realizar integração
		int numMaxViagens = interfaceObterNumViagens.obterNumViagens();
		//De início considera-se que não haverá integração:
		numViagem = numMaxViagens;
        
        //Viagem dentro do tempo de integração
        if(viagem != null){        	
        	numViagem = viagem.getNumViagens();
         	
        	Calendar horaUltimaViagem = viagem.getHora();
        	tempoDecorrido = Calendar.getInstance().getTimeInMillis() - horaUltimaViagem.getTimeInMillis();
        	
        	//Integração
        	if ((tempoDecorrido <= tempoMaxIntegracao*1000)&&(numViagem < numMaxViagens)){
        		numViagem++;
        		//Não recebe dinheiro, mas incrementa o numero de passageiros
        		interfaceRegistrarArrecadacao.registrarArrecadacao(onibusID, 0);
        		estado="INT-OK";
        	}
        }
        //Viagem fora do tempo de integração: nova viagem
        if (!estado.equals("INT-OK")){	        	        
        	
        	//Recomeçar a contagem, pois vai ser feita uma nova viagem
			numViagem = 0;

        	//4. Se o tipo de passageiro não for isento: 
        	if (!tipo.getFormaPgtoPassagem().equalsIgnoreCase("isento")){

        		//5. buscar a tarifa:
        		valor = calcularValorPassagem(tipo);

        		//6. Se a forma de pgto for debito, deve-se verificar o saldo do cartão:
        		if (tipo.getFormaPgtoPassagem().equalsIgnoreCase("debito")){

        			//7. Debitar a passagem no valor obtido
        			if (interfaceCartaoMgt.podeDebitar(cartaoID, valor)){
        				interfaceRegistrarViagem.debitarPassagem(cartaoID, valor);
        				estado = "PD-OK";

        				//Registrar o credito de arrecadacao para a corrida do onibus  
        				interfaceRegistrarArrecadacao.registrarCredito(onibusID, valor);
        			}
        			else {
        				estado = "PD-NOK";
        				return estado;
        			}
        		}

        		//8. Registrar Arrecadação se for pagamento na forma manual:
        		else if (tipo.getFormaPgtoPassagem().equalsIgnoreCase("manual")){
        			interfaceRegistrarArrecadacao.registrarArrecadacao(onibusID, valor);
        			estado = "PM-OK";
        		}            
        	}
        }
        if (estado.matches("\\w+-OK")){
        	//Registrar Viagem:
        	interfaceRegistrarViagem.registrarViagem(cartaoID, linha, numViagem);
        } 

        return estado;
    }

    private float calcularValorPassagem(TipoPassageiro tipo){
    	Tarifa tarifa = interfaceViacaoMgt.buscarTarifa();
    	float desconto = (float) tipo.getDesconto(); 
    	return ((100 - desconto)/100)*tarifa.getValorTarifa();
    }
    
	public ILinhaMgt getInterfaceLinhaMgt() {
		return interfaceLinhaMgt;
	}

	public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
		this.interfaceLinhaMgt = interfaceLinhaMgt;
	}

	public IViacaoMgt getInterfaceViacaoMgt() {
		return interfaceViacaoMgt;
	}

	public void setInterfaceViacaoMgt(IViacaoMgt interfaceViacaoMgt) {
		this.interfaceViacaoMgt = interfaceViacaoMgt;
	}

	public IRegistrarArrecadacao getInterfaceRegistrarArrecadacao() {
		return interfaceRegistrarArrecadacao;
	}

	public void setInterfaceRegistrarArrecadacao(
			IRegistrarArrecadacao interfaceRegistrarArrecadacao) {
		this.interfaceRegistrarArrecadacao = interfaceRegistrarArrecadacao;
	}

	public IRegistrarViagem getInterfaceRegistrarViagem() {
		return interfaceRegistrarViagem;
	}

	public void setInterfaceRegistrarViagem(
			IRegistrarViagem interfaceRegistrarViagem) {
		this.interfaceRegistrarViagem = interfaceRegistrarViagem;
	} 
    
	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}
	//***********VARIABILIDADE de TEMPO DE INTEGRAÇÃO:***********
	public IObterTempo getInterfaceObterTempo() {
		return interfaceObterTempo;
	}

	public void setInterfaceObterTempo(IObterTempo interfaceObterTempo) {
		this.interfaceObterTempo = interfaceObterTempo;
	}		
	//******************************************************************
	
	//****VARIABILIDADE de NUMERO DE VIAGENS máximas de INTEGRAÇÃO:*****
	public IObterNumViagens getInterfaceObterNumViagens() {
		return interfaceObterNumViagens;
	}

	public void setInterfaceObterNumViagens(IObterNumViagens interfaceObterNumViagens) {
		this.interfaceObterNumViagens = interfaceObterNumViagens;
	}
	//******************************************************************
}
