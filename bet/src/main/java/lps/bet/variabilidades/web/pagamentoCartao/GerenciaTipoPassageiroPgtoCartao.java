package lps.bet.variabilidades.web.pagamentoCartao;

import lps.bet.interfaces.ICartaoMgt;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.web.ControladorBet;
import lps.bet.variabilidades.pagamentoCartaoMgr.IPagtoCartaoMgt;
import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

//import sun.text.CompactShortArray.Iterator;

public class GerenciaTipoPassageiroPgtoCartao extends ControladorBet {

	protected ICartaoMgt interfaceCartaoMgt;
	protected Boolean combinarCartoes = false;
	protected IPagtoCartaoMgt interfacePagtoCartaoMgt;

	protected void criarTipoPassageiro(TipoPassageiro tipo) {
		interfaceCartaoMgt.criarTipoPassageiro(tipo);
	}

	protected ModelAndView buscarTiposPassageiros() {
		List tipos = interfaceCartaoMgt.buscarTiposPassageiros();

		ModelAndView mav = new ModelAndView("gerenciaTipoPassageiro");
		mav.addObject("tipos", tipos);
		return mav;
	}

	protected ModelAndView buscarTipoPassageiro(int tipoID) {
		TipoPassageiro tipo = interfaceCartaoMgt.buscarTipoPassageiro(tipoID);
		ModelAndView mav = new ModelAndView("gerenciaTipoPassageiro");
		if (tipo == null) {
			mav.addObject("mensagem", "Tipo de Passageiro n�o encontrado.");
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

		if (tipoID == null) {
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");
		} else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			tipo = interfaceCartaoMgt.buscarTipoPassageiro(Integer
					.parseInt(tipoID));
			if (combinarCartoes)
				mav.addObject("listaTiposIncompativeis", tipo
						.getTiposIncompativeis());
		}
		if (combinarCartoes) {
			Collection tipos = interfaceCartaoMgt.buscarTiposPermitidos(null);
			tipos.remove(tipo);
			mav.addObject("listaTipos", tipos);
		}

		mav.addObject("tipo", tipo);		
		
		TipoPassagPagtoCartao tipoPassagPagtoCartao = null;
		float valorAquisicao = 0;
		boolean pagtoAquisicaoCartao = false;
		if (tipoID != null){
			tipoPassagPagtoCartao = interfacePagtoCartaoMgt.buscarTipoPassagPagtoCartao(tipo.getTipoID());
			valorAquisicao = tipoPassagPagtoCartao.getValorAquisicao();
			pagtoAquisicaoCartao = tipoPassagPagtoCartao.isPagtoAquisicaoCartao();				
		}
		mav.addObject("valorAquisicao", valorAquisicao);
		mav.addObject("pagtoAquisicaoCartao", pagtoAquisicaoCartao);
		mav.addObject("pagarCartoes", true);			

		return mav;
	}

	protected void alterarTipoPassageiro(TipoPassageiro tipo) {
		interfaceCartaoMgt.alterarTipoPassageiro(tipo);
	}

	protected TipoPassageiro montarTipoPassageiro(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		TipoPassageiro tipo;

		// Opera��o de Cria��o
		if (request.getParameter("tipoID") == null) {
			tipo = new TipoPassageiro();
		}
		// Sen�o precisa buscar
		else {
			tipo = interfaceCartaoMgt.buscarTipoPassageiro(Integer
					.parseInt(request.getParameter("tipoID")));
		}

		tipo.setNomeTipo(request.getParameter("nomeTipo").trim());
		tipo.setDescricaoTipo(request.getParameter("descricaoTipo").trim());
		tipo.setFormaPgtoPassagem(request.getParameter("formaPgtoPassagem"));
		tipo.setDesconto(Integer.parseInt(request.getParameter("desconto")
				.trim()));

		if (getCombinarCartoes()) {
			Collection todosTipos = interfaceCartaoMgt.buscarTiposPassageiros();
			Collection tiposIncompativeis = tipo.getTiposIncompativeis();
			if (tiposIncompativeis == null) {
				tiposIncompativeis = new HashSet();
				tipo.setTiposIncompativeis(tiposIncompativeis);
			}
			tiposIncompativeis.clear();
			java.util.Iterator itTipos = todosTipos.iterator();
			while (itTipos.hasNext()) {
				TipoPassageiro tipoIncompativel = (TipoPassageiro) itTipos
						.next();
				String checkTipoIncompativel = request.getParameter("chkTipo"
						+ tipoIncompativel.getTipoID());
				if (checkTipoIncompativel != null)
					tiposIncompativeis.add(tipoIncompativel);
			}
		}

		return tipo;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao");

		if (request.getServletPath().equals("/gerenciaTipoPassageiro.html")) {
			// Quando � chamado pela primeira vez a URL n�o possui o par�metro
			// 'operacao'
			if (operacao == null)
				return buscarTiposPassageiros();

			if (operacao.equals("criar")) {
				criarTipoPassageiro(montarTipoPassageiro(request));
				interfacePagtoCartaoMgt.registrarTipoPassagPagtoCartao(interfacePagtoCartaoMgt.buscarUltimoTipoPassageiro(), Boolean.parseBoolean(request.getParameter("pagtoAquisicaoCartao")) ,Float.parseFloat(request.getParameter("valorAquisicao")));				
			}

			else if (operacao.equals("alterar")) {
				alterarTipoPassageiro(montarTipoPassageiro(request));
				interfacePagtoCartaoMgt.registrarTipoPassagPagtoCartao(Integer.parseInt(request.getParameter("tipoID")), Boolean.parseBoolean(request.getParameter("pagtoAquisicaoCartao")),Float.parseFloat(request.getParameter("valorAquisicao")));				
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

	public void setCombinarCartoes(Boolean combinarCartoes) {
		this.combinarCartoes = combinarCartoes;
	}

	public Boolean getCombinarCartoes() {
		return combinarCartoes;
	}


	public IPagtoCartaoMgt getInterfacePagtoCartaoMgt() {
		return interfacePagtoCartaoMgt;
	}

	public void setInterfacePagtoCartaoMgt(IPagtoCartaoMgt interfacePagtoCartaoMgt) {
		this.interfacePagtoCartaoMgt = interfacePagtoCartaoMgt;
	}
}
