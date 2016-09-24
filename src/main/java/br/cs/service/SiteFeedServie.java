package br.cs.service;

import br.cs.entity.Feed;
import br.cs.entity.SiteFeed;
import br.cs.feed.RSSFeedParser;
import br.cs.repository.SiteFeedRepository;
import java.net.MalformedURLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SiteFeedServie
{
  private static final Logger logger = LoggerFactory.getLogger(SiteFeedServie.class);
  @Autowired
  private SiteFeedRepository siteFeedRepository;
  @Autowired
  private FeedServie feedServie;
  @Autowired
  private FeedMessageServie feedMessageServie;
  
  @Scheduled(cron="0 * 0/25 * * *")
  public void gerenciarFeed()
  {
    logger.info("Gerenciar feeds");
    List<SiteFeed> feeds = this.siteFeedRepository.findAllByAtivo(true);
    for (SiteFeed siteFeed : feeds) {
      try
      {
        logger.info(siteFeed.getUrl());
        RSSFeedParser parser = new RSSFeedParser(siteFeed.getUrl());
        Feed feed = parser.readFeed();
        this.feedServie.salvar(feed);
      }
      catch (MalformedURLException e)
      {
        logger.debug(e.getMessage());
      }
    }
  }
  
  @Scheduled(cron="0 * 0/35 * * *")
  public void deletarFeed()
  {
    logger.info("deletar feeds");
    this.feedMessageServie.apagarFeedsLidos();
  }
  
  public List<SiteFeed> findAllByAtivo()
  {
    return this.siteFeedRepository.findAllByAtivo(true);
  }
  
  public List<SiteFeed> findAll()
  {
    return this.siteFeedRepository.findAll();
  }
  
  public boolean validarSiteFeed(SiteFeed siteFeed)
  {
    try
    {
      RSSFeedParser parser = new RSSFeedParser(siteFeed.getUrl());
      Feed feed = parser.readFeed();
      return feed.getEntries() != null;
    }
    catch (RuntimeException e)
    {
      logger.debug(e.getMessage());
    }
    catch (Exception e)
    {
      logger.debug(e.getMessage());
    }
    return false;
  }
  
  public boolean gravarSiteFeed(SiteFeed siteFeed)
  {
    if (validarSiteFeed(siteFeed))
    {
      this.siteFeedRepository.save(siteFeed);
      return true;
    }
    return false;
  }
  
  public SiteFeed findById(Integer idSiteFeed)
  {
    return this.siteFeedRepository.findById(idSiteFeed);
  }
  
  public void excluirSiteFeed(Integer idSiteFeed)
  {
    this.siteFeedRepository.delete(idSiteFeed);
  }
  
  public void ativarInativarSiteFeed(Integer idSiteFeed)
  {
    SiteFeed siteFeed = this.siteFeedRepository.findById(idSiteFeed);
    siteFeed.setAtivo(Boolean.valueOf(!siteFeed.getAtivo().booleanValue()));
    this.siteFeedRepository.save(siteFeed);
  }
  
  public void lerTodosFeed()
  {
    this.feedMessageServie.lerTodosFeed();
  }
}
