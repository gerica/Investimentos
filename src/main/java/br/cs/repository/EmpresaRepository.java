package br.cs.repository;

import br.cs.entity.Empresa;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public abstract interface EmpresaRepository
  extends CrudRepository<Empresa, Integer>
{
  public abstract Empresa findById(Integer paramInteger);
  
  public abstract List<Empresa> findByNome(String paramString);
  
  public abstract List<Empresa> findAll();
  
  public abstract List<Empresa> findAllByAtivo(Boolean paramBoolean);
}
