package br.cs.web.home;

import br.cs.service.CurvaPatrimonioService;
import br.cs.service.NotaService;
import br.cs.service.PapelService;
import br.cs.service.RendaFixaService;
import br.cs.web.LoginController;
import br.cs.web.json.JsonUtil;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InformacaoController
{
  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
  private static final String PATH_INDEX = "/site/index";
  private static final String PAGE_INDEX = "index";
  @Autowired
  private NotaService notaService;
  @Autowired
  private PapelService papelService;
  @Autowired
  private RendaFixaService rendaFixaService;
  @Autowired
  private CurvaPatrimonioService curvaPatrimonioService;
  
  @RequestMapping(value={"/site/index"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String index(ModelMap model)
  {
    logger.info("/site/index");
    this.curvaPatrimonioService.gerenciarCurvaPatrimonial();
    model.put("lists", this.notaService.findAllByAtivo());
    model.put("listsInvest", this.papelService.findBalancoHoje());
    model.put("listsRendaFixa", JsonUtil.getListJsonRendaFixa(this.rendaFixaService.findByDataSaidaIsNullAndAtivo()));
    model.put("qtdNotas", this.notaService.countByAtivo());
    return "index";
  }
  
  @RequestMapping({"error.html"})
  public String error(HttpServletRequest request, Model model)
  {
    model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
    Throwable throwable = (Throwable)request.getAttribute("javax.servlet.error.exception");
    String errorMessage = null;
    if (throwable != null) {
      errorMessage = throwable.getMessage();
    }
    model.addAttribute("errorMessage", errorMessage);
    return "error.html";
  }
}
