package lps.bet.variabilidades;

import java.util.Calendar;

import lps.bet.basico.tiposDados.Viagem;
import lps.bet.variabilidades.numViagensMgr.INumViagensMgt;
import lps.bet.variabilidades.tempoMgr.ITempoMgt;

public aspect ViagemTempoNumViagensCtrl extends ViagemIntegracaoCtrl{

	ITempoMgt interfaceTempoMgt;
	INumViagensMgt interfaceNumViagensMgt;

	public ITempoMgt getInterfaceTempoMgt() {
		return interfaceTempoMgt;
	}
	public void setInterfaceTempoMgt(ITempoMgt interfaceTempoMgt) {
		this.interfaceTempoMgt = interfaceTempoMgt;
	}
	
	public INumViagensMgt getInterfaceNumViagensMgt() {
		return interfaceNumViagensMgt;
	}

	public void setInterfaceNumViagensMgt(INumViagensMgt interfaceNumViagensMgt) {
		this.interfaceNumViagensMgt = interfaceNumViagensMgt;
	}

	public String verificarIntegracao(int cartaoID, int onibusID){
		String estado = "INT-NOK";

		
		System.out.println("ASPECTO Validar Tempo NumViagens");
		
		//Verificar se est� dentro do tempo para realizar integra��o
		int tempoMaxIntegracao = interfaceTempoMgt.buscarTempo();
		//De in�cio considera-se que n�o haver� integra��o, ent�o o tempo passou do que poderia ser:
		long tempoDecorrido = tempoMaxIntegracao+1;
		
		int numMaxViagens = interfaceNumViagensMgt.buscarMaxNumViagens();
		int numViagem = 0;
		
		Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);
		
		//Viagem dentro do tempo de integra��o
		if (viagem != null){
			Calendar horaUltimaViagem = viagem.getHora();
			tempoDecorrido = Calendar.getInstance().getTimeInMillis() - horaUltimaViagem.getTimeInMillis();
			numViagem = viagem.getNumViagens();
			
			//Integra��o
			if ((tempoDecorrido <= tempoMaxIntegracao*1000)&&(numViagem < numMaxViagens)){
				estado = processarIntegracao(onibusID, viagem);					
			}
		}
		return estado;		
	}
	
}
