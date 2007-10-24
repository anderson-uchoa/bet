package lps.bet.basico.web.autenticacao;

import javax.servlet.http.HttpServletRequest;


public interface IAutenticacao {
	
	public boolean estaAutenticado(String login);
	public boolean estaExpirada(HttpServletRequest request);
	
	public void doLogin(HttpServletRequest request, String login, String senha) throws LoginException;
	public void doLogout(String login);
	
	public void atualizarSessao(HttpServletRequest request);
}