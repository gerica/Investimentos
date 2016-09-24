package br.cs.repository;

import br.cs.entity.SiteFeed;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public abstract interface SiteFeedRepository
  extends CrudRepository<SiteFeed, Integer>
{
  public abstract List<SiteFeed> findAll();
  
  public abstract List<SiteFeed> findAllByAtivo(boolean paramBoolean);
  
  public abstract SiteFeed findById(Integer paramInteger);
}
