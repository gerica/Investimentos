package br.cs.entity;

public class Privilege
{
  private String name;
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Privilege [name=");
    builder.append(this.name);
    builder.append("]");
    return this.name;
  }
}
