package lps.bet.variabilidades;

import static org.junit.Assert.assertEquals;
import lps.bet.basico.passageiroMgr.PassageiroDAO;
import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.variabilidades.empresaUsuariaMgr.EmpresaUsuariaDAO;
import lps.bet.variabilidades.tiposDados.EmpresaUsuaria;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class TestEmpresaUsuariaDAO {

	ApplicationContext context;
	EmpresaUsuariaDAO dao;
	PassageiroDAO daoPassageiro;
	
	@Test
	public void testBuscarEmpresaPorPassageiro(){
		Passageiro passageiro = daoPassageiro.buscarPassageiroPorID(11);		

		EmpresaUsuaria empresa = dao.buscarEmpresaPorPassageiro(passageiro);
		assertEquals(12, empresa.getUsuarioID());
	}
	
	@Before 
	public void setup(){
		context = new FileSystemXmlApplicationContext("C:\\svn\\trunk\\bet\\target\\classes\\WEB-INF\\bet-servlet.xml");
		dao = (EmpresaUsuariaDAO) context.getBean("EmpresaUsuariaDAO");
		daoPassageiro = (PassageiroDAO) context.getBean("PassageiroMgr");
	}
	
}
