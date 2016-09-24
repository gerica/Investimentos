package br.cs.web.carteira;

import br.cs.entity.Empresa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarteiraAngularController
{
  private static final Logger logger = LoggerFactory.getLogger(CarteiraAngularController.class);
  private static final String PATH_CARTEIRA_ANGULAR_VIEW = "site/carteiraAngularView";
  private static final String PATH_GRAVAR_EMPRESA = "gravarEmpresa";
  
  @RequestMapping(value={"site/carteiraAngularView"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String preIncluir()
  {
    return "carteiraAngularView";
  }
  
  @RequestMapping(value={"gravarEmpresa"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Empresa createEmployee(@RequestBody Empresa emp)
  {
    logger.info("gravarEmpresa");
    return emp;
  }
}
