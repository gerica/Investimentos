package br.cs.service;

import br.cs.entity.RendaFixa;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.repository.RendaFixaRepository;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RendaFixaService
{
  private static final Logger logger = LoggerFactory.getLogger(RendaFixaService.class);
  @Autowired
  private RendaFixaRepository rendaFixaRepository;
  
  public void salvar(RendaFixa rendaFixa)
    throws InvestimentoBusinessException
  {
    logger.info("salvar");
    
    this.rendaFixaRepository.save(rendaFixa);
  }
  
  public List<RendaFixa> findByDataSaidaIsNullAndAtivo()
  {
    return this.rendaFixaRepository.findByDataSaidaIsNullAndAtivo(Boolean.valueOf(true));
  }
  
  public List<RendaFixa> findByDataSaidaIsNotNullAndAtivo()
  {
    return this.rendaFixaRepository.findByDataSaidaIsNotNullAndAtivoOrderByDataEntrada(Boolean.valueOf(false));
  }
  
  public RendaFixa findById(Integer idRendaFixa)
  {
    return this.rendaFixaRepository.findById(idRendaFixa);
  }
  
  public void salvarSaida(RendaFixa rendaFixa)
    throws InvestimentoBusinessException
  {
    logger.info("salvar");
    if (rendaFixa.getDataSaida() == null) {
      throw new InvestimentoBusinessException("A data final n�o pode ser nulo");
    }
    if (rendaFixa.getResgate() == null) {
      throw new InvestimentoBusinessException("O resgate n�o pode ser nulo");
    }
    RendaFixa objBanco = this.rendaFixaRepository.findById(rendaFixa.getId());
    objBanco.setDataSaida(rendaFixa.getDataSaida());
    objBanco.setResgate(rendaFixa.getResgate());
    objBanco.setAtivo(Boolean.valueOf(false));
    salvar(objBanco);
  }
  
  public void inativar(Integer idRendaFixa)
    throws InvestimentoBusinessException
  {
    RendaFixa objBanco = this.rendaFixaRepository.findById(idRendaFixa);
    objBanco.setAtivo(Boolean.valueOf(false));
    salvar(objBanco);
  }
  
  public List<RendaFixa> findBydataEntradaLessThanEqualAndDataSaidaIsNullAndAtivo(Date dataMaior)
  {
    return this.rendaFixaRepository.findBydataEntradaLessThanEqualAndDataSaidaIsNullAndAtivo(dataMaior, true);
  }
}
