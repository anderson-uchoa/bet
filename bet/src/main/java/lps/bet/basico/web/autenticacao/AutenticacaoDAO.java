package lps.bet.basico.web.autenticacao;

import java.util.List;
import java.util.WeakHashMap;

import lps.bet.basico.tiposDados.Usuario;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AutenticacaoDAO extends HibernateDaoSupport implements IAutenticacao{	
	
	private WeakHashMap<String, String> senhas;
	
	public AutenticacaoDAO(){
		senhas = new WeakHashMap<String, String>();
	}
	
	public void doLogin(String login, String senha) throws LoginException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		List usuarios = getHibernateTemplate().findByCriteria(criteria);
		
		if (usuarios.isEmpty())
			throw new LoginException("Usuário não cadastrado");
	
		Usuario usuario = (Usuario) usuarios.get(0); 
		if (!usuario.getSenha().equals(senha))
			throw new LoginException("Senha incorreta");
		
		senhas.put(login, senha);
	}

	public void doLogout(String login) {
		if (senhas.containsKey(login))
			senhas.remove(login);
	}

	public boolean estaAutenticado(String login) {
		return senhas.containsKey(login);
	}
	
}
