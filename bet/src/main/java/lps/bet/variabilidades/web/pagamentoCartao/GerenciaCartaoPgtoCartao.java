package lps.bet.variabilidades.web.pagamentoCartao;

import lps.bet.interfaces.ICartaoMgt;
import lps.bet.basico.passageiroMgr.IPassageiroMgt;
import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Pagamento;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.web.ControladorBet;
import lps.bet.basico.web.controlGerencia.UtilsGerencia;
import lps.bet.variabilidades.pagamentoCartaoMgr.IPagtoCartaoMgt;
import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

public class GerenciaCartaoPgtoCartao extends ControladorBet {

	ICartaoMgt interfaceCartaoMgt;
	IPassageiroMgt interfacePassageiroMgt;
	IPagtoCartaoMgt interfacePagtoCartaoMgt;

	SimpleDateFormat sdf;

	public GerenciaCartaoPgtoCartao() {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	protected void criarPagamento(Cartao cartao, TipoPassagPagtoCartao passageiroPagtoCartao){
		Pagamento pagamento = new Pagamento();
		pagamento.setCartao(cartao);
		pagamento.setDtPgto(cartao.getDtAquisicao());
		pagamento.setValorPgto(passageiroPagtoCartao.getValorAquisicao());
		interfaceCartaoMgt.criarPagamento(pagamento);		
		interfacePagtoCartaoMgt.registrarPagtoCartao(interfacePagtoCartaoMgt.buscarUltimoPagamento(), "Aquisi��o");		
	}

	protected void criarCartao(Cartao cartao) {
		interfaceCartaoMgt.criarCartao(cartao);		
		TipoPassagPagtoCartao passageiroPagtoCartao = interfacePagtoCartaoMgt.buscarTipoPassagPagtoCartao(cartao.getTipoPassageiro().getTipoID());
		if (passageiroPagtoCartao.isPagtoAquisicaoCartao()){
			criarPagamento(cartao, passageiroPagtoCartao);			
		}
	}
	
	protected void criarPagamento(Pagamento pagamento) {
		interfaceCartaoMgt.criarPagamento(pagamento);
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
			mav.addObject("mensagem", "Cart�o n�o encontrado.");
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

		// SimpleDateFormat sdf = new SimpleDateFormat();
		mav.addObject("sdf", sdf);

		Cartao cartao = null;
		Calendar dtAquisicao;
		Calendar dtValidade;
		List passageiros = interfacePassageiroMgt.buscarPassageiros();
		mav.addObject("passageiros", passageiros);
		List valores = new ArrayList<Float>();
		Collection tipos;

		if (cartaoID == null) {
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");
			dtAquisicao = Calendar.getInstance();
			dtValidade = Calendar.getInstance();
			Passageiro passageiro = interfacePassageiroMgt
					.buscarPassageiroPorID(Integer.parseInt(passageiroID));
			tipos = interfaceCartaoMgt.buscarTiposPermitidos(passageiro);		
			
			if (tipos.size() == 0) 
				mav.addObject("mensagem", "N�o h� tipos compat�veis dispon�veis.");			
			mav.addObject("passageiro", passageiro);

		} else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			cartao = interfaceCartaoMgt
					.buscarCartao(Integer.parseInt(cartaoID));
			tipos = interfaceCartaoMgt.buscarTiposPermitidos(cartao
					.getPassageiro(), cartao.getTipoPassageiro());
			// Na altera��o pode-se optar por n�o mudar o tipo, ent�o ele tb
			// deve ser uma op��o:
			//tipos.add(cartao.getTipoPassageiro());
			dtAquisicao = cartao.getDtAquisicao();
			dtValidade = cartao.getDtValidade();		
		}
		for (Iterator it = tipos.iterator(); it.hasNext();) {
			TipoPassageiro tipo = (TipoPassageiro) it.next();
			TipoPassagPagtoCartao passagPagtoCartao = interfacePagtoCartaoMgt.buscarTipoPassagPagtoCartao(tipo.getTipoID());			
			if (passagPagtoCartao.isPagtoAquisicaoCartao()){
			valores.add(passagPagtoCartao.getValorAquisicao());	
			}
			else
				valores.add(0);						
			}
			mav.addObject("primValor", valores.get(0));
			mav.addObject("pagarCartoes", true);
			mav.addObject("valores", valores);			
		
		mav.addObject("tipos", tipos);
		mav.addObject("cartao", cartao);
		mav.addObject("dtAquisicao", dtAquisicao);
		mav.addObject("dtValidade", dtValidade);		
		return mav;
	}

	protected void alterarCartao(Cartao cartao) {
		interfaceCartaoMgt.alterarCartao(cartao);
	}

	protected Cartao montarCartao(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Cartao cartao;

		// Opera��o de Cria��o
		if (request.getParameter("cartaoID") == null) {
			cartao = new Cartao();
			cartao.setDtAquisicao(Calendar.getInstance());
		}
		// Sen�o precisa buscar
		else {
			cartao = interfaceCartaoMgt.buscarCartao(Integer.parseInt(request
					.getParameter("cartaoID")));
		}

		Calendar dtValidade = UtilsGerencia.calendarFromStrings(request
				.getParameter("dtValidade").trim(), "23:59");
		cartao.setDtValidade(dtValidade);

		String strSaldo = request.getParameter("saldo").trim();
		float saldo = strSaldo.matches("[0-9]*\\.?[0-9]+") ? Float
				.parseFloat(strSaldo) : 0;
		cartao.setSaldo(saldo);
		Passageiro passageiro = interfacePassageiroMgt.buscarPassageiro(request
				.getParameter("nomePassageiro").trim());
		cartao.setPassageiro(passageiro);

		TipoPassageiro tipoRequisitado = interfaceCartaoMgt
				.buscarTipoPassageiro(request.getParameter("nomeTipo").trim());
		cartao.setTipoPassageiro(tipoRequisitado);

		return cartao;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao");

		if (request.getServletPath().equals("/gerenciaCartao.html")) {
			// Quando � chamado pela primeira vez a URL n�o possui o par�metro
			// 'operacao'
			if (operacao == null)
				return buscarCartoes();

			if (operacao.equals("criar")) {
				criarCartao(montarCartao(request));				
			}

			else if (operacao.equals("alterar")) {
				alterarCartao(montarCartao(request));
			}

			if (operacao.equals("remover")) {
				String[] cartoesIDs = request.getParameterValues("chkCartaoID");
				for (int i = 0; i < cartoesIDs.length; i++) {
					int cartaoID = Integer.parseInt(cartoesIDs[i]);
					interfaceCartaoMgt.removerCartao(cartaoID);
				}
			}

			// mostrando um cart�o ou todos, dependendo da operacao requisitada
			if (operacao.equals("buscar")) {
				return buscarCartao(Integer.parseInt(request
						.getParameter("cartaoID")));
			} else {
				return buscarCartoes();
			}
		} else {
			return mostrarForm(request.getParameter("cartaoID"), request
					.getParameter("usuarioID"));
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
