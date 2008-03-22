package lps.bet.basico.validadorServidorCtrl;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteIProcessarTransacao extends Remote{
    public String processarTransacao(int cod, int cartaoID, int onibusID) throws RemoteException;
}
