package lps.bet.variabilidades.web.limitePassagens;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.passageiroMgr.IPassageiroMgt;
import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.web.ControladorBet;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.variabilidades.limitePassagensMgr.ILimitePassagensMgt;
import lps.bet.variabilidades.tiposDados.CartaoLimPassagens;

import org.springframework.web.servlet.ModelAndView;

public class AcessoBasicoLimPassagens extends ControladorBet {

    ICartaoMgt interfaceCartaoMgt;
    IPassageiroMgt interfacePassageiroMgt;
    ILimitePassagensMgt interfaceLimitePassagensMgt;

    SimpleDateFormat sdf;

    public AcessoBasicoLimPassagens() {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    protected ModelAndView buscarCartoesPorPassageiro(long cpf) {
        Collection cartoes = interfacePassageiroMgt.buscarCartoesPorPassageiro(cpf);
        ModelAndView mav = new ModelAndView("acessoBasico");

        if (cartoes == null) {
            mav.addObject("mensagemSemCartao", "Passageiro não possui cartão.");
        } else {
            Calendar data = Calendar.getInstance();
            mav.addObject("cartoes", cartoes);
            mav.addObject("sdf", sdf);
            mav.addObject("data", data);
        }
        return mav;
    }

    protected ModelAndView buscarCartoes() {
        List cartoes = interfaceCartaoMgt.buscarCartoes();
        Collection cartoesLimPassagens = interfaceLimitePassagensMgt.buscarTodosCartoes();
        
        ModelAndView mav = new ModelAndView("acessoBasico");
        mav.addObject("cartoes", cartoes);
        mav.addObject("cartoesLimPassagens", cartoesLimPassagens);
        mav.addObject("sdf", sdf);
        return mav;
    }

    protected ModelAndView buscarCartao(int cartaoID) {
        Cartao cartao = interfaceCartaoMgt.buscarCartao(cartaoID);
        CartaoLimPassagens cartaoLimPassagens = interfaceLimitePassagensMgt.buscarCartaoLimPassagens(cartaoID);
        
        ModelAndView mav = new ModelAndView("acessoBasico");
        mav.addObject("cartao", cartao);
        mav.addObject("cartaoLimPassagens", cartaoLimPassagens);
        mav.addObject("sdf", sdf);
        return mav;
    }

    protected ModelAndView buscarPassageiro(long cpf) {
        Passageiro passageiro = interfacePassageiroMgt.buscarPassageiro(cpf);
        ModelAndView mav = new ModelAndView("acessoBasico");

        if (passageiro == null) {
            mav.addObject("mensagem", "Passageiro não encontrado.");
        } else {
            mav.addObject("passageiro", passageiro);
            mav.addObject("sdf", sdf);
            mav.addObject("acessarPassageiro", "Dados do Passageiro");
        }

        Collection cartoes = interfacePassageiroMgt.buscarCartoesPorPassageiro(cpf);

        if (cartoes == null) {
            mav.addObject("mensagemSemCartao", "Passageiro não possui cartão.");
        } else {
            Calendar data = Calendar.getInstance();
            mav.addObject("cartoes", cartoes);
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
        }
        return mav;
    }

    protected ModelAndView buscarCartaoPorID(int cartaoID, long cpf) {
        Cartao cartaoBuscado = interfaceCartaoMgt.buscarCartao(cartaoID);
        Collection cartoes = interfacePassageiroMgt.buscarCartoesPorPassageiro(cpf);
        ModelAndView mav = new ModelAndView("acessoBasico");

        for (Iterator iterator = cartoes.iterator(); iterator.hasNext();) {
            Cartao cartao = (Cartao) iterator.next();
            if (cartao == cartaoBuscado) {
                mav.addObject("cartao", cartao);
                mav.addObject("sdf", sdf);
                return mav;
            }
        }
        mav.addObject("mensagemCartao", "Cartão não pertence ao passageiro.");
        return mav;
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String operacao = request.getParameter("operacao");

        if (operacao == null) {
            return buscarCartoes();
        }

        if (operacao.equals("acessarPassageiro")) {
            return buscarPassageiro(Long.parseLong(request.getParameter("cpf").trim()));
        }

        //Mostrando um cartão ou todos, dependendo da operacao requisitada
        if (operacao.equals("buscar")) {
            return buscarCartaoPorID(Integer.parseInt(request.getParameter("cartaoID").trim()), Long.parseLong(request.getParameter("cpf").trim()));
        } else {
            return buscarCartoes();
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
