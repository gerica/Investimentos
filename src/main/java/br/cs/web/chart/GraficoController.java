package br.cs.web.chart;

import br.cs.entity.Empresa;
import br.cs.entity.Papel;
import br.cs.service.EmpresaService;
import br.cs.service.PapelService;
import br.cs.web.chart.enuns.ChartTypeEnum;
import br.cs.web.dtos.GraficoRendaVariavelDTO;
import br.cs.web.json.JsonResponse;
import br.cs.web.json.JsonResult;
import br.cs.web.json.enums.JsonReponseEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GraficoController
{
  private static final Logger logger = LoggerFactory.getLogger(GraficoController.class);
  public static final String PATH_GRAFICOS = "/site/graficos";
  public static final String PATH_VIEW_CHARTS = "/site/graficos/telaChartS";
  public static final String PATH_VIEW_CHARTS_RENDA_FIXA = "/site/graficos/telaChartRendaFixa";
  public static final String PATH_LINE_PAPEIS = "/site/graficos/lineChart";
  public static final String PATH_CANDLE_PAPEIS = "/site/graficos/candleChart/{idPapel}";
  private static final String PATH_MONTAR_COMBO_PAPEL = "/site/graficos/montarComboPapel/{idEmpresa}";
  private static final String PATH_PESQUISAR = "/site/graficos/pesquisar";
  private static final String PATH_LIMPAR = "/site/graficos/limpar";
  private static final String PAGE = "chartRendaVariavel";
  private static final String FORM_PAGE = "graficoRendaVariavelForm";
  private static final String FORM_PAGE_RENDA_FIXA = "graficoRendaFixaForm";
  @Autowired
  private PapelService papelService;
  @Autowired
  private EmpresaService empresaService;
  
  @RequestMapping(value={"/site/graficos/telaChartS"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView preChartS()
  {
    logger.info("/site/graficos/telaChartS");
    GraficoRendaVariavelDTO modelObject = new GraficoRendaVariavelDTO();
    modelObject.setChartType(ChartTypeEnum.CHART_LINE.getDesc());
    ModelAndView model = new ModelAndView("chartRendaVariavel", "graficoRendaVariavelForm", modelObject);
    model.addObject("empresaLista", getComboBoxEmpresa());
    model.addObject("tiposGraficosLista", getComboBoxTipoGrafico());
    return model;
  }
  
  @RequestMapping(value={"/site/graficos/telaChartRendaFixa"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView preChartsRendaFixa()
  {
    logger.info("/site/graficos/telaChartRendaFixa");
    ModelAndView model = new ModelAndView("chartRendaVariavel", "graficoRendaFixaForm", new GraficoRendaVariavelDTO());
    model.addObject("empresaLista", getComboBoxEmpresa());
    model.addObject("tiposGraficosLista", getComboBoxTipoGrafico());
    return model;
  }
  
  @RequestMapping(value={"/site/graficos/lineChart"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public JsonResponse montaLineChart()
  {
    logger.info("/site/graficos/lineChart");
    
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
    res.setResult(this.papelService.getJsonCharLinePapel(Integer.valueOf(11)));
    return res;
  }
  
  @RequestMapping(value={"/site/graficos/candleChart/{idPapel}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public String montaCandleChart(@PathVariable("idPapel") Integer idPapel, ModelMap model)
  {
    logger.info("/site/graficos/candleChart/{idPapel}");
    
    return null;
  }
  
  @RequestMapping(value={"/site/graficos/montarComboPapel/{idEmpresa}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public JsonResponse montaComboPapel(@PathVariable("idEmpresa") Integer idEmpresa)
  {
    logger.info("/site/graficos/montarComboPapel/{idEmpresa}");
    Empresa empresa = this.empresaService.findById(idEmpresa);
    
    JsonResponse res = new JsonResponse();
    res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
    res.setResult(getComboBoxPapel(empresa));
    return res;
  }
  
  @RequestMapping(value={"/site/graficos/pesquisar"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public JsonResponse pesquisar(@RequestBody GraficoRendaVariavelDTO dto)
  {
    logger.info("/site/graficos/pesquisar");
    JsonResponse res = new JsonResponse();
    if ((dto.getChartType() == null) || ("".equals(dto.getChartType())))
    {
      logger.info("ERRO NA VALIDA��O");
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage("Informe o tipo de gr�fico.");
      return res;
    }
    if (ChartTypeEnum.CHART_LINE.getDesc().equals(dto.getChartType()))
    {
      if (dto.getPapel() != null) {
        res.setResult(this.papelService.getJsonCharLinePapel(dto.getPapel().getId()));
      } else {
        res.setResult(this.papelService.getJsonCharLinePapel(null));
      }
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
      res.setObjeto(ChartTypeEnum.CHART_LINE.getDesc());
    }
    else if (ChartTypeEnum.CHART_CANDLE.getDesc().equals(dto.getChartType()))
    {
      if (dto.getPapel() != null) {
        res.setResult(this.papelService.getJsonCharCandlePapel(dto.getPapel().getId()));
      } else {
        res.setResult(this.papelService.getJsonCharCandlePapel(null));
      }
      res.setStatus(JsonReponseEnum.RESPONSE_SUCCESS.getTipo());
      res.setMessage(JsonReponseEnum.MSG_SUCCESS.getTipo());
      res.setObjeto(ChartTypeEnum.CHART_CANDLE.getDesc());
    }
    else
    {
      res.setStatus(JsonReponseEnum.RESPONSE_FAIL.getTipo());
      res.setMessage("Esse tipo de gr�fico ser� implementado.");
    }
    return res;
  }
  
  @RequestMapping(params={"/site/graficos/limpar"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String limpar(@ModelAttribute("graficoRendaVariavelForm") GraficoRendaVariavelDTO dto, BindingResult bindingResult, ModelMap model)
  {
    logger.info("/site/graficos/limpar");
    model.put("graficoRendaVariavelForm", new GraficoRendaVariavelDTO());
    model.put("empresaLista", getComboBoxEmpresa());
    model.put("tiposGraficosLista", getComboBoxTipoGrafico());
    
    return "chartRendaVariavel";
  }
  
  private Map<Integer, String> getComboBoxEmpresa()
  {
    List<Empresa> list = this.empresaService.findAllByAtivo();
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
  
  private Map<String, String> getComboBoxTipoGrafico()
  {
    Map<String, String> list = new HashMap<String, String>();
    list.put(ChartTypeEnum.CHART_LINE.getDesc(), "Linha");
    list.put(ChartTypeEnum.CHART_CANDLE.getDesc(), "Vela");
    return list;
  }
}
