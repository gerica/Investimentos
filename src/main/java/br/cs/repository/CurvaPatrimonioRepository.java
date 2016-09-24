package br.cs.repository;

import br.cs.entity.CurvaPatrimonio;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public abstract interface CurvaPatrimonioRepository
  extends CrudRepository<CurvaPatrimonio, Integer>
{
  public abstract List<CurvaPatrimonio> findAllByOrderByDataAsc();
  
  public abstract CurvaPatrimonio findByDataLessThanEqualAndDataGreaterThanEqual(Date paramDate1, Date paramDate2);
}
