
package lps.bet.basico.tiposDados ;

import java.util.Collection;

public class Passageiro extends Usuario{

	private String nomePassageiro;
	private int passageiroID;
	private long cpf;
	private Collection cartoes;


	public Passageiro(){
	}

	public String getNomePassageiro () {
		return nomePassageiro;
	} 
	public void setNomePassageiro (String nomePassageiro) {
		this.nomePassageiro = nomePassageiro;
	}
	public int getPassageiroID () {
		return passageiroID;
	} 
	public void setPassageiroID (int passageiroID) {
		this.passageiroID = passageiroID;
	}
	public Collection getCartoes() {
		return this.cartoes;
	}
	public void setCartoes(Collection cartoes) {
		this.cartoes = cartoes;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

}