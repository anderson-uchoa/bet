package lps.bet.basico.cartaoMgr;

import java.util.Collection;
import java.util.List;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Pagamento;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.tiposDados.Viagem;

public interface ICartaoMgt {
    
	public boolean validarCartao(int cartaoID);
	
	public TipoPassageiro buscarTipoPassagPorCartao(int cartaoID);
	
	public float buscarSaldo(int cartaoID);
	
	public boolean podeDebitar(int cartaoID, float valor);
	
	public Collection buscarTiposPermitidos(Passageiro passageiro);
	
	public void carregarCartao(int cartaoID, float valor);
	
	public List buscarViagens();
	public List buscarViagensPorCartao(int cartaoID);
	public Viagem buscarUltimaViagem(int cartaoID);
	public Viagem buscarViagem(int viagemID);
	public void criarViagem(Viagem viagem);	
	public void alterarViagem(Viagem viagem);
	public void removerViagem(int viagemID);
	
	public List buscarCartoes();
	public Cartao buscarCartao(int cartaoID);
	public void criarCartao(Cartao cartao);	
	public void alterarCartao(Cartao cartao);	
	public void removerCartao(int cartaoID);	
	
	public Pagamento buscarPagamento(int pgtoID);
	public void criarPagamento(Pagamento pagamento);	
	public void alterarPagamento(Pagamento pagamento);
	public List buscarPagamentos();
	public void removerPagamento(int pgtoID);
	
	public List buscarTiposPassageiros();
	public TipoPassageiro buscarTipoPassageiro(int tipoID);
	public TipoPassageiro buscarTipoPassageiro(String nomeTipo);
	public void criarTipoPassageiro(TipoPassageiro tipo);
	public void alterarTipoPassageiro(TipoPassageiro tipo);
	public void removerTipoPassageiro(int tipoID);
	

}
