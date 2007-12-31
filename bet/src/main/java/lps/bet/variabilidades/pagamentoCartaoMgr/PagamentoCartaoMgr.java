package lps.bet.variabilidades.pagamentoCartaoMgr;

import java.util.ArrayList;
import java.util.Collection;

import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.variabilidades.tiposDados.PagamentoPagtoCartao;
import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;

public class PagamentoCartaoMgr implements IPagtoCartaoMgt {
    
	PagtoCartaoDAO pagtoCartaoDAO;
    TipoPassagPagtoCartaoDAO tipoPassagPagtoCartaoDAO;
    ICartaoMgt interfaceCartaoMgt;

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

    public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}

	public PagamentoPagtoCartao buscarPagamentoPagtoCartao(int pgtoID) {
        return pagtoCartaoDAO.buscarPagamentoPagtoCartao(pgtoID);
    }

    public TipoPassagPagtoCartao buscarTipoPassagPagtoCartao(int tipoID) {
        return tipoPassagPagtoCartaoDAO.buscarTipoPassagPagtoCartao(tipoID);
    }
    
    public TipoPassagPagtoCartao buscarTipoPagtoPorTipoPassageiro(TipoPassageiro tipoPassageiro){
    	return tipoPassagPagtoCartaoDAO.buscarTipoPagtoPorTipoPassageiro(tipoPassageiro);
    }

    public int buscarUltimoPagamento() {
        return pagtoCartaoDAO.buscarUltimoPagamento();
    }

    public int buscarUltimoTipoPassageiro() {
        return tipoPassagPagtoCartaoDAO.buscarUltimoTipoPassageiro();
    }

    public void criarPagtoCartao(PagamentoPagtoCartao pgtoCartao) {
        pagtoCartaoDAO.criarPagtoCartao(pgtoCartao);
    }
    
    public void alterarPagtoCartao(PagamentoPagtoCartao pgtoCartao) {
        pagtoCartaoDAO.alterarPagtoCartao(pgtoCartao);

    }

    public void registrarTipoPassagPagtoCartao(TipoPassagPagtoCartao tipoPagamento) {
        tipoPassagPagtoCartaoDAO.registrarTipoPassagPagtoCartao(tipoPagamento);
    }

    public Collection<TipoPassagPagtoCartao> buscarTodosTipos(){
    	return tipoPassagPagtoCartaoDAO.buscarTodosTipos();
    }

    
    
    public Collection<PagamentoPagtoCartao> buscarPagtosCartao() {
		return pagtoCartaoDAO.buscarPagtosCartao();
	}

	public Collection<TipoPassagPagtoCartao> buscarTiposPagtosPermitidos(Collection<TipoPassageiro> tiposPermitidos){
    	
    	Collection<TipoPassagPagtoCartao> tiposPagtosPermitidos = new ArrayList<TipoPassagPagtoCartao>();
    	Collection<TipoPassagPagtoCartao> todosTiposPagtos = buscarTodosTipos();
    	
    	for (TipoPassagPagtoCartao tipoPassagPagtoCartao : todosTiposPagtos) {
			if (tiposPermitidos.contains(tipoPassagPagtoCartao.getTipoPassageiro())){
				tiposPagtosPermitidos.add(tipoPassagPagtoCartao);
			}
		}
    	return tiposPagtosPermitidos;
    }
    
}
