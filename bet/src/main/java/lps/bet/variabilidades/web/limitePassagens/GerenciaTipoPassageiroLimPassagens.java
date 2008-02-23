package lps.bet.variabilidades.web.limitePassagens;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.web.ControladorBet;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.variabilidades.limitePassagensMgr.ILimitePassagensMgt;
import lps.bet.variabilidades.tiposDados.TipoPassagLimPassagens;

import org.springframework.web.servlet.ModelAndView;


public class GerenciaTipoPassageiroLimPassagens extends ControladorBet {

	protected ICartaoMgt interfaceCartaoMgt;
	protected ILimitePassagensMgt interfaceLimitePassagensMgt;

	protected void criarTipoPassageiro(TipoPassageiro tipo) {
		interfaceCartaoMgt.criarTipoPassageiro(tipo);
	}

	protected ModelAndView buscarTiposPassageiros() {
		List tipos = interfaceCartaoMgt.buscarTiposPassageiros();
		Collection<TipoPassagLimPassagens> tiposLimPassagens = interfaceLimitePassagensMgt.buscarTodosTipos();
		
		ModelAndView mav = new ModelAndView("gerenciaTipoPassageiro");
		mav.addObject("tipos", tipos);
		mav.addObject("tiposLimPassagens", tiposLimPassagens);
		
		return mav;
	}

	protected ModelAndView buscarTipoPassageiro(int tipoID) {
		TipoPassageiro tipo = interfaceCartaoMgt.buscarTipoPassageiro(tipoID);
		ModelAndView mav = new ModelAndView("gerenciaTipoPassageiro");
		if (tipo == null) {
			mav.addObject("mensagem", "Tipo de Passageiro não encontrado.");
		} else {
			List tipos = new ArrayList();
			tipos.add(tipo);
			mav.addObject("tipos", tipos);
		}
		return mav;
	}

	protected ModelAndView mostrarForm(String tipoID) {

		ModelAndView mav = new ModelAndView("formTipoPassageiro");
		mav.addObject("tipoID", tipoID);

		TipoPassageiro tipo = null;
		TipoPassagLimPassagens limPassagens = null;
		
		if (tipoID == null) {
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");
		} else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			tipo = interfaceCartaoMgt.buscarTipoPassageiro(Integer.parseInt(tipoID));
			limPassagens = interfaceLimitePassagensMgt.buscarTipoPassagLimPassagens(Integer.parseInt(tipoID));
		}

		mav.addObject("tipo", tipo);		
		mav.addObject("limPassagens", limPassagens);
	
		return mav;
	}

	protected void alterarTipoPassageiro(TipoPassageiro tipo) {
		interfaceCartaoMgt.alterarTipoPassageiro(tipo);
	}

	protected TipoPassagLimPassagens montarTipoPassagLimPassagens(HttpServletRequest request, TipoPassageiro tipoPassageiro){
		String operacao = request.getParameter("operacao");

		TipoPassagLimPassagens limPassagens;
		
		// Operação de Criação
		if (request.getParameter("tipoID") == null) {
			limPassagens = new TipoPassagLimPassagens();
		}
		// Senão precisa buscar
		else {
			limPassagens = interfaceLimitePassagensMgt.buscarTipoPassagLimPassagens(Integer.parseInt(request.getParameter("tipoID")));
		}
		
		limPassagens.setLimitePassagens(Integer.parseInt(request.getParameter("limitePassagens")));
		limPassagens.setTipoPassageiro(tipoPassageiro);

		return limPassagens;
	}
	
	protected TipoPassageiro montarTipoPassageiro(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		TipoPassageiro tipo;

		// Operação de Criação
		if (request.getParameter("tipoID") == null) {
			tipo = new TipoPassageiro();
		}
		// Senão precisa buscar
		else {
			tipo = interfaceCartaoMgt.buscarTipoPassageiro(Integer
					.parseInt(request.getParameter("tipoID")));
		}

		tipo.setNomeTipo(request.getParameter("nomeTipo").trim());
		tipo.setDescricaoTipo(request.getParameter("descricaoTipo").trim());
		tipo.setFormaPgtoPassagem(request.getParameter("formaPgtoPassagem"));
		tipo.setDesconto(Integer.parseInt(request.getParameter("desconto").trim()));

		return tipo;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao");

		if (request.getServletPath().equals("/gerenciaTipoPassageiro.html")) {
			// Quando é chamado pela primeira vez a URL não possui o parâmetro
			// 'operacao'
			if (operacao == null)
				return buscarTiposPassageiros();

			if (operacao.equals("criar") || operacao.equals("alterar")) {
				//criarTipoPassageiro(montarTipoPassageiro(request));
				TipoPassagLimPassagens limPassagens = montarTipoPassagLimPassagens(request, montarTipoPassageiro(request));
				interfaceLimitePassagensMgt.salvarTipoPassagLimPassagens(limPassagens);				
			}
			
			if (operacao.equals("remover")) {
				String[] tiposIDs = request.getParameterValues("chkTipoID");
				for (int i = 0; i < tiposIDs.length; i++) {
					int tipoID = Integer.parseInt(tiposIDs[i]);
					interfaceCartaoMgt.removerTipoPassageiro(tipoID);
				}
			}

			// Mostrando um tipo ou todas eles, dependendo da operacao
			// requisitada
			if (operacao.equals("buscar")) {
				return buscarTipoPassageiro(Integer.parseInt(request
						.getParameter("tipoID")));
			} else {
				return buscarTiposPassageiros();
			}
		} else {
			return mostrarForm(request.getParameter("tipoID"));
		}
	}

	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}

	public ILimitePassagensMgt getInterfaceLimitePassagensMgt() {
		return interfaceLimitePassagensMgt;
	}

	public void setInterfaceLimitePassagensMgt(
			ILimitePassagensMgt interfaceLimitePassagensMgt) {
		this.interfaceLimitePassagensMgt = interfaceLimitePassagensMgt;
	}
	
}
