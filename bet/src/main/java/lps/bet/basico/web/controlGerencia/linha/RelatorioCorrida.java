package lps.bet.basico.web.controlGerencia.linha;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.web.ControladorBet;

import org.springframework.web.servlet.ModelAndView;

public class RelatorioCorrida extends ControladorBet{

	ILinhaMgt interfaceLinhaMgt;
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List todosOnibus = interfaceLinhaMgt.buscarTodosOnibus();
		List linhas = interfaceLinhaMgt.buscarLinhas();
		ModelAndView mav = new ModelAndView("formBuscaAvancadaCorrida");
		mav.addObject("todosOnibus", todosOnibus);
		mav.addObject("linhas", linhas);
		return mav;
	}

	public ILinhaMgt getInterfaceLinhaMgt() {
		return interfaceLinhaMgt;
	}

	public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
		this.interfaceLinhaMgt = interfaceLinhaMgt;
	}
	
}
