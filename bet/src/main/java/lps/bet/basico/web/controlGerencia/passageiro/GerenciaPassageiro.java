package lps.bet.basico.web.controlGerencia.passageiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.passageiroMgr.IPassageiroMgt;
import lps.bet.basico.tiposDados.Passageiro;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class GerenciaPassageiro extends MultiActionController {

	IPassageiroMgt interfacePassageiroMgt;

	public IPassageiroMgt getInterfacePassageiroMgt() {
		return interfacePassageiroMgt;
	}

	public void setInterfacePassageiroMgt(IPassageiroMgt interfacePassageiroMgt) {
		this.interfacePassageiroMgt = interfacePassageiroMgt;
	}

	protected void criarPassageiro(Passageiro passageiro){
		interfacePassageiroMgt.criarPassageiro(passageiro);
	}
	
	protected Passageiro montarPassageiro(HttpServletRequest request){

		Passageiro passageiro;
		
		//Operação de Criação
		if (request.getParameter("passageiroID")==null){
			passageiro = new Passageiro();
		}
		//Senão precisa buscar
		else {
			passageiro = interfacePassageiroMgt.buscarPassageiro(Long.parseLong(request.getParameter("cpf").trim()));
		}
		String strCPF = request.getParameter("cpf");
		passageiro.setCpf(Long.parseLong(strCPF.trim()));
		passageiro.setNomePassageiro(request.getParameter("nomePassageiro").trim());
				
		return passageiro;
	}
	
	protected ModelAndView buscarPassageiros(){
		List passageiros = interfacePassageiroMgt.buscarPassageiros();

		ModelAndView mav = new ModelAndView("gerenciaPassageiro");
		mav.addObject("passageiros", passageiros);
		return mav;
	}
	
	protected ModelAndView buscarPassageiro(long cpf){
		Passageiro passageiro = interfacePassageiroMgt.buscarPassageiro(cpf);
		ModelAndView mav = new ModelAndView("gerenciaPassageiro");
		if (passageiro == null){
			mav.addObject("mensagem", "Passageiro não encontrado.");
		}
		else{
			Collection passageiros = new ArrayList();		
			passageiros.add(passageiro);
			mav.addObject("passageiros", passageiros);			
		}
		return mav;
	}
 
	protected ModelAndView mostrarForm(String passageiroID, String forwardView){
		String view = "formPassageiro";
		
		if (forwardView != null){
			view = forwardView;
		}
	
		ModelAndView mav = new ModelAndView(view);
		mav.addObject("passageiroID",passageiroID);
		
		Passageiro passageiro = null;
		
		if (passageiroID == null){
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");						
		}
		else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			passageiro = interfacePassageiroMgt.buscarPassageiroPorID(Integer.parseInt(passageiroID));
		}
		mav.addObject("passageiro",passageiro);		
		return mav;		
	}

	
	protected void alterarPassageiro(Passageiro passageiro){
		interfacePassageiroMgt.alterarPassageiro(passageiro);
	}

	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao");
		
		if (request.getServletPath().equals("/gerenciaPassageiro.html")){
			//Quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
			if (operacao == null)
				return buscarPassageiros();
			
			if (operacao.equals("criar")){
				criarPassageiro(montarPassageiro(request));
			}
			
			else if (operacao.equals("alterar")){
				alterarPassageiro(montarPassageiro(request));
			}
			
			if (operacao.equals("remover")){
				String[] passageirosIDs = request.getParameterValues("chkPassageiroID");
				for (int i = 0; i < passageirosIDs.length; i++) {
					int passageiroID = Integer.parseInt(passageirosIDs[i]);
					interfacePassageiroMgt.removerPassageiroPorID(passageiroID);
				}
			}
			
			// mostrando um passageiro ou todos, dependendo da operacao requisitada
			if (operacao.equals("buscar")){
				return buscarPassageiro(Long.parseLong(request.getParameter("cpf").trim()));			
			}
			else {
				return buscarPassageiros();
			}			
		}
		else {
			return mostrarForm(request.getParameter("passageiroID"),request.getParameter("forwardView"));
		}
	}

}
