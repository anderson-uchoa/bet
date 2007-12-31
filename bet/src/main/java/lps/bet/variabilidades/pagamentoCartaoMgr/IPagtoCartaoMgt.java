package lps.bet.variabilidades.pagamentoCartaoMgr;

import java.util.Collection;

import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.variabilidades.tiposDados.PagamentoPagtoCartao;
import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;

public interface IPagtoCartaoMgt {
	
    public void criarPagtoCartao(PagamentoPagtoCartao pgtoCartao);
    public void alterarPagtoCartao(PagamentoPagtoCartao pgtoCartao);
    public PagamentoPagtoCartao buscarPagamentoPagtoCartao(int pgtoID);

    public int buscarUltimoPagamento();

    public void registrarTipoPassagPagtoCartao(TipoPassagPagtoCartao tipoPagamento);

    public TipoPassagPagtoCartao buscarTipoPassagPagtoCartao(int tipoID);
    public TipoPassagPagtoCartao buscarTipoPagtoPorTipoPassageiro(TipoPassageiro tipoPassageiro);    
    public int buscarUltimoTipoPassageiro();

    public Collection<TipoPassagPagtoCartao> buscarTodosTipos();    
    public Collection<PagamentoPagtoCartao> buscarPagtosCartao();
    public Collection<TipoPassagPagtoCartao> buscarTiposPagtosPermitidos(Collection<TipoPassageiro> tiposPermitidos);
}
