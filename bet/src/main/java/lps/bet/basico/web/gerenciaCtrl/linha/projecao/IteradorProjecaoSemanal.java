package lps.bet.basico.web.gerenciaCtrl.linha.projecao;

import java.util.Calendar;

public class IteradorProjecaoSemanal extends IteradorProjecao{
	public IteradorProjecaoSemanal(String dtInicio, String dtFim, String hora){
		super(dtInicio, dtFim, hora);
	}

	public Object next() {
		Calendar data = (Calendar)dtAtual.clone(); 
		dtAtual.add(Calendar.WEEK_OF_MONTH, +1);
		return data;
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}
