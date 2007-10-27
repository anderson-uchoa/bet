package lps.bet.basico.web.controlGerencia.viacao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.tiposDados.SistViarioUrbano;
import lps.bet.basico.tiposDados.Tarifa;
import lps.bet.basico.viacaoMgr.IViacaoMgt;
import lps.bet.basico.web.ControladorBet;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaTarifa extends ControladorBet{
	
	IViacaoMgt interfaceViacaoMgt;
	SimpleDateFormat sdf;
	
	public GerenciaTarifa(){
		sdf = new SimpleDateFormat();
		sdf.applyPattern("dd/MM/yyyy");
	}

	protected void criarTarifa(Tarifa tarifa){
		interfaceViacaoMgt.criarTarifa(tarifa);
	}
	
	protected ModelAndView buscarTarifas(){
		List tarifas = interfaceViacaoMgt.buscarTarifas();
		
		Calendar data = Calendar.getInstance();

		ModelAndView mav = new ModelAndView("gerenciaTarifa");
		mav.addObject("tarifas", tarifas);
		mav.addObject("sdf", sdf);
		mav.addObject("data",data);
		return mav;
	}
	
	protected ModelAndView buscarTarifa(int tarifaID){
		Tarifa tarifa = interfaceViacaoMgt.buscarTarifa(tarifaID);
		ModelAndView mav = new ModelAndView("gerenciaTarifa");
		if (tarifa == null){
			mav.addObject("mensagem", "Tarifa não encontrada.");
		}
		else{
			List tarifas = new ArrayList();
			tarifas.add(tarifa);
			mav.addObject("tarifas", tarifas);
			mav.addObject("sdf", sdf);			
		}
		return mav;
	}
 
	protected ModelAndView mostrarForm(String tarifaID){

		ModelAndView mav = new ModelAndView("formTarifa");
		mav.addObject("tarifaID",tarifaID);
		
		mav.addObject("sdf", sdf);
		
		Tarifa tarifa = null;
		Calendar data;
		
		if (tarifaID == null){
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");						
			data = Calendar.getInstance();
		}
		else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			tarifa = interfaceViacaoMgt.buscarTarifa(Integer.parseInt(tarifaID));
			data = tarifa.getDtAtualizacao();
		}
		mav.addObject("tarifa",tarifa);		
		mav.addObject("data",data);
		return mav;		
	}

	
	protected void alterarTarifa(Tarifa tarifa){
		interfaceViacaoMgt.alterarTarifa(tarifa);
	}

	protected Tarifa montarTarifa(HttpServletRequest request){
		String operacao = request.getParameter("operacao");
		Tarifa tarifa;
		
		//Operação de Criação
		if (request.getParameter("tarifaID")==null){
			tarifa = new Tarifa();
			tarifa.setDtAtualizacao(Calendar.getInstance());
		}
		//Senão precisa buscar
		else {
			tarifa = interfaceViacaoMgt.buscarTarifa(Integer.parseInt(request.getParameter("tarifaID")));
		}
		
		String strValorTarifa = request.getParameter("valorTarifa");
		float valorTarifa = strValorTarifa.trim().matches("[0-9]*\\.?[0-9]+") ? Float.parseFloat(strValorTarifa) : 0;
		SistViarioUrbano sistViarioUrbano = interfaceViacaoMgt.buscarSistViarioUrbano();

		tarifa.setValorTarifa(valorTarifa);
		tarifa.setNomeTarifa(request.getParameter("nomeTarifa"));
		tarifa.setSistViarioUrbano(sistViarioUrbano);
				
		return tarifa;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao");
		
		if (request.getServletPath().equals("/gerenciaTarifa.html")){
			//Quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
			if (operacao == null)
				return buscarTarifas();
			
			if (operacao.equals("criar")){
				criarTarifa(montarTarifa(request));
			}
			
			else if (operacao.equals("alterar")){
				alterarTarifa(montarTarifa(request));
			}
			
			//Código do if a ser tirado posteriormente (não existe remover no Tarifa):
			if (operacao.equals("remover")){
				String[] tarifasIDs = request.getParameterValues("chkTarifaID");
				for (int i = 0; i < tarifasIDs.length; i++) {
					int tarifaID = Integer.parseInt(tarifasIDs[i]);
					interfaceViacaoMgt.removerTarifa(tarifaID);
				}
			}
			
			// mostrando uma tarifa ou todas elas, dependendo da operacao requisitada
			if (operacao.equals("buscar")){
				return buscarTarifa(Integer.parseInt(request.getParameter("tarifaID")));			
			}
			else {
				return buscarTarifas();
			}			
		}
		else {
			return mostrarForm(request.getParameter("tarifaID"));
		}
	}


	public IViacaoMgt getInterfaceViacaoMgt() {
		return interfaceViacaoMgt;
	}

	public void setInterfaceViacaoMgt(IViacaoMgt interfaceViacaoMgt) {
		this.interfaceViacaoMgt = interfaceViacaoMgt;
	}
		
}
