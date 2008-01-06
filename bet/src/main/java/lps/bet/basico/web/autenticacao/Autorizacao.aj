package lps.bet.basico.web.autenticacao;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.web.ControladorBet;
import lps.bet.basico.web.MenuBet;

import org.springframework.web.servlet.ModelAndView;

public aspect Autorizacao {
	
	private String nomeArquivoMenu;
	
	public String getNomeArquivoMenu(){
		return nomeArquivoMenu;
	}
	
	public void setNomeArquivoMenu(String nomeArquivoMenu){
		this.nomeArquivoMenu = nomeArquivoMenu;
	}

	declare precedence: Autenticacao, Autorizacao;

	pointcut acesso(): execution(* lps.bet..*.handleRequestInternal(..))
					&& !execution(* lps.bet.basico.web.autenticacao.GerenciaLogin.handleRequestInternal(..));
	
	pointcut login(): execution(* lps.bet.basico.web.autenticacao.GerenciaLogin.doLogin(..));
		
	ModelAndView around() : acesso (){
		Object[] args = thisJoinPoint.getArgs();
		
		if (args.length == 0)
			return null;

		Integer nivel = obterNivelAcesso(args);
		ControladorBet controladorBet = (ControladorBet) thisJoinPoint.getTarget();
		
		if (nivel < controladorBet.getNivelMinimoAcesso()){
			HttpServletResponse response = (HttpServletResponse) args[1];
			try {
				response.sendRedirect("acessonegado.htm");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		return adicionaMenu(proceed(), args);
	}
	
	ModelAndView around() : login(){
		return adicionaMenu(proceed(), thisJoinPoint.getArgs());
	}
	
	private Integer obterNivelAcesso(Object[] args){
		HttpServletRequest request = (HttpServletRequest) args[0];		
		Integer nivel = (Integer) request.getSession(false).getAttribute("nivel");
		return nivel;
	}
	
	private ModelAndView adicionaMenu(ModelAndView mav, Object[] args) {
		if (!mav.getModelMap().containsKey("excecao"))			
			mav.addObject("menu", new MenuBet(nomeArquivoMenu, obterNivelAcesso(args)));
		return mav;		
	}
	
}
