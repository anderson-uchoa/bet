package lps.bet.basico.web.cargaCartao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.cartaoMgr.ICartaoMgt;
import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.web.ControladorBet;

import org.springframework.web.servlet.ModelAndView;

public class CargaCartao extends ControladorBet implements ISolicitarCarga{
	
	ICartaoMgt interfaceCartaoMgt;
	//IContabilidade interfaceContabilidade;
	
	SimpleDateFormat sdf;
	
	public CargaCartao(){
		sdf = new SimpleDateFormat();
		sdf.applyPattern("dd/MM/yyyy");
	}
	
	public void solicitarCarga(int cartaoID, float valor) {
		interfaceCartaoMgt.carregarCartao(cartaoID, valor);		
	}
	
	protected ModelAndView buscarCartoes(){
		List cartoes = interfaceCartaoMgt.buscarCartoes();
		
		Calendar data = Calendar.getInstance();

		ModelAndView mav = new ModelAndView("cargaCartao");
		mav.addObject("cartoes", cartoes);
		mav.addObject("sdf", sdf);
		mav.addObject("data",data);
		return mav;
	}
	
	protected ModelAndView buscarCartao(int cartaoID){
		Cartao cartao = interfaceCartaoMgt.buscarCartao(cartaoID);
		ModelAndView mav = new ModelAndView("cargaCartao");
		if (cartao == null){
			mav.addObject("mensagem", "Cartão não encontrado.");
		}
		else{
			List cartoes = new ArrayList();
			cartoes.add(cartao);
			Calendar data = Calendar.getInstance();
			mav.addObject("cartoes", cartoes);
			mav.addObject("sdf", sdf);
			mav.addObject("data",data);
		}
		return mav;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String operacao = request.getParameter("operacao");
		Cartao cartao;
		
		if (operacao == null){
			return buscarCartoes();
		}
		
		if (operacao.equals("carregar")){

			int cartaoID = Integer.parseInt(request.getParameter("cartaoID"));

			String strValor = request.getParameter("valor");
			float valor = strValor.trim().matches("[0-9]*\\.?[0-9]+") ? Float.parseFloat(strValor) : 0;

			solicitarCarga(cartaoID, valor);

			//ModelAndView mav = new ModelAndView("cargaCartao");
			//return mav;
		}

		//Mostrando um cartão ou todos, dependendo da operacao requisitada
		if (operacao.equals("buscar")){
			return buscarCartao(Integer.parseInt(request.getParameter("cartaoID")));			
		}
		else {
			return buscarCartoes();
		}
	}

	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}
	
}
