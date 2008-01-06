package lps.bet.variabilidades.web.empresaUsuaria;

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
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.web.ControladorBet;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.variabilidades.empresaUsuariaMgr.IEmpresaUsuariaMgt;
import lps.bet.variabilidades.tiposDados.EmpresaUsuaria;

import org.springframework.web.servlet.ModelAndView;

public class AcessoBasicoEmpresaUsuaria extends ControladorBet {

    ICartaoMgt interfaceCartaoMgt;
    IPassageiroMgt interfacePassageiroMgt;
    IEmpresaUsuariaMgt interfaceEmpresaUsuariaMgt;

    SimpleDateFormat sdf;

    public AcessoBasicoEmpresaUsuaria() {
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

        ModelAndView mav = new ModelAndView("acessoBasico");
        mav.addObject("cartoes", cartoes);
        mav.addObject("sdf", sdf);
        return mav;
    }

    protected ModelAndView buscarCartao(int cartaoID) {
        Cartao cartao = interfaceCartaoMgt.buscarCartao(cartaoID);

        ModelAndView mav = new ModelAndView("acessoBasico");
        mav.addObject("cartao", cartao);
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
        }
        return mav;
    }
    
    protected ModelAndView buscarEmpresaUsuaria(String cnpj){
    	EmpresaUsuaria empresaUsuaria = interfaceEmpresaUsuariaMgt.buscarEmpresaUsuariaPorCNPJ(cnpj);
        ModelAndView mav = new ModelAndView("acessoBasico");

        if (empresaUsuaria == null) {
            mav.addObject("mensagem", "Empresa não encontrada.");
        } else {
            mav.addObject("empresaUsuaria", empresaUsuaria);
            mav.addObject("acessarPassageiro", "Dados da Empresa");
        }

        Collection<Passageiro> passageiros = empresaUsuaria.getPassageiros();

        if (passageiros == null) {
            mav.addObject("mensagemSemPassageiros", "Empresa não possui funcionários cadastrados.");
        } 
        else {            
            mav.addObject("passageiros", passageiros);
            List tipos = interfaceCartaoMgt.buscarTiposPassageiros();
            mav.addObject("tipos", tipos);
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
    
	protected ModelAndView mostrarForm(String nomeTipo, String cnpj){

		ModelAndView mav = new ModelAndView("formCartoesEmpresaUsuaria");
		
		TipoPassageiro tipo = interfaceCartaoMgt.buscarTipoPassageiro(nomeTipo);
		
		EmpresaUsuaria empresa = interfaceEmpresaUsuariaMgt.buscarEmpresaUsuariaPorCNPJ(cnpj);		
		Collection<Passageiro> passageiros = interfaceEmpresaUsuariaMgt.buscarPassageirosPorEmpresa(empresa.getNomeFantasia());
		Collection<Cartao> cartoesEmpresa = new ArrayList<Cartao>();
		
		for (Iterator j = passageiros.iterator(); j.hasNext();) {
			Passageiro passag = (Passageiro) j.next();
			Collection<Cartao> cartoes = passag.getCartoes();
			
			for (Iterator i = cartoes.iterator(); i.hasNext();) {
				Cartao cartaoPassageiro = (Cartao) i.next();
			
				if (tipo.equals(cartaoPassageiro.getTipoPassageiro())){
					cartoesEmpresa.add(cartaoPassageiro);
				}
			}
		}
		
		mav.addObject("cartoesEmpresa", cartoesEmpresa);
				
        Calendar data = Calendar.getInstance();   
        mav.addObject("sdf", sdf);
        mav.addObject("data", data);	
        
		return mav;		
	}

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String operacao = request.getParameter("operacao");

        if (request.getServletPath().equals("/acessoBasico.html")) {
			if (operacao == null) {
				return buscarCartoes();
			}
			if (operacao.equals("acessarPassageiro")) {
				if (request.getParameter("cpf") != "") {
					//Acesso básico de Passageiro
					return buscarPassageiro(Long.parseLong(request
							.getParameter("cpf").trim()));
				} else {
					//Acesso básico de Empresa
					return buscarEmpresaUsuaria(request.getParameter("cnpj")
							.trim());
				}
			}
			//Mostrando um cartão ou todos, dependendo da operacao requisitada
			if (operacao.equals("buscar")) {
				return buscarCartaoPorID(Integer.parseInt(request.getParameter(
						"cartaoID").trim()), Long.parseLong(request
						.getParameter("cpf").trim()));
			} else {
				return buscarCartoes();
			}
		}
        else {
        	return mostrarForm(request.getParameter("nomeTipo"), request.getParameter("cnpjEmpresa"));
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

	public IEmpresaUsuariaMgt getInterfaceEmpresaUsuariaMgt() {
		return interfaceEmpresaUsuariaMgt;
	}

	public void setInterfaceEmpresaUsuariaMgt(
			IEmpresaUsuariaMgt interfaceEmpresaUsuariaMgt) {
		this.interfaceEmpresaUsuariaMgt = interfaceEmpresaUsuariaMgt;
	}   
    
}
