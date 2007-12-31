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
		Collection<TipoPassagPagtoCartao> tiposPagamentos = interfacePagtoCartaoMgt.buscarTodosTipos();
		
		ModelAndView mav = new ModelAndView("gerenciaTipoPassageiro");
		mav.addObject("tipos", tipos);
		mav.addObject("tiposPagamentos", tiposPagamentos);
		
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
		TipoPassagPagtoCartao tipoPagamento = null;
		
		if (tipoID == null) {
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");
		} else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			tipo = interfaceCartaoMgt.buscarTipoPassageiro(Integer.parseInt(tipoID));
			tipoPagamento = interfacePagtoCartaoMgt.buscarTipoPassagPagtoCartao(Integer.parseInt(tipoID));
		}

		mav.addObject("tipo", tipo);		
		mav.addObject("tipoPagamento", tipoPagamento);
	

		return mav;
	}

	protected void alterarTipoPassageiro(TipoPassageiro tipo) {
		interfaceCartaoMgt.alterarTipoPassageiro(tipo);
	}

	protected TipoPassagPagtoCartao montarTipoPassagPagtoCartao(HttpServletRequest request, TipoPassageiro tipoPassageiro){
		String operacao = request.getParameter("operacao");

		TipoPassagPagtoCartao tipoPagamento;
		
		// Operação de Criação
		if (request.getParameter("tipoID") == null) {
			tipoPagamento = new TipoPassagPagtoCartao();
		}
		// Senão precisa buscar
		else {
			tipoPagamento = interfacePagtoCartaoMgt.buscarTipoPassagPagtoCartao(Integer.parseInt(request.getParameter("tipoID")));
		}
		
		tipoPagamento.setPagtoAquisicaoCartao(Boolean.parseBoolean(request.getParameter("pagtoAquisicaoCartao")));

		tipoPagamento.setValorAquisicao(Float.parseFloat(request.getParameter("valorAquisicao")));
		tipoPagamento.setTipoPassageiro(tipoPassageiro);

		return tipoPagamento;
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
		tipo.setDesconto(Integer.parseInt(request.getParameter("desconto")
				.trim()));

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
				TipoPassagPagtoCartao tipoPagamento = montarTipoPassagPagtoCartao(request, montarTipoPassageiro(request));
				interfacePagtoCartaoMgt.registrarTipoPassagPagtoCartao(tipoPagamento);				
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
