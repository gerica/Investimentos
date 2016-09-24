package br.cs.web.json;

import br.cs.entity.Empresa;

public class JsonEmpresa
{
  private Integer id;
  private String nome;
  private String setor;
  private String subsetor;
  
  public JsonEmpresa(Empresa empresa)
  {
    this.id = empresa.getId();
    this.nome = empresa.getNome();
    this.setor = empresa.getSetor();
    this.subsetor = empresa.getSubsetor();
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
}
