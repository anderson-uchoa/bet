package lps.bet.basico.web.controlGerencia.linha;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.tiposDados.Onibus;
import lps.bet.basico.web.ControladorBet;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaOnibus extends ControladorBet{
	
	ILinhaMgt interfaceLinhaMgt;

	protected void criarOnibus(Onibus onibus){
		interfaceLinhaMgt.criarOnibus(onibus);
	}
	
	protected ModelAndView buscarTodosOnibus(){
		List todosOnibus = interfaceLinhaMgt.buscarTodosOnibus();

		ModelAndView mav = new ModelAndView("gerenciaOnibus");
		mav.addObject("todosOnibus", todosOnibus);
		return mav;
	}
	
	protected ModelAndView buscarOnibus(int onibusID){
		Onibus onibus = interfaceLinhaMgt.buscarOnibus(onibusID);
		ModelAndView mav = new ModelAndView("gerenciaOnibus");
		if(onibus == null){
			mav.addObject("mensagem", "Ônibus não encontrado.");	
		}
		else{
			List todosOnibus = new ArrayList();
			todosOnibus.add(onibus);			
			mav.addObject("todosOnibus", todosOnibus);			
		}
		return mav;
	}
	
	protected void alterarOnibus(Onibus onibus){
		interfaceLinhaMgt.alterarOnibus(onibus);
	}

	protected Onibus montarOnibus(HttpServletRequest request){
		String operacao = request.getParameter("operacao");
		Onibus onibus;
		
		//Operação de Criação
		if (request.getParameter("onibusID") == null){
			onibus = new Onibus();			
		}
		//Senão precisa buscar
		else {
			onibus = interfaceLinhaMgt.buscarOnibus(Integer.parseInt(request.getParameter("onibusID")));
		}

		onibus.setEmCorrida(false);
					
		return onibus;
	}
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String operacao = request.getParameter("operacao");
		
		
		// quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
		if (operacao == null)
			return buscarTodosOnibus();
		
		if (operacao.equals("criar")){
			criarOnibus(montarOnibus(request));
		}
		else if (operacao.equals("alterar")){
			alterarOnibus(montarOnibus(request));
		}

		//Código do if a ser tirado posteriormente (não existe remover no Tarifa):
		if (operacao.equals("remover")){
			String[] onibusIDs = request.getParameterValues("chkOnibusID");
			
			for (int i = 0; i < onibusIDs.length; i++) {
				int onibusID = Integer.parseInt(onibusIDs[i]);
				interfaceLinhaMgt.removerOnibus(onibusID);
			}
		}
		
		//Mostrando um funcionário ou todos, dependendo da operacao requisitada
		if (operacao.equals("buscar")){
			return buscarOnibus(Integer.parseInt(request.getParameter("onibusID")));			
		}
		else {
			return buscarTodosOnibus();
		}		
	}
	
	public ILinhaMgt getInterfaceLinhaMgt() {
		return interfaceLinhaMgt;
	}

	public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
		this.interfaceLinhaMgt = interfaceLinhaMgt;
	}

}
