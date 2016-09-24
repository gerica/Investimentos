package br.cs.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="usuario")
public class Usuario
  implements UserDetails
{
  private static final long serialVersionUID = -6495190382190264872L;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Integer id;
  @NotBlank(message="O nome n�o pode ser em branco!")
  @Size(min=3, max=30, message="O nome dever� ser 3 e no m�xima 30 caracteres!")
  @Column(name="nome")
  private String nome;
  @NotBlank(message="A senha n�o pode ser em branco!")
  @Size(min=3, max=30, message="O senha dever� ser 3 e no m�xima 30 caracteres!")
  @Column(name="senha")
  private String senha;
  @Transient
  private List<Role> authorities;
  @Transient
  private boolean accountNonExpired = true;
  @Transient
  private boolean accountNonLocked = true;
  @Transient
  private boolean credentialsNonExpired = true;
  @Transient
  private boolean enabled = true;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getNome()
  {
    return this.nome;
  }
  
  public void setNome(String nome)
  {
    this.nome = nome;
  }
  
  public String getSenha()
  {
    return this.senha;
  }
  
  public void setSenha(String senha)
  {
    this.senha = senha;
  }
  
  public boolean equals(Object obj)
  {
    return EqualsBuilder.reflectionEquals(this, obj, new String[] { "id", "nome" });
  }
  
  public int hashCode()
  {
    return HashCodeBuilder.reflectionHashCode(this, new String[] { "id", "nome" });
  }
  
  public String toString()
  {
    return "Usuario [id=" + this.id + ", nome=" + this.nome + "]";
  }
  
  public List<Role> getAuthorities()
  {
    return this.authorities;
  }
  
  public void setAuthorities(List<Role> authorities)
  {
    this.authorities = authorities;
  }
  
  public boolean isAccountNonExpired()
  {
    return this.accountNonExpired;
  }
  
  public void setAccountNonExpired(boolean accountNonExpired)
  {
    this.accountNonExpired = accountNonExpired;
  }
  
  public boolean isAccountNonLocked()
  {
    return this.accountNonLocked;
  }
  
  public void setAccountNonLocked(boolean accountNonLocked)
  {
    this.accountNonLocked = accountNonLocked;
  }
  
  public boolean isCredentialsNonExpired()
  {
    return this.credentialsNonExpired;
  }
  
  public void setCredentialsNonExpired(boolean credentialsNonExpired)
  {
    this.credentialsNonExpired = credentialsNonExpired;
  }
  
  public boolean isEnabled()
  {
    return this.enabled;
  }
  
  public void setEnabled(boolean enabled)
  {
    this.enabled = enabled;
  }
  
  public String getPassword()
  {
    return this.senha;
  }
  
  public String getUsername()
  {
    return this.nome;
  }
}
