package lps.bet.basico.web.autenticacao;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.IOException;

import lps.bet.basico.web.autenticacao.AutenticacaoDAO;

public aspect Autenticacao {

	AutenticacaoDAO autenticacaoDAO;
	
	public AutenticacaoDAO getAutenticacaoDAO() {
		return autenticacaoDAO;
	}

	public void setAutenticacaoDAO(AutenticacaoDAO autenticacaoDAO) {
		this.autenticacaoDAO = autenticacaoDAO;
	}

	pointcut acesso(): execution(* lps.bet.basico.web..*.handleRequestInternal(..)) && !execution(* lps.bet.basico.web.acessoBasico.GerenciaLogin.handleRequestInternal(..));
	
	before() : acesso (){
		Object[] args = thisJoinPoint.getArgs();
		if (args.length > 0){
			HttpServletRequest request = (HttpServletRequest) args[0];
			HttpServletResponse response = (HttpServletResponse) args[1];
			HttpSession sessao = request.getSession(false);
			System.out.println("--> Sess�o: " + sessao);
			
			if (sessao == null){
				//if (login == null){
				//Redirecionar para a p�gina de login
				try {
					System.out.println("--> Entrou com sessao nula");
					response.sendRedirect("login.html");	
				} catch (IOException e) {
					System.err.println(e);
				}				
				//}
			}
			else {
				System.out.println("--> Sess�o n�o � nula");
				String login = (String) sessao.getAttribute("login");	
				System.out.println("--> Login: " + login);
				//Resource resource = new ClassPathResource("WEB-INF/bet-servlet.xml");
				//BeanFactory factory = new XmlBeanFactory(resource);
				//AutenticacaoDAO autenticacaoDAO = (AutenticacaoDAO) factory.getBean("AutenticacaoDAO");

				System.out.println("--> ESTA AUTENTICADO? " + autenticacaoDAO.estaAutenticado(login));
				System.out.println("--> ESTA EXPIRADA? " + autenticacaoDAO.estaExpirada(request));
				
				//Verificar se n�o est� autenticado ou se sess�o expirou:
				if (!autenticacaoDAO.estaAutenticado(login) || autenticacaoDAO.estaExpirada(request)){
					
					System.out.println("--> Verificou q n�o est� autenticado ou q a sess�o expirou");
					//Redirecionar para a p�gina de login
					try {
						response.sendRedirect("login.html");						
					} catch (IOException e) {
						System.err.println(e);
					}
				}
				//Atualizar hora de novo uso na aplica��o
				else{
					System.out.println("--> Deve ser atualizada a hora de uso da aplicacao");
					autenticacaoDAO.atualizarSessao(request);
				}
			}
		}	
	}
}
