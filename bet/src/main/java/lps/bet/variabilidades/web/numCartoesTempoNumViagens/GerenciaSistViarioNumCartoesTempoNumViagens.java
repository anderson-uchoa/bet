package lps.bet.variabilidades.web.numCartoesTempoNumViagens;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.tiposDados.SistViarioUrbano;
import lps.bet.basico.viacaoMgr.IViacaoMgt;
import lps.bet.basico.web.ControladorBet;
import lps.bet.variabilidades.numCartoesMgr.INumCartoesMgt;
import lps.bet.variabilidades.numViagensMgr.INumViagensMgt;
import lps.bet.variabilidades.tempoMgr.ITempoMgt;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaSistViarioNumCartoesTempoNumViagens extends ControladorBet{	

		IViacaoMgt interfaceViacaoMgt;
		INumCartoesMgt interfaceNumCartoesMgt;
		INumViagensMgt interfaceNumViagensMgt;
		ITempoMgt interfaceTempoMgt;
		
		protected ModelAndView buscarSistViarioUrbano(){
			ModelAndView mav = new ModelAndView("gerenciaSistViarioUrbano");
			SistViarioUrbano sistema = interfaceViacaoMgt.buscarSistViarioUrbano();
			mav.addObject("sistema", sistema);
		
			Integer limiteCartoes = interfaceNumCartoesMgt.buscarLimiteCartoes(); 
			Integer numViagens = interfaceNumViagensMgt.buscarMaxNumViagens();
			Integer tempoMaxIntegracao = interfaceTempoMgt.buscarTempo();
			
			mav.addObject("limiteCartoes", limiteCartoes);
			mav.addObject("numViagens", numViagens);
			mav.addObject("tempoMaxIntegracao", tempoMaxIntegracao);
			
			return mav;
		}

		protected boolean criarSistViarioUrbano(SistViarioUrbano sistema){
			return interfaceViacaoMgt.criarSistViarioUrbano(sistema);
		}
		
		protected void alterarSistViarioUrbano(SistViarioUrbano sistema){
			interfaceViacaoMgt.alterarSistViarioUrbano(sistema);
		}
		
		protected SistViarioUrbano montarSistViarioUrbano(HttpServletRequest request) {
			String operacao = request.getParameter("operacao");
			SistViarioUrbano sistema;

			//Operação de Criação
			if (request.getParameter("viacaoID")==null){
				sistema = new SistViarioUrbano();
			}
			
			//Senão precisa buscar
			else{
				sistema = interfaceViacaoMgt.buscarSistViarioUrbano();
			}

			sistema.setEmpresaViaria(interfaceViacaoMgt.buscarEmpresaViaria());
			sistema.setNomeCidade(request.getParameter("nomeCidade"));
			
			return sistema;
		}	

		protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String operacao = request.getParameter("operacao");
				
			// quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
			if (operacao == null)
				return buscarSistViarioUrbano();
			
			if (operacao.equals("criar")){
				if (criarSistViarioUrbano(montarSistViarioUrbano(request))){
					interfaceNumCartoesMgt.criarLimiteCartoes(Integer.parseInt(request.getParameter("limiteCartoes")));
					interfaceNumViagensMgt.criarMaxNumViagens(Integer.parseInt(request.getParameter("numViagens")));
					interfaceTempoMgt.criarTempo(Integer.parseInt(request.getParameter("tempoMaxIntegracao")));
				}
			}
			else if (operacao.equals("alterar")){
				alterarSistViarioUrbano(montarSistViarioUrbano(request));
				interfaceNumCartoesMgt.alterarLimiteCartoes(Integer.parseInt(request.getParameter("limiteCartoes")));
				interfaceNumViagensMgt.alterarMaxNumViagens(Integer.parseInt(request.getParameter("numViagens")));
				interfaceTempoMgt.alterarTempo(Integer.parseInt(request.getParameter("tempoMaxIntegracao")));
			}

			return buscarSistViarioUrbano();

		}

		public IViacaoMgt getInterfaceViacaoMgt() {
			return interfaceViacaoMgt;
		}

		public void setInterfaceViacaoMgt(IViacaoMgt interfaceViacaoMgt) {
			this.interfaceViacaoMgt = interfaceViacaoMgt;
		}
				
		public INumCartoesMgt getInterfaceNumCartoesMgt() {
			return interfaceNumCartoesMgt;
		}

		public void setInterfaceNumCartoesMgt(INumCartoesMgt interfaceNumCartoesMgt) {
			this.interfaceNumCartoesMgt = interfaceNumCartoesMgt;
		}

		public INumViagensMgt getInterfaceNumViagensMgt() {
			return interfaceNumViagensMgt;
		}

		public void setInterfaceNumViagensMgt(INumViagensMgt interfaceNumViagensMgt) {
			this.interfaceNumViagensMgt = interfaceNumViagensMgt;
		}

		public ITempoMgt getInterfaceTempoMgt() {
			return interfaceTempoMgt;
		}

		public void setInterfaceTempoMgt(ITempoMgt interfaceTempoMgt) {
			this.interfaceTempoMgt = interfaceTempoMgt;
		}
}


