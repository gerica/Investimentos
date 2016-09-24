package br.cs.entity;

import br.cs.component.util.DataUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="feed")
public class Feed
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Integer id;
  @Column(name="title")
  private String title;
  @Column(name="link")
  private String link;
  @Column(name="description")
  private String description;
  @Column(name="language")
  private String language;
  @Column(name="copyright")
  private String copyright;
  @Column(name="lastBuildDate")
  private Date lastBuildDate;
  @OneToMany(fetch=FetchType.LAZY, mappedBy="feed")
  @OrderBy("data ASC")
  final List<FeedMessage> entries = new ArrayList<FeedMessage>();
  
  public Feed() {}
  
  public Feed(String title, String link, String description, String language, String copyright, String lastBuildDate)
  {
    this.title = title;
    this.link = link;
    this.description = description;
    this.language = language;
    this.copyright = copyright;
    this.lastBuildDate = DataUtil.parseToDate(lastBuildDate);
  }
  
  public List<FeedMessage> getMessages()
  {
    return this.entries;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public String getLink()
  {
    return this.link;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getLanguage()
  {
    return this.language;
  }
  
  public String getCopyright()
  {
    return this.copyright;
  }
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public List<FeedMessage> getEntries()
  {
    return this.entries;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public void setLink(String link)
  {
    this.link = link;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public void setLanguage(String language)
  {
    this.language = language;
  }
  
  public void setCopyright(String copyright)
  {
    this.copyright = copyright;
  }
  
  public String toString()
  {
    return 
      "Feed [id=" + this.id + ", title=" + this.title + ", link=" + this.link + ", description=" + this.description + ", language=" + this.language + ", copyright=" + this.copyright + ", lastBuildDate=" + this.lastBuildDate + ", entries=" + this.entries + "]";
  }
}
