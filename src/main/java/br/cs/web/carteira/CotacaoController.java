package br.cs.web.carteira;

import br.cs.entity.Cotacao;
import br.cs.entity.Papel;
import br.cs.entity.pojt.CotacaoTendencia;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.service.CotacaoService;
import br.cs.service.PapelService;
import br.cs.web.json.JsonCotacao;
import br.cs.web.json.JsonResponse;
import br.cs.web.json.enums.JsonReponseEnum;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CotacaoController
{
  private static final Logger logger = LoggerFactory.getLogger(CotacaoController.class);
  private static final String TYPE_UPLOAD = "application/vnd.ms-excel";
  private static final String PATH_PRE_COTACAO = "site/cotacaoView/{idPapel}";
  private static final String PATH_UPLOAD_COTACAO = "site/uploadPapel";
  private static final String PATH_COTACAO_ANALISAR_ALTA = "site/analisarCotacaoAltaView/{idPapel}";
  private static final String PATH_LIMPAR = "site/limparCotacao";
  private static final String PATH_GRAVAR = "site/gravarCotacao";
  private static final String PATH_ATUALIZAR_BMF = "site/atualizarBMF";
  private static final String PAGE_COTACAO_VIEW = "cotacaoView";
  private static final String FORM_COTACAO = "cotacaoForm";
  @Autowired
  private PapelService papelService;
  @Autowired
  private CotacaoService cotacaoService;
  
  @RequestMapping(value={"site/cotacaoView/{idPapel}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView cotacaoView(@PathVariable("idPapel") Integer idPapel)
  {
    logger.info("site/cotacaoView/{idPapel}" + idPapel);
    ModelAndView model = new ModelAndView("cotacaoView", "cotacaoForm", new Cotacao());
    Papel papel = this.papelService.findById(idPapel);
    model.addObject("papel", papel);
    
    List<Cotacao> list = this.cotacaoService.findAllByPapelOrderByDataDesc(papel);
    model.addObject("lists", list);
    return model;
  }
  
  @RequestMapping(value={"site/analisarCotacaoAltaView/{idPapel}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String analizarAlta(@PathVariable("idPapel") Integer idPapel, @ModelAttribute("cotacaoForm") Cotacao cotacao, ModelMap model)
  {
    logger.info("site/analisarCotacaoAltaView/{idPapel}" + idPapel);
    Papel papel = this.papelService.findById(idPapel);
    model.put("papel", papel);
    
    List<CotacaoTendencia> list = this.cotacaoService.analizarAltaStopLoss(papel);
    if (list.isEmpty()) {
      model.addAttribute("errorMsg", "N�o existe cota��o cadastrada para esse papel. " + papel.getNome());
    } else {
      model.put("lists", list);
    }
    return "analisarCotacaoAltaView";
  }
  
  @RequestMapping(value={"site/uploadPapel"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse upload(MultipartHttpServletRequest request, HttpServletResponse response, @ModelAttribute("cotacaoForm") Cotacao cotacao)
  {
    logger.info("site/uploadPapel");
    JsonResponse res = new JsonResponse();
    Iterator<String> itr = request.getFileNames();
    if (cotacao.getPapel() == null)
    {
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage("O papel n�o foi informado para o servidor.");
      return res;
    }
    if (itr.hasNext())
    {
      MultipartFile multipartFile = request.getFile((String)itr.next());
      if ("application/vnd.ms-excel".equals(multipartFile.getContentType()))
      {
        try
        {
          this.cotacaoService.cadastrarCotacao(multipartFile, cotacao);
          res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
          res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
        }
        catch (InvestimentoBusinessException e)
        {
          logger.debug(e.getMessage());
          res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
          res.setMessage(e.getMessage());
        }
      }
      else
      {
        logger.info("ERRO NO TIPO");
        res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
        res.setMessage("O tipo aceito � vnd.ms-excel");
      }
      Papel papel = this.papelService.findById(cotacao.getPapel().getId());
      List<Cotacao> list = this.cotacaoService.findAllByPapelOrderByDataDesc(papel);
      res.setResult(getListJsonCotacoes(list));
    }
    else
    {
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage("Informe algum arquivo para fazer upload.");
      return res;
    }
    return res;
  }
  
  @RequestMapping(value={"site/gravarCotacao"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse gravar(@Valid @RequestBody Cotacao cotacao, BindingResult bindingResult)
  {
    logger.info("site/gravarCotacao");
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
      this.cotacaoService.salvar(cotacao);
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
    }
    Papel papel = this.papelService.findById(cotacao.getPapel().getId());
    List<Cotacao> list = this.cotacaoService.findAllByPapelOrderByDataDesc(papel);
    
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setResult(getListJsonCotacoes(list));
    return res;
  }
  
  @RequestMapping(value={"site/limparCotacao"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse limpar()
  {
    logger.info("site/limparCotacao");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    return res;
  }
  
  @InitBinder
  public void initBinder(WebDataBinder webDataBinder)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    Locale in_ID = new Locale("pt", "BR");
    DecimalFormat nf = (DecimalFormat)NumberFormat.getInstance(in_ID);
    
    dateFormat.setLenient(false);
    webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    webDataBinder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, nf, true));
  }
  
  @RequestMapping(value={"site/atualizarBMF"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse atulizarBMF(@RequestBody Cotacao cotacao)
  {
    logger.info("site/atualizarBMF");
    JsonResponse res = new JsonResponse();
    try
    {
      this.cotacaoService.atualizarBMF(cotacao);
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    }
    catch (InvestimentoBusinessException e)
    {
      logger.debug(e.getMessage());
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(e.getMessage());
      return res;
    }
    Papel papel = this.papelService.findById(cotacao.getPapel().getId());
    List<Cotacao> list = this.cotacaoService.findAllByPapelOrderByDataDesc(papel);
    
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setResult(getListJsonCotacoes(list));
    return res;
  }
  
  private List<JsonCotacao> getListJsonCotacoes(List<Cotacao> list)
  {
    List<JsonCotacao> result = new ArrayList<JsonCotacao>();
    for (Cotacao p : list) {
      result.add(new JsonCotacao(p));
    }
    return result;
  }
}
