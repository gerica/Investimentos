package br.cs.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="papel")
public class Papel
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Integer id;
  @NotBlank(message="O nome n�o pode ser em branco!")
  @Size(min=3, max=30, message="O nome dever� ser 3 e no m�xima 30 caracteres!")
  @Column(name="nome")
  private String nome;
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="id_empresa", nullable=false)
  private Empresa empresa;
  @OneToMany(fetch=FetchType.EAGER, mappedBy="papel", cascade={javax.persistence.CascadeType.ALL})
  @OrderBy("data ASC")
  private Set<Cotacao> cotacoes;
  @OneToMany(fetch=FetchType.LAZY, mappedBy="papel", cascade={javax.persistence.CascadeType.ALL})
  @OrderBy("dataUltimaCotacao ASC")
  private Set<Fundamento> fundamentos;
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
  
  public int hashCode()
  {
    int prime = 31;
    int result = 1;
    result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
    result = 31 * result + (this.nome == null ? 0 : this.nome.hashCode());
    return result;
  }
  
  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Papel other = (Papel)obj;
    if (this.id == null)
    {
      if (other.id != null) {
        return false;
      }
    }
    else if (!this.id.equals(other.id)) {
      return false;
    }
    if (this.nome == null)
    {
      if (other.nome != null) {
        return false;
      }
    }
    else if (!this.nome.equals(other.nome)) {
      return false;
    }
    return true;
  }
  
  public String getNome()
  {
    return this.nome;
  }
  
  public void setNome(String nome)
  {
    this.nome = nome;
  }
  
  public Empresa getEmpresa()
  {
    return this.empresa;
  }
  
  public void setEmpresa(Empresa empresa)
  {
    this.empresa = empresa;
  }
  
  public Set<Cotacao> getCotacoes()
  {
    return this.cotacoes;
  }
  
  public void setCotacoes(Set<Cotacao> cotacoes)
  {
    this.cotacoes = cotacoes;
  }
  
  public Boolean getAtivo()
  {
    return this.ativo;
  }
  
  public void setAtivo(Boolean ativo)
  {
    this.ativo = ativo;
  }
  
  public Set<Fundamento> getFundamentos()
  {
    return this.fundamentos;
  }
  
  public void setFundamentos(Set<Fundamento> fundamentos)
  {
    this.fundamentos = fundamentos;
  }
}
