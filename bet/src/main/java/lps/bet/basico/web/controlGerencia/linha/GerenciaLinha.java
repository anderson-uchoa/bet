package lps.bet.basico.web.controlGerencia.linha;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.SistViarioUrbano;
import lps.bet.basico.viacaoMgr.IViacaoMgt;
import lps.bet.basico.web.ControladorBet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GerenciaLinha extends ControladorBet {

    ILinhaMgt interfaceLinhaMgt;
    IViacaoMgt interfaceViacaoMgt;

    protected void criarLinha(Linha linha) {
        interfaceLinhaMgt.criarLinha(linha);
    }

    protected ModelAndView buscarLinhas() {
        List linhas = interfaceLinhaMgt.buscarLinhas();

        ModelAndView mav = new ModelAndView("gerenciaLinha");
        mav.addObject("linhas", linhas);
        return mav;
    }

    protected ModelAndView buscarLinha(int linhaID) {
        Linha linha = interfaceLinhaMgt.buscarLinha(linhaID);
        ModelAndView mav = new ModelAndView("gerenciaLinha");
        if (linha == null) {
            mav.addObject("mensagem", "Linha não encontrada.");
        } else {
            List linhas = new ArrayList();
            linhas.add(linha);
            mav.addObject("linhas", linhas);
        }
        return mav;
    }

    protected void alterarLinha(Linha linha) {
        interfaceLinhaMgt.alterarLinha(linha);
    }

    protected Linha montarLinha(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Linha linha;

        //Operação de Criação
        if (request.getParameter("linhaID") == null) {
            linha = new Linha();
        }
        //Senão precisa buscar
        else {
            linha = interfaceLinhaMgt.buscarLinha(Integer.parseInt(request.getParameter("linhaID")));
            System.out.println(request.getParameter("linhaID"));
            System.out.println(linha == null);
        }

        SistViarioUrbano sistema = interfaceViacaoMgt.buscarSistViarioUrbano();

        linha.setNomeLinha(request.getParameter("nomeLinha"));
        linha.setPontoSaida(request.getParameter("pontoSaida"));
        linha.setPontoChegada(request.getParameter("pontoChegada"));
        linha.setSistViarioUrbano(sistema);

        return linha;
    }

    protected ModelAndView mostrarForm(String linhaID) {

        ModelAndView mav = new ModelAndView("formLinha");
        mav.addObject("linhaID", linhaID);

        Linha linha = null;

        if (linhaID == null) {
            mav.addObject("operacao", "criar");
            mav.addObject("nomeOperacao", "Criar");
        } else {
            mav.addObject("operacao", "alterar");
            mav.addObject("nomeOperacao", "Alterar");
            linha = interfaceLinhaMgt.buscarLinha(Integer.parseInt(linhaID));
        }
        mav.addObject("linha", linha);
        return mav;
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String operacao = request.getParameter("operacao");

        if (request.getServletPath().equals("/gerenciaLinha.html")) {
            //Quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
            if (operacao == null)
                return buscarLinhas();

            if (operacao.equals("criar")) {
                criarLinha(montarLinha(request));
            } else if (operacao.equals("alterar")) {
                alterarLinha(montarLinha(request));
            }

            //Código do if a ser tirado posteriormente (não existe remover no Linha):
            if (operacao.equals("remover")) {
                String[] linhasIDs = request.getParameterValues("chkLinhaID");

                for (int i = 0; i < linhasIDs.length; i++) {
                    int linhaID = Integer.parseInt(linhasIDs[i]);
                    interfaceLinhaMgt.removerLinha(linhaID);
                }
            }

            //Mostrando uma linha ou todas, dependendo da operacao requisitada
            if (operacao.equals("buscar")) {
                return buscarLinha(Integer.parseInt(request.getParameter("linhaID")));
            } else {
                return buscarLinhas();
            }
        } else {
            return mostrarForm(request.getParameter("linhaID"));
        }
    }

    public ILinhaMgt getInterfaceLinhaMgt() {
        return interfaceLinhaMgt;
    }

    public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
        this.interfaceLinhaMgt = interfaceLinhaMgt;
    }

    public IViacaoMgt getInterfaceViacaoMgt() {
        return interfaceViacaoMgt;
    }

    public void setInterfaceViacaoMgt(IViacaoMgt interfaceViacaoMgt) {
        this.interfaceViacaoMgt = interfaceViacaoMgt;
    }
}
