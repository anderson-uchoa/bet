package lps.bet.basico.web.gerenciaCtrl;

import java.util.Calendar;

public class UtilsGerencia {
	
	public static Calendar calendarFromString(String data){
		
		Calendar dataCompleta = Calendar.getInstance();
		
		String[] partesDDMMAAAA = data.split("/");
		
		int dia = Integer.parseInt(partesDDMMAAAA[0]);
		int mes = Integer.parseInt(partesDDMMAAAA[1]);
		int ano = Integer.parseInt(partesDDMMAAAA[2]);
		
		dataCompleta.set(Calendar.DAY_OF_MONTH, dia);
		dataCompleta.set(Calendar.MONTH,  mes-1);
		dataCompleta.set(Calendar.YEAR, ano);
		
		return dataCompleta;
	}
	
	public static Calendar calendarFromStrings(String data, String hora){
		String[] partesHHMM = hora.split(":");
		
		int hh = Integer.parseInt(partesHHMM[0]);
		int mm = Integer.parseInt(partesHHMM[1]); 

		Calendar dataCompleta = Calendar.getInstance();
		
		dataCompleta.set(Calendar.HOUR_OF_DAY, hh);
		dataCompleta.set(Calendar.MINUTE,  mm);
		dataCompleta.set(Calendar.SECOND, 0);
		dataCompleta.set(Calendar.MILLISECOND, 0);

		String[] partesDDMMAAAA = data.split("/");
		
		int dia = Integer.parseInt(partesDDMMAAAA[0]);
		int mes = Integer.parseInt(partesDDMMAAAA[1]);
		int ano = Integer.parseInt(partesDDMMAAAA[2]);
		
		dataCompleta.set(Calendar.DAY_OF_MONTH, dia);
		dataCompleta.set(Calendar.MONTH,  mes-1);
		dataCompleta.set(Calendar.YEAR, ano);
		
		return dataCompleta;
		
		
		
	}
	

}
