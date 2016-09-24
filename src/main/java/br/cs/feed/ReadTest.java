package br.cs.feed;

import java.net.MalformedURLException;

import br.cs.entity.Feed;
import br.cs.entity.FeedMessage;

public class ReadTest
{
  public static void main(String[] args)
  {
    try
    {
      RSSFeedParser parser = new RSSFeedParser("http://www.valor.com.br/financas/investimentos/rss");
      Feed feed = parser.readFeed();
      System.out.println(feed);
      for (FeedMessage message : feed.getMessages()) {
        System.out.println(message);
      }
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
  }
}
