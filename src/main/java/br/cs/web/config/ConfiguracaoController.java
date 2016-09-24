package br.cs.web.config;

import br.cs.entity.ConfiguracaoAnaliseCotacoes;
import br.cs.entity.Usuario;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.service.ConfiguracaoAnaliseCotacoesService;
import br.cs.web.json.JsonResponse;
import br.cs.web.json.enums.JsonReponseEnum;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfiguracaoController
{
  private static final Logger logger = LoggerFactory.getLogger(ConfiguracaoController.class);
  private static final String PATH_CONFIG = "site/config/";
  private static final String PATH_INIT_PAGE = "site/config/carregarPagina";
  private static final String PATH_CONFIG_COTACAO = "site/config/configuracaoCotacao.json";
  private static final String PATH_GRAVAR_CONFIG = "site/config/gravarConfiguracao";
  private static final String PAGE_VIEW = "configSiteView";
  private static final String FORM_VIEW = "configSiteForm";
  @Autowired
  private ConfiguracaoAnaliseCotacoesService service;
  
  @RequestMapping(value={"site/config/carregarPagina"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView carregarPagina()
  {
    logger.info("site/config/carregarPagina");
    ModelAndView model = new ModelAndView("configSiteView", "configSiteForm", new Usuario());
    return model;
  }
  
  @RequestMapping(value={"site/config/configuracaoCotacao.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public JsonResponse telaCrudFeed()
  {
    logger.info("site/config/configuracaoCotacao.json");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setObjeto(this.service.findByUsuario());
    res.setController("site/config/configuracaoCotacao.json");
    return res;
  }
  
  @RequestMapping(value={"site/config/gravarConfiguracao"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public JsonResponse gravarConfiguracao(@Valid @RequestBody ConfiguracaoAnaliseCotacoes config, BindingResult bindingResult)
  {
    logger.info("site/config/gravarConfiguracao");
    JsonResponse res = new JsonResponse();
    if (bindingResult.hasErrors())
    {
      logger.info("ERRO NA VALIDA��O");
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(bindingResult.getAllErrors());
      return res;
    }
    try
    {
      this.service.gravar(config);
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
      res.setController("site/config/gravarConfiguracao");
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    return res;
  }
}
