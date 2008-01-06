package lps.bet.variabilidades.web.empresaUsuaria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.passageiroMgr.IPassageiroMgt;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.web.ControladorBet;
import lps.bet.variabilidades.empresaUsuariaMgr.IEmpresaUsuariaMgt;
import lps.bet.variabilidades.tiposDados.EmpresaUsuaria;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaPassageiroEmpresaUsuaria extends ControladorBet{

	IPassageiroMgt interfacePassageiroMgt;
	IEmpresaUsuariaMgt interfaceEmpresaUsuariaMgt;

	public GerenciaPassageiroEmpresaUsuaria() {
		nivelMinimoAcesso = 10;
	}

	public IPassageiroMgt getInterfacePassageiroMgt() {
		return interfacePassageiroMgt;
	}

	public void setInterfacePassageiroMgt(IPassageiroMgt interfacePassageiroMgt) {
		this.interfacePassageiroMgt = interfacePassageiroMgt;
	}	

	public IEmpresaUsuariaMgt getInterfaceEmpresaUsuariaMgt() {
		return interfaceEmpresaUsuariaMgt;
	}

	public void setInterfaceEmpresaUsuariaMgt(
			IEmpresaUsuariaMgt interfaceEmpresaUsuariaMgt) {
		this.interfaceEmpresaUsuariaMgt = interfaceEmpresaUsuariaMgt;
	}

	protected void criarPassageiro(Passageiro passageiro){
		interfacePassageiroMgt.criarPassageiro(passageiro);
	}
	
	protected Passageiro montarPassageiro(HttpServletRequest request){

		Passageiro passageiro;
		
		//Operação de Criação
		if (request.getParameter("usuarioID")==null){
			passageiro = new Passageiro();
		}
		//Senão precisa buscar
		else {
			passageiro = interfacePassageiroMgt.buscarPassageiro(Long.parseLong(request.getParameter("cpf").trim()));
		}
		String strCPF = request.getParameter("cpf");
		passageiro.setCpf(Long.parseLong(strCPF.trim()));
		passageiro.setNomePassageiro(request.getParameter("nomePassageiro").trim());
		passageiro.setLogin(request.getParameter("login").trim());
		passageiro.setSenha(request.getParameter("senha").trim());
		passageiro.setNivelAcesso(1);
				
		return passageiro;
	}

	private void atribuirEmpresaAoPassageiro(String nomeFantasia, Passageiro passageiro) {
		EmpresaUsuaria empresaNova = null;
		if (!nomeFantasia.equals("nenhuma")){
			empresaNova = interfaceEmpresaUsuariaMgt.buscarEmpresaUsuaria(nomeFantasia);			
		}		
		interfaceEmpresaUsuariaMgt.atribuirEmpresaAoPassageiro(empresaNova, passageiro);
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
 
	protected ModelAndView mostrarForm(String usuarioID, String forwardView){
		String view = "formPassageiro";
		
		if (forwardView != null){
			view = forwardView;
		}
	
		ModelAndView mav = new ModelAndView(view);
		mav.addObject("usuarioID",usuarioID);
		
		Passageiro passageiro = null;
		EmpresaUsuaria empresaUsuaria = null;
		
		if (usuarioID == null){
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");						
		}
		else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			passageiro = interfacePassageiroMgt.buscarPassageiroPorID(Integer.parseInt(usuarioID));
			empresaUsuaria = interfaceEmpresaUsuariaMgt.buscarEmpresaPorPassageiro(passageiro);
		}
		mav.addObject("passageiro",passageiro);
		mav.addObject("empresaUsuaria", empresaUsuaria);
		
		//Para poder selecionar alguma empresa na criação ou alteração
		List empresas = interfaceEmpresaUsuariaMgt.buscarEmpresasUsuarias();
		mav.addObject("empresas", empresas);
		
		
		return mav;		
	}

	
	protected void alterarPassageiro(Passageiro passageiro){
		interfacePassageiroMgt.alterarPassageiro(passageiro);
	}

	
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao");
				
		if (request.getServletPath().equals("/gerenciaPassageiro.html")){
			//Quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
			if (operacao == null)
				return buscarPassageiros();
			
			if (operacao.equals("criar")){
				Passageiro passageiro = montarPassageiro(request);
				criarPassageiro(passageiro);
				String nomeFantasia = request.getParameter("nomeFantasia").trim();
				atribuirEmpresaAoPassageiro(nomeFantasia, passageiro); 
			}
			
			else if (operacao.equals("alterar")){
				Passageiro passageiro = montarPassageiro(request);
				alterarPassageiro(passageiro);
				String nomeFantasia = request.getParameter("nomeFantasia").trim();
				atribuirEmpresaAoPassageiro(nomeFantasia, passageiro); 
			}
			
			if (operacao.equals("remover")){
				String[] passageirosIDs = request.getParameterValues("chkUsuarioID");
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
			return mostrarForm(request.getParameter("usuarioID"),request.getParameter("forwardView"));
		}
	}

}
