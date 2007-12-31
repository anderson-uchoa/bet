package lps.bet.variabilidades.viagemTempoCtrl;

import java.util.Calendar;

import lps.bet.basico.linhaMgr.IRegistrarArrecadacao;
import lps.bet.basico.tiposDados.Viagem;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.interfaces.IProcessarViagem;
import lps.bet.variabilidades.tempoMgr.ITempoMgt;

public class ViagemTempoCtrl implements IProcessarViagem{

	ITempoMgt interfaceTempoMgt;
	ICartaoMgt interfaceCartaoMgt;
	IRegistrarArrecadacao interfaceRegistrarArrecadacao;
	IProcessarViagem interfaceProcessarViagem;

	public ITempoMgt getInterfaceTempoMgt() {
		return interfaceTempoMgt;
	}
	public void setInterfaceTempoMgt(ITempoMgt interfaceTempoMgt) {
		this.interfaceTempoMgt = interfaceTempoMgt;
	}

	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}
	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}

	public IRegistrarArrecadacao getInterfaceRegistrarArrecadacao() {
		return interfaceRegistrarArrecadacao;
	}
	public void setInterfaceRegistrarArrecadacao(IRegistrarArrecadacao interfaceRegistrarArrecadacao) {
		this.interfaceRegistrarArrecadacao = interfaceRegistrarArrecadacao;
	}
	
	public IProcessarViagem getInterfaceProcessarViagem() {
		return interfaceProcessarViagem;
	}
	public void setInterfaceProcessarViagem(
			IProcessarViagem interfaceProcessarViagem) {
		this.interfaceProcessarViagem = interfaceProcessarViagem;
	}
	
	
	public String processarViagem(int cartaoID, int onibusID) {
		String estado="INT-NOK";

		estado = verificarIntegracao(cartaoID, onibusID);					

		//Viagem fora do tempo de integração: nova viagem
		if (!estado.equals("INT-OK")){
			return interfaceProcessarViagem.processarViagem(cartaoID, onibusID);
		}
		else {
			return estado;
		}
	}	

	public String verificarIntegracao(int cartaoID, int onibusID){
		String estado = "INT-NOK";
		//Verificar se está dentro do tempo para realizar integração
		int tempoMaxIntegracao = interfaceTempoMgt.buscarTempo();
		System.out.println("TempoMax: " + tempoMaxIntegracao);
		
		//De início considera-se que não haverá integração, então o tempo passou do que poderia ser:
		long tempoDecorrido = tempoMaxIntegracao+1;

		Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);
		
		//Viagem dentro do tempo de integração
		if (viagem != null){
			Calendar horaUltimaViagem = viagem.getHora();
			tempoDecorrido = Calendar.getInstance().getTimeInMillis() - horaUltimaViagem.getTimeInMillis();
			System.out.println("Tempo Decorrido: " + tempoDecorrido);
			
			//Integração
			if (tempoDecorrido <= tempoMaxIntegracao*1000){
				estado = processarIntegracao(onibusID, viagem);					
			}
		}
		return estado;		
	}
	
	public String processarIntegracao(int onibusID, Viagem viagem){
		//Não recebe dinheiro, mas incrementa o numero de passageiros para a corrida
		interfaceRegistrarArrecadacao.registrarArrecadacao(onibusID, 0);

		//Uma integração é feita para a viagem
		viagem.setNumViagens(viagem.getNumViagens()+1);
		interfaceCartaoMgt.alterarViagem(viagem);
		String estado = "INT-OK";
		System.out.println("ESTADO: INT-OK");
		return estado;		
	}
}
