package br.cs.repository;

import br.cs.entity.RendaFixa;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public abstract interface RendaFixaRepository
  extends CrudRepository<RendaFixa, Integer>
{
  public abstract RendaFixa findById(Integer paramInteger);
  
  public abstract List<RendaFixa> findByDataSaidaIsNullAndAtivo(Boolean paramBoolean);
  
  public abstract List<RendaFixa> findByDataSaidaIsNotNullAndAtivoOrderByDataEntrada(Boolean paramBoolean);
  
  public abstract List<RendaFixa> findBydataEntradaLessThanEqualAndDataSaidaIsNullAndAtivo(Date paramDate, boolean paramBoolean);
}
