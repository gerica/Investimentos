package br.cs.web.dtos;

import br.cs.entity.Empresa;
import br.cs.entity.Papel;

public class GraficoRendaVariavelDTO
{
  private Empresa empresa;
  private Papel papel;
  private String chartType;
  
  public Empresa getEmpresa()
  {
    return this.empresa;
  }
  
  public void setEmpresa(Empresa empresa)
  {
    this.empresa = empresa;
  }
  
  public Papel getPapel()
  {
    return this.papel;
  }
  
  public void setPapel(Papel papel)
  {
    this.papel = papel;
  }
  
  public String getChartType()
  {
    return this.chartType;
  }
  
  public void setChartType(String chartType)
  {
    this.chartType = chartType;
  }
}
