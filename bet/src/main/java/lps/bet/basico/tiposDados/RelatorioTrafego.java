package lps.bet.basico.tiposDados;

import java.util.ArrayList;
import java.util.List;

public class RelatorioTrafego {

	private List<ItemRelatorioTrafego> itens;
	
	public void adicionarItemRelatorioTrafego(ItemRelatorioTrafego item){
		itens.add(item);
	}
	
	public List<ItemRelatorioTrafego> getItens(){
		return itens;
	}

	public RelatorioTrafego() {
		itens = new ArrayList<ItemRelatorioTrafego>();
	}
		
}
