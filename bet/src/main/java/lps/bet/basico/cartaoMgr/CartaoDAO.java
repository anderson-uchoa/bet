package lps.bet.basico.cartaoMgr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.TipoPassageiro;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CartaoDAO extends HibernateDaoSupport{
	
	public void salvarCartao(Cartao cartao) {
		getHibernateTemplate().saveOrUpdate(cartao);
    }

	public void criarCartao(Cartao cartao){
		salvarCartao(cartao);
	}
	
	public void alterarCartao(Cartao cartao){
		salvarCartao(cartao);
	}
	
	public Cartao buscarCartao(int cartaoID){
		return (Cartao) getHibernateTemplate().get(Cartao.class, new Integer(cartaoID));
	}
	
	public List buscarCartoes(){
		return getHibernateTemplate().loadAll(Cartao.class);
	}
		
	public void removerCartao(Cartao cartao){
		getHibernateTemplate().delete(cartao);
	}
	
	public boolean validarCartao(Cartao cartao){
		
		Calendar hoje = Calendar.getInstance();
		if (cartao == null)
			return false;
		
		return !cartao.getDtValidade().before(hoje);  
			
	}
	
	public float buscarSaldo(int cartaoID){
		Cartao cartao = buscarCartao(cartaoID);
		return cartao.getSaldo();
	}
	
	public boolean podeDebitar(int cartaoID, float valor){
		return ((buscarSaldo(cartaoID)-valor)>=0);
	}
	
	private void atualizarSaldo(int cartaoID, float valor){
		Cartao cartao = buscarCartao(cartaoID);
		cartao.setSaldo(cartao.getSaldo() + valor);
		salvarCartao(cartao);		
	}
	
	public void debitarPassagem(int cartaoID, float valor){
		atualizarSaldo(cartaoID, valor * -1);
	}
	
	public void carregarCartao(int cartaoID, float valor){
		atualizarSaldo(cartaoID, valor);
	}
	
	public void removerCartao(int cartaoID){
		Cartao cartao = buscarCartao(cartaoID);
		removerCartao(cartao);
	}
	
	public List buscarTiposDosCartoes(Collection cartoes){
		List tipos = new ArrayList();
		for (Iterator iterator = cartoes.iterator(); iterator.hasNext();) {
			Cartao cartao = (Cartao) iterator.next();
			TipoPassageiro tipo = cartao.getTipoPassageiro();
			tipos.add(tipo);
		}
		return tipos; 
	}
	
}
