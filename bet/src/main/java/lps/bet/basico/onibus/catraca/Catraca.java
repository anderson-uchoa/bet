package lps.bet.basico.onibus.catraca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lps.bet.basico.onibus.controlValidadorOnibus.IPermitirLeitura;
import lps.bet.basico.onibus.visor.VisorEvent;
import lps.bet.basico.onibus.visor.VisorListener;


public class Catraca implements IGirarCatraca, ILiberarCatraca{

	IPermitirLeitura interfacePermitirLeitura;
	boolean travada;
	
	List listeners;

	public Catraca() {
		travada = true;
		listeners = new ArrayList();
	}
	
	public void girarCatraca(){
		if (!travada){
			interfacePermitirLeitura.permitirLeitura();
			travarCatraca();
		}
	}	
	
	public void liberarCatraca(){
		travada = false;
		disparaCatracaLiberada();
	}	
	
	private void travarCatraca(){
		travada = true;
	}
	
	private void disparaCatracaLiberada(){
		for (Iterator iterator = listeners.iterator(); iterator.hasNext();) {
			CatracaListener listener = (CatracaListener) iterator.next();
			listener.catracaLiberada(new CatracaEvent(this));			
		}
	}

	public void addCatracaListener(CatracaListener listener){
		if (!listeners.contains(listener))
			listeners.add(listener);
	}
	
	public void removeCatracaListener(CatracaListener listener){
		listeners.remove(listener);
	}
	
	public void setInterfacePermitirLeitura(IPermitirLeitura interfacePermitirLeitura){
		this.interfacePermitirLeitura = interfacePermitirLeitura;
	}
	
}
