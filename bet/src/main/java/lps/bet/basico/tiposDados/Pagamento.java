package lps.bet.basico.tiposDados;

import java.util.Calendar;



public class Pagamento {
 
	private Calendar dtPgto;
	private float valorPgto;
	private int pgtoID;
	private Cartao cartao;

	public Calendar getDtPgto () {
		return dtPgto;
	} 
	public void setDtPgto (Calendar dtPgto) {
		this.dtPgto = dtPgto;
	}

	public float getValorPgto () {
		return valorPgto;
	} 
	public void setValorPgto (float valorPgto) {
		this.valorPgto = valorPgto;
	}

	public int getPgtoID () {
		return pgtoID;
	} 
	public void setPgtoID (int pgtoID) {
		this.pgtoID = pgtoID;
	}

	public Cartao getCartao() {
		return this.cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

}