package lps.bet.variabilidades.web.pagamentoCartao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.passageiroMgr.IPassageiroMgt;
import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.web.ControladorBet;
import lps.bet.basico.web.gerenciaCtrl.UtilsGerencia;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.variabilidades.pagamentoCartaoMgr.IPagtoCartaoMgt;
import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaCartaoPgtoCartao extends ControladorBet {

	ICartaoMgt interfaceCartaoMgt;
	IPassageiroMgt interfacePassageiroMgt;
	IPagtoCartaoMgt interfacePagtoCartaoMgt;

	SimpleDateFormat sdf;

	public GerenciaCartaoPgtoCartao() {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	protected void criarCartao(Cartao cartao) {
		interfaceCartaoMgt.criarCartao(cartao);		
	}
	
	protected void alterarCartao(Cartao cartao) {
		interfaceCartaoMgt.alterarCartao(cartao);
	}
	
	protected ModelAndView buscarCartoes() {
		List cartoes = interfaceCartaoMgt.buscarCartoes();

		// SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar data = Calendar.getInstance();

		ModelAndView mav = new ModelAndView("gerenciaCartao");
		mav.addObject("cartoes", cartoes);
		mav.addObject("sdf", sdf);
		mav.addObject("data", data);
		return mav;
	}
	
	protected ModelAndView buscarCartao(int cartaoID) {
		Cartao cartao = interfaceCartaoMgt.buscarCartao(cartaoID);
		ModelAndView mav = new ModelAndView("gerenciaCartao");
		if (cartao == null) {
			mav.addObject("mensagem", "Cartão não encontrado.");
		} else {
			List cartoes = new ArrayList();
			cartoes.add(cartao);
			Calendar data = Calendar.getInstance();
			mav.addObject("cartoes", cartoes);
			mav.addObject("sdf", sdf);
			mav.addObject("data", data);
		}
		return mav;
	}

	protected ModelAndView mostrarForm(String cartaoID, String passageiroID) {

		ModelAndView mav = new ModelAndView("formCartao");
		mav.addObject("cartaoID", cartaoID);
		mav.addObject("sdf", sdf);

		Cartao cartao;
		Collection tipos;		
		String nomeOperacao, operacao;

		if (cartaoID == null) {
			operacao = "criar";
			nomeOperacao = "Criar";
			cartao = null;
			Calendar data = Calendar.getInstance();
			mav.addObject("data", data);	
			
			Passageiro passageiro = interfacePassageiroMgt.buscarPassageiroPorID(Integer.parseInt(passageiroID));
			mav.addObject("passageiro", passageiro);
			tipos = interfaceCartaoMgt.buscarTiposPermitidos(passageiro);		
			
			if (tipos.size() == 0) 
				mav.addObject("mensagem", "Não há tipos disponíveis.");			
			

		} 
		else {
			operacao = "alterar";
			nomeOperacao = "Alterar";
			cartao = interfaceCartaoMgt.buscarCartao(Integer.parseInt(cartaoID));
			tipos = interfaceCartaoMgt.buscarTiposPermitidos(cartao.getPassageiro(), cartao.getTipoPassageiro());
			// Na alteração pode-se optar por não mudar o tipo, então ele tb deve ser uma opção:
			tipos.add(cartao.getTipoPassageiro());
		}
		
		Collection<TipoPassagPagtoCartao> tiposPagamento = interfacePagtoCartaoMgt.buscarTiposPagtosPermitidos(tipos);

		mav.addObject("operacao", operacao);
		mav.addObject("nomeOperacao", nomeOperacao);
		mav.addObject("tiposPagamento", tiposPagamento);
		mav.addObject("cartao", cartao);
	
		return mav;
	}

	protected Cartao montarCartao(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Cartao cartao;

		// Operação de Criação
		if (request.getParameter("cartaoID") == null) {
			cartao = new Cartao();
			cartao.setDtAquisicao(Calendar.getInstance());
		}
		// Senão precisa buscar
		else {
			cartao = interfaceCartaoMgt.buscarCartao(Integer.parseInt(request.getParameter("cartaoID")));
		}

		Calendar dtValidade = UtilsGerencia.calendarFromStrings(request.getParameter("dtValidade").trim(), "23:59");
		cartao.setDtValidade(dtValidade);

		String strSaldo = request.getParameter("saldo").trim();
		float saldo = strSaldo.matches("[0-9]*\\.?[0-9]+") ? Float.parseFloat(strSaldo) : 0;
		cartao.setSaldo(saldo);
		Passageiro passageiro = interfacePassageiroMgt.buscarPassageiro(request.getParameter("nomePassageiro").trim());
		cartao.setPassageiro(passageiro);

		TipoPassageiro tipoRequisitado = interfaceCartaoMgt.buscarTipoPassageiro(request.getParameter("nomeTipo").trim());
		cartao.setTipoPassageiro(tipoRequisitado);

		return cartao;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao");

		if (request.getServletPath().equals("/gerenciaCartao.html")) {
			// Quando é chamado pela primeira vez a URL não possui o parâmetro
			// 'operacao'
			if (operacao == null)
				return buscarCartoes();

			if ((operacao.equals("criar")) || (operacao.equals("alterar"))){
				Cartao cartao = montarCartao(request);
				if (operacao.equals("criar")) {
					criarCartao(cartao);				
				}
				else {
					alterarCartao(cartao);
				}
			}

			if (operacao.equals("remover")) {
				String[] cartoesIDs = request.getParameterValues("chkCartaoID");
				for (int i = 0; i < cartoesIDs.length; i++) {
					int cartaoID = Integer.parseInt(cartoesIDs[i]);
					interfaceCartaoMgt.removerCartao(cartaoID);
				}
			}

			// mostrando um cartão ou todos, dependendo da operacao requisitada
			if (operacao.equals("buscar")) {
				return buscarCartao(Integer.parseInt(request.getParameter("cartaoID")));
			} else {
				return buscarCartoes();
			}
		} else {
			return mostrarForm(request.getParameter("cartaoID"), request.getParameter("usuarioID"));
		}
	}
	

	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}

	public IPassageiroMgt getInterfacePassageiroMgt() {
		return interfacePassageiroMgt;
	}

	public void setInterfacePassageiroMgt(IPassageiroMgt interfacePassageiroMgt) {
		this.interfacePassageiroMgt = interfacePassageiroMgt;
	}

	public IPagtoCartaoMgt getInterfacePagtoCartaoMgt() {
		return interfacePagtoCartaoMgt;
	}

	public void setInterfacePagtoCartaoMgt(IPagtoCartaoMgt interfacePagtoCartaoMgt) {
		this.interfacePagtoCartaoMgt = interfacePagtoCartaoMgt;
	}


}
