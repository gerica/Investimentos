package br.cs.repository;

import br.cs.entity.Nota;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public abstract interface NotaRepository
  extends CrudRepository<Nota, Integer>
{
  public abstract List<Nota> findAllByAtivo(Boolean paramBoolean);
  
  public abstract Nota findAllById(Integer paramInteger);
  
  public abstract Long countByAtivo(Boolean paramBoolean);
}
