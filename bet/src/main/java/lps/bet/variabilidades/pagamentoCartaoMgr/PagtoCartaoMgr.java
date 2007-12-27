package lps.bet.variabilidades.pagamentoCartaoMgr;

import lps.bet.variabilidades.tiposDados.PagamentoPagtoCartao;
import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;

public class PagtoCartaoMgr implements IPagtoCartaoMgt {
    PagtoCartaoDAO pagtoCartaoDAO;
    TipoPassagPagtoCartaoDAO tipoPassagPagtoCartaoDAO;

    public PagtoCartaoDAO getPagtoCartaoDAO() {
        return pagtoCartaoDAO;
    }

    public void setPagtoCartaoDAO(PagtoCartaoDAO pagtoCartaoDAO) {
        this.pagtoCartaoDAO = pagtoCartaoDAO;
    }

    public TipoPassagPagtoCartaoDAO getTipoPassagPagtoCartaoDAO() {
        return tipoPassagPagtoCartaoDAO;
    }

    public void setTipoPassagPagtoCartaoDAO(
            TipoPassagPagtoCartaoDAO tipoPassagPagtoCartaoDAO) {
        this.tipoPassagPagtoCartaoDAO = tipoPassagPagtoCartaoDAO;
    }

    public PagtoCartaoMgr() {
    }

    public PagamentoPagtoCartao buscarPagamentoPagtoCartao(int pgtoID) {
        return pagtoCartaoDAO.buscarPagamentoPagtoCartao(pgtoID);
    }

    public TipoPassagPagtoCartao buscarTipoPassagPagtoCartao(int tipoID) {
        return tipoPassagPagtoCartaoDAO.buscarTipoPassagPagtoCartao(tipoID);
    }

    public int buscarUltimoPagamento() {
        return pagtoCartaoDAO.buscarUltimoPagamento();
    }

    public int buscarUltimoTipoPassageiro() {
        return tipoPassagPagtoCartaoDAO.buscarUltimoTipoPassageiro();
    }

    public void registrarPagtoCartao(int pagtoID, String tipoPagto) {
        pagtoCartaoDAO.registrarPagtoCartao(pagtoID, tipoPagto);

    }

    public void registrarTipoPassagPagtoCartao(int tipoID,
                                               boolean pagtoAquisicaoCartao, float valorAquisicao) {
        tipoPassagPagtoCartaoDAO.registrarTipoPassagPagtoCartao(tipoID, pagtoAquisicaoCartao, valorAquisicao);
    }


}
