package lps.bet.variabilidades.cartaoNumViagensMgr;

import lps.bet.basico.cartaoMgr.ICartaoMgt;
import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Viagem;
import lps.bet.interfaces.IRegistrarViagem;

public class CartaoNumViagensMgr implements IRegistrarViagem{

	IRegistrarViagem interfaceRegistrarViagem;
	ICartaoMgt interfaceCartaoMgt;
	
	public void debitarPassagem(int cartaoID, float valor) {
		interfaceRegistrarViagem.debitarPassagem(cartaoID, valor);		
	}

	public void registrarViagem(int cartaoID, Linha linha, int numViagem) {
		Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);
		viagem.setNumViagens(numViagem);
		System.out.println("Viagem de integração: NumViagem->" + numViagem + " ViagemID: "+ viagem.getViagemID());
		interfaceCartaoMgt.alterarViagem(viagem);
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

}
