package lps.bet.basico.web.autenticacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


public class GerenciaLogin extends MultiActionController{

	IAutenticacao interfaceAutenticacao;
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao") != null ? request.getParameter("operacao") : "login";
		
		if (operacao.equals("login") && request.getParameter("login") == null)
			return new ModelAndView("gerenciaLogin");
		
		return (operacao.equals("login")) ? doLogin(request) : doLogout(request);
	}
	
	private ModelAndView doLogin(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("gerenciaLogin");

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		try {
			interfaceAutenticacao.doLogin(request, login, senha);
			mav.setViewName("controlGerencia");
		} catch (LoginException e) {
			mav.addObject("excecao", e);
		}
		
		mav.addObject("login", login);
		return mav;
	}
	
	private ModelAndView doLogout (HttpServletRequest request){
		interfaceAutenticacao.doLogout(request);
		
		return new ModelAndView("gerenciaLogin");
	}

	public IAutenticacao getInterfaceAutenticacao() {
		return interfaceAutenticacao;
	}

	public void setInterfaceAutenticacao(IAutenticacao interfaceAutenticacao) {
		this.interfaceAutenticacao = interfaceAutenticacao;
	}

}
