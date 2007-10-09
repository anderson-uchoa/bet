package lps.bet.basico.funcionarioMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Cargo;
import lps.bet.basico.tiposDados.Funcionario;

public class FuncionarioMgr implements IFuncionarioMgt{

	FuncionarioDAO funcionarioDAO;
	CargoDAO cargoDAO;
	
	public void alterarFuncionario(Funcionario funcionario) {
		funcionarioDAO.alterarFuncionario(funcionario);		
	}

	public Funcionario buscarFuncionario(int usuarioID) {
		return funcionarioDAO.buscarFuncionario(usuarioID);
	}

	public List buscarFuncionarios() {
		return funcionarioDAO.buscarFuncionarios();
	}

	public void criarFuncionario(Funcionario funcionario) {
		funcionarioDAO.criarFuncionario(funcionario);		
	}

	public void removerFuncionario(int usuarioID) {
		funcionarioDAO.removerFuncionario(usuarioID);
	}

	public void alterarCargo(Cargo cargo) {
		cargoDAO.alterarCargo(cargo);
	}

	public Cargo buscarCargo(int cargoID) {
		return cargoDAO.buscarCargo(cargoID);
	}
	

	public Cargo buscarCargo(String nomeCargo) {
		return cargoDAO.buscarCargo(nomeCargo);
	}

	public List buscarCargos() {
		return cargoDAO.buscarCargos();
	}

	public void criarCargo(Cargo cargo) {
		cargoDAO.criarCargo(cargo);
	}

	public void removerCargo(int cargoID) {
		cargoDAO.removerCargo(cargoID);
	}

	public FuncionarioDAO getFuncionarioDAO() {
		return funcionarioDAO;
	}

	public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}

	public CargoDAO getCargoDAO() {
		return cargoDAO;
	}

	public void setCargoDAO(CargoDAO cargoDAO) {
		this.cargoDAO = cargoDAO;
	}
	
}
