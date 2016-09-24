package br.cs.web.json.chart;

import br.cs.web.json.JsonCotacao;
import java.util.ArrayList;
import java.util.List;

public class JsonChartLinePapel
{
  public String papel;
  public List<JsonCotacao> cotacoes;
  
  public String getPapel()
  {
    return this.papel;
  }
  
  public void setPapel(String papel)
  {
    this.papel = papel;
  }
  
  public List<JsonCotacao> getCotacoes()
  {
    return this.cotacoes;
  }
  
  public void setCotacoes(List<JsonCotacao> cotacoes)
  {
    this.cotacoes = cotacoes;
  }
  
  public void addCotacoes(JsonCotacao jsonCotacao)
  {
    if (this.cotacoes == null) {
      this.cotacoes = new ArrayList<JsonCotacao>();
    }
    this.cotacoes.add(jsonCotacao);
  }
}
