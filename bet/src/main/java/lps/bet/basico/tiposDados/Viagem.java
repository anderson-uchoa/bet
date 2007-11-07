package lps.bet.basico.tiposDados;

import java.util.Calendar;


public class Viagem {

	private Calendar hora;
	private int viagemID;
	private int numViagens;
	private Cartao cartao;
	private Linha linha;
	   
	public Viagem(){
	}
	
	public Calendar getHora () {
		return hora;
	} 
	public void setHora (Calendar hora) {
		this.hora = hora;
	}

	public int getViagemID () {
		return viagemID;
	} 
	public void setViagemID (int viagemID) {
		this.viagemID = viagemID;
	}
	
	public int getNumViagens() {
		return numViagens;
	}

	public void setNumViagens(int numViagens) {
		this.numViagens = numViagens;
	}

	public Cartao getCartao() {
		return this.cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Linha getLinha() {
		return this.linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}
}