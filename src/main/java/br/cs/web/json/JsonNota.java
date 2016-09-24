package br.cs.web.json;

import br.cs.component.util.FormatadorUtil;
import br.cs.entity.Nota;

public class JsonNota
{
  private String id;
  private String data;
  private String titulo;
  private String sumario;
  private String descricao;
  
  public JsonNota(Nota n)
  {
    this.id = n.getId().toString();
    this.data = FormatadorUtil.formatarData(n.getData());
    this.titulo = n.getTitulo();
    this.sumario = n.getSumario();
    this.descricao = n.getDescricao();
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getData()
  {
    return this.data;
  }
  
  public void setData(String data)
  {
    this.data = data;
  }
  
  public String getTitulo()
  {
    return this.titulo;
  }
  
  public void setTitulo(String titulo)
  {
    this.titulo = titulo;
  }
  
  public String getSumario()
  {
    return this.sumario;
  }
  
  public void setSumario(String sumario)
  {
    this.sumario = sumario;
  }
  
  public String getDescricao()
  {
    return this.descricao;
  }
  
  public void setDescricao(String descricao)
  {
    this.descricao = descricao;
  }
}
