package br.cs.repository;

import br.cs.entity.Cotacao;
import br.cs.entity.Papel;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract interface CotacaoRepository
  extends PagingAndSortingRepository<Cotacao, Integer>
{
  public abstract Cotacao findByData(Date paramDate);
  
  public abstract List<Cotacao> findAllByPapelOrderByDataDesc(Papel paramPapel);
  
  public abstract List<Cotacao> findAllByPapelOrderByDataAsc(Papel paramPapel);
  
  public abstract List<Cotacao> findByDataAndPapel(Date paramDate, Papel paramPapel);
  
  @Query("SELECT distinct(c) FROM Cotacao c WHERE c.data IN (SELECT max(c1.data) FROM Cotacao c1 WHERE c1.papel = ?2) and c.papel = ?1")
  public abstract Cotacao findUltimaCotacaoByPapel(Papel paramPapel1, Papel paramPapel2);
}
