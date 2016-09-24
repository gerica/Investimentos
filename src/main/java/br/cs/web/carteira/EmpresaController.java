package br.cs.web.carteira;

import br.cs.entity.Empresa;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.service.EmpresaService;
import br.cs.service.PapelService;
import br.cs.web.json.JsonResponse;
import br.cs.web.json.JsonUtil;
import br.cs.web.json.enums.JsonReponseEnum;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class EmpresaController
  extends WebMvcConfigurerAdapter
{
  private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);
  private static final String PATH_CARTEIRA_VIEW = "site/carteiraView";
  private static final String PATH_LIMPAR = "site/limpar";
  private static final String PATH_GRAVAR = "site/gravar";
  private static final String PATH_PRE_ALTERAR = "site/alterarCateira/{idCarteira}";
  private static final String PATH_EXCLUIR = "site/excluirCateira/{idCarteira}";
  private static final String PAGE_EMPRESA_VIEW = "empresaView";
  private static final String FORM_EMPRESA_VIEW = "carteiraForm";
  @Autowired
  private EmpresaService empresaService;
  @Autowired
  private PapelService papelService;
  
  @RequestMapping(value={"site/carteiraView"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView carteiraView()
  {
    logger.info("site/carteiraView");
    
    List<Empresa> list = this.empresaService.findAllByAtivo();
    ModelAndView model = new ModelAndView("empresaView", "carteiraForm", new Empresa());
    model.addObject("lists", list);
    
    return model;
  }
  
  @RequestMapping(value={"site/limpar"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse limpar(HttpServletRequest request, Map<String, Object> model)
  {
    logger.info("site/limpar");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    return res;
  }
  
  @RequestMapping(value={"site/gravar"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public JsonResponse gravar(@Valid @RequestBody Empresa carteira, BindingResult bindingResult)
  {
    logger.info("site/gravar");
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
      this.empresaService.salvar(carteira);
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    res.setResult(JsonUtil.getListJsonEmpersa(this.empresaService.findAllByAtivo()));
    
    return res;
  }
  
  @RequestMapping(value={"site/alterarCateira/{idCarteira}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView preAlterar(@PathVariable("idCarteira") Integer idCarteira)
  {
    logger.info("site/alterarCateira/{idCarteira}");
    ModelAndView model = new ModelAndView("empresaView", "carteiraForm", this.empresaService.findById(idCarteira));
    model.addObject("lists", this.empresaService.findAllByAtivo());
    return model;
  }
  
  @RequestMapping(value={"site/excluirCateira/{idCarteira}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public JsonResponse excluir(@PathVariable("idCarteira") Integer idCarteira)
  {
    logger.info("site/excluirCateira/{idCarteira}");
    JsonResponse res = new JsonResponse();
    try
    {
      this.empresaService.inativar(idCarteira);
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    res.setResult(JsonUtil.getListJsonEmpersa(this.empresaService.findAllByAtivo()));
    return res;
  }
}
