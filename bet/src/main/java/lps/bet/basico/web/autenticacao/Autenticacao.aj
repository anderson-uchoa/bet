package lps.bet.basico.autenticacao;

public aspect Autenticacao {
	pointcut acesso(): execution(* lps.bet.basico.web.controlGerencia.*.*.*(..));
	
	before() : acesso (){
		Object[] args = thisJoinPoint.getArgs();
		if (args.length > 0)
			System.out.println(args[0]);
	}
}
