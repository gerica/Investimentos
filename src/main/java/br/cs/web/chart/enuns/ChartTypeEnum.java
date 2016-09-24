package br.cs.web.chart.enuns;

public enum ChartTypeEnum
{
  CHART_LINE("chartLine"),  CHART_CANDLE("chartCandle");
  
  private final String desc;
  
  private ChartTypeEnum(String d)
  {
    this.desc = d;
  }
  
  public String getDesc()
  {
    return this.desc;
  }
}
