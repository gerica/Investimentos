package br.cs.repository;

import br.cs.entity.OperacaoEntrada;
import br.cs.entity.OperacaoSaida;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public abstract interface OperacaoSaidaRepository
  extends CrudRepository<OperacaoSaida, Integer>
{
  public abstract List<OperacaoSaida> findAll();
  
  public abstract OperacaoSaida findById(Integer paramInteger);
  
  public abstract OperacaoSaida findAllByAtivo(boolean paramBoolean);
  
  public abstract List<OperacaoSaida> findByOperacaoEntrada(OperacaoEntrada paramOperacaoEntrada);
}
