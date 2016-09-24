package br.cs.service;

import br.cs.entity.OperacaoEntrada;
import br.cs.entity.OperacaoSaida;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.repository.OperacaoSaidaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperacaoSaidaService
{
  private static final Logger logger = LoggerFactory.getLogger(OperacaoSaidaService.class);
  @Autowired
  private OperacaoSaidaRepository operacaoSaidaRepository;
  @Autowired
  private OperacaoEntradaService operacaoEntradaService;
  
  public OperacaoSaida findById(Integer idOperacao)
  {
    logger.info("find by id");
    return this.operacaoSaidaRepository.findById(idOperacao);
  }
  
  public void salvar(OperacaoSaida operacao)
    throws InvestimentoBusinessException
  {
    logger.info("salvar opera��o de sa�da");
    OperacaoEntrada entrada = this.operacaoEntradaService.findById(operacao.getOperacaoEntrada().getId());
    if (operacao.getQuantidade().intValue() > entrada.getQuantidade().intValue()) {
      throw new InvestimentoBusinessException("A quantidade que foi informada � maior que a quantida na opera��o de entrada.");
    }
    if (operacao.getQuantidade().intValue() < entrada.getQuantidade().intValue())
    {
      entrada.setQuantidade(Integer.valueOf(entrada.getQuantidade().intValue() - operacao.getQuantidade().intValue()));
    }
    else
    {
      entrada.setQuantidade(Integer.valueOf(entrada.getQuantidade().intValue() - operacao.getQuantidade().intValue()));
      entrada.setAtivo(Boolean.valueOf(false));
    }
    this.operacaoEntradaService.salvar(entrada);
    this.operacaoSaidaRepository.save(operacao);
  }
  
  public List<OperacaoSaida> findByOperacaoEntrada(OperacaoEntrada operacaoEntrada)
  {
    return this.operacaoSaidaRepository.findByOperacaoEntrada(operacaoEntrada);
  }
}
