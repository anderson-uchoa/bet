# LPS-BET #

A família de aplicações desenvolvida para a geração de produtos com arquitetura baseada em componentes é a de controle de Bilhetes Eletrônicos para Transporte municipal, denominada sistema BET. Sistemas desse tipo têm o propósito de facilitar o uso do transporte municipal, fornecendo diversas vantagens ao passageiro, como o uso de um cartão como forma de pagamento das passagens.

O software deve permitir que a rede BET trabalhe de forma computadorizada e que a empresa de transportes possua um computador central que mantenha os dados de todos os passageiros, cartões, itinerários, ônibus e viagens realizadas. Os ônibus têm uma leitora instalada que aceita um cartão como entrada e comunica-se com o computador da empresa por rádio para realizar o débito do valor correspondente no cartão do passageiro. Pode ainda existir um sistema de integração de ônibus, que permite ao usuário pagar um único valor de passagem, sujeito a algumas restrições, dependendo das particularidades de cada membro da família. Além disso, o sistema deve cuidar da armazenagem das viagens e de medidas de segurança. Existem também terminais espalhados nas agências da empresa de transportes, que permitem que o passageiro faça consulta sobre suas viagens.

Dessa forma, uma parte do sistema BET corresponde a um sistema de informação e outra a um sistema de tempo real. Os requisitos referentes ao armazenamento e manipulação de dados, como viagens e passageiros, são característicos de sistemas de informação. As funcionalidades que estão relacionadas à leitora instalada em cada ônibus corresponde ao sistema de tempo real, pois suas características dependem do cumprimento de requisitos temporais e lógicos, como a liberação da catraca para o passageiro passar e travar a catraca assim que ele passar por ela, além da comunicação com o sistema central.
O domínio do sistema BET será desenvolvido visando a geração de três produtos, a partir de uma análise feita em três sistemas reais existentes de três municípios brasileiros, os sistemas BET de São Carlos, Fortaleza e Campo Grande.


## São Carlos ##

O Sistema BET de São Carlos possui um sistema de integração temporal. Pode então ser usado o cartão do passageiro para debitar o valor de uma passagem no caso de uma viagem regular, ou no caso de ser uma viagem de integração não haverá um novo débito de passagem. No sistema de transporte de São Carlos não existem terminais para integração. O passageiro também pode acessar informações do sistema, como saldo e viagens, e pode imprimir um extrato. Existem diversos tipos de passageiros que possuem descontos diferenciados e uma quantidade máxima de passagens que podem carregar nos cartões mensalmente, havendo também uma combinação de cartões que podem adquirir.

Os requisitos do sistema BET de São Carlos foram levantados considerando  informações obtidas a partir de usuários e o próprio uso do sistema.

## Fortaleza ##

O Sistema BET de Fortaleza não possui um sistema de integração temporal, possuindo apenas a forma de integração pelo uso de terminais em determinados pontos na cidade. Nesse caso não precisa ser passado o cartão novamente na leitora para debitar a viagem do passageiro. Existem também diversos tipos de passageiros, entre os quais o estudante, que possui um desconto de 50% no valor da passagem. Entretanto, o pagamento deve ser feito durante a viagem, não havendo carga do cartão.

Os requisitos do sistema BET foram levantados considerando sites (http://www.vtefortaleza.com.br, http://www.sindionibus.com.br),  informações obtidas a partir de usuários e o próprio uso do sistema.

## Campo Grande ##

O Sistema BET de Campo Grande possui duas formas possíveis de integração: temporal e por terminais. Pode então ser usado o cartão do passageiro para debitar o valor de uma passagem no caso de uma viagem regular, ou no caso de ser uma viagem de integração não haverá um novo débito de passagem. Assim como, podem ser usados terminais para a troca de passageiros entre linhas de ônibus. A viagem de integração temporal só permite que seja feita uma integração. O passageiro também pode acessar informações do sistema, como saldo e viagens, e pode imprimir um extrato.  Os diversos tipos de passageiros possuem descontos variados para realizar a carga dos cartões e podem realizar qualquer combinação de cartões que desejarem.

Os requisitos do sistema BET foram levantados considerando o site do transporte coletivo de Campo Grande (http://www.assetur.com.br/pagintegracao.html) e informações obtidas a partir de usuários do sistema.