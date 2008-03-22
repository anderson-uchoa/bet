package lps.bet.basico.web.gerenciaCtrl.linha;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.tiposDados.Validador;
import lps.bet.basico.web.ControladorBet;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaValidador extends ControladorBet{
	
	ILinhaMgt interfaceLinhaMgt;

	protected void criarValidador(Validador validador){
		interfaceLinhaMgt.criarValidador(validador);
	}
	
	protected ModelAndView buscarValidadores(){
		List validadores = interfaceLinhaMgt.buscarValidadores();

		ModelAndView mav = new ModelAndView("gerenciaValidador");
		mav.addObject("validadores", validadores);
		return mav;
	}
	
	protected ModelAndView buscarValidador(int validadorID){
		Validador validador = interfaceLinhaMgt.buscarValidador(validadorID);
		ModelAndView mav = new ModelAndView("gerenciaValidador");
		if (validador == null){
			mav.addObject("mensagem", "Validador não encontrado.");	
		}
		else{
			List validadores = new ArrayList();
			validadores.add(validador);			
			mav.addObject("validadores", validadores);			
		}
		return mav;
	}
	
	protected void alterarValidador(Validador validador){
		interfaceLinhaMgt.alterarValidador(validador);
	}

	protected Validador montarValidador(HttpServletRequest request){
		String operacao = request.getParameter("operacao");
		Validador validador;
		
		//Operação de Criação
		if (request.getParameter("validadorID") == null){
			validador = new Validador();
			validador.setEmCorrida(false);
			validador.setEmUso(false);
		}
		//Senão precisa buscar
		else {
			validador = interfaceLinhaMgt.buscarValidador(Integer.parseInt(request.getParameter("validadorID")));
		}	
					
		return validador;
	}
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String operacao = request.getParameter("operacao");
		
		
		// quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
		if (operacao == null)
			return buscarValidadores();
		
		if (operacao.equals("criar")){
			criarValidador(montarValidador(request));
		}
		else if (operacao.equals("alterar")){
			alterarValidador(montarValidador(request));
		}

		//Código do if a ser tirado posteriormente (não existe remover no Tarifa):
		if (operacao.equals("remover")){
			String[] validadoresIDs = request.getParameterValues("chkValidadorID");
			
			for (int i = 0; i < validadoresIDs.length; i++) {
				int validadorID = Integer.parseInt(validadoresIDs[i]);
				interfaceLinhaMgt.removerValidador(validadorID);
			}
		}
		
		//Mostrando um funcionário ou todos, dependendo da operacao requisitada
		if (operacao.equals("buscar")){
			return buscarValidador(Integer.parseInt(request.getParameter("validadorID")));			
		}
		else {
			return buscarValidadores();
		}		
	}
	
	public ILinhaMgt getInterfaceLinhaMgt() {
		return interfaceLinhaMgt;
	}

	public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
		this.interfaceLinhaMgt = interfaceLinhaMgt;
	}

}
