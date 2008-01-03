package lps.bet.basico.web.autenticacao;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public aspect Autenticacao {

	AutenticacaoDAO autenticacaoDAO;
	
	public AutenticacaoDAO getAutenticacaoDAO() {
		return autenticacaoDAO;
	}

	public void setAutenticacaoDAO(AutenticacaoDAO autenticacaoDAO) {
		this.autenticacaoDAO = autenticacaoDAO;
	}

	pointcut acesso(): execution(* lps.bet..*.handleRequestInternal(..)) && !execution(* lps.bet.basico.web.autenticacao.GerenciaLogin.handleRequestInternal(..));
	
	before() : acesso (){
		Object[] args = thisJoinPoint.getArgs();
		if (args.length > 0){
			HttpServletRequest request = (HttpServletRequest) args[0];
			HttpServletResponse response = (HttpServletResponse) args[1];
			HttpSession sessao = request.getSession(false);
			
			if (sessao == null){
				//Redirecionar para a p�gina de login
				try {
					response.sendRedirect("login.html");	
				} catch (IOException e) {
					System.err.println(e);
				}				
			}
			else {
				String login = (String) sessao.getAttribute("login");	
				
				//Verificar se n�o est� autenticado ou se sess�o expirou:
				if (!autenticacaoDAO.estaAutenticado(login) || autenticacaoDAO.estaExpirada(request)){
					
					//Redirecionar para a p�gina de login
					try {
						response.sendRedirect("login.html");						
					} catch (IOException e) {
						System.err.println(e);
					}
				}
				//Atualizar hora de novo uso na aplica��o
				else{
					autenticacaoDAO.atualizarSessao(request);
				}
			}
		}	
	}
}
