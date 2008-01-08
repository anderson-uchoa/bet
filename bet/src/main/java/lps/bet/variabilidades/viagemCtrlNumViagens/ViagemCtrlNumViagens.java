package lps.bet.variabilidades.viagemCtrlNumViagens;

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
import lps.bet.variabilidades.numViagensMgr.INumViagensMgt;


public class ViagemCtrlNumViagens implements IProcessarViagem {

    IRegistrarViagem interfaceRegistrarViagem;
    IRegistrarArrecadacao interfaceRegistrarArrecadacao;
    ILinhaMgt interfaceLinhaMgt;
    IViacaoMgt interfaceViacaoMgt;
    ICartaoMgt interfaceCartaoMgt;

    //VARIABILIDADE de NUMERO DE VIAGENS m�ximas de INTEGRA��O:
    INumViagensMgt interfaceNumViagensMgt;

    int numViagem;

    public ViagemCtrlNumViagens() {
    }

    public String processarViagem(int cartaoID, int onibusID) {
        String estado = "IS-OK";
        float valor = 0;

        //2. Buscar a linha do �nibus naquele momento:
        Linha linha = interfaceLinhaMgt.buscarLinhaAtualValidador(onibusID);

        //3. Buscar o tipo de passageiro:
        TipoPassageiro tipo = interfaceCartaoMgt.buscarTipoPassagPorCartao(cartaoID);

        //*************** VARIABILIDADE - NUMERO DE VIAGENS*****************:
        //Verificar se est� dentro do n�mero permitido de viagens para realizar integra��o

        int numMaxViagens = interfaceNumViagensMgt.buscarMaxNumViagens();
        System.out.println("Obter NumMAXViagens" + interfaceNumViagensMgt.buscarMaxNumViagens());

        //De in�cio considera-se que n�o haver� integra��o:
        numViagem = numMaxViagens;

        Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);

        //Viagem dentro do tempo de integra��o
        if (viagem != null) {
            numViagem = viagem.getNumViagens();

            //Integra��o
            if (numViagem < numMaxViagens) {
                //Uma integra��o � feita para a viagem
                viagem.setNumViagens(numViagem + 1);
                interfaceCartaoMgt.alterarViagem(viagem);
                //N�o recebe dinheiro, mas incrementa o n�mero de passageiros
                interfaceRegistrarArrecadacao.registrarArrecadacao(onibusID, 0);
                estado = "INT-OK";
            }
        }
        //Viagem fora do tempo de integra��o: nova viagem
        if (!estado.equals("INT-OK")) {

            //Recome�ar a contagem, pois vai ser feita uma nova viagem
            numViagem = 0;

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
                interfaceRegistrarViagem.registrarViagem(cartaoID, linha);
            }
        }
        return estado;
    }

    private float calcularValorPassagem(TipoPassageiro tipo) {
        Tarifa tarifa = interfaceViacaoMgt.buscarTarifa();
        System.out.println("TIPO: " + tipo.getTipoID() + "Desconto: " + tipo.getDesconto());
        float desconto = (float) tipo.getDesconto();
        return ((100 - desconto) / 100) * tarifa.getValorTarifa();
    }

    public IRegistrarViagem getInterfaceRegistrarViagem() {
        return interfaceRegistrarViagem;
    }

    public void setInterfaceRegistrarViagem(
            IRegistrarViagem interfaceRegistrarViagem) {
        this.interfaceRegistrarViagem = interfaceRegistrarViagem;
    }

    public IRegistrarArrecadacao getInterfaceRegistrarArrecadacao() {
        return interfaceRegistrarArrecadacao;
    }

    public void setInterfaceRegistrarArrecadacao(
            IRegistrarArrecadacao interfaceRegistrarArrecadacao) {
        this.interfaceRegistrarArrecadacao = interfaceRegistrarArrecadacao;
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

    public ICartaoMgt getInterfaceCartaoMgt() {
        return interfaceCartaoMgt;
    }

    public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
        this.interfaceCartaoMgt = interfaceCartaoMgt;
    }

    //VARIABILIDADE de NUMERO DE VIAGENS m�ximas de INTEGRA��O:
    public INumViagensMgt getInterfaceNumViagensMgt() {
        return interfaceNumViagensMgt;
    }

    public void setInterfaceNumViagensMgt(INumViagensMgt interfaceNumViagensMgt) {
        this.interfaceNumViagensMgt = interfaceNumViagensMgt;
    }

}
