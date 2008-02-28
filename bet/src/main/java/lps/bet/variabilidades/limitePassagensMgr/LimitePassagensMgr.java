package lps.bet.variabilidades.limitePassagensMgr;

import java.util.Calendar;
import java.util.Collection;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.Period;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.viacaoMgr.IViacaoMgt;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.variabilidades.tiposDados.CartaoLimPassagens;
import lps.bet.variabilidades.tiposDados.TipoPassagLimPassagens;

public class LimitePassagensMgr implements ILimitePassagensMgt{

	TipoPassagLimPassagensDAO tipoPassagLimPassagensDAO;
	CartaoLimPassagensDAO cartaoLimPassagensDAO;
	
	IViacaoMgt interfaceViacaoMgt;
	ICartaoMgt interfaceCartaoMgt;
	
	public TipoPassagLimPassagens buscarLimPassagensPorTipo(TipoPassageiro tipoPassageiro){
		return tipoPassagLimPassagensDAO.buscarLimPassagensPorTipo(tipoPassageiro);
	}

	public TipoPassagLimPassagens buscarTipoPassagLimPassagens(int tipoID) {
		return tipoPassagLimPassagensDAO.buscarTipoPassagLimPassagens(tipoID);
	}

	public Collection<TipoPassagLimPassagens> buscarTodosTipos() {
		return tipoPassagLimPassagensDAO.buscarTodosTipos();
	}

	public void registrarTipoPassagLimPassagens(TipoPassagLimPassagens limPassagens) {
		tipoPassagLimPassagensDAO.registrarTipoPassagLimPassagens(limPassagens);		
	}

	public void salvarTipoPassagLimPassagens(TipoPassagLimPassagens limPassagens) {
		tipoPassagLimPassagensDAO.salvarTipoPassagLimPassagens(limPassagens);		
	}

	public CartaoLimPassagens buscarCartaoLimPassagens(int cartaoID) {
		return cartaoLimPassagensDAO.buscarCartaoLimPassagens(cartaoID);
	}

	public CartaoLimPassagens buscarQtdPassagensPorCartao(Cartao cartao) {
		return cartaoLimPassagensDAO.buscarQtdPassagensPorCartao(cartao);
	}

	public Collection<CartaoLimPassagens> buscarTodosCartoes() {
		return cartaoLimPassagensDAO.buscarTodosCartoes();
	}

	public void registrarCartaoLimPassagens(CartaoLimPassagens cartaoPassagens) {
		cartaoLimPassagensDAO.registrarCartaoLimPassagens(cartaoPassagens);		
	}

	public void salvarCartaoLimPassagens(CartaoLimPassagens cartaoPassagens) {
		cartaoLimPassagensDAO.salvarCartaoLimPassagens(cartaoPassagens);
	}
	
	public boolean verificarPossibilidadeCarga(int cartaoID, float valor) {
		
		Cartao cartao = interfaceCartaoMgt.buscarCartao(cartaoID);		
		float tarifa = interfaceViacaoMgt.buscarTarifa().getValorTarifa();
		int desconto = cartao.getTipoPassageiro().getDesconto();
		
		Float floatQtdPassagens = valor/(tarifa*(1 - desconto/100));
		int qtdPassagens = floatQtdPassagens.intValue();
		
		int limitePassagens = tipoPassagLimPassagensDAO.buscarLimPassagensPorTipo(cartao.getTipoPassageiro()).getLimitePassagens();
		
		//Verifica já inicialmente se a quantidade requisitada ultrapassa o limite
		if (qtdPassagens > limitePassagens){
			return false;
		}

		CartaoLimPassagens cartaoLimPassagens = cartaoLimPassagensDAO.buscarCartaoLimPassagens(cartaoID);
		
		//Primeira carga do cartão, a data de início de contagem recebe o dia em si
		if (cartaoLimPassagens == null){
			cartaoLimPassagens = new CartaoLimPassagens();
			cartaoLimPassagens.setCartaoID(cartaoID);
			cartaoLimPassagens.setCartao(cartao);
			cartaoLimPassagens.setQtdPassagensMes(0);
			cartaoLimPassagens.setDtInicioContagem(Calendar.getInstance());			
			cartaoLimPassagensDAO.salvarCartaoLimPassagens(cartaoLimPassagens);
		}
		
		Calendar dtInicio = cartaoLimPassagens.getDtInicioContagem();		
		Calendar dtAtual = Calendar.getInstance();
	
		DateTime inicio = new DateTime(dtInicio);
		DateTime fim = new DateTime(dtAtual);
		Months periodoEntreCargas = Months.monthsBetween(inicio, fim);
		
		int qtdPassagensMes = cartaoLimPassagens.getQtdPassagensMes();

		//Se a data atual for mais do que um mês desde a data de início, 
		//então a qtdPassagensMes corresponde à qtdPassagens 
		//colocar o mês de Inicio como sendo o mês da data atual
		if (periodoEntreCargas.isGreaterThan(Months.ZERO)){
			dtInicio.set(Calendar.MONTH, dtAtual.get(Calendar.MONTH));
			dtInicio.set(Calendar.YEAR, dtAtual.get(Calendar.YEAR));
			cartaoLimPassagens.setDtInicioContagem(dtInicio);
			cartaoLimPassagens.setQtdPassagensMes(qtdPassagens);
			cartaoLimPassagensDAO.salvarCartaoLimPassagens(cartaoLimPassagens);
			return true;
		}
		//Foram feitas cargas dentro do mês
		else{
			//A quantidade de passagens a serem compradas ultrapassa o limite mensal
			if(qtdPassagensMes + qtdPassagens > limitePassagens){
				return false;
			}
			//A quantidade não ultrapassa o limite mensal
			else{
				cartaoLimPassagens.setQtdPassagensMes(qtdPassagensMes + qtdPassagens);
				cartaoLimPassagensDAO.salvarCartaoLimPassagens(cartaoLimPassagens);
				return true;
			}
		}		
		
	}

	public TipoPassagLimPassagensDAO getTipoPassagLimPassagensDAO() {
		return tipoPassagLimPassagensDAO;
	}

	public void setTipoPassagLimPassagensDAO(
			TipoPassagLimPassagensDAO tipoPassagLimPassagensDAO) {
		this.tipoPassagLimPassagensDAO = tipoPassagLimPassagensDAO;
	}

	public CartaoLimPassagensDAO getCartaoLimPassagensDAO() {
		return cartaoLimPassagensDAO;
	}

	public void setCartaoLimPassagensDAO(CartaoLimPassagensDAO cartaoLimPassagensDAO) {
		this.cartaoLimPassagensDAO = cartaoLimPassagensDAO;
	}

	public IViacaoMgt getInterfaceViacaoMgt() {
		return interfaceViacaoMgt;
	}

	public void setInterfaceViacaoMgt(IViacaoMgt interfaceViacaoMgt) {
		this.interfaceViacaoMgt = interfaceViacaoMgt;
	}

	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}	
}
