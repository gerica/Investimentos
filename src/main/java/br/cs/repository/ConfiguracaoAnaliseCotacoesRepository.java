package br.cs.repository;

import br.cs.entity.ConfiguracaoAnaliseCotacoes;
import br.cs.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract interface ConfiguracaoAnaliseCotacoesRepository
  extends PagingAndSortingRepository<ConfiguracaoAnaliseCotacoes, Integer>
{
  public abstract ConfiguracaoAnaliseCotacoes findByUsuario(Usuario paramUsuario);
}
