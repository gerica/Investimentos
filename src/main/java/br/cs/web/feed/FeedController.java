package br.cs.web.feed;

import br.cs.entity.SiteFeed;
import br.cs.service.FeedMessageServie;
import br.cs.service.SiteFeedServie;
import br.cs.web.json.JsonResponse;
import br.cs.web.json.JsonUtil;
import br.cs.web.json.enums.JsonReponseEnum;
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

@Controller
public class FeedController
{
  private static final Logger logger = LoggerFactory.getLogger(FeedController.class);
  private static final String PATH_FEED = "site/feed/";
  private static final String PATH_VIEW_ALL = "site/feed/verTodosRss";
  private static final String PATH_ATUALIZAR_FEED_LIDO = "site/feed/atualizarFeedLidoNaoLido/{idFeedMessage}/{isLido}";
  private static final String PATH_ATIVAR_DESATIVAR_FAVORITO = "site/feed/adicionarRemoverFavoritos/{idFeedMessage}/{isFavorito}";
  private static final String PATH_TOP_CINCO_FEEDS = "site/feed/topCincoFeeds.json";
  private static final String PATH_CRUD_FEED = "site/feed/crudFeedPage.json";
  private static final String PATH_GERENCIAR_FEED = "site/feed/gerenciarFeeds.json";
  private static final String PATH_VALIDAR_FEED = "site/feed/validarFeedSite";
  private static final String PATH_GRAVAR_FEED = "site/feed/gravarFeedSite";
  private static final String PATH_PRE_ALTERAR_FEED = "site/feed/alterarSiteFeed/{idSiteFeed}";
  private static final String PATH_EXCLUIR_FEED = "site/feed/excluirSiteFeed/{idSiteFeed}";
  private static final String PATH_ATIVAR_INATIVAR_FEED = "site/feed/ativarInativarSiteFeed/{idSiteFeed}";
  private static final String PATH_LER_TODOS_FEED = "site/feed/lerTodosFeeds.json";
  private static final String PAGE_FEED = "feedsView";
  private static final String MSG_INVALIDO_URL = "A url informada n�o � um feed de rss v�lido.";
  @Autowired
  private FeedMessageServie feedMessageServie;
  @Autowired
  private SiteFeedServie siteFeedServie;
  
  @RequestMapping(value={"site/feed/verTodosRss"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String visualizarTodosFeeds(ModelMap model)
  {
    logger.info("site/feed/verTodosRss");
    model.put("listFeeds", JsonUtil.getListJsonFeedMessage(this.feedMessageServie.findAllByOrderByDataDesc()));
    return "feedsView";
  }
  
  @RequestMapping(value={"site/feed/atualizarFeedLidoNaoLido/{idFeedMessage}/{isLido}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse atualizarFeedLido(@PathVariable("idFeedMessage") Integer idFeedMessage, @PathVariable("isLido") Boolean isLido)
  {
    logger.info("site/feed/atualizarFeedLidoNaoLido/{idFeedMessage}/{isLido}" + idFeedMessage);
    this.feedMessageServie.atualizarFeedLidoNaoLido(idFeedMessage, isLido);
    JsonResponse res = new JsonResponse();
    
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    return res;
  }
  
  @RequestMapping(value={"site/feed/adicionarRemoverFavoritos/{idFeedMessage}/{isFavorito}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse adicionarRemoverFavoritos(@PathVariable("idFeedMessage") Integer idFeedMessage, @PathVariable("isFavorito") Boolean isFavorito)
  {
    logger.info("site/feed/adicionarRemoverFavoritos/{idFeedMessage}/{isFavorito}" + idFeedMessage);
    this.feedMessageServie.adicionarRemoverFavoritos(idFeedMessage, isFavorito);
    JsonResponse res = new JsonResponse();
    
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    return res;
  }
  
  @RequestMapping(value={"site/feed/topCincoFeeds.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public JsonResponse topCincoFeeds()
  {
    logger.info("site/feed/topCincoFeeds.json");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(this.feedMessageServie.countByLido());
    res.setResult(JsonUtil.getListJsonFeedMessage(this.feedMessageServie.findTop5ByLidoOrderByDataDesc()));
    return res;
  }
  
  @RequestMapping(value={"site/feed/crudFeedPage.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public JsonResponse telaCrudFeed()
  {
    logger.info("site/feed/crudFeedPage.json");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setResult(JsonUtil.getListJsonSiteFeed(this.siteFeedServie.findAll()));
    res.setController("site/feed/crudFeedPage.json");
    return res;
  }
  
  @RequestMapping(value={"site/feed/lerTodosFeeds.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse lerTodosFeeds()
  {
    logger.info("site/feed/lerTodosFeeds.json");
    JsonResponse res = new JsonResponse();
    
    this.siteFeedServie.lerTodosFeed();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    
    return res;
  }
  
  @RequestMapping(value={"site/feed/gerenciarFeeds.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public JsonResponse gerenciarFeeds()
  {
    logger.info("site/feed/gerenciarFeeds.json");
    JsonResponse res = new JsonResponse();
    
    this.siteFeedServie.deletarFeed();
    this.siteFeedServie.gerenciarFeed();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    
    return res;
  }
  
  @RequestMapping(value={"site/feed/validarFeedSite"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public JsonResponse validarSiteFeed(@Valid @RequestBody SiteFeed siteFeed, BindingResult bindingResult)
  {
    logger.info("site/feed/validarFeedSite");
    JsonResponse res = new JsonResponse();
    if (bindingResult.hasErrors())
    {
      logger.info("ERRO NA VALIDA��O");
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(bindingResult.getAllErrors());
      return res;
    }
    boolean valido = this.siteFeedServie.validarSiteFeed(siteFeed);
    if (valido)
    {
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
      res.setResult(JsonUtil.getListJsonSiteFeed(this.siteFeedServie.findAll()));
    }
    else
    {
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage("A url informada n�o � um feed de rss v�lido.");
    }
    return res;
  }
  
  @RequestMapping(value={"site/feed/gravarFeedSite"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public JsonResponse gravarSiteFeed(@Valid @RequestBody SiteFeed siteFeed, BindingResult bindingResult)
  {
    logger.info("site/feed/gravarFeedSite");
    JsonResponse res = new JsonResponse();
    if (bindingResult.hasErrors())
    {
      logger.info("ERRO NA VALIDA��O");
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage(bindingResult.getAllErrors());
      return res;
    }
    boolean valido = this.siteFeedServie.gravarSiteFeed(siteFeed);
    if (valido)
    {
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
      res.setResult(JsonUtil.getListJsonSiteFeed(this.siteFeedServie.findAll()));
    }
    else
    {
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      
      res.setMessage("A url informada n�o � um feed de rss v�lido.");
    }
    return res;
  }
  
  @RequestMapping(value={"site/feed/alterarSiteFeed/{idSiteFeed}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse preAlterar(@PathVariable("idSiteFeed") Integer idSiteFeed)
  {
    logger.info("site/feed/alterarSiteFeed/{idSiteFeed}");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setResult(JsonUtil.getListJsonSiteFeed(this.siteFeedServie.findAll()));
    res.setObjeto(this.siteFeedServie.findById(idSiteFeed));
    res.setMessage("Alterar Site feed");
    return res;
  }
  
  @RequestMapping(value={"site/feed/excluirSiteFeed/{idSiteFeed}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse excluir(@PathVariable("idSiteFeed") Integer idSiteFeed)
  {
    logger.info("site/feed/excluirSiteFeed/{idSiteFeed}");
    JsonResponse res = new JsonResponse();
    
    this.siteFeedServie.excluirSiteFeed(idSiteFeed);
    
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    res.setResult(JsonUtil.getListJsonSiteFeed(this.siteFeedServie.findAll()));
    return res;
  }
  
  @RequestMapping(value={"site/feed/ativarInativarSiteFeed/{idSiteFeed}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public JsonResponse ativarInativarFeed(@PathVariable("idSiteFeed") Integer idSiteFeed)
  {
    logger.info("site/feed/ativarInativarSiteFeed/{idSiteFeed}");
    JsonResponse res = new JsonResponse();
    
    this.siteFeedServie.ativarInativarSiteFeed(idSiteFeed);
    
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    res.setResult(JsonUtil.getListJsonSiteFeed(this.siteFeedServie.findAll()));
    return res;
  }
}
