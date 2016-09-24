package br.cs.web.investidor;

import br.cs.service.FundamentoService;
import br.cs.web.json.JsonResponse;
import br.cs.web.json.JsonUtil;
import br.cs.web.json.enums.JsonReponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnaliseFundamentalistaController
{
  private static final Logger logger = LoggerFactory.getLogger(AnaliseFundamentalistaController.class);
  private static final String PATH = "site/fundamentalista/";
  private static final String PATH_ABA_VIEW = "site/fundamentalista/analiseFundamentalistaView/{idPapel}";
  @Autowired
  private FundamentoService fundamentoService;
  
  @RequestMapping(value={"site/fundamentalista/analiseFundamentalistaView/{idPapel}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public JsonResponse abaFundamentalistaView(@PathVariable("idPapel") Integer idPapel)
  {
    logger.info("site/fundamentalista/analiseFundamentalistaView/{idPapel}");
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setObjeto(JsonUtil.getJsonFundamento(this.fundamentoService.findLastFundamento(idPapel)));
    res.setController("analiseFundamentalistaView");
    return res;
  }
}
