package lps.bet.basico.web.controlGerencia.linha.projecao;

import java.util.Calendar;

public class IteradorProjecaoDiaria extends IteradorProjecao{

	public IteradorProjecaoDiaria(String dtInicio, String dtFim, String hora){
		super(dtInicio, dtFim, hora);
	}
	
	public Object next() {
		Calendar data = (Calendar)dtAtual.clone(); 
		dtAtual.add(Calendar.DAY_OF_MONTH, +1);
		return data;
	}

	public void remove(){}
	
}
