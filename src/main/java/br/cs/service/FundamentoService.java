package br.cs.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cs.entity.Fundamento;
import br.cs.entity.Papel;
import br.cs.repository.FundamentoRepository;
import br.cs.service.dtos.FundamentosDTO;

@Service
public class FundamentoService
{
  private static final Logger logger = LoggerFactory.getLogger(FundamentoService.class);
  @Autowired
  private FundamentoRepository fundamentoRepository;
  @Autowired
  private PapelService papelService;
  
  public Fundamento findLastFundamento(Papel papel)
  {
    logger.info("findLastFundamento");
    List<Fundamento> fundamentos = this.fundamentoRepository.findByPapelOrderByUltimoBalancoDesc(papel);
    Fundamento fundamento = null;
    if (!fundamentos.isEmpty()) {
      fundamento = (Fundamento)fundamentos.get(0);
    }
    return fundamento;
  }
  
  public void salvar(Fundamento fundamento, FundamentosDTO dto)
  {
    logger.info("salvar");
    try
    {
      BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
      beanUtilsBean.getConvertUtils().register(new BigDecimalConverter(null), BigDecimal.class);
      beanUtilsBean.getConvertUtils().register(new DateConverter(null), Date.class);
      
      beanUtilsBean.copyProperties(fundamento, dto);
    }
    catch (IllegalAccessException|InvocationTargetException e)
    {
      logger.info("erro ao compriar enteidades, fundamento");
      logger.debug(e.getMessage());
    }
    this.fundamentoRepository.save(fundamento);
  }
  
  public Fundamento findLastFundamento(Integer idPapel)
  {
    return findLastFundamento(this.papelService.findById(idPapel));
  }
}
