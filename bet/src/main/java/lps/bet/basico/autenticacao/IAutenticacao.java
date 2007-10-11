package lps.bet.basico.autenticacao;

import lps.bet.basico.tiposDados.Usuario;

public interface IAutenticacao {
	public boolean estaAutenticado(String login);
	public void doLogin(String login, String senha) throws LoginException;
	public void doLogout(String login);
}