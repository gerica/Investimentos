package br.cs.web.json;

import java.util.ArrayList;
import java.util.List;

public class JsonChartCandlePapel
{
  public String papel;
  public List<JsonCandle> candles;
  
  public String getPapel()
  {
    return this.papel;
  }
  
  public void setPapel(String papel)
  {
    this.papel = papel;
  }
  
  public List<JsonCandle> getCandles()
  {
    return this.candles;
  }
  
  public void setCandles(List<JsonCandle> candles)
  {
    this.candles = candles;
  }
  
  public void addCandels(JsonCandle candle)
  {
    if (this.candles == null) {
      this.candles = new ArrayList<JsonCandle>();
    }
    this.candles.add(candle);
  }
}
