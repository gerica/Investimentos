package br.cs.web.json.enums;

public enum JsonReponseEnum
{
  RESPONSE_SUCCESS("sucesso"),  RESPONSE_FAIL("falha"),  MSG_SUCCESS("Opera��o realizada com sucesso.");
  
  private final String tipo;
  
  private JsonReponseEnum(String tipo)
  {
    this.tipo = tipo;
  }
  
  public String getTipo()
  {
    return this.tipo;
  }
}
