--
-- PostgreSQL database dump
--

-- Started on 2008-08-02 10:46:18

SET client_encoding = 'LATIN1';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1791 (class 1262 OID 16404)
-- Name: BET; Type: DATABASE; Schema: -; Owner: root
--


CREATE DATABASE "BET2" WITH TEMPLATE = template0 ENCODING = 'LATIN1';


ALTER DATABASE "BET2" OWNER TO root;

--\connect "BET2"

SET client_encoding = 'LATIN1';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1792 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: root
--

COMMENT ON SCHEMA public IS 'Standard public schema';


--
-- TOC entry 287 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: root
--

CREATE PROCEDURAL LANGUAGE plpgsql;


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1292 (class 1259 OID 50392)
-- Dependencies: 1648 4
-- Name: cargo; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE cargo (
    cargoid integer NOT NULL,
    nomecargo character varying(255),
    nivelacessodefault integer DEFAULT 3
);


ALTER TABLE public.cargo OWNER TO root;

--
-- TOC entry 1293 (class 1259 OID 50396)
-- Dependencies: 4
-- Name: cartao; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE cartao (
    cartaoid integer NOT NULL,
    dtaquisicao timestamp without time zone,
    dtvalidade timestamp without time zone,
    saldo real,
    tipopassageiro_fk integer NOT NULL,
    passageiro_fk integer NOT NULL
);


ALTER TABLE public.cartao OWNER TO root;

--
-- TOC entry 1294 (class 1259 OID 50400)
-- Dependencies: 1649 1650 4
-- Name: corrida; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE corrida (
    corridaid integer NOT NULL,
    numcorridadia integer,
    horasaidaprevista timestamp without time zone,
    horachegadaprevista timestamp without time zone,
    horasaida timestamp without time zone,
    horachegada timestamp without time zone,
    saida boolean,
    encerrado boolean,
    linha_fk integer NOT NULL,
    onibus_fk integer NOT NULL,
    arrecadacao real,
    qtdpassageiros integer DEFAULT 0 NOT NULL,
    credito real DEFAULT 0 NOT NULL
);


ALTER TABLE public.corrida OWNER TO root;

--
-- TOC entry 1295 (class 1259 OID 50404)
-- Dependencies: 4
-- Name: empresa_viaria; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE empresa_viaria (
    empresaid integer NOT NULL,
    nomefantasia character varying(255)
);


ALTER TABLE public.empresa_viaria OWNER TO root;

--
-- TOC entry 1296 (class 1259 OID 50408)
-- Dependencies: 4
-- Name: funcionario; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE funcionario (
    usuarioid integer NOT NULL,
    nomefuncionario character varying(255),
    dtadmissao timestamp without time zone,
    empresaviaria_fk integer NOT NULL,
    cargo_fk integer NOT NULL
);


ALTER TABLE public.funcionario OWNER TO root;

--
-- TOC entry 1297 (class 1259 OID 50412)
-- Dependencies: 4
-- Name: linha; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE linha (
    linhaid integer NOT NULL,
    nomelinha character varying(255),
    pontosaida character varying(255),
    pontochegada character varying(255),
    sistviariourbano_fk integer NOT NULL
);


ALTER TABLE public.linha OWNER TO root;

--
-- TOC entry 1313 (class 1259 OID 58872)
-- Dependencies: 4
-- Name: onibus; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE onibus (
    busid integer NOT NULL,
    validador_fk integer
);


ALTER TABLE public.onibus OWNER TO root;

--
-- TOC entry 1299 (class 1259 OID 50420)
-- Dependencies: 4
-- Name: pagamento; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE pagamento (
    pgtoid integer NOT NULL,
    dtpgto timestamp without time zone,
    valorpgto real,
    cartao_fk integer NOT NULL
);


ALTER TABLE public.pagamento OWNER TO root;

--
-- TOC entry 1300 (class 1259 OID 50424)
-- Dependencies: 4
-- Name: passageiro; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE passageiro (
    passageiroid integer NOT NULL,
    nomepassageiro character varying(255),
    cpf bigint,
    varempresausuaria_fk integer
);


ALTER TABLE public.passageiro OWNER TO root;

--
-- TOC entry 1301 (class 1259 OID 50428)
-- Dependencies: 4
-- Name: sist_viario_urbano; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE sist_viario_urbano (
    viacaoid integer NOT NULL,
    nomecidade character varying(255)
);


ALTER TABLE public.sist_viario_urbano OWNER TO root;

--
-- TOC entry 1302 (class 1259 OID 50432)
-- Dependencies: 4
-- Name: tarifa; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE tarifa (
    tarifaid integer NOT NULL,
    nometarifa character varying(255),
    valortarifa real,
    dtatualizacao timestamp without time zone,
    sistviariourbano_fk integer NOT NULL
);


ALTER TABLE public.tarifa OWNER TO root;

--
-- TOC entry 1303 (class 1259 OID 50436)
-- Dependencies: 4
-- Name: tipo_passageiro; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE tipo_passageiro (
    tipoid integer NOT NULL,
    nometipo character varying(255),
    descricaotipo character varying(255),
    formapgtopassagem character varying(255),
    desconto integer
);


ALTER TABLE public.tipo_passageiro OWNER TO root;

--
-- TOC entry 1305 (class 1259 OID 50499)
-- Dependencies: 1652 4
-- Name: usuario; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE usuario (
    usuarioid integer NOT NULL,
    "login" character varying(255),
    senha character varying(255),
    nivelacesso integer DEFAULT 0
);


ALTER TABLE public.usuario OWNER TO root;

--
-- TOC entry 1298 (class 1259 OID 50416)
-- Dependencies: 1651 4
-- Name: validador; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE validador (
    validadorid integer NOT NULL,
    emcorrida boolean,
    terminal_fk integer,
    emuso boolean DEFAULT false
);


ALTER TABLE public.validador OWNER TO root;

--
-- TOC entry 1317 (class 1259 OID 59002)
-- Dependencies: 4
-- Name: var_combinacao_tipo_passageiro; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE var_combinacao_tipo_passageiro (
    tipoid integer NOT NULL,
    tipoid_incompativel integer NOT NULL
);


ALTER TABLE public.var_combinacao_tipo_passageiro OWNER TO root;

--
-- TOC entry 1311 (class 1259 OID 58835)
-- Dependencies: 4
-- Name: var_empresa_usuaria; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE var_empresa_usuaria (
    empresaid integer NOT NULL,
    contato character varying(255),
    nomefantasia character varying(255),
    razaosocial character varying(255),
    cnpj character varying(15),
    endereco character varying(255),
    telefone character varying(10),
    email character varying(255)
);


ALTER TABLE public.var_empresa_usuaria OWNER TO root;

--
-- TOC entry 1316 (class 1259 OID 58980)
-- Dependencies: 4
-- Name: var_lim_passagens_cartao; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE var_lim_passagens_cartao (
    cartaoid integer NOT NULL,
    qtdpassagensmes integer,
    dtiniciocontagem timestamp without time zone
);


ALTER TABLE public.var_lim_passagens_cartao OWNER TO root;

--
-- TOC entry 1315 (class 1259 OID 58961)
-- Dependencies: 4
-- Name: var_lim_passagens_tipo_passageiro; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE var_lim_passagens_tipo_passageiro (
    tipoid integer NOT NULL,
    limitepassagens integer
);


ALTER TABLE public.var_lim_passagens_tipo_passageiro OWNER TO root;

--
-- TOC entry 1308 (class 1259 OID 50537)
-- Dependencies: 4
-- Name: var_linha_integrada; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE var_linha_integrada (
    integracaoid integer NOT NULL,
    linhaintegrada_fk integer,
    linhaoriginal_fk integer
);


ALTER TABLE public.var_linha_integrada OWNER TO root;

--
-- TOC entry 1314 (class 1259 OID 58954)
-- Dependencies: 1657 4
-- Name: var_num_cartoes; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE var_num_cartoes (
    numcartoesid integer NOT NULL,
    limitecartoes integer DEFAULT 0
);


ALTER TABLE public.var_num_cartoes OWNER TO root;

--
-- TOC entry 1307 (class 1259 OID 50525)
-- Dependencies: 1654 4
-- Name: var_num_viagens; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE var_num_viagens (
    numviagensid integer NOT NULL,
    numviagens integer DEFAULT 0
);


ALTER TABLE public.var_num_viagens OWNER TO root;

--
-- TOC entry 1309 (class 1259 OID 50544)
-- Dependencies: 1655 4
-- Name: var_pgto_cartao_pagamento; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE var_pgto_cartao_pagamento (
    pgtoid integer NOT NULL,
    tipopagto character varying(255) DEFAULT 'carga'::character varying
);


ALTER TABLE public.var_pgto_cartao_pagamento OWNER TO root;

--
-- TOC entry 1310 (class 1259 OID 50549)
-- Dependencies: 1656 4
-- Name: var_pgto_cartao_tipo_passageiro; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE var_pgto_cartao_tipo_passageiro (
    tipoid integer NOT NULL,
    valoraquisicao real,
    pgtoaquisicaocartao boolean DEFAULT false
);


ALTER TABLE public.var_pgto_cartao_tipo_passageiro OWNER TO root;

--
-- TOC entry 1306 (class 1259 OID 50520)
-- Dependencies: 1653 4
-- Name: var_tempo; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE var_tempo (
    tempoid integer NOT NULL,
    tempomaxintegracao integer DEFAULT 0
);


ALTER TABLE public.var_tempo OWNER TO root;

--
-- TOC entry 1312 (class 1259 OID 58863)
-- Dependencies: 4
-- Name: var_terminal; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE var_terminal (
    terminalid integer NOT NULL,
    nometerminal character varying(255)
);


ALTER TABLE public.var_terminal OWNER TO root;

--
-- TOC entry 1304 (class 1259 OID 50440)
-- Dependencies: 4
-- Name: viagem; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE viagem (
    viagemid integer NOT NULL,
    hora timestamp without time zone,
    cartao_fk integer NOT NULL,
    linha_fk integer NOT NULL,
    numviagens integer
);


ALTER TABLE public.viagem OWNER TO root;

--
-- TOC entry 1763 (class 0 OID 50392)
-- Dependencies: 1292
-- Data for Name: cargo; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO cargo (cargoid, nomecargo, nivelacessodefault) VALUES (1, 'Motorista', 3);
INSERT INTO cargo (cargoid, nomecargo, nivelacessodefault) VALUES (2, 'Cobrador', 3);
INSERT INTO cargo (cargoid, nomecargo, nivelacessodefault) VALUES (3, 'Atendente', 3);
INSERT INTO cargo (cargoid, nomecargo, nivelacessodefault) VALUES (5, 'Mantenedor', 5);
INSERT INTO cargo (cargoid, nomecargo, nivelacessodefault) VALUES (4, 'Gerente', 5);


--
-- TOC entry 1764 (class 0 OID 50396)
-- Dependencies: 1293
-- Data for Name: cartao; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (5, '2007-10-30 17:03:49.218', '2009-10-30 23:59:00', 12, 2, 7);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (4, '2007-10-30 16:37:42.937', '2009-10-30 23:59:00', 0, 4, 9);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (8, '2007-12-28 10:52:37.968', '2010-12-28 23:59:00', 10, 3, 5);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (9, '2007-12-30 12:20:00', '2007-12-30 23:59:00', 0, 1, 6);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (10, '2007-12-30 15:41:29.125', '2007-12-30 23:59:00', 12, 1, 9);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (11, '2007-12-30 15:48:01.609', '2007-12-30 23:59:00', 123, 3, 6);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (13, '2007-12-30 16:34:21.75', '2007-12-30 23:59:00', 2013.13, 3, 11);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (12, '2007-12-30 16:33:29.562', '2007-12-30 23:59:00', 212.12, 2, 5);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (16, '2008-01-06 11:15:58.906', '2010-01-06 23:59:00', 10.15, 4, 17);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (15, '2008-01-06 11:15:30.203', '2008-01-06 23:59:00', 10.15, 4, 15);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (14, '2008-01-06 02:11:47.312', '2009-01-06 23:59:00', 51.259998, 4, 11);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (1, '2007-01-01 00:00:00', '2009-01-02 23:59:00', 105.5, 1, 5);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (3, '2005-01-01 00:00:00', '2010-01-01 23:59:00', 0, 4, 6);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (18, '2008-01-10 15:34:25.14', '2011-01-10 23:59:00', 400, 5, 24);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (19, '2008-01-10 15:38:45.687', '2011-01-10 23:59:00', 10, 6, 5);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (17, '2008-01-10 15:33:36.984', '2010-01-10 23:59:00', 217.5, 4, 24);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (7, '2007-11-09 12:23:50.312', '2010-11-09 23:59:00', 277.5, 4, 5);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (20, '2008-02-24 16:55:33.765', '2010-02-24 23:59:00', 10, 1, 26);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (21, '2008-02-24 18:02:23.14', '2011-02-24 23:59:00', 1052, 7, 26);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (22, '2008-03-02 14:38:13.921', '2010-03-02 23:59:00', 12, 3, 26);
INSERT INTO cartao (cartaoid, dtaquisicao, dtvalidade, saldo, tipopassageiro_fk, passageiro_fk) VALUES (6, '2007-10-30 17:49:50.343', '2010-10-30 23:59:00', 87.5, 4, 7);


--
-- TOC entry 1765 (class 0 OID 50400)
-- Dependencies: 1294
-- Data for Name: corrida; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (19, 1, '2007-11-15 08:00:00', '2007-11-15 09:00:00', NULL, NULL, false, false, 2, 1, 0, 0, 0);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (3, 3, '2007-01-01 09:00:00', '2007-01-01 10:00:00', '2007-11-06 15:47:04.765', '2007-11-06 16:09:08.484', false, true, 1, 1, 0, 0, 0);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (4, 1, '2007-10-10 07:00:00', '2007-10-10 08:00:00', '2007-11-06 16:09:18.156', '2007-11-06 16:33:56.109', false, true, 2, 1, 0, 3, 0);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (6, 1, '2007-11-02 08:00:00', '2007-11-02 09:00:00', '2007-11-06 19:40:03.203', '2007-11-07 23:26:31.671', false, true, 2, 1, 13.75, 56, 37.5);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (7, 1, '2007-11-03 08:00:00', '2007-11-03 09:00:00', '2007-11-07 23:26:38.843', '2007-11-10 15:10:28.828', false, true, 2, 1, 0, 0, 0);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (8, 1, '2007-11-04 08:00:00', '2007-11-04 09:00:00', '2007-11-10 15:10:34.765', '2007-11-10 17:13:10.125', false, true, 2, 1, 5, 16, 7.5);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (9, 1, '2007-11-05 08:00:00', '2007-11-05 09:00:00', '2007-11-10 17:13:14.625', '2007-11-20 10:40:03.406', false, true, 2, 1, 0, 2, 2.5);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (10, 1, '2007-11-06 08:00:00', '2007-11-06 09:00:00', '2007-11-20 10:48:25.031', '2007-11-26 13:47:44.203', false, true, 2, 1, 0, 1, 0);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (11, 1, '2007-11-07 08:00:00', '2007-11-07 09:00:00', '2007-11-26 13:47:51.765', '2007-11-26 14:11:03.468', false, true, 2, 1, 10, 14, 2.5);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (5, 1, '2007-11-01 08:00:00', '2007-11-01 09:00:00', '2007-11-06 16:34:07.765', '2007-11-06 19:39:40.812', false, false, 2, 9, 0, 0, 0);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (12, 1, '2007-11-08 08:00:00', '2007-11-08 09:00:00', '2007-11-26 14:11:12.781', '2008-01-08 11:33:22.843', false, true, 3, 1, 2.5, 12, 7.5);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (1, 1, '2007-01-01 07:00:00', '2007-01-01 08:00:00', '2008-01-08 11:34:12.859', '2008-01-08 12:46:56.781', false, true, 3, 1, 0, 0, 0);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (2, 1, '2007-01-01 08:00:00', '2007-01-01 09:00:00', '2008-01-08 12:47:41.359', '2008-01-10 14:06:56.75', false, true, 2, 1, 1.25, 1, 0);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (13, 1, '2007-11-09 08:00:00', '2007-11-09 09:00:00', '2008-01-10 14:07:03.984', '2008-01-10 15:25:05.093', false, true, 3, 1, 3.75, 4, 2.5);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (20, 1, '2008-10-10 10:00:00', '2008-10-10 10:30:00', NULL, NULL, false, false, 3, 1, 0, 0, 0);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (14, 1, '2007-11-10 08:00:00', '2007-11-10 09:00:00', '2008-01-10 15:25:13.515', '2008-01-11 21:07:48.062', false, true, 2, 1, 3.75, 9, 15);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (15, 1, '2007-11-11 08:00:00', '2007-11-11 09:00:00', '2008-01-11 21:07:53', '2008-01-11 21:11:14.25', false, true, 1, 1, 2, 2, 5);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (16, 1, '2007-11-12 08:00:00', '2007-11-12 09:00:00', '2008-01-11 21:11:17.359', '2008-03-19 09:27:10.703', false, true, 2, 1, 3.75, 9, 5);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (17, 1, '2007-11-13 08:00:00', '2007-11-13 09:00:00', '2008-03-19 09:27:18.281', '2008-03-19 09:34:32.562', false, true, 2, 1, 0, 2, 5);
INSERT INTO corrida (corridaid, numcorridadia, horasaidaprevista, horachegadaprevista, horasaida, horachegada, saida, encerrado, linha_fk, onibus_fk, arrecadacao, qtdpassageiros, credito) VALUES (18, 1, '2007-11-14 08:00:00', '2007-11-14 09:00:00', '2008-03-19 09:34:36.25', NULL, true, false, 2, 1, 0, 0, 0);


--
-- TOC entry 1766 (class 0 OID 50404)
-- Dependencies: 1295
-- Data for Name: empresa_viaria; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO empresa_viaria (empresaid, nomefantasia) VALUES (1, 'Donegan BET');


--
-- TOC entry 1767 (class 0 OID 50408)
-- Dependencies: 1296
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO funcionario (usuarioid, nomefuncionario, dtadmissao, empresaviaria_fk, cargo_fk) VALUES (1, 'Antônio dos Santos', '2000-05-07 10:00:00', 1, 1);
INSERT INTO funcionario (usuarioid, nomefuncionario, dtadmissao, empresaviaria_fk, cargo_fk) VALUES (2, 'Francisco das Chagas', '2002-05-07 10:00:00', 1, 2);
INSERT INTO funcionario (usuarioid, nomefuncionario, dtadmissao, empresaviaria_fk, cargo_fk) VALUES (3, 'Paula Marques Donegan', '2000-05-07 10:00:00', 1, 4);
INSERT INTO funcionario (usuarioid, nomefuncionario, dtadmissao, empresaviaria_fk, cargo_fk) VALUES (4, 'Carlos de Camargo Salles', '2002-05-07 10:00:00', 1, 3);
INSERT INTO funcionario (usuarioid, nomefuncionario, dtadmissao, empresaviaria_fk, cargo_fk) VALUES (8, 'Fulano Beltrano Cigano', '2007-10-28 16:23:50.156', 1, 3);


--
-- TOC entry 1768 (class 0 OID 50412)
-- Dependencies: 1297
-- Data for Name: linha; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO linha (linhaid, nomelinha, pontosaida, pontochegada, sistviariourbano_fk) VALUES (1, 'linha 1', 'ponto 1', 'ponto 2', 1);
INSERT INTO linha (linhaid, nomelinha, pontosaida, pontochegada, sistviariourbano_fk) VALUES (2, 'Parangaba Papicu', 'Parangaba', 'Papicu', 1);
INSERT INTO linha (linhaid, nomelinha, pontosaida, pontochegada, sistviariourbano_fk) VALUES (3, 'Grande Circular', 'ponto 1', 'ponto 2', 1);


--
-- TOC entry 1784 (class 0 OID 58872)
-- Dependencies: 1313
-- Data for Name: onibus; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO onibus (busid, validador_fk) VALUES (1, 17);
INSERT INTO onibus (busid, validador_fk) VALUES (2, 2);
INSERT INTO onibus (busid, validador_fk) VALUES (3, 3);
INSERT INTO onibus (busid, validador_fk) VALUES (4, 5);
INSERT INTO onibus (busid, validador_fk) VALUES (5, 9);
INSERT INTO onibus (busid, validador_fk) VALUES (6, 1);
INSERT INTO onibus (busid, validador_fk) VALUES (11, 15);
INSERT INTO onibus (busid, validador_fk) VALUES (12, 18);


--
-- TOC entry 1770 (class 0 OID 50420)
-- Dependencies: 1299
-- Data for Name: pagamento; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (1, '2007-12-28 16:51:35.265', 12, 3);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (2, '2007-12-28 17:12:30.234', 12, 7);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (3, '2007-12-28 21:58:30.703', 10, 1);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (4, '2007-12-28 22:00:26.625', 6, 1);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (5, '2007-12-30 12:20:53.921', 1212, 1);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (6, '2007-12-30 15:41:29.125', 10, 10);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (7, '2007-12-30 15:48:01.609', 10, 11);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (8, '2007-01-01 00:00:00', 10, 1);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (9, '2007-01-01 00:00:00', 10, 1);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (10, '2007-12-30 16:33:04.515', 321, 8);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (11, '2007-12-30 16:33:30.515', 12.12, 12);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (12, '2007-12-30 16:34:22.015', 13.13, 13);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (13, '2007-12-30 16:34:21.75', 10, 13);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (14, '2007-12-30 16:34:21.75', 10, 13);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (15, '2007-12-30 16:34:21.75', 10, 13);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (16, '2007-12-30 16:48:06.062', 1000, 13);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (17, '2007-12-30 16:48:41.328', 200, 12);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (18, '2008-01-06 11:13:48', 30, 14);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (19, '2008-01-06 11:14:39.265', 11.11, 14);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (20, '2008-01-06 11:16:31.921', 0.15000001, 16);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (21, '2008-01-06 11:16:33.718', 0.15000001, 15);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (22, '2008-01-06 11:16:35.234', 0.15000001, 14);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (23, '2008-01-07 12:22:51.828', 10, 5);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (24, '2008-01-07 15:25:15.109', 10, 16);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (25, '2008-01-07 15:25:15.828', 10, 15);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (26, '2008-01-07 15:25:16.50', 10, 14);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (27, '2008-01-10 15:33:37.703', 10, 17);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (28, '2008-01-10 15:34:25.453', 400, 18);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (29, '2008-01-10 15:34:25.14', 321, 18);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (30, '2008-01-10 15:35:00.39', 100, 17);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (31, '2008-01-10 15:37:20.953', 7.5, 17);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (32, '2008-01-10 15:37:21.437', 7.5, 7);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (33, '2008-01-10 15:38:45.687', 10, 19);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (34, '2008-01-10 15:39:18.406', 10, 19);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (35, '2008-01-12 20:59:53.875', 100, 7);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (36, '2008-01-12 21:00:54.187', 100, 17);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (37, '2008-01-12 21:00:55.187', 100, 7);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (38, '2008-02-24 16:55:34.39', 10, 20);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (39, '2008-02-24 18:02:24.281', 100, 21);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (40, '2008-02-26 21:44:53.218', 11, 21);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (41, '2008-02-26 21:46:37.984', 100, 21);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (42, '2008-02-26 21:47:08.734', 110, 21);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (43, '2008-02-26 21:48:20.703', 100, 21);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (44, '2008-02-26 21:49:05.343', 200, 21);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (45, '2008-02-26 21:50:17.50', 100, 21);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (46, '2008-02-26 21:53:25.796', 230, 21);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (47, '2008-02-26 22:01:59.109', 100, 21);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (48, '2008-02-27 21:23:05.265', 1, 21);
INSERT INTO pagamento (pgtoid, dtpgto, valorpgto, cartao_fk) VALUES (49, '2008-03-02 14:38:15.187', 12, 22);


--
-- TOC entry 1771 (class 0 OID 50424)
-- Dependencies: 1300
-- Data for Name: passageiro; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (6, 'Otavio', 998987343, NULL);
INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (7, 'Masiero', 8726812032, NULL);
INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (9, 'Fulaninha Beltraninha', 1000, NULL);
INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (10, 'teste', 1234, NULL);
INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (11, 'Passageiro de Teste', 123412341, 12);
INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (15, 'Cristina Oliveira', 33445566, 12);
INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (17, 'Carlos Alberto', 45454545, 12);
INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (18, 'Vanessa Borges', 7777777, 12);
INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (16, 'Eu Passageiro', 111111, 12);
INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (24, 'Rosana', 1111, 23);
INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (5, 'Paula', 3423430342, 23);
INSERT INTO passageiro (passageiroid, nomepassageiro, cpf, varempresausuaria_fk) VALUES (26, 'Teste NumCartoes', 101010, NULL);


--
-- TOC entry 1772 (class 0 OID 50428)
-- Dependencies: 1301
-- Data for Name: sist_viario_urbano; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO sist_viario_urbano (viacaoid, nomecidade) VALUES (1, 'Sao Carlos');


--
-- TOC entry 1773 (class 0 OID 50432)
-- Dependencies: 1302
-- Data for Name: tarifa; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO tarifa (tarifaid, nometarifa, valortarifa, dtatualizacao, sistviariourbano_fk) VALUES (1, 'Passagem', 2.5, '2007-01-01 08:00:00', 1);


--
-- TOC entry 1774 (class 0 OID 50436)
-- Dependencies: 1303
-- Data for Name: tipo_passageiro; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO tipo_passageiro (tipoid, nometipo, descricaotipo, formapgtopassagem, desconto) VALUES (1, 'estudante', 'estudante', 'manual', 50);
INSERT INTO tipo_passageiro (tipoid, nometipo, descricaotipo, formapgtopassagem, desconto) VALUES (3, 'idoso', 'isento - idosos', 'isento', 100);
INSERT INTO tipo_passageiro (tipoid, nometipo, descricaotipo, formapgtopassagem, desconto) VALUES (2, 'cobrador', 'cobrador', 'isento', 100);
INSERT INTO tipo_passageiro (tipoid, nometipo, descricaotipo, formapgtopassagem, desconto) VALUES (4, 'normal', 'Normal com debito', 'debito', 0);
INSERT INTO tipo_passageiro (tipoid, nometipo, descricaotipo, formapgtopassagem, desconto) VALUES (5, 'teste', 'teste', 'debito', 31);
INSERT INTO tipo_passageiro (tipoid, nometipo, descricaotipo, formapgtopassagem, desconto) VALUES (6, 'TipoPgto', 'Pagamento de Cartao', 'debito', 10);
INSERT INTO tipo_passageiro (tipoid, nometipo, descricaotipo, formapgtopassagem, desconto) VALUES (7, 'TesteLimite', 'Limite de Passagens', 'debito', 10);


--
-- TOC entry 1776 (class 0 OID 50499)
-- Dependencies: 1305
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (1, 'jao', 'root', 5);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (2, 'paula', 'paula', 5);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (3, 'pedro', 'root', 3);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (4, 'foo', 'root', 3);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (6, 'baz', 'root', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (7, 'oooo', 'root', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (8, 'fulano', 'beltrano', 3);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (9, 'fulaninha', 'fulaninha', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (10, 'passageiro', 'passageiro', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (11, 'passag', 'passag', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (14, 'empresa', 'empresa', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (15, 'kika', 'kika', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (17, 'carlos', 'carlos', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (18, 'van', 'van', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (16, 'eu', 'eu', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (22, 'testeempresa', 'testeempresa', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (12, 'uol', 'uol', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (13, 'teste', 'teste', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (24, 'rosana', 'rosana', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (5, 'bar', 'root', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (23, 'usp', 'usp', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (25, 'nova', 'nova', 1);
INSERT INTO usuario (usuarioid, "login", senha, nivelacesso) VALUES (26, 'testeNum', 'testeNum', 1);


--
-- TOC entry 1769 (class 0 OID 50416)
-- Dependencies: 1298
-- Data for Name: validador; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (17, false, NULL, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (2, false, NULL, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (3, false, NULL, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (5, false, NULL, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (9, false, NULL, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (15, false, NULL, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (21, false, NULL, false);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (24, false, 1, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (16, false, 3, false);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (19, false, 4, false);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (23, false, 1, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (22, false, 2, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (18, false, 1, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (25, false, 3, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (20, false, 2, true);
INSERT INTO validador (validadorid, emcorrida, terminal_fk, emuso) VALUES (1, true, NULL, true);


--
-- TOC entry 1788 (class 0 OID 59002)
-- Dependencies: 1317
-- Data for Name: var_combinacao_tipo_passageiro; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO var_combinacao_tipo_passageiro (tipoid, tipoid_incompativel) VALUES (1, 2);


--
-- TOC entry 1782 (class 0 OID 58835)
-- Dependencies: 1311
-- Data for Name: var_empresa_usuaria; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO var_empresa_usuaria (empresaid, contato, nomefantasia, razaosocial, cnpj, endereco, telefone, email) VALUES (14, 'empresa', 'empresa', 'empresa', '123', 'empresa', '33443344', 'empresa@empresa.com.br');
INSERT INTO var_empresa_usuaria (empresaid, contato, nomefantasia, razaosocial, cnpj, endereco, telefone, email) VALUES (13, 'teste', 'teste', 'teste', '34534545', 'rua tal', '40404040', 'email@isso.com.br');
INSERT INTO var_empresa_usuaria (empresaid, contato, nomefantasia, razaosocial, cnpj, endereco, telefone, email) VALUES (12, 'Otavio', 'UOL', 'UOL', '123123123', 'Av. Faria Lima', '32131231', 'uol@uol.com.br');
INSERT INTO var_empresa_usuaria (empresaid, contato, nomefantasia, razaosocial, cnpj, endereco, telefone, email) VALUES (22, 'teste', 'teste', 'teste', '34534545', 'rua talteste', '40404040', 'email@isso.com.br');
INSERT INTO var_empresa_usuaria (empresaid, contato, nomefantasia, razaosocial, cnpj, endereco, telefone, email) VALUES (23, 'USP', 'USP', 'USP', '7777777', 'avenida sao carlense', '33003300', 'usp@usp.br');
INSERT INTO var_empresa_usuaria (empresaid, contato, nomefantasia, razaosocial, cnpj, endereco, telefone, email) VALUES (25, 'Nova', 'Nova', 'Nova', '12344321', 'Rua', '33330000', 'isso@isso.com');


--
-- TOC entry 1787 (class 0 OID 58980)
-- Dependencies: 1316
-- Data for Name: var_lim_passagens_cartao; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO var_lim_passagens_cartao (cartaoid, qtdpassagensmes, dtiniciocontagem) VALUES (1, 10, '2000-01-01 00:00:00');
INSERT INTO var_lim_passagens_cartao (cartaoid, qtdpassagensmes, dtiniciocontagem) VALUES (21, 40, '2008-02-01 21:44:51.843');


--
-- TOC entry 1786 (class 0 OID 58961)
-- Dependencies: 1315
-- Data for Name: var_lim_passagens_tipo_passageiro; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO var_lim_passagens_tipo_passageiro (tipoid, limitepassagens) VALUES (1, 10);
INSERT INTO var_lim_passagens_tipo_passageiro (tipoid, limitepassagens) VALUES (2, 20);
INSERT INTO var_lim_passagens_tipo_passageiro (tipoid, limitepassagens) VALUES (3, 30);
INSERT INTO var_lim_passagens_tipo_passageiro (tipoid, limitepassagens) VALUES (4, 40);
INSERT INTO var_lim_passagens_tipo_passageiro (tipoid, limitepassagens) VALUES (5, 50);
INSERT INTO var_lim_passagens_tipo_passageiro (tipoid, limitepassagens) VALUES (6, 60);
INSERT INTO var_lim_passagens_tipo_passageiro (tipoid, limitepassagens) VALUES (7, 100);


--
-- TOC entry 1779 (class 0 OID 50537)
-- Dependencies: 1308
-- Data for Name: var_linha_integrada; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO var_linha_integrada (integracaoid, linhaintegrada_fk, linhaoriginal_fk) VALUES (1, 2, 1);
INSERT INTO var_linha_integrada (integracaoid, linhaintegrada_fk, linhaoriginal_fk) VALUES (2, 3, 2);
INSERT INTO var_linha_integrada (integracaoid, linhaintegrada_fk, linhaoriginal_fk) VALUES (3, 2, 2);
INSERT INTO var_linha_integrada (integracaoid, linhaintegrada_fk, linhaoriginal_fk) VALUES (4, 1, 1);
INSERT INTO var_linha_integrada (integracaoid, linhaintegrada_fk, linhaoriginal_fk) VALUES (5, 3, 3);
INSERT INTO var_linha_integrada (integracaoid, linhaintegrada_fk, linhaoriginal_fk) VALUES (6, 3, 1);


--
-- TOC entry 1785 (class 0 OID 58954)
-- Dependencies: 1314
-- Data for Name: var_num_cartoes; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO var_num_cartoes (numcartoesid, limitecartoes) VALUES (1, 2);


--
-- TOC entry 1778 (class 0 OID 50525)
-- Dependencies: 1307
-- Data for Name: var_num_viagens; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO var_num_viagens (numviagensid, numviagens) VALUES (1, 1);


--
-- TOC entry 1780 (class 0 OID 50544)
-- Dependencies: 1309
-- Data for Name: var_pgto_cartao_pagamento; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (3, 'Carga');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (4, 'Aquisi?o');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (5, 'Carga');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (6, 'Aquisição');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (7, 'Aquisição');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (8, 'Aquisição');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (9, 'Aquisição');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (10, 'Aquisi?o');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (13, 'Aquisição');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (14, 'Aquisição');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (15, 'Aquisição');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (23, 'Aquisi?o');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (29, 'Aquisição');
INSERT INTO var_pgto_cartao_pagamento (pgtoid, tipopagto) VALUES (33, 'Aquisição');


--
-- TOC entry 1781 (class 0 OID 50549)
-- Dependencies: 1310
-- Data for Name: var_pgto_cartao_tipo_passageiro; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO var_pgto_cartao_tipo_passageiro (tipoid, valoraquisicao, pgtoaquisicaocartao) VALUES (2, 0, false);
INSERT INTO var_pgto_cartao_tipo_passageiro (tipoid, valoraquisicao, pgtoaquisicaocartao) VALUES (3, 10, true);
INSERT INTO var_pgto_cartao_tipo_passageiro (tipoid, valoraquisicao, pgtoaquisicaocartao) VALUES (4, 0, false);
INSERT INTO var_pgto_cartao_tipo_passageiro (tipoid, valoraquisicao, pgtoaquisicaocartao) VALUES (1, 12, true);
INSERT INTO var_pgto_cartao_tipo_passageiro (tipoid, valoraquisicao, pgtoaquisicaocartao) VALUES (5, 321, true);
INSERT INTO var_pgto_cartao_tipo_passageiro (tipoid, valoraquisicao, pgtoaquisicaocartao) VALUES (6, 10, true);


--
-- TOC entry 1777 (class 0 OID 50520)
-- Dependencies: 1306
-- Data for Name: var_tempo; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO var_tempo (tempoid, tempomaxintegracao) VALUES (1, 10000);


--
-- TOC entry 1783 (class 0 OID 58863)
-- Dependencies: 1312
-- Data for Name: var_terminal; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO var_terminal (terminalid, nometerminal) VALUES (4, 'Novo Terminal');
INSERT INTO var_terminal (terminalid, nometerminal) VALUES (1, 'Parangaba');
INSERT INTO var_terminal (terminalid, nometerminal) VALUES (3, 'Centro');
INSERT INTO var_terminal (terminalid, nometerminal) VALUES (5, 'Teste Novo Terminal');
INSERT INTO var_terminal (terminalid, nometerminal) VALUES (6, 'Lagoa Redonda');
INSERT INTO var_terminal (terminalid, nometerminal) VALUES (2, 'Papicu');


--
-- TOC entry 1775 (class 0 OID 50440)
-- Dependencies: 1304
-- Data for Name: viagem; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (1, '2007-11-06 15:05:52.359', 1, 1, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (2, '2007-11-06 15:08:03.218', 1, 1, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (3, '2007-11-06 15:17:32.875', 1, 1, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (4, '2007-11-06 15:31:42.812', 1, 1, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (5, '2007-11-06 15:32:40.312', 6, 1, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (6, '2007-11-06 19:40:18.703', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (7, '2007-11-06 19:41:02.843', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (8, '2007-11-06 19:42:34.859', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (9, '2007-11-06 19:43:19.875', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (10, '2007-11-06 19:52:15.796', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (11, '2007-11-06 19:52:32.437', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (12, '2007-11-06 19:52:49.531', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (13, '2007-11-06 19:52:57.468', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (14, '2007-11-06 20:01:54.531', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (15, '2007-11-06 20:02:03.187', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (16, '2007-11-06 20:02:11.109', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (17, '2007-11-06 20:02:22.187', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (18, '2007-11-06 20:30:57.984', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (20, '2007-11-06 20:31:22.078', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (21, '2007-11-06 20:31:37.812', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (22, '2007-11-06 20:42:04.125', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (23, '2007-11-06 20:48:30.765', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (19, '2007-11-06 20:31:11.812', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (25, '2007-11-06 21:26:27.718', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (24, '2007-11-06 20:48:42.078', 6, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (26, '2007-11-06 21:26:48.437', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (27, '2007-11-06 23:08:17.843', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (28, '2007-11-06 23:08:25.796', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (29, '2007-11-06 23:08:37.14', 6, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (30, '2007-11-06 23:08:45.562', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (31, '2007-11-06 23:40:51.578', 4, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (32, '2007-11-07 19:48:19.171', 4, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (33, '2007-11-07 20:01:35.968', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (34, '2007-11-07 20:30:17.093', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (35, '2007-11-07 20:30:24.484', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (36, '2007-11-07 20:30:31.968', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (37, '2007-11-07 20:30:43.359', 4, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (38, '2007-11-07 20:30:52.25', 4, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (39, '2007-11-07 12:35:00', 6, 1, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (41, '2007-11-07 23:26:48.156', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (42, '2007-11-08 09:10:25.875', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (43, '2007-11-08 10:55:16.39', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (45, '2007-11-08 11:12:38.578', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (40, '2007-11-07 20:37:43.859', 6, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (47, '2007-11-08 11:14:38.625', 6, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (48, '2007-11-08 13:05:25.50', 6, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (46, '2007-11-08 11:12:56.781', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (50, '2007-11-08 13:05:49.50', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (52, '2007-11-08 13:58:46.921', 3, 2, 2);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (51, '2007-11-08 13:33:58.937', 1, 2, 3);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (53, '2007-11-08 19:14:28.156', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (54, '2007-11-08 19:14:42.171', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (55, '2007-11-08 22:26:05.187', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (49, '2007-11-08 13:05:36.421', 6, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (56, '2007-11-08 22:26:48.406', 6, 2, 2);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (57, '2007-11-08 22:51:59.562', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (59, '2007-11-08 22:59:52.921', 3, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (60, '2007-11-08 23:00:15.718', 6, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (61, '2007-11-08 23:00:39.062', 6, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (58, '2007-11-08 22:52:11.609', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (62, '2007-11-09 09:32:27.64', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (63, '2007-11-09 10:01:04.125', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (64, '2007-11-09 10:01:18.062', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (65, '2007-11-09 10:01:25.062', 3, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (66, '2007-11-09 10:02:02.968', 5, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (67, '2007-11-09 10:02:09.765', 6, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (68, '2007-11-10 15:10:41.406', 1, 1, 4);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (69, '2007-11-10 15:11:11.015', 6, 1, 2);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (44, '2007-11-08 10:55:51.296', 4, 2, 2);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (70, '2007-11-10 16:01:29.89', 1, 1, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (71, '2007-11-10 16:01:44.062', 1, 1, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (72, '2007-11-10 16:01:52.343', 6, 1, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (74, '2007-11-10 16:02:56.515', 6, 1, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (75, '2007-11-10 17:13:41.14', 3, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (73, '2007-11-10 16:02:47.781', 1, 1, 2);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (76, '2007-11-26 13:48:06.875', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (77, '2007-11-26 13:57:23.171', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (78, '2007-11-26 13:57:36.796', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (79, '2007-11-26 14:04:26.484', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (80, '2007-11-26 14:04:51.375', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (81, '2007-11-26 14:05:05.546', 7, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (82, '2007-11-26 14:09:21.109', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (83, '2007-11-26 14:09:42.515', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (84, '2007-11-26 14:10:55.75', 1, 2, 2);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (85, '2007-11-26 14:32:10.296', 3, 3, 2);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (86, '2007-11-26 14:33:01.421', 5, 3, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (87, '2007-11-26 14:38:20.234', 1, 3, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (88, '2007-11-26 14:38:52.312', 1, 3, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (89, '2007-11-26 14:39:03.328', 7, 3, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (90, '2007-11-26 14:39:18.281', 7, 3, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (91, '2008-01-08 12:48:07.406', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (92, '2008-01-10 14:07:16.015', 1, 3, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (93, '2008-01-10 14:07:22.468', 1, 3, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (94, '2008-01-10 14:07:57.109', 3, 3, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (95, '2008-01-10 14:08:15.046', 1, 3, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (96, '2008-01-10 15:25:24.546', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (97, '2008-01-10 15:25:32.921', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (98, '2008-01-10 15:25:45.328', 3, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (99, '2008-01-11 20:44:26.937', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (100, '2008-01-11 20:46:15', 7, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (101, '2008-01-11 20:46:23.875', 7, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (102, '2008-01-11 20:46:35.546', 7, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (103, '2008-01-11 21:05:16.046', 7, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (104, '2008-01-11 21:07:29.531', 7, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (105, '2008-01-11 21:08:01.859', 7, 1, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (106, '2008-01-11 21:11:05.421', 7, 1, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (107, '2008-01-11 23:01:12.75', 7, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (108, '2008-01-11 23:01:32.718', 7, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (109, '2008-01-12 20:41:09.859', 1, 2, 2);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (110, '2008-01-12 20:45:24.265', 1, 2, 1);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (111, '2008-01-12 20:47:43.531', 1, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (112, '2008-03-19 09:28:00.687', 6, 2, 0);
INSERT INTO viagem (viagemid, hora, cartao_fk, linha_fk, numviagens) VALUES (113, '2008-03-19 09:28:08.546', 6, 2, 0);


--
-- TOC entry 1699 (class 2606 OID 50548)
-- Dependencies: 1309 1309
-- Name: VAR_PGTO_PAGAMENTO_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY var_pgto_cartao_pagamento
    ADD CONSTRAINT "VAR_PGTO_PAGAMENTO_pkey" PRIMARY KEY (pgtoid);


--
-- TOC entry 1659 (class 2606 OID 50395)
-- Dependencies: 1292 1292
-- Name: cargo_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (cargoid);


--
-- TOC entry 1661 (class 2606 OID 50399)
-- Dependencies: 1293 1293
-- Name: cartao_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY cartao
    ADD CONSTRAINT cartao_pkey PRIMARY KEY (cartaoid);


--
-- TOC entry 1663 (class 2606 OID 50403)
-- Dependencies: 1294 1294
-- Name: corrida_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY corrida
    ADD CONSTRAINT corrida_pkey PRIMARY KEY (corridaid);


--
-- TOC entry 1665 (class 2606 OID 50407)
-- Dependencies: 1295 1295
-- Name: empresa_viaria_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY empresa_viaria
    ADD CONSTRAINT empresa_viaria_pkey PRIMARY KEY (empresaid);


--
-- TOC entry 1667 (class 2606 OID 50411)
-- Dependencies: 1296 1296
-- Name: funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (usuarioid);


--
-- TOC entry 1697 (class 2606 OID 50542)
-- Dependencies: 1308 1308
-- Name: integracaoid; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY var_linha_integrada
    ADD CONSTRAINT integracaoid PRIMARY KEY (integracaoid);


--
-- TOC entry 1669 (class 2606 OID 50415)
-- Dependencies: 1297 1297
-- Name: linha_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY linha
    ADD CONSTRAINT linha_pkey PRIMARY KEY (linhaid);


--
-- TOC entry 1710 (class 2606 OID 58958)
-- Dependencies: 1314 1314
-- Name: numcartoesid; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY var_num_cartoes
    ADD CONSTRAINT numcartoesid PRIMARY KEY (numcartoesid);


--
-- TOC entry 1693 (class 2606 OID 50529)
-- Dependencies: 1307 1307
-- Name: numviagensid; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY var_num_viagens
    ADD CONSTRAINT numviagensid PRIMARY KEY (numviagensid);


--
-- TOC entry 1671 (class 2606 OID 50419)
-- Dependencies: 1298 1298
-- Name: onibus_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY validador
    ADD CONSTRAINT onibus_pkey PRIMARY KEY (validadorid);


--
-- TOC entry 1708 (class 2606 OID 58887)
-- Dependencies: 1313 1313
-- Name: onibus_pkey1; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY onibus
    ADD CONSTRAINT onibus_pkey1 PRIMARY KEY (busid);


--
-- TOC entry 1673 (class 2606 OID 50423)
-- Dependencies: 1299 1299
-- Name: pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY pagamento
    ADD CONSTRAINT pagamento_pkey PRIMARY KEY (pgtoid);


--
-- TOC entry 1675 (class 2606 OID 50513)
-- Dependencies: 1300 1300
-- Name: passageiro_cpf_key; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY passageiro
    ADD CONSTRAINT passageiro_cpf_key UNIQUE (cpf);


--
-- TOC entry 1677 (class 2606 OID 50427)
-- Dependencies: 1300 1300
-- Name: passageiro_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY passageiro
    ADD CONSTRAINT passageiro_pkey PRIMARY KEY (passageiroid);


--
-- TOC entry 1679 (class 2606 OID 50431)
-- Dependencies: 1301 1301
-- Name: sist_viario_urbano_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY sist_viario_urbano
    ADD CONSTRAINT sist_viario_urbano_pkey PRIMARY KEY (viacaoid);


--
-- TOC entry 1681 (class 2606 OID 50435)
-- Dependencies: 1302 1302
-- Name: tarifa_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY tarifa
    ADD CONSTRAINT tarifa_pkey PRIMARY KEY (tarifaid);


--
-- TOC entry 1691 (class 2606 OID 50524)
-- Dependencies: 1306 1306
-- Name: tempoID; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY var_tempo
    ADD CONSTRAINT "tempoID" PRIMARY KEY (tempoid);


--
-- TOC entry 1705 (class 2606 OID 58866)
-- Dependencies: 1312 1312
-- Name: terminal_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY var_terminal
    ADD CONSTRAINT terminal_pkey PRIMARY KEY (terminalid);


--
-- TOC entry 1683 (class 2606 OID 50439)
-- Dependencies: 1303 1303
-- Name: tipo_passageiro_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY tipo_passageiro
    ADD CONSTRAINT tipo_passageiro_pkey PRIMARY KEY (tipoid);


--
-- TOC entry 1687 (class 2606 OID 50502)
-- Dependencies: 1305 1305
-- Name: usuarioID; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT "usuarioID" PRIMARY KEY (usuarioid);


--
-- TOC entry 1689 (class 2606 OID 50511)
-- Dependencies: 1305 1305
-- Name: usuario_login_key; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_login_key UNIQUE ("login");


--
-- TOC entry 1716 (class 2606 OID 59005)
-- Dependencies: 1317 1317 1317
-- Name: var_combinacao_tipo_passageiro_pk; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY var_combinacao_tipo_passageiro
    ADD CONSTRAINT var_combinacao_tipo_passageiro_pk PRIMARY KEY (tipoid, tipoid_incompativel);


--
-- TOC entry 1703 (class 2606 OID 58838)
-- Dependencies: 1311 1311
-- Name: var_empresa_usuaria_restricao_pk; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY var_empresa_usuaria
    ADD CONSTRAINT var_empresa_usuaria_restricao_pk PRIMARY KEY (empresaid);


--
-- TOC entry 1714 (class 2606 OID 58983)
-- Dependencies: 1316 1316
-- Name: var_lim_passagens_cartao_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY var_lim_passagens_cartao
    ADD CONSTRAINT var_lim_passagens_cartao_pkey PRIMARY KEY (cartaoid);


--
-- TOC entry 1712 (class 2606 OID 58964)
-- Dependencies: 1315 1315
-- Name: var_lim_passagens_tipo_passageiro_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY var_lim_passagens_tipo_passageiro
    ADD CONSTRAINT var_lim_passagens_tipo_passageiro_pkey PRIMARY KEY (tipoid);


--
-- TOC entry 1701 (class 2606 OID 50553)
-- Dependencies: 1310 1310
-- Name: var_pgto_cartao_tipo_passageiro_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY var_pgto_cartao_tipo_passageiro
    ADD CONSTRAINT var_pgto_cartao_tipo_passageiro_pkey PRIMARY KEY (tipoid);


--
-- TOC entry 1685 (class 2606 OID 50443)
-- Dependencies: 1304 1304
-- Name: viagem_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY viagem
    ADD CONSTRAINT viagem_pkey PRIMARY KEY (viagemid);


--
-- TOC entry 1694 (class 1259 OID 58937)
-- Dependencies: 1308
-- Name: fki_linhaintegrada_fk; Type: INDEX; Schema: public; Owner: root; Tablespace: 
--

CREATE INDEX fki_linhaintegrada_fk ON var_linha_integrada USING btree (linhaintegrada_fk);


--
-- TOC entry 1695 (class 1259 OID 58943)
-- Dependencies: 1308
-- Name: fki_linhaoriginal_fk; Type: INDEX; Schema: public; Owner: root; Tablespace: 
--

CREATE INDEX fki_linhaoriginal_fk ON var_linha_integrada USING btree (linhaoriginal_fk);


--
-- TOC entry 1706 (class 1259 OID 58926)
-- Dependencies: 1313
-- Name: fki_validador_fk; Type: INDEX; Schema: public; Owner: root; Tablespace: 
--

CREATE INDEX fki_validador_fk ON onibus USING btree (validador_fk);


--
-- TOC entry 1759 (class 2606 OID 58989)
-- Dependencies: 1316 1293 1660
-- Name: cartaoid; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_lim_passagens_cartao
    ADD CONSTRAINT cartaoid FOREIGN KEY (cartaoid) REFERENCES cartao(cartaoid);


--
-- TOC entry 1737 (class 2606 OID 58839)
-- Dependencies: 1300 1311 1702
-- Name: fk27045e58172707ff; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY passageiro
    ADD CONSTRAINT fk27045e58172707ff FOREIGN KEY (varempresausuaria_fk) REFERENCES var_empresa_usuaria(empresaid);


--
-- TOC entry 1738 (class 2606 OID 50603)
-- Dependencies: 1686 1305 1300
-- Name: fk27045e58e2439770; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY passageiro
    ADD CONSTRAINT fk27045e58e2439770 FOREIGN KEY (passageiroid) REFERENCES usuario(usuarioid);


--
-- TOC entry 1735 (class 2606 OID 50598)
-- Dependencies: 1299 1660 1293
-- Name: fk2e9a13264b5f81f5; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY pagamento
    ADD CONSTRAINT fk2e9a13264b5f81f5 FOREIGN KEY (cartao_fk) REFERENCES cartao(cartaoid);


--
-- TOC entry 1722 (class 2606 OID 50573)
-- Dependencies: 1294 1668 1297
-- Name: fk38eeb77a49908bb3; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY corrida
    ADD CONSTRAINT fk38eeb77a49908bb3 FOREIGN KEY (linha_fk) REFERENCES linha(linhaid);


--
-- TOC entry 1723 (class 2606 OID 50568)
-- Dependencies: 1294 1298 1670
-- Name: fk38eeb77aa07d54f5; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY corrida
    ADD CONSTRAINT fk38eeb77aa07d54f5 FOREIGN KEY (onibus_fk) REFERENCES validador(validadorid);


--
-- TOC entry 1721 (class 2606 OID 58867)
-- Dependencies: 1294 1670 1298
-- Name: fk38eeb77aecfee01f; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY corrida
    ADD CONSTRAINT fk38eeb77aecfee01f FOREIGN KEY (onibus_fk) REFERENCES validador(validadorid);


--
-- TOC entry 1746 (class 2606 OID 58944)
-- Dependencies: 1668 1308 1297
-- Name: fk40df03704b1b8e42; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_linha_integrada
    ADD CONSTRAINT fk40df03704b1b8e42 FOREIGN KEY (linhaoriginal_fk) REFERENCES linha(linhaid);


--
-- TOC entry 1745 (class 2606 OID 58949)
-- Dependencies: 1297 1668 1308
-- Name: fk40df0370a4c8c32a; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_linha_integrada
    ADD CONSTRAINT fk40df0370a4c8c32a FOREIGN KEY (linhaintegrada_fk) REFERENCES linha(linhaid);


--
-- TOC entry 1732 (class 2606 OID 50474)
-- Dependencies: 1301 1678 1297
-- Name: fk451572a49f81bbd; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY linha
    ADD CONSTRAINT fk451572a49f81bbd FOREIGN KEY (sistviariourbano_fk) REFERENCES sist_viario_urbano(viacaoid);


--
-- TOC entry 1728 (class 2606 OID 50578)
-- Dependencies: 1296 1658 1292
-- Name: fk50401ddb1bd5e73; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fk50401ddb1bd5e73 FOREIGN KEY (cargo_fk) REFERENCES cargo(cargoid);


--
-- TOC entry 1726 (class 2606 OID 50588)
-- Dependencies: 1296 1664 1295
-- Name: fk50401ddb9539faf3; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fk50401ddb9539faf3 FOREIGN KEY (empresaviaria_fk) REFERENCES empresa_viaria(empresaid);


--
-- TOC entry 1727 (class 2606 OID 50583)
-- Dependencies: 1296 1686 1305
-- Name: fk50401ddbb1824ec6; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fk50401ddbb1824ec6 FOREIGN KEY (usuarioid) REFERENCES usuario(usuarioid);


--
-- TOC entry 1731 (class 2606 OID 50593)
-- Dependencies: 1297 1678 1301
-- Name: fk6234f4a9ef2c6f5; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY linha
    ADD CONSTRAINT fk6234f4a9ef2c6f5 FOREIGN KEY (sistviariourbano_fk) REFERENCES sist_viario_urbano(viacaoid);


--
-- TOC entry 1724 (class 2606 OID 50459)
-- Dependencies: 1294 1297 1668
-- Name: fk63ba435a4fdc984; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY corrida
    ADD CONSTRAINT fk63ba435a4fdc984 FOREIGN KEY (linha_fk) REFERENCES linha(linhaid);


--
-- TOC entry 1725 (class 2606 OID 50454)
-- Dependencies: 1298 1670 1294
-- Name: fk63ba435a52b7d144; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY corrida
    ADD CONSTRAINT fk63ba435a52b7d144 FOREIGN KEY (onibus_fk) REFERENCES validador(validadorid);


--
-- TOC entry 1719 (class 2606 OID 50449)
-- Dependencies: 1300 1676 1293
-- Name: fk760f334e2c4bf892; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY cartao
    ADD CONSTRAINT fk760f334e2c4bf892 FOREIGN KEY (passageiro_fk) REFERENCES passageiro(passageiroid);


--
-- TOC entry 1720 (class 2606 OID 50444)
-- Dependencies: 1303 1682 1293
-- Name: fk760f334e8da140a8; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY cartao
    ADD CONSTRAINT fk760f334e8da140a8 FOREIGN KEY (tipopassageiro_fk) REFERENCES tipo_passageiro(tipoid);


--
-- TOC entry 1752 (class 2606 OID 58844)
-- Dependencies: 1305 1686 1311
-- Name: fk7bcf305211c4b161; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_empresa_usuaria
    ADD CONSTRAINT fk7bcf305211c4b161 FOREIGN KEY (empresaid) REFERENCES usuario(usuarioid);


--
-- TOC entry 1755 (class 2606 OID 58975)
-- Dependencies: 1682 1303 1315
-- Name: fk812dc9909f769a4c; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_lim_passagens_tipo_passageiro
    ADD CONSTRAINT fk812dc9909f769a4c FOREIGN KEY (tipoid) REFERENCES tipo_passageiro(tipoid);


--
-- TOC entry 1740 (class 2606 OID 50484)
-- Dependencies: 1302 1678 1301
-- Name: fk93116d1f49f81bbd; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY tarifa
    ADD CONSTRAINT fk93116d1f49f81bbd FOREIGN KEY (sistviariourbano_fk) REFERENCES sist_viario_urbano(viacaoid);


--
-- TOC entry 1744 (class 2606 OID 50489)
-- Dependencies: 1297 1668 1304
-- Name: fk96e418814fdc984; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY viagem
    ADD CONSTRAINT fk96e418814fdc984 FOREIGN KEY (linha_fk) REFERENCES linha(linhaid);


--
-- TOC entry 1743 (class 2606 OID 50494)
-- Dependencies: 1660 1293 1304
-- Name: fk96e41881d319b948; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY viagem
    ADD CONSTRAINT fk96e41881d319b948 FOREIGN KEY (cartao_fk) REFERENCES cartao(cartaoid);


--
-- TOC entry 1717 (class 2606 OID 50563)
-- Dependencies: 1676 1300 1293
-- Name: fkae7c3f4e6fb9d35; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY cartao
    ADD CONSTRAINT fkae7c3f4e6fb9d35 FOREIGN KEY (passageiro_fk) REFERENCES passageiro(passageiroid);


--
-- TOC entry 1718 (class 2606 OID 50558)
-- Dependencies: 1293 1303 1682
-- Name: fkae7c3f4e99de0c55; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY cartao
    ADD CONSTRAINT fkae7c3f4e99de0c55 FOREIGN KEY (tipopassageiro_fk) REFERENCES tipo_passageiro(tipoid);


--
-- TOC entry 1733 (class 2606 OID 58914)
-- Dependencies: 1704 1312 1298
-- Name: fkbdd9cf4244489bc6; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY validador
    ADD CONSTRAINT fkbdd9cf4244489bc6 FOREIGN KEY (terminal_fk) REFERENCES var_terminal(terminalid);


--
-- TOC entry 1753 (class 2606 OID 58927)
-- Dependencies: 1670 1298 1313
-- Name: fkc3a93fd6c6010933; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY onibus
    ADD CONSTRAINT fkc3a93fd6c6010933 FOREIGN KEY (validador_fk) REFERENCES validador(validadorid);


--
-- TOC entry 1758 (class 2606 OID 58994)
-- Dependencies: 1660 1293 1316
-- Name: fkc85c73613efceb88; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_lim_passagens_cartao
    ADD CONSTRAINT fkc85c73613efceb88 FOREIGN KEY (cartaoid) REFERENCES cartao(cartaoid);


--
-- TOC entry 1739 (class 2606 OID 50608)
-- Dependencies: 1301 1678 1302
-- Name: fkcb7e791f9ef2c6f5; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY tarifa
    ADD CONSTRAINT fkcb7e791f9ef2c6f5 FOREIGN KEY (sistviariourbano_fk) REFERENCES sist_viario_urbano(viacaoid);


--
-- TOC entry 1742 (class 2606 OID 50623)
-- Dependencies: 1297 1668 1304
-- Name: fkcf51248149908bb3; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY viagem
    ADD CONSTRAINT fkcf51248149908bb3 FOREIGN KEY (linha_fk) REFERENCES linha(linhaid);


--
-- TOC entry 1741 (class 2606 OID 50628)
-- Dependencies: 1293 1660 1304
-- Name: fkcf5124814b5f81f5; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY viagem
    ADD CONSTRAINT fkcf5124814b5f81f5 FOREIGN KEY (cartao_fk) REFERENCES cartao(cartaoid);


--
-- TOC entry 1729 (class 2606 OID 50469)
-- Dependencies: 1296 1664 1295
-- Name: fkd093b1bb54da1b2b; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fkd093b1bb54da1b2b FOREIGN KEY (empresaviaria_fk) REFERENCES empresa_viaria(empresaid);


--
-- TOC entry 1730 (class 2606 OID 50464)
-- Dependencies: 1658 1296 1292
-- Name: fkd093b1bb892ea0d3; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fkd093b1bb892ea0d3 FOREIGN KEY (cargo_fk) REFERENCES cargo(cargoid);


--
-- TOC entry 1736 (class 2606 OID 50479)
-- Dependencies: 1660 1293 1299
-- Name: fkd4b22306d319b948; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY pagamento
    ADD CONSTRAINT fkd4b22306d319b948 FOREIGN KEY (cartao_fk) REFERENCES cartao(cartaoid);


--
-- TOC entry 1750 (class 2606 OID 50643)
-- Dependencies: 1682 1310 1303
-- Name: fkec5fa0679f769a4c; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_pgto_cartao_tipo_passageiro
    ADD CONSTRAINT fkec5fa0679f769a4c FOREIGN KEY (tipoid) REFERENCES tipo_passageiro(tipoid);


--
-- TOC entry 1757 (class 2606 OID 58965)
-- Dependencies: 1303 1682 1315
-- Name: fkec5fa0679f769a4c; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_lim_passagens_tipo_passageiro
    ADD CONSTRAINT fkec5fa0679f769a4c FOREIGN KEY (tipoid) REFERENCES tipo_passageiro(tipoid);


--
-- TOC entry 1760 (class 2606 OID 58984)
-- Dependencies: 1316 1660 1293
-- Name: fkec5fa0679f769a4c; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_lim_passagens_cartao
    ADD CONSTRAINT fkec5fa0679f769a4c FOREIGN KEY (cartaoid) REFERENCES cartao(cartaoid);


--
-- TOC entry 1748 (class 2606 OID 58932)
-- Dependencies: 1668 1308 1297
-- Name: linhaintegrada_fk; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_linha_integrada
    ADD CONSTRAINT linhaintegrada_fk FOREIGN KEY (linhaintegrada_fk) REFERENCES linha(linhaid);


--
-- TOC entry 1747 (class 2606 OID 58938)
-- Dependencies: 1668 1308 1297
-- Name: linhaoriginal_fk; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_linha_integrada
    ADD CONSTRAINT linhaoriginal_fk FOREIGN KEY (linhaoriginal_fk) REFERENCES linha(linhaid);


--
-- TOC entry 1749 (class 2606 OID 50638)
-- Dependencies: 1299 1672 1309
-- Name: pgtoid; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_pgto_cartao_pagamento
    ADD CONSTRAINT pgtoid FOREIGN KEY (pgtoid) REFERENCES pagamento(pgtoid);


--
-- TOC entry 1751 (class 2606 OID 50633)
-- Dependencies: 1303 1682 1310
-- Name: tipoid; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_pgto_cartao_tipo_passageiro
    ADD CONSTRAINT tipoid FOREIGN KEY (tipoid) REFERENCES tipo_passageiro(tipoid);


--
-- TOC entry 1756 (class 2606 OID 58970)
-- Dependencies: 1303 1682 1315
-- Name: tipoid; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_lim_passagens_tipo_passageiro
    ADD CONSTRAINT tipoid FOREIGN KEY (tipoid) REFERENCES tipo_passageiro(tipoid);


--
-- TOC entry 1754 (class 2606 OID 58921)
-- Dependencies: 1313 1670 1298
-- Name: validador_fk; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY onibus
    ADD CONSTRAINT validador_fk FOREIGN KEY (validador_fk) REFERENCES validador(validadorid);


--
-- TOC entry 1734 (class 2606 OID 58909)
-- Dependencies: 1704 1298 1312
-- Name: validador_terminal_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY validador
    ADD CONSTRAINT validador_terminal_fk_fkey FOREIGN KEY (terminal_fk) REFERENCES var_terminal(terminalid);


--
-- TOC entry 1762 (class 2606 OID 59006)
-- Dependencies: 1317 1682 1303
-- Name: var_combinacao_tipo_passageiro_fk1; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_combinacao_tipo_passageiro
    ADD CONSTRAINT var_combinacao_tipo_passageiro_fk1 FOREIGN KEY (tipoid) REFERENCES tipo_passageiro(tipoid);


--
-- TOC entry 1761 (class 2606 OID 59011)
-- Dependencies: 1317 1682 1303
-- Name: var_combinacao_tipo_passageiro_fk2; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY var_combinacao_tipo_passageiro
    ADD CONSTRAINT var_combinacao_tipo_passageiro_fk2 FOREIGN KEY (tipoid_incompativel) REFERENCES tipo_passageiro(tipoid);


--
-- TOC entry 1793 (class 0 OID 0)
-- Dependencies: 4
-- Name: public; Type: ACL; Schema: -; Owner: root
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM root;
GRANT ALL ON SCHEMA public TO root;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2008-08-02 10:47:09

--
-- PostgreSQL database dump complete
--

