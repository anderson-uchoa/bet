package lps.bet.variabilidades.viagemCtrlTempo;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.linhaMgr.IRegistrarArrecadacao;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Tarifa;
import lps.bet.basico.tiposDados.TipoPassageiro;
import lps.bet.basico.tiposDados.Viagem;
import lps.bet.basico.viacaoMgr.IViacaoMgt;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.interfaces.IProcessarViagem;
import lps.bet.interfaces.IRegistrarViagem;
import lps.bet.variabilidades.tempoMgr.ITempoMgt;

import java.util.Calendar;

public class ViagemCtrlTempo implements IProcessarViagem {

    IRegistrarViagem interfaceRegistrarViagem;
    IRegistrarArrecadacao interfaceRegistrarArrecadacao;
    ILinhaMgt interfaceLinhaMgt;
    IViacaoMgt interfaceViacaoMgt;
    ICartaoMgt interfaceCartaoMgt;

    //VARIABILIDADE de TEMPO de INTEGRA��O:
    ITempoMgt interfaceTempoMgt;

    long tempoDecorrido;

    public String processarViagem(int cartaoID, int onibusID) {
        String estado = "IS-OK";
        float valor = 0;

        //2. Buscar a linha do �nibus naquele momento:
        Linha linha = interfaceLinhaMgt.buscarLinhaAtualValidador(onibusID);

        //3. Buscar o tipo de passageiro:
        TipoPassageiro tipo = interfaceCartaoMgt.buscarTipoPassagPorCartao(cartaoID);

        //*************** VARIABILIDADE - TEMPO*****************:
        //Verificar se est� dentro do tempo para realizar integra��o
        int tempoMaxIntegracao = interfaceTempoMgt.buscarTempo();
        //De in�cio considera-se que n�o haver� integra��o, ent�o o tempo passou do que poderia ser:
        tempoDecorrido = tempoMaxIntegracao + 1;

        Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);

        //Viagem dentro do tempo de integra��o
        if (viagem != null) {
            Calendar horaUltimaViagem = viagem.getHora();
            tempoDecorrido = Calendar.getInstance().getTimeInMillis() - horaUltimaViagem.getTimeInMillis();
            if (tempoDecorrido <= tempoMaxIntegracao * 1000) {
                //N�o recebe dinheiro, mas incrementa o numero de passageiros
                interfaceRegistrarArrecadacao.registrarArrecadacao(onibusID, 0);

                //Uma integra��o � feita para a viagem
                viagem.setNumViagens(viagem.getNumViagens() + 1);
                interfaceCartaoMgt.alterarViagem(viagem);
                estado = "INT-OK";
            }
        }
        //Viagem fora do tempo de integra��o: nova viagem
        if (!estado.equals("INT-OK")) {
            //4. Se o tipo de passageiro n�o for isento:
            if (!tipo.getFormaPgtoPassagem().equalsIgnoreCase("isento")) {

                //5. buscar a tarifa:
                valor = calcularValorPassagem(tipo);

                //6. Se a forma de pgto for debito, deve-se verificar o saldo do cart�o:
                if (tipo.getFormaPgtoPassagem().equalsIgnoreCase("debito")) {

                    //7. Debitar a passagem no valor obtido
                    if (interfaceCartaoMgt.podeDebitar(cartaoID, valor)) {
                        interfaceRegistrarViagem.debitarPassagem(cartaoID, valor);
                        estado = "PD-OK";

                        //Registrar o credito de arrecadacao para a corrida do onibus
                        interfaceRegistrarArrecadacao.registrarCredito(onibusID, valor);
                    } else {
                        estado = "PD-NOK";
                        return estado;
                    }
                }

                //8. Registrar Arrecada��o se for pagamento na forma manual:
                else if (tipo.getFormaPgtoPassagem().equalsIgnoreCase("manual")) {
                    interfaceRegistrarArrecadacao.registrarArrecadacao(onibusID, valor);
                    estado = "PM-OK";
                }
            }

            if (estado.matches("\\w+-OK")) {
                //Registrar Viagem:
                int numViagem = 0; //Viagem inicial
                interfaceRegistrarViagem.registrarViagem(cartaoID, linha);
            }
        }

        return estado;
    }

    private float calcularValorPassagem(TipoPassageiro tipo) {
        Tarifa tarifa = interfaceViacaoMgt.buscarTarifa();
        float desconto = (float) tipo.getDesconto();
        return ((100 - desconto) / 100) * tarifa.getValorTarifa();
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

    public IRegistrarArrecadacao getInterfaceRegistrarArrecadacao() {
        return interfaceRegistrarArrecadacao;
    }

    public void setInterfaceRegistrarArrecadacao(
            IRegistrarArrecadacao interfaceRegistrarArrecadacao) {
        this.interfaceRegistrarArrecadacao = interfaceRegistrarArrecadacao;
    }

    public IRegistrarViagem getInterfaceRegistrarViagem() {
        return interfaceRegistrarViagem;
    }

    public void setInterfaceRegistrarViagem(
            IRegistrarViagem interfaceRegistrarViagem) {
        this.interfaceRegistrarViagem = interfaceRegistrarViagem;
    }

    public ICartaoMgt getInterfaceCartaoMgt() {
        return interfaceCartaoMgt;
    }

    public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
        this.interfaceCartaoMgt = interfaceCartaoMgt;
    }
    //***********VARIABILIDADE de TEMPO DE INTEGRA��O:***********

    public ITempoMgt getInterfaceTempoMgt() {
        return interfaceTempoMgt;
    }

    public void setInterfaceTempoMgt(ITempoMgt interfaceTempoMgt) {
        this.interfaceTempoMgt = interfaceTempoMgt;
    }

    //************************************************************
}

