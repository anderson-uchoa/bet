package lps.bet.basico.web.controlGerencia.viacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.tiposDados.EmpresaViaria;
import lps.bet.basico.viacaoMgr.IViacaoMgt;
import lps.bet.basico.web.ControladorBet;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaEmpresaViaria extends ControladorBet{

	IViacaoMgt interfaceViacaoMgt;

	protected ModelAndView buscarEmpresaViaria(){
		EmpresaViaria empresa = interfaceViacaoMgt.buscarEmpresaViaria();
		ModelAndView mav = new ModelAndView("gerenciaEmprViaria");
		mav.addObject("empresa", empresa);
		return mav;
	}

	protected boolean criarEmpresaViaria(EmpresaViaria empresa){
		return interfaceViacaoMgt.criarEmpresaViaria(empresa);
	}
	
	protected void alterarEmpresaViaria(EmpresaViaria empresa){
		interfaceViacaoMgt.alterarEmpresaViaria(empresa);
	}
	
	protected EmpresaViaria montarEmpresaViaria(HttpServletRequest request) {
		EmpresaViaria empresa;

		//Operação de Criação
		if (request.getParameter("empresaID")==null){
			empresa = new EmpresaViaria();
		}
		
		//Senão precisa buscar
		else{
			empresa = interfaceViacaoMgt.buscarEmpresaViaria();
		}
		
		empresa.setNomeFantasia(request.getParameter("nomeFantasia"));
		
		return empresa;
	}	

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao");
			
		// quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
		if (operacao == null)
			return buscarEmpresaViaria();
		
		if (operacao.equals("criar")){
			criarEmpresaViaria(montarEmpresaViaria(request));
		}
		else if (operacao.equals("alterar")){
			alterarEmpresaViaria(montarEmpresaViaria(request));
		}

		return buscarEmpresaViaria();

	}
	
	public IViacaoMgt getInterfaceViacaoMgt() {
		return interfaceViacaoMgt;
	}

	public void setInterfaceViacaoMgt(IViacaoMgt interfaceViacaoMgt) {
		this.interfaceViacaoMgt = interfaceViacaoMgt;
	}
		
}
