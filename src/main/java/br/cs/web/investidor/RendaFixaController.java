package br.cs.web.investidor;

import br.cs.entity.RendaFixa;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.service.RendaFixaService;
import br.cs.web.json.JsonResponse;
import br.cs.web.json.JsonUtil;
import br.cs.web.json.enums.JsonReponseEnum;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RendaFixaController
{
  private static final Logger logger = LoggerFactory.getLogger(RendaVariavelController.class);
  private static final String PATH_OPERACAO_VIEW = "site/rendaFixaView";
  private static final String PATH_GRAVAR = "site/gravarRendaFixa";
  private static final String PATH_LIMPAR = "site/limparRendaFixa";
  private static final String PATH_PRE_OPERACAO_SAIDA = "site/preRendaFixaSaida/{idRendaFixa}";
  private static final String PATH_GRAVAR_SAIDA = "site/gravarRendaFixaSaida";
  private static final String PATH_PRE_ALTERAR_RENDA_FIXA = "site/preAlterar/{idRendaFixa}";
  private static final String PATH_APAGAR_RENDA_FIXA = "site/apagarRendaFixa/{idRendaFixa}";
  private static final String PATH_HISTORICO_RENDA_FIXA = "site/historicoRendaFixa";
  private static final String PAGE_RENDA_FIXA_VIEW = "rendaFixaView";
  private static final String PAGE_RENDA_FIXA_SAIDA = "rendaFixaSaida";
  private static final String FORM_RENDA_FIXA = "rendaFixaForm";
  @Autowired
  private RendaFixaService rendaFixaService;
  
  @InitBinder
  public void initBinder(WebDataBinder webDataBinder)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat dfImporte = new DecimalFormat("#,##0.00");
    dateFormat.setLenient(false);
    webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    webDataBinder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, dfImporte, true));
  }
  
  @RequestMapping(value={"site/rendaFixaView"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView preOperacaoEntrada()
  {
    logger.info("site/rendaFixaView");
    ModelAndView model = new ModelAndView("rendaFixaView", "rendaFixaForm", new RendaFixa());
    
    model.addObject("lists", JsonUtil.getListJsonRendaFixa(this.rendaFixaService.findByDataSaidaIsNullAndAtivo()));
    
    return model;
  }
  
  @RequestMapping(value={"site/limparRendaFixa"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse limpar()
  {
    logger.info("site/limparRendaFixa");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    return res;
  }
  
  @RequestMapping(value={"site/gravarRendaFixa"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public JsonResponse gravar(@Valid @RequestBody RendaFixa rendaFixa, BindingResult bindingResult)
  {
    logger.info("site/gravarRendaFixa");
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
      this.rendaFixaService.salvar(rendaFixa);
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setResult(JsonUtil.getListJsonRendaFixa(this.rendaFixaService.findByDataSaidaIsNullAndAtivo()));
    return res;
  }
  
  @RequestMapping(value={"site/preRendaFixaSaida/{idRendaFixa}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String preSaida(@PathVariable("idRendaFixa") Integer idRendaFixa, ModelMap model)
  {
    logger.info("site/preRendaFixaSaida/{idRendaFixa}");
    model.put("rendaFixaForm", this.rendaFixaService.findById(idRendaFixa));
    
    return "rendaFixaSaida";
  }
  
  @RequestMapping(value={"site/gravarRendaFixaSaida"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public JsonResponse gravarSaida(@RequestBody RendaFixa rendaFixa)
  {
    logger.info("site/gravarRendaFixa");
    JsonResponse res = new JsonResponse();
    try
    {
      this.rendaFixaService.salvarSaida(rendaFixa);
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    return res;
  }
  
  @RequestMapping(value={"site/apagarRendaFixa/{idRendaFixa}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse excluir(@PathVariable("idRendaFixa") Integer idRendaFixa)
  {
    logger.info("site/preRendaFixaSaida/{idRendaFixa}");
    JsonResponse res = new JsonResponse();
    try
    {
      this.rendaFixaService.inativar(idRendaFixa);
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    res.setResult(JsonUtil.getListJsonRendaFixa(this.rendaFixaService.findByDataSaidaIsNullAndAtivo()));
    
    return res;
  }
  
  @RequestMapping(value={"site/preAlterar/{idRendaFixa}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView preAlterar(@PathVariable("idRendaFixa") Integer idRendaFixa)
  {
    logger.info("site/preAlterar/{idRendaFixa}");
    RendaFixa rendaFixa = this.rendaFixaService.findById(idRendaFixa);
    ModelAndView model = new ModelAndView("rendaFixaView", "rendaFixaForm", rendaFixa);
    model.addObject("lists", JsonUtil.getListJsonRendaFixa(this.rendaFixaService.findByDataSaidaIsNullAndAtivo()));
    return model;
  }
  
  @RequestMapping(value={"site/historicoRendaFixa"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse historico()
  {
    logger.info("site/historicoRendaFixa");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    res.setResult(JsonUtil.getListJsonRendaFixa(this.rendaFixaService.findByDataSaidaIsNotNullAndAtivo()));
    return res;
  }
}
