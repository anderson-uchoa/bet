package lps.bet.basico.web.acessoBasico;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.autenticacao.IAutenticacao;
import lps.bet.basico.autenticacao.LoginException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class GerenciaLogin extends MultiActionController{

	IAutenticacao interfaceAutenticacao;
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ModelAndView mav = new ModelAndView("gerenciaLogin");
		
		if (login == null) return mav;
		
		try {
			interfaceAutenticacao.doLogin(login, senha);
			mav.setViewName("controlGerencia");
		} catch (LoginException e) {
			mav.addObject("excecao", e);
			mav.addObject("login", login);
		}
		
		return mav;
	}

	public IAutenticacao getInterfaceAutenticacao() {
		return interfaceAutenticacao;
	}

	public void setInterfaceAutenticacao(IAutenticacao interfaceAutenticacao) {
		this.interfaceAutenticacao = interfaceAutenticacao;
	}

}
