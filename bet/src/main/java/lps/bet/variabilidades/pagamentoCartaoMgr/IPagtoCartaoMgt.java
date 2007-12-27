package lps.bet.variabilidades.pagamentoCartaoMgr;

import lps.bet.variabilidades.tiposDados.PagamentoPagtoCartao;
import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;

public interface IPagtoCartaoMgt {
    public void registrarPagtoCartao(int pagtoID, String tipoPagto);

    public PagamentoPagtoCartao buscarPagamentoPagtoCartao(int pgtoID);

    public int buscarUltimoPagamento();

    public void registrarTipoPassagPagtoCartao(int tipoID, boolean pagtoAquisicaoCartao, float valorAquisicao);

    public TipoPassagPagtoCartao buscarTipoPassagPagtoCartao(int tipoID);

    public int buscarUltimoTipoPassageiro();


}
