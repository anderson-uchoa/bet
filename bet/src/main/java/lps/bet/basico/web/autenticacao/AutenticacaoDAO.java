package lps.bet.basico.web.autenticacao;

import java.util.Calendar;
import java.util.List;
import java.util.WeakHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lps.bet.basico.tiposDados.Usuario;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AutenticacaoDAO extends HibernateDaoSupport implements IAutenticacao{	
	
	private WeakHashMap<String, String> senhas;
	
	//Tempo de Sessão (sem expirar)
	private long tempoSessao;
	
	public AutenticacaoDAO(){
		senhas = new WeakHashMap<String, String>();
	}
	
	public void doLogin(HttpServletRequest request, String login, String senha) throws LoginException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		List usuarios = getHibernateTemplate().findByCriteria(criteria);
		
		if (usuarios.isEmpty())
			throw new LoginException("Usuário não cadastrado");
	
		Usuario usuario = (Usuario) usuarios.get(0); 
		if (!usuario.getSenha().equals(senha))
			throw new LoginException("Senha incorreta");
		
		else{
			senhas.put(login, senha);
			criarSessao(request);
		}
	}

	public void doLogout(HttpServletRequest request) {
		HttpSession sessao = request.getSession(false);
		String login = (String) sessao.getAttribute("login");
		if (senhas.containsKey(login)){
			senhas.remove(login);
			sessao.invalidate();
		}	
	}
	
	protected void criarSessao(HttpServletRequest request){
		HttpSession sessao = request.getSession(true);
		String login = request.getParameter("login");
		sessao.setAttribute("login", login);
		Calendar hora = Calendar.getInstance();
		sessao.setAttribute("hora", hora);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		List usuarios = getHibernateTemplate().findByCriteria(criteria);
		
		Usuario usuario = (Usuario) usuarios.get(0);
		sessao.setAttribute("nivel", usuario.getNivelAcesso());
	}
	
	public void atualizarSessao(HttpServletRequest request){
		HttpSession sessao = request.getSession(false);
		Calendar novaHora = Calendar.getInstance();
		sessao.setAttribute("hora", novaHora);
	}

	public boolean estaAutenticado(String login) {
		return senhas.containsKey(login);
	}
	
	public boolean estaExpirada(HttpServletRequest request){
		HttpSession sessao = request.getSession(false);
		Calendar hora = (Calendar) sessao.getAttribute("hora");
		long horaEmSegundos = hora.getTimeInMillis()/1000;		
		long horaAtual = Calendar.getInstance().getTimeInMillis()/1000;
		
		if (horaAtual - horaEmSegundos <= tempoSessao){
			return false;
		}
		else {
			doLogout(request);
			return true;
		}
	}

	public long getTempoSessao() {
		return tempoSessao;
	}

	public void setTempoSessao(long tempoSessao) {
		this.tempoSessao = tempoSessao;
	}	
}
