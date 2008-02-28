package lps.bet.variabilidades.tiposDados;

import java.util.Calendar;

import lps.bet.basico.tiposDados.Cartao;

public class CartaoLimPassagens {
	
	private int cartaoID;
	private int qtdPassagensMes;
	private Calendar dtInicioContagem; 
	private Cartao cartao;
	
	public int getCartaoID() {
		return cartaoID;
	}
	public void setCartaoID(int cartaoID) {
		this.cartaoID = cartaoID;
	}
	
	public int getQtdPassagensMes() {
		return qtdPassagensMes;
	}
	public void setQtdPassagensMes(int qtdPassagensMes) {
		this.qtdPassagensMes = qtdPassagensMes;
	}	
	
	public Calendar getDtInicioContagem() {
		return dtInicioContagem;
	}
	public void setDtInicioContagem(Calendar dtInicioContagem) {
		this.dtInicioContagem = dtInicioContagem;
	}
	
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
}
