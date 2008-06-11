<?xml version="1.0" encoding="ISO-8859-1"?>
		
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="xml" encoding="ISO-8859-1" indent="yes"/> 
		 
	<xsl:template match="/">
		<div class="header">
			<div class="top_info">&#160;</div>
			<div class="logo">				
				<h1><a href="#" title="BET Gestão"><span class="dark">BET</span>Gestão</a></h1>
				<h2><xsl:value-of select="formsData/forms/form[@variant='Variabilidades']/data/textatt[@name='1']"/></h2>
			</div>
		</div>
		<div class="bar menu">
			<ul>
				<li nivel="1"><a href="acessoBasico.html">Consulta</a>		
				</li>
				<li nivel="5"><a href="#">Viação</a>
					<ul>
						<li nivel="5"><a href="gerenciaEmprViaria.html">Empresa Viária</a></li>
						<li nivel="5"><a href="gerenciaSistViarioUrbano.html">Sistema Viário</a></li>
						<li nivel="5"><a href="gerenciaTarifa.html">Tarifa</a></li>
					</ul> 					
				</li>
				<li nivel="3"><a href="#">Cartão</a>
					<ul>
						<li nivel="3"><a href="gerenciaCartao.html">Cartão</a></li>
						<li nivel="5"><a href="gerenciaTipoPassageiro.html">Tipo Passageiro</a></li>
						<li nivel="5"><a href="gerenciaPagamento.html">Pagamento</a></li>
						<li nivel="5"><a href="gerenciaViagem.html">Viagem</a></li>
						<li nivel="3"><a href="cargaCartao.html">Carga Cartão</a></li>
					</ul>
				</li>
				<li nivel="5"><a href="#">Funcionário</a>
					<ul>
						<li nivel="5"><a href="gerenciaFuncionario.html">Funcionário</a></li>
						<li nivel="5"><a href="gerenciaCargo.html">Cargo</a></li>
					</ul> 
				</li>
				<li nivel="3"><a href="#">Passageiro</a>				
					<ul>
						<li nivel="3"><a href="gerenciaPassageiro.html">Passageiro</a></li>
						<xsl:if test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='terminal']='existente'">
							<li nivel="3"><a href="gerenciaEmprUsuaria.html">Empresa Usuária</a></li>	
						</xsl:if>						
					</ul>
				</li>			
				<li nivel="3"><a href="#">Linha</a>				
					<ul>
						<li nivel="5"><a href="gerenciaLinha.html">Linha</a></li>
						
						<xsl:choose>
							<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='linha integrada'">
								<li nivel="5"><a href="gerenciaLinhaIntegrada.html">Linha Integrada</a></li>
							</xsl:when>
<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, linha integrada'">
								<li nivel="5"><a href="gerenciaLinhaIntegrada.html">Linha Integrada</a></li>
							</xsl:when>
<xsl:when test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='integracao']='tempo, numero de viagens, linha integrada'">
								<li nivel="5"><a href="gerenciaLinhaIntegrada.html">Linha Integrada</a></li>
							</xsl:when>							
						</xsl:choose>						
						
						<li nivel="5"><a href="gerenciaCorrida.html">Corrida</a></li>						
						<li nivel="5"><a href="gerenciaValidador.html">Validador</a></li>
						<li nivel="5"><a href="gerenciaOnibus.html">Ônibus</a></li>
						<xsl:if test="formsData/forms/form/form[@variant='Formas de Integracao']/data/combo[@name='terminal']='existente'">
							<li nivel="5"><a href="gerenciaTerminal.html">Terminal</a></li>						
						</xsl:if>
					</ul> 
				</li>
				<li nivel="3"><a href="#">Relatórios</a>				
					<ul>
						<li nivel="5"><a href="relatorioUsuario.html">Relatório Diário de Usuários</a></li>											
					</ul> 
				</li>
				<li nivel="0" class="logout"><a href="logout.html?operacao=logout">Sair</a></li>
			</ul>
		</div>
		
	</xsl:template>
</xsl:stylesheet>