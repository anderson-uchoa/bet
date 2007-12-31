package lps.bet.basico.cartaoMgr;

import java.util.Calendar;
import java.util.List;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Pagamento;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PagamentoDAO extends HibernateDaoSupport{

	public void salvarPagamento(Pagamento pagamento){
		getHibernateTemplate().saveOrUpdate(pagamento);
	}
	
	public int criarPagamento(Pagamento pagamento){
		salvarPagamento(pagamento);
		return pagamento.getPgtoID();
	}
	
	public void alterarPagamento(Pagamento pagamento){
		salvarPagamento(pagamento);
	}
	
	public List buscarPagamentos(){
		return getHibernateTemplate().loadAll(Pagamento.class);
	}

	public Pagamento buscarPagamento(int pgtoID){
		return (Pagamento) getHibernateTemplate().get(Pagamento.class, new Integer(pgtoID));
	}

	public void removerPagamento(Pagamento pagamento){
		getHibernateTemplate().delete(pagamento);
	}
	
	public void registrarPagamento(Cartao cartao, float valor){
		Pagamento pagamento = new Pagamento();
		pagamento.setCartao(cartao);
		pagamento.setDtPgto(Calendar.getInstance());
		pagamento.setValorPgto(valor);
		salvarPagamento(pagamento);
	}
	
	public void removerPagamento(int pgtoID){
		Pagamento pagamento = buscarPagamento(pgtoID);
		removerPagamento(pagamento);
	}
}
