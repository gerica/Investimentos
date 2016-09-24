package br.cs.web.json;

public class JsonCandle
{
  private String data;
  private String minimo;
  private String abertura;
  private String fechamento;
  private String maximo;
  
  public String getData()
  {
    return this.data;
  }
  
  public void setData(String data)
  {
    this.data = data;
  }
  
  public String getMinimo()
  {
    return this.minimo;
  }
  
  public void setMinimo(String minimo)
  {
    this.minimo = minimo;
  }
  
  public String getAbertura()
  {
    return this.abertura;
  }
  
  public void setAbertura(String abertura)
  {
    this.abertura = abertura;
  }
  
  public String getFechamento()
  {
    return this.fechamento;
  }
  
  public void setFechamento(String fechamento)
  {
    this.fechamento = fechamento;
  }
  
  public String getMaximo()
  {
    return this.maximo;
  }
  
  public void setMaximo(String maximo)
  {
    this.maximo = maximo;
  }
}
