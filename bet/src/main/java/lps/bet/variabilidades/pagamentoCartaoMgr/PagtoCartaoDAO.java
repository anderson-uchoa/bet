package lps.bet.variabilidades.pagamentoCartaoMgr;

import lps.bet.variabilidades.tiposDados.PagamentoPagtoCartao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Collection;
import java.util.List;

public class PagtoCartaoDAO extends HibernateDaoSupport {

    String hqlBuscarUltimoPagamento;

    public void salvarPagamentoPagtoCartao(PagamentoPagtoCartao pagamento) {
        getHibernateTemplate().saveOrUpdate(pagamento);
    }

    public void criarPagtoCartao(PagamentoPagtoCartao pgtoCartao) {
        salvarPagamentoPagtoCartao(pgtoCartao);
    }

    public void alterarPagtoCartao(PagamentoPagtoCartao pgtoCartao) {
        salvarPagamentoPagtoCartao(pgtoCartao);
    }
    
    public PagamentoPagtoCartao buscarPagamentoPagtoCartao(int pgtoID) {
        return (PagamentoPagtoCartao) getHibernateTemplate().get(PagamentoPagtoCartao.class, new Integer(pgtoID));
    }

    public int buscarUltimoPagamento() {
        int ultimo;
        //List pagamentos = getHibernateTemplate().find(hqlBuscarUltimoPagamento);
        List pagamentos = getHibernateTemplate().loadAll(PagamentoPagtoCartao.class);

        if (pagamentos.isEmpty()) {
            return 1;
        } else {
            ultimo = pagamentos.size();
            return ((PagamentoPagtoCartao) pagamentos.get(ultimo - 1)).getPagtoID();
        }
    }
    
    public Collection<PagamentoPagtoCartao> buscarPagtosCartao() {
    	return getHibernateTemplate().loadAll(PagamentoPagtoCartao.class);
    }

    public String getHqlBuscarUltimoPagamento() {
        return hqlBuscarUltimoPagamento;
    }

    public void setHqlBuscarUltimoPagamento(String hqlBuscarUltimoPagamento) {
        this.hqlBuscarUltimoPagamento = hqlBuscarUltimoPagamento;
    }
}
