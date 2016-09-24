package br.cs.entity;

import br.cs.component.util.FormatadorUtil;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="curva_patrimonio")
public class CurvaPatrimonio
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Integer id;
  @Column(name="data")
  private Date data;
  @Column(name="renda_variavel")
  private Double valorRendaVariavel;
  @Column(name="renda_fixa")
  private Double valorRendaFixa;
  @Column(name="valor_total")
  private Double valorTotal;
  @Column(name=" dois_procento")
  private Double doisPorcento;
  @Column(name="seis_porcento")
  private Double seisPorcento;
  @Column(name="variacao")
  private Double variacao;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Date getData()
  {
    return this.data;
  }
  
  public void setData(Date data)
  {
    this.data = data;
  }
  
  public Double getValorRendaVariavel()
  {
    return this.valorRendaVariavel;
  }
  
  public void setValorRendaVariavel(Double valorRendaVariavel)
  {
    this.valorRendaVariavel = valorRendaVariavel;
  }
  
  public Double getValorRendaFixa()
  {
    return this.valorRendaFixa;
  }
  
  public void setValorRendaFixa(Double valorRendaFixa)
  {
    this.valorRendaFixa = valorRendaFixa;
  }
  
  public Double getValorTotal()
  {
    return this.valorTotal;
  }
  
  public void setValorTotal(Double valorTotal)
  {
    this.valorTotal = valorTotal;
  }
  
  public Double getDoisPorcento()
  {
    return this.doisPorcento;
  }
  
  public void setDoisPorcento(Double doisPorcento)
  {
    this.doisPorcento = doisPorcento;
  }
  
  public Double getSeisPorcento()
  {
    return this.seisPorcento;
  }
  
  public void setSeisPorcento(Double seisPorcento)
  {
    this.seisPorcento = seisPorcento;
  }
  
  public Double getVariacao()
  {
    return this.variacao;
  }
  
  public void setVariacao(Double variacao)
  {
    this.variacao = variacao;
  }
  
  public String getDataFormatada()
  {
    return FormatadorUtil.formatarData(this.data);
  }
  
  public String getValor2Porcento()
  {
    return FormatadorUtil.formatarMoeda(this.doisPorcento);
  }
  
  public String getValor6Porcento()
  {
    return FormatadorUtil.formatarMoeda(this.seisPorcento);
  }
  
  public String getValorTotalFormatado()
  {
    return FormatadorUtil.formatarMoeda(this.valorTotal);
  }
  
  public Long getDataString()
  {
    return Long.valueOf(this.data.getTime());
  }
  
  public String getVariacaoFormatada()
  {
    return FormatadorUtil.formatarPorcentagem(this.variacao);
  }
  
  public String toString()
  {
    return 
      "CurvaPatrimonio [id=" + this.id + ", data=" + this.data + ", valorRendaVariavel=" + this.valorRendaVariavel + ", valorRendaFixa=" + this.valorRendaFixa + ", valorTotal=" + this.valorTotal + ", doisPorcento=" + this.doisPorcento + ", seisPorcento=" + this.seisPorcento + ", variacao=" + this.variacao + "]";
  }
}
