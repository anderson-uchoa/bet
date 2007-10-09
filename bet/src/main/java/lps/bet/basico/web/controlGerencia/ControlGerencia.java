package lps.bet.basico.web.controlGerencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ControlGerencia extends MultiActionController{

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
}
