package br.cs.web.carteira;

import br.cs.entity.Empresa;
import br.cs.entity.Papel;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.service.EmpresaService;
import br.cs.service.PapelService;
import br.cs.web.json.JsonResponse;
import br.cs.web.json.JsonUtil;
import br.cs.web.json.enums.JsonReponseEnum;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PapelController
{
  private static final Logger logger = LoggerFactory.getLogger(PapelController.class);
  private static final String PATH_PRE_PAPEL = "site/papelView/{idEmpresa}";
  private static final String PATH_PAEIS_VIEW = "site/papeisView";
  private static final String PATH_LIMPAR = "site/limparPapel";
  private static final String PATH_GRAVAR = "site/gravarPapel";
  private static final String PATH_PRE_ALTERAR = "site/alterarPapel/{idPapel}";
  private static final String PATH_EXCLUIR = "site/excluirPapel/{idPapel}";
  private static final String PATH_ATUALIZAR_TODOS_BMF = "site/atualizarBMFTodos";
  private static final String PATH_GERENCIAR_PAPEL = "site/gerenciarPapel/{idPapel}";
  private static final String PAGE_PAPEL = "papelView";
  private static final String PAGE_PAPEIS = "papeisView";
  private static final String FORM_PAPEL = "papelForm";
  private static final String PAGE_GERENCIAR_PAPEL_VIEW = "gerenciarPapelView";
  private static final String FORM_GERENCIAR_PAPEL_VIEW = "gerenciarPapelForm";
  @Autowired
  private EmpresaService empresaService;
  @Autowired
  private PapelService papelService;
  
  @RequestMapping(value={"site/papelView/{idEmpresa}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView papelView(@PathVariable("idEmpresa") Integer idEmpresa)
  {
    logger.info("site/papelView/{idEmpresa}" + idEmpresa);
    ModelAndView model = new ModelAndView("papelView", "papelForm", new Papel());
    Empresa empresa = this.empresaService.findById(idEmpresa);
    model.addObject("empresa", empresa);
    List<Papel> list = this.papelService.findAllByEmpresaAndAtivo(empresa);
    model.addObject("lists", list);
    return model;
  }
  
  @RequestMapping(value={"site/gerenciarPapel/{idPapel}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView gerenciarPapel(@PathVariable("idPapel") Integer idPapel)
    throws InvestimentoBusinessException
  {
    logger.info("site/gerenciarPapel/{idPapel}" + idPapel);
    
    Papel papel = this.papelService.consolidar(idPapel);
    ModelAndView model = new ModelAndView("gerenciarPapelView", "gerenciarPapelForm", papel.getEmpresa());
    model.addObject("empresa", papel.getEmpresa());
    model.addObject("papel", papel);
    return model;
  }
  
  @RequestMapping(value={"site/papeisView"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String papeISView(ModelMap model)
  {
    logger.info("site/papeisView");
    model.put("lists", this.papelService.findAllByAtivo());
    return "papeisView";
  }
  
  @RequestMapping(value={"site/limparPapel"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse limpar()
  {
    logger.info("site/limparPapel");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    return res;
  }
  
  @RequestMapping(value={"site/gravarPapel"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public JsonResponse gravar(@Valid @RequestBody Papel papel, BindingResult bindingResult)
  {
    logger.info("site/gravarPapel");
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
      this.papelService.salvar(papel);
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    Empresa empresa = this.empresaService.findById(papel.getEmpresa().getId());
    List<Papel> list = this.papelService.findAllByEmpresaAndAtivo(empresa);
    
    res.setResult(JsonUtil.getListJsonPapeis(list));
    return res;
  }
  
  @RequestMapping(value={"site/alterarPapel/{idPapel}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView preAlterar(@PathVariable("idPapel") Integer idPapel)
  {
    logger.info("site/alterarPapel/{idPapel}");
    Papel papel = this.papelService.findById(idPapel);
    ModelAndView model = new ModelAndView("papelView", "papelForm", papel);
    Empresa empresa = this.empresaService.findById(papel.getEmpresa().getId());
    model.addObject("empresa", empresa);
    List<Papel> list = this.papelService.findAllByEmpresaAndAtivo(empresa);
    model.addObject("lists", list);
    return model;
  }
  
  @RequestMapping(value={"site/atualizarBMFTodos"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse atulizarTodosBMF()
  {
    logger.info("site/atualizarBMFTodos");
    JsonResponse res = new JsonResponse();
    try
    {
      this.papelService.schedulerAtualizarCotacao();
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
  
  @RequestMapping(value={"site/excluirPapel/{idPapel}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse excluir(@PathVariable("idPapel") Integer idPapel)
  {
    logger.info("site/excluirPapel/{idPapel}");
    JsonResponse res = new JsonResponse();
    Papel papel = null;
    try
    {
      papel = this.papelService.inativar(idPapel);
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    if (papel != null)
    {
      Empresa empresa = this.empresaService.findById(papel.getEmpresa().getId());
      List<Papel> list = this.papelService.findAllByEmpresaAndAtivo(empresa);
      res.setResult(JsonUtil.getListJsonPapeis(list));
    }
    return res;
  }
}
