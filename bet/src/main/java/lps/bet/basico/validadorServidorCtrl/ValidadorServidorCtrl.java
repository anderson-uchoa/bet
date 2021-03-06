package lps.bet.basico.validadorServidorCtrl;

import lps.bet.basico.corridaCtrl.IRegistrarCorrida;
import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.interfaces.IProcessarViagem;

public class ValidadorServidorCtrl implements IProcessarTransacao {

    IRegistrarCorrida interfaceRegistrarCorrida;
    IProcessarViagem interfaceProcessarViagem;
    ICartaoMgt interfaceCartaoMgt;
    ILinhaMgt interfaceLinhaMgt;

    public ValidadorServidorCtrl() {
    }

    public String processarTransacao(int cod, int cartaoID, int validadorID) {

        String resposta = new String();

        //Se cart�o � v�lido, pode processar a transa��o:
        if (interfaceCartaoMgt.validarCartao(cartaoID)) {

            //Codigo 2 corresponde a registro de corrida
            if (cod == 2) {
                System.out.println("Vai chamar registrar corrida");
                resposta = interfaceRegistrarCorrida.registrarCorrida(validadorID);
            }

            //Codigo 3 corresponde a uma viagem sendo realizada
            else if (cod == 3) {

                //Verificar se viagem pode ser feita
                boolean viagemPermitida = interfaceLinhaMgt.verificarPermissaoViagem(validadorID);
                if (!viagemPermitida) { //Viagem n�o permitida, pois n�o h� corrida aberta
                    resposta = "V-NOK";
                } else {//Validar e Processar viagem
                    resposta = interfaceProcessarViagem.processarViagem(cartaoID, validadorID);
                    float saldo = interfaceCartaoMgt.buscarCartao(cartaoID).getSaldo();
                    String strSaldo = Float.toString(saldo);
                    if (resposta.equals("PD-OK")) {
                        resposta = resposta + " " + strSaldo;
                    }
                }
            }
            return resposta;
        }
        //Sen�o cart�o � inv�lido e n�o deve ser processado
        else {
            System.out.println("Cart�o � inv�lido!");
            return resposta = "NOK";
        }

    }

    public IProcessarViagem getInterfaceProcessarViagem() {
        return interfaceProcessarViagem;
    }

    public void setInterfaceProcessarViagem(IProcessarViagem processarViagem) {
        interfaceProcessarViagem = processarViagem;
    }

    public IRegistrarCorrida getInterfaceRegistrarCorrida() {
        return interfaceRegistrarCorrida;
    }

    public void setInterfaceRegistrarCorrida(IRegistrarCorrida registrarCorrida) {
        interfaceRegistrarCorrida = registrarCorrida;
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
}
