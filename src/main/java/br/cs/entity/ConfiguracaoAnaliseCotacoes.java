package br.cs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="configuracao_analise_cotacoes")
public class ConfiguracaoAnaliseCotacoes
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Integer id;
  @Column(name="qtd_dias_apresentar_cotacoes")
  private Integer qtdDiasApresentarCotacoes;
  @Column(name="risco_stop_loss")
  private Integer riscoStopLoss;
  @Column(name="risco_stop_win")
  private Integer riscoStopWin;
  @Column(name="qtd_dias_calculo_stop_loss")
  private Integer qtdDiasCalculoStopLoss;
  @Column(name="qtd_dias_calculo_stop_win")
  private Integer qtdDiasCalculoStopWin;
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="id_usuario", nullable=false)
  private Usuario usuario;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getQtdDiasApresentarCotacoes()
  {
    return this.qtdDiasApresentarCotacoes;
  }
  
  public void setQtdDiasApresentarCotacoes(Integer qtdDiasApresentarCotacoes)
  {
    this.qtdDiasApresentarCotacoes = qtdDiasApresentarCotacoes;
  }
  
  public Integer getRiscoStopLoss()
  {
    return this.riscoStopLoss;
  }
  
  public void setRiscoStopLoss(Integer riscoStopLoss)
  {
    this.riscoStopLoss = riscoStopLoss;
  }
  
  public Integer getQtdDiasCalculoStopLoss()
  {
    return this.qtdDiasCalculoStopLoss;
  }
  
  public void setQtdDiasCalculoStopLoss(Integer qtdDiasCalculoStopLoss)
  {
    this.qtdDiasCalculoStopLoss = qtdDiasCalculoStopLoss;
  }
  
  public Integer getQtdDiasCalculoStopWin()
  {
    return this.qtdDiasCalculoStopWin;
  }
  
  public void setQtdDiasCalculoStopWin(Integer qtdDiasCalculoStopWin)
  {
    this.qtdDiasCalculoStopWin = qtdDiasCalculoStopWin;
  }
  
  public Usuario getUsuario()
  {
    return this.usuario;
  }
  
  public void setUsuario(Usuario usuario)
  {
    this.usuario = usuario;
  }
  
  @Transient
  public void configDefault()
  {
    if (this.qtdDiasApresentarCotacoes == null) {
      this.qtdDiasApresentarCotacoes = Integer.valueOf(25);
    }
    if (this.qtdDiasCalculoStopLoss == null) {
      this.qtdDiasCalculoStopLoss = Integer.valueOf(11);
    }
    if (this.qtdDiasCalculoStopWin == null) {
      this.qtdDiasCalculoStopWin = Integer.valueOf(20);
    }
    if (this.riscoStopLoss == null) {
      this.riscoStopLoss = Integer.valueOf(1);
    }
    if (this.riscoStopWin == null) {
      this.riscoStopWin = Integer.valueOf(1);
    }
  }
  
  public Integer getRiscoStopWin()
  {
    return this.riscoStopWin;
  }
  
  public void setRiscoStopWin(Integer riscoStopWin)
  {
    this.riscoStopWin = riscoStopWin;
  }
  
  public String toString()
  {
    return 
    
      "ConfiguracaoAnaliseCotacoes [id=" + this.id + ", qtdDiasApresentarCotacoes=" + this.qtdDiasApresentarCotacoes + ", riscoStopLoss=" + this.riscoStopLoss + ", riscoStopWin=" + this.riscoStopWin + ", qtdDiasCalculoStopLoss=" + this.qtdDiasCalculoStopLoss + ", qtdDiasCalculoStopWin=" + this.qtdDiasCalculoStopWin + ", usuario=" + this.usuario + "]";
  }
}
