package br.cs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.cs.entity.ConfiguracaoAnaliseCotacoes;
import br.cs.entity.Usuario;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.repository.ConfiguracaoAnaliseCotacoesRepository;

@Service
public class ConfiguracaoAnaliseCotacoesService
{
  private static final Logger logger = LoggerFactory.getLogger(ConfiguracaoAnaliseCotacoesService.class);
  @Autowired
  private ConfiguracaoAnaliseCotacoesRepository repository;
  
  public ConfiguracaoAnaliseCotacoes findByUsuario()
  {
    Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    logger.info("findByUsuario " + usuario);
    ConfiguracaoAnaliseCotacoes config = this.repository.findByUsuario(usuario);
    if (config == null) {
      config = new ConfiguracaoAnaliseCotacoes();
    }
    config.configDefault();
    return config;
  }
  
  public void gravar(ConfiguracaoAnaliseCotacoes config)
    throws InvestimentoBusinessException
  {
    logger.info("Salvar " + config);
    Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    config.setUsuario(usuario);
    this.repository.save(config);
  }
}
