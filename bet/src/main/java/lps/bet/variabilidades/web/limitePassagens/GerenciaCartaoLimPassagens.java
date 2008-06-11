package lps.bet.variabilidades.web.limitePassagens;

import lps.bet.basico.passageiroMgr.IPassageiroMgt;
import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.web.ControladorBet;
import lps.bet.basico.web.controlGerencia.UtilsGerencia;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.variabilidades.limitePassagensMgr.ILimitePassagensMgt;
import lps.bet.variabilidades.tiposDados.CartaoLimPassagens;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GerenciaCartaoLimPassagens extends ControladorBet {

    ICartaoMgt interfaceCartaoMgt;
    IPassageiroMgt interfacePassageiroMgt;
    ILimitePassagensMgt interfaceLimitePassagensMgt;

    SimpleDateFormat sdf;

    public GerenciaCartaoLimPassagens() {
    	sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    protected void criarCartao(Cartao cartao) {
        interfaceCartaoMgt.criarCartao(cartao);
    }

    protected ModelAndView buscarCartoes() {
        List cartoes = interfaceCartaoMgt.buscarCartoes();

        Calendar data = Calendar.getInstance();

        ModelAndView mav = new ModelAndView("gerenciaCartao");
        //mav.addObject("cartoes", cartoes);
        mav.addObject("sdf", sdf);
        mav.addObject("data", data);
        
        Collection cartoesLimPassagens = new ArrayList();
        Collection cartoesSemLimite = new ArrayList();
        
        for (Iterator iterator = cartoes.iterator(); iterator.hasNext();) {
			Cartao cartao = (Cartao) iterator.next();
			CartaoLimPassagens cartaoLimite = interfaceLimitePassagensMgt.buscarCartaoLimPassagens(cartao.getCartaoID());
			if (cartaoLimite == null){
				cartoesSemLimite.add(cartao);
			}
			else{
				cartoesLimPassagens.add(cartaoLimite);	
			}				
		}
        mav.addObject("cartoesLimPassagens", cartoesLimPassagens);
        mav.addObject("cartoesSemLimite", cartoesSemLimite);
        
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
            List cartoesLimPassagens = new ArrayList();
            CartaoLimPassagens cartaoLimPassagens = interfaceLimitePassagensMgt.buscarCartaoLimPassagens(cartaoID);
            cartoesLimPassagens.add(cartaoLimPassagens);
            mav.addObject("cartoesLimPassagens", cartoesLimPassagens);
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
                mav.addObject("mensagem", "Não há tipos compatíveis disponíveis.");
            mav.addObject("passageiro", passageiro);

        } else {
            mav.addObject("operacao", "alterar");
            mav.addObject("nomeOperacao", "Alterar");
            cartao = interfaceCartaoMgt
                    .buscarCartao(Integer.parseInt(cartaoID));
            tipos = interfaceCartaoMgt.buscarTiposPermitidos(cartao
                    .getPassageiro(), cartao.getTipoPassageiro());
            // Na alteração pode-se optar por não mudar o tipo, então ele tb
            // deve ser uma opção:
            //tipos.add(cartao.getTipoPassageiro());
            dtAquisicao = cartao.getDtAquisicao();
            dtValidade = cartao.getDtValidade();
        }
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

        // Operação de Criação
        if (request.getParameter("cartaoID") == null) {
            cartao = new Cartao();
            cartao.setDtAquisicao(Calendar.getInstance());
        }
        // Senão precisa buscar
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
            // Quando é chamado pela primeira vez a URL não possui o parâmetro
            // 'operacao'
            if (operacao == null)
                return buscarCartoes();

            if (operacao.equals("criar")) {
                criarCartao(montarCartao(request));
            } else if (operacao.equals("alterar")) {
                alterarCartao(montarCartao(request));
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

	public ILimitePassagensMgt getInterfaceLimitePassagensMgt() {
		return interfaceLimitePassagensMgt;
	}

	public void setInterfaceLimitePassagensMgt(
			ILimitePassagensMgt interfaceLimitePassagensMgt) {
		this.interfaceLimitePassagensMgt = interfaceLimitePassagensMgt;
	}  
    
}
