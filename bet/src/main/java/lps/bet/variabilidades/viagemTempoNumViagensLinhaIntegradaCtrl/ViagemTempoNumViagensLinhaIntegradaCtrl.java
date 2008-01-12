package lps.bet.variabilidades.viagemTempoNumViagensLinhaIntegradaCtrl;

import java.util.Calendar;

import lps.bet.basico.linhaMgr.ILinhaMgt;
import lps.bet.basico.linhaMgr.IRegistrarArrecadacao;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Viagem;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.interfaces.IProcessarViagem;
import lps.bet.variabilidades.linhaIntegradaMgr.ILinhaIntegradaMgt;
import lps.bet.variabilidades.numViagensMgr.INumViagensMgt;
import lps.bet.variabilidades.tempoMgr.ITempoMgt;

public class ViagemTempoNumViagensLinhaIntegradaCtrl implements IProcessarViagem {

    ITempoMgt interfaceTempoMgt;

    INumViagensMgt interfaceNumViagensMgt;

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

        //Verificar se está dentro do tempo para realizar integração
        int tempoMaxIntegracao = interfaceTempoMgt.buscarTempo();
        //De início considera-se que não haverá integração, então o tempo passou do que poderia ser:
        long tempoDecorrido = tempoMaxIntegracao + 1;

        int numMaxViagens = interfaceNumViagensMgt.buscarMaxNumViagens();
        int numViagem = 0;

        Linha linhaViagem = interfaceLinhaMgt.buscarLinhaAtualValidador(onibusID);

        Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);

        //Viagem dentro do tempo de integração
        if (viagem != null) {
            Calendar horaUltimaViagem = viagem.getHora();
            tempoDecorrido = Calendar.getInstance().getTimeInMillis() - horaUltimaViagem.getTimeInMillis();

            numViagem = viagem.getNumViagens();

            Linha linhaOriginal = viagem.getLinha();

            //Integração
            if ((tempoDecorrido <= tempoMaxIntegracao * 1000) && (numViagem < numMaxViagens) && (interfaceLinhaIntegradaMgt.verificarLinhaIntegrada(linhaViagem, linhaOriginal))) {
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

    public ITempoMgt getInterfaceTempoMgt() {
        return interfaceTempoMgt;
    }

    public void setInterfaceTempoMgt(ITempoMgt interfaceTempoMgt) {
        this.interfaceTempoMgt = interfaceTempoMgt;
    }

    public INumViagensMgt getInterfaceNumViagensMgt() {
        return interfaceNumViagensMgt;
    }

    public void setInterfaceNumViagensMgt(INumViagensMgt interfaceNumViagensMgt) {
        this.interfaceNumViagensMgt = interfaceNumViagensMgt;
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
