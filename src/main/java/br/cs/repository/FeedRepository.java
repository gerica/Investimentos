package br.cs.repository;

import br.cs.entity.Feed;
import org.springframework.data.repository.CrudRepository;

public abstract interface FeedRepository
  extends CrudRepository<Feed, Integer>
{
  public abstract Feed findByTitle(String paramString);
}
