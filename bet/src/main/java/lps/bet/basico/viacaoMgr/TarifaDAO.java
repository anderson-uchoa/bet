package lps.bet.basico.viacaoMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Tarifa;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TarifaDAO extends HibernateDaoSupport{

	String hqlBuscarTarifasOrdenadas;
	
	public String getHqlBuscarTarifasOrdenadas() {
		return hqlBuscarTarifasOrdenadas;
	}

	public void setHqlBuscarTarifasOrdenadas(String hqlBuscarTarifasOrdenadas) {
		this.hqlBuscarTarifasOrdenadas = hqlBuscarTarifasOrdenadas;
	}

	public void salvarTarifa(Tarifa tarifa) {
		getHibernateTemplate().saveOrUpdate(tarifa);
    }

	public void removerTarifa(int tarifaID){
		getHibernateTemplate().delete(buscarTarifa(tarifaID));
	}
	
	public List buscarTarifas(){
		return getHibernateTemplate().find(hqlBuscarTarifasOrdenadas);
	}
	
	public Tarifa buscarTarifa(){
		List tarifas = buscarTarifas();
		Tarifa tarifa = (Tarifa) tarifas.get(0);
		System.out.println("TARIFA: " + tarifa.getNomeTarifa() + " Valor Tarifa: " + tarifa.getValorTarifa());
		return (Tarifa) tarifas.get(0);
	}
	
	public Tarifa buscarTarifa(int tarifaID){
		return (Tarifa) getHibernateTemplate().get(Tarifa.class, new Integer(tarifaID));
	}
	
}
