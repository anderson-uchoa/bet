--
-- PostgreSQL database dump
--

-- Started on 2007-10-28 14:12:58

SET client_encoding = 'LATIN1';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1684 (class 1262 OID 16404)
-- Name: BET; Type: DATABASE; Schema: -; Owner: root
--

CREATE DATABASE "BET" WITH TEMPLATE = template0 ENCODING = 'LATIN1';


ALTER DATABASE "BET" OWNER TO root;

--\connect "BET"

SET client_encoding = 'LATIN1';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1685 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: root
--

COMMENT ON SCHEMA public IS 'Standard public schema';


--
-- TOC entry 275 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: root
--

CREATE PROCEDURAL LANGUAGE plpgsql;


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1280 (class 1259 OID 50392)
-- Dependencies: 4
-- Name: cargo; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE cargo (
    cargoid integer NOT NULL,
    nomecargo character varying(255),
    nivelacessodefault integer DEFAULT 3
);


ALTER TABLE public.cargo OWNER TO root;

--
-- TOC entry 1281 (class 1259 OID 50396)
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
-- TOC entry 1282 (class 1259 OID 50400)
-- Dependencies: 4
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
    qtdpassageiros integer NOT NULL DEFAULT 0,
    credito real NOT NULL DEFAULT 0
);


ALTER TABLE public.corrida OWNER TO root;

--
-- TOC entry 1283 (class 1259 OID 50404)
-- Dependencies: 4
-- Name: empresa_viaria; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE empresa_viaria (
    empresaid integer NOT NULL,
    nomefantasia character varying(255)
);


ALTER TABLE public.empresa_viaria OWNER TO root;

--
-- TOC entry 1284 (class 1259 OID 50408)
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
-- TOC entry 1285 (class 1259 OID 50412)
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
-- TOC entry 1286 (class 1259 OID 50416)
-- Dependencies: 4
-- Name: onibus; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE onibus (
    onibusid integer NOT NULL,
    emcorrida boolean
);


ALTER TABLE public.onibus OWNER TO root;

--
-- TOC entry 1287 (class 1259 OID 50420)
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
-- TOC entry 1288 (class 1259 OID 50424)
-- Dependencies: 4
-- Name: passageiro; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE passageiro (
    passageiroid integer NOT NULL,
    nomepassageiro character varying(255),
    cpf bigint
);


ALTER TABLE public.passageiro OWNER TO root;

--
-- TOC entry 1289 (class 1259 OID 50428)
-- Dependencies: 4
-- Name: sist_viario_urbano; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE sist_viario_urbano (
    viacaoid integer NOT NULL,
    nomecidade character varying(255)
);


ALTER TABLE public.sist_viario_urbano OWNER TO root;

--
-- TOC entry 1290 (class 1259 OID 50432)
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
-- TOC entry 1291 (class 1259 OID 50436)
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
-- TOC entry 1293 (class 1259 OID 50499)
-- Dependencies: 1624 4
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
-- TOC entry 1292 (class 1259 OID 50440)
-- Dependencies: 4
-- Name: viagem; Type: TABLE; Schema: public; Owner: root; Tablespace: 
--

CREATE TABLE viagem (
    viagemid integer NOT NULL,
    hora timestamp without time zone,
    cartao_fk integer NOT NULL,
    linha_fk integer NOT NULL
);

ALTER TABLE public.viagem OWNER TO root;


-- ALTERACOES PARA A VARIABILIDADE COMBINACAO DE CARTOES (Alex,28.11.07)
CREATE TABLE tipo_passageiro_restricao
(
  tipoid integer NOT NULL,
  tipoid_incompativel integer NOT NULL
);

ALTER TABLE public.tipo_passageiro_restricao OWNER TO root;
-- FIM ALTERACOES PARA A VARIABILIDADE COMBINACAO DE CARTOES (Alex,28.11.07)



--
-- TOC entry 1626 (class 2606 OID 50395)
-- Dependencies: 1280 1280
-- Name: cargo_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (cargoid);


--
-- TOC entry 1628 (class 2606 OID 50399)
-- Dependencies: 1281 1281
-- Name: cartao_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY cartao
    ADD CONSTRAINT cartao_pkey PRIMARY KEY (cartaoid);


--
-- TOC entry 1630 (class 2606 OID 50403)
-- Dependencies: 1282 1282
-- Name: corrida_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY corrida
    ADD CONSTRAINT corrida_pkey PRIMARY KEY (corridaid);


--
-- TOC entry 1632 (class 2606 OID 50407)
-- Dependencies: 1283 1283
-- Name: empresa_viaria_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY empresa_viaria
    ADD CONSTRAINT empresa_viaria_pkey PRIMARY KEY (empresaid);


--
-- TOC entry 1634 (class 2606 OID 50411)
-- Dependencies: 1284 1284
-- Name: funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (usuarioid);


--
-- TOC entry 1636 (class 2606 OID 50415)
-- Dependencies: 1285 1285
-- Name: linha_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY linha
    ADD CONSTRAINT linha_pkey PRIMARY KEY (linhaid);


--
-- TOC entry 1638 (class 2606 OID 50419)
-- Dependencies: 1286 1286
-- Name: onibus_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY onibus
    ADD CONSTRAINT onibus_pkey PRIMARY KEY (onibusid);


--
-- TOC entry 1640 (class 2606 OID 50423)
-- Dependencies: 1287 1287
-- Name: pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY pagamento
    ADD CONSTRAINT pagamento_pkey PRIMARY KEY (pgtoid);


--
-- TOC entry 1642 (class 2606 OID 50513)
-- Dependencies: 1288 1288
-- Name: passageiro_cpf_key; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY passageiro
    ADD CONSTRAINT passageiro_cpf_key UNIQUE (cpf);


--
-- TOC entry 1644 (class 2606 OID 50427)
-- Dependencies: 1288 1288
-- Name: passageiro_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY passageiro
    ADD CONSTRAINT passageiro_pkey PRIMARY KEY (passageiroid);


--
-- TOC entry 1646 (class 2606 OID 50431)
-- Dependencies: 1289 1289
-- Name: sist_viario_urbano_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY sist_viario_urbano
    ADD CONSTRAINT sist_viario_urbano_pkey PRIMARY KEY (viacaoid);


--
-- TOC entry 1648 (class 2606 OID 50435)
-- Dependencies: 1290 1290
-- Name: tarifa_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY tarifa
    ADD CONSTRAINT tarifa_pkey PRIMARY KEY (tarifaid);


--
-- TOC entry 1650 (class 2606 OID 50439)
-- Dependencies: 1291 1291
-- Name: tipo_passageiro_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY tipo_passageiro
    ADD CONSTRAINT tipo_passageiro_pkey PRIMARY KEY (tipoid);


--
-- TOC entry 1654 (class 2606 OID 50502)
-- Dependencies: 1293 1293
-- Name: usuarioID; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT "usuarioID" PRIMARY KEY (usuarioid);


--
-- TOC entry 1656 (class 2606 OID 50511)
-- Dependencies: 1293 1293
-- Name: usuario_login_key; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_login_key UNIQUE ("login");


--
-- TOC entry 1652 (class 2606 OID 50443)
-- Dependencies: 1292 1292
-- Name: viagem_pkey; Type: CONSTRAINT; Schema: public; Owner: root; Tablespace: 
--

ALTER TABLE ONLY viagem
    ADD CONSTRAINT viagem_pkey PRIMARY KEY (viagemid);


--
-- TOC entry 1663 (class 2606 OID 50474)
-- Dependencies: 1289 1285 1645
-- Name: fk451572a49f81bbd; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY linha
    ADD CONSTRAINT fk451572a49f81bbd FOREIGN KEY (sistviariourbano_fk) REFERENCES sist_viario_urbano(viacaoid);


--
-- TOC entry 1659 (class 2606 OID 50459)
-- Dependencies: 1282 1635 1285
-- Name: fk63ba435a4fdc984; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY corrida
    ADD CONSTRAINT fk63ba435a4fdc984 FOREIGN KEY (linha_fk) REFERENCES linha(linhaid);


--
-- TOC entry 1660 (class 2606 OID 50454)
-- Dependencies: 1282 1286 1637
-- Name: fk63ba435a52b7d144; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY corrida
    ADD CONSTRAINT fk63ba435a52b7d144 FOREIGN KEY (onibus_fk) REFERENCES onibus(onibusid);


--
-- TOC entry 1657 (class 2606 OID 50449)
-- Dependencies: 1281 1288 1643
-- Name: fk760f334e2c4bf892; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY cartao
    ADD CONSTRAINT fk760f334e2c4bf892 FOREIGN KEY (passageiro_fk) REFERENCES passageiro(passageiroid);


--
-- TOC entry 1658 (class 2606 OID 50444)
-- Dependencies: 1281 1291 1649
-- Name: fk760f334e8da140a8; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY cartao
    ADD CONSTRAINT fk760f334e8da140a8 FOREIGN KEY (tipopassageiro_fk) REFERENCES tipo_passageiro(tipoid);


--
-- TOC entry 1665 (class 2606 OID 50484)
-- Dependencies: 1290 1289 1645
-- Name: fk93116d1f49f81bbd; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY tarifa
    ADD CONSTRAINT fk93116d1f49f81bbd FOREIGN KEY (sistviariourbano_fk) REFERENCES sist_viario_urbano(viacaoid);


--
-- TOC entry 1667 (class 2606 OID 50489)
-- Dependencies: 1635 1292 1285
-- Name: fk96e418814fdc984; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY viagem
    ADD CONSTRAINT fk96e418814fdc984 FOREIGN KEY (linha_fk) REFERENCES linha(linhaid);


--
-- TOC entry 1666 (class 2606 OID 50494)
-- Dependencies: 1627 1281 1292
-- Name: fk96e41881d319b948; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY viagem
    ADD CONSTRAINT fk96e41881d319b948 FOREIGN KEY (cartao_fk) REFERENCES cartao(cartaoid);


--
-- TOC entry 1661 (class 2606 OID 50469)
-- Dependencies: 1284 1283 1631
-- Name: fkd093b1bb54da1b2b; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fkd093b1bb54da1b2b FOREIGN KEY (empresaviaria_fk) REFERENCES empresa_viaria(empresaid);


--
-- TOC entry 1662 (class 2606 OID 50464)
-- Dependencies: 1625 1280 1284
-- Name: fkd093b1bb892ea0d3; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fkd093b1bb892ea0d3 FOREIGN KEY (cargo_fk) REFERENCES cargo(cargoid);


--
-- TOC entry 1664 (class 2606 OID 50479)
-- Dependencies: 1287 1281 1627
-- Name: fkd4b22306d319b948; Type: FK CONSTRAINT; Schema: public; Owner: root
--


-- ALTERACOES PARA A VARIABILIDADE COMBINACAO DE CARTOES (Alex,28.11.07)
ALTER TABLE ONLY pagamento
    ADD CONSTRAINT fkd4b22306d319b948 FOREIGN KEY (cartao_fk) REFERENCES cartao(cartaoid);

ALTER TABLE ONLY tipo_passageiro_restricao
    ADD CONSTRAINT tipo_passageiro_restricao_pk PRIMARY KEY (tipoid, tipoid_incompativel);

ALTER TABLE ONLY tipo_passageiro_restricao  
    ADD CONSTRAINT tipo_passageiro_restricao_fk1 FOREIGN KEY (tipoid)
      REFERENCES tipo_passageiro (tipoid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE ONLY tipo_passageiro_restricao      
    ADD CONSTRAINT tipo_passageiro_restricao_fk2 FOREIGN KEY (tipoid_incompativel)
      REFERENCES tipo_passageiro (tipoid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
-- FIM DAS ALTERACOES PARA A VARIABILIDADE COMBINACAO DE CARTOES (Alex,28.11.07)

--
-- TOC entry 1686 (class 0 OID 0)
-- Dependencies: 4
-- Name: public; Type: ACL; Schema: -; Owner: root
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM root;
GRANT ALL ON SCHEMA public TO root;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2007-10-28 14:13:00

--
-- PostgreSQL database dump complete
--

