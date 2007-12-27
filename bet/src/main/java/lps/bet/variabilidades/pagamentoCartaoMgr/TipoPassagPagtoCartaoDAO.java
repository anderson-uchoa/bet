package lps.bet.variabilidades.pagamentoCartaoMgr;

import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class TipoPassagPagtoCartaoDAO extends HibernateDaoSupport {
    String hqlBuscarUltimoTipoPassageiro;

    public void salvarTipoPassagPagtoCartao(TipoPassagPagtoCartao tipoPassageiro) {
        getHibernateTemplate().saveOrUpdate(tipoPassageiro);
    }

    public void registrarTipoPassagPagtoCartao(int tipoID, boolean pagtoAquisicaoCartao, float valorAquisicao) {
        TipoPassagPagtoCartao tipoPassageiro = new TipoPassagPagtoCartao();
        tipoPassageiro.setTipoID(tipoID);
        tipoPassageiro.setPagtoAquisicaoCartao(pagtoAquisicaoCartao);
        tipoPassageiro.setValorAquisicao(valorAquisicao);
        salvarTipoPassagPagtoCartao(tipoPassageiro);
    }

    public TipoPassagPagtoCartao buscarTipoPassagPagtoCartao(int tipoID) {
        return (TipoPassagPagtoCartao) getHibernateTemplate().get(TipoPassagPagtoCartao.class, new Integer(tipoID));

    }

    public int buscarUltimoTipoPassageiro() {
        //List passageiros = getHibernateTemplate().find(hqlBuscarUltimoTipoPassageiro);
        //return ((TipoPassagPagtoCartao) passageiros.get(0)).getTipoID();
        int ultimo;
        List passageiros = getHibernateTemplate().loadAll(TipoPassagPagtoCartao.class);

        if (passageiros.isEmpty()) {
            return 1;
        } else {
            ultimo = passageiros.size();
            return ((TipoPassagPagtoCartao) passageiros.get(ultimo - 1)).getTipoID();
        }
    }

    public String getHqlBuscarUltimoTipoPassageiro() {
        return hqlBuscarUltimoTipoPassageiro;
    }

    public void setHqlBuscarUltimoTipoPassageiro(
            String hqlBuscarUltimoTipoPassageiro) {
        this.hqlBuscarUltimoTipoPassageiro = hqlBuscarUltimoTipoPassageiro;
    }
}
