package lps.bet.basico.funcionarioMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Funcionario;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FuncionarioDAO extends HibernateDaoSupport{

	public void salvarFuncionario(Funcionario funcionario) {
		getHibernateTemplate().saveOrUpdate(funcionario);
    }

	public Funcionario buscarFuncionario(int usuarioID){
		return (Funcionario) getHibernateTemplate().get(Funcionario.class, new Integer(usuarioID));
	}
	
	public List buscarFuncionarios(){
		return getHibernateTemplate().loadAll(Funcionario.class);
	}
		
	public void alterarFuncionario(Funcionario funcionario) {
		salvarFuncionario(funcionario);		
	}

	public void criarFuncionario(Funcionario funcionario) {
		salvarFuncionario(funcionario);		
	}

	public void removerFuncionario(Funcionario funcionario){
		getHibernateTemplate().delete(funcionario);
	}

	public void removerFuncionario(int usuarioID) {
		Funcionario funcionario = buscarFuncionario(usuarioID);
		removerFuncionario(funcionario);
	}
		
}
