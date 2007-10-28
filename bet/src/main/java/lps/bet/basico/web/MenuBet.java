package lps.bet.basico.web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.regexp.RE;

public class MenuBet {
	
	private String nomeArquivo;
	private int nivelUsuario;
	
	public MenuBet(String nomeArquivo, int nivelUsuario){
		this.nomeArquivo = nomeArquivo;
		this.nivelUsuario = nivelUsuario;
	}
	
	public String obterMenuPersonalizado(){
		RE itemMenuRE = new RE("<li nivel=\"(\\d+)\".*");
		StringBuilder resultado = new StringBuilder();
		
		try {
			BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
			String linhaMenu = null;
			while ((linhaMenu = leitor.readLine()) != null){
				if (itemMenuRE.match(linhaMenu)){
					int nivel = Integer.parseInt(itemMenuRE.getParen(1));
					if (nivelUsuario >= nivel)
						resultado.append(linhaMenu);
				}
				else
					resultado.append(linhaMenu);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return resultado.toString();
	}
}
