package lps.bet.basico.funcionarioMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Cargo;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CargoDAO extends HibernateDaoSupport {
	
	String hqlBuscarCargoPorNome;
	
	public void salvarCargo(Cargo cargo) {
		getHibernateTemplate().saveOrUpdate(cargo);
    }

	public Cargo buscarCargo(int cargoID){
		return (Cargo) getHibernateTemplate().get(Cargo.class, new Integer(cargoID));
	}
	
	public Cargo buscarCargo(String nomeCargo){
		List cargos = getHibernateTemplate().findByNamedParam(hqlBuscarCargoPorNome, "nomeCargo", nomeCargo);
		//List cargos = getHibernateTemplate().find(hqlBuscarCargoPorNome, nomeCargo);
		return (Cargo) cargos.get(0);
	}
	
	public List buscarCargos(){
		return getHibernateTemplate().loadAll(Cargo.class);
	}
		
	public void alterarCargo(Cargo cargo) {
		salvarCargo(cargo);		
	}

	public void criarCargo(Cargo cargo) {
		salvarCargo(cargo);		
	}

	public void removerCargo(Cargo cargo){
		getHibernateTemplate().delete(cargo);
	}

	public void removerCargo(int cargoID) {
		Cargo cargo = buscarCargo(cargoID);
		removerCargo(cargo);
	}

	public String getHqlBuscarCargoPorNome() {
		return hqlBuscarCargoPorNome;
	}

	public void setHqlBuscarCargoPorNome(String hqlBuscarCargoPorNome) {
		this.hqlBuscarCargoPorNome = hqlBuscarCargoPorNome;
	}
}
