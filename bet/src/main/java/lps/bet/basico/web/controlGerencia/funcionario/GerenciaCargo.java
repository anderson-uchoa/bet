package lps.bet.basico.web.controlGerencia.funcionario;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.funcionarioMgr.IFuncionarioMgt;
import lps.bet.basico.tiposDados.Cargo;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class GerenciaCargo extends MultiActionController {

	IFuncionarioMgt interfaceFuncionarioMgt;

	protected void criarCargo(Cargo cargo){
		interfaceFuncionarioMgt.criarCargo(cargo);
	}
	
	protected ModelAndView buscarCargos(){
		List cargos = interfaceFuncionarioMgt.buscarCargos();
		
		ModelAndView mav = new ModelAndView("gerenciaCargo");
		mav.addObject("cargos", cargos);
		return mav;
	}
	
	protected ModelAndView buscarCargo(int cargoID){
		Cargo cargo = interfaceFuncionarioMgt.buscarCargo(cargoID);
		ModelAndView mav = new ModelAndView("gerenciaCargo");
		if (cargo == null){
			mav.addObject("mensagem", "Cargo não encontrado.");
		}
		else{
			List cargos = new ArrayList();
			cargos.add(cargo);

			mav.addObject("cargos", cargos);			
		}
		return mav;
	}
	
	protected void alterarCargo(Cargo cargo){
		interfaceFuncionarioMgt.alterarCargo(cargo);
	}

	protected Cargo montarCargo(HttpServletRequest request){
		String operacao = request.getParameter("operacao");
		Cargo cargo;
		
		//Operação de Criação
		if (request.getParameter("cargoID")==null){
			cargo = new Cargo();
		}
		//Senão precisa buscar
		else {
			cargo = interfaceFuncionarioMgt.buscarCargo(Integer.parseInt(request.getParameter("cargoID")));
		}

		cargo.setNomeCargo(request.getParameter("nomeCargo").trim());
						
		return cargo;
	}
	
	protected ModelAndView mostrarForm(String cargoID){

		ModelAndView mav = new ModelAndView("formCargo");
		mav.addObject("cargoID",cargoID);
		
		Cargo cargo = null;
		
		if (cargoID == null){
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");						
		}
		else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			cargo = interfaceFuncionarioMgt.buscarCargo(Integer.parseInt(cargoID));
		}
		mav.addObject("cargo",cargo);		
		return mav;		
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String operacao = request.getParameter("operacao");

		if (request.getServletPath().equals("/gerenciaCargo.html")){	
			// quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
			if (operacao == null)
				return buscarCargos();

			if (operacao.equals("criar")){
				criarCargo(montarCargo(request));
			}
			else if (operacao.equals("alterar")){
				alterarCargo(montarCargo(request));
			}

			//Código do if a ser tirado posteriormente (não existe remover no Tarifa):
			if (operacao.equals("remover")){
				String[] cargosIDs = request.getParameterValues("chkCargoID");

				for (int i = 0; i < cargosIDs.length; i++) {
					int cargoID = Integer.parseInt(cargosIDs[i]);
					interfaceFuncionarioMgt.removerCargo(cargoID);
				}
			}

			//Mostrando um cargo ou todos, dependendo da operacao requisitada
			if (operacao.equals("buscar")){
				return buscarCargo(Integer.parseInt(request.getParameter("cargoID")));			
			}
			else {
				return buscarCargos();
			}		
		}
		else{
			return mostrarForm(request.getParameter("cargoID"));
		}
	}

	public IFuncionarioMgt getInterfaceFuncionarioMgt() {
		return interfaceFuncionarioMgt;
	}

	public void setInterfaceFuncionarioMgt(IFuncionarioMgt interfaceFuncionarioMgt) {
		this.interfaceFuncionarioMgt = interfaceFuncionarioMgt;
	}
	
}
