package lps.bet.variabilidades.tiposDados;

import lps.bet.basico.tiposDados.TipoPassageiro;

public class TipoPassagPagtoCartao {
	
	private int tipoID;
	private boolean pagtoAquisicaoCartao;
	private float valorAquisicao;
	private TipoPassageiro tipoPassageiro;
	
	public int getTipoID() {
		return tipoID;
	}
	public void setTipoID(int tipoID) {
		this.tipoID = tipoID;
	}
	
	public boolean isPagtoAquisicaoCartao() {
		return pagtoAquisicaoCartao;
	}
	public void setPagtoAquisicaoCartao(boolean pagtoAquisicaoCartao) {
		this.pagtoAquisicaoCartao = pagtoAquisicaoCartao;
	}
	
	public float getValorAquisicao() {
		return valorAquisicao;
	}
	public void setValorAquisicao(float valorAquisicao) {
		this.valorAquisicao = valorAquisicao;
	}
	
	public TipoPassageiro getTipoPassageiro() {
		return tipoPassageiro;
	}
	public void setTipoPassageiro(TipoPassageiro tipoPassageiro) {
		this.tipoPassageiro = tipoPassageiro;
	}
}
