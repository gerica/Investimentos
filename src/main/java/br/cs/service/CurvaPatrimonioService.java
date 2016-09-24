package br.cs.service;

import br.cs.component.util.CalculosFinanceiros;
import br.cs.component.util.FormatadorUtil;
import br.cs.entity.CurvaPatrimonio;
import br.cs.entity.OperacaoEntrada;
import br.cs.entity.RendaFixa;
import br.cs.repository.CurvaPatrimonioRepository;
import java.util.Date;
import java.util.List;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurvaPatrimonioService
{
  private static final Logger logger = LoggerFactory.getLogger(CurvaPatrimonioService.class);
  @Autowired
  private CurvaPatrimonioRepository curvaPatrimonioRepository;
  @Autowired
  private RendaFixaService rendaFixaService;
  @Autowired
  private OperacaoEntradaService operacaoEntradaService;
  
  public List<CurvaPatrimonio> findAllByOrderByDataAsc()
  {
    logger.info("findAllOrderByData");
    return this.curvaPatrimonioRepository.findAllByOrderByDataAsc();
  }
  
  public void salvar(CurvaPatrimonio entities)
  {
    logger.info("Gravar entidade: " + entities);
    this.curvaPatrimonioRepository.save(entities);
  }
  
  public void gerenciarCurvaPatrimonial()
  {
    gerenciarCurvaPatrimonial(null);
  }
  
  public void gerenciarCurvaPatrimonial(Date data)
  {
    logger.info("Gerenciar curva Patrimonial");
    Date[] datas = FormatadorUtil.getDataMesAnterior(data);
    Date dataMaior = datas[0];
    Date dataMenor = datas[1];
    
    CurvaPatrimonio curvaPatrimonio = this.curvaPatrimonioRepository.findByDataLessThanEqualAndDataGreaterThanEqual(dataMaior, dataMenor);
    if (curvaPatrimonio == null)
    {
      consolidarCurvaPatrimonialMes(dataMaior);
      logger.info("Consolida��o para o m�s: " + datas[0] + " realizada com sucesso.");
    }
    else
    {
      logger.info("Consolida��o para o m�s: " + datas[0] + " j� foi realizada.");
    }
  }
  
  private void consolidarCurvaPatrimonialMes(Date dataMaior)
  {
    CurvaPatrimonio curva = new CurvaPatrimonio();
    curva.setData(dataMaior);
    curva.setValorRendaVariavel(calcularRendaVariavelMes(dataMaior));
    curva.setValorRendaFixa(calcularRendaFixaMes(dataMaior));
    curva.setValorTotal(Double.valueOf(curva.getValorRendaFixa().doubleValue() + curva.getValorRendaVariavel().doubleValue()));
    curva.setDoisPorcento(Double.valueOf(curva.getValorTotal().doubleValue() * 0.02D));
    curva.setSeisPorcento(Double.valueOf(curva.getValorTotal().doubleValue() * 0.06D));
    curva.setVariacao(calcularVariacaoMes(curva));
    salvar(curva);
  }
  
  private Double calcularRendaVariavelMes(Date dataMaior)
  {
    Double valor = Double.valueOf(0.0D);
    List<OperacaoEntrada> operacoesEntrada = this.operacaoEntradaService.findByDataLessThanEqualAndAtivo(dataMaior);
    for (OperacaoEntrada operacao : operacoesEntrada) {
      valor = Double.valueOf(valor.doubleValue() + operacao.getQuantidade().intValue() * operacao.getPrecoUnitario().doubleValue());
    }
    return valor;
  }
  
  public Double calcularRendaFixaMes(Date dataMaior)
  {
    Double valor = Double.valueOf(0.0D);
    List<RendaFixa> rendasFixa = this.rendaFixaService.findBydataEntradaLessThanEqualAndDataSaidaIsNullAndAtivo(dataMaior);
    for (RendaFixa fixa : rendasFixa)
    {
      int diffMes = FormatadorUtil.differencaEmMes(new LocalDate(fixa.getDataEntrada()), new LocalDate(dataMaior));
      if (diffMes >= 0) {
        valor = Double.valueOf(valor.doubleValue() + CalculosFinanceiros.calcularJurosCompostosMontante(fixa.getInvestimento(), fixa.getTaxa(), diffMes).doubleValue());
      }
    }
    return valor;
  }
  
  private Double calcularVariacaoMes(CurvaPatrimonio curvaAtual)
  {
    Double valor = Double.valueOf(0.0D);
    Date[] datas = FormatadorUtil.getDataMesAnterior(curvaAtual.getData());
    Date dataMaior = datas[0];
    Date dataMenor = datas[1];
    CurvaPatrimonio curvaAnterior = this.curvaPatrimonioRepository.findByDataLessThanEqualAndDataGreaterThanEqual(dataMaior, dataMenor);
    if (curvaAnterior != null)
    {
      Double valorAnterior = curvaAnterior.getValorTotal();
      Double valorCorrente = curvaAtual.getValorTotal();
      Double valorDiferenca = Double.valueOf(valorCorrente.doubleValue() - valorAnterior.doubleValue());
      valor = CalculosFinanceiros.calcularVariacaoEntreValores(valorAnterior, valorDiferenca);
    }
    return valor;
  }
}
