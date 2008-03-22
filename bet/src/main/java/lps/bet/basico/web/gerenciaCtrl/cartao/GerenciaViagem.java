package lps.bet.basico.web.gerenciaCtrl.cartao;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Viagem;
import lps.bet.basico.web.ControladorBet;
import lps.bet.basico.web.gerenciaCtrl.UtilsGerencia;
import lps.bet.interfaces.ICartaoMgt;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class GerenciaViagem extends ControladorBet {

    ICartaoMgt interfaceCartaoMgt;
    ILinhaMgt interfaceLinhaMgt;

    SimpleDateFormat sdfData;
    SimpleDateFormat sdfHora;

    public GerenciaViagem() {
        sdfData = new SimpleDateFormat();
        sdfData.applyPattern("dd/MM/yyyy");

        sdfHora = new SimpleDateFormat();
        sdfHora.applyPattern("hh:mm");

    }

    public ICartaoMgt getInterfaceCartaoMgt() {
        return interfaceCartaoMgt;
    }

    public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
        this.interfaceCartaoMgt = interfaceCartaoMgt;
    }

    public ILinhaMgt getInterfaceLinhaMgt() {
        return interfaceLinhaMgt;
    }

    public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
        this.interfaceLinhaMgt = interfaceLinhaMgt;
    }

    protected ModelAndView buscarViagens() {
        List viagens = interfaceCartaoMgt.buscarViagens();

        SimpleDateFormat sdf = new SimpleDateFormat();
        //Calendar data = Calendar.getInstance();

        ModelAndView mav = new ModelAndView("gerenciaViagem");

        mav.addObject("viagens", viagens);
        mav.addObject("sdf", sdf);
        //mav.addObject("data", data);
        return mav;
    }

    protected ModelAndView buscarViagensPorCartao(int cartaoID) {
        List viagens = interfaceCartaoMgt.buscarViagensPorCartao(cartaoID);
        ModelAndView mav = new ModelAndView("gerenciaViagem");
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (viagens.isEmpty()) {
            mav.addObject("mensagem", "Viagens não foram encontradas para o cartão.");
        } else {
            mav.addObject("viagens", viagens);
            mav.addObject("sdf", sdf);
        }
        return mav;
    }

    protected void criarViagem(Viagem viagem) {
        interfaceCartaoMgt.criarViagem(viagem);
    }

    protected void alterarViagem(Viagem viagem) {
        interfaceCartaoMgt.alterarViagem(viagem);
    }

    protected Viagem montarViagem(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Viagem viagem;

        //Operação de Criação
        if (request.getParameter("viagemID") == null) {
            viagem = new Viagem();
        }
        //Senão precisa buscar
        else {
            viagem = interfaceCartaoMgt.buscarViagem(Integer.parseInt(request.getParameter("viagemID")));
        }

        Cartao cartao = interfaceCartaoMgt.buscarCartao(Integer.parseInt(request.getParameter("cartaoID")));
        viagem.setCartao(cartao);

        Calendar hora = UtilsGerencia.calendarFromStrings(request.getParameter("dataViagem").trim(), request.getParameter("horaViagem").trim());
        viagem.setHora(hora);

        Linha linha = interfaceLinhaMgt.buscarLinha(request.getParameter("nomeLinha").trim());
        viagem.setLinha(linha);

        return viagem;
    }

    protected ModelAndView mostrarForm(String viagemID) {

        ModelAndView mav = new ModelAndView("formViagem");
        mav.addObject("viagemID", viagemID);

        Viagem viagem = null;
        //Calendar hora;

        if (viagemID == null) {
            mav.addObject("operacao", "criar");
            mav.addObject("nomeOperacao", "Criar");
        } else {
            mav.addObject("operacao", "alterar");
            mav.addObject("nomeOperacao", "Alterar");
            viagem = interfaceCartaoMgt.buscarViagem(Integer.parseInt(viagemID));
            //hora = viagem.getHora();
        }
        mav.addObject("viagem", viagem);
        List linhas = interfaceLinhaMgt.buscarLinhas();
        mav.addObject("linhas", linhas);

        List cartoes = interfaceCartaoMgt.buscarCartoes();
        mav.addObject("cartoes", cartoes);
        mav.addObject("sdfData", sdfData);
        mav.addObject("sdfHora", sdfHora);
        return mav;
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        String operacao = request.getParameter("operacao");

        if (request.getServletPath().equals("/gerenciaViagem.html")) {
            // quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
            if (operacao == null)
                return buscarViagens();

            if (operacao.equals("criar")) {
                criarViagem(montarViagem(request));
            } else if (operacao.equals("alterar")) {
                alterarViagem(montarViagem(request));
            }

            if (operacao.equals("remover")) {
                String[] viagensIDs = request.getParameterValues("chkViagemID");

                for (int i = 0; i < viagensIDs.length; i++) {
                    int viagemID = Integer.parseInt(viagensIDs[i]);
                    interfaceCartaoMgt.removerViagem(viagemID);
                }
            }

            //Mostrando um pagamento ou todos, dependendo da operacao requisitada
            if (operacao.equals("buscar")) {
                return buscarViagensPorCartao(Integer.parseInt(request.getParameter("cartaoID").trim()));
            } else {
                return buscarViagens();
            }
        } else {
            return mostrarForm(request.getParameter("viagemID"));
        }
    }

}
