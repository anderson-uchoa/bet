package lps.bet.basico.web.acessoBasico;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.cartaoMgr.ICartaoMgt;
import lps.bet.basico.passageiroMgr.IPassageiroMgt;
import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.web.ControladorBet;

import org.springframework.web.servlet.ModelAndView;

public class AcessoBasico extends ControladorBet {

	ICartaoMgt interfaceCartaoMgt;
	IPassageiroMgt interfacePassageiroMgt;
	
	SimpleDateFormat sdf;
	
	public AcessoBasico(){
		sdf = new SimpleDateFormat();
		sdf.applyPattern("dd/MM/yyyy");
	}
	
	protected ModelAndView buscarCartoesPorPassageiro(long cpf){
		Collection cartoes = interfacePassageiroMgt.buscarCartoesPorPassageiro(cpf);
		ModelAndView mav = new ModelAndView("acessoBasico");
		
		if (cartoes == null){
			mav.addObject("mensagemSemCartao", "Passageiro não possui cartão.");	
		}		
		else{
			Calendar data = Calendar.getInstance();
			mav.addObject("cartoes", cartoes);
			mav.addObject("sdf", sdf);
			mav.addObject("data",data);
		}
		return mav;
	}

	protected ModelAndView buscarCartoes(){
		List cartoes = interfaceCartaoMgt.buscarCartoes();

		ModelAndView mav = new ModelAndView("acessoBasico");
		mav.addObject("cartoes", cartoes);
		mav.addObject("sdf", sdf);
		return mav;
	}
	
	protected ModelAndView buscarCartao(int cartaoID){
		Cartao cartao = interfaceCartaoMgt.buscarCartao(cartaoID);

		ModelAndView mav = new ModelAndView("acessoBasico");
		mav.addObject("cartao", cartao);
		mav.addObject("sdf", sdf);
		return mav;
	}
	
	protected ModelAndView buscarPassageiro(long cpf){
		Passageiro passageiro = interfacePassageiroMgt.buscarPassageiro(cpf);
		ModelAndView mav = new ModelAndView("acessoBasico");
		
		if (passageiro == null){
			mav.addObject("mensagem", "Passageiro não encontrado.");
		}
		else {
			mav.addObject("passageiro", passageiro);
			mav.addObject("sdf", sdf);
			mav.addObject("acessarPassageiro", "Dados do Passageiro");				
		}			
	
		Collection cartoes = interfacePassageiroMgt.buscarCartoesPorPassageiro(cpf);
		
		if (cartoes == null){
			mav.addObject("mensagemSemCartao", "Passageiro não possui cartão.");	
		}		
		else{
			Calendar data = Calendar.getInstance();
			mav.addObject("cartoes", cartoes);
			mav.addObject("data",data);
		}
		return mav;
	}
	
	protected ModelAndView buscarCartaoPorID(int cartaoID, long cpf){
		Cartao cartaoBuscado = interfaceCartaoMgt.buscarCartao(cartaoID);
		Collection cartoes = interfacePassageiroMgt.buscarCartoesPorPassageiro(cpf);
		ModelAndView mav = new ModelAndView("acessoBasico");
		
		for (Iterator iterator = cartoes.iterator(); iterator.hasNext();) {
			Cartao cartao = (Cartao) iterator.next();
			if (cartao == cartaoBuscado){
				mav.addObject("cartao", cartao);
				mav.addObject("sdf", sdf);
				return mav;
			}
		}
		mav.addObject("mensagemCartao", "Cartão não pertence ao passageiro.");
		return mav;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String operacao = request.getParameter("operacao");
		
		if (operacao == null){
			return buscarCartoes();
		}
		
		if (operacao.equals("acessarPassageiro")){
			return buscarPassageiro(Long.parseLong(request.getParameter("cpf").trim()));
		}

		//Mostrando um cartão ou todos, dependendo da operacao requisitada
		if (operacao.equals("buscar")){
			return buscarCartaoPorID(Integer.parseInt(request.getParameter("cartaoID").trim()), Long.parseLong(request.getParameter("cpf").trim()));			
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

	public IPassageiroMgt getInterfacePassageiroMgt() {
		return interfacePassageiroMgt;
	}

	public void setInterfacePassageiroMgt(IPassageiroMgt interfacePassageiroMgt) {
		this.interfacePassageiroMgt = interfacePassageiroMgt;
	}
	
}
