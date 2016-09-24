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
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="nota")
public class Nota
{
  private static final int SIZE_DESCRICAO = 45;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Integer id;
  @Column(name="data")
  @NotNull(message="A data n�o pode ser em branco!")
  @JsonSerialize(using=CustomDateSerializer.class)
  @JsonDeserialize(using=CustomDateDeserializer.class)
  private Date data;
  @Column(name="titulo")
  @NotEmpty(message="O valor do t�tulo n�o pode ser em branco!")
  private String titulo;
  @Column(name="sumario")
  private String sumario;
  @Column(name="descricao")
  @NotEmpty(message="O valor da descri��o n�o pode ser em branco!")
  private String descricao;
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
  
  public Date getData()
  {
    return this.data;
  }
  
  public void setData(Date data)
  {
    this.data = data;
  }
  
  public String getTitulo()
  {
    return this.titulo;
  }
  
  public void setTitulo(String titulo)
  {
    this.titulo = titulo;
  }
  
  public String getSumario()
  {
    return this.sumario;
  }
  
  public void setSumario(String sumario)
  {
    this.sumario = sumario;
  }
  
  public String getDescricao()
  {
    return this.descricao;
  }
  
  public void setDescricao(String descricao)
  {
    this.descricao = descricao;
  }
  
  public Boolean getAtivo()
  {
    return this.ativo;
  }
  
  public void setAtivo(Boolean ativo)
  {
    this.ativo = ativo;
  }
  
  @Transactional
  public String getDescricaoCurta()
  {
    String valor = null;
    if (this.descricao == null)
    {
      valor = "";
    }
    else if (this.descricao.length() > 45)
    {
      valor = this.descricao.substring(0, 45);
      valor = valor + "...";
    }
    else
    {
      valor = this.descricao;
    }
    return valor;
  }
}
