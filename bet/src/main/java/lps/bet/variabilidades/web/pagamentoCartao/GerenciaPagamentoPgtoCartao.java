package lps.bet.variabilidades.web.pagamentoCartao;

import lps.bet.interfaces.ICartaoMgt;
import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Pagamento;
import lps.bet.basico.web.ControladorBet;
import lps.bet.basico.web.controlGerencia.UtilsGerencia;
import lps.bet.variabilidades.pagamentoCartaoMgr.IPagtoCartaoMgt;
import lps.bet.variabilidades.tiposDados.PagamentoPagtoCartao;
import lps.bet.variabilidades.tiposDados.TipoPassagPagtoCartao;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class GerenciaPagamentoPgtoCartao extends ControladorBet{
	
	protected ICartaoMgt interfaceCartaoMgt;
	protected SimpleDateFormat sdf;
	protected IPagtoCartaoMgt interfacePagtoCartaoMgt;	
	
	public GerenciaPagamentoPgtoCartao(){
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}

	protected ModelAndView buscarPagamentos(){
		List pagamentos = interfaceCartaoMgt.buscarPagamentos();
		Collection<PagamentoPagtoCartao> pagtosCartoes = interfacePagtoCartaoMgt.buscarPagtosCartao();		
		
		Calendar data = Calendar.getInstance();

		ModelAndView mav = new ModelAndView("gerenciaPagamento");
		
		mav.addObject("pagamentos", pagamentos);
		mav.addObject("pagtosCartoes", pagtosCartoes);
		mav.addObject("sdf", sdf);
		mav.addObject("data",data);
		return mav;		
	}
	
	protected ModelAndView buscarPagamento(int pgtoID){
		Pagamento pagamento = interfaceCartaoMgt.buscarPagamento(pgtoID);
		ModelAndView mav = new ModelAndView("gerenciaPagamento");
		if (pagamento == null){
			mav.addObject("mensagem", "Pagamento não encontrado.");
		}
		else {
			List pagamentos = new ArrayList();
			pagamentos.add(pagamento);

			mav.addObject("pagamentos", pagamentos);
			mav.addObject("sdf", sdf);			
		}

		return mav;
	}
	
	protected void criarPagamento(Pagamento pagamento, HttpServletRequest request){
		int pagtoID = interfaceCartaoMgt.criarPagamento(pagamento);
		
        PagamentoPagtoCartao pgtoCartao = new PagamentoPagtoCartao();
        pgtoCartao.setPagtoID(pagtoID);        
		pgtoCartao.setTipoPagto(request.getParameter("tipoPagto").trim());
		
        interfacePagtoCartaoMgt.criarPagtoCartao(pgtoCartao);
	}
	
	protected void alterarPagamento(Pagamento pagamento, HttpServletRequest request){
		interfaceCartaoMgt.alterarPagamento(pagamento);
		
        PagamentoPagtoCartao pgtoCartao;
	    pgtoCartao = interfacePagtoCartaoMgt.buscarPagamentoPagtoCartao(pagamento.getPgtoID());
        pgtoCartao.setTipoPagto(request.getParameter("tipoPagto").trim());
        
		interfacePagtoCartaoMgt.alterarPagtoCartao(pgtoCartao);
	}

	protected Pagamento montarPagamento(HttpServletRequest request){
		String operacao = request.getParameter("operacao");
        
        Pagamento pagamento;

        //PagamentoPagtoCartao pgtoCartao;

        //Operação de Criação
		if (request.getParameter("pgtoID")==null){
			pagamento = new Pagamento();
            //pgtoCartao = new PagamentoPagtoCartao();

        }
		//Senão precisa buscar
		else {
			int pgtoID = Integer.parseInt(request.getParameter("pgtoID").trim());
			pagamento = interfaceCartaoMgt.buscarPagamento(pgtoID);
            //pgtoCartao = interfacePagtoCartaoMgt.buscarPagamentoPagtoCartao(pagamento.getPgtoID());
        }

		Cartao cartao = interfaceCartaoMgt.buscarCartao(Integer.parseInt(request.getParameter("cartaoID"))); 
		pagamento.setCartao(cartao);
		
		pagamento.setDtPgto(UtilsGerencia.calendarFromString(request.getParameter("dtPgto")));
		
		String strValorPgto = request.getParameter("valorPgto").trim();
		float valorPgto = strValorPgto.matches("[0-9]*\\.?[0-9]+") ? Float.parseFloat(strValorPgto) : 0;
		pagamento.setValorPgto(valorPgto);
		//pgtoCartao.setTipoPagto(request.getParameter("tipoPagto").trim());
		
        //interfacePagtoCartaoMgt.registrarPagtoCartao(pgtoCartao);

        return pagamento;
	}
	
	protected ModelAndView mostrarForm(String pgtoID){

		ModelAndView mav = new ModelAndView("formPagamento");
		mav.addObject("pgtoID",pgtoID);
		
		mav.addObject("sdf", sdf);
		
		Pagamento pagamento = null;		
		PagamentoPagtoCartao pagtoCartao = null;
		TipoPassagPagtoCartao tipoPassagPagtoCartao = null;

    	Calendar data;
		
		if (pgtoID == null){
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");						
			data = Calendar.getInstance();			
		}
		else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			pagamento = interfaceCartaoMgt.buscarPagamento(Integer.parseInt(pgtoID));			
			//Buscar dados complementares de pagamento de cartão
			pagtoCartao = interfacePagtoCartaoMgt.buscarPagamentoPagtoCartao(pagamento.getPgtoID());
			data = pagamento.getDtPgto();		
		}
		mav.addObject("pagamento",pagamento);		
		mav.addObject("data",data);		
		List cartoes = interfaceCartaoMgt.buscarCartoes();
		mav.addObject("cartoes", cartoes);
		mav.addObject("pagtoCartao", pagtoCartao);
		
		return mav;		
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String operacao = request.getParameter("operacao");
		
		if (request.getServletPath().equals("/gerenciaPagamento.html")){
			// quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
			if (operacao == null)
				return buscarPagamentos();

			if ((operacao.equals("criar"))||(operacao.equals("alterar"))){
				Pagamento pagamento = montarPagamento(request);
				if (operacao.equals("criar")){
					criarPagamento(pagamento,request);
				}
				else if (operacao.equals("alterar")){
					alterarPagamento(pagamento,request);
				}
			}	
			
			//Código do if a ser tirado posteriormente (não existe remover no Tarifa):
			if (operacao.equals("remover")){
				String[] pgtosIDs = request.getParameterValues("chkPgtoID");

				for (int i = 0; i < pgtosIDs.length; i++) {
					int pgtoID = Integer.parseInt(pgtosIDs[i]);
					interfaceCartaoMgt.removerPagamento(pgtoID);
				}
			}

			//Mostrando um pagamento ou todos, dependendo da operacao requisitada
			if (operacao.equals("buscar")){
				return buscarPagamento(Integer.parseInt(request.getParameter("pgtoID")));			
			}
			else {
				return buscarPagamentos();
			}		
		}
		else{
			return mostrarForm(request.getParameter("pgtoID"));
		}
	}

	public IPagtoCartaoMgt getInterfacePagtoCartaoMgt() {
		return interfacePagtoCartaoMgt;
	}

	public void setInterfacePagtoCartaoMgt(IPagtoCartaoMgt interfacePagtoCartaoMgt) {
		this.interfacePagtoCartaoMgt = interfacePagtoCartaoMgt;
	}
	
}
