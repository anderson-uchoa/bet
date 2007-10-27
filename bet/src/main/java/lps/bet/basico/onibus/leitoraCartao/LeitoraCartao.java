package lps.bet.basico.onibus.leitoraCartao;

import lps.bet.basico.onibus.controlValidadorOnibus.ITratarCartao;

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
