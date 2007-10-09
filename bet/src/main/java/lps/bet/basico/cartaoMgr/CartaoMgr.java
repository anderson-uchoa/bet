/*
 * CartaoMgr.java
 *
 * Created on 26 de Agosto de 2007, 20:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lps.bet.basico.cartaoMgr;

import java.util.Collection;
import java.util.List;

import lps.bet.basico.passageiroMgr.IPassageiroMgt;
import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Pagamento;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.tiposDados.Viagem;

/**
 *
 * @author Paula
 */
public class CartaoMgr implements IRegistrarViagem, ICartaoMgt {

	CartaoDAO cartaoDAO;
	ViagemDAO viagemDAO;
	TipoPassageiroDAO tipoPassageiroDAO;
	PagamentoDAO pagamentoDAO;
	IPassageiroMgt interfacePassageiroMgt;

	public IPassageiroMgt getInterfacePassageiroMgt() {
		return interfacePassageiroMgt;
	}

	public void setInterfacePassageiroMgt(IPassageiroMgt interfacePassageiroMgt) {
		this.interfacePassageiroMgt = interfacePassageiroMgt;
	}

	/** Creates a new instance of CartaoMgr */
    public CartaoMgr() {
    }
           
    public CartaoDAO getCartaoDAO() {
		return cartaoDAO;
	}

	public void setCartaoDAO(CartaoDAO cartaoDAO) {
		this.cartaoDAO = cartaoDAO;
	}

	public PagamentoDAO getPagamentoDAO() {
		return pagamentoDAO;
	}

	public void setPagamentoDAO(PagamentoDAO pagamentoDAO) {
		this.pagamentoDAO = pagamentoDAO;
	}

	public TipoPassageiroDAO getTipoPassageiroDAO() {
		return tipoPassageiroDAO;
	}

	public void setTipoPassageiroDAO(TipoPassageiroDAO tipoPassageiroDAO) {
		this.tipoPassageiroDAO = tipoPassageiroDAO;
	}

	public ViagemDAO getViagemDAO() {
		return viagemDAO;
	}

	public void setViagemDAO(ViagemDAO viagemDAO) {
		this.viagemDAO = viagemDAO;
	}
	
	//---------------------------------------------------------
    //Métodos da lógica de negócio
	
	public boolean validarCartao(int cartaoID){
		//Mudar para o validar cartao so passar o id e nao o objeto, ai nao precisa o do buscar aqui
		Cartao cartao = cartaoDAO.buscarCartao(cartaoID);
		return cartaoDAO.validarCartao(cartao);
	}
	
	public TipoPassageiro buscarTipoPassagPorCartao(int cartaoID){
		return tipoPassageiroDAO.buscarTipoPassagPorCartao(cartaoID);
	}
	
	public float buscarSaldo(int cartaoID){
		return cartaoDAO.buscarSaldo(cartaoID);
	}
	
	public boolean podeDebitar(int cartaoID, float valor){
		return cartaoDAO.podeDebitar(cartaoID, valor);
	}
	
	public void debitarPassagem(int cartaoID, float valor){
		cartaoDAO.debitarPassagem(cartaoID, valor);
	}
	
	public void registrarViagem(int cartaoID, Linha linha){
		Cartao cartao = cartaoDAO.buscarCartao(cartaoID);
		viagemDAO.registrarViagem(cartao, linha);
		
	}
	
	public void carregarCartao(int cartaoID, float valor) {
		System.out.println("Entrou no carregar cartao do CartaoMgr");
		cartaoDAO.carregarCartao(cartaoID, valor);
		Cartao cartao = cartaoDAO.buscarCartao(cartaoID);
		pagamentoDAO.registrarPagamento(cartao, valor);
	}

	public List buscarTiposPermitidos(Passageiro passageiro) {
		Collection cartoes = interfacePassageiroMgt.buscarCartoesPorPassageiro(passageiro.getCpf());
		if (cartoes != null){
			List tiposJaAdquiridos = cartaoDAO.buscarTiposDosCartoes(cartoes);
			return tipoPassageiroDAO.buscarTiposPermitidos(tiposJaAdquiridos);
		}
		else {
			return tipoPassageiroDAO.buscarTodosTipos();
		}
	}

	public Cartao buscarCartao(int cartaoID){
		return cartaoDAO.buscarCartao(cartaoID);
	}
	
	public void removerCartao(int cartaoID){
		cartaoDAO.removerCartao(cartaoID);
	}
		
	public void alterarCartao(Cartao cartao) {
		cartaoDAO.alterarCartao(cartao);		
	}

	public List buscarCartoes() {
		return cartaoDAO.buscarCartoes();
	}

	public void criarCartao(Cartao cartao) {
		cartaoDAO.criarCartao(cartao);		
	}

	public List buscarTiposPassageiros() {
		return tipoPassageiroDAO.buscarTiposPassageiros();
	}
	
	public TipoPassageiro buscarTipoPassageiro(String nomeTipo){
		return tipoPassageiroDAO.buscarTipoPassageiro(nomeTipo);
	}

	public TipoPassageiro buscarTipoPassageiro(int tipoID) {
		return tipoPassageiroDAO.buscarTipoPassageiro(tipoID);
	}
	
	public void criarTipoPassageiro(TipoPassageiro tipo){
		tipoPassageiroDAO.criarTipoPassageiro(tipo);
	}
	
	public void alterarTipoPassageiro(TipoPassageiro tipo){
		tipoPassageiroDAO.alterarTipoPassageiro(tipo);
	}
	
	public void removerTipoPassageiro(int tipoID){
		tipoPassageiroDAO.removerTipoPassageiro(tipoID);
	}
	
	public Pagamento buscarPagamento(int pgtoID) {
		return pagamentoDAO.buscarPagamento(pgtoID);
	}
	
	public void alterarPagamento(Pagamento pagamento) {
		pagamentoDAO.alterarPagamento(pagamento);
	}

	public List buscarPagamentos() {
		return pagamentoDAO.buscarPagamentos();
	}

	public void criarPagamento(Pagamento pagamento) {
		pagamentoDAO.criarPagamento(pagamento);
	}

	public void removerPagamento(int pgtoID){
		pagamentoDAO.removerPagamento(pgtoID);
	}
	
	public List buscarViagensPorCartao(int cartaoID){
		return viagemDAO.buscarViagensPorCartao(cartaoID);
	}

	public List buscarViagens() {
		return viagemDAO.buscarViagens();
	}
	
	public Viagem buscarViagem(int viagemID){
		return viagemDAO.buscarViagem(viagemID);
	}
	
	public void criarViagem(Viagem viagem){
		viagemDAO.criarViagem(viagem);
	}
	
	public void alterarViagem(Viagem viagem){
		viagemDAO.alterarViagem(viagem);
	}
	
	public void removerViagem(int viagemID){
		viagemDAO.removerViagem(viagemID);
	}
}
