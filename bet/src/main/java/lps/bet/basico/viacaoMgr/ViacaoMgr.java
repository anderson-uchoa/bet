package lps.bet.basico.viacaoMgr;

import java.util.List;

import lps.bet.basico.tiposDados.EmpresaViaria;
import lps.bet.basico.tiposDados.SistViarioUrbano;
import lps.bet.basico.tiposDados.Tarifa;

public class ViacaoMgr implements IViacaoMgt{
	
	EmpresaViariaDAO empresaViariaDAO;
	SistViarioUrbanoDAO sistViarioUrbanoDAO;
	TarifaDAO tarifaDAO;
	
	public TarifaDAO getTarifaDAO() {
		return tarifaDAO;
	}
	public void setTarifaDAO(TarifaDAO tarifaDAO) {
		this.tarifaDAO = tarifaDAO;
	}
	public EmpresaViariaDAO getEmpresaViariaDAO() {
		return empresaViariaDAO;
	}
	public void setEmpresaViariaDAO(EmpresaViariaDAO empresaDAO) {
		this.empresaViariaDAO = empresaDAO;
	}
	public SistViarioUrbanoDAO getSistViarioUrbanoDAO() {
		return sistViarioUrbanoDAO;
	}
	public void setSistViarioUrbanoDAO(SistViarioUrbanoDAO sistemaDAO) {
		this.sistViarioUrbanoDAO = sistemaDAO;
	}
	
	public Tarifa buscarTarifa(){
		return tarifaDAO.buscarTarifa();
	}

	public Tarifa buscarTarifa(int tarifaID){
		return tarifaDAO.buscarTarifa(tarifaID);
	} 
	
	public List buscarTarifas(){
		return tarifaDAO.buscarTarifas();
	}
	
	public EmpresaViaria buscarEmpresaViaria() {
		return empresaViariaDAO.buscarEmpresaViaria();
	}
	
	public SistViarioUrbano buscarSistViarioUrbano() {
		return sistViarioUrbanoDAO.buscarSistViarioUrbano();
	}
	
	public void criarTarifa(Tarifa tarifa){
		tarifaDAO.salvarTarifa(tarifa);
	}
	public void alterarEmpresaViaria(EmpresaViaria empresa) {
		empresaViariaDAO.salvarEmpresaViaria(empresa);
	}
	
	public void alterarSistViarioUrbano(SistViarioUrbano sistema) {
		sistViarioUrbanoDAO.salvarSistViarioUrbano(sistema);
	}
	
	public void alterarTarifa(Tarifa tarifa) {
		tarifaDAO.salvarTarifa(tarifa);
	}
	
	public boolean criarEmpresaViaria(EmpresaViaria empresa) {
		if (empresaViariaDAO.buscarEmpresaViaria() != null){
			return false;
		}
		else{
			empresaViariaDAO.salvarEmpresaViaria(empresa);
			if (empresaViariaDAO.buscarEmpresaViaria() == empresa){
				return true;
			}
			else return false;
		}
	}
	
	public boolean criarSistViarioUrbano(SistViarioUrbano sistema) {
		if (sistViarioUrbanoDAO.buscarSistViarioUrbano() != null){
			return false;
		}
		else{
			sistViarioUrbanoDAO.salvarSistViarioUrbano(sistema);
			if (sistViarioUrbanoDAO.buscarSistViarioUrbano() == sistema){
				return true;
			}
			else return false;
		}
	}
	
	//A ser removido posteriormente:
	public void removerTarifa(int tarifaID) {
		tarifaDAO.removerTarifa(tarifaID);
		
	}	
		
}
