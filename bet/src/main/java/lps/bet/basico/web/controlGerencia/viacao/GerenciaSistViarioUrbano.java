package lps.bet.basico.web.controlGerencia.viacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.tiposDados.SistViarioUrbano;
import lps.bet.basico.viacaoMgr.IViacaoMgt;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class GerenciaSistViarioUrbano extends MultiActionController{

	IViacaoMgt interfaceViacaoMgt;

	protected ModelAndView buscarSistViarioUrbano(){
		SistViarioUrbano sistema = interfaceViacaoMgt.buscarSistViarioUrbano();
		ModelAndView mav = new ModelAndView("gerenciaSistViarioUrbano");
		mav.addObject("sistema", sistema);
		return mav;
	}

	protected boolean criarSistViarioUrbano(SistViarioUrbano sistema){
		return interfaceViacaoMgt.criarSistViarioUrbano(sistema);
	}
	
	protected void alterarSistViarioUrbano(SistViarioUrbano sistema){
		interfaceViacaoMgt.alterarSistViarioUrbano(sistema);
	}
	
	protected SistViarioUrbano montarSistViarioUrbano(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		SistViarioUrbano sistema;

		//Operação de Criação
		if (request.getParameter("viacaoID")==null){
			sistema = new SistViarioUrbano();
		}
		
		//Senão precisa buscar
		else{
			sistema = interfaceViacaoMgt.buscarSistViarioUrbano();
		}

		sistema.setEmpresaViaria(interfaceViacaoMgt.buscarEmpresaViaria());
		sistema.setNomeCidade(request.getParameter("nomeCidade"));
		
		return sistema;
	}	

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao");
			
		// quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
		if (operacao == null)
			return buscarSistViarioUrbano();
		
		if (operacao.equals("criar")){
			criarSistViarioUrbano(montarSistViarioUrbano(request));
		}
		else if (operacao.equals("alterar")){
			alterarSistViarioUrbano(montarSistViarioUrbano(request));
		}

		return buscarSistViarioUrbano();

	}

	public IViacaoMgt getInterfaceViacaoMgt() {
		return interfaceViacaoMgt;
	}

	public void setInterfaceViacaoMgt(IViacaoMgt interfaceViacaoMgt) {
		this.interfaceViacaoMgt = interfaceViacaoMgt;
	}
	
}
