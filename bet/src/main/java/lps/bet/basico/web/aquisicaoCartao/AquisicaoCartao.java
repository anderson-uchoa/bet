package lps.bet.basico.web.aquisicaoCartao;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.cartaoMgr.ICartaoMgt;
import lps.bet.basico.passageiroMgr.IPassageiroMgt;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.web.ControladorBet;

import org.springframework.web.servlet.ModelAndView;

public class AquisicaoCartao extends ControladorBet implements ISolicitarCartao{

	ICartaoMgt interfaceCartaoMgt;
	IPassageiroMgt interfacePassageiroMgt;
	
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
	
	protected void criarPassageiro(Passageiro passageiro){
		interfacePassageiroMgt.criarPassageiro(passageiro);
	}

	public boolean solicitarCartao(long cpf) {
		return false;
	}
	
	protected Passageiro montarPassageiro(HttpServletRequest request){
		String operacao = request.getParameter("operacao");
		Passageiro passageiro;
		
		//Operação de Criação
		if (request.getParameter("passageiroID")==null){
			passageiro = new Passageiro();
		}
		//Senão precisa buscar
		else {
			passageiro = interfacePassageiroMgt.buscarPassageiro(Long.parseLong(request.getParameter("cpf").trim()));
		}

		passageiro.setCpf(Long.parseLong(request.getParameter("cpf").trim()));
		passageiro.setNomePassageiro(request.getParameter("nomePassageiro"));
				
		return passageiro;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long cpf = Long.parseLong(request.getParameter("cpf").trim());
		
		Passageiro passageiro = interfacePassageiroMgt.buscarPassageiro(cpf);
		
		if (passageiro == null){			
			ModelAndView mav = new ModelAndView("formPassageiro");
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");
			mav.addObject("forwardView", "formAquisicaoCartao");
			return mav;
		}
		
		else {
			return null; //buscarTiposPermitidos(cpf);
		}
	}
	
	public Collection buscarTiposPermitidos(long cpf) {
		//Passageiro passageiro = interfacePassageiroMgt.buscarPassageiro(cpf);
	//	if (passageiro == null){
	//		criarPassageiro(montarPassageiro(request));
	//	}
		//return interfaceCartaoMgt.buscarTiposPermitidos(passageiro);
		return null;
	}

}
