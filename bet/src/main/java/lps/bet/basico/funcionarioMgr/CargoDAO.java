package lps.bet.basico.funcionarioMgr;

import lps.bet.basico.tiposDados.Cargo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class CargoDAO extends HibernateDaoSupport {

    public void salvarCargo(Cargo cargo) {
        getHibernateTemplate().saveOrUpdate(cargo);
    }

    public Cargo buscarCargo(int cargoID) {
        return (Cargo) getHibernateTemplate().get(Cargo.class, cargoID);
    }

    public Cargo buscarCargo(String nomeCargo) {
        DetachedCriteria cargoPorNome = DetachedCriteria.forClass(Cargo.class);
        cargoPorNome.add(Restrictions.eq("nomeCargo", nomeCargo));
        List cargos = getHibernateTemplate().findByCriteria(cargoPorNome);
        return (Cargo) cargos.get(0);
    }

    public List buscarCargos() {
        return getHibernateTemplate().loadAll(Cargo.class);
    }

    public void alterarCargo(Cargo cargo) {
        salvarCargo(cargo);
    }

    public void criarCargo(Cargo cargo) {
        salvarCargo(cargo);
    }

    public void removerCargo(Cargo cargo) {
        getHibernateTemplate().delete(cargo);
    }

    public void removerCargo(int cargoID) {
        Cargo cargo = buscarCargo(cargoID);
        removerCargo(cargo);
    }
}
