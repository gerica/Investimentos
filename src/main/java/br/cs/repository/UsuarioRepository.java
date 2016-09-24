package br.cs.repository;

import br.cs.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public abstract interface UsuarioRepository
  extends CrudRepository<Usuario, Integer>
{
  public abstract Usuario findByNome(String paramString);
}
