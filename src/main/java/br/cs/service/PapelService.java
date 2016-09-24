package br.cs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.cs.component.util.DataUtil;
import br.cs.component.util.FormatadorUtil;
import br.cs.entity.ConfiguracaoAnaliseCotacoes;
import br.cs.entity.Cotacao;
import br.cs.entity.Empresa;
import br.cs.entity.Fundamento;
import br.cs.entity.OperacaoEntrada;
import br.cs.entity.Papel;
import br.cs.entity.pojt.CotacaoTendencia;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.repository.PapelRepository;
import br.cs.service.dtos.FundamentosDTO;
import br.cs.web.dtos.BalancoCarteiraDTO;
import br.cs.web.json.JsonCandle;
import br.cs.web.json.JsonChartCandlePapel;
import br.cs.web.json.JsonCotacao;
import br.cs.web.json.chart.JsonChartLinePapel;

@Service
public class PapelService {
	private static final Logger logger = LoggerFactory.getLogger(PapelService.class);
	@Autowired
	private PapelRepository papelRepository;
	@Autowired
	private CotacaoService cotacaoService;
	@Autowired
	private OperacaoEntradaService operacaoEntradaService;
	@Autowired
	private ConfiguracaoAnaliseCotacoesService analiseCotacoesService;
	@Autowired
	private ParseHtmlSiteService siteService;
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private FundamentoService fundamentoService;
	private Date hoje = new Date();

	public void salvar(Papel papel) throws InvestimentoBusinessException {
		logger.debug("salvar");
		this.validar(papel);
		this.papelRepository.save(papel);
	}

	public List<Papel> findAll() {
		return this.papelRepository.findAll();
	}

	public List<Papel> findAllByAtivo() {
		return this.papelRepository.findAllByAtivo(Boolean.valueOf(true));
	}

	public List<Papel> findAllByEmpresa(Empresa empresa) {
		return this.papelRepository.findAllByEmpresa(empresa);
	}

	public List<Papel> findAllByEmpresaAndAtivo(Empresa empresa) {
		return this.papelRepository.findAllByEmpresaAndAtivo(empresa, Boolean.valueOf(true));
	}

	public Papel findById(Integer idPapel) {
		return this.papelRepository.findById(idPapel);
	}

	public List<BalancoCarteiraDTO> findBalancoHoje() {
		List<OperacaoEntrada> entradas = this.operacaoEntradaService.findAllOperacaoAtiva();
		BalancoCarteiraDTO dto = null;
		ArrayList<BalancoCarteiraDTO> balancos = new ArrayList<BalancoCarteiraDTO>();
		for (OperacaoEntrada entrada : entradas) {
			Cotacao cotacao = this.cotacaoService.findUltimaCotacaoByPapel(entrada.getPapel());
			if (cotacao == null)
				continue;
			dto = new BalancoCarteiraDTO();
			dto.setPapel(entrada.getPapel().getNome());
			dto.setDataInvestimento(entrada.getData());
			dto.setDataUltimaCotacao(cotacao.getData());
			dto.setValorInvestimento(entrada.getPrecoUnitario());
			dto.setValorUltimaCotacao(cotacao.getFechamento());
			dto.setTotalInvestimento(this.calcularTotalInvestimento(entrada));
			dto.setPorcentagemLucroPrejuizo(this.calcularProcentagemLucroPrejuizo(entrada, cotacao));
			dto.setSaldoLucroPrejuizo(this.calcularSaldo(entrada, cotacao));
			dto.setLucroPrejuizo(this.calcularQtdLucroPrejuizo(entrada, cotacao));
			balancos.add(dto);
		}
		return balancos;
	}

	@Scheduled(cron = "0 0 20 * * ?")
	public void schedulerAtualizarCotacao() throws InvestimentoBusinessException {
		List<Papel> papeis = this.papelRepository.findAllByAtivo(Boolean.valueOf(true));
		for (Papel papel : papeis) {
			try {
				this.cotacaoService.atualizarBMF(papel);
				continue;
			} catch (InvestimentoBusinessException e) {
				logger.debug(e.getMessage());
				throw new InvestimentoBusinessException(e.getMessage());
			}
		}
	}

	public List<JsonChartLinePapel> getJsonCharLinePapel(Integer idPapel) {
		ConfiguracaoAnaliseCotacoes configuracao = this.analiseCotacoesService.findByUsuario();
		ArrayList<JsonChartLinePapel> result = new ArrayList<JsonChartLinePapel>();
		List<Papel> papeis = null;
		if (idPapel == null || idPapel == 0) {
			papeis = this.findAll();
		} else {
			papeis = new ArrayList<Papel>();
			papeis.add(this.findById(idPapel));
		}
		JsonChartLinePapel chartLinePapel = null;
		JsonCotacao jsonCotacao = null;
		CalcularMediaMovelExponecial calculo = null;
		for (Papel p : papeis) {
			calculo = new CalcularMediaMovelExponecial();
			chartLinePapel = new JsonChartLinePapel();
			chartLinePapel.setPapel(p.getNome());
			int size = p.getCotacoes().size();
			int count = 0;
			if (size <= 0)
				continue;
			List tendenciasStopLoss = this.cotacaoService.analizarAltaStopLoss(p);
			List tendenciasStopWin = this.cotacaoService.analizarAltaStopWin(p);
			for (Cotacao c : p.getCotacoes()) {
				jsonCotacao = new JsonCotacao();
				jsonCotacao.setId(c.getId());
				jsonCotacao.setData(FormatadorUtil.formatarData((Date) c.getData(), (String) "yyyy-MM-dd"));
				jsonCotacao.setFechamento(FormatadorUtil.formatarMoedaEN((Double) c.getFechamento()));
				jsonCotacao.setPapel(c.getPapel().getNome());
				if (size - count <= configuracao.getQtdDiasApresentarCotacoes()) {
					this.recuperarValorStopLoss(jsonCotacao, tendenciasStopLoss);
					this.recuperarValorStopWin(jsonCotacao, tendenciasStopWin);
					chartLinePapel.addCotacoes(jsonCotacao);
				}
				jsonCotacao.setMme(calculo.calcularMMeMaximoMinimo(c));
				++count;
			}
			result.add(chartLinePapel);
		}
		return result;
	}

	private void recuperarValorStopWin(JsonCotacao jsonCotacao, List<CotacaoTendencia> tendenciasStopWin) {
		for (CotacaoTendencia ct : tendenciasStopWin) {
			if (!jsonCotacao.getId().equals(ct.getId()))
				continue;
			jsonCotacao.setStopWin(FormatadorUtil.formatarMoedaEN((Double) ct.getWin()));
			break;
		}
	}

	private void recuperarValorStopLoss(JsonCotacao jsonCotacao, List<CotacaoTendencia> tendenciasStopLoss) {
		for (CotacaoTendencia ct : tendenciasStopLoss) {
			if (!jsonCotacao.getId().equals(ct.getId()))
				continue;
			jsonCotacao.setStopLoss(FormatadorUtil.formatarMoedaEN((Double) ct.getStop()));
			break;
		}
	}

	public List<JsonChartCandlePapel> getJsonCharCandlePapel(Integer idPapel) {
		ConfiguracaoAnaliseCotacoes configuracao = this.analiseCotacoesService.findByUsuario();
		ArrayList<JsonChartCandlePapel> result = new ArrayList<JsonChartCandlePapel>();
		List<Papel> papeis = null;
		if (idPapel == null || idPapel == 0) {
			papeis = this.findAll();
		} else {
			papeis = new ArrayList<Papel>();
			papeis.add(this.findById(idPapel));
		}
		JsonChartCandlePapel chart = null;
		JsonCandle candle = null;
		for (Papel p : papeis) {
			chart = new JsonChartCandlePapel();
			chart.setPapel(p.getNome());
			int size = p.getCotacoes().size();
			int count = 0;
			for (Cotacao c : p.getCotacoes()) {
				candle = new JsonCandle();
				if (size - count <= configuracao.getQtdDiasApresentarCotacoes()) {
					candle.setData(FormatadorUtil.formatarData((Date) c.getData(), (String) "yyyy-MM-dd"));
					candle.setMinimo(FormatadorUtil.formatarMoedaEN((Double) c.getMinima()));
					candle.setAbertura(FormatadorUtil.formatarMoedaEN((Double) c.getAbertura()));
					candle.setFechamento(FormatadorUtil.formatarMoedaEN((Double) c.getFechamento()));
					candle.setMaximo(FormatadorUtil.formatarMoedaEN((Double) c.getMaxima()));
					chart.addCandels(candle);
				}
				++count;
			}
			result.add(chart);
		}
		return result;
	}

	public Papel inativar(Integer idPapel) throws InvestimentoBusinessException {
		Papel papel = this.papelRepository.findById(idPapel);
		papel.setAtivo(Boolean.valueOf(false));
		this.salvar(papel);
		return papel;
	}

	private Double calcularSaldo(OperacaoEntrada entrada, Cotacao cotacao) {
		return (double) entrada.getQuantidade().intValue() * cotacao.getFechamento();
	}

	private Double calcularProcentagemLucroPrejuizo(OperacaoEntrada entrada, Cotacao cotacao) {
		Double valorEntrada = (double) entrada.getPrecoUnitario();
		Double valorAtual = cotacao.getFechamento();
		Double diferenca = valorAtual - valorEntrada;
		return diferenca * 100.0 / valorEntrada / 100.0;
	}

	private Double calcularQtdLucroPrejuizo(OperacaoEntrada entrada, Cotacao cotacao) {
		return cotacao.getFechamento() - entrada.getPrecoUnitario();
	}

	private Double calcularTotalInvestimento(OperacaoEntrada entrada) {
		return (double) entrada.getQuantidade().intValue() * entrada.getPrecoUnitario();
	}

	private void validar(Papel papel) throws InvestimentoBusinessException {
		logger.debug("validar carteira");
		List list = this.papelRepository.findByNome(papel.getNome());
		if (!list.isEmpty()) {
			Papel objBanco;
			if (list.size() == 1 && (objBanco = (Papel) list.iterator().next()).getId().equals(papel.getId())) {
				return;
			}
			throw new InvestimentoBusinessException("Nome de Empresa j\u00e1 cadastrado");
		}
	}

	public Papel consolidar(Integer idPapel) throws InvestimentoBusinessException {
		logger.info("Consolidar " + idPapel);
		Papel papel = this.findById(idPapel);
		Empresa empresa = papel.getEmpresa();
		Fundamento fundamento = this.fundamentoService.findLastFundamento(papel);
		if (fundamento == null || fundamento.getDataUltimaCotacao() == null
				|| DataUtil.compararData((Date) this.hoje, (Date) fundamento.getDataUltimaCotacao()) > 0) {
			FundamentosDTO dto = this.siteService.lerSiteFundamentalista(papel.getNome());
			if (FormatadorUtil.verificarStringVazia((String) empresa.getSetor())
					|| FormatadorUtil.verificarStringVazia((String) empresa.getSubsetor())) {
				empresa.setSetor(dto.getSetor());
				empresa.setSubsetor(dto.getSubsetor());
				this.empresaService.salvar(empresa);
			}
			if (fundamento == null || DataUtil.compararData((Date) dto.getUltimoBalanco(), (Date) fundamento.getUltimoBalanco()) > 0) {
				fundamento = new Fundamento();
				fundamento.setPapel(papel);
			}
			this.fundamentoService.salvar(fundamento, dto);
		}
		return papel;
	}

	private class CalcularMediaMovelExponecial {
		private static final int QTD_DIAS = 14;
		private List<Double> mmeAnteriores;

		private CalcularMediaMovelExponecial() {
			this.mmeAnteriores = new ArrayList<Double>();
		}

		public String calcularMMeMaximoMinimo(final Cotacao cotacao) {
			Double mme = 0.0;
			final Double valor = cotacao.getFechamento();
			Double mmeAnterior = 0.0;
			if (!this.mmeAnteriores.isEmpty()) {
				mmeAnterior = this.mmeAnteriores.get(this.mmeAnteriores.size() - 1);
			}
			mme = (valor - mmeAnterior) * 0.13333333333333333 + mmeAnterior;
			this.mmeAnteriores.add(mme);
			return FormatadorUtil.formatarMoedaEN(mme);
		}
	}
}