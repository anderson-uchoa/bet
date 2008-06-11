<?xml version="1.0" encoding="UTF-8"?>
		
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="xml"/>

<xsl:output method="xml" doctype-system="http://www.springframework.org/dtd/spring-beans.dtd" 
     doctype-public="-//SPRING//DTD BEAN//EN" indent="yes"/> 
	 
	 
	<xsl:template match="/">
		
	<beans>
			
			<!-- Exposicao da interface do servidor para invocacao remota -->
			<bean name="TratarCartao" class="org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter">
			  <property name="service">
				<ref bean="ValidadorServidorCtrl"/>
			  </property>
			  <property name="serviceInterface">
				<value>lps.bet.basico.validadorServidorCtrl.IProcessarTransacao</value>
			  </property>
			</bean>
			
			<!-- Configuracao do acesso web -->
			<bean id="velocityConfigurer" class="org.springframework.web.servlet.view.velocity.VelocityConfigurer">
				<property name="resourceLoaderPath">				
					<xsl:choose>
						<!--Com Pagto de Cartao-->
						<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
							<xsl:choose>
								<!--Com Empresa Usuaria-->
								<xsl:when test="formsData/forms/form/form[@variant='Responsaveis por Cartoes']/data/combo[@name='empresasUsuarias']='existente'">
									<xsl:choose>
										<!--Com Terminal-->
										<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='terminal']='existente'">
											<value>/WEB-INF/html/variabilidades/nucleo-PgtoCartaoEmprUsuariasTerminal/</value>
										</xsl:when>
										<!--Sem Terminal-->
										<xsl:otherwise>
											<value>/WEB-INF/html/variabilidades/nucleo-PgtoCartaoEmprUsuarias/</value>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:when>
								<!--Sem Empresa Usuaria-->
								<xsl:otherwise>
									<xsl:choose>
										<!--Com Terminal-->
										<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='terminal']='existente'">
											<value>/WEB-INF/html/variabilidades/nucleo-PgtoCartaoTerminal/</value>
										</xsl:when>
										<!--Sem Terminal-->
										<xsl:otherwise>
											<value>/WEB-INF/html/variabilidades/nucleo-PgtoCartao/</value>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:otherwise>
							</xsl:choose>
						</xsl:when>
						<!--Sem Pagto de Cartao-->
						<xsl:otherwise>
							<xsl:choose>
								<!--Com Empresa Usuaria-->
								<xsl:when test="formsData/forms/form/form[@variant='Responsaveis por Cartoes']/data/combo[@name='empresasUsuarias']='existente'">
									<xsl:choose>
										<!--Com Terminal-->
										<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='terminal']='existente'">
											<xsl:choose>
												<!--Com Integracao por Tempo, Num Viagens e Linha Integrada-->													
												<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens, linha integrada'">
													<xsl:choose>
														<!--Com Acesso Adicional-->													
														<xsl:when test="formsData/forms/form/form[@variant='Acesso a Informacoes']/data/combo[@name='acessoAdicional']='existente'">															
															<xsl:choose>
																<!--Com Numero de Cartoes -->
																<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='restricaoCartoes']='numero de cartoes'">
																	<value>/WEB-INF/html/variabilidades/nucleo-EmprUsuariasTerminalIntegracaoAcessoAdicionalNumCartoes/</value>
																</xsl:when>
																<!--Sem Numero de Cartoes-->
																<xsl:otherwise>
																	<value>/WEB-INF/html/variabilidades/nucleo-EmprUsuariasTerminalIntegracaoAcessoAdicional/</value>
																</xsl:otherwise>
															</xsl:choose>															
														</xsl:when>
														<!--Sem Acesso Adicional-->
														<xsl:otherwise>
															<value>/WEB-INF/html/variabilidades/nucleo-EmprUsuariasTerminalIntegracao/</value>
														</xsl:otherwise>
													</xsl:choose>													
												</xsl:when>																						
												<!--Sem Integracao por Tempo, Num Viagens e Linha Integrada-->
												<xsl:otherwise>
													<value>/WEB-INF/html/variabilidades/nucleo-EmprUsuariasTerminal/</value>
												</xsl:otherwise>
											</xsl:choose>											
										</xsl:when>
										<!--Sem Terminal-->
										<xsl:otherwise>
											<value>/WEB-INF/html/variabilidades/nucleo-EmprUsuarias/</value>						
										</xsl:otherwise>
									</xsl:choose>
								</xsl:when>
								<!--Sem Empresa Usuaria-->
								<xsl:otherwise>
									<xsl:choose>
										<!--Com Terminal-->
										<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='terminal']='existente'">
											<value>/WEB-INF/html/variabilidades/nucleo-Terminal/</value>
										</xsl:when>
										<!--Sem Terminal-->
										<xsl:otherwise>
											<xsl:choose>
												<!--Com Integracao por Tempo, NumViagens e Linha Integrada-->
												<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens, linha integrada'">
													<value>/WEB-INF/html/variabilidades/nucleo-Integracao/</value>
												</xsl:when>
												<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, linha integrada'">
													<xsl:choose>
														<!--Com Acesso Adicional-->
														<xsl:when test="formsData/forms/form/form[@variant='Acesso a Informacoes']/data/combo[@name='acessoAdicional']='existente'">
															<xsl:choose>
																<!--Com Limite de Passagens-->
																<xsl:when test="formsData/forms/form/form[@variant='Carga de Cartao']/data/combo[@name='limitePassagens']='existente'">
																	<value>/WEB-INF/html/variabilidades/nucleo-TempoLinhaIntegradaAcessoAdicionalLimPassagens/</value>
																</xsl:when>
																<!--Sem Limite de Passagens-->
																<xsl:otherwise>
																	<value>/WEB-INF/html/variabilidades/nucleo-TempoLinhaIntegradaAcessoAdicional/</value>
																</xsl:otherwise>
															</xsl:choose>															
														</xsl:when>
														<!--Sem Acesso Adicional-->
														<xsl:otherwise>
															<xsl:choose>
																<!--Com Limite de Passagens-->
																<xsl:when test="formsData/forms/form/form[@variant='Carga de Cartao']/data/combo[@name='limitePassagens']='existente'">
																	<value>/WEB-INF/html/variabilidades/nucleo-TempoLinhaIntegradaLimPassagens/</value>
																</xsl:when>
																<!--Sem Limite de Passagens-->
																<xsl:otherwise>
																	<value>/WEB-INF/html/variabilidades/nucleo-TempoLinhaIntegrada/</value>
																</xsl:otherwise>
															</xsl:choose>	
														</xsl:otherwise>
													</xsl:choose>													
												</xsl:when>
												<!--Sem Integracao por Tempo, NumViagens e LinhaIntegrada-->
												<xsl:otherwise>
													<value>/WEB-INF/html/nucleo/</value>
												</xsl:otherwise>											
											</xsl:choose>											
										</xsl:otherwise>
									</xsl:choose>
								</xsl:otherwise>
							</xsl:choose>							
						</xsl:otherwise>
					</xsl:choose>								
				</property>					
			</bean>
			
			<bean class="org.springframework.web.servlet.view.velocity.VelocityViewResolver">
				<property name="prefix">
					<value></value>
				</property> 
				<property name="suffix">
					<value>.vm</value>
				</property> 
			</bean>

			<bean id="urlMapping" class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
				<property name="mappings">
					<props>
						<prop key="/gerenciaCtrl.html">GerenciaCtrl</prop>
						<prop key="/gerenciaFuncionario.html">GerenciaFuncionario</prop>
						<prop key="/gerenciaCargo.html">GerenciaCargo</prop>
						<prop key="/gerenciaLinha.html">GerenciaLinha</prop>
						<prop key="/gerenciaValidador.html">GerenciaValidador</prop>
						<prop key="/gerenciaOnibus.html">GerenciaOnibus</prop>
						<prop key="/gerenciaCorrida.html">GerenciaCorrida</prop>
						<prop key="/gerenciaTarifa.html">GerenciaTarifa</prop>
						<prop key="/gerenciaEmprViaria.html">GerenciaEmpresaViaria</prop>
						
						<xsl:if test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='inexistente'">
							<prop key="/gerenciaSistViarioUrbano.html">GerenciaSistViarioUrbano</prop>
						</xsl:if>
						<xsl:if test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='linha integrada'">
							<prop key="/gerenciaSistViarioUrbano.html">GerenciaSistViarioUrbano</prop>
						</xsl:if>
						
						<prop key="/gerenciaViagem.html">GerenciaViagem</prop>
						
						<prop key="/aquisicaoCartao.html">AquisicaoCartao</prop>		
					
						<xsl:if test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='inexistente'">
								<prop key="/gerenciaTipoPassageiro.html">GerenciaTipoPassageiro</prop>
								<prop key="/formTipoPassageiro.html">GerenciaTipoPassageiro</prop>								
								<prop key="/gerenciaPagamento.html">GerenciaPagamento</prop>
								
								<prop key="/formPagamento.html">GerenciaPagamento</prop>
						</xsl:if>						
						
						<xsl:if test="formsData/forms/form/form[@variant='Responsaveis por Cartoes']/data/combo[@name='empresasUsuarias']='inexistente'">
								<prop key="/gerenciaPassageiro.html">GerenciaPassageiro</prop>
								<prop key="/cargaCartao.html">CargaCartao</prop>						
								<prop key="/acessoBasico.html">AcessoBasico</prop>
								<prop key="/formPassageiro.html">GerenciaPassageiro</prop>
						</xsl:if>
						
						<xsl:if test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='restricaoCartoes']='numero de cartoes'">
							<prop key="/gerenciaCartao.html">GerenciaCartao</prop>
							<prop key="/formCartao.html">GerenciaCartao</prop>
						</xsl:if>
						
						<prop key="/login.html">GerenciaLogin</prop>
						<prop key="/logout.html">GerenciaLogin</prop>
						
						<prop key="/formTarifa.html">GerenciaTarifa</prop>						
						<prop key="/formViagem.html">GerenciaViagem</prop>
						<prop key="/formLinha.html">GerenciaLinha</prop>
						<prop key="/formCorrida.html">GerenciaCorrida</prop>
						<prop key="/formCargo.html">GerenciaCargo</prop>
						<prop key="/formFuncionario.html">GerenciaFuncionario</prop>
						
						<prop key="/buscaAvancadaCorrida.html">GerenciaCorrida</prop>
						
						<prop key="/tratarCartao.html">TratarCartao</prop>
					
						<!--Pagamento de Cartao-->
						<xsl:if test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
							<prop key="/gerenciaTipoPassageiro.html">GerenciaTipoPassageiroPgtoCartao</prop>
							<prop key="/gerenciaCartao.html">GerenciaCartaoPgtoCartao</prop>
							<prop key="/gerenciaPagamento.html">GerenciaPagamentoPgtoCartao</prop>
							<prop key="/formTipoPassageiro.html">GerenciaTipoPassageiroPgtoCartao</prop>
							<prop key="/formPagamento.html">GerenciaPagamentoPgtoCartao</prop>
							<prop key="/formCartao.html">GerenciaCartaoPgtoCartao</prop>
						</xsl:if>
						<!--Fim da parte web do Pagamento de Cartao -->					
						
						<xsl:if test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='terminal']='existente'">
							<!--Terminal-->						
							<prop key="/gerenciaTerminal.html">GerenciaTerminal</prop>
							<prop key="/formTerminal.html">GerenciaTerminal</prop>
							<prop key="/formTerminalValidador.html">GerenciaTerminal</prop>
							<!--Fim da parte web de Terminal-->
						</xsl:if>													
					
						<xsl:if test="formsData/forms/form/form[@variant='Responsaveis por Cartoes']/data/combo[@name='empresasUsuarias']='existente'">
							<!--Empresa Usuaria-->
							<prop key="/gerenciaEmprUsuaria.html">GerenciaEmpresaUsuaria</prop>
							<prop key="/formEmprUsuaria.html">GerenciaEmpresaUsuaria</prop>
							<prop key="/cargaCartao.html">CargaCartaoEmpresaUsuaria</prop>
							<prop key="/gerenciaPassageiro.html">GerenciaPassageiroEmpresaUsuaria</prop>
							<prop key="/formPassageiro.html">GerenciaPassageiroEmpresaUsuaria</prop>
							<prop key="/acessoBasico.html">AcessoBasicoEmpresaUsuaria</prop>
							<prop key="/formCartoesEmpresaUsuaria.html">AcessoBasicoEmpresaUsuaria</prop>
							<!--Fim da parte web de Empresa Usuaria-->
						</xsl:if>
																		
						<xsl:if test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='linha integrada'">
							<!-- Linha Integrada-->
							<prop key="/gerenciaLinhaIntegrada.html">GerenciaLinhaIntegrada</prop>
							<prop key="/formLinhaIntegrada.html">GerenciaLinhaIntegrada</prop>
							<!-- Fim da parte de Linha Integrada-->																
						</xsl:if>
						
						<xsl:if test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, linha integrada'">
							<!-- Linha Integrada-->
							<prop key="/gerenciaLinhaIntegrada.html">GerenciaLinhaIntegrada</prop>
							<prop key="/formLinhaIntegrada.html">GerenciaLinhaIntegrada</prop>
							<!-- Fim da parte de Linha Integrada-->																
							
							<!-- Tempo para integracao-->
							<prop key="/gerenciaSistViarioUrbano.html">GerenciaSistViarioTempo</prop>
							<!-- Fim da parte de Tempo para integracao-->
						</xsl:if>
						
						<xsl:if test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens'">
							<xsl:choose>
								<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='restricaoCartoes']='inexistente'">
									<!-- Integracao por Tempo e Numero de Viagens-->
									<prop key="/gerenciaSistViarioUrbano.html">GerenciaSistViarioTempoNumViagens</prop>
									<!-- Fim da parte de Integracao por Tempo e Numero de Viagens-->							
								</xsl:when>
								<xsl:otherwise>
									<!-- Integracao por Tempo e Numero de Viagens e ainda existe Numero de Cartoes-->
									<prop key="/gerenciaSistViarioUrbano.html">GerenciaSistViarioNumCartoesTempoNumViagens</prop>
								</xsl:otherwise>
							</xsl:choose>										
						</xsl:if>
						
						<xsl:if test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens, linha integrada'">
							<!-- Linha Integrada-->
							<prop key="/gerenciaLinhaIntegrada.html">GerenciaLinhaIntegrada</prop>
							<prop key="/formLinhaIntegrada.html">GerenciaLinhaIntegrada</prop>
							<!-- Fim da parte de Linha Integrada-->											
							<xsl:choose>
								<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='restricaoCartoes']='inexistente'">
									<!-- Integracao por Tempo e Numero de Viagens-->
									<prop key="/gerenciaSistViarioUrbano.html">GerenciaSistViarioTempoNumViagens</prop>
									<!-- Fim da parte de Integracao por Tempo e Numero de Viagens-->							
								</xsl:when>
								<xsl:otherwise>
									<!-- Integracao por Tempo e Numero de Viagens e ainda existe Numero de Cartoes-->
									<prop key="/gerenciaSistViarioUrbano.html">GerenciaSistViarioNumCartoesTempoNumViagens</prop>
								</xsl:otherwise>
							</xsl:choose>						
						</xsl:if>					
						
						<xsl:if test="formsData/forms/form/form[@variant='Acesso a Informacoes']/data/combo[@name='acessoAdicional']='existente'">
							<!--Acesso Adicional-->
							<prop key="/acessoAdicional.html">AcessoAdicional</prop>
							<!--Fim da parte de Acesso Adicional-->
						</xsl:if>
						
						<xsl:if test="formsData/forms/form/form[@variant='Carga de Cartao']/data/combo[@name='limitePassagens']='existente'">
							<!--Limite de Passagens por Mes-->
							<prop key="/gerenciaTipoPassageiro.html">GerenciaTipoPassageiroLimPassagens</prop>
							<prop key="/formTipoPassageiro.html">GerenciaTipoPassageiroLimPassagens</prop>			
							<prop key="/cargaCartao.html">CargaCartaoLimPassagens</prop>
							<prop key="/acessoBasico.html">AcessoBasicoLimPassagens</prop>
							<prop key="/gerenciaCartao.html">GerenciaCartaoLimPassagens</prop>				
							<prop key="/formCartao.html">GerenciaCartaoLimPassagens</prop>
							<!--Fim da parte de Limite de Passagens por Mes-->
						</xsl:if>
						
						<!--Numero de Cartoes-->
						<xsl:if test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='restricaoCartoes']='numero de cartoes'">
							<prop key="/gerenciaCartao.html">GerenciaCartaoNumCartoes</prop>
							<prop key="/formCartao.html">GerenciaCartaoNumCartoes</prop>
						</xsl:if>			
						<!--Fim de Numero de Cartoes-->
				
					</props>
				</property>
			</bean>	
			
			<!--Parte do Acesso WEB-->
			<bean id="GerenciaCtrl" class="lps.bet.basico.web.gerenciaCtrl.GerenciaCtrl"/>
			
			<bean id="CargaCartao" class="lps.bet.basico.web.cargaCartao.CargaCartao">
				<property name="interfaceCartaoMgt">
					<xsl:choose>
						<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
							<ref bean="CartaoPgtoCartaoCtrl"/>
						</xsl:when>
						<xsl:otherwise>
							<ref bean="CartaoMgr"/>
						</xsl:otherwise>
					</xsl:choose>
				</property> 
				<property name="nivelMinimoAcesso">
					<value>${config.nivel.CargaCartao}</value>
				</property>
			</bean>
			
			<bean id="AcessoBasico" class="lps.bet.basico.web.acessoBasico.AcessoBasico">
				<property name="interfaceCartaoMgt">
					<xsl:choose>
						<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
							<ref bean="CartaoPgtoCartaoCtrl"/>
						</xsl:when>
						<xsl:otherwise>
							<ref bean="CartaoMgr"/>
						</xsl:otherwise>
					</xsl:choose>
				</property> 
				<property name="interfacePassageiroMgt">
					<ref bean="PassageiroMgr"/>
				</property> 
				<property name="nivelMinimoAcesso">
					<value>${config.nivel.AcessoBasico}</value>
				</property>
			</bean>

			<bean id="GerenciaLogin" class="lps.bet.basico.web.autenticacao.GerenciaLogin">
				<property name="interfaceAutenticacao">
					<ref bean="AutenticacaoDAO"/>
				</property>		
			</bean>
			
			<bean id="AquisicaoCartao" class="lps.bet.basico.web.aquisicaoCartao.AquisicaoCartao">
				<property name="interfaceCartaoMgt">
					<xsl:choose>
						<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
							<ref bean="CartaoPgtoCartaoCtrl"/>
						</xsl:when>
						<xsl:otherwise>
							<ref bean="CartaoMgr"/>
						</xsl:otherwise>
					</xsl:choose>
				</property> 
				<property name="interfacePassageiroMgt">
					<ref bean="PassageiroMgr"/>
				</property> 
			</bean>
			
			<bean id="GerenciaTarifa" class="lps.bet.basico.web.gerenciaCtrl.viacao.GerenciaTarifa">
				<property name="interfaceViacaoMgt">
					<ref bean="ViacaoMgr"/>
				</property> 
			</bean>

			<bean id="GerenciaEmpresaViaria" class="lps.bet.basico.web.gerenciaCtrl.viacao.GerenciaEmpresaViaria">
				<property name="interfaceViacaoMgt">
					<ref bean="ViacaoMgr"/>
				</property>
			</bean>

			<bean id="GerenciaSistViarioUrbano" class="lps.bet.basico.web.gerenciaCtrl.viacao.GerenciaSistViarioUrbano">
				<property name="interfaceViacaoMgt">
				<ref bean="ViacaoMgr"/>
				</property> 
			</bean>

			<bean id="GerenciaFuncionario" class="lps.bet.basico.web.gerenciaCtrl.funcionario.GerenciaFuncionario">
				<property name="interfaceFuncionarioMgt">
					<ref bean="FuncionarioMgr"/>
				</property> 
				<property name="interfaceViacaoMgt">
					<ref bean="ViacaoMgr"/>
				</property> 
			</bean>

			<bean id="GerenciaCargo" class="lps.bet.basico.web.gerenciaCtrl.funcionario.GerenciaCargo">
				<property name="interfaceFuncionarioMgt">
					<ref bean="FuncionarioMgr"/>
				</property>
			</bean>
			
			<bean id="GerenciaLinha" class="lps.bet.basico.web.gerenciaCtrl.linha.GerenciaLinha">
				<property name="interfaceLinhaMgt">
					<ref bean="LinhaMgr"/>
				</property> 
				<property name="interfaceViacaoMgt">
					<ref bean="ViacaoMgr"/>
				</property> 
			</bean>
			
		    <bean id="GerenciaValidador" class="lps.bet.basico.web.gerenciaCtrl.linha.GerenciaValidador">
				<property name="interfaceLinhaMgt">
					<ref bean="LinhaMgr"/>
				</property>
			</bean>

			<bean id="GerenciaOnibus" class="lps.bet.basico.web.gerenciaCtrl.linha.GerenciaOnibus">
				<property name="interfaceLinhaMgt">
					<ref bean="LinhaMgr"/>
				</property> 
			</bean>
			
			<bean id="GerenciaCorrida" class="lps.bet.basico.web.gerenciaCtrl.linha.GerenciaCorrida">
				<property name="interfaceLinhaMgt">
					<ref bean="LinhaMgr"/>
				</property>
			</bean>

			<bean id="GerenciaPassageiro" class="lps.bet.basico.web.gerenciaCtrl.passageiro.GerenciaPassageiro">
				<property name="interfacePassageiroMgt">
					<ref bean="PassageiroMgr"/>
				</property> 
				<property name="nivelMinimoAcesso">
					<value>${config.nivel.GerenciaPassageiro}</value>
				</property>
			</bean>
			
			<bean id="GerenciaTipoPassageiro" class="lps.bet.basico.web.gerenciaCtrl.cartao.GerenciaTipoPassageiro">
				<property name="interfaceCartaoMgt">
					<ref bean="CartaoMgr"/>
				</property> 
			</bean>
			
			<bean id="GerenciaCartao" class="lps.bet.basico.web.gerenciaCtrl.cartao.GerenciaCartao">
				<property name="interfaceCartaoMgt">
					<ref bean="CartaoMgr"/>
				</property> 
				<property name="interfacePassageiroMgt">
					<ref bean="PassageiroMgr"/>
				</property> 
				<property name="nivelMinimoAcesso">
					<value>${config.nivel.GerenciaCartao}</value>
				</property>
			</bean>	

			<bean id="GerenciaPagamento" class="lps.bet.basico.web.gerenciaCtrl.cartao.GerenciaPagamento">
				<property name="interfaceCartaoMgt">
					<ref bean="CartaoMgr"/>
				</property> 
			</bean>
			
			<bean id="GerenciaViagem" class="lps.bet.basico.web.gerenciaCtrl.cartao.GerenciaViagem">
				<property name="interfaceCartaoMgt">
					<ref bean="CartaoMgr"/>
				</property> 
				<property name="interfaceLinhaMgt">
					<ref bean="LinhaMgr"/>
				</property> 
			</bean>
			
			
			<bean id="ValidadorServidorCtrl" class="lps.bet.basico.validadorServidorCtrl.ValidadorServidorCtrl">
				<property name="interfaceRegistrarCorrida">
					<ref bean="CorridaCtrl"/>
				</property> 
				<property name="interfaceProcessarViagem">
					<xsl:choose>
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='solucaoIntegracao']='com componentes'">
							<xsl:choose>
								<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo'">
									<ref bean="ViagemTempoCtrl"/>
								</xsl:when>
								<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='numero de viagens'">
									<ref bean="ViagemNumViagensCtrl"/>
								</xsl:when>
								<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='linha integrada'">
									<ref bean="ViagemLinhaIntegradaCtrl"/>
								</xsl:when>
								<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens'">
									<ref bean="ViagemTempoNumViagensCtrl"/>
								</xsl:when>
								<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, linha integrada'">
									<ref bean="ViagemTempoLinhaIntegradaCtrl"/>
								</xsl:when>
								<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='numero de viagens, linha integrada'">
									<ref bean="ViagemNumViagensLinhaIntegradaCtrl"/>
								</xsl:when>
								<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens, linha integrada'">
									<ref bean="ViagemTempoNumViagensLinhaIntegradaCtrl"/>
								</xsl:when>
								<xsl:otherwise>
									<ref bean="ViagemCtrl"/>
								</xsl:otherwise>
							</xsl:choose>
						</xsl:when>
						<xsl:otherwise>
							<ref bean="ViagemCtrl"/>
						</xsl:otherwise>
					</xsl:choose>				
				</property> 
				<property name="interfaceCartaoMgt">
					<xsl:choose>
						<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
							<ref bean="CartaoPgtoCartaoCtrl"/>
						</xsl:when>
						<xsl:otherwise>
							<ref bean="CartaoMgr"/>
						</xsl:otherwise>
					</xsl:choose>
				</property> 
				<property name="interfaceLinhaMgt">
					<ref bean="LinhaMgr"/>
				</property> 
			</bean>

			<bean id="CorridaCtrl" class="lps.bet.basico.corridaCtrl.CorridaCtrl">
				<property name="interfaceAtualizarCorrida">
					<ref bean="LinhaMgr"/>
				</property> 
			</bean>

			<bean id="ViagemCtrl" class="lps.bet.basico.viagemCtrl.ViagemCtrl">
				<property name="interfaceRegistrarViagem">
					<ref bean="CartaoMgr"/>
				</property> 
				<property name="interfaceCartaoMgt">
					<xsl:choose>
						<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
							<ref bean="CartaoPgtoCartaoCtrl"/>
						</xsl:when>
						<xsl:otherwise>
							<ref bean="CartaoMgr"/>
						</xsl:otherwise>
					</xsl:choose>
				</property> 
				<property name="interfaceRegistrarArrecadacao">
					<ref bean="LinhaMgr"/>
				</property> 
				<property name="interfaceLinhaMgt">
					<ref bean="LinhaMgr"/>
				</property> 
				<property name="interfaceViacaoMgt">
					<ref bean="ViacaoMgr"/>
				</property> 
			</bean>

			<!--bean id="CargaCartao" class="lps.bet.basico.web.cargaCartao.CargaCartao">
				<property name="interfaceCartaoMgt" ref="CartaoMgr"/>
			</bean-->
			
			<bean id="CartaoMgr" class="lps.bet.basico.cartaoMgr.CartaoMgr">
				<property name="viagemDAO">
					<ref bean="ViagemDAO"/>
				</property> 
				<property name="cartaoDAO">
					<ref bean="CartaoDAO"/>
				</property> 
				<property name="tipoPassageiroDAO">
					<ref bean="TipoPassageiroDAO"/>
				</property> 
				<property name="pagamentoDAO">
					<ref bean="PagamentoDAO"/>
				</property> 
				<property name="interfacePassageiroMgt">
					<ref bean="PassageiroMgr"/>
				</property> 
			</bean>

			<bean id="LinhaMgr" class="lps.bet.basico.linhaMgr.LinhaMgr">
				<property name="linhaDAO">
					<ref bean="LinhaDAO"/>
				</property> 
				<property name="corridaDAO">
					<ref bean="CorridaDAO"/>
				</property> 
				<property name="onibusDAO">
					<ref bean="OnibusDAO"/>
				</property> 
			    <property name="validadorDAO">
					<ref bean="ValidadorDAO"/>
				</property>
		 	</bean>

			<!-- Como PassageiroMgr eh implementado pelo DAO, ele ja foi colocado diretamente na parte dos DAOs-->
			
			<bean id="ViacaoMgr" class="lps.bet.basico.viacaoMgr.ViacaoMgr">
				<property name="empresaViariaDAO">
					<ref bean="EmpresaViariaDAO"/>
				</property> 
				<property name="sistViarioUrbanoDAO">
					<ref bean="SistViarioUrbanoDAO"/>
				</property> 
				<property name="tarifaDAO">
					<ref bean="TarifaDAO"/>
				</property> 
			</bean>

			<bean id="FuncionarioMgr" class="lps.bet.basico.funcionarioMgr.FuncionarioMgr">
				<property name="funcionarioDAO">
					<ref bean="FuncionarioDAO"/>
				</property> 
				<property name="cargoDAO">
					<ref bean="CargoDAO"/>
				</property> 
			</bean>
				
			<!--Beans de Aspectos Basicos do sistema BET-->

			<bean id="AutenticacaoAspecto" class="lps.bet.basico.web.autenticacao.Autenticacao" factory-method="aspectOf">
				<property name="autenticacaoDAO">
					<ref bean="AutenticacaoDAO"/>
				</property>
			</bean>

			<bean id="AutorizacaoAspecto" class="lps.bet.basico.web.autenticacao.Autorizacao" factory-method="aspectOf">
				<property name="nomeArquivoMenu">
						<value>src/main/resources/menu.xml</value>					
				</property>
			</bean>

			
			<!-- Beans relacionados ao Hibernate: dataSource e sessionFactory -->
			
			<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
				<property name="url">
				 	<value>${config.db.url}</value>
				</property> 
				<property name="username">
				 	<value>${config.db.username}</value>
				</property> 
				<property name="password"> 
					<value>${config.db.password}</value>
				</property> 
				<property name="driverClassName">
					<value>org.postgresql.Driver</value>
				</property> 
			</bean>
			
			<bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
			  <property name="dataSource">
				<ref bean="dataSource"/>
			  </property> 
			  <property name="mappingDirectoryLocations">
					<list>
						<value>${config.hibernate.location1}</value>
					</list>
			  </property>
			  <property name="hibernateProperties">
			    <props>
			      <prop key="hibernate.dialect">${config.hibernate.dialect}</prop>
			    </props>
			  </property>
			</bean>
					
			
			<!-- DAOs do Servidor do sistema BET -->
			
			<bean id="AutenticacaoDAO" class="lps.bet.basico.web.autenticacao.AutenticacaoDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property>
				<property name="tempoSessao">
					<value>120</value>
				</property>
			</bean>

			
			<bean id="LinhaDAO" class="lps.bet.basico.linhaMgr.LinhaDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 
				<property name="hqlBuscarLinhaCorrida">
					<value>${config.hql.BuscarLinhaCorrida}</value>
				</property>
				<property name="hqlBuscarLinhaPorNome">
					<value>${config.hql.BuscarLinhaPorNome}</value>
				</property>
			</bean>
			
			<bean id="CorridaDAO" class="lps.bet.basico.linhaMgr.CorridaDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 
				<property name="hqlBuscarCorridasPrevistas">
					<value>${config.hql.BuscarCorridasPrevistas}</value>
				</property>
				<property name="hqlBuscarCorridaAtualValidador">
					<value>${config.hql.BuscarCorridaAtualValidador}</value>
				</property>
			</bean>
			
			<bean id="ValidadorDAO" class="lps.bet.basico.linhaMgr.ValidadorDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property>
			</bean>
			
			<bean id="OnibusDAO" class="lps.bet.basico.linhaMgr.OnibusDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 
			</bean>
			
			<bean id="ViagemDAO" class="lps.bet.basico.cartaoMgr.ViagemDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 				
			</bean>
			
			<bean id="CartaoDAO" class="lps.bet.basico.cartaoMgr.CartaoDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 
			</bean>
			
			<bean id="TipoPassageiroDAO" class="lps.bet.basico.cartaoMgr.TipoPassageiroDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 
				<property name="hqlBuscarTipoPassagPorCartao">
					<value>${config.hql.BuscarTipoPassagPorCartao}</value>
				</property>			
			</bean>
			
			<bean id="PagamentoDAO" class="lps.bet.basico.cartaoMgr.PagamentoDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 
			</bean>
			
		    <bean id="TarifaDAO" class="lps.bet.basico.viacaoMgr.TarifaDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 
				<property name="hqlBuscarTarifasOrdenadas">
					<value>${config.hql.BuscarTarifasOrdenadas}</value>
				</property>
			</bean>
			
			<bean id="EmpresaViariaDAO" class="lps.bet.basico.viacaoMgr.EmpresaViariaDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 
			</bean>
			
			<bean id="SistViarioUrbanoDAO" class="lps.bet.basico.viacaoMgr.SistViarioUrbanoDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 
			</bean>

			<bean id="FuncionarioDAO" class="lps.bet.basico.funcionarioMgr.FuncionarioDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 
			</bean>	

			<bean id="CargoDAO" class="lps.bet.basico.funcionarioMgr.CargoDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 				
			</bean>	
			
			<bean id="PassageiroMgr" class="lps.bet.basico.passageiroMgr.PassageiroDAO">
				<property name="sessionFactory">
					<ref bean="sessionFactory"/>
				</property> 				
			</bean>

			<!--**************************************************************************
			************************ Variabilidades da LPS BET *****************************
			*****************************************************************************-->

			<!--Pagamento de Cartao-->
			<xsl:if test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
				<bean id="CartaoPgtoCartaoCtrl" class="lps.bet.variabilidades.cartaoPgtoCartaoCtrl.CartaoPgtoCartaoCtrl">
					<property name="interfaceCartaoMgt">
						<ref bean="CartaoMgr"/>
					</property>
					<property name="interfacePagtoCartaoMgt">
						<ref bean="PagamentoCartaoMgr"/>
					</property>		
				</bean>
				
				<bean id="PagamentoCartaoMgr" class="lps.bet.variabilidades.pagamentoCartaoMgr.PagamentoCartaoMgr">
					<property name="pagtoCartaoDAO">
						<ref bean="PagtoCartaoDAO"/>
					</property>
					<property name="tipoPassagPagtoCartaoDAO">
						<ref bean="TipoPassagPagtoCartaoDAO"/>
					</property>
				</bean>
				
				<bean id="PagtoCartaoDAO" class="lps.bet.variabilidades.pagamentoCartaoMgr.PagtoCartaoDAO">
					<property name="sessionFactory">
						<ref bean="sessionFactory"/>
					</property>
				</bean>
				
				<bean id="TipoPassagPagtoCartaoDAO" class="lps.bet.variabilidades.pagamentoCartaoMgr.TipoPassagPagtoCartaoDAO">
					<property name="sessionFactory">
						<ref bean="sessionFactory"/>
					</property>
				</bean>
				
				<bean id="GerenciaTipoPassageiroPgtoCartao" class="lps.bet.variabilidades.web.pagamentoCartao.GerenciaTipoPassageiroPgtoCartao">
					<property name="interfaceCartaoMgt">
						<ref bean="CartaoMgr"/>
					</property>
					<property name="interfacePagtoCartaoMgt">
						<ref bean="PagamentoCartaoMgr"/>
					</property>
				</bean>

				<bean id="GerenciaCartaoPgtoCartao" class="lps.bet.variabilidades.web.pagamentoCartao.GerenciaCartaoPgtoCartao">
					<property name="interfaceCartaoMgt">
						<ref bean="CartaoPgtoCartaoCtrl"/>
					</property>
					<property name="interfacePassageiroMgt">
						<ref bean="PassageiroMgr"/>
					</property>
					<property name="nivelMinimoAcesso">
						<value>${config.nivel.GerenciaCartao}</value>
					</property>
					<property name="interfacePagtoCartaoMgt">
						<ref bean="PagamentoCartaoMgr"/>
					</property>
				</bean>
				
				<bean id="GerenciaPagamentoPgtoCartao" class="lps.bet.variabilidades.web.pagamentoCartao.GerenciaPagamentoPgtoCartao">
					<property name="interfaceCartaoMgt">
						<ref bean="CartaoMgr"/>
					</property>
					<property name="interfacePagtoCartaoMgt">
						<ref bean="PagamentoCartaoMgr"/>
					</property>
				</bean>
			</xsl:if>
			
			<!--Fim de Pagamento de Cartao-->
			
			<!--Feature Terminal:-->
			<xsl:if test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='terminal']='existente'">
				<bean id="GerenciaTerminal" class="lps.bet.variabilidades.web.terminal.GerenciaTerminal">
					<property name="interfaceTerminalMgt">
						<ref bean="TerminalMgr"/>
					</property>
					<property name="interfaceLinhaMgt">
						<ref bean="LinhaMgr"/>
					</property>
				</bean>

				<bean id="TerminalMgr" class="lps.bet.variabilidades.terminalMgr.TerminalMgr">
					<property name="terminalDAO">
						<ref bean="TerminalDAO"/>
					</property>
				</bean>

				<bean id="TerminalDAO" class="lps.bet.variabilidades.terminalMgr.TerminalDAO">
					<property name="sessionFactory">
						<ref bean="sessionFactory"/>
					</property>
				</bean>
			</xsl:if>				
			<!--FIM Feature Terminal:-->
			
			<!-- Empresa Usuaria-->
			<xsl:if test="formsData/forms/form/form[@variant='Responsaveis por Cartoes']/data/combo[@name='empresasUsuarias']='existente'">
				
				<bean id="GerenciaEmpresaUsuaria" class="lps.bet.variabilidades.web.empresaUsuaria.GerenciaEmpresaUsuaria">
					<property name="interfaceEmpresaUsuariaMgt">
						<ref bean="EmpresaUsuariaMgr"/>
					</property>
					<property name="nivelMinimoAcesso">
						<value>${config.nivel.GerenciaPassageiro}</value>
					</property>
				</bean>
				
				<bean id="GerenciaPassageiroEmpresaUsuaria" class="lps.bet.variabilidades.web.empresaUsuaria.GerenciaPassageiroEmpresaUsuaria">
					<property name="interfacePassageiroMgt">
						<ref bean="PassageiroMgr"/>
					</property>
					<property name="interfaceEmpresaUsuariaMgt">
						<ref bean="EmpresaUsuariaMgr"/>
					</property>
					<property name="nivelMinimoAcesso">
						<value>${config.nivel.GerenciaPassageiro}</value>
					</property>
				</bean>

				<bean id="CargaCartaoEmpresaUsuaria" class="lps.bet.variabilidades.web.empresaUsuaria.CargaCartaoEmpresaUsuaria">
					<property name="interfaceCartaoMgt">
						<xsl:choose>
							<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
								<ref bean="CartaoPgtoCartaoCtrl"/>
							</xsl:when>
							<xsl:otherwise>
								<ref bean="CartaoMgr"/>
							</xsl:otherwise>
						</xsl:choose>						
						
					</property>
					<property name="interfaceEmpresaUsuariaMgt">
						<ref bean="EmpresaUsuariaMgr"/>
					</property>
					<property name="nivelMinimoAcesso">
						<value>${config.nivel.CargaCartao}</value>
					</property>
				</bean>
				
				<bean id="AcessoBasicoEmpresaUsuaria" class="lps.bet.variabilidades.web.empresaUsuaria.AcessoBasicoEmpresaUsuaria">
					<property name="interfaceCartaoMgt">
						<xsl:choose>
							<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
								<ref bean="CartaoPgtoCartaoCtrl"/>
							</xsl:when>
							<xsl:otherwise>
								<ref bean="CartaoMgr"/>
							</xsl:otherwise>
						</xsl:choose>
					</property>
					<property name="interfacePassageiroMgt">
						<ref bean="PassageiroMgr"/>
					</property>
					<property name="interfaceEmpresaUsuariaMgt">
						<ref bean="EmpresaUsuariaMgr"/>
					</property>
					<property name="nivelMinimoAcesso">
						<value>${config.nivel.AcessoBasico}</value>
					</property>
				</bean>
				
				<bean id="EmpresaUsuariaDAO" class="lps.bet.variabilidades.empresaUsuariaMgr.EmpresaUsuariaDAO">
					<property name="sessionFactory">
						<ref bean="sessionFactory"/>
					</property>
					<property name="hqlBuscarEmpresaPorPassageiro">
						<value>${config.hql.BuscarEmpresaPorPassageiro}</value>
					</property>		
				</bean>
				
				<bean id="EmpresaUsuariaMgr" class="lps.bet.variabilidades.empresaUsuariaMgr.EmpresaUsuariaMgr">
					<property name="empresaUsuariaDAO">
						<ref bean="EmpresaUsuariaDAO"/>
					</property>
				</bean>		
			
			</xsl:if>
			<!--Fim de Empresa Usuaria-->
			
			<!--Limite de Passagens por Mes-->
			<xsl:if test="formsData/forms/form/form[@variant='Carga de Cartao']/data/combo[@name='limitePassagens']='existente'">				
				<bean id="GerenciaTipoPassageiroLimPassagens" class="lps.bet.variabilidades.web.limitePassagens.GerenciaTipoPassageiroLimPassagens">
			        <property name="interfaceCartaoMgt">
			            <ref bean="CartaoMgr"/>
			        </property>
					<property name="interfaceLimitePassagensMgt">
			            <ref bean="LimitePassagensMgr"/>
			        </property>
			    </bean>
				
				<bean id="LimitePassagensMgr" class="lps.bet.variabilidades.limitePassagensMgr.LimitePassagensMgr">
			        <property name="tipoPassagLimPassagensDAO">
			            <ref bean="TipoPassagLimPassagensDAO"/>
			        </property>
					<property name="cartaoLimPassagensDAO">
			            <ref bean="CartaoLimPassagensDAO"/>
			        </property>
					<property name="interfaceViacaoMgt">
			            <ref bean="ViacaoMgr"/>
			        </property>
					<property name="interfaceCartaoMgt">
			            <ref bean="CartaoMgr"/>
			        </property>
			    </bean>

				<bean id="CartaoLimPassagensDAO" class="lps.bet.variabilidades.limitePassagensMgr.CartaoLimPassagensDAO">
			        <property name="sessionFactory">
			            <ref bean="sessionFactory"/>
			        </property>
			    </bean>
				
				<bean id="TipoPassagLimPassagensDAO" class="lps.bet.variabilidades.limitePassagensMgr.TipoPassagLimPassagensDAO">
			        <property name="sessionFactory">
			            <ref bean="sessionFactory"/>
			        </property>
			    </bean>
				
				<bean id="CargaCartaoLimPassagens" class="lps.bet.variabilidades.web.limitePassagens.CargaCartaoLimPassagens">
			        <property name="interfaceCartaoMgt">
			            <ref bean="CartaoMgr"/>
			        </property>
					<property name="interfaceLimitePassagensMgt">
			            <ref bean="LimitePassagensMgr"/>
			        </property>		    
			        <property name="nivelMinimoAcesso">
			            <value>${config.nivel.CargaCartao}</value>
			        </property>
			    </bean>
				
				<bean id="AcessoBasicoLimPassagens" class="lps.bet.variabilidades.web.limitePassagens.AcessoBasicoLimPassagens">
			        <property name="interfaceCartaoMgt">
						<xsl:choose>
							<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
								<ref bean="CartaoPgtoCartaoCtrl"/>
							</xsl:when>
							<xsl:otherwise>
								<ref bean="CartaoMgr"/>
							</xsl:otherwise>
						</xsl:choose>			            
			        </property>
			        <property name="interfacePassageiroMgt">
			            <ref bean="PassageiroMgr"/>
			        </property>
					<property name="interfaceLimitePassagensMgt">
			            <ref bean="LimitePassagensMgr"/>
			        </property>	
			        <property name="nivelMinimoAcesso">
			            <value>${config.nivel.AcessoBasico}</value>
			        </property>
			    </bean>
				
				<bean id="GerenciaCartaoLimPassagens" class="lps.bet.variabilidades.web.limitePassagens.GerenciaCartaoLimPassagens">
			        <property name="interfaceCartaoMgt">
			            <ref bean="CartaoMgr"/>
			        </property>
			        <property name="interfacePassageiroMgt">
			            <ref bean="PassageiroMgr"/>
			        </property>
					<property name="interfaceLimitePassagensMgt">
			            <ref bean="LimitePassagensMgr"/>
			        </property>
			        <property name="nivelMinimoAcesso">
			            <value>${config.nivel.GerenciaCartao}</value>
			        </property>
			    </bean>
			</xsl:if>	
			<!--Fim de Limite de Passagens por Mes-->
			
			<!--Acesso Adicional-->
			<xsl:if test="formsData/forms/form/form[@variant='Acesso a Informacoes']/data/combo[@name='acessoAdicional']='existente'">							
				<bean id="AcessoAdicional" class="lps.bet.variabilidades.web.acessoAdicional.AcessoAdicional">
			        <property name="interfaceCartaoMgt">
			            <xsl:choose>
							<xsl:when test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='pgtoCartao']='existente'">
								<ref bean="CartaoPgtoCartaoCtrl"/>
							</xsl:when>
							<xsl:otherwise>
								<ref bean="CartaoMgr"/>
							</xsl:otherwise>
						</xsl:choose>
			        </property>        
					<property name="nivelMinimoAcesso">
			            <value>${config.nivel.AcessoBasico}</value>
			        </property>
			    </bean>				
			</xsl:if>
			<!--Fim de Acesso Adicional-->
			
			<!--Numero de Cartoes-->
			<xsl:if test="formsData/forms/form/form[@variant='Aquisicao de Cartao']/data/combo[@name='restricaoCartoes']='numero de cartoes'">
			    <bean id="GerenciaCartaoNumCartoes" class="lps.bet.variabilidades.web.numCartoes.GerenciaCartaoNumCartoes">
					<property name="interfaceCartaoMgt">
						<ref bean="CartaoMgr"/>
					</property>
					<property name="interfacePassageiroMgt">
						<ref bean="PassageiroMgr"/>
					</property>
					<property name="interfaceNumCartoesMgt">
						<ref bean="NumCartoesMgr"/>
					</property>
				</bean>
				
				<bean id="NumCartoesMgr" class="lps.bet.variabilidades.numCartoesMgr.NumCartoesDAO">
			        <property name="sessionFactory">
			            <ref bean="sessionFactory"/>
			        </property>
			    </bean>			
				
				<xsl:choose>
					<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens'">
						<bean id="GerenciaSistViarioNumCartoesTempoNumViagens" class="lps.bet.variabilidades.web.numCartoesTempoNumViagens.GerenciaSistViarioNumCartoesTempoNumViagens">
							<property name="interfaceViacaoMgt">
								<ref bean="ViacaoMgr"/>
							</property>
							<property name="interfaceNumViagensMgt">
								<ref bean="NumViagensMgr"/>
							</property>
							<property name="interfaceTempoMgt">
								<ref bean="TempoMgr"/>
							</property>
							<property name="interfaceNumCartoesMgt">
								<ref bean="NumCartoesMgr"/>
							</property>						
						</bean>
					</xsl:when>
					<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens, linha integrada'">
						<bean id="GerenciaSistViarioNumCartoesTempoNumViagens" class="lps.bet.variabilidades.web.numCartoesTempoNumViagens.GerenciaSistViarioNumCartoesTempoNumViagens">
							<property name="interfaceViacaoMgt">
								<ref bean="ViacaoMgr"/>
							</property>
							<property name="interfaceNumViagensMgt">
								<ref bean="NumViagensMgr"/>
							</property>
							<property name="interfaceTempoMgt">
								<ref bean="TempoMgr"/>
							</property>
							<property name="interfaceNumCartoesMgt">
								<ref bean="NumCartoesMgr"/>
							</property>						
						</bean>
					</xsl:when>
					<xsl:otherwise>
						<bean id="GerenciaSistViarioNumCartoes" class="lps.bet.variabilidades.web.numCartoes.GerenciaSistViarioNumCartoes">
							<property name="interfaceViacaoMgt">
								<ref bean="ViacaoMgr"/>
							</property>
							<property name="interfaceNumCartoesMgt">
								<ref bean="NumCartoesMgr"/>
							</property>						
						</bean>
					</xsl:otherwise>
				</xsl:choose>
				
			</xsl:if>			
			<!--Fim de Numero de Cartoes-->
			
			<!--Feature Group INTEGRACAO:-->
			<xsl:choose>				
				<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='solucaoIntegracao']='com componentes'">
					<!--Solucao com Componente-->
					<xsl:choose>
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo'">
							
							<bean id="TempoMgr" class="lps.bet.variabilidades.tempoMgr.TempoDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>	
									
							<bean id="ViagemTempoCtrl" class="lps.bet.variabilidades.viagemTempoCtrl.ViagemTempoCtrl">
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceProcessarViagem">
									<ref bean="ViagemCtrl"/>
								</property>
							</bean>
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='numero de viagens'">
							<bean id="NumViagensMgr" class="lps.bet.variabilidades.numViagensMgr.NumViagensDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>

							<bean id="ViagemNumViagensCtrl" class="lps.bet.variabilidades.viagemNumViagensCtrl.ViagemNumViagensCtrl">
								<property name="interfaceNumViagensMgt">
									<ref bean="NumViagensMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceProcessarViagem">
									<ref bean="ViagemCtrl"/>
								</property>
							</bean>
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='linha integrada'">
							<!--LINHA DE INTEGRACAO para integracao:-->	
							<bean id="GerenciaLinhaIntegrada" class="lps.bet.variabilidades.web.linhaIntegrada.GerenciaLinhaIntegrada">
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
							</bean>
							
							<bean id="LinhaIntegradaMgr" class="lps.bet.variabilidades.linhaIntegradaMgr.LinhaIntegradaDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>			
			
							<bean id="ViagemLinhaIntegradaCtrl" class="lps.bet.variabilidades.viagemLinhaIntegradaCtrl.ViagemLinhaIntegradaCtrl">
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceProcessarViagem">
									<ref bean="ViagemCtrl"/>
								</property>
							</bean>					
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens'">
							<!--Tempo e NumViagens - parte web-->
							<bean id="GerenciaSistViarioTempoNumViagens" class="lps.bet.variabilidades.web.tempoNumViagens.GerenciaSistViarioTempoNumViagens">
								<property name="interfaceViacaoMgt">
									<ref bean="ViacaoMgr"/>
								</property>
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
								<property name="interfaceNumViagensMgt">
									<ref bean="NumViagensMgr"/>
								</property>
							</bean>
							
							<bean id="TempoMgr" class="lps.bet.variabilidades.tempoMgr.TempoDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="NumViagensMgr" class="lps.bet.variabilidades.numViagensMgr.NumViagensDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							
							<!--Alternativa para o ControlarViagemTempoNumViagens, usando ainda o ControlarViagem-->
							<bean id="ViagemTempoNumViagensCtrl" class="lps.bet.variabilidades.viagemTempoNumViagensCtrl.ViagemTempoNumViagensCtrl">
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
								<property name="interfaceNumViagensMgt">
									<ref bean="NumViagensMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceProcessarViagem">
									<ref bean="ViagemCtrl"/>
								</property>
							</bean>
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, linha integrada'">
							<!--Tempo - parte web-->
							<bean id="GerenciaSistViarioTempo" class="lps.bet.variabilidades.web.tempo.GerenciaSistViarioTempo">
								<property name="interfaceViacaoMgt">
									<ref bean="ViacaoMgr"/>
								</property>
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
							</bean>
							
							<!--LINHA DE INTEGRACAO para integracao:-->	
							<bean id="GerenciaLinhaIntegrada" class="lps.bet.variabilidades.web.linhaIntegrada.GerenciaLinhaIntegrada">
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
							</bean>
							
							<bean id="TempoMgr" class="lps.bet.variabilidades.tempoMgr.TempoDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="LinhaIntegradaMgr" class="lps.bet.variabilidades.linhaIntegradaMgr.LinhaIntegradaDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							
							<bean id="ViagemTempoLinhaIntegradaCtrl" class="lps.bet.variabilidades.viagemTempoLinhaIntegradaCtrl.ViagemTempoLinhaIntegradaCtrl">
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceProcessarViagem">
									<ref bean="ViagemCtrl"/>
								</property>
							</bean>
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='numero de viagens, linha integrada'">
							<!--LINHA DE INTEGRACAO para integracao:-->	
							<bean id="GerenciaLinhaIntegrada" class="lps.bet.variabilidades.web.linhaIntegrada.GerenciaLinhaIntegrada">
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
							</bean>
							
							<bean id="NumViagensMgr" class="lps.bet.variabilidades.numViagensMgr.NumViagensDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="LinhaIntegradaMgr" class="lps.bet.variabilidades.linhaIntegradaMgr.LinhaIntegradaDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							
							<!--Alternativa para as variabilidades NumViagens e Linha Integrando, usando ainda o ControlarViagem-->
							<bean id="ViagemNumViagensLinhaIntegradaCtrl" class="lps.bet.variabilidades.viagemNumViagensLinhaIntegradaCtrl.ViagemNumViagensLinhaIntegradaCtrl">
								<property name="interfaceNumViagensMgt">
									<ref bean="NumViagensMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceProcessarViagem">
									<ref bean="ViagemCtrl"/>
								</property>
							</bean>
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens, linha integrada'">
							<!--Tempo e NumViagens - parte web-->
							<bean id="GerenciaSistViarioTempoNumViagens" class="lps.bet.variabilidades.web.tempoNumViagens.GerenciaSistViarioTempoNumViagens">
								<property name="interfaceViacaoMgt">
									<ref bean="ViacaoMgr"/>
								</property>
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
								<property name="interfaceNumViagensMgt">
									<ref bean="NumViagensMgr"/>
								</property>
							</bean>
							
							<!--LINHA DE INTEGRACAO para integracao:-->	
							<bean id="GerenciaLinhaIntegrada" class="lps.bet.variabilidades.web.linhaIntegrada.GerenciaLinhaIntegrada">
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
							</bean>
							
							<bean id="TempoMgr" class="lps.bet.variabilidades.tempoMgr.TempoDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="NumViagensMgr" class="lps.bet.variabilidades.numViagensMgr.NumViagensDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="LinhaIntegradaMgr" class="lps.bet.variabilidades.linhaIntegradaMgr.LinhaIntegradaDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							
							<!--Alternativa para as variabilidades Tempo, NumViagens e Linha Integrando, usando ainda o ControlarViagem-->
							<bean id="ViagemTempoNumViagensLinhaIntegradaCtrl" class="lps.bet.variabilidades.viagemTempoNumViagensLinhaIntegradaCtrl.ViagemTempoNumViagensLinhaIntegradaCtrl">
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
								<property name="interfaceNumViagensMgt">
									<ref bean="NumViagensMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceProcessarViagem">
									<ref bean="ViagemCtrl"/>
								</property>
							</bean>	
						</xsl:when>
						
					</xsl:choose>
				</xsl:when>
				
				
				<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='solucaoIntegracao']='com aspectos'">
				<!--Solucao com Aspecto-->
					<xsl:choose>
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo'">
							<bean id="TempoMgr" class="lps.bet.variabilidades.tempoMgr.TempoDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="ViagemTempoCtrlAspecto" class="lps.bet.variabilidades.ViagemTempoCtrl" factory-method="aspectOf">
									<property name="interfaceTempoMgt">
										<ref bean="TempoMgr"/>
									</property>
									<property name="interfaceCartaoMgt">
										<ref bean="CartaoMgr"/>
									</property>
									<property name="interfaceRegistrarArrecadacao">
										<ref bean="LinhaMgr"/>
									</property>
							</bean>								
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='numero de viagens'">
							<bean id="NumViagensMgr" class="lps.bet.variabilidades.numViagensMgr.NumViagensDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							
							<bean id="ViagemNumViagensCtrlAspecto" class="lps.bet.variabilidades.ViagemNumViagensCtrl" factory-method="aspectOf">
									<property name="interfaceNumViagensMgt">
										<ref bean="NumViagensMgr"/>
									</property>
									<property name="interfaceCartaoMgt">
										<ref bean="CartaoMgr"/>
									</property>
									<property name="interfaceRegistrarArrecadacao">
										<ref bean="LinhaMgr"/>
									</property>
								</bean>
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='linha integrada'">
							<!--LINHA DE INTEGRACAO para integracao:-->	
							<bean id="GerenciaLinhaIntegrada" class="lps.bet.variabilidades.web.linhaIntegrada.GerenciaLinhaIntegrada">
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
							</bean>
							
							<bean id="LinhaIntegradaMgr" class="lps.bet.variabilidades.linhaIntegradaMgr.LinhaIntegradaDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>								
							</bean>
							<bean id="ViagemLinhaIntegradaCtrlAspecto" class="lps.bet.variabilidades.ViagemLinhaIntegradaCtrl" factory-method="aspectOf">
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
							</bean>
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens'">
							<!--Tempo e NumViagens - parte web-->
							<bean id="GerenciaSistViarioTempoNumViagens" class="lps.bet.variabilidades.web.tempoNumViagens.GerenciaSistViarioTempoNumViagens">
								<property name="interfaceViacaoMgt">
									<ref bean="ViacaoMgr"/>
								</property>
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
								<property name="interfaceNumViagensMgt">
									<ref bean="NumViagensMgr"/>
								</property>
							</bean>
	
							<bean id="TempoMgr" class="lps.bet.variabilidades.tempoMgr.TempoDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="NumViagensMgr" class="lps.bet.variabilidades.numViagensMgr.NumViagensDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="ViagemTempoNumViagensCtrlAspecto" class="lps.bet.variabilidades.ViagemTempoNumViagensCtrl" factory-method="aspectOf">
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
								<property name="interfaceNumViagensMgt">
									<ref bean="NumViagensMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
							</bean>
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, linha integrada'">
							<!--LINHA DE INTEGRACAO para integracao:-->	
							<bean id="GerenciaLinhaIntegrada" class="lps.bet.variabilidades.web.linhaIntegrada.GerenciaLinhaIntegrada">
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
							</bean>
							
							<bean id="TempoMgr" class="lps.bet.variabilidades.tempoMgr.TempoDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="LinhaIntegradaMgr" class="lps.bet.variabilidades.linhaIntegradaMgr.LinhaIntegradaDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							
							<bean id="ViagemTempoLinhaIntegradaCtrlAspecto" class="lps.bet.variabilidades.ViagemTempoLinhaIntegradaCtrl" factory-method="aspectOf">
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
							</bean>
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='numero de viagens, linha integrada'">
							<!--LINHA DE INTEGRACAO para integracao:-->	
							<bean id="GerenciaLinhaIntegrada" class="lps.bet.variabilidades.web.linhaIntegrada.GerenciaLinhaIntegrada">
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
							</bean>
							
							<bean id="NumViagensMgr" class="lps.bet.variabilidades.numViagensMgr.NumViagensDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="LinhaIntegradaMgr" class="lps.bet.variabilidades.linhaIntegradaMgr.LinhaIntegradaDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							
							<bean id="ViagemNumViagensLinhaIntegradaCtrlAspecto" class="lps.bet.variabilidades.ViagemNumViagensLinhaIntegradaCtrl" factory-method="aspectOf">
								<property name="interfaceNumViagensMgt">
									<ref bean="NumViagensMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
							</bean>
						</xsl:when>
						
						<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens, linha integrada'">
							<!--Tempo e NumViagens - parte web-->
							<bean id="GerenciaSistViarioTempoNumViagens" class="lps.bet.variabilidades.web.tempoNumViagens.GerenciaSistViarioTempoNumViagens">
								<property name="interfaceViacaoMgt">
									<ref bean="ViacaoMgr"/>
								</property>
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
								<property name="interfaceNumViagensMgt">
									<ref bean="NumViagensMgr"/>
								</property>
							</bean>
	
							<!--LINHA DE INTEGRACAO para integracao:-->	
							<bean id="GerenciaLinhaIntegrada" class="lps.bet.variabilidades.web.linhaIntegrada.GerenciaLinhaIntegrada">
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
							</bean>
							<bean id="TempoMgr" class="lps.bet.variabilidades.tempoMgr.TempoDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="NumViagensMgr" class="lps.bet.variabilidades.numViagensMgr.NumViagensDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							<bean id="LinhaIntegradaMgr" class="lps.bet.variabilidades.linhaIntegradaMgr.LinhaIntegradaDAO">
								<property name="sessionFactory">
									<ref bean="sessionFactory"/>
								</property>
							</bean>
							
							<bean id="ViagemTempoNumViagensLinhaIntegradaCtrlAspecto" class="lps.bet.variabilidades.ViagemTempoNumViagensLinhaIntegradaCtrl" factory-method="aspectOf">
								<property name="interfaceTempoMgt">
									<ref bean="TempoMgr"/>
								</property>
								<property name="interfaceNumViagensMgt">
									<ref bean="NumViagensMgr"/>
								</property>
								<property name="interfaceLinhaIntegradaMgt">
									<ref bean="LinhaIntegradaMgr"/>
								</property>
								<property name="interfaceLinhaMgt">
									<ref bean="LinhaMgr"/>
								</property>
								<property name="interfaceCartaoMgt">
									<ref bean="CartaoMgr"/>
								</property>
								<property name="interfaceRegistrarArrecadacao">
									<ref bean="LinhaMgr"/>
								</property>
							</bean>
						</xsl:when>

					</xsl:choose>
					
				</xsl:when>
				
			</xsl:choose>
			<!--FIM da Feature Group INTEGRACAO-->
			
		</beans>
		
	</xsl:template>
</xsl:stylesheet>