package br.cs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="site_feed")
public class SiteFeed
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Integer id;
  @Column(name="url")
  private String url;
  @Column(name="ativo")
  private Boolean ativo = Boolean.valueOf(true);
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public void setUrl(String url)
  {
    this.url = url;
  }
  
  public Boolean getAtivo()
  {
    return this.ativo;
  }
  
  public void setAtivo(Boolean ativo)
  {
    this.ativo = ativo;
  }
}
