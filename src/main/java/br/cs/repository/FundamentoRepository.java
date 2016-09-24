package br.cs.repository;

import br.cs.entity.Fundamento;
import br.cs.entity.Papel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public abstract interface FundamentoRepository
  extends CrudRepository<Fundamento, Integer>
{
  public abstract Fundamento findAllById(Integer paramInteger);
  
  public abstract List<Fundamento> findByPapelOrderByUltimoBalancoDesc(Papel paramPapel);
}
