package lps.bet.variabilidades.viagemNumViagensCtrl;

import lps.bet.basico.cartaoMgr.ICartaoMgt;
import lps.bet.basico.linhaMgr.IRegistrarArrecadacao;
import lps.bet.basico.tiposDados.Viagem;
import lps.bet.interfaces.IProcessarViagem;
import lps.bet.variabilidades.numViagensMgr.INumViagensMgt;

public class ViagemNumViagensCtrl implements IProcessarViagem{

	INumViagensMgt interfaceNumViagensMgt;
	ICartaoMgt interfaceCartaoMgt;
	IRegistrarArrecadacao interfaceRegistrarArrecadacao;
	IProcessarViagem interfaceProcessarViagem;
	
	public INumViagensMgt getInterfaceNumViagensMgt() {
		return interfaceNumViagensMgt;
	}
	public void setInterfaceNumViagensMgt(INumViagensMgt interfaceNumViagensMgt) {
		this.interfaceNumViagensMgt = interfaceNumViagensMgt;
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
	public void setInterfaceRegistrarArrecadacao(
			IRegistrarArrecadacao interfaceRegistrarArrecadacao) {
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

		//Viagem fora do tempo de integra��o: nova viagem
		if (!estado.equals("INT-OK")){
			return interfaceProcessarViagem.processarViagem(cartaoID, onibusID);
		}
		else {
			return estado;
		}
	}
	
	public String verificarIntegracao(int cartaoID, int onibusID){

		String estado = "INT-NOK";
		//Verificar se est� dentro do n�mero permitido de viagens para realizar integra��o
		int numViagem = 0;
		int numMaxViagens = interfaceNumViagensMgt.buscarMaxNumViagens();
		System.out.println("Obter NumMAXViagens" + interfaceNumViagensMgt.buscarMaxNumViagens());

		//De in�cio considera-se que n�o haver� integra��o:
		numViagem = numMaxViagens;

		Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);
		
		//Viagem dentro do tempo de integra��o
		if (viagem != null){
			numViagem = viagem.getNumViagens();

			//Integra��o
			if (numViagem < numMaxViagens){
				estado = processarIntegracao(onibusID, viagem);
			}
		}
		return estado;		
	}
	
	public String processarIntegracao(int onibusID, Viagem viagem){
		//N�o recebe dinheiro, mas incrementa o numero de passageiros para a corrida
		interfaceRegistrarArrecadacao.registrarArrecadacao(onibusID, 0);

		//Uma integra��o � feita para a viagem
		viagem.setNumViagens(viagem.getNumViagens()+1);
		interfaceCartaoMgt.alterarViagem(viagem);
		String estado = "INT-OK";
		System.out.println("ESTADO: INT-OK");
		return estado;		
	}
}
