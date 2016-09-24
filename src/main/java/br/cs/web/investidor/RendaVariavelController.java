package br.cs.web.investidor;

import br.cs.entity.Empresa;
import br.cs.entity.OperacaoEntrada;
import br.cs.entity.OperacaoSaida;
import br.cs.entity.Papel;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.service.EmpresaService;
import br.cs.service.OperacaoEntradaService;
import br.cs.service.OperacaoSaidaService;
import br.cs.service.PapelService;
import br.cs.web.json.JsonResponse;
import br.cs.web.json.JsonResult;
import br.cs.web.json.JsonUtil;
import br.cs.web.json.enums.JsonReponseEnum;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class RendaVariavelController
{
  private static final Logger logger = LoggerFactory.getLogger(RendaVariavelController.class);
  private static final String PATH_OPERACAO_VIEW = "site/operacaoView";
  private static final String PATH_MONTAR_COMBO_PAPEL = "site/montarComboPapel/{idEmpresa}";
  private static final String PATH_GRAVAR = "site/gravarOperacao";
  private static final String PATH_LIMPAR = "site/limparOperacao";
  private static final String PATH_AVALIAR_ENTRADA = "site/operacaoAvaliacaoEntrada/{idOperacao}";
  private static final String PATH_APAGAR_OPERACAO_ENTRADA = "site/apagarOoperacaoEntrada/{idOperacao}";
  private static final String PATH_VISUALIZAR_OPERACAO_ENTRADA = "site/visualizarOperacao/{idOperacao}";
  private static final String PATH_PRE_OPERACAO_SAIDA = "site/preOperacaoSaida/{idOperacao}";
  private static final String PATH_OPERACAO_SAIDA_GRAVAR = "site/gravarOperacaoSaida";
  private static final String PATH_OPERACAO_SAIDA_LIMPAR = "site/limparOperacaoSaida";
  private static final String PATH_HISTORICO_OPERACAO = "site/historicoOperacao";
  private static final String FROM_OPERACAO_ENTRADA = "operacaoEntradaForm";
  private static final String PAGE_OPERACAO = "operacaoView";
  @Autowired
  private EmpresaService empresaService;
  @Autowired
  private PapelService papelService;
  @Autowired
  private OperacaoEntradaService operacaoEntradaService;
  @Autowired
  private OperacaoSaidaService operacaoSaidaService;
  
  @RequestMapping(value={"site/operacaoView"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView preOperacaoEntrada()
  {
    logger.info("site/operacaoView");
    ModelAndView model = new ModelAndView("operacaoView", "operacaoEntradaForm", new OperacaoEntrada());
    
    model.addObject("empresaLista", getComboBoxEmpresa());
    model.addObject("operacoesLista", getComboBoxTipoOperacao());
    model.addObject("lists", this.operacaoEntradaService.findAllOperacaoAtiva());
    
    return model;
  }
  
  @RequestMapping(value={"site/montarComboPapel/{idEmpresa}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public JsonResponse montaComboPapel(@PathVariable("idEmpresa") Integer idEmpresa)
  {
    logger.info("site/montarComboPapel/{idEmpresa}");
    Empresa empresa = this.empresaService.findById(idEmpresa);
    
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setResult(getComboBoxPapel(empresa));
    return res;
  }
  
  @RequestMapping(value={"site/operacaoAvaliacaoEntrada/{idOperacao}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse avaliarEntrada(@PathVariable("idOperacao") Integer idOperacao)
  {
    logger.info("site/operacaoAvaliacaoEntrada/{idOperacao}" + idOperacao);
    JsonResponse res = new JsonResponse();
    try
    {
      this.operacaoEntradaService.avaliarEntrada(idOperacao);
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    res.setResult(JsonUtil.getListJsonRendaVariavel(this.operacaoEntradaService.findAllOperacaoAtiva()));
    return res;
  }
  
  @RequestMapping(value={"site/apagarOoperacaoEntrada/{idOperacao}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse excluirNota(@PathVariable("idOperacao") Integer idOperacao)
  {
    logger.info("site/apagarOoperacaoEntrada/{idOperacao}");
    JsonResponse res = new JsonResponse();
    try
    {
      this.operacaoEntradaService.inativar(idOperacao);
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    res.setResult(JsonUtil.getListJsonRendaVariavel(this.operacaoEntradaService.findAllOperacaoAtiva()));
    return res;
  }
  
  @RequestMapping(value={"site/visualizarOperacao/{idOperacao}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String visualizarOperacao(@PathVariable("idOperacao") Integer idOperacao, ModelMap model)
  {
    logger.info("site/visualizarOperacao/{idOperacao}" + idOperacao);
    
    OperacaoEntrada operacaoEntrada = this.operacaoEntradaService.findById(idOperacao);
    model.put("operacaoEntrada", operacaoEntrada);
    
    return "operacaoDetalheView";
  }
  
  @RequestMapping(value={"site/gravarOperacao"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public JsonResponse gravar(@Valid @RequestBody OperacaoEntrada operacao, BindingResult bindingResult)
  {
    logger.info("site/gravarOperacao");
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
      this.operacaoEntradaService.salvar(operacao);
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    res.setResult(JsonUtil.getListJsonRendaVariavel(this.operacaoEntradaService.findAllOperacaoAtiva()));
    return res;
  }
  
  @RequestMapping(value={"site/limparOperacao"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse limpar()
  {
    logger.info("site/limparOperacao");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    return res;
  }
  
  @RequestMapping(value={"site/preOperacaoSaida/{idOperacao}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String preSaida(@PathVariable("idOperacao") Integer idOperacao, ModelMap model)
  {
    logger.info("site/limparOperacao");
    model.put("operacaoSaidaForm", new OperacaoSaida());
    model.put("operacaoEntrada", this.operacaoEntradaService.findById(idOperacao));
    return "operacaoSaidaView";
  }
  
  @RequestMapping(value={"site/limparOperacaoSaida"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse limparSaida()
  {
    logger.info("site/limparOperacao");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    return res;
  }
  
  @RequestMapping(value={"site/gravarOperacaoSaida"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public JsonResponse gravarSaida(@Valid @RequestBody OperacaoSaida operacao, BindingResult bindingResult, ModelMap model)
  {
    logger.info("site/gravarOperacaoSaida");
    
    logger.info("site/gravarOperacao");
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
      this.operacaoSaidaService.salvar(operacao);
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    res.setResult(JsonUtil.getListJsonRendaVariavel(this.operacaoEntradaService.findAllOperacaoAtiva()));
    return res;
  }
  
  @InitBinder
  public void initBinder(WebDataBinder webDataBinder)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat dfImporte = new DecimalFormat("#,##0.00");
    dateFormat.setLenient(false);
    webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    webDataBinder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, dfImporte, true));
  }
  
  @RequestMapping(value={"site/historicoOperacao"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse historico()
  {
    logger.info("site/historicoOperacao");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    res.setResult(this.operacaoEntradaService.findAllOperacao());
    return res;
  }
  
  private Map<Integer, String> getComboBoxEmpresa()
  {
    List<Empresa> list = this.empresaService.findAll();
    Map<Integer, String> map = new HashMap<Integer, String>();
    for (Empresa empresa : list) {
      map.put(empresa.getId(), empresa.getNome());
    }
    return map;
  }
  
  private List<JsonResult> getComboBoxPapel(Empresa empresa)
  {
    List<Papel> list = this.papelService.findAllByEmpresa(empresa);
    List<JsonResult> listResult = new ArrayList<JsonResult>();
    for (Papel papel : list)
    {
      JsonResult result = new JsonResult();
      result.setId(papel.getId());
      result.setValor(papel.getNome());
      listResult.add(result);
    }
    return listResult;
  }
  
  private Map<String, String> getComboBoxTipoOperacao()
  {
    Map<String, String> list = new HashMap<String, String>();
    list.put("comprar", "Comprar");
    list.put("vender", "Vender");
    return list;
  }
}
