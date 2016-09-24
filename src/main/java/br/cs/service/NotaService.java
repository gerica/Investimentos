package br.cs.service;

import br.cs.entity.Nota;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.repository.NotaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaService
{
  private static final Logger logger = LoggerFactory.getLogger(NotaService.class);
  @Autowired
  private NotaRepository notaRepository;
  
  public void salvar(Nota nota)
    throws InvestimentoBusinessException
  {
    logger.info("salvar");
    this.notaRepository.save(nota);
  }
  
  public List<Nota> findAllByAtivo()
  {
    return this.notaRepository.findAllByAtivo(Boolean.valueOf(true));
  }
  
  public void inativar(Integer idNota)
  {
    Nota nota = this.notaRepository.findAllById(idNota);
    nota.setAtivo(Boolean.valueOf(false));
    this.notaRepository.save(nota);
  }
  
  public Nota findById(Integer idNota)
  {
    return this.notaRepository.findAllById(idNota);
  }
  
  public void alterar(Nota nota)
  {
    logger.info("alterar");
    this.notaRepository.save(nota);
  }
  
  public Long countByAtivo()
  {
    logger.info("find qtd notas");
    return this.notaRepository.countByAtivo(Boolean.valueOf(true));
  }
}
