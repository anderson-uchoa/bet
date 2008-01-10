package lps.bet.variabilidades.web.terminal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.tiposDados.Validador;
import lps.bet.basico.web.ControladorBet;
import lps.bet.variabilidades.terminalMgr.ITerminalMgt;
import lps.bet.variabilidades.tiposDados.Terminal;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaTerminal extends ControladorBet{

	ITerminalMgt interfaceTerminalMgt;
	ILinhaMgt interfaceLinhaMgt;
		
	public GerenciaTerminal(){
		
	}

	protected void criarTerminal(Terminal terminal){
		interfaceTerminalMgt.criarTerminal(terminal);
	}

	protected ModelAndView buscarTerminal(){
		List terminais = interfaceTerminalMgt.buscarTerminal();
		
		ModelAndView mav = new ModelAndView("gerenciaTerminal");		
		mav.addObject("terminais", terminais);
		return mav;
	}
	
	protected ModelAndView buscarTerminal(int terminalID){
		Terminal terminal = interfaceTerminalMgt.buscarTerminal(terminalID);
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
		interfaceTerminalMgt.alterarTerminal(terminal);
	}

	protected void associarValidadorAoTerminal(HttpServletRequest request){
		Terminal terminal = interfaceTerminalMgt.buscarTerminal(Integer.parseInt(request.getParameter("terminalID")));
		Validador validador = interfaceLinhaMgt.buscarValidador(Integer.parseInt(request.getParameter("validadorID")));
		interfaceTerminalMgt.atribuirValidadorAoTerminal(terminal, validador);
		validador.setEmUso(true);
		interfaceLinhaMgt.alterarValidador(validador);
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
			terminal = interfaceTerminalMgt.buscarTerminal(Integer.parseInt(request.getParameter("terminalID")));
		}
		
		terminal.setNomeTerminal(request.getParameter("nomeTerminal").trim());
				
		return terminal;
	}
	
	protected ModelAndView mostrarFormTerminalValidador(String terminalID){

		ModelAndView mav = new ModelAndView("formTerminalValidador");
		mav.addObject("terminalID",terminalID);
				
		Terminal terminal = interfaceTerminalMgt.buscarTerminal(Integer.parseInt(terminalID));				
		mav.addObject("terminal",terminal);		
		
		Collection<Validador> validadores = interfaceLinhaMgt.buscarValidadoresNaoEmUso();
		mav.addObject("validadores", validadores);
		
		return mav;		
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
			terminal = interfaceTerminalMgt.buscarTerminal(Integer.parseInt(terminalID));
			
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
			else if (operacao.equals("associar")|| operacao.equals("Associar")){
				associarValidadorAoTerminal(request);
			}
			
			if (operacao.equals("remover") || operacao.equals("Remover")){
				String[] terminaisIDs = request.getParameterValues("chkTerminalID");
				
				for (int i = 0; i < terminaisIDs.length; i++) {
					int terminalID = Integer.parseInt(terminaisIDs[i]);
					Terminal term = new Terminal();
					term.setTerminalID(terminalID);
					interfaceTerminalMgt.removerTerminal(term);
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
		else if (request.getServletPath().contains("/formTerminalValidador.html")){
			return mostrarFormTerminalValidador(request.getParameter("terminalID"));
		}
		else{
			return mostrarForm(request.getParameter("terminalID"));
		}
	}
	
	public ITerminalMgt getInterfaceTerminalMgt() {
		return interfaceTerminalMgt;
	}

	public void setInterfaceTerminalMgt(ITerminalMgt interfaceTerminalMgt) {
		this.interfaceTerminalMgt = interfaceTerminalMgt;
	}

	public ILinhaMgt getInterfaceLinhaMgt() {
		return interfaceLinhaMgt;
	}

	public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
		this.interfaceLinhaMgt = interfaceLinhaMgt;
	}
		
}
