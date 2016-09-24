package br.cs.repository;

import br.cs.entity.FeedMessage;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public abstract interface FeedMessageRepository
  extends CrudRepository<FeedMessage, Integer>
{
  public abstract List<FeedMessage> findByTitle(String paramString);
  
  public abstract List<FeedMessage> findAllByOrderByPubDateDesc();
  
  public abstract FeedMessage findById(Integer paramInteger);
  
  public abstract List<FeedMessage> findTop5ByLidoOrderByPubDateDesc(boolean paramBoolean);
  
  public abstract Long countByLido(boolean paramBoolean);
  
  @Modifying
  @Transactional
  @Query("delete from FeedMessage f where f.lido = ?1 and f.favorito = ?2 and pubDate < ?3")
  public abstract void deleteByLidoAndFavoritoAndPubDateLessThan(boolean paramBoolean1, boolean paramBoolean2, Date paramDate);
  
  @Modifying
  @Transactional
  @Query("update FeedMessage f set f.lido = true")
  public abstract void lerTodosFeed();
}
