package lps.bet.variabilidades.limitePassagensMgr;

import java.util.Collection;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.variabilidades.tiposDados.CartaoLimPassagens;
import lps.bet.variabilidades.tiposDados.TipoPassagLimPassagens;

public interface ILimitePassagensMgt {

	public void salvarTipoPassagLimPassagens(TipoPassagLimPassagens limPassagens);	
	public void registrarTipoPassagLimPassagens(TipoPassagLimPassagens limPassagens);	
	public TipoPassagLimPassagens buscarTipoPassagLimPassagens(int tipoID);	
	public TipoPassagLimPassagens buscarLimPassagensPorTipo(TipoPassageiro tipoPassageiro);	
	public Collection<TipoPassagLimPassagens> buscarTodosTipos();
	
	public void salvarCartaoLimPassagens(CartaoLimPassagens cartaoPassagens);
	public void registrarCartaoLimPassagens(CartaoLimPassagens cartaoPassagens);
	public CartaoLimPassagens buscarCartaoLimPassagens(int cartaoID);	    
	public CartaoLimPassagens buscarQtdPassagensPorCartao(Cartao cartao);
	public Collection<CartaoLimPassagens> buscarTodosCartoes();

	public boolean verificarPossibilidadeCarga(int cartaoID, float valor);
	
}
