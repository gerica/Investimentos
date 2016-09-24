package br.cs.entity;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;

public class Role
  implements GrantedAuthority
{
  private static final long serialVersionUID = 1L;
  private String name;
  private List<Privilege> privileges;
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getAuthority()
  {
    return this.name;
  }
  
  public List<Privilege> getPrivileges()
  {
    return this.privileges;
  }
  
  public void setPrivileges(List<Privilege> privileges)
  {
    this.privileges = privileges;
  }
  
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Role [name=");
    builder.append(this.name);
    builder.append(", privileges=");
    builder.append(this.privileges);
    builder.append("]");
    return builder.toString();
  }
}
