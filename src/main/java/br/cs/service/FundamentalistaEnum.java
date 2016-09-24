package br.cs.service;

import br.cs.component.util.FormatadorUtil;

public enum FundamentalistaEnum
{
  NULL,  PAPEL,  TIPO,  EMPRESA,  SETOR,  SUBSETOR,  COTACAO,  DATAULTCOT,  MIN52SEM,  MAX52SEM,  VOLMED2M,  VALORDEMERCADO,  ULTBALANCOPROCESSADO,  VALORDAFIRMA,  NROACOES,  DIA,  PL,  LPA,  MES,  PVP,  VPA,  _30DIAS,  PEBIT,  MARGBRUTA,  _12MESES,  PSR,  MARGEBIT,  _2015,  PATIVOS,  MARGLIQUIDA,  _2014,  PCAPGIRO,  EBITATIVO,  _2013,  PATIVCIRCLIQ,  ROIC,  _2012,  DIVYIELD,  ROE,  _2011,  EVEBIT,  LIQUIDEZCORR,  _2010,  GIROATIVOS,  DIVBRPATRIM,  CRESREC5A,  ATIVO,  DIVBRUTA,  DISPONIBILIDADES,  DIVLIQUIDA,  ATIVOCIRCULANTE,  PATRIMLIQ,  RECEITALIQUIDA,  EBIT,  LUCROLIQUIDO;
  
  public static FundamentalistaEnum get(String texto)
  {
    FundamentalistaEnum retorno = NULL;
    if (texto.toUpperCase().equals(PAPEL.toString())) {
      retorno = PAPEL;
    } else if (texto.toUpperCase().equals(TIPO.toString())) {
      retorno = TIPO;
    } else if (texto.toUpperCase().equals(EMPRESA.toString())) {
      retorno = EMPRESA;
    } else if (texto.toUpperCase().equals(SETOR.toString())) {
      retorno = SETOR;
    } else if (texto.toUpperCase().equals(SUBSETOR.toString())) {
      retorno = SUBSETOR;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(COTACAO.toString())) {
      retorno = COTACAO;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(DATAULTCOT.toString())) {
      retorno = DATAULTCOT;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(MIN52SEM.toString())) {
      retorno = MIN52SEM;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(MAX52SEM.toString())) {
      retorno = MAX52SEM;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(VOLMED2M.toString())) {
      retorno = VOLMED2M;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(VALORDEMERCADO.toString())) {
      retorno = VALORDEMERCADO;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(ULTBALANCOPROCESSADO.toString())) {
      retorno = ULTBALANCOPROCESSADO;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(VALORDAFIRMA.toString())) {
      retorno = VALORDAFIRMA;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(NROACOES.toString())) {
      retorno = NROACOES;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(DIA.toString())) {
      retorno = DIA;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(PL.toString())) {
      retorno = PL;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(LPA.toString())) {
      retorno = LPA;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(MES.toString())) {
      retorno = MES;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(PVP.toString())) {
      retorno = PVP;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(VPA.toString())) {
      retorno = VPA;
    } else if (("_" + FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase()).equals(_30DIAS.toString())) {
      retorno = _30DIAS;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(PEBIT.toString())) {
      retorno = PEBIT;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(MARGBRUTA.toString())) {
      retorno = MARGBRUTA;
    } else if (("_" + FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase()).equals(_12MESES.toString())) {
      retorno = _12MESES;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(PSR.toString())) {
      retorno = PSR;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(MARGEBIT.toString())) {
      retorno = MARGEBIT;
    } else if (("_" + FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase()).equals(_2015.toString())) {
      retorno = _2015;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(PATIVOS.toString())) {
      retorno = PATIVOS;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(MARGLIQUIDA.toString())) {
      retorno = MARGLIQUIDA;
    } else if (("_" + FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase()).equals(_2014.toString())) {
      retorno = _2014;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(PCAPGIRO.toString())) {
      retorno = PCAPGIRO;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(EBITATIVO.toString())) {
      retorno = EBITATIVO;
    } else if (("_" + FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase()).equals(_2013.toString())) {
      retorno = _2013;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(PATIVCIRCLIQ.toString())) {
      retorno = PATIVCIRCLIQ;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(ROIC.toString())) {
      retorno = ROIC;
    } else if (("_" + FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase()).equals(_2012.toString())) {
      retorno = _2012;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(DIVYIELD.toString())) {
      retorno = DIVYIELD;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(ROE.toString())) {
      retorno = ROE;
    } else if (("_" + FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase()).equals(_2011.toString())) {
      retorno = _2011;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(EVEBIT.toString())) {
      retorno = EVEBIT;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(LIQUIDEZCORR.toString())) {
      retorno = LIQUIDEZCORR;
    } else if (("_" + FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase()).equals(_2010.toString())) {
      retorno = _2010;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(GIROATIVOS.toString())) {
      retorno = GIROATIVOS;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(DIVBRPATRIM.toString())) {
      retorno = DIVBRPATRIM;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(CRESREC5A.toString())) {
      retorno = CRESREC5A;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(ATIVO.toString())) {
      retorno = ATIVO;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(DIVBRUTA.toString())) {
      retorno = DIVBRUTA;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(DISPONIBILIDADES.toString())) {
      retorno = DISPONIBILIDADES;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(DIVLIQUIDA.toString())) {
      retorno = DIVLIQUIDA;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(ATIVOCIRCULANTE.toString())) {
      retorno = ATIVOCIRCULANTE;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(PATRIMLIQ.toString())) {
      retorno = PATRIMLIQ;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(RECEITALIQUIDA.toString())) {
      retorno = RECEITALIQUIDA;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(EBIT.toString())) {
      retorno = EBIT;
    } else if (FormatadorUtil.retirarCaracteresEspaco(texto).toUpperCase().equals(LUCROLIQUIDO.toString())) {
      retorno = LUCROLIQUIDO;
    }
    return retorno;
  }
}
