package lps.bet.basico.funcionarioMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Cargo;
import lps.bet.basico.tiposDados.Funcionario;

public interface IFuncionarioMgt {

	public Funcionario buscarFuncionario(int funcionarioID);	
	public List buscarFuncionarios();	
	public void criarFuncionario(Funcionario funcionario);	
	public void alterarFuncionario(Funcionario funcionario);	
	public void removerFuncionario(int funcionarioID);

	public Cargo buscarCargo(int cargoID);
	public Cargo buscarCargo(String nomeCargo);
	public List buscarCargos();	
	public void criarCargo(Cargo cargo);	
	public void alterarCargo(Cargo cargo);	
	public void removerCargo(int cargoID);	
	
}
