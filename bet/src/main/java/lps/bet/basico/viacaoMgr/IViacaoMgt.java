package lps.bet.basico.viacaoMgr;

import java.util.List;

import lps.bet.basico.tiposDados.EmpresaViaria;
import lps.bet.basico.tiposDados.SistViarioUrbano;
import lps.bet.basico.tiposDados.Tarifa;

public interface IViacaoMgt {
	
	//Métodos fornecidos pela interface relacionados a Tarifa
	public Tarifa buscarTarifa();
	public Tarifa buscarTarifa(int tarifaID);
	public List buscarTarifas();
	public void criarTarifa(Tarifa tarifa);
	public void alterarTarifa(Tarifa tarifa);
	
	//A ser removido posteriormente
	public void removerTarifa(int tarifaID);
	
	//Métodos fornecidos pela interface relacionados a Empresa Viária:
	public EmpresaViaria buscarEmpresaViaria();
	public boolean criarEmpresaViaria(EmpresaViaria empresa);
	public void alterarEmpresaViaria(EmpresaViaria empresa);
	
	//Métodos fornecidos pela interface relacionados a Sistema Urbano:
	public SistViarioUrbano buscarSistViarioUrbano();
	public boolean criarSistViarioUrbano(SistViarioUrbano sistema);
	public void alterarSistViarioUrbano(SistViarioUrbano sistema);
	
}
