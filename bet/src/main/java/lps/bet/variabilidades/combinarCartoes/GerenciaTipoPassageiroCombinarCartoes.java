package lps.bet.variabilidades.combinarCartoes;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.variabilidades.tiposDados.TipoPassageiroCombinacaoRestrita;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaTipoPassageiroCombinarCartoes extends
		lps.bet.basico.web.controlGerencia.cartao.GerenciaTipoPassageiro {

	private ICartaoMgt interfaceCartaoMgt;

	protected ModelAndView mostrarForm(String tipoID) {

		ModelAndView mav = new ModelAndView("formTipoPassageiro");
		mav.addObject("tipoID", tipoID);

		TipoPassageiro tipo = null;
		TipoPassageiroCombinacaoRestrita tipoC = null;

		if (tipoID == null) {
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");
		} else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			tipo = getInterfaceCartaoMgt().buscarTipoPassageiro(Integer
					.parseInt(tipoID));
			tipoC = (TipoPassageiroCombinacaoRestrita)tipo;
					
			mav.addObject("listaTiposIncompativeis", tipoC.getTiposIncompativeis());
		}

		Collection tipos = getInterfaceCartaoMgt().buscarTiposPassageiros();		
		tipos.remove(tipo);
		
		mav.addObject("listaTipos", tipos);

		mav.addObject("tipo", tipo);
		return mav;
	}

	protected TipoPassageiro montarTipoPassageiro(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		TipoPassageiroCombinacaoRestrita tipo;

		// Operação de Criação
		if (request.getParameter("tipoID") == null) {
			tipo = new TipoPassageiroCombinacaoRestrita();
		}
		// Senão precisa buscar
		else {
			tipo = (TipoPassageiroCombinacaoRestrita)getInterfaceCartaoMgt().buscarTipoPassageiro(Integer
					.parseInt(request.getParameter("tipoID")));
		}

		tipo.setNomeTipo(request.getParameter("nomeTipo").trim());
		tipo.setDescricaoTipo(request.getParameter("descricaoTipo").trim());
		tipo.setFormaPgtoPassagem(request.getParameter("formaPgtoPassagem"));
		tipo.setDesconto(Integer.parseInt(request.getParameter("desconto")
				.trim()));

		Collection todosTipos = getInterfaceCartaoMgt().buscarTiposPassageiros();
		Collection tiposIncompativeis = tipo.getTiposIncompativeis();
		if (tiposIncompativeis == null) {
			tiposIncompativeis = new HashSet();
			tipo.setTiposIncompativeis(tiposIncompativeis);
		}
		tiposIncompativeis.clear();
		java.util.Iterator itTipos = todosTipos.iterator();
		while (itTipos.hasNext()) {
			TipoPassageiro tipoIncompativel = (TipoPassageiro) itTipos.next();
			String checkTipoIncompativel = request.getParameter("chkTipo"
					+ tipoIncompativel.getTipoID());
			if (checkTipoIncompativel != null)
				tiposIncompativeis.add(tipoIncompativel);
		}

		return tipo;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		super.setInterfaceCartaoMgt(interfaceCartaoMgt);
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}

	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

}
