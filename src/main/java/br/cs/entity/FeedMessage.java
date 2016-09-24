package br.cs.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="feed_message")
public class FeedMessage
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Integer id;
  @Column(name="pubDate")
  private Date pubDate;
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="id_feed", nullable=false)
  private Feed feed;
  @Column(name="title")
  private String title;
  @Column(name="description")
  private String description;
  @Column(name="link")
  private String link;
  @Column(name="author")
  private String author;
  @Column(name="guid")
  private String guid;
  @Column(name="lido")
  private Boolean lido = Boolean.valueOf(false);
  @Column(name="favorito")
  private Boolean favorito = Boolean.valueOf(false);
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
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
  
  public String getAuthor()
  {
    return this.author;
  }
  
  public void setAuthor(String author)
  {
    this.author = author;
  }
  
  public String getGuid()
  {
    return this.guid;
  }
  
  public void setGuid(String guid)
  {
    this.guid = guid;
  }
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Feed getFeed()
  {
    return this.feed;
  }
  
  public void setFeed(Feed feed)
  {
    this.feed = feed;
  }
  
  public Boolean getLido()
  {
    return this.lido;
  }
  
  public void setLido(Boolean lido)
  {
    this.lido = lido;
  }
  
  public Boolean getFavorito()
  {
    return this.favorito;
  }
  
  public void setFavorito(Boolean favorito)
  {
    this.favorito = favorito;
  }
  
  public Date getPubDate()
  {
    return this.pubDate;
  }
  
  public void setPubDate(Date pubDate)
  {
    this.pubDate = pubDate;
  }
  
  public String toString()
  {
    return 
      "FeedMessage [id=" + this.id + ", pubDate=" + this.pubDate + ", feed=" + this.feed + ", title=" + this.title + ", description=" + this.description + ", link=" + this.link + ", author=" + this.author + ", guid=" + this.guid + ", lido=" + this.lido + ", favorito=" + this.favorito + "]";
  }
}
