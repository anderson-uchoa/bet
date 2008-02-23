package lps.bet.variabilidades.web.numCartoes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.tiposDados.SistViarioUrbano;
import lps.bet.basico.viacaoMgr.IViacaoMgt;
import lps.bet.basico.web.ControladorBet;
import lps.bet.variabilidades.numCartoesMgr.INumCartoesMgt;
import lps.bet.variabilidades.numViagensMgr.INumViagensMgt;
import lps.bet.variabilidades.tempoMgr.ITempoMgt;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaSistViarioNumCartoes extends ControladorBet{	

		IViacaoMgt interfaceViacaoMgt;
		INumCartoesMgt interfaceNumCartoesMgt;
		
		protected ModelAndView buscarSistViarioUrbano(){
			ModelAndView mav = new ModelAndView("gerenciaSistViarioUrbano");
			SistViarioUrbano sistema = interfaceViacaoMgt.buscarSistViarioUrbano();
			mav.addObject("sistema", sistema);
		
			Integer limiteCartoes = interfaceNumCartoesMgt.buscarLimiteCartoes(); 
			
			mav.addObject("limiteCartoes", limiteCartoes);

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
				}
			}
			else if (operacao.equals("alterar")){
				alterarSistViarioUrbano(montarSistViarioUrbano(request));
				interfaceNumCartoesMgt.alterarLimiteCartoes(Integer.parseInt(request.getParameter("limiteCartoes")));
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

}


