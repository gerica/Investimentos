package br.cs.entity;

import br.cs.component.custom.CustomDateDeserializer;
import br.cs.component.custom.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="renda_fixa")
public class RendaFixa
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Integer id;
  @Column(name="data_entrada")
  @NotNull(message="A data da entrada n�o pode ser em branco!")
  @JsonSerialize(using=CustomDateSerializer.class)
  @JsonDeserialize(using=CustomDateDeserializer.class)
  private Date dataEntrada;
  @Column(name="data_saida")
  @JsonSerialize(using=CustomDateSerializer.class)
  @JsonDeserialize(using=CustomDateDeserializer.class)
  private Date dataSaida;
  @Column(name="resgate")
  private Double resgate;
  @Column(name="taxa")
  @NotNull(message="O valor da taxa n�o pode ser em branco!")
  private Double taxa;
  @Column(name="observacao")
  @NotEmpty(message="O valor da observa��o n�o pode ser em branco!")
  private String observacao;
  @Column(name="nome_banco")
  @NotEmpty(message="O banco n�o pode ser em branco!")
  private String nomeBanco;
  @Column(name="investimento")
  @NotNull(message="O valor do investimento n�o pode ser em branco!")
  private Double investimento;
  @Column(name="ativo")
  private Boolean ativo = Boolean.valueOf(true);
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Date getDataEntrada()
  {
    return this.dataEntrada;
  }
  
  public void setDataEntrada(Date dataEntrada)
  {
    this.dataEntrada = dataEntrada;
  }
  
  public Date getDataSaida()
  {
    return this.dataSaida;
  }
  
  public void setDataSaida(Date dataSaida)
  {
    this.dataSaida = dataSaida;
  }
  
  public Double getResgate()
  {
    return this.resgate;
  }
  
  public void setResgate(Double resgate)
  {
    this.resgate = resgate;
  }
  
  public Double getTaxa()
  {
    return this.taxa;
  }
  
  public void setTaxa(Double taxa)
  {
    this.taxa = taxa;
  }
  
  public String getObservacao()
  {
    return this.observacao;
  }
  
  public void setObservacao(String observacao)
  {
    this.observacao = observacao;
  }
  
  public String getNomeBanco()
  {
    return this.nomeBanco;
  }
  
  public void setNomeBanco(String nomeBanco)
  {
    this.nomeBanco = nomeBanco;
  }
  
  public Double getInvestimento()
  {
    return this.investimento;
  }
  
  public void setInvestimento(Double investimento)
  {
    this.investimento = investimento;
  }
  
  public Boolean getAtivo()
  {
    return this.ativo;
  }
  
  public void setAtivo(Boolean ativo)
  {
    this.ativo = ativo;
  }
}
