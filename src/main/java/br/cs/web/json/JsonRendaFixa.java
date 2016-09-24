package br.cs.web.json;

import br.cs.component.util.FormatadorUtil;
import br.cs.entity.RendaFixa;

public class JsonRendaFixa
{
  private String id;
  private String dataEntrada;
  private String dataSaida;
  private String resgate;
  private String taxa;
  private String observacao;
  private String nomeBanco;
  private String investimento;
  
  public JsonRendaFixa(RendaFixa r)
  {
    this.id = r.getId().toString();
    this.dataEntrada = FormatadorUtil.formatarData(r.getDataEntrada());
    this.dataSaida = FormatadorUtil.formatarData(r.getDataSaida());
    this.resgate = FormatadorUtil.formatarMoeda(r.getResgate());
    this.taxa = FormatadorUtil.formatarPorcentagem(r.getTaxa());
    this.observacao = r.getObservacao();
    this.nomeBanco = r.getNomeBanco();
    this.investimento = FormatadorUtil.formatarMoeda(r.getInvestimento());
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getDataEntrada()
  {
    return this.dataEntrada;
  }
  
  public void setDataEntrada(String dataEntrada)
  {
    this.dataEntrada = dataEntrada;
  }
  
  public String getDataSaida()
  {
    return this.dataSaida;
  }
  
  public void setDataSaida(String dataSaida)
  {
    this.dataSaida = dataSaida;
  }
  
  public String getResgate()
  {
    return this.resgate;
  }
  
  public void setResgate(String resgate)
  {
    this.resgate = resgate;
  }
  
  public String getTaxa()
  {
    return this.taxa;
  }
  
  public void setTaxa(String taxa)
  {
    this.taxa = taxa;
  }
  
  public String getObservacao()
  {
    return this.observacao;
  }
  
  public void setObservacao(String observacao)
  {
    this.observacao = observacao;
  }
  
  public String getNomeBanco()
  {
    return this.nomeBanco;
  }
  
  public void setNomeBanco(String nomeBanco)
  {
    this.nomeBanco = nomeBanco;
  }
  
  public String getInvestimento()
  {
    return this.investimento;
  }
  
  public void setInvestimento(String investimento)
  {
    this.investimento = investimento;
  }
}
