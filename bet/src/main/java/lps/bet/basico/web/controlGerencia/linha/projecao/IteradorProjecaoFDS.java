package lps.bet.basico.web.controlGerencia.linha.projecao;

import java.util.Calendar;

public class IteradorProjecaoFDS extends IteradorProjecao{

	public IteradorProjecaoFDS(String dtInicio, String dtFim, String hora){
		super(dtInicio, dtFim, hora);
	}


	public Object next() {
		boolean achou = false;
		Calendar data = null;

		while (!((achou) || (dtAtual.after(dtFim)))){
			if ((dtAtual.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) || (dtAtual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
				data = (Calendar) dtAtual.clone();
				achou = true;
			}
			dtAtual.add(Calendar.DAY_OF_MONTH, +1);
		}
		return data;
	}

	public void remove() {
		// TODO Auto-generated method stub

	}

}
