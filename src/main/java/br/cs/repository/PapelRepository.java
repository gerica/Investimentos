package br.cs.repository;

import br.cs.entity.Empresa;
import br.cs.entity.Papel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public abstract interface PapelRepository
  extends CrudRepository<Papel, Integer>
{
  public abstract List<Papel> findByNome(String paramString);
  
  public abstract List<Papel> findAll();
  
  public abstract List<Papel> findAllByEmpresa(Empresa paramEmpresa);
  
  public abstract Papel findById(Integer paramInteger);
  
  public abstract List<Papel> findAllByEmpresaAndAtivo(Empresa paramEmpresa, Boolean paramBoolean);
  
  public abstract List<Papel> findAllByAtivo(Boolean paramBoolean);
}
