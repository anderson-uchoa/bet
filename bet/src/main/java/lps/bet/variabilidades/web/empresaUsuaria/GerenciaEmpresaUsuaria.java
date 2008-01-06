package lps.bet.variabilidades.web.empresaUsuaria;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.web.ControladorBet;
import lps.bet.variabilidades.empresaUsuariaMgr.IEmpresaUsuariaMgt;
import lps.bet.variabilidades.tiposDados.EmpresaUsuaria;

import org.springframework.web.servlet.ModelAndView;


public class GerenciaEmpresaUsuaria extends ControladorBet{
	
	public IEmpresaUsuariaMgt interfaceEmpresaUsuariaMgt;
	
	public GerenciaEmpresaUsuaria(){
		nivelMinimoAcesso = 10;
	}

	protected void criarEmprUsuaria(EmpresaUsuaria empresa){
		interfaceEmpresaUsuariaMgt.criarEmpresa(empresa);
		
	
	}

	protected ModelAndView buscarEmprUsuaria(){
		List empresasUsuarias = interfaceEmpresaUsuariaMgt.buscarEmpresasUsuarias();
		
		ModelAndView mav = new ModelAndView("gerenciaEmprUsuaria");
		
		mav.addObject("empresaUsuaria", empresasUsuarias);
		return mav;
	}
	
	protected ModelAndView buscarEmprUsuaria(int empresaID){
		EmpresaUsuaria empresaUsuaria = interfaceEmpresaUsuariaMgt.buscarEmpUsu(empresaID);
		ModelAndView mav = new ModelAndView("gerenciaEmprUsuaria");
		if (empresaUsuaria==null){
			mav.addObject("mensagem", "Empresa Usu�ria n�o encontrada.");
		}
		else{
			List empresaUsuarias = new ArrayList();
			empresaUsuarias.add(empresaUsuaria);	
			mav.addObject("empresaUsuaria", empresaUsuarias);
					
		}

		return mav;

	}
	
	protected void alterarEmprUsuaria(EmpresaUsuaria empresa){
		interfaceEmpresaUsuariaMgt.alterarEmpresa(empresa);
	}

	protected EmpresaUsuaria montarEmprUsuaria(HttpServletRequest request){
		String operacao = request.getParameter("operacao");
		EmpresaUsuaria empresa;
		
		//Opera��o de Cria��o
		if (request.getParameter("usuarioID")==null){
			empresa = new EmpresaUsuaria();
		}
		//Sen�o precisa buscar
		else {
			empresa = interfaceEmpresaUsuariaMgt.buscarEmpUsu(Integer.parseInt(request.getParameter("usuarioID")));
		}
		empresa.setNomeFantasia(request.getParameter("nomeFantasia").trim());
		empresa.setContato(request.getParameter("contato").trim());
		empresa.setRazaoSocial(request.getParameter("razaoSocial").trim());
		empresa.setCnpj(request.getParameter("cnpj").trim());
		empresa.setEndereco(request.getParameter("endereco").trim());
		empresa.setTelefone(request.getParameter("telefone").trim());
		empresa.setEmail(request.getParameter("email").trim());
		
		empresa.setLogin(request.getParameter("login").trim());
		empresa.setSenha(request.getParameter("senha").trim());
		empresa.setNivelAcesso(1);		
		
		return empresa;
	}
	
	protected ModelAndView mostrarForm(String empresaID){

		ModelAndView mav = new ModelAndView("formEmprUsuaria");
		mav.addObject("empresaID",empresaID);
		
		EmpresaUsuaria empresa = null;
		
		if (empresaID == null){
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");						
			
		}
		else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			empresa = interfaceEmpresaUsuariaMgt.buscarEmpUsu(Integer.parseInt(empresaID));
		}
		mav.addObject("empresa",empresa);		
		return mav;		
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String operacao = request.getParameter("operacao");
		if (request.getServletPath().equals("/gerenciaEmprUsuaria.html")){
			// quando � chamado pela primeira vez a URL n�o possui o par�metro 'operacao'
			if (operacao == null)
				return buscarEmprUsuaria();

			if (operacao.equals("criar")|| operacao.equals("Criar")){				
				criarEmprUsuaria(montarEmprUsuaria(request));
			}
			else if (operacao.equals("alterar")|| operacao.equals("Alterar")){				
				alterarEmprUsuaria(montarEmprUsuaria(request));
			}

			if (operacao.equals("remover") || operacao.equals("Remover")){
				String[] empresasIDs = request.getParameterValues("chkUsuarioID");
				for (int i = 0; i < empresasIDs.length; i++) {
					int usuarioID = Integer.parseInt(empresasIDs[i]);
					EmpresaUsuaria emp = new EmpresaUsuaria();
					emp.setUsuarioID(usuarioID);
					interfaceEmpresaUsuariaMgt.removerEmpresa(emp);
				}
			}

			//Mostrando uma empresa usu�ria ou todas, dependendo da operacao requisitada
			if (operacao.equals("buscar") || operacao.equals("Buscar")){
				if(request.getParameter("usuarioID").equals("") || request.getParameter("usuarioID")==null){
					buscarEmprUsuaria();
				}
				return buscarEmprUsuaria(Integer.parseInt(request.getParameter("usuarioID")));			
			}
			else {
				return buscarEmprUsuaria();
			}		
		}
		else{
			return mostrarForm(request.getParameter("usuarioID"));
		}
	}

	public IEmpresaUsuariaMgt getInterfaceEmpresaUsuariaMgt() {
		return interfaceEmpresaUsuariaMgt;
	}

	public void setInterfaceEmpresaUsuariaMgt(
			IEmpresaUsuariaMgt interfaceEmpresaUsuariaMgt) {
		this.interfaceEmpresaUsuariaMgt = interfaceEmpresaUsuariaMgt;
	}
	
}
