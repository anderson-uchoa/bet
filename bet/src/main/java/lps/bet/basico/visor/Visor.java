package lps.bet.basico.onibus.visor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Visor implements IVisor{

	List listeners;
	
	public Visor() {
		listeners = new ArrayList();
	}
        
	public void mostrar(String msg){
		disparaNovaMensagem(msg);
		limpar();
	}
	private void limpar(){
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				disparaNovaMensagem("");
			}
		}, 4000);
		
	}
		
	public void addVisorListener(VisorListener listener){
		if (!listeners.contains(listener))
			listeners.add(listener);
	}
	
	public void removeVisorListener(VisorListener listener){
		listeners.remove(listener);
	}
	
	private void disparaNovaMensagem(String msg){
		for (Iterator iterator = listeners.iterator(); iterator.hasNext();) {
			VisorListener listener = (VisorListener) iterator.next();
			VisorEvent visorEvt = new VisorEvent(this);
			visorEvt.setMensagem(msg);
			listener.novaMensagem(visorEvt);			
		}
	}
}
