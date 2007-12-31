package lps.bet.variabilidades;

import lps.bet.variabilidades.linhaIntegradaMgr.ILinhaIntegradaMgt;
import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.tiposDados.Viagem;

public aspect ViagemLinhaIntegradaCtrl extends ViagemIntegracaoCtrl{

	ILinhaIntegradaMgt interfaceLinhaIntegradaMgt;
	ILinhaMgt interfaceLinhaMgt;
	
	public ILinhaIntegradaMgt getInterfaceLinhaIntegradaMgt() {
		return interfaceLinhaIntegradaMgt;
	}
	public void setInterfaceLinhaIntegradaMgt(
			ILinhaIntegradaMgt interfaceLinhaIntegradaMgt) {
		this.interfaceLinhaIntegradaMgt = interfaceLinhaIntegradaMgt;
	}

	public ILinhaMgt getInterfaceLinhaMgt() {
		return interfaceLinhaMgt;
	}
	public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
		this.interfaceLinhaMgt = interfaceLinhaMgt;
	}

/*	public String verificarIntegracao(int cartaoID, int onibusID){

		String estado = "INT-NOK";

		System.out.println("ASPECTO Validar Linha Integrada");
		
		int linhaViagemID = interfaceLinhaMgt.buscarLinhaAtualOnibus(onibusID).getLinhaID();
		System.out.println("LinhaID: " + linhaViagemID );
		
		Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);

		//Viagem dentro do tempo de integração
		if (viagem != null){
		
			int linhaOriginalID = viagem.getLinha().getLinhaID();
			System.out.println("LinhaOriginalID: " + linhaOriginalID);
			//Integração
			if (interfaceLinhaIntegradaMgt.verificarLinhaIntegrada(linhaViagemID, linhaOriginalID)){
				estado = processarIntegracao(onibusID, viagem);
			}
		}
		return estado;		
	}
	*/	
}
