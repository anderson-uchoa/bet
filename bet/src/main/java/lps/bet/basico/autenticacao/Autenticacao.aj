package lps.bet.basico.autenticacao;

public aspect Autenticacao {
	pointcut acesso(): execution(* lps.bet.basico.web.*.*.*(..));
	
	before() : acesso (){
		Object[] args = thisJoinPoint.getArgs();
		System.out.println(args[0]);
	}
}
