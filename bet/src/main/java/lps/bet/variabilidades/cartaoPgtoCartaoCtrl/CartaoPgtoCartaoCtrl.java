package lps.bet.variabilidades.cartaoPgtoCartaoCtrl;

import java.util.Collection;
import java.util.List;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Pagamento;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.tiposDados.Viagem;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.variabilidades.pagamentoCartaoMgr.IPagtoCartaoMgt;
import lps.bet.variabilidades.tiposDados.PagamentoPagtoCartao;
import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;

public class CartaoPgtoCartaoCtrl implements ICartaoMgt {

	ICartaoMgt interfaceCartaoMgt;
	IPagtoCartaoMgt interfacePagtoCartaoMgt;
	
	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}
	
	public IPagtoCartaoMgt getInterfacePagtoCartaoMgt() {
		return interfacePagtoCartaoMgt;
	}

	public void setInterfacePagtoCartaoMgt(IPagtoCartaoMgt interfacePagtoCartaoMgt) {
		this.interfacePagtoCartaoMgt = interfacePagtoCartaoMgt;
	}

	public void alterarCartao(Cartao cartao) {
		Cartao cartaoDesatualizado = interfaceCartaoMgt.buscarCartao(cartao.getCartaoID());
		if (cartaoDesatualizado.getTipoPassageiro() != cartao.getTipoPassageiro()){
			criarPagamentoPgtoCartao(cartao);
		}
		interfaceCartaoMgt.alterarCartao(cartao);
	}

	public void alterarPagamento(Pagamento pagamento) {
		interfaceCartaoMgt.alterarPagamento(pagamento);		
	}

	public void alterarTipoPassageiro(TipoPassageiro tipo) {
		interfaceCartaoMgt.alterarTipoPassageiro(tipo);		
	}

	public void alterarViagem(Viagem viagem) {
		interfaceCartaoMgt.alterarViagem(viagem);		
	}

	public Cartao buscarCartao(int cartaoID) {
		return interfaceCartaoMgt.buscarCartao(cartaoID);
	}

	public List buscarCartoes() {
		return interfaceCartaoMgt.buscarCartoes();
	}

	public Pagamento buscarPagamento(int pgtoID) {
		return interfaceCartaoMgt.buscarPagamento(pgtoID);
	}

	public List buscarPagamentos() {
		return buscarPagamentos();
	}

	public float buscarSaldo(int cartaoID) {
		return interfaceCartaoMgt.buscarSaldo(cartaoID);
	}

	public TipoPassageiro buscarTipoPassageiro(int tipoID) {
		return interfaceCartaoMgt.buscarTipoPassageiro(tipoID);
	}

	public TipoPassageiro buscarTipoPassageiro(String nomeTipo) {
		return interfaceCartaoMgt.buscarTipoPassageiro(nomeTipo);
	}

	public TipoPassageiro buscarTipoPassagPorCartao(int cartaoID) {
		return interfaceCartaoMgt.buscarTipoPassagPorCartao(cartaoID);
	}

	public List buscarTiposPassageiros() {
		return interfaceCartaoMgt.buscarTiposPassageiros();
	}

	public Collection buscarTiposPermitidos(Passageiro passageiro, TipoPassageiro tipoSelecionado) {
		return interfaceCartaoMgt.buscarTiposPermitidos(passageiro, tipoSelecionado);
	}

	public Collection buscarTiposPermitidos(Passageiro passageiro) {
		return interfaceCartaoMgt.buscarTiposPermitidos(passageiro);
	}

	public Viagem buscarUltimaViagem(int cartaoID) {
		return interfaceCartaoMgt.buscarUltimaViagem(cartaoID);
	}

	public Viagem buscarViagem(int viagemID) {
		return interfaceCartaoMgt.buscarViagem(viagemID);
	}

	public List buscarViagens() {
		return interfaceCartaoMgt.buscarViagens();
	}

	public List buscarViagensPorCartao(int cartaoID) {
		return interfaceCartaoMgt.buscarViagensPorCartao(cartaoID);
	}

	public void carregarCartao(int cartaoID, float valor) {
		interfaceCartaoMgt.carregarCartao(cartaoID, valor);		
	}

	public void criarPagamentoPgtoCartao(Cartao cartao){
		//Buscar Tipo de Passageiro do Cartão para verificar depois se tem q pagar
		TipoPassagPagtoCartao tipoPassagPagtoCartao = interfacePagtoCartaoMgt.buscarTipoPassagPagtoCartao(cartao.getTipoPassageiro().getTipoID());
		
		//Verificar Pagamento para Aquisicao de Cartao
		if (tipoPassagPagtoCartao.isPagtoAquisicaoCartao()){
			
			Pagamento pagamento = new Pagamento();
			pagamento.setCartao(cartao);
			pagamento.setDtPgto(cartao.getDtAquisicao());
			pagamento.setValorPgto(tipoPassagPagtoCartao.getValorAquisicao());
			
			//Criar pagamento normalmente (como já era com carga de cartão)
			int pagtoID = criarPagamento(pagamento);
			
			//Criar pagamento de aquisicao de cartao
			PagamentoPagtoCartao pgtoCartao = new PagamentoPagtoCartao();
			pgtoCartao.setPagtoID(pagtoID);
			pgtoCartao.setTipoPagto("Aquisição");
			pgtoCartao.setPagamento(pagamento);
			
			//Criar pagamento de aquisição de cartão com os dados adicionais
			interfacePagtoCartaoMgt.criarPagtoCartao(pgtoCartao);
		}
	}

	public void criarCartao(Cartao cartao) {
		interfaceCartaoMgt.criarCartao(cartao);
		criarPagamentoPgtoCartao(cartao);
	}

	public int criarPagamento(Pagamento pagamento) {
		return interfaceCartaoMgt.criarPagamento(pagamento);
		
	}

	public void criarTipoPassageiro(TipoPassageiro tipo) {
		interfaceCartaoMgt.criarTipoPassageiro(tipo);		
	}

	public void criarViagem(Viagem viagem) {
		interfaceCartaoMgt.criarViagem(viagem);		
	}

	public boolean podeDebitar(int cartaoID, float valor) {
		return interfaceCartaoMgt.podeDebitar(cartaoID, valor);
	}

	public void removerCartao(int cartaoID) {
		interfaceCartaoMgt.removerCartao(cartaoID);		
	}

	public void removerPagamento(int pgtoID) {
		interfaceCartaoMgt.removerPagamento(pgtoID);
	}

	public void removerTipoPassageiro(int tipoID) {
		interfaceCartaoMgt.removerTipoPassageiro(tipoID);		
	}

	public void removerViagem(int viagemID) {
		interfaceCartaoMgt.removerViagem(viagemID);		
	}

	public boolean validarCartao(int cartaoID) {
		return interfaceCartaoMgt.validarCartao(cartaoID);
	}

	public Collection buscarTiposPermitidos(Cartao cartao) {
		return interfaceCartaoMgt.buscarTiposPermitidos(cartao);
	}
	
}
