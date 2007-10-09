/*
 * IGirarCatraca.java
 *
 * Created on 4 de Junho de 2007, 21:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lps.bet.basico.catraca;

import lps.bet.basico.visor.VisorListener;

/**
 *
 * @author Paula
 */
public interface IGirarCatraca {
	public void removeCatracaListener (CatracaListener listener);
	public void addCatracaListener (CatracaListener listener);
    public void girarCatraca();
}
