package br.cs.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.cs.component.util.DataUtil;
import br.cs.entity.Cotacao;
import br.cs.execao.InvestimentoBusinessException;

@Service
public class HTMLParser
{
  private static final Logger logger = LoggerFactory.getLogger(HTMLParser.class);
  
  public List<Cotacao> read(String papel)
    throws InvestimentoBusinessException
  {
    if ((papel == null) || ("".equals(papel))) {
      throw new InvestimentoBusinessException("O papel informado � nulo ou vazio.");
    }
    logger.info("Lendo url para obter cota��o do papel: " + papel);
    List<Cotacao> list = new ArrayList<Cotacao>();
    
    Cotacao cotacao = null;
    try
    {
      String url = "http://pregao-online.bmfbovespa.com.br/Noticias.aspx?idioma=pt-BR&Papel=" + papel.toUpperCase();
      
      Document doc = Jsoup.connect(url).get();
      
      Elements tableElements = doc.select("table");
      
      Elements tableRowElements = tableElements.select(":not(thead) tr");
      for (int i = 0; i < tableRowElements.size(); i++)
      {
        cotacao = new Cotacao();
        Element row = tableRowElements.get(i);
        Elements rowItems = row.select("td");
        if (rowItems.size() > 0)
        {
          cotacao.setData(DataUtil.parseToDate(rowItems.get(7).text(), "dd/MM/yy"));
          cotacao.setFechamento(Double.valueOf(Double.parseDouble(rowItems.get(2).text().replace(',', '.'))));
          cotacao.setAbertura(Double.valueOf(Double.parseDouble(rowItems.get(3).text().replace(',', '.'))));
          cotacao.setMaxima(Double.valueOf(Double.parseDouble(rowItems.get(4).text().replace(',', '.'))));
          cotacao.setMinima(Double.valueOf(Double.parseDouble(rowItems.get(5).text().replace(',', '.'))));
          
          System.out.println(cotacao);
          
          list.add(cotacao);
        }
      }
    }
    catch (IOException e)
    {
      throw new InvestimentoBusinessException(e.getMessage());
    }
    Document doc;
    return list;
  }
  
  public static void main(String[] args)
  {
    HTMLParser p = new HTMLParser();
    try
    {
      p.read("vale5");
    }
    catch (InvestimentoBusinessException e)
    {
      e.printStackTrace();
    }
  }
}
