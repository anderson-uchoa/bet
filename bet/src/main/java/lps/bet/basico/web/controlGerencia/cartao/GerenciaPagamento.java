package lps.bet.basico.web.controlGerencia.cartao;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.Pagamento;
import lps.bet.basico.web.ControladorBet;
import lps.bet.basico.web.gerenciaCtrl.UtilsGerencia;
import lps.bet.interfaces.ICartaoMgt;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GerenciaPagamento extends ControladorBet {

    ICartaoMgt interfaceCartaoMgt;
    SimpleDateFormat sdf;

    public GerenciaPagamento() {
    	sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public ICartaoMgt getInterfaceCartaoMgt() {
        return interfaceCartaoMgt;
    }

    public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
        this.interfaceCartaoMgt = interfaceCartaoMgt;
    }

    protected ModelAndView buscarPagamentos() {
        List pagamentos = interfaceCartaoMgt.buscarPagamentos();
        //List cartoes = interfaceCartaoMgt.buscarCartoes();

        Calendar data = Calendar.getInstance();

        ModelAndView mav = new ModelAndView("gerenciaPagamento");

        mav.addObject("pagamentos", pagamentos);
        //mav.addObject("cartoes", cartoes);
        mav.addObject("sdf", sdf);
        mav.addObject("data", data);
        return mav;
    }

    protected ModelAndView buscarPagamento(int pgtoID) {
        Pagamento pagamento = interfaceCartaoMgt.buscarPagamento(pgtoID);
        ModelAndView mav = new ModelAndView("gerenciaPagamento");
        if (pagamento == null) {
            mav.addObject("mensagem", "Pagamento não encontrado.");
        } else {
            List pagamentos = new ArrayList();
            pagamentos.add(pagamento);

            mav.addObject("pagamentos", pagamentos);
            mav.addObject("sdf", sdf);
        }

        return mav;
    }

    protected void criarPagamento(Pagamento pagamento) {
        interfaceCartaoMgt.criarPagamento(pagamento);
    }

    protected void alterarPagamento(Pagamento pagamento) {
        interfaceCartaoMgt.alterarPagamento(pagamento);
    }

    protected Pagamento montarPagamento(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Pagamento pagamento;

        //Operação de Criação
        if (request.getParameter("pgtoID") == null) {
            pagamento = new Pagamento();

        }
        //Senão precisa buscar
        else {
            pagamento = interfaceCartaoMgt.buscarPagamento(Integer.parseInt(request.getParameter("pgtoID")));
        }

        Cartao cartao = interfaceCartaoMgt.buscarCartao(Integer.parseInt(request.getParameter("cartaoID")));
        pagamento.setCartao(cartao);

        pagamento.setDtPgto(UtilsGerencia.calendarFromString(request.getParameter("dtPgto")));

        String strValorPgto = request.getParameter("valorPgto").trim();
        float valorPgto = strValorPgto.matches("[0-9]*\\.?[0-9]+") ? Float.parseFloat(strValorPgto) : 0;
        pagamento.setValorPgto(valorPgto);

        return pagamento;
    }

    protected ModelAndView mostrarForm(String pgtoID) {

        ModelAndView mav = new ModelAndView("formPagamento");
        mav.addObject("pgtoID", pgtoID);

        mav.addObject("sdf", sdf);

        Pagamento pagamento = null;
        Calendar data;

        if (pgtoID == null) {
            mav.addObject("operacao", "criar");
            mav.addObject("nomeOperacao", "Criar");
            data = Calendar.getInstance();
        } else {
            mav.addObject("operacao", "alterar");
            mav.addObject("nomeOperacao", "Alterar");
            pagamento = interfaceCartaoMgt.buscarPagamento(Integer.parseInt(pgtoID));
            data = pagamento.getDtPgto();
        }
        mav.addObject("pagamento", pagamento);
        mav.addObject("data", data);
        List cartoes = interfaceCartaoMgt.buscarCartoes();
        mav.addObject("cartoes", cartoes);
        return mav;
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        String operacao = request.getParameter("operacao");

        if (request.getServletPath().equals("/gerenciaPagamento.html")) {
            // quando é chamado pela primeira vez a URL não possui o parâmetro 'operacao'
            if (operacao == null)
                return buscarPagamentos();

            if (operacao.equals("criar")) {
                criarPagamento(montarPagamento(request));
            } else if (operacao.equals("alterar")) {
                alterarPagamento(montarPagamento(request));
            }

            //Código do if a ser tirado posteriormente (não existe remover no Tarifa):
            if (operacao.equals("remover")) {
                String[] pgtosIDs = request.getParameterValues("chkPgtoID");

                for (int i = 0; i < pgtosIDs.length; i++) {
                    int pgtoID = Integer.parseInt(pgtosIDs[i]);
                    interfaceCartaoMgt.removerPagamento(pgtoID);
                }
            }

            //Mostrando um pagamento ou todos, dependendo da operacao requisitada
            if (operacao.equals("buscar")) {
                return buscarPagamento(Integer.parseInt(request.getParameter("pgtoID")));
            } else {
                return buscarPagamentos();
            }
        } else {
            return mostrarForm(request.getParameter("pgtoID"));
        }
    }


}
