package br.cs.web.home;

import br.cs.entity.Nota;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.service.NotaService;
import br.cs.web.json.JsonResponse;
import br.cs.web.json.JsonUtil;
import br.cs.web.json.enums.JsonReponseEnum;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotaController
{
  private static final Logger logger = LoggerFactory.getLogger(NotaController.class);
  private static final String PATH_PRE_INCLUIR_NOTA = "site/preIncluirNota";
  private static final String PATH_LIMPAR = "site/limparNota";
  private static final String PATH_GRAVAR = "site/gravarNota";
  private static final String PATH_EXCLUIR = "site/excluirNota/{idNota}";
  private static final String PATH_PRE_ALTERAR = "site/preAlterarNota/{idNota}";
  private static final String PATH_RECUPERAR = "site/recuperar/{idNota}";
  @Autowired
  private NotaService notaService;
  
  @InitBinder
  public void initBinder(WebDataBinder webDataBinder)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
  }
  
  @RequestMapping(value={"site/preIncluirNota"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView preIncluirNota()
  {
    logger.info("site/preIncluirNota");
    ModelAndView model = new ModelAndView("notaView", "notaForm", new Nota());
    model.addObject("lists", this.notaService.findAllByAtivo());
    return model;
  }
  
  @RequestMapping(value={"site/limparNota"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse limpar()
  {
    logger.info("site/limparNota");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    return res;
  }
  
  @RequestMapping(value={"site/gravarNota"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public JsonResponse gravar(@Valid @RequestBody Nota nota, BindingResult bindingResult)
  {
    logger.info("site/gravarNota");
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
      this.notaService.salvar(nota);
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setResult(JsonUtil.getListJsonNota(this.notaService.findAllByAtivo()));
    return res;
  }
  
  @RequestMapping(value={"site/excluirNota/{idNota}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse excluirNota(@PathVariable("idNota") Integer idNota)
  {
    logger.info("site/excluirNota/{idNota}");
    JsonResponse res = new JsonResponse();
    this.notaService.inativar(idNota);
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    res.setResult(JsonUtil.getListJsonNota(this.notaService.findAllByAtivo()));
    return res;
  }
  
  @RequestMapping(value={"site/preAlterarNota/{idNota}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView preAlterarNota(@PathVariable("idNota") Integer idNota)
  {
    logger.info("site/preAlterarNota/{idNota}");
    Nota nota = this.notaService.findById(idNota);
    ModelAndView model = new ModelAndView("notaView", "notaForm", nota);
    model.addObject("lists", this.notaService.findAllByAtivo());
    return model;
  }
  
  @RequestMapping(value={"site/recuperar/{idNota}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse recuperar(@PathVariable("idNota") Integer idNota)
  {
    logger.info("site/recuperar/{idNota}" + idNota);
    JsonResponse res = new JsonResponse();
    
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setResult(JsonUtil.getListJsonNota(this.notaService.findById(idNota)));
    return res;
  }
}
