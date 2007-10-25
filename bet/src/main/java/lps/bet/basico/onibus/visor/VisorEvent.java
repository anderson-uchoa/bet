package lps.bet.basico.onibus.visor;

public class VisorEvent extends java.util.EventObject{

	String mensagem;
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public VisorEvent(Object source) {
		super(source);
	}
	
}
