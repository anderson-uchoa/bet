package lps.bet.basico.web.acessoBasico;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import lps.bet.basico.web.autenticacao.*;

public class GerenciaLogin extends MultiActionController{

	IAutenticacao interfaceAutenticacao;
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao");
		
		if (request.getParameter("login") == null)
			return new ModelAndView("gerenciaLogin");
		
		return (operacao.equals("login")) ? doLogin(request) : doLogout(request);
	}
	
	private ModelAndView doLogin(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("gerenciaLogin");

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		try {
			interfaceAutenticacao.doLogin(login, senha);
			request.getSession().setAttribute("login", login);
			mav.setViewName("controlGerencia");
		} catch (LoginException e) {
			mav.addObject("excecao", e);
		}
		
		mav.addObject("login", login);
		return mav;
	}
	
	private ModelAndView doLogout (HttpServletRequest request){
		interfaceAutenticacao.doLogout(request.getParameter("login"));
		
		return new ModelAndView("gerenciaLogin");
	}

	public IAutenticacao getInterfaceAutenticacao() {
		return interfaceAutenticacao;
	}

	public void setInterfaceAutenticacao(IAutenticacao interfaceAutenticacao) {
		this.interfaceAutenticacao = interfaceAutenticacao;
	}

}
