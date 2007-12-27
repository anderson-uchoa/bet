package lps.bet.variabilidades;

import lps.bet.variabilidades.numViagensMgr.INumViagensMgt;
import lps.bet.basico.tiposDados.Viagem;

public aspect ViagemNumViagensCtrl /*extends ViagemIntegracaoCtrl*/{

	INumViagensMgt interfaceNumViagensMgt;
	
	public INumViagensMgt getInterfaceNumViagensMgt() {
		return interfaceNumViagensMgt;
	}

	public void setInterfaceNumViagensMgt(INumViagensMgt interfaceNumViagensMgt) {
		this.interfaceNumViagensMgt = interfaceNumViagensMgt;
	}

/*	public String verificarIntegracao(int cartaoID, int onibusID){

		System.out.println("ASPECTO Validar Num Viagens");
		
		String estado = "INT-NOK";
		//Verificar se está dentro do número permitido de viagens para realizar integração
		int numViagem = 0;
		int numMaxViagens = interfaceNumViagensMgt.buscarMaxNumViagens();
		System.out.println("Obter NumMAXViagens" + interfaceNumViagensMgt.buscarMaxNumViagens());

		//De início considera-se que não haverá integração:
		numViagem = numMaxViagens;

		Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);
		
		//Viagem dentro do tempo de integração
		if (viagem != null){
			numViagem = viagem.getNumViagens();

			//Integração
			if (numViagem < numMaxViagens){
				estado = processarIntegracao(onibusID, viagem);
			}
		}
		return estado;		
	}
	*/
}
