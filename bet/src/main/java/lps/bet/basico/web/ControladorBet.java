package lps.bet.basico.web;

import org.springframework.web.servlet.mvc.AbstractController;

public abstract class ControladorBet extends AbstractController{
	protected int nivelMinimoAcesso;

	public int getNivelMinimoAcesso() {
		return nivelMinimoAcesso;
	}

	public void setNivelMinimoAcesso(int nivelMinimoAcesso) {
		this.nivelMinimoAcesso = nivelMinimoAcesso;
	}

//	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
//			HttpServletResponse arg1) throws Exception {
//		return null;
//	}
	
}
