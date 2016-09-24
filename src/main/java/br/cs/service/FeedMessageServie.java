package br.cs.service;

import br.cs.component.util.DataUtil;
import br.cs.entity.FeedMessage;
import br.cs.repository.FeedMessageRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class FeedMessageServie
{
  private static final Logger logger = LoggerFactory.getLogger(FeedMessageServie.class);
  @Autowired
  private FeedMessageRepository feedMessageRepository;
  private Date hoje = new Date();
  
  public void salvar(FeedMessage feedMessage)
  {
    logger.info("Salvar");
    if (podeIncluir(feedMessage))
    {
      logger.info("Salvado: " + feedMessage.getTitle());
      this.feedMessageRepository.save(feedMessage);
    }
  }
  
  private boolean podeIncluir(FeedMessage feedMessage)
  {
    List<FeedMessage> messages = this.feedMessageRepository.findByTitle(feedMessage.getTitle());
    return messages.isEmpty();
  }
  
  public List<FeedMessage> findAllByOrderByDataDesc()
  {
    logger.info("findAllByOrderByDataDesc");
    return this.feedMessageRepository.findAllByOrderByPubDateDesc();
  }
  
  public void atualizarFeedLidoNaoLido(Integer idFeedMessage, Boolean isLido)
  {
    logger.info("atualizarFeedLidoNaoLido: " + idFeedMessage + ", " + isLido);
    FeedMessage feedMessage = this.feedMessageRepository.findById(idFeedMessage);
    feedMessage.setLido(isLido);
    this.feedMessageRepository.save(feedMessage);
  }
  
  public void adicionarRemoverFavoritos(Integer idFeedMessage, Boolean isFavorito)
  {
    logger.info("adicionar/remover favoritos");
    FeedMessage feedMessage = this.feedMessageRepository.findById(idFeedMessage);
    feedMessage.setFavorito(isFavorito);
    this.feedMessageRepository.save(feedMessage);
  }
  
  public List<FeedMessage> findTop5ByLidoOrderByDataDesc()
  {
    logger.info("find top 5");
    return this.feedMessageRepository.findTop5ByLidoOrderByPubDateDesc(false);
  }
  
  public Long countByLido()
  {
    return this.feedMessageRepository.countByLido(false);
  }
  
  public void apagarFeedsLidos()
  {
    logger.info("deletar feeds");
    this.feedMessageRepository.deleteByLidoAndFavoritoAndPubDateLessThan(true, false, new Date());
  }
  
  public void lerTodosFeed()
  {
    this.feedMessageRepository.lerTodosFeed();
  }
  
  public List<FeedMessage> limparFeedsRepitidoEDataHoje(List<FeedMessage> messages)
  {
    logger.info("Limpar data: " + this.hoje);
    List<FeedMessage> result = new ArrayList<FeedMessage>();
    for (FeedMessage feedMessage : messages) {
      if ((DataUtil.compararData(feedMessage.getPubDate(), this.hoje) == 0) && 
        (podeIncluir(feedMessage))) {
        result.add(feedMessage);
      }
    }
    return result;
  }
}
