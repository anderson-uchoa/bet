/*
 * IGirarCatraca.java
 *
 * Created on 4 de Junho de 2007, 21:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lps.bet.basico.onibus.catraca;

import lps.bet.basico.onibus.visor.VisorListener;

/**
 *
 * @author Paula
 */
public interface IGirarCatraca {
	
    public void girarCatraca();
	public void removeCatracaListener (CatracaListener listener);
	public void addCatracaListener (CatracaListener listener);

}

