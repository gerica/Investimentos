package br.cs.web.json;

import br.cs.component.util.FormatadorUtil;
import br.cs.entity.Cotacao;

public class JsonCotacao {
	private Integer id;
	private String data;
	private String abertura;
	private String maxima;
	private String minima;
	private String fechamento;
	private String papel;
	private String mme;
	private String stopLoss;
	private String stopWin;

	public JsonCotacao(Cotacao cotacao) {
		this.id = cotacao.getId();
		this.data = FormatadorUtil.formatarData(cotacao.getData());
		this.abertura = ("R$ " + FormatadorUtil.formatarMoeda(cotacao.getAbertura()));
		this.maxima = ("R$ " + FormatadorUtil.formatarMoeda(cotacao.getMaxima()));
		this.minima = ("R$ " + FormatadorUtil.formatarMoeda(cotacao.getMinima()));
		this.fechamento = ("R$ " + FormatadorUtil.formatarMoeda(cotacao.getFechamento()));
		this.papel = cotacao.getPapel().getNome();
	}

	public JsonCotacao() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAbertura() {
		return this.abertura;
	}

	public void setAbertura(String abertura) {
		this.abertura = abertura;
	}

	public String getMaxima() {
		return this.maxima;
	}

	public void setMaxima(String maxima) {
		this.maxima = maxima;
	}

	public String getMinima() {
		return this.minima;
	}

	public void setMinima(String minima) {
		this.minima = minima;
	}

	public String getFechamento() {
		return this.fechamento;
	}

	public void setFechamento(String fechamento) {
		this.fechamento = fechamento;
	}

	public String getPapel() {
		return this.papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public String getMme() {
		return this.mme;
	}

	public void setMme(String mme) {
		this.mme = mme;
	}

	public String getStopLoss() {
		return this.stopLoss;
	}

	public void setStopLoss(String stopLoss) {
		this.stopLoss = stopLoss;
	}

	public String getStopWin() {
		return this.stopWin;
	}

	public void setStopWin(String stopWin) {
		this.stopWin = stopWin;
	}
}
