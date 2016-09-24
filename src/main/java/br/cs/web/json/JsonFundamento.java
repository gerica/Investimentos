package br.cs.web.json;

import br.cs.component.util.FormatadorUtil;
import br.cs.entity.Fundamento;

public class JsonFundamento
{
  private String id;
  private String papel;
  private String cotacao;
  private String dataUltimaCotacao;
  private String min52Semanas;
  private String max52Semanas;
  private String volumeNegociacao2Meses;
  private String valorMercado;
  private String valorFirma;
  private String ultimoBalanco;
  private String numeroAcoes;
  private String dia;
  private String mes;
  private String trintaDias;
  private String dozeMeses;
  private String oscilacao2015;
  private String oscilacao2014;
  private String oscilacao2013;
  private String oscilacao2012;
  private String oscilacao2011;
  private String oscilacao2010;
  private String p_l;
  private String p_vp;
  private String p_ebit;
  private String psr;
  private String p_ativos;
  private String p_capitacaoGiro;
  private String p_ativoCirculanteLiquido;
  private String dividendoYield;
  private String ev_ebit;
  private String giroAtivos;
  private String crescrimentoReceitaLiquidaCincoAnos;
  private String lucroPorAcao;
  private String valorPatrimonialPorAcao;
  private String margemBruto;
  private String margemEbit;
  private String margemLiquida;
  private String ebit_ativo;
  private String roic;
  private String roe;
  private String liquidesCorrente;
  private String dividaBruta_Patrimonio;
  private String ativo;
  private String disponibilidades;
  private String ativoCirculante;
  private String dividaBruta;
  private String dividaLiquida;
  private String patrimonioLiquido;
  private String receitaLiquidaDozeMeses;
  private String ebitDozeMeses;
  private String lucroLiquidoDozeMeses;
  private String receitaLiquidaTresMeses;
  private String ebitTresMeses;
  private String lucroLiquidoTresMeses;
  
  public JsonFundamento(Fundamento fundamento)
  {
    this.id = fundamento.getId().toString();
    this.papel = fundamento.getPapel().getNome();
    this.cotacao = FormatadorUtil.formatarMoeda(fundamento.getCotacao());
    this.dataUltimaCotacao = FormatadorUtil.formatarData(fundamento.getDataUltimaCotacao());
    this.min52Semanas = FormatadorUtil.formatarMoeda(fundamento.getMin52Semanas());
    this.max52Semanas = FormatadorUtil.formatarMoeda(fundamento.getMax52Semanas());
    this.volumeNegociacao2Meses = FormatadorUtil.formatarMoeda(fundamento.getVolumeNegociacao2Meses());
    this.valorMercado = FormatadorUtil.formatarMoeda(fundamento.getValorMercado());
    this.valorFirma = FormatadorUtil.formatarMoeda(fundamento.getValorFirma());
    this.ultimoBalanco = FormatadorUtil.formatarData(fundamento.getUltimoBalanco());
    this.numeroAcoes = FormatadorUtil.formatarMoeda(fundamento.getNumeroAcoes());
    this.dia = FormatadorUtil.formatarPorcentagem(fundamento.getDia());
    this.mes = FormatadorUtil.formatarPorcentagem(fundamento.getMes());
    this.trintaDias = FormatadorUtil.formatarPorcentagem(fundamento.getTrintaDias());
    this.dozeMeses = FormatadorUtil.formatarPorcentagem(fundamento.getDozeMeses());
    this.oscilacao2015 = FormatadorUtil.formatarPorcentagem(fundamento.getOscilacao2015());
    this.oscilacao2014 = FormatadorUtil.formatarPorcentagem(fundamento.getOscilacao2014());
    this.oscilacao2013 = FormatadorUtil.formatarPorcentagem(fundamento.getOscilacao2013());
    this.oscilacao2012 = FormatadorUtil.formatarPorcentagem(fundamento.getOscilacao2012());
    this.oscilacao2011 = FormatadorUtil.formatarPorcentagem(fundamento.getOscilacao2011());
    this.oscilacao2010 = FormatadorUtil.formatarPorcentagem(fundamento.getOscilacao2010());
    this.p_l = FormatadorUtil.formatarMoeda(fundamento.getP_l());
    this.p_vp = FormatadorUtil.formatarMoeda(fundamento.getP_vp());
    this.p_ebit = FormatadorUtil.formatarMoeda(fundamento.getP_ebit());
    this.psr = FormatadorUtil.formatarMoeda(fundamento.getPsr());
    this.p_ativos = FormatadorUtil.formatarMoeda(fundamento.getP_ativos());
    this.p_capitacaoGiro = FormatadorUtil.formatarMoeda(fundamento.getP_capitacaoGiro());
    this.p_ativoCirculanteLiquido = FormatadorUtil.formatarPorcentagem(fundamento.getP_ativoCirculanteLiquido());
    this.dividendoYield = FormatadorUtil.formatarPorcentagem(fundamento.getDividendoYield());
    this.ev_ebit = FormatadorUtil.formatarMoeda(fundamento.getEv_ebit());
    this.giroAtivos = FormatadorUtil.formatarMoeda(fundamento.getGiroAtivos());
    this.crescrimentoReceitaLiquidaCincoAnos = FormatadorUtil.formatarPorcentagem(fundamento.getCrescrimentoReceitaLiquidaCincoAnos());
    this.lucroPorAcao = FormatadorUtil.formatarMoeda(fundamento.getLucroPorAcao());
    this.valorPatrimonialPorAcao = FormatadorUtil.formatarMoeda(fundamento.getValorPatrimonialPorAcao());
    this.margemBruto = FormatadorUtil.formatarPorcentagem(fundamento.getMargemBruto());
    this.margemEbit = FormatadorUtil.formatarPorcentagem(fundamento.getMargemEbit());
    this.margemLiquida = FormatadorUtil.formatarPorcentagem(fundamento.getMargemLiquida());
    this.ebit_ativo = FormatadorUtil.formatarPorcentagem(fundamento.getEbit_ativo());
    this.roic = FormatadorUtil.formatarPorcentagem(fundamento.getRoic());
    this.roe = FormatadorUtil.formatarPorcentagem(fundamento.getRoe());
    this.liquidesCorrente = FormatadorUtil.formatarMoeda(fundamento.getLiquidesCorrente());
    this.dividaBruta_Patrimonio = FormatadorUtil.formatarMoeda(fundamento.getDividaBruta_Patrimonio());
    this.ativo = FormatadorUtil.formatarMoeda(fundamento.getAtivo());
    this.disponibilidades = FormatadorUtil.formatarMoeda(fundamento.getDisponibilidades());
    this.ativoCirculante = FormatadorUtil.formatarMoeda(fundamento.getAtivoCirculante());
    this.dividaBruta = FormatadorUtil.formatarMoeda(fundamento.getDividaBruta());
    this.dividaLiquida = FormatadorUtil.formatarMoeda(fundamento.getDividaLiquida());
    this.patrimonioLiquido = FormatadorUtil.formatarMoeda(fundamento.getPatrimonioLiquido());
    this.receitaLiquidaDozeMeses = FormatadorUtil.formatarMoeda(fundamento.getReceitaLiquidaDozeMeses());
    this.ebitDozeMeses = FormatadorUtil.formatarMoeda(fundamento.getEbitDozeMeses());
    this.lucroLiquidoDozeMeses = FormatadorUtil.formatarMoeda(fundamento.getLucroLiquidoDozeMeses());
    this.receitaLiquidaTresMeses = FormatadorUtil.formatarMoeda(fundamento.getReceitaLiquidaTresMeses());
    this.ebitTresMeses = FormatadorUtil.formatarMoeda(fundamento.getEbitTresMeses());
    this.lucroLiquidoTresMeses = FormatadorUtil.formatarMoeda(fundamento.getLucroLiquidoTresMeses());
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getPapel()
  {
    return this.papel;
  }
  
  public void setPapel(String papel)
  {
    this.papel = papel;
  }
  
  public String getCotacao()
  {
    return this.cotacao;
  }
  
  public void setCotacao(String cotacao)
  {
    this.cotacao = cotacao;
  }
  
  public String getDataUltimaCotacao()
  {
    return this.dataUltimaCotacao;
  }
  
  public void setDataUltimaCotacao(String dataUltimaCotacao)
  {
    this.dataUltimaCotacao = dataUltimaCotacao;
  }
  
  public String getMin52Semanas()
  {
    return this.min52Semanas;
  }
  
  public void setMin52Semanas(String min52Semanas)
  {
    this.min52Semanas = min52Semanas;
  }
  
  public String getMax52Semanas()
  {
    return this.max52Semanas;
  }
  
  public void setMax52Semanas(String max52Semanas)
  {
    this.max52Semanas = max52Semanas;
  }
  
  public String getVolumeNegociacao2Meses()
  {
    return this.volumeNegociacao2Meses;
  }
  
  public void setVolumeNegociacao2Meses(String volumeNegociacao2Meses)
  {
    this.volumeNegociacao2Meses = volumeNegociacao2Meses;
  }
  
  public String getValorMercado()
  {
    return this.valorMercado;
  }
  
  public void setValorMercado(String valorMercado)
  {
    this.valorMercado = valorMercado;
  }
  
  public String getValorFirma()
  {
    return this.valorFirma;
  }
  
  public void setValorFirma(String valorFirma)
  {
    this.valorFirma = valorFirma;
  }
  
  public String getUltimoBalanco()
  {
    return this.ultimoBalanco;
  }
  
  public void setUltimoBalanco(String ultimoBalanco)
  {
    this.ultimoBalanco = ultimoBalanco;
  }
  
  public String getNumeroAcoes()
  {
    return this.numeroAcoes;
  }
  
  public void setNumeroAcoes(String numeroAcoes)
  {
    this.numeroAcoes = numeroAcoes;
  }
  
  public String getDia()
  {
    return this.dia;
  }
  
  public void setDia(String dia)
  {
    this.dia = dia;
  }
  
  public String getMes()
  {
    return this.mes;
  }
  
  public void setMes(String mes)
  {
    this.mes = mes;
  }
  
  public String getTrintaDias()
  {
    return this.trintaDias;
  }
  
  public void setTrintaDias(String trintaDias)
  {
    this.trintaDias = trintaDias;
  }
  
  public String getDozeMeses()
  {
    return this.dozeMeses;
  }
  
  public void setDozeMeses(String dozeMeses)
  {
    this.dozeMeses = dozeMeses;
  }
  
  public String getOscilacao2015()
  {
    return this.oscilacao2015;
  }
  
  public void setOscilacao2015(String oscilacao2015)
  {
    this.oscilacao2015 = oscilacao2015;
  }
  
  public String getOscilacao2014()
  {
    return this.oscilacao2014;
  }
  
  public void setOscilacao2014(String oscilacao2014)
  {
    this.oscilacao2014 = oscilacao2014;
  }
  
  public String getOscilacao2013()
  {
    return this.oscilacao2013;
  }
  
  public void setOscilacao2013(String oscilacao2013)
  {
    this.oscilacao2013 = oscilacao2013;
  }
  
  public String getOscilacao2012()
  {
    return this.oscilacao2012;
  }
  
  public void setOscilacao2012(String oscilacao2012)
  {
    this.oscilacao2012 = oscilacao2012;
  }
  
  public String getOscilacao2011()
  {
    return this.oscilacao2011;
  }
  
  public void setOscilacao2011(String oscilacao2011)
  {
    this.oscilacao2011 = oscilacao2011;
  }
  
  public String getOscilacao2010()
  {
    return this.oscilacao2010;
  }
  
  public void setOscilacao2010(String oscilacao2010)
  {
    this.oscilacao2010 = oscilacao2010;
  }
  
  public String getP_l()
  {
    return this.p_l;
  }
  
  public void setP_l(String p_l)
  {
    this.p_l = p_l;
  }
  
  public String getP_vp()
  {
    return this.p_vp;
  }
  
  public void setP_vp(String p_vp)
  {
    this.p_vp = p_vp;
  }
  
  public String getP_ebit()
  {
    return this.p_ebit;
  }
  
  public void setP_ebit(String p_ebit)
  {
    this.p_ebit = p_ebit;
  }
  
  public String getPsr()
  {
    return this.psr;
  }
  
  public void setPsr(String psr)
  {
    this.psr = psr;
  }
  
  public String getP_ativos()
  {
    return this.p_ativos;
  }
  
  public void setP_ativos(String p_ativos)
  {
    this.p_ativos = p_ativos;
  }
  
  public String getP_capitacaoGiro()
  {
    return this.p_capitacaoGiro;
  }
  
  public void setP_capitacaoGiro(String p_capitacaoGiro)
  {
    this.p_capitacaoGiro = p_capitacaoGiro;
  }
  
  public String getP_ativoCirculanteLiquido()
  {
    return this.p_ativoCirculanteLiquido;
  }
  
  public void setP_ativoCirculanteLiquido(String p_ativoCirculanteLiquido)
  {
    this.p_ativoCirculanteLiquido = p_ativoCirculanteLiquido;
  }
  
  public String getEv_ebit()
  {
    return this.ev_ebit;
  }
  
  public void setEv_ebit(String ev_ebit)
  {
    this.ev_ebit = ev_ebit;
  }
  
  public String getGiroAtivos()
  {
    return this.giroAtivos;
  }
  
  public void setGiroAtivos(String giroAtivos)
  {
    this.giroAtivos = giroAtivos;
  }
  
  public String getCrescrimentoReceitaLiquidaCincoAnos()
  {
    return this.crescrimentoReceitaLiquidaCincoAnos;
  }
  
  public void setCrescrimentoReceitaLiquidaCincoAnos(String crescrimentoReceitaLiquidaCincoAnos)
  {
    this.crescrimentoReceitaLiquidaCincoAnos = crescrimentoReceitaLiquidaCincoAnos;
  }
  
  public String getLucroPorAcao()
  {
    return this.lucroPorAcao;
  }
  
  public void setLucroPorAcao(String lucroPorAcao)
  {
    this.lucroPorAcao = lucroPorAcao;
  }
  
  public String getValorPatrimonialPorAcao()
  {
    return this.valorPatrimonialPorAcao;
  }
  
  public void setValorPatrimonialPorAcao(String valorPatrimonialPorAcao)
  {
    this.valorPatrimonialPorAcao = valorPatrimonialPorAcao;
  }
  
  public String getMargemBruto()
  {
    return this.margemBruto;
  }
  
  public void setMargemBruto(String margemBruto)
  {
    this.margemBruto = margemBruto;
  }
  
  public String getMargemEbit()
  {
    return this.margemEbit;
  }
  
  public void setMargemEbit(String margemEbit)
  {
    this.margemEbit = margemEbit;
  }
  
  public String getMargemLiquida()
  {
    return this.margemLiquida;
  }
  
  public void setMargemLiquida(String margemLiquida)
  {
    this.margemLiquida = margemLiquida;
  }
  
  public String getEbit_ativo()
  {
    return this.ebit_ativo;
  }
  
  public void setEbit_ativo(String ebit_ativo)
  {
    this.ebit_ativo = ebit_ativo;
  }
  
  public String getRoic()
  {
    return this.roic;
  }
  
  public void setRoic(String roic)
  {
    this.roic = roic;
  }
  
  public String getRoe()
  {
    return this.roe;
  }
  
  public void setRoe(String roe)
  {
    this.roe = roe;
  }
  
  public String getLiquidesCorrente()
  {
    return this.liquidesCorrente;
  }
  
  public void setLiquidesCorrente(String liquidesCorrente)
  {
    this.liquidesCorrente = liquidesCorrente;
  }
  
  public String getDividaBruta_Patrimonio()
  {
    return this.dividaBruta_Patrimonio;
  }
  
  public void setDividaBruta_Patrimonio(String dividaBruta_Patrimonio)
  {
    this.dividaBruta_Patrimonio = dividaBruta_Patrimonio;
  }
  
  public String getAtivo()
  {
    return this.ativo;
  }
  
  public void setAtivo(String ativo)
  {
    this.ativo = ativo;
  }
  
  public String getDisponibilidades()
  {
    return this.disponibilidades;
  }
  
  public void setDisponibilidades(String disponibilidades)
  {
    this.disponibilidades = disponibilidades;
  }
  
  public String getAtivoCirculante()
  {
    return this.ativoCirculante;
  }
  
  public void setAtivoCirculante(String ativoCirculante)
  {
    this.ativoCirculante = ativoCirculante;
  }
  
  public String getDividaBruta()
  {
    return this.dividaBruta;
  }
  
  public void setDividaBruta(String dividaBruta)
  {
    this.dividaBruta = dividaBruta;
  }
  
  public String getDividaLiquida()
  {
    return this.dividaLiquida;
  }
  
  public void setDividaLiquida(String dividaLiquida)
  {
    this.dividaLiquida = dividaLiquida;
  }
  
  public String getPatrimonioLiquido()
  {
    return this.patrimonioLiquido;
  }
  
  public void setPatrimonioLiquido(String patrimonioLiquido)
  {
    this.patrimonioLiquido = patrimonioLiquido;
  }
  
  public String getReceitaLiquidaDozeMeses()
  {
    return this.receitaLiquidaDozeMeses;
  }
  
  public void setReceitaLiquidaDozeMeses(String receitaLiquidaDozeMeses)
  {
    this.receitaLiquidaDozeMeses = receitaLiquidaDozeMeses;
  }
  
  public String getEbitDozeMeses()
  {
    return this.ebitDozeMeses;
  }
  
  public void setEbitDozeMeses(String ebitDozeMeses)
  {
    this.ebitDozeMeses = ebitDozeMeses;
  }
  
  public String getLucroLiquidoDozeMeses()
  {
    return this.lucroLiquidoDozeMeses;
  }
  
  public void setLucroLiquidoDozeMeses(String lucroLiquidoDozeMeses)
  {
    this.lucroLiquidoDozeMeses = lucroLiquidoDozeMeses;
  }
  
  public String getReceitaLiquidaTresMeses()
  {
    return this.receitaLiquidaTresMeses;
  }
  
  public void setReceitaLiquidaTresMeses(String receitaLiquidaTresMeses)
  {
    this.receitaLiquidaTresMeses = receitaLiquidaTresMeses;
  }
  
  public String getEbitTresMeses()
  {
    return this.ebitTresMeses;
  }
  
  public void setEbitTresMeses(String ebitTresMeses)
  {
    this.ebitTresMeses = ebitTresMeses;
  }
  
  public String getLucroLiquidoTresMeses()
  {
    return this.lucroLiquidoTresMeses;
  }
  
  public void setLucroLiquidoTresMeses(String lucroLiquidoTresMeses)
  {
    this.lucroLiquidoTresMeses = lucroLiquidoTresMeses;
  }
  
  public String getDividendoYield()
  {
    return this.dividendoYield;
  }
  
  public void setDividendoYield(String dividendoYield)
  {
    this.dividendoYield = dividendoYield;
  }
}
