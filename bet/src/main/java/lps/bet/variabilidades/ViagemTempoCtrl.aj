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
		
		//Verificar se est� dentro do tempo para realizar integra��o
		int tempoMaxIntegracao = interfaceTempoMgt.buscarTempo();
		System.out.println("Aspecto: -> TempoMax: " + tempoMaxIntegracao);
		
		//De in�cio considera-se que n�o haver� integra��o, ent�o o tempo passou do que poderia ser:
		long tempoDecorrido = tempoMaxIntegracao+1;

		Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);
		
		//Viagem dentro do tempo de integra��o
		if (viagem != null){
			Calendar horaUltimaViagem = viagem.getHora();
			tempoDecorrido = Calendar.getInstance().getTimeInMillis() - horaUltimaViagem.getTimeInMillis();
			System.out.println("Aspecto: -> Tempo Decorrido: " + tempoDecorrido);
			
			//Integra��o
			if (tempoDecorrido <= tempoMaxIntegracao*1000){
				estado = processarIntegracao(onibusID, viagem);					
			}
		}
		return estado;		
	}
	*/	
}