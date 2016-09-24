package br.cs.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cs.component.util.DataUtil;
import br.cs.entity.ConfiguracaoAnaliseCotacoes;
import br.cs.entity.Cotacao;
import br.cs.entity.Papel;
import br.cs.entity.pojt.CotacaoTendencia;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.repository.CotacaoRepository;

@Service
public class CotacaoService {
	private static final Logger logger = LoggerFactory.getLogger(CotacaoService.class);
	@Autowired
	private CotacaoRepository cotacaoRepository;
	@Autowired
	private CotacaoBovespaService cotacaoBovespaService;
	@Autowired
	private ReadXMLFileDomService readXMLFileDomService;
	@Autowired
	private HTMLParser htmlParser;
	@Autowired
	private PapelService papelService;
	@Autowired
	private ConfiguracaoAnaliseCotacoesService analiseCotacoesService;

	/* Error */
	public void cadastrarCotacao(org.springframework.web.multipart.MultipartFile file, Cotacao cotacaoTela) throws InvestimentoBusinessException {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
		try {
			try {
				br = new BufferedReader(new InputStreamReader(file.getInputStream()));
				int linhaAtual = 0;
				while ((line = br.readLine()) != null) {
					if (linhaAtual == 0) {
						++linhaAtual;
						continue;
					}
					Cotacao cotacao = new Cotacao();
					++linhaAtual;
					try {
						String[] values = line.split(cvsSplitBy);
						cotacao.setData(DataUtil.parseToDate((String) values[0]));
						cotacao.setAbertura(Double.valueOf(values[4].replace(",", ".")));
						cotacao.setMaxima(Double.valueOf(values[7].replace(",", ".")));
						cotacao.setMinima(Double.valueOf(values[5].replace(",", ".")));
						cotacao.setFechamento(Double.valueOf(values[2].replace(",", ".")));
						cotacao.setPapel(cotacaoTela.getPapel());
					} catch (Exception e) {
						logger.debug("Erro ao criar objeto de cota\u00e7\u00e3o");
						e.printStackTrace();
						throw new InvestimentoBusinessException(e.getMessage());
					}
					this.salvarCotacao(cotacao);
				}
			} catch (FileNotFoundException e) {
				throw new InvestimentoBusinessException(e.getMessage());
			} catch (IOException e) {
				throw new InvestimentoBusinessException(e.getMessage());
			}
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new InvestimentoBusinessException(e.getMessage());
				}
			}
		}
	}

	private void salvarCotacao(Cotacao cotacao) throws InvestimentoBusinessException {
		logger.info("salvar cotacao");
		if (cotacao.getData() != null) {
			List<Cotacao> cotacoes = this.cotacaoRepository.findByDataAndPapel(cotacao.getData(), cotacao.getPapel());
			if (cotacoes.size() >= 1) {
				throw new InvestimentoBusinessException("Existe duas ou mais cota��es cadastada para esse dia: ");
			}
			this.cotacaoRepository.save(cotacao);
			logger.debug("Cota��o j� cadastrada para essa data: " + cotacao.getData());
		}
		logger.debug("Cota��o esta sem data");
	}

	public List<Cotacao> findAllByPapelOrderByDataDesc(Papel papel) {
		return this.cotacaoRepository.findAllByPapelOrderByDataDesc(papel);
	}

	public List<CotacaoTendencia> analizarAltaStopLoss(Papel papel) {
		ConfiguracaoAnaliseCotacoes configuracao = this.analiseCotacoesService.findByUsuario();
		List<CotacaoTendencia> tendencias = new ArrayList<CotacaoTendencia>();
		List<Cotacao> cotacoes = this.cotacaoRepository.findAllByPapelOrderByDataAsc(papel);
		if (!cotacoes.isEmpty()) {
			LinkedList<Cotacao> cotacaoTemp = new LinkedList();

			int indice = 0;
			CalcularTendenciaAlta alta = new CalcularTendenciaAlta(configuracao.getQtdDiasCalculoStopLoss(), configuracao.getRiscoStopLoss());
			for (Cotacao cotacao : cotacoes) {
				CotacaoTendencia cotacaoTendencia = new CotacaoTendencia();
				cotacaoTemp.add(cotacao);
				tendencias.add(cotacaoTendencia);
				indice++;
				cotacaoTendencia.setId(cotacao.getId());
				cotacaoTendencia.setData(cotacao.getData());
				cotacaoTendencia.setRuptura(alta.calularRupturaBaixa(cotacaoTemp, indice));
				cotacaoTendencia.setSoma(alta.calcularSoma(tendencias, indice));
				cotacaoTendencia.setRompeu(Boolean.valueOf(alta.verificarRuptura(cotacaoTemp, indice)));
				cotacaoTendencia.setNumeroRupturas(alta.calcularNumeroRompmento(tendencias, indice));
				cotacaoTendencia.setMediaRupturas(alta.calcularMediaRupturas(cotacaoTendencia));
				cotacaoTendencia.setStop(alta.calcularStopLoss(cotacaoTendencia, cotacao));
				cotacaoTendencia.setValorProtegido(alta.calcularValorProtegidoLoss(tendencias, indice));
			}
			alta.ordernarByDataDesc(tendencias);
			tendencias = alta.reduzierQuantidadeCotacao(tendencias, configuracao.getQtdDiasApresentarCotacoes().intValue());
		}
		return tendencias;
	}

	public List<CotacaoTendencia> analizarAltaStopWin(Papel papel) {
		ConfiguracaoAnaliseCotacoes configuracao = this.analiseCotacoesService.findByUsuario();
		List<CotacaoTendencia> tendencias = new ArrayList<CotacaoTendencia>();
		List<Cotacao> cotacoes = this.cotacaoRepository.findAllByPapelOrderByDataAsc(papel);
		if (!cotacoes.isEmpty()) {
			LinkedList<Cotacao> cotacaoTemp = new LinkedList();

			int indice = 0;
			CalcularTendenciaAlta alta = new CalcularTendenciaAlta(configuracao.getQtdDiasCalculoStopWin(), configuracao.getRiscoStopWin());
			for (Cotacao cotacao : cotacoes) {
				CotacaoTendencia cotacaoTendencia = new CotacaoTendencia();
				cotacaoTemp.add(cotacao);
				tendencias.add(cotacaoTendencia);
				indice++;
				cotacaoTendencia.setId(cotacao.getId());
				cotacaoTendencia.setData(cotacao.getData());
				cotacaoTendencia.setRuptura(alta.calularRupturaBaixa(cotacaoTemp, indice));
				cotacaoTendencia.setSoma(alta.calcularSoma(tendencias, indice));
				cotacaoTendencia.setRompeu(Boolean.valueOf(alta.verificarRuptura(cotacaoTemp, indice)));
				cotacaoTendencia.setNumeroRupturas(alta.calcularNumeroRompmento(tendencias, indice));
				cotacaoTendencia.setMediaRupturas(alta.calcularMediaRupturas(cotacaoTendencia));
				cotacaoTendencia.setWin(alta.calcularStopWin(cotacaoTendencia, cotacao));
				cotacaoTendencia.setValorProtegido(alta.calcularValorProtegidoWin(tendencias, indice));
			}
			alta.ordernarByDataDesc(tendencias);
			tendencias = alta.reduzierQuantidadeCotacao(tendencias, configuracao.getQtdDiasApresentarCotacoes().intValue());
		}
		return tendencias;
	}

	public CotacaoTendencia getUltimoValorTendencia(Papel papel) {
		List<CotacaoTendencia> list = analizarAltaStopLoss(papel);
		if (!list.isEmpty()) {
			return (CotacaoTendencia) list.iterator().next();
		}
		return null;
	}

	private class CalcularTendenciaAlta {
		private Integer diasCalculo;
		private Integer risco;

		public CalcularTendenciaAlta(Integer dias, Integer risco) {
			this.diasCalculo = dias;
			this.risco = risco;
		}

		private Double calularRupturaBaixa(LinkedList<Cotacao> cotacaos, int indice) {
			if (cotacaos.size() > 1) {
				Cotacao atual = (Cotacao) cotacaos.get(indice - 1);
				Cotacao anterior = (Cotacao) cotacaos.get(indice - 2);
				if (anterior.getMinima().doubleValue() > atual.getMinima().doubleValue()) {
					return Double.valueOf(anterior.getMinima().doubleValue() - atual.getMinima().doubleValue());
				}
			}
			return Double.valueOf(0.0D);
		}

		public List<CotacaoTendencia> reduzierQuantidadeCotacao(List<CotacaoTendencia> tendencias, int qtd) {
			List<CotacaoTendencia> result = new ArrayList<CotacaoTendencia>();
			for (int i = 0; i < qtd; i++) {
				if (tendencias.size() > i) {
					result.add((CotacaoTendencia) tendencias.get(i));
				}
			}
			return result;
		}

		private Double calcularSoma(List<CotacaoTendencia> tendencias, int indice) {
			double soma = 0.0D;
			if (tendencias.size() >= this.diasCalculo.intValue()) {
				for (int i = indice - 1; i >= indice - this.diasCalculo.intValue(); i--) {
					soma += ((CotacaoTendencia) tendencias.get(i)).getRuptura().doubleValue();
				}
			}
			return Double.valueOf(soma);
		}

		private boolean verificarRuptura(List<Cotacao> cotacaos, int indice) {
			if (cotacaos.size() > 1) {
				Cotacao atual = (Cotacao) cotacaos.get(indice - 1);
				Cotacao anterior = (Cotacao) cotacaos.get(indice - 2);
				if (atual.getMinima().doubleValue() > anterior.getMinima().doubleValue()) {
					return true;
				}
			}
			return false;
		}

		private Integer calcularNumeroRompmento(List<CotacaoTendencia> tendencias, int indice) {
			Integer soma = Integer.valueOf(0);
			if (tendencias.size() >= this.diasCalculo.intValue()) {
				for (int i = indice - 1; i >= indice - this.diasCalculo.intValue(); i--) {
					if (((CotacaoTendencia) tendencias.get(i)).getRompeu().booleanValue()) {
						soma = Integer.valueOf(soma.intValue() + 1);
					}
				}
			}
			return soma;
		}

		private Double calcularMediaRupturas(CotacaoTendencia cotacaoTendencia) {
			if ((cotacaoTendencia.getSoma().doubleValue() > 0.0D) && (cotacaoTendencia.getNumeroRupturas().intValue() > 0)) {
				return Double.valueOf(cotacaoTendencia.getSoma().doubleValue() / cotacaoTendencia.getNumeroRupturas().intValue());
			}
			return Double.valueOf(0.0D);
		}

		private Double calcularStopLoss(CotacaoTendencia cotacaoTendencia, Cotacao cotacao) {
			if (cotacaoTendencia.getMediaRupturas().doubleValue() > 0.0D) {
				return Double.valueOf(cotacao.getMinima().doubleValue() - cotacaoTendencia.getMediaRupturas().doubleValue() * this.risco.intValue());
			}
			return Double.valueOf(0.0D);
		}

		private Double calcularStopWin(CotacaoTendencia cotacaoTendencia, Cotacao cotacao) {
			if (cotacaoTendencia.getMediaRupturas().doubleValue() > 0.0D) {
				return Double.valueOf(cotacao.getMaxima().doubleValue() + cotacaoTendencia.getMediaRupturas().doubleValue() * this.risco.intValue());
			}
			return Double.valueOf(0.0D);
		}

		private Double calcularValorProtegidoLoss(List<CotacaoTendencia> tendencias, int indice) {
			if (tendencias.size() >= 3) {
				int i = indice - 1;
				if (i >= indice - 3) {
					CotacaoTendencia terceiro = (CotacaoTendencia) tendencias.get(i);
					CotacaoTendencia segundo = (CotacaoTendencia) tendencias.get(i - 1);
					CotacaoTendencia primeiro = (CotacaoTendencia) tendencias.get(i - 2);
					if ((primeiro.getStop().doubleValue() > 0.0D) && (segundo.getStop().doubleValue() > 0.0D)
							&& (terceiro.getStop().doubleValue() > 0.0D)) {
						Double initMax = Double.valueOf(Math.max(primeiro.getStop().doubleValue(), segundo.getStop().doubleValue()));
						return Double.valueOf(Math.max(initMax.doubleValue(), terceiro.getStop().doubleValue()));
					}
				}
			}
			return Double.valueOf(0.0D);
		}

		private Double calcularValorProtegidoWin(List<CotacaoTendencia> tendencias, int indice) {
			if (tendencias.size() >= 3) {
				int i = indice - 1;
				if (i >= indice - 3) {
					CotacaoTendencia terceiro = (CotacaoTendencia) tendencias.get(i);
					CotacaoTendencia segundo = (CotacaoTendencia) tendencias.get(i - 1);
					CotacaoTendencia primeiro = (CotacaoTendencia) tendencias.get(i - 2);
					if ((primeiro.getWin().doubleValue() > 0.0D) && (segundo.getWin().doubleValue() > 0.0D)
							&& (terceiro.getWin().doubleValue() > 0.0D)) {
						Double initMax = Double.valueOf(Math.max(primeiro.getWin().doubleValue(), segundo.getWin().doubleValue()));
						return Double.valueOf(Math.max(initMax.doubleValue(), terceiro.getWin().doubleValue()));
					}
				}
			}
			return Double.valueOf(0.0D);
		}

		@SuppressWarnings("unchecked")
		public void ordernarByDataDesc(List<CotacaoTendencia> tendencias) {
//			Collections.sort(tendencias, new Comparator() {
//				public int compare(CotacaoTendencia o1, CotacaoTendencia o2) {
//					return o2.getData().compareTo(o1.getData());
//				}
//			});
		}
	}

	public void salvar(Cotacao cotacao) throws InvestimentoBusinessException {
		logger.debug("salvar");
		validar(cotacao);
		this.cotacaoRepository.save(cotacao);
	}

	private void validar(Cotacao cotacao) throws InvestimentoBusinessException {
		logger.debug("validar carteira");

		List<Cotacao> cotacoes = this.cotacaoRepository.findByDataAndPapel(cotacao.getData(), cotacao.getPapel());
		if (cotacoes.size() > 1) {
			throw new InvestimentoBusinessException("Existe duas ou mais cota��es cadastada para esse dia: ");
		}
		if (cotacoes.size() == 1) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			throw new InvestimentoBusinessException("Cotacao j� cadastrada para essa data: " + dateFormat.format(cotacao.getData()));
		}
	}

	public Cotacao findByDataAndPapel(Date data, Papel papel) throws InvestimentoBusinessException {
		List<Cotacao> cotacoes = this.cotacaoRepository.findByDataAndPapel(data, papel);
		if (cotacoes.size() > 1) {
			throw new InvestimentoBusinessException("Existe duas ou mais cota��es cadastada para esse dia: ");
		}
		if (cotacoes.size() == 1) {
			return (Cotacao) cotacoes.get(0);
		}
		return null;
	}

	public void atualizarBMF(Cotacao cotacao) throws InvestimentoBusinessException {
		Papel papel = this.papelService.findById(cotacao.getPapel().getId());
		atualizarBMF(papel);
	}

	public void atualizarBMF(Papel papel) throws InvestimentoBusinessException {
		List<Cotacao> cotacoes = this.htmlParser.read(papel.getNome());
		if (!cotacoes.isEmpty()) {
			if (!cotacoes.isEmpty()) {
				for (Cotacao c : cotacoes) {
					c.setPapel(papel);
					salvar(c);
				}
			}
		}
	}

	public Cotacao findUltimaCotacaoByPapel(Papel papel) {
		return this.cotacaoRepository.findUltimaCotacaoByPapel(papel, papel);
	}
}
