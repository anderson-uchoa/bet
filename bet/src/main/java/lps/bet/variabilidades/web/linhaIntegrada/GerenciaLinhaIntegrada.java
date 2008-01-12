package lps.bet.variabilidades.web.linhaIntegrada;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.web.ControladorBet;
import lps.bet.variabilidades.linhaIntegradaMgr.ILinhaIntegradaMgt;
import lps.bet.variabilidades.tiposDados.LinhaIntegrada;

import org.springframework.web.servlet.ModelAndView;

public class GerenciaLinhaIntegrada extends ControladorBet {
	
	ILinhaMgt interfaceLinhaMgt;
	ILinhaIntegradaMgt interfaceLinhaIntegradaMgt;
	
	protected void criarLinhaIntegrada(LinhaIntegrada linhaIntegrada) {
        interfaceLinhaIntegradaMgt.criarLinhaIntegrada(linhaIntegrada);
    }

    protected ModelAndView buscarLinhasIntegracao() {
        List linhasIntegracao = interfaceLinhaIntegradaMgt.buscarLinhasIntegracao();

        ModelAndView mav = new ModelAndView("gerenciaLinhaIntegrada");
        mav.addObject("linhasIntegracao", linhasIntegracao);
        return mav;
    }

    protected ModelAndView buscarLinhaIntegrada(int integracaoID) {
        LinhaIntegrada integracao = interfaceLinhaIntegradaMgt.buscarIntegracao(integracaoID);
        ModelAndView mav = new ModelAndView("gerenciaLinhaIntegrada");
        if (integracao == null) {
            mav.addObject("mensagem", "Linha de Integração não encontrada.");
        } else {
            List linhasIntegradas = new ArrayList();
            linhasIntegradas.add(integracao);
            mav.addObject("linhasIntegradas", linhasIntegradas);
        }
        return mav;
    }

    protected void alterarLinhaIntegrada(LinhaIntegrada linhaIntegrada) {
        interfaceLinhaIntegradaMgt.alterarLinhaIntegrada(linhaIntegrada);
    }

    protected LinhaIntegrada montarIntegracao(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        LinhaIntegrada linhaIntegrada;

        //Operação de Criação
        if (request.getParameter("integracaoID") == null) {
            linhaIntegrada = new LinhaIntegrada();
        }
        //Senão precisa buscar
        else {
            linhaIntegrada = interfaceLinhaIntegradaMgt.buscarIntegracao(Integer.parseInt(request.getParameter("integracaoID")));           
        }

        Linha linhaOriginal = interfaceLinhaMgt.buscarLinha(request.getParameter("nomeLinhaOriginal"));
        linhaIntegrada.setLinhaOriginal(linhaOriginal);
        
        Linha linhaASerIntegrada = interfaceLinhaMgt.buscarLinha(request.getParameter("nomeLinhaIntegrada"));
        linhaIntegrada.setLinhaIntegrada(linhaASerIntegrada);

        return linhaIntegrada;
    }

    protected ModelAndView mostrarForm(String integracaoID) {

        ModelAndView mav = new ModelAndView("formLinhaIntegrada");
        mav.addObject("integracaoID", integracaoID);

        LinhaIntegrada linhaIntegrada = null;

        if (integracaoID == null) {
            mav.addObject("operacao", "criar");
            mav.addObject("nomeOperacao", "Criar");
        } else {
            mav.addObject("operacao", "alterar");
            mav.addObject("nomeOperacao", "Alterar");
            linhaIntegrada = interfaceLinhaIntegradaMgt.buscarIntegracao(Integer.parseInt(integracaoID));
        }
        mav.addObject("linhaIntegrada", linhaIntegrada);
        
        List linhas = interfaceLinhaMgt.buscarLinhas();
        mav.addObject("linhas", linhas);
        
        return mav;
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String operacao = request.getParameter("operacao");

        if (request.getServletPath().equals("/gerenciaLinhaIntegrada.html")) {
            //Quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
            if (operacao == null)
                return buscarLinhasIntegracao();

            if (operacao.equals("criar")) {
                criarLinhaIntegrada(montarIntegracao(request));
            } else if (operacao.equals("alterar")) {
                alterarLinhaIntegrada(montarIntegracao(request));
            }

            //Código do if a ser tirado posteriormente (não existe remover no Linha):
            if (operacao.equals("remover")) {
                String[] integracoesIDs = request.getParameterValues("chkIntegracaoID");

                for (int i = 0; i < integracoesIDs.length; i++) {
                    int integracaoID = Integer.parseInt(integracoesIDs[i]);
                    interfaceLinhaIntegradaMgt.removerLinhaIntegrada(integracaoID);
                }
            }

            //Mostrando uma linha ou todas, dependendo da operacao requisitada
            if (operacao.equals("buscar")) {
                return buscarLinhaIntegrada(Integer.parseInt(request.getParameter("integracaoID")));
            } else {
                return buscarLinhasIntegracao();
            }
        } else {
            return mostrarForm(request.getParameter("integracaoID"));
        }
    }

	public ILinhaMgt getInterfaceLinhaMgt() {
		return interfaceLinhaMgt;
	}

	public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
		this.interfaceLinhaMgt = interfaceLinhaMgt;
	}

	public ILinhaIntegradaMgt getInterfaceLinhaIntegradaMgt() {
		return interfaceLinhaIntegradaMgt;
	}

	public void setInterfaceLinhaIntegradaMgt(
			ILinhaIntegradaMgt interfaceLinhaIntegradaMgt) {
		this.interfaceLinhaIntegradaMgt = interfaceLinhaIntegradaMgt;
	}   

}
