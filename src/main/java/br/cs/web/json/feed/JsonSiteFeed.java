package br.cs.web.json.feed;

import br.cs.entity.SiteFeed;

public class JsonSiteFeed
{
  private Integer id;
  private String nomeSite;
  private boolean ativo;
  private String ativoDesc;
  
  public JsonSiteFeed(SiteFeed siteFeed)
  {
    this.id = siteFeed.getId();
    this.nomeSite = siteFeed.getUrl();
    this.ativo = siteFeed.getAtivo().booleanValue();
    this.ativoDesc = (siteFeed.getAtivo().booleanValue() ? "Ativo" : "N�o Ativo");
  }
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getNomeSite()
  {
    return this.nomeSite;
  }
  
  public void setNomeSite(String nomeSite)
  {
    this.nomeSite = nomeSite;
  }
  
  public boolean isAtivo()
  {
    return this.ativo;
  }
  
  public void setAtivo(boolean ativo)
  {
    this.ativo = ativo;
  }
  
  public String getAtivoDesc()
  {
    return this.ativoDesc;
  }
  
  public void setAtivoDesc(String ativoDesc)
  {
    this.ativoDesc = ativoDesc;
  }
}
