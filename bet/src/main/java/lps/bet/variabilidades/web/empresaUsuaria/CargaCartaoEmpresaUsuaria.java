package lps.bet.variabilidades.web.empresaUsuaria;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.web.ControladorBet;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.variabilidades.empresaUsuariaMgr.IEmpresaUsuariaMgt;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

public class CargaCartaoEmpresaUsuaria extends ControladorBet{

    ICartaoMgt interfaceCartaoMgt;
    IEmpresaUsuariaMgt interfaceEmpresaUsuariaMgt;    

    SimpleDateFormat sdf;

    public CargaCartaoEmpresaUsuaria() {
    	sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void solicitarCarga(int cartaoID, float valor) {
        interfaceCartaoMgt.carregarCartao(cartaoID, valor);
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
            return buscarCartoes();
        }
              
        if (operacao.equals("carregar")) {

        	//Carga de apenas um cartão
            if (!request.getParameter("cartaoID").equals("")){
            	int cartaoID = Integer.parseInt(request.getParameter("cartaoID"));

                String strValor = request.getParameter("valor");
                float valor = strValor.trim().matches("[0-9]*\\.?[0-9]+") ? Float.parseFloat(strValor) : 0;

                solicitarCarga(cartaoID, valor);	
            }
            //Carga em massa
            else{
            	Collection<Passageiro> passageiros = interfaceEmpresaUsuariaMgt.buscarPassageirosPorEmpresa(request.getParameter("nomeFantasia"));
            	TipoPassageiro tipo = interfaceCartaoMgt.buscarTipoPassageiro(request.getParameter("nomeTipo"));
            	String strValorEmpresa = request.getParameter("valorEmpresa");
                float valorEmpresa = strValorEmpresa.trim().matches("[0-9]*\\.?[0-9]+") ? Float.parseFloat(strValorEmpresa) : 0;
                
            	for (Iterator j = passageiros.iterator(); j.hasNext();) {
					Passageiro passag = (Passageiro) j.next();
					Collection<Cartao> cartoes = passag.getCartoes();
					
					for (Iterator i = cartoes.iterator(); i.hasNext();) {
						Cartao cartaoPassageiro = (Cartao) i.next();
					
						if (tipo.equals(cartaoPassageiro.getTipoPassageiro())){
							solicitarCarga(cartaoPassageiro.getCartaoID(), valorEmpresa);
						}
					}
				}
            }
        }

        //Mostrando um cartão ou todos, dependendo da operacao requisitada
        if (operacao.equals("buscar")) {
            return buscarCartao(Integer.parseInt(request.getParameter("cartaoID")));
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

	public IEmpresaUsuariaMgt getInterfaceEmpresaUsuariaMgt() {
		return interfaceEmpresaUsuariaMgt;
	}

	public void setInterfaceEmpresaUsuariaMgt(
			IEmpresaUsuariaMgt interfaceEmpresaUsuariaMgt) {
		this.interfaceEmpresaUsuariaMgt = interfaceEmpresaUsuariaMgt;
	}
}
