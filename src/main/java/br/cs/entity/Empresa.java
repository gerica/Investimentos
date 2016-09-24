package br.cs.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="empresa")
public class Empresa
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Integer id;
  @NotBlank(message="O nome n�o pode ser em branco!")
  @Size(min=3, max=30, message="O nome dever� ser 3 e no m�xima 30 caracteres!")
  @Column(name="nome")
  private String nome;
  @OneToMany(fetch=FetchType.EAGER, mappedBy="empresa", cascade={javax.persistence.CascadeType.ALL})
  private Set<Papel> papeis;
  @Column(name="ativo")
  private Boolean ativo = Boolean.valueOf(true);
  @Column(name="setor")
  private String setor;
  @Column(name="subsetor")
  private String subsetor;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Set<Papel> getPapeis()
  {
    return this.papeis;
  }
  
  public void setPapeis(Set<Papel> papeis)
  {
    this.papeis = papeis;
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
    Empresa other = (Empresa)obj;
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
  
  public Boolean getAtivo()
  {
    return this.ativo;
  }
  
  public void setAtivo(Boolean ativo)
  {
    this.ativo = ativo;
  }
  
  public String getSetor()
  {
    return this.setor;
  }
  
  public void setSetor(String setor)
  {
    this.setor = setor;
  }
  
  public String getSubsetor()
  {
    return this.subsetor;
  }
  
  public void setSubsetor(String subsetor)
  {
    this.subsetor = subsetor;
  }
}
