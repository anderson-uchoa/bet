package lps.bet.variabilidades.web.acessoAdicional;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.web.ControladorBet;
import lps.bet.interfaces.ICartaoMgt;

import org.springframework.web.servlet.ModelAndView;

public class AcessoAdicional extends ControladorBet {
	
	ICartaoMgt interfaceCartaoMgt;

    SimpleDateFormat sdf, hora;

    public AcessoAdicional() {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        hora = new SimpleDateFormat("hh:mm:ss");
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	int cartaoID = Integer.parseInt(request.getParameter("cartaoID"));
    	List viagens = interfaceCartaoMgt.buscarViagensPorCartao(cartaoID);
    	
    	ModelAndView mav = new ModelAndView("acessoAdicional");
    	mav.addObject("viagens", viagens);
    	mav.addObject("cartaoID", cartaoID);
    	mav.addObject("sdf", sdf);
    	mav.addObject("hora", hora);
    	return mav;
    }

	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}    
    
}
