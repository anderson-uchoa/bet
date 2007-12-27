package lps.bet.variabilidades;

import java.util.Calendar;

import lps.bet.basico.tiposDados.Viagem;
import lps.bet.variabilidades.tempoMgr.ITempoMgt;

public aspect ViagemTempoCtrl /*extends ViagemIntegracaoCtrl*/{

	ITempoMgt interfaceTempoMgt;
	
	public ITempoMgt getInterfaceTempoMgt() {
		return interfaceTempoMgt;
	}
	public void setInterfaceTempoMgt(ITempoMgt interfaceTempoMgt) {
		this.interfaceTempoMgt = interfaceTempoMgt;
	}
	
/*	public String verificarIntegracao(int cartaoID, int onibusID){
		
		System.out.println("ASPECTO Validar Tempo");
		String estado = "INT-NOK";
		
		//Verificar se está dentro do tempo para realizar integração
		int tempoMaxIntegracao = interfaceTempoMgt.buscarTempo();
		System.out.println("Aspecto: -> TempoMax: " + tempoMaxIntegracao);
		
		//De início considera-se que não haverá integração, então o tempo passou do que poderia ser:
		long tempoDecorrido = tempoMaxIntegracao+1;

		Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);
		
		//Viagem dentro do tempo de integração
		if (viagem != null){
			Calendar horaUltimaViagem = viagem.getHora();
			tempoDecorrido = Calendar.getInstance().getTimeInMillis() - horaUltimaViagem.getTimeInMillis();
			System.out.println("Aspecto: -> Tempo Decorrido: " + tempoDecorrido);
			
			//Integração
			if (tempoDecorrido <= tempoMaxIntegracao*1000){
				estado = processarIntegracao(onibusID, viagem);					
			}
		}
		return estado;		
	}
	*/	
}