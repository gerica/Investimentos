package br.cs.web.json;

import br.cs.entity.Papel;

public class JsonPapel
{
  private Integer id;
  private String nome;
  private String emrpesa;
  
  public JsonPapel(Papel papel)
  {
    this.id = papel.getId();
    this.nome = papel.getNome();
  }
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getNome()
  {
    return this.nome;
  }
  
  public void setNome(String nome)
  {
    this.nome = nome;
  }
  
  public String getEmrpesa()
  {
    return this.emrpesa;
  }
  
  public void setEmrpesa(String emrpesa)
  {
    this.emrpesa = emrpesa;
  }
}
