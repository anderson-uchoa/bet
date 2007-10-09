package lps.bet.basico.leitoraCartao;

import java.util.List;

import lps.bet.basico.controlValidadorOnibus.ITratarCartao;

public class LeitoraCartao implements ILeitora{
	
	private ITratarCartao interfaceTratarCartao;
    
    public LeitoraCartao(){
    }
    
    public ITratarCartao getInterfaceTratarCartao() {
		return interfaceTratarCartao;
	}

	public void setInterfaceTratarCartao(ITratarCartao interfaceTratarCartao) {
		this.interfaceTratarCartao = interfaceTratarCartao;
	}

	public void passarCartao(String dados){
        interfaceTratarCartao.tratarCartao(dados);
    }
}
