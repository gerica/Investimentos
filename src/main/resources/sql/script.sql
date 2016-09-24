-- Table: empresa

-- DROP TABLE empresa;

CREATE TABLE empresa
(
  id integer NOT NULL,
  nome text,
  ativo boolean,
  CONSTRAINT id_carteira_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE empresa
  OWNER TO postgres;
  
ALTER TABLE empresa
ADD setor text,
ADD subsetor text;


-- Table: curva_patrimonio

-- DROP TABLE curva_patrimonio;

CREATE TABLE curva_patrimonio
(
  id integer NOT NULL,
  data date,
  renda_variavel real,
  renda_fixa real,
  valor_total real,
  dois_procento real,
  seis_porcento real,
  variacao real,  
  CONSTRAINT id_curva_patrimonio_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE curva_patrimonio
  OWNER TO postgres;

  
-- DROP TABLE site_feed;

CREATE TABLE site_feed
(
  id integer NOT NULL,
  url text,
  ativo boolean,
  CONSTRAINT id_site_feed_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE site_feed
  OWNER TO postgres;
  
-- DROP TABLE feed;

CREATE TABLE feed
(
  id integer NOT NULL,
  title text,
  link text,
  description text,
  language text,
  copyright text,
  lastBuildDate date,
  CONSTRAINT id_feed_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE feed
  OWNER TO postgres;

-- DROP TABLE feed_message;

CREATE TABLE feed_message
(
  id integer NOT NULL,
  pubDate timestamp,
  title text,
  link text,
  description text,
  author text,
  guid text,
  lido boolean,
  favorito boolean,
  id_feed integer,
  CONSTRAINT id_feed_message_pk PRIMARY KEY (id),
  CONSTRAINT id_feed_fk FOREIGN KEY (id_feed)
      REFERENCES feed (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE feed_message
  OWNER TO postgres;

  
-- DROP TABLE configuracao_analise_cotacoes;

CREATE TABLE configuracao_analise_cotacoes
(
  id integer NOT NULL,
  qtd_dias_apresentar_cotacoes integer,
  risco_stop_loss integer,
  risco_stop_win integer,
  qtd_dias_calculo_stop_loss integer,
  qtd_dias_calculo_stop_win integer,
  id_usuario integer,
  CONSTRAINT id_configuracao_analise_cotacoes_pk PRIMARY KEY (id),
  CONSTRAINT id_usuario_configuracao_fk FOREIGN KEY (id_usuario)
      REFERENCES usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE configuracao_analise_cotacoes
  OWNER TO postgres;

    
-- DROP TABLE fundamento;

CREATE TABLE fundamento
(
	id integer NOT NULL,
	id_papel integer,
	cotacao real,
	dataUltimaCotacao date,
	min52Semanas real,
	max52Semanas real,
	volumeNegociacao2Meses real,
	valorMercado real,
	valorFirma real,
	ultimoBalanco date,	
	numeroAcoes real,	
	dia real,
	mes real,
	trintaDias real,
	dozeMeses real,
	oscilacao2015 real,
	oscilacao2014 real,
	oscilacao2013 real,
	oscilacao2012 real,
	oscilacao2011 real,
	oscilacao2010 real,
	p_l real,
	p_vp real,
	p_ebit real,
	psr real,
	p_ativos real,
	p_capitacaoGiro real,
	p_ativoCirculanteLiquido real,
	dividendoYield real,
	ev_ebit real,
	giroAtivos real,
	crescrimentoReceitaLiquidaCincoAnos real,
	lucroPorAcao real,
	valorPatrimonialPorAcao real,
	margemBruto real,
	margemEbit real,
	margemLiquida real,
	ebit_ativo real,
	roic real,
	roe real,
	liquidesCorrente real,
	dividaBruta_Patrimonio real,
	ativo real,
	disponibilidades real,
	ativoCirculante real,
	dividaBruta real,
	dividaLiquida real,
	patrimonioLiquido real,
	receitaLiquidaDozeMeses real,
	ebitDozeMeses real,
	lucroLiquidoDozeMeses real,
	receitaLiquidaTresMeses real,
	ebitTresMeses real,
	lucroLiquidoTresMeses real,

  CONSTRAINT id_fundamento_pk PRIMARY KEY (id),
  CONSTRAINT id_papel_fundamento_fk FOREIGN KEY (id_papel)
      REFERENCES papel (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fundamento
  OWNER TO postgres;
