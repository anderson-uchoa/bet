--
-- PostgreSQL database dump
--

-- Started on 2007-09-01 11:01:22

SET client_encoding = 'LATIN1';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 1659 (class 0 OID 33454)
-- Dependencies: 1280
-- Data for Name: cartao; Type: TABLE DATA; Schema: public; Owner: root
--

DELETE FROM pagamento;
DELETE FROM viagem;
DELETE FROM cartao;
DELETE FROM tipo_passageiro;
DELETE FROM tarifa;
DELETE FROM corrida;
DELETE FROM linha;
DELETE FROM onibus;
DELETE FROM passageiro;
DELETE FROM funcionario;
DELETE FROM cargo;
DELETE FROM empresa_viaria;
DELETE FROM sist_viario_urbano;
DELETE FROM usuario;

INSERT INTO passageiro VALUES (5, 'Paula', 3423430342);
INSERT INTO passageiro VALUES (6, 'Otavio', 998987343);
INSERT INTO passageiro VALUES (7, 'Masiero', 8726812032);

INSERT INTO sist_viario_urbano VALUES (1, 'Basico');
INSERT INTO empresa_viaria VALUES (1, 'Donegan BET');

INSERT INTO cargo VALUES (1, 'Motorista', 3);
INSERT INTO cargo VALUES (2, 'Cobrador', 3);
INSERT INTO cargo VALUES (3, 'Atendente', 3);
INSERT INTO cargo VALUES (4, 'Gerente', 5);
INSERT INTO cargo VALUES (5, 'Manutenedor', 5);

INSERT INTO funcionario VALUES (1, 'Antônio dos Santos', '2000-05-07 10:00:00', 1, 1);
INSERT INTO funcionario VALUES (2, 'Francisco das Chagas', '2002-05-07 10:00:00', 1, 2);
INSERT INTO funcionario VALUES (3, 'Paula Marques Donegan', '2000-05-07 10:00:00', 1, 4);
INSERT INTO funcionario VALUES (4, 'Carlos de Camargo Salles', '2002-05-07 10:00:00', 1, 3);

INSERT INTO linha  VALUES (1, 'linha 1', 'ponto 1', 'ponto 2',1);

INSERT INTO onibus  VALUES (1, false);

INSERT INTO corrida  VALUES (3, 3, '2007-01-01 09:00:00', '2007-01-01 10:00:00', NULL, NULL, false, false, 1, 1, 0);
INSERT INTO corrida  VALUES (2, 2, '2007-01-01 08:00:00', '2007-01-01 09:00:00', NULL, NULL, false, false, 1, 1, 0);
INSERT INTO corrida  VALUES (1, 1, '2007-01-01 07:00:00', '2007-01-01 08:00:00', NULL, NULL, false, false, 1, 1, 0);

INSERT INTO tarifa VALUES (1, 'Passagem', 2.50, '2007-01-01 08:00:00', 1);

INSERT INTO tipo_passageiro  VALUES (1, 'estudante', 'estudante', 'manual', 50);
INSERT INTO tipo_passageiro  VALUES (3, 'idoso', 'isento - idosos', 'isento', 100);
INSERT INTO tipo_passageiro  VALUES (2, 'cobrador', 'cobrador', 'isento',100);

INSERT INTO cartao  VALUES (1, '2007-01-01 00:00:00', '2008-01-02 00:00:00', 0, 1, 5);
INSERT INTO cartao  VALUES (3, '2005-01-01 00:00:00', '2006-01-01 00:00:00', 0, 1, 6);
INSERT INTO cartao  VALUES (2, '2006-01-01 00:00:00', '2009-08-31 00:00:00', 0, 1, 7);

INSERT INTO usuario VALUES (1, 'jao', 'root',5);
INSERT INTO usuario VALUES (2, 'paula', 'paula',5);
INSERT INTO usuario VALUES (3, 'pedro', 'root',3);
INSERT INTO usuario VALUES (4, 'foo', 'root',3);
INSERT INTO usuario VALUES (5, 'bar', 'root',1);
INSERT INTO usuario VALUES (6, 'baz', 'root',1);
INSERT INTO usuario VALUES (7, 'oooo', 'root',1);
