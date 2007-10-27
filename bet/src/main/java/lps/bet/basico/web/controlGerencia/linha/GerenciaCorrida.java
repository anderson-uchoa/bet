package lps.bet.basico.web.controlGerencia.linha;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.tiposDados.Corrida;
import lps.bet.basico.web.ControladorBet;
import lps.bet.basico.web.controlGerencia.linha.projecao.IteradorProjecao;
import lps.bet.basico.web.controlGerencia.linha.projecao.IteradorProjecaoDiaria;
import lps.bet.basico.web.controlGerencia.linha.projecao.IteradorProjecaoDiasUteis;
import lps.bet.basico.web.controlGerencia.linha.projecao.IteradorProjecaoFDS;
import lps.bet.basico.web.controlGerencia.linha.projecao.IteradorProjecaoSemanal;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaCorrida extends ControladorBet{

	ILinhaMgt interfaceLinhaMgt;

	SimpleDateFormat sdfData;
	SimpleDateFormat sdfHora;
	
	public GerenciaCorrida(){
		sdfData = new SimpleDateFormat();
		sdfData.applyPattern("dd/MM/yyyy");
		sdfHora = new SimpleDateFormat();
		sdfHora.applyPattern("hh:mm");
	}
	
	protected List<Corrida> montarCorridas(HttpServletRequest request){
				
		String periodicidade = request.getParameter("periodicidade");
		String strHoraSaidaPrevista = request.getParameter("horaSaidaPrevista");
		String strHoraChegadaPrevista = request.getParameter("horaChegadaPrevista");
		String repeticaoCorrida = request.getParameter("repeticaoCorrida");
		String dtCorrida = request.getParameter("dtCorrida");
		String dtInicio = request.getParameter("dtInicio");
		String dtFim = request.getParameter("dtFim");
		
		IteradorProjecao iteradorSaida;
		IteradorProjecao iteradorChegada;
		
		if (repeticaoCorrida.equals("unica")){
			iteradorChegada = new IteradorProjecaoDiaria(dtCorrida, dtCorrida, strHoraChegadaPrevista);
			iteradorSaida = new IteradorProjecaoDiaria(dtCorrida, dtCorrida, strHoraSaidaPrevista);
		}
		else {
			if (periodicidade.equals("diaria")){
				iteradorChegada = new IteradorProjecaoDiaria(dtInicio, dtFim, strHoraChegadaPrevista);
				iteradorSaida = new IteradorProjecaoDiaria(dtInicio, dtFim, strHoraSaidaPrevista);				
			}
			else if (periodicidade.equals("semanal")){
				iteradorChegada = new IteradorProjecaoSemanal(dtInicio, dtFim, strHoraChegadaPrevista);
				iteradorSaida = new IteradorProjecaoSemanal(dtInicio, dtFim, strHoraSaidaPrevista);
			}
			else if (periodicidade.equals("uteis")){
				iteradorChegada = new IteradorProjecaoDiasUteis(dtInicio, dtFim, strHoraChegadaPrevista);
				iteradorSaida = new IteradorProjecaoDiasUteis(dtInicio, dtFim, strHoraSaidaPrevista);
			}
			else {
				iteradorChegada = new IteradorProjecaoFDS(dtInicio, dtFim, strHoraChegadaPrevista);
				iteradorSaida = new IteradorProjecaoFDS(dtInicio, dtFim, strHoraSaidaPrevista);
			}
		}
		
		
		List<Corrida> corridas = new ArrayList<Corrida>();
		while (iteradorSaida.hasNext()){
			Corrida corrida;
			
			//Operação de Criação
			if (request.getParameter("corridaID")==null){
				corrida = new Corrida();
			}
			//Senão precisa buscar
			else {
				corrida = interfaceLinhaMgt.buscarCorrida(Integer.parseInt(request.getParameter("corridaID")));
			}
			Calendar hora = (Calendar) iteradorSaida.next();
			if (hora != null){
				//Calendar horaSaidaPrevista = (Calendar) iteradorSaida.next();
				Calendar horaSaidaPrevista = hora;
				Calendar horaChegadaPrevista = (Calendar) iteradorChegada.next();
				corrida.setHoraSaidaPrevista(horaSaidaPrevista);
				corrida.setHoraChegadaPrevista(horaChegadaPrevista);
				corrida.setNumCorridaDia(1);
				corrida.setArrecadacao(0);
				corrida.setEncerrado(false);
				corrida.setSaida(false);
				corrida.setLinha(interfaceLinhaMgt.buscarLinha(request.getParameter("nomeLinha")));
				corrida.setOnibus(interfaceLinhaMgt.buscarOnibus(Integer.parseInt(request.getParameter("onibusID"))));
				
				corridas.add(corrida);
			}
		}
		return corridas;
	}
	
	protected void criarCorridas(List<Corrida> corridas){
		for (Corrida corrida : corridas) {
			interfaceLinhaMgt.criarCorrida(corrida);
		}
	}
	
	protected ModelAndView buscarCorridas(){
		List corridas = interfaceLinhaMgt.buscarCorridas();
		List todosOnibus = interfaceLinhaMgt.buscarTodosOnibus();
		List linhas = interfaceLinhaMgt.buscarLinhas();
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar data = Calendar.getInstance();

		ModelAndView mav = new ModelAndView("gerenciaCorrida");
		mav.addObject("corridas", corridas);
		mav.addObject("todosOnibus", todosOnibus);
		mav.addObject("linhas", linhas);
		mav.addObject("sdf", sdf);
		mav.addObject("data",data);
		return mav;
	}
	
	protected ModelAndView buscarCorrida(int corridaID){
		Corrida corrida = interfaceLinhaMgt.buscarCorrida(corridaID);
		ModelAndView mav = new ModelAndView("gerenciaCorrida");
		
		if (corrida == null){
			mav.addObject("mensagem", "Corrida não encontrada.");
		}
		else{
			List corridas = new ArrayList();
			corridas.add(corrida);
			
			SimpleDateFormat sdf = new SimpleDateFormat();		
			Calendar data = Calendar.getInstance();
			mav.addObject("corridas", corridas);
			mav.addObject("sdf", sdf);
			mav.addObject("data",data);			
		}
		return mav;
	}
	
	protected void alterarCorridas(List<Corrida> corridas){
		for (Corrida corrida : corridas) {
			interfaceLinhaMgt.alterarCorrida(corrida);
		}
	}
	
	protected ModelAndView mostrarForm(String corridaID){

		ModelAndView mav = new ModelAndView("formCorrida");
		mav.addObject("corridaID",corridaID);
		
		Corrida corrida = null;
		//Calendar data;
		
		if (corridaID == null){
			mav.addObject("operacao", "criar");
			mav.addObject("nomeOperacao", "Criar");						
			//data = Calendar.getInstance();
		}
		else {
			mav.addObject("operacao", "alterar");
			mav.addObject("nomeOperacao", "Alterar");
			corrida = interfaceLinhaMgt.buscarCorrida(Integer.parseInt(corridaID));
			//data = pagamento.getDtPgto();
		}
		mav.addObject("corrida",corrida);		
		//mav.addObject("data",data);
		List linhas = interfaceLinhaMgt.buscarLinhas();
		List todosOnibus = interfaceLinhaMgt.buscarTodosOnibus();
		mav.addObject("linhas", linhas);
		mav.addObject("todosOnibus", todosOnibus);
		mav.addObject("sdfData", sdfData);
		mav.addObject("sdfHora", sdfHora);
		return mav;		
	}
	

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String operacao = request.getParameter("operacao");
		
		if (request.getServletPath().equals("/gerenciaCorrida.html")){		
			//Quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
			if (operacao == null)
				return buscarCorridas();

			if (operacao.equals("criar")){
				criarCorridas(montarCorridas(request));
			}
			else if (operacao.equals("alterar")){
				alterarCorridas(montarCorridas(request));
			}

			if (operacao.equals("remover")){
				String[] corridasIDs = request.getParameterValues("chkCorridaID");
				for (int i = 0; i < corridasIDs.length; i++) {
					int corridaID = Integer.parseInt(corridasIDs[i]);
					interfaceLinhaMgt.removerCorrida(corridaID);
				}
			}

			if (operacao.equals("buscar")){
				return buscarCorrida(Integer.parseInt(request.getParameter("corridaID")));			
			}
			else {
				return buscarCorridas();
			}
		}
		else{
			return mostrarForm(request.getParameter("corridaID"));
		}


	}

	
	public ILinhaMgt getInterfaceLinhaMgt() {
		return interfaceLinhaMgt;
	}

	public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
		this.interfaceLinhaMgt = interfaceLinhaMgt;
	}
}
