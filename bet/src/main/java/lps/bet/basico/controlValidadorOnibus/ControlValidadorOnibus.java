package lps.bet.basico.onibus.controlValidadorOnibus;

import lps.bet.basico.controlValidadorServidor.IProcessarTransacao;
import lps.bet.basico.onibus.catraca.Catraca;
import lps.bet.basico.onibus.catraca.ILiberarCatraca;
import lps.bet.basico.onibus.leitoraCartao.ILeitora;
import lps.bet.basico.onibus.validadorMgr.IValidadorMgt;
import lps.bet.basico.onibus.visor.IVisor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class ControlValidadorOnibus implements IPermitirLeitura, ITratarCartao{
	
	boolean leituraPermitida = true;
	IValidadorMgt interfaceValidadorMgt;
	IVisor interfaceVisor;
	ILiberarCatraca interfaceLiberarCatraca;
	ILeitora interfaceLeitora;
	IProcessarTransacao interfaceProcessarTransacao;
	
	public ControlValidadorOnibus(){
	}

/*	public static void main(String[] args) {
		Resource resource = new ClassPathResource("onibus-context.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		Catraca catraca = (Catraca) factory.getBean("Catraca");
		
		
		ControlValidadorOnibus controlValidadorOnibus = (ControlValidadorOnibus) factory.getBean("ControlValidadorOnibus");
		
		controlValidadorOnibus.tratarCartao("0100010001");
		catraca.girarCatraca();
		controlValidadorOnibus.tratarCartao("0200000123");
		catraca.girarCatraca();
		controlValidadorOnibus.tratarCartao("0300000345");
		catraca.girarCatraca();
		controlValidadorOnibus.tratarCartao("0200000001");
		catraca.girarCatraca();
		controlValidadorOnibus.tratarCartao("0300000001");
		catraca.girarCatraca();
		controlValidadorOnibus.tratarCartao("0300000002");
		catraca.girarCatraca();		
		controlValidadorOnibus.tratarCartao("0300000003");
		catraca.girarCatraca();
		controlValidadorOnibus.tratarCartao("0300000004");
		catraca.girarCatraca();
		controlValidadorOnibus.tratarCartao("0200000001");
		catraca.girarCatraca();
	}*/

	public void tratarCartao(String dados){
		if (!leituraPermitida){
			interfaceVisor.mostrar("Leitura negada.");
			return;
		}
				
		int cod = Integer.parseInt(dados.substring(0, 2));
		
		if (cod == 1){
			iniciarValidador(dados);
		}
		else{
			int cartaoID = Integer.parseInt(dados.substring(2, 10));
			System.out.println("ValidadorOnibus -> Cartao:" + cartaoID);
			leituraPermitida = false;
			int onibusID = interfaceValidadorMgt.getOnibusID();
			if (onibusID == 0){
				interfaceVisor.mostrar("Validador não iniciado");
				permitirLeitura();
			}
			else{
				String resposta = interfaceProcessarTransacao.processarTransacao(cod, cartaoID, interfaceValidadorMgt.getOnibusID());
				registrarResposta(resposta);				
			}
		}
	}

	private void iniciarValidador(String dados){
		int onibusID = Integer.parseInt(dados.substring(6, 10));
		int validadorID = Integer.parseInt(dados.substring(2, 6));
		interfaceValidadorMgt.setOnibusID(onibusID);
		interfaceValidadorMgt.setValidadorID(validadorID);
		interfaceVisor.mostrar("Validador iniciado.");
	}

	public void registrarResposta(String resposta){
		System.out.println(resposta);
		if (resposta.startsWith("V-NOK")){ //Viagem não pode ser feita, porque o ônibus não está em uma corrida ainda
			permitirLeitura();
			interfaceVisor.mostrar("Não há corrida em andamento");
		}
		if (resposta.startsWith("PM") || resposta.startsWith("IS-OK")){
			interfaceVisor.mostrar("Viagem permitida.");
			interfaceLiberarCatraca.liberarCatraca();
		}
		else if (resposta.startsWith("PD-OK")){
			String prefix = "PD-OK ";
			String saldo = resposta.substring(prefix.length());
			interfaceVisor.mostrar("Viagem debitada. Saldo: " + saldo);
			interfaceLiberarCatraca.liberarCatraca();
		}
		else{ // Nenhuma viagem foi feita. Portanto a catraca continua travada e uma nova leitura pode ser feita.
			permitirLeitura();
			if (resposta.startsWith("CO")){
				String msg = "Corrida registrada: ";
				if (resposta.startsWith("CO-I")){
					msg += "Saída.";
				}
				else{
					msg += "Chegada.";
				}
				interfaceVisor.mostrar(msg);
			}
			else if (resposta.startsWith("PD-NOK") || resposta.startsWith("IS-NOK")){
				interfaceVisor.mostrar("Viagem negada.");
			}
			else{
				interfaceVisor.mostrar("Erro.");
			}
		}
	}

	public void permitirLeitura() {
		leituraPermitida = true;
	}
	
	public boolean isLeituraPermitida() {
		return leituraPermitida;
	}

	public void setLeituraPermitida(boolean leituraPermitida) {
		this.leituraPermitida = leituraPermitida;
	}

	public IValidadorMgt getInterfaceValidadorMgt() {
		return interfaceValidadorMgt;
	}

	public void setInterfaceValidadorMgt(IValidadorMgt interfaceValidadorMgt) {
		this.interfaceValidadorMgt = interfaceValidadorMgt;
	}

	public IVisor getInterfaceVisor() {
		return interfaceVisor;
	}

	public void setInterfaceVisor(IVisor interfaceVisor) {
		this.interfaceVisor = interfaceVisor;
	}

	public ILiberarCatraca getInterfaceLiberarCatraca() {
		return interfaceLiberarCatraca;
	}

	public void setInterfaceLiberarCatraca(ILiberarCatraca interfaceLiberarCatraca) {
		this.interfaceLiberarCatraca = interfaceLiberarCatraca;
	}

	public ILeitora getInterfaceLeitora() {
		return interfaceLeitora;
	}

	public void setInterfaceLeitora(ILeitora interfaceLeitora) {
		this.interfaceLeitora = interfaceLeitora;
	}

	public IProcessarTransacao getInterfaceProcessarTransacao() {
		return interfaceProcessarTransacao;
	}

	public void setInterfaceProcessarTransacao(IProcessarTransacao interfaceProcessarTransacao) {
		this.interfaceProcessarTransacao = interfaceProcessarTransacao;
	}

}
