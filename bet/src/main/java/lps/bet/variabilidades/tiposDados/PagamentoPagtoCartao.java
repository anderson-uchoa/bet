package lps.bet.variabilidades.tiposDados;

import lps.bet.basico.tiposDados.Pagamento;

public class PagamentoPagtoCartao {
	
	private int pagtoID;
	private String tipoPagto;
	private Pagamento pagamento;	
	
	public int getPagtoID() {
		return pagtoID;
	}
	public void setPagtoID(int pagtoID) {
		this.pagtoID = pagtoID;
	}
	
	public String getTipoPagto() {
		return tipoPagto;
	}
	public void setTipoPagto(String tipoPagto) {
		this.tipoPagto = tipoPagto;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}	
}
