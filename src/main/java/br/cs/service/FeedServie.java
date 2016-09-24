package br.cs.service;

import br.cs.entity.Feed;
import br.cs.entity.FeedMessage;
import br.cs.repository.FeedRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedServie
{
  private static final Logger logger = LoggerFactory.getLogger(FeedServie.class);
  @Autowired
  private FeedRepository feedRepository;
  @Autowired
  private FeedMessageServie feedMessageServie;
  
  public void salvar(Feed feed)
  {
    logger.info("Salvar");
    Feed feedBanco = findByTitle(feed);
    if (feedBanco == null)
    {
      logger.info("Nova feed encontrada");
      if ((feed.getTitle() == null) || ("".equals(feed.getTitle()))) {
        feed.setTitle(feed.getLink());
      }
      this.feedRepository.save(feed);
      feedBanco = feed;
    }
    List<FeedMessage> messages = this.feedMessageServie.limparFeedsRepitidoEDataHoje(feed.getEntries());
    salvarMessage(messages, feedBanco);
  }
  
  private void salvarMessage(List<FeedMessage> messages, Feed feedBanco)
  {
    for (FeedMessage feedMessage : messages)
    {
      logger.info(feedMessage.getTitle());
      feedMessage.setFeed(feedBanco);
      this.feedMessageServie.salvar(feedMessage);
    }
  }
  
  private Feed findByTitle(Feed feed)
  {
    return this.feedRepository.findByTitle(feed.getTitle());
  }
}
