package lps.bet.basico.web.autenticacao;


public interface IAutenticacao {
	public boolean estaAutenticado(String login);
	public void doLogin(String login, String senha) throws LoginException;
	public void doLogout(String login);
}