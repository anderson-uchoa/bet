package lps.bet.basico.web.relatorios;


public class RelatorioUsuario /*extends ControladorBet*/{
	
/*	ILinhaMgt interfaceLinhaMgt;
	
	protected ModelAndView buscarUsuarioDiaAtual(){	
		Collection<Corrida> corridasDoDia = interfaceLinhaMgt.buscarCorridasDoDia();
	}
	
	protected ModelAndView buscarCartoes() {
        List cartoes = interfaceCartaoMgt.buscarCartoes();

        Calendar data = Calendar.getInstance();

        ModelAndView mav = new ModelAndView("cargaCartao");
        mav.addObject("cartoes", cartoes);
        mav.addObject("sdf", sdf);
        mav.addObject("data", data);
        
        List empresasUsuarias = interfaceEmpresaUsuariaMgt.buscarEmpresasUsuarias();
        List tipos = interfaceCartaoMgt.buscarTiposPassageiros();
        
        mav.addObject("empresasUsuarias", empresasUsuarias);
        mav.addObject("tipos", tipos);
        
        return mav;
    }

    protected ModelAndView buscarCartao(int cartaoID) {
        Cartao cartao = interfaceCartaoMgt.buscarCartao(cartaoID);
        ModelAndView mav = new ModelAndView("cargaCartao");
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

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String operacao = request.getParameter("operacao");
        Cartao cartao;

        if (operacao == null) {
            return buscarUsuarioDiaAtual();
        }
              
        if (operacao.equals("buscar")) {

        	   	Collection<Passageiro> passageiros = interfaceEmpresaUsuariaMgt.buscarPassageirosPorEmpresa(request.getParameter("nomeFantasia"));
            	TipoPassageiro tipo = interfaceCartaoMgt.buscarTipoPassageiro(request.getParameter("nomeTipo"));
            	String strValorEmpresa = request.getParameter("valorEmpresa");
                float valorEmpresa = strValorEmpresa.trim().matches("[0-9]*\\.?[0-9]+") ? Float.parseFloat(strValorEmpresa) : 0;
                            	
            }
        }

        //Mostrando um cartão ou todos, dependendo da operacao requisitada
        if (operacao.equals("buscar")) {
            return buscarCartao(Integer.parseInt(request.getParameter("cartaoID")));
        } else {
            return buscarCartoes();
        }
		public ILinhaMgt getInterfaceLinhaMgt() {
			return interfaceLinhaMgt;
		}

		public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
			this.interfaceLinhaMgt = interfaceLinhaMgt;
		}
    }



*/
}
