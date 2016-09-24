package br.cs.web.chart;

import br.cs.entity.CurvaPatrimonio;
import br.cs.service.CurvaPatrimonioService;
import br.cs.web.json.JsonResponse;
import br.cs.web.json.enums.JsonReponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CurvaPatrimonioController
{
  private static final Logger logger = LoggerFactory.getLogger(GraficoController.class);
  public static final String PATH = "/site/curvaPatrimonio";
  public static final String PATH_VIEW_INIT = "/site/curvaPatrimonio/preCurva.page";
  public static final String PATH_MONTAR_TABELA_PATRIMONIO = "/site/curvaPatrimonio/montarTabelaPatrimonio.json";
  private static final String PAGE = "curvaPatrimonio";
  private static final String FORM_PAGE = "curvaPatrimonioForm";
  @Autowired
  private CurvaPatrimonioService curvaPatrimonioService;
  
  @RequestMapping(value={"/site/curvaPatrimonio/preCurva.page"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView preCurva()
  {
    logger.info("/site/curvaPatrimonio/preCurva.page");
    ModelAndView model = new ModelAndView("curvaPatrimonio", "curvaPatrimonioForm", new CurvaPatrimonio());
    return model;
  }
  
  @RequestMapping(value={"/site/curvaPatrimonio/montarTabelaPatrimonio.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public JsonResponse montaTabelaPatrimonio()
  {
    logger.info("/site/curvaPatrimonio/montarTabelaPatrimonio.json");
    
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setResult(this.curvaPatrimonioService.findAllByOrderByDataAsc());
    return res;
  }
}
