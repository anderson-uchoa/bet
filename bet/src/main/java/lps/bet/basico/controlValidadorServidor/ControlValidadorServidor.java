package lps.bet.basico.controlValidadorServidor;

import lps.bet.basico.cartaoMgr.ICartaoMgt;
import lps.bet.basico.controlarCorrida.IRegistrarCorrida;
import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.interfaces.IProcessarViagem;

public class ControlValidadorServidor implements IProcessarTransacao{

	IRegistrarCorrida interfaceRegistrarCorrida;
	IProcessarViagem interfaceProcessarViagem;
	ICartaoMgt interfaceCartaoMgt;
	ILinhaMgt interfaceLinhaMgt;

	public ControlValidadorServidor() {		
	}

	public String processarTransacao(int cod, int cartaoID, int onibusID){		
		
		String resposta = new String();
		
		//Se cartão é válido, pode processar a transação:
		if (interfaceCartaoMgt.validarCartao(cartaoID)){
			
			//Codigo 2 corresponde a registro de corrida
			if (cod==2){
				System.out.println("Vai chamar registrar corrida");
				resposta = interfaceRegistrarCorrida.registrarCorrida(onibusID);
			}
			
			//Codigo 3 corresponde a uma viagem sendo realizada
			else if (cod==3){
				
				//Verificar se viagem pode ser feita
				boolean viagemPermitida = interfaceLinhaMgt.verificarPermissaoViagem(onibusID);
				if (!viagemPermitida){ //Viagem não permitida, pois não há corrida aberta
					resposta = "V-NOK";
				}
				else{//Validar e Processar viagem
					resposta = interfaceProcessarViagem.processarViagem(cartaoID, onibusID);
					float saldo = interfaceCartaoMgt.buscarCartao(cartaoID).getSaldo();
					String strSaldo = Float.toString(saldo);
					if (resposta.equals("PD-OK")){
						resposta = resposta + " " + strSaldo;
					}
				}
			}
			return resposta;
		}
		//Senão cartão é inválido e não deve ser processado
		else { 
			System.out.println("Cartão é inválido!");
			return resposta="NOK";
		}
		
	}

	public IProcessarViagem getInterfaceProcessarViagem() {
		return interfaceProcessarViagem;
	}

	public void setInterfaceProcessarViagem(IProcessarViagem processarViagem) {
		interfaceProcessarViagem = processarViagem;
	}

	public IRegistrarCorrida getInterfaceRegistrarCorrida() {
		return interfaceRegistrarCorrida;
	}

	public void setInterfaceRegistrarCorrida(IRegistrarCorrida registrarCorrida) {
		interfaceRegistrarCorrida = registrarCorrida;
	}
	
	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}

	public ILinhaMgt getInterfaceLinhaMgt() {
		return interfaceLinhaMgt;
	}

	public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
		this.interfaceLinhaMgt = interfaceLinhaMgt;
	}	
}
