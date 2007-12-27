package lps.bet.variabilidades.viagemLinhaIntegradaCtrl;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.linhaMgr.IRegistrarArrecadacao;
import lps.bet.basico.tiposDados.Viagem;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.interfaces.IProcessarViagem;
import lps.bet.variabilidades.linhaIntegradaMgr.ILinhaIntegradaMgt;

public class ViagemLinhaIntegradaCtrl implements IProcessarViagem {

    ILinhaIntegradaMgt interfaceLinhaIntegradaMgt;
    ILinhaMgt interfaceLinhaMgt;
    ICartaoMgt interfaceCartaoMgt;
    IRegistrarArrecadacao interfaceRegistrarArrecadacao;
    IProcessarViagem interfaceProcessarViagem;

    public String processarViagem(int cartaoID, int onibusID) {

        String estado = "INT-NOK";

        estado = verificarIntegracao(cartaoID, onibusID);

        //Viagem fora do tempo de integração: nova viagem
        if (!estado.equals("INT-OK")) {
            return interfaceProcessarViagem.processarViagem(cartaoID, onibusID);
        } else {
            return estado;
        }
    }

    public String verificarIntegracao(int cartaoID, int onibusID) {

        String estado = "INT-NOK";

        System.out.println("Componente Validar Linha Integrada");

        int linhaViagemID = interfaceLinhaMgt.buscarLinhaAtualOnibus(onibusID).getLinhaID();
        System.out.println("LinhaID: " + linhaViagemID);

        Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);

        //Viagem dentro do tempo de integração
        if (viagem != null) {

            int linhaOriginalID = viagem.getLinha().getLinhaID();
            System.out.println("LinhaOriginalID: " + linhaOriginalID);
            //Integração
            if (interfaceLinhaIntegradaMgt.verificarLinhaIntegrada(linhaViagemID, linhaOriginalID)) {
                estado = processarIntegracao(onibusID, viagem);
            }
        }
        return estado;
    }

    public String processarIntegracao(int onibusID, Viagem viagem) {
        //Não recebe dinheiro, mas incrementa o numero de passageiros para a corrida
        interfaceRegistrarArrecadacao.registrarArrecadacao(onibusID, 0);

        //Uma integração é feita para a viagem
        viagem.setNumViagens(viagem.getNumViagens() + 1);
        interfaceCartaoMgt.alterarViagem(viagem);
        String estado = "INT-OK";
        System.out.println("ESTADO: INT-OK");
        return estado;
    }

    public ILinhaIntegradaMgt getInterfaceLinhaIntegradaMgt() {
        return interfaceLinhaIntegradaMgt;
    }

    public void setInterfaceLinhaIntegradaMgt(
            ILinhaIntegradaMgt interfaceLinhaIntegradaMgt) {
        this.interfaceLinhaIntegradaMgt = interfaceLinhaIntegradaMgt;
    }

    public ILinhaMgt getInterfaceLinhaMgt() {
        return interfaceLinhaMgt;
    }

    public void setInterfaceLinhaMgt(ILinhaMgt interfaceLinhaMgt) {
        this.interfaceLinhaMgt = interfaceLinhaMgt;
    }

    public ICartaoMgt getInterfaceCartaoMgt() {
        return interfaceCartaoMgt;
    }

    public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
        this.interfaceCartaoMgt = interfaceCartaoMgt;
    }

    public IRegistrarArrecadacao getInterfaceRegistrarArrecadacao() {
        return interfaceRegistrarArrecadacao;
    }

    public void setInterfaceRegistrarArrecadacao(
            IRegistrarArrecadacao interfaceRegistrarArrecadacao) {
        this.interfaceRegistrarArrecadacao = interfaceRegistrarArrecadacao;
    }

    public IProcessarViagem getInterfaceProcessarViagem() {
        return interfaceProcessarViagem;
    }

    public void setInterfaceProcessarViagem(
            IProcessarViagem interfaceProcessarViagem) {
        this.interfaceProcessarViagem = interfaceProcessarViagem;
    }
}
