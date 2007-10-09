package lps.bet.basico.web.controlGerencia.funcionario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lps.bet.basico.funcionarioMgr.IFuncionarioMgt;
import lps.bet.basico.tiposDados.Cargo;
import lps.bet.basico.tiposDados.EmpresaViaria;
import lps.bet.basico.tiposDados.Funcionario;
import lps.bet.basico.tiposDados.Pagamento;
import lps.bet.basico.viacaoMgr.IViacaoMgt;
import lps.bet.basico.web.controlGerencia.UtilsGerencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class GerenciaFuncionario extends MultiActionController{
	
	IFuncionarioMgt interfaceFuncionarioMgt;
	IViacaoMgt interfaceViacaoMgt;
	SimpleDateFormat sdf;
	
	public GerenciaFuncionario(){
		sdf = new SimpleDateFormat();
		sdf.applyPattern("dd/MM/yyyy");
	}

	protected void criarFuncionario(Funcionario funcionario){
		interfaceFuncionarioMgt.criarFuncionario(funcionario);
	}

	protected ModelAndView buscarFuncionarios(){
		List funcionarios = interfaceFuncionarioMgt.buscarFuncionarios();
		List cargos = interfaceFuncionarioMgt.buscarCargos();
		
		Calendar data = Calendar.getInstance();

		ModelAndView mav = new ModelAndView("gerenciaFuncionario");
		
		mav.addObject("funcionarios", funcionarios);
		mav.addObject("cargos", cargos);
		mav.addObject("sdf", sdf);
		mav.addObject("data",data);
		return mav;
	}
	
	protected ModelAndView buscarFuncionario(int usuarioID){
		Funcionario funcionario = interfaceFuncionarioMgt.buscarFuncionario(usuarioID);
		ModelAndView mav = new ModelAndView("gerenciaFuncionario");
		if (funcionario==null){
			mav.addObject("mensagem", "Funcion�rio n�o encontrado.");
		}
		else{
			List funcionarios = new ArrayList();
			funcionarios.add(funcionario);			
			mav.addObject("funcionarios", funcionarios);
			mav.addObject("sdf", sdf);			
		}

		return mav;

	}
	
	protected void alterarFuncionario(Funcionario funcionario){
		interfaceFuncionarioMgt.alterarFuncionario(funcionario);
	}

	protected Funcionario montarFuncionario(HttpServletRequest request){
		String operacao = request.getParameter("operacao");
		Funcionario funcionario;
		
		//Opera��o de Cria��o
		if (request.getParameter("usuarioID")==null){
			funcionario = new Funcionario();
		}
		//Sen�o precisa buscar
		else {
			funcionario = interfaceFuncionarioMgt.buscarFuncionario(Integer.parseInt(request.getParameter("usuarioID")));
		}
		funcionario.setDtAdmissao(UtilsGerencia.calendarFromString(request.getParameter("dtAdmissao").trim()));

		EmpresaViaria empresa = interfaceViacaoMgt.buscarEmpresaViaria();
		Cargo cargo = interfaceFuncionarioMgt.buscarCargo(request.getParameter("nomeCargo"));
		
		funcionario.setNomeFuncionario(request.getParameter("nomeFuncionario").trim());
		funcionario.setEmpresaViaria(empresa);
		funcionario.setCargo(cargo);
		
		return funcionario;
	}
	
	protected ModelAndView mostrarForm(String funcionarioID){

		ModelAndView mav = new ModelAndView("formFuncionario");
		mav.addObject("funcionarioID",funcionarioID);
		
		mav.addObject("sdf", sdf);
		
		Funcionario funcionario = null;
		Calendar data;
		List cargos = interfaceFuncionarioMgt.buscarCargos();
		mav.addObject("cargos", cargos);
		
		if (funcionarioID == null){
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");						
			data = Calendar.getInstance();
		}
		else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			funcionario = interfaceFuncionarioMgt.buscarFuncionario(Integer.parseInt(funcionarioID));
			data = funcionario.getDtAdmissao();
		}
		mav.addObject("funcionario",funcionario);		
		mav.addObject("data",data);
		return mav;		
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String operacao = request.getParameter("operacao");
		
		if (request.getServletPath().equals("/gerenciaFuncionario.html")){
			// quando � chamado pela primeira vez a URL n�o possui o par�metro 'operacao'
			if (operacao == null)
				return buscarFuncionarios();

			if (operacao.equals("criar")){
				criarFuncionario(montarFuncionario(request));
			}
			else if (operacao.equals("alterar")){
				alterarFuncionario(montarFuncionario(request));
			}

			//C�digo do if a ser tirado posteriormente (n�o existe remover no Tarifa):
			if (operacao.equals("remover")){
				String[] funcionariosIDs = request.getParameterValues("chkFuncionarioID");

				for (int i = 0; i < funcionariosIDs.length; i++) {
					int funcionarioID = Integer.parseInt(funcionariosIDs[i]);
					interfaceFuncionarioMgt.removerFuncionario(funcionarioID);
				}
			}

			//Mostrando um funcion�rio ou todos, dependendo da operacao requisitada
			if (operacao.equals("buscar")){
				return buscarFuncionario(Integer.parseInt(request.getParameter("funcionarioID")));			
			}
			else {
				return buscarFuncionarios();
			}		
		}
		else{
			return mostrarForm(request.getParameter("funcionarioID"));
		}
	}

	public IFuncionarioMgt getInterfaceFuncionarioMgt() {
		return interfaceFuncionarioMgt;
	}

	public void setInterfaceFuncionarioMgt(IFuncionarioMgt interfaceFuncionarioMgt) {
		this.interfaceFuncionarioMgt = interfaceFuncionarioMgt;
	}

	public IViacaoMgt getInterfaceViacaoMgt() {
		return interfaceViacaoMgt;
	}

	public void setInterfaceViacaoMgt(IViacaoMgt interfaceViacaoMgt) {
		this.interfaceViacaoMgt = interfaceViacaoMgt;
	}
	
}
