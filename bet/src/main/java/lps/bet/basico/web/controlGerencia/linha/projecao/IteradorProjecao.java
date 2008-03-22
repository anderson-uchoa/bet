package lps.bet.basico.web.controlGerencia.linha.projecao;

import java.util.Calendar;
import java.util.Iterator;

import lps.bet.basico.web.gerenciaCtrl.UtilsGerencia;

public abstract class IteradorProjecao implements Iterator{
	
	protected int WEEK_IN_MILLISECONDS = 604800000;
	protected int DAY_IN_MILLISECONDS = 86400000;
	
	Calendar dtAtual;
	Calendar dtFim;
	
	protected IteradorProjecao(String strDtInicio, String strDtFim, String strHora){
		dtAtual = UtilsGerencia.calendarFromStrings(strDtInicio, strHora);
		dtFim = UtilsGerencia.calendarFromStrings(strDtFim, strHora);
	}
	
	public boolean hasNext() {
		return !dtAtual.after(dtFim);
	}
	
}
