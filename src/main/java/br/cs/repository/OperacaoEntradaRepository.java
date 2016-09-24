package br.cs.repository;

import br.cs.entity.OperacaoEntrada;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public abstract interface OperacaoEntradaRepository
  extends CrudRepository<OperacaoEntrada, Integer>
{
  public abstract List<OperacaoEntrada> findAll();
  
  public abstract OperacaoEntrada findById(Integer paramInteger);
  
  public abstract List<OperacaoEntrada> findAllByAtivoOrderByData(boolean paramBoolean);
  
  public abstract List<OperacaoEntrada> findByDataLessThanEqualAndDataGreaterThanEqual(Date paramDate1, Date paramDate2);
  
  public abstract List<OperacaoEntrada> findByDataLessThanEqualAndAtivo(Date paramDate, boolean paramBoolean);
}
