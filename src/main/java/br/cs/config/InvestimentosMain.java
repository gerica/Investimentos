package br.cs.config;

import br.cs.service.CurvaPatrimonioService;
import br.cs.service.SiteFeedServie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InvestimentosMain
{
  private static final Logger logger = LoggerFactory.getLogger(InvestimentosMain.class);
  @Autowired
  private CurvaPatrimonioService curvaPatrimonioService;
  @Autowired
  private SiteFeedServie siteFeedServie;
  
  public static void main(String... args)
  {
    logger.info("investimento main");
    ApplicationContext context = new AnnotationConfigApplicationContext(new Class[] { RootConfig.class });
    InvestimentosMain main = (InvestimentosMain)context.getBean(InvestimentosMain.class);
    
    main.siteFeedServie.gerenciarFeed();
    
    System.exit(0);
  }
  
  private void curvaPatrimonialMensal() {}
}
