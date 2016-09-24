package br.cs.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.cs.component.util.DataUtil;
import br.cs.component.util.FormatadorUtil;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.service.dtos.FundamentosDTO;

@Service
public class ParseHtmlSiteService {
	private static final Logger logger = LoggerFactory.getLogger(ParseHtmlSiteService.class);
	FundamentalistaEnum fundamentalistaEnum;

	public FundamentosDTO lerSiteFundamentalista(String papel) throws InvestimentoBusinessException {
		logger.info("papel: " + papel);
		if ((papel == null) || ("".equals(papel))) {
			throw new InvestimentoBusinessException("O nome do papel � obrigado.");
		}
		String url = "http://www.fundamentus.com.br/detalhes.php?papel=" + papel;

		FundamentosDTO dto = new FundamentosDTO();
		try {
			Document doc = Jsoup.connect(url).userAgent("Mozilla").get();

			Elements tables = doc.getElementsByTag("table");
			Iterator iterator;
			for (Iterator localIterator1 = tables.iterator(); localIterator1.hasNext(); iterator.hasNext()) {
				Element table = (Element) localIterator1.next();
				Elements tds = table.getElementsByTag("td");

				iterator = tds.iterator();
				while (iterator.hasNext()) {
					Element td = (Element) iterator.next();
					String tdText = td.text();
					if (tdText.startsWith("?")) {
						tdText = tdText.replace("?", "");
					}
					this.fundamentalistaEnum = FundamentalistaEnum.get(tdText);
					Element conteudo = null;
					switch (this.fundamentalistaEnum) {
					case ATIVOCIRCULANTE:
						conteudo = (Element) iterator.next();
						dto.setPapelNome(conteudo.text());
						break;
					case COTACAO:
						conteudo = (Element) iterator.next();
						dto.setTipo(conteudo.text());
						break;
					case CRESREC5A:
						dto.setEmpresa(conteudo.text());
						break;
					case DATAULTCOT:
						conteudo = (Element) iterator.next();
						dto.setSetor(conteudo.text());
						break;
					case DIA:
						conteudo = (Element) iterator.next();
						dto.setSubsetor(conteudo.text());
						break;
					case DISPONIBILIDADES:
						conteudo = (Element) iterator.next();
						dto.setCotacao(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case DIVBRPATRIM:
						conteudo = (Element) iterator.next();
						dto.setDataUltimaCotacao(DataUtil.parseToDate(conteudo.text()));
						break;
					case DIVBRUTA:
						conteudo = (Element) iterator.next();
						dto.setMin52Semanas(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case DIVLIQUIDA:
						conteudo = (Element) iterator.next();
						dto.setMax52Semanas(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case DIVYIELD:
						conteudo = (Element) iterator.next();
						dto.setVolumeNegociacao2Meses(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case EBIT:
						conteudo = (Element) iterator.next();
						dto.setValorMercado(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case EBITATIVO:
						conteudo = (Element) iterator.next();
						dto.setUltimoBalanco(DataUtil.parseToDate(conteudo.text()));
						break;
					case EMPRESA:
						conteudo = (Element) iterator.next();
						dto.setValorFirma(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case EVEBIT:
						conteudo = (Element) iterator.next();
						dto.setNumeroAcoes(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case GIROATIVOS:
						conteudo = (Element) iterator.next();
						dto.setDia(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case LIQUIDEZCORR:
						conteudo = (Element) iterator.next();
						dto.setP_l(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case LPA:
						conteudo = (Element) iterator.next();
						dto.setLucroPorAcao(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case LUCROLIQUIDO:
						conteudo = (Element) iterator.next();
						dto.setMes(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case MARGBRUTA:
						conteudo = (Element) iterator.next();
						dto.setP_vp(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case MARGEBIT:
						conteudo = (Element) iterator.next();
						dto.setValorPatrimonialPorAcao(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case MARGLIQUIDA:
						conteudo = (Element) iterator.next();
						dto.setTrintaDias(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case MAX52SEM:
						conteudo = (Element) iterator.next();
						dto.setP_ebit(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case MES:
						conteudo = (Element) iterator.next();
						dto.setMargemBruto(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case MIN52SEM:
						conteudo = (Element) iterator.next();
						dto.setDozeMeses(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case NROACOES:
						conteudo = (Element) iterator.next();
						dto.setPsr(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case NULL:
						conteudo = (Element) iterator.next();
						dto.setMargemEbit(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case PAPEL:
						conteudo = (Element) iterator.next();
						dto.setOscilacao2015(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case PATIVCIRCLIQ:
						conteudo = (Element) iterator.next();
						dto.setP_ativos(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case PATIVOS:
						conteudo = (Element) iterator.next();
						dto.setMargemLiquida(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case PATRIMLIQ:
						conteudo = (Element) iterator.next();
						dto.setOscilacao2014(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case PCAPGIRO:
						conteudo = (Element) iterator.next();
						dto.setP_capitacaoGiro(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case PEBIT:
						conteudo = (Element) iterator.next();
						dto.setEbit_ativo(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case PL:
						conteudo = (Element) iterator.next();
						dto.setOscilacao2013(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case PSR:
						conteudo = (Element) iterator.next();
						dto.setP_ativoCirculanteLiquido(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text()))
								.doubleValue() / 100.0D));
						break;
					case PVP:
						conteudo = (Element) iterator.next();
						dto.setRoic(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case RECEITALIQUIDA:
						conteudo = (Element) iterator.next();
						dto.setOscilacao2012(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case ROE:
						conteudo = (Element) iterator.next();
						dto.setDividendoYield(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case ROIC:
						conteudo = (Element) iterator.next();
						dto.setRoe(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case SETOR:
						conteudo = (Element) iterator.next();
						dto.setOscilacao2011(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case SUBSETOR:
						conteudo = (Element) iterator.next();
						dto.setEv_ebit(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case TIPO:
						conteudo = (Element) iterator.next();
						dto.setLiquidesCorrente(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case ULTBALANCOPROCESSADO:
						conteudo = (Element) iterator.next();
						dto.setOscilacao2010(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())).doubleValue() / 100.0D));
						break;
					case VALORDAFIRMA:
						conteudo = (Element) iterator.next();
						dto.setGiroAtivos(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case VALORDEMERCADO:
						conteudo = (Element) iterator.next();
						dto.setDividaBruta_Patrimonio(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case VOLMED2M:
						conteudo = (Element) iterator.next();
						dto.setCrescrimentoReceitaLiquidaCincoAnos(Double.valueOf(Double.valueOf(FormatadorUtil.formatarParaNumero(conteudo.text()))
								.doubleValue() / 100.0D));
						break;
					case VPA:
						conteudo = (Element) iterator.next();
						dto.setAtivo(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case _12MESES:
						conteudo = (Element) iterator.next();
						dto.setDividaBruta(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case _2010:
						conteudo = (Element) iterator.next();
						dto.setDisponibilidades(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case _2011:
						conteudo = (Element) iterator.next();
						dto.setDividaLiquida(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case _2012:
						conteudo = (Element) iterator.next();
						dto.setAtivoCirculante(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case _2013:
						conteudo = (Element) iterator.next();
						dto.setPatrimonioLiquido(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						break;
					case _2014:
						conteudo = (Element) iterator.next();
						if (dto.getReceitaLiquidaDozeMeses() == null) {
							dto.setReceitaLiquidaDozeMeses(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						} else {
							dto.setReceitaLiquidaTresMeses(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						}
						break;
					case _2015:
						conteudo = (Element) iterator.next();
						if (dto.getEbitDozeMeses() == null) {
							dto.setEbitDozeMeses(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						} else {
							dto.setEbitTresMeses(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						}
						break;
					case _30DIAS:
						conteudo = (Element) iterator.next();
						if (dto.getLucroLiquidoDozeMeses() == null) {
							dto.setLucroLiquidoDozeMeses(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						} else {
							dto.setLucroLiquidoTresMeses(new BigDecimal(FormatadorUtil.formatarParaNumero(conteudo.text())));
						}
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}

	public static void main(String[] args) throws InvestimentoBusinessException {
		ParseHtmlSiteService service = new ParseHtmlSiteService();
		FundamentosDTO dto = service.lerSiteFundamentalista("HGTX3");
		System.out.println(dto);
	}
}
