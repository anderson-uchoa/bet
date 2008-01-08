package lps.bet.variabilidades.web.terminal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.web.ControladorBet;
import lps.bet.variabilidades.terminalMgr.ITerminalMgr;
import lps.bet.variabilidades.tiposDados.Terminal;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaTerminal extends ControladorBet{

	ITerminalMgr interfaceTerminalMgr;
		
	public GerenciaTerminal(){
		
	}

	protected void criarTerminal(Terminal terminal){
		interfaceTerminalMgr.criarTerminal(terminal);
	}

	protected ModelAndView buscarTerminal(){
		List terminais = interfaceTerminalMgr.buscarTerminal();
		
		ModelAndView mav = new ModelAndView("gerenciaTerminal");
		
		mav.addObject("terminais", terminais);
		return mav;
	}
	
	protected ModelAndView buscarTerminal(int terminalID){
		Terminal terminal = interfaceTerminalMgr.buscarTerminal(terminalID);
		ModelAndView mav = new ModelAndView("gerenciaTerminal");

		if (terminal==null){
			mav.addObject("mensagem", "Terminal não encontrado.");
		}
		else{
			List terminais = new ArrayList();
			terminais.add(terminal);			
			mav.addObject("terminais", terminais);
					
		}

		return mav;

	}
	
	protected void alterarTerminal(Terminal terminal){
		interfaceTerminalMgr.alterarTerminal(terminal);
	}

	protected Terminal montarTerminal(HttpServletRequest request){
		String operacao = request.getParameter("operacao");
		Terminal terminal;
		
		//Operação de Criação
		if (request.getParameter("terminalID")==null){
			terminal = new Terminal();
		}
		//Senão precisa buscar
		else {
			terminal = interfaceTerminalMgr.buscarTerminal(Integer.parseInt(request.getParameter("terminalID")));
		}
		
		terminal.setNomeTerminal(request.getParameter("nomeTerminal").trim());
		terminal.setQtdValidadores(Integer.parseInt(request.getParameter("qtdValidadores")));
		
		return terminal;
	}
	
	protected ModelAndView mostrarForm(String terminalID){

		ModelAndView mav = new ModelAndView("formTerminal");
		mav.addObject("terminalID",terminalID);
		
		Terminal terminal = null;
		
		if (terminalID == null){
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");						
			
		}
		else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			terminal = interfaceTerminalMgr.buscarTerminal(Integer.parseInt(terminalID));
			
		}
		mav.addObject("terminal",terminal);		
		return mav;		
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String operacao = request.getParameter("operacao");
		if (request.getServletPath().equals("/gerenciaTerminal.html")){
			// quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
			if (operacao == null)
				return buscarTerminal();

			if (operacao.equals("criar")|| operacao.equals("Criar")){
				criarTerminal(montarTerminal(request));
			}
			else if (operacao.equals("alterar")|| operacao.equals("Alterar")){
				alterarTerminal(montarTerminal(request));
			}

			if (operacao.equals("remover") || operacao.equals("Remover")){
				String[] terminaisIDs = request.getParameterValues("chkTerminalID");
				
				for (int i = 0; i < terminaisIDs.length; i++) {
					int terminalID = Integer.parseInt(terminaisIDs[i]);
					Terminal term = new Terminal();
					term.setTerminalID(terminalID);
					interfaceTerminalMgr.removerTerminal(term);
				}
			}

			//Mostrando um terminal ou todos, dependendo da operacao requisitada
			if (operacao.equals("buscar") || operacao.equals("Buscar")){
				if(request.getParameter("terminalID").equals("") || request.getParameter("terminalID")==null){
					buscarTerminal();
				}
				return buscarTerminal(Integer.parseInt(request.getParameter("terminalID")));			
			}
			else {
				return buscarTerminal();
			}		
		}
		else{
			return mostrarForm(request.getParameter("terminalID"));
		}
	}

	public ITerminalMgr getInterfaceTerminalMgr() {
		return interfaceTerminalMgr;
	}

	public void setInterfaceTerminalMgr(ITerminalMgr interfaceTerminalMgr) {
		this.interfaceTerminalMgr = interfaceTerminalMgr;
	}

		
}
