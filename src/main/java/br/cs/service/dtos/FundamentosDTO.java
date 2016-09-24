package br.cs.service.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class FundamentosDTO
{
  private String papelNome;
  private String tipo;
  private String empresa;
  private String setor;
  private String subsetor;
  private Double cotacao;
  private Date dataUltimaCotacao;
  private Double min52Semanas;
  private Double max52Semanas;
  private BigDecimal volumeNegociacao2Meses;
  private BigDecimal valorMercado;
  private BigDecimal valorFirma;
  private Date ultimoBalanco;
  private BigDecimal numeroAcoes;
  private Double dia;
  private Double mes;
  private Double trintaDias;
  private Double dozeMeses;
  private Double oscilacao2015;
  private Double oscilacao2014;
  private Double oscilacao2013;
  private Double oscilacao2012;
  private Double oscilacao2011;
  private Double oscilacao2010;
  private Double p_l;
  private Double p_vp;
  private Double p_ebit;
  private Double psr;
  private Double p_ativos;
  private Double p_capitacaoGiro;
  private Double p_ativoCirculanteLiquido;
  private Double dividendoYield;
  private Double ev_ebit;
  private Double giroAtivos;
  private Double crescrimentoReceitaLiquidaCincoAnos;
  private Double lucroPorAcao;
  private Double valorPatrimonialPorAcao;
  private Double margemBruto;
  private Double margemEbit;
  private Double margemLiquida;
  private Double ebit_ativo;
  private Double roic;
  private Double roe;
  private Double liquidesCorrente;
  private Double dividaBruta_Patrimonio;
  private BigDecimal ativo;
  private BigDecimal disponibilidades;
  private BigDecimal ativoCirculante;
  private BigDecimal dividaBruta;
  private BigDecimal dividaLiquida;
  private BigDecimal patrimonioLiquido;
  private BigDecimal receitaLiquidaDozeMeses;
  private BigDecimal ebitDozeMeses;
  private BigDecimal lucroLiquidoDozeMeses;
  private BigDecimal receitaLiquidaTresMeses;
  private BigDecimal ebitTresMeses;
  private BigDecimal lucroLiquidoTresMeses;
  
  public String getTipo()
  {
    return this.tipo;
  }
  
  public void setTipo(String tipo)
  {
    this.tipo = tipo;
  }
  
  public String getEmpresa()
  {
    return this.empresa;
  }
  
  public void setEmpresa(String empresa)
  {
    this.empresa = empresa;
  }
  
  public String getSetor()
  {
    return this.setor;
  }
  
  public void setSetor(String setor)
  {
    this.setor = setor;
  }
  
  public String getSubsetor()
  {
    return this.subsetor;
  }
  
  public void setSubsetor(String subsetor)
  {
    this.subsetor = subsetor;
  }
  
  public Double getCotacao()
  {
    return this.cotacao;
  }
  
  public void setCotacao(Double cotacao)
  {
    this.cotacao = cotacao;
  }
  
  public Date getDataUltimaCotacao()
  {
    return this.dataUltimaCotacao;
  }
  
  public void setDataUltimaCotacao(Date dataUltimaCotacao)
  {
    this.dataUltimaCotacao = dataUltimaCotacao;
  }
  
  public Double getMin52Semanas()
  {
    return this.min52Semanas;
  }
  
  public void setMin52Semanas(Double min52Semanas)
  {
    this.min52Semanas = min52Semanas;
  }
  
  public Double getMax52Semanas()
  {
    return this.max52Semanas;
  }
  
  public void setMax52Semanas(Double max52Semanas)
  {
    this.max52Semanas = max52Semanas;
  }
  
  public BigDecimal getVolumeNegociacao2Meses()
  {
    return this.volumeNegociacao2Meses;
  }
  
  public void setVolumeNegociacao2Meses(BigDecimal volumeNegociacao2Meses)
  {
    this.volumeNegociacao2Meses = volumeNegociacao2Meses;
  }
  
  public BigDecimal getValorMercado()
  {
    return this.valorMercado;
  }
  
  public void setValorMercado(BigDecimal valorMercado)
  {
    this.valorMercado = valorMercado;
  }
  
  public BigDecimal getValorFirma()
  {
    return this.valorFirma;
  }
  
  public void setValorFirma(BigDecimal valorFirma)
  {
    this.valorFirma = valorFirma;
  }
  
  public Date getUltimoBalanco()
  {
    return this.ultimoBalanco;
  }
  
  public void setUltimoBalanco(Date ultimoBalanco)
  {
    this.ultimoBalanco = ultimoBalanco;
  }
  
  public BigDecimal getNumeroAcoes()
  {
    return this.numeroAcoes;
  }
  
  public void setNumeroAcoes(BigDecimal numeroAcoes)
  {
    this.numeroAcoes = numeroAcoes;
  }
  
  public Double getDia()
  {
    return this.dia;
  }
  
  public void setDia(Double dia)
  {
    this.dia = dia;
  }
  
  public Double getMes()
  {
    return this.mes;
  }
  
  public void setMes(Double mes)
  {
    this.mes = mes;
  }
  
  public Double getTrintaDias()
  {
    return this.trintaDias;
  }
  
  public void setTrintaDias(Double trintaDias)
  {
    this.trintaDias = trintaDias;
  }
  
  public Double getOscilacao2015()
  {
    return this.oscilacao2015;
  }
  
  public void setOscilacao2015(Double oscilacao2015)
  {
    this.oscilacao2015 = oscilacao2015;
  }
  
  public Double getOscilacao2014()
  {
    return this.oscilacao2014;
  }
  
  public void setOscilacao2014(Double oscilacao2014)
  {
    this.oscilacao2014 = oscilacao2014;
  }
  
  public Double getOscilacao2013()
  {
    return this.oscilacao2013;
  }
  
  public void setOscilacao2013(Double oscilacao2013)
  {
    this.oscilacao2013 = oscilacao2013;
  }
  
  public Double getOscilacao2012()
  {
    return this.oscilacao2012;
  }
  
  public void setOscilacao2012(Double oscilacao2012)
  {
    this.oscilacao2012 = oscilacao2012;
  }
  
  public Double getOscilacao2011()
  {
    return this.oscilacao2011;
  }
  
  public void setOscilacao2011(Double oscilacao2011)
  {
    this.oscilacao2011 = oscilacao2011;
  }
  
  public Double getOscilacao2010()
  {
    return this.oscilacao2010;
  }
  
  public void setOscilacao2010(Double oscilacao2010)
  {
    this.oscilacao2010 = oscilacao2010;
  }
  
  public Double getP_l()
  {
    return this.p_l;
  }
  
  public void setP_l(Double p_l)
  {
    this.p_l = p_l;
  }
  
  public Double getP_vp()
  {
    return this.p_vp;
  }
  
  public void setP_vp(Double p_vp)
  {
    this.p_vp = p_vp;
  }
  
  public Double getP_ebit()
  {
    return this.p_ebit;
  }
  
  public void setP_ebit(Double p_ebit)
  {
    this.p_ebit = p_ebit;
  }
  
  public Double getPsr()
  {
    return this.psr;
  }
  
  public void setPsr(Double psr)
  {
    this.psr = psr;
  }
  
  public Double getP_ativos()
  {
    return this.p_ativos;
  }
  
  public void setP_ativos(Double p_ativos)
  {
    this.p_ativos = p_ativos;
  }
  
  public Double getP_capitacaoGiro()
  {
    return this.p_capitacaoGiro;
  }
  
  public void setP_capitacaoGiro(Double p_capitacaoGiro)
  {
    this.p_capitacaoGiro = p_capitacaoGiro;
  }
  
  public Double getP_ativoCirculanteLiquido()
  {
    return this.p_ativoCirculanteLiquido;
  }
  
  public void setP_ativoCirculanteLiquido(Double p_ativoCirculanteLiquido)
  {
    this.p_ativoCirculanteLiquido = p_ativoCirculanteLiquido;
  }
  
  public Double getDividendoYield()
  {
    return this.dividendoYield;
  }
  
  public void setDividendoYield(Double dividendoYield)
  {
    this.dividendoYield = dividendoYield;
  }
  
  public Double getEv_ebit()
  {
    return this.ev_ebit;
  }
  
  public void setEv_ebit(Double ev_ebit)
  {
    this.ev_ebit = ev_ebit;
  }
  
  public Double getGiroAtivos()
  {
    return this.giroAtivos;
  }
  
  public void setGiroAtivos(Double giroAtivos)
  {
    this.giroAtivos = giroAtivos;
  }
  
  public Double getCrescrimentoReceitaLiquidaCincoAnos()
  {
    return this.crescrimentoReceitaLiquidaCincoAnos;
  }
  
  public void setCrescrimentoReceitaLiquidaCincoAnos(Double crescrimentoReceitaLiquidaCincoAnos)
  {
    this.crescrimentoReceitaLiquidaCincoAnos = crescrimentoReceitaLiquidaCincoAnos;
  }
  
  public Double getLucroPorAcao()
  {
    return this.lucroPorAcao;
  }
  
  public void setLucroPorAcao(Double lucroPorAcao)
  {
    this.lucroPorAcao = lucroPorAcao;
  }
  
  public Double getValorPatrimonialPorAcao()
  {
    return this.valorPatrimonialPorAcao;
  }
  
  public void setValorPatrimonialPorAcao(Double valorPatrimonialPorAcao)
  {
    this.valorPatrimonialPorAcao = valorPatrimonialPorAcao;
  }
  
  public Double getMargemBruto()
  {
    return this.margemBruto;
  }
  
  public void setMargemBruto(Double margemBruto)
  {
    this.margemBruto = margemBruto;
  }
  
  public Double getMargemEbit()
  {
    return this.margemEbit;
  }
  
  public void setMargemEbit(Double margemEbit)
  {
    this.margemEbit = margemEbit;
  }
  
  public Double getMargemLiquida()
  {
    return this.margemLiquida;
  }
  
  public void setMargemLiquida(Double margemLiquida)
  {
    this.margemLiquida = margemLiquida;
  }
  
  public Double getEbit_ativo()
  {
    return this.ebit_ativo;
  }
  
  public void setEbit_ativo(Double ebit_ativo)
  {
    this.ebit_ativo = ebit_ativo;
  }
  
  public Double getRoic()
  {
    return this.roic;
  }
  
  public void setRoic(Double roic)
  {
    this.roic = roic;
  }
  
  public Double getRoe()
  {
    return this.roe;
  }
  
  public void setRoe(Double roe)
  {
    this.roe = roe;
  }
  
  public Double getLiquidesCorrente()
  {
    return this.liquidesCorrente;
  }
  
  public void setLiquidesCorrente(Double liquidesCorrente)
  {
    this.liquidesCorrente = liquidesCorrente;
  }
  
  public Double getDividaBruta_Patrimonio()
  {
    return this.dividaBruta_Patrimonio;
  }
  
  public void setDividaBruta_Patrimonio(Double dividaBruta_Patrimonio)
  {
    this.dividaBruta_Patrimonio = dividaBruta_Patrimonio;
  }
  
  public BigDecimal getAtivo()
  {
    return this.ativo;
  }
  
  public void setAtivo(BigDecimal ativo)
  {
    this.ativo = ativo;
  }
  
  public BigDecimal getDisponibilidades()
  {
    return this.disponibilidades;
  }
  
  public void setDisponibilidades(BigDecimal disponibilidades)
  {
    this.disponibilidades = disponibilidades;
  }
  
  public BigDecimal getAtivoCirculante()
  {
    return this.ativoCirculante;
  }
  
  public void setAtivoCirculante(BigDecimal ativoCirculante)
  {
    this.ativoCirculante = ativoCirculante;
  }
  
  public BigDecimal getDividaBruta()
  {
    return this.dividaBruta;
  }
  
  public void setDividaBruta(BigDecimal dividaBruta)
  {
    this.dividaBruta = dividaBruta;
  }
  
  public BigDecimal getDividaLiquida()
  {
    return this.dividaLiquida;
  }
  
  public void setDividaLiquida(BigDecimal dividaLiquida)
  {
    this.dividaLiquida = dividaLiquida;
  }
  
  public BigDecimal getPatrimonioLiquido()
  {
    return this.patrimonioLiquido;
  }
  
  public void setPatrimonioLiquido(BigDecimal patrimonioLiquido)
  {
    this.patrimonioLiquido = patrimonioLiquido;
  }
  
  public BigDecimal getReceitaLiquidaDozeMeses()
  {
    return this.receitaLiquidaDozeMeses;
  }
  
  public void setReceitaLiquidaDozeMeses(BigDecimal receitaLiquidaDozeMeses)
  {
    this.receitaLiquidaDozeMeses = receitaLiquidaDozeMeses;
  }
  
  public BigDecimal getEbitDozeMeses()
  {
    return this.ebitDozeMeses;
  }
  
  public void setEbitDozeMeses(BigDecimal ebitDozeMeses)
  {
    this.ebitDozeMeses = ebitDozeMeses;
  }
  
  public BigDecimal getLucroLiquidoDozeMeses()
  {
    return this.lucroLiquidoDozeMeses;
  }
  
  public void setLucroLiquidoDozeMeses(BigDecimal lucoLiquidoDozeMeses)
  {
    this.lucroLiquidoDozeMeses = lucoLiquidoDozeMeses;
  }
  
  public BigDecimal getReceitaLiquidaTresMeses()
  {
    return this.receitaLiquidaTresMeses;
  }
  
  public void setReceitaLiquidaTresMeses(BigDecimal receitaLiquidaTresMeses)
  {
    this.receitaLiquidaTresMeses = receitaLiquidaTresMeses;
  }
  
  public BigDecimal getEbitTresMeses()
  {
    return this.ebitTresMeses;
  }
  
  public void setEbitTresMeses(BigDecimal ebitTresMeses)
  {
    this.ebitTresMeses = ebitTresMeses;
  }
  
  public BigDecimal getLucroLiquidoTresMeses()
  {
    return this.lucroLiquidoTresMeses;
  }
  
  public void setLucroLiquidoTresMeses(BigDecimal lucoLiquidoTresMeses)
  {
    this.lucroLiquidoTresMeses = lucoLiquidoTresMeses;
  }
  
  public Double getDozeMeses()
  {
    return this.dozeMeses;
  }
  
  public void setDozeMeses(Double dozeMeses)
  {
    this.dozeMeses = dozeMeses;
  }
  
  public String getPapelNome()
  {
    return this.papelNome;
  }
  
  public void setPapelNome(String papelNome)
  {
    this.papelNome = papelNome;
  }
  
  public String toString()
  {
    return 
    
      "FundamentosDTO [papelNome=" + this.papelNome + ", tipo=" + this.tipo + ", empresa=" + this.empresa + ", setor=" + this.setor + ", subsetor=" + this.subsetor + ", cotacao=" + this.cotacao + ", dataUltimaCotacao=" + this.dataUltimaCotacao + ", min52Semanas=" + this.min52Semanas + ", max52Semanas=" + this.max52Semanas + ", volumeNegociacao2Meses=" + this.volumeNegociacao2Meses + ", valorMercado=" + this.valorMercado + ", valorFirma=" + this.valorFirma + ", ultimoBalanco=" + this.ultimoBalanco + ", numeroAcoes=" + this.numeroAcoes + ", dia=" + this.dia + ", mes=" + this.mes + ", trintaDias=" + this.trintaDias + ", dozeMeses=" + this.dozeMeses + ", oscilacao2015=" + this.oscilacao2015 + ", oscilacao2014=" + this.oscilacao2014 + ", oscilacao2013=" + this.oscilacao2013 + ", oscilacao2012=" + this.oscilacao2012 + ", oscilacao2011=" + this.oscilacao2011 + ", oscilacao2010=" + this.oscilacao2010 + ", p_l=" + this.p_l + ", p_vp=" + this.p_vp + ", p_ebit=" + this.p_ebit + ", psr=" + this.psr + ", p_ativos=" + this.p_ativos + ", p_capitacaoGiro=" + this.p_capitacaoGiro + ", p_ativoCirculanteLiquido=" + this.p_ativoCirculanteLiquido + ", dividendoYield=" + this.dividendoYield + ", ev_ebit=" + this.ev_ebit + ", giroAtivos=" + this.giroAtivos + ", crescrimentoReceitaLiquidaCincoAnos=" + this.crescrimentoReceitaLiquidaCincoAnos + ", lucroPorAcao=" + this.lucroPorAcao + ", valorPatrimonialPorAcao=" + this.valorPatrimonialPorAcao + ", margemBruto=" + this.margemBruto + ", margemEbit=" + this.margemEbit + ", margemLiquida=" + this.margemLiquida + ", ebit_ativo=" + this.ebit_ativo + ", roic=" + this.roic + ", roe=" + this.roe + ", liquidesCorrente=" + this.liquidesCorrente + ", dividaBruta_Patrimonio=" + this.dividaBruta_Patrimonio + ", ativo=" + this.ativo + ", disponibilidades=" + this.disponibilidades + ", ativoCirculante=" + this.ativoCirculante + ", dividaBruta=" + this.dividaBruta + ", dividaLiquida=" + this.dividaLiquida + ", patrimonioLiquido=" + this.patrimonioLiquido + ", receitaLiquidaDozeMeses=" + this.receitaLiquidaDozeMeses + ", ebitDozeMeses=" + this.ebitDozeMeses + ", lucroLiquidoDozeMeses=" + this.lucroLiquidoDozeMeses + ", receitaLiquidaTresMeses=" + this.receitaLiquidaTresMeses + ", ebitTresMeses=" + this.ebitTresMeses + ", lucroLiquidoTresMeses=" + this.lucroLiquidoTresMeses + "]";
  }
}
