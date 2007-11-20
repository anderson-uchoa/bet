package lps.bet.variabilidades;

import lps.bet.basico.cartaoMgr.ICartaoMgt;
import lps.bet.basico.linhaMgr.IRegistrarArrecadacao;
import lps.bet.basico.tiposDados.Viagem;


public abstract aspect ViagemIntegracaoCtrl {
	
	ICartaoMgt interfaceCartaoMgt;
	IRegistrarArrecadacao interfaceRegistrarArrecadacao;
	
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

	pointcut validarIntegracao(): call(String lps.bet.interfaces.IProcessarViagem.*(..));
	
	String around(): validarIntegracao(){
		
		String estado="INT-NOK";
		Object[] args = thisJoinPoint.getArgs();

		System.out.println("Aspecto entrecortou!");
		
		if (args.length > 0){
			int cartaoID = (Integer) args[0];
			int onibusID = (Integer) args[1];

			estado = verificarIntegracao(cartaoID, onibusID);						
		}
		
		//Viagem fora do tempo de integração: nova viagem
		if (!estado.equals("INT-OK")){
			return proceed();
		}
		else {
			return estado;
		}
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
	
	public abstract String verificarIntegracao(int cartaoID, int onibusID);
}
