package lps.bet.basico.relatorioMgr;

import java.util.Calendar;

import lps.bet.basico.tiposDados.RelatorioTrafego;

public interface IRelatorioMgt {

	public RelatorioTrafego construirRelatorioTrafego(Calendar dataRelatorio);
	
}
