package br.cs.web.json.feed;

import java.util.Date;

import br.cs.component.util.DataUtil;
import br.cs.component.util.FormatadorUtil;
import br.cs.entity.FeedMessage;

public class JsonFeedMessage
{
  private String id;
  private String title;
  private String description;
  private String link;
  private String data;
  private String dataHora;
  private String nomeFeed;
  private Boolean lido;
  private Boolean favorito;
  private static final String CSS_LIDO = "success";
  private static final String CSS_NAO_LIDO = "gradeX";
  private static final String CSS_LIDO_CHECK = "fa fa-square-o";
  private static final String CSS_NAO_LIDO_CHECK = "fa fa-check-square";
  private static final String CSS_FAVORITO = "glyphicon glyphicon-star";
  private static final String CSS_NAO_FAVORITO = "glyphicon glyphicon-star-empty";
  
  public JsonFeedMessage(FeedMessage message)
  {
    this.id = message.getId().toString();
    this.title = message.getTitle();
    this.description = message.getDescription();
    this.link = message.getLink();
    Date hoje = new Date();
    if (DataUtil.compararData(message.getPubDate(), hoje) == 0) {
      this.data = ("Hoje �s " + FormatadorUtil.formatarData(message.getPubDate(), "hh:mm"));
    } else {
      this.data = FormatadorUtil.formatarData(message.getPubDate());
    }
    this.dataHora = FormatadorUtil.formatarData(message.getPubDate(), "dd/MM/yyyy hh:mm");
    this.nomeFeed = message.getFeed().getTitle();
    this.lido = message.getLido();
    this.favorito = message.getFavorito();
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public String getLink()
  {
    return this.link;
  }
  
  public void setLink(String link)
  {
    this.link = link;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getData()
  {
    return this.data;
  }
  
  public void setData(String data)
  {
    this.data = data;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getNomeFeed()
  {
    return this.nomeFeed;
  }
  
  public void setNomeFeed(String nomeFeed)
  {
    this.nomeFeed = nomeFeed;
  }
  
  public String getCssLido()
  {
    if (this.lido.booleanValue()) {
      return "success";
    }
    return "gradeX";
  }
  
  public String getCssLidoCheck()
  {
    if (this.lido.booleanValue()) {
      return "fa fa-check-square";
    }
    return "fa fa-square-o";
  }
  
  public String getCssFavorito()
  {
    if (this.favorito.booleanValue()) {
      return "glyphicon glyphicon-star";
    }
    return "glyphicon glyphicon-star-empty";
  }
  
  public String getDataHora()
  {
    return this.dataHora;
  }
  
  public void setDataHora(String dataHora)
  {
    this.dataHora = dataHora;
  }
}
