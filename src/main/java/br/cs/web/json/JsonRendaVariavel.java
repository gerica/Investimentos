package br.cs.web.json;

import br.cs.component.util.FormatadorUtil;
import br.cs.entity.OperacaoEntrada;

public class JsonRendaVariavel {
	private int id;
	private String empresa;
	private String papel;
	private String data;
	private String tipoOperacao;
	private String precoUnitario;
	private String despesa;
	private String quantidade;
	private String stopLoss;
	private String porcentagemEntrada;
	private boolean avaliacaoEntradaRealizada;

	public JsonRendaVariavel(OperacaoEntrada o) {
		this.id = o.getId().intValue();
		this.empresa = o.getEmpresa().getNome();
		this.papel = o.getPapel().getNome();
		this.data = FormatadorUtil.formatarData(o.getData());
		this.tipoOperacao = o.getTipoOperacao();
		this.precoUnitario = FormatadorUtil.formatarMoeda(o.getPrecoUnitario());
		this.despesa = FormatadorUtil.formatarMoeda(o.getDespesa());
		this.quantidade = o.getQuantidade().toString();
		this.stopLoss = FormatadorUtil.formatarMoeda(o.getStopLoss());
		if (o.getAvaliacaoEntrada() != null) {
			this.porcentagemEntrada = FormatadorUtil.formatarMoeda(Double.valueOf(o.getAvaliacaoEntrada().doubleValue() / 100.0D));
		}
		this.avaliacaoEntradaRealizada = o.isAvaliacaoEntradaRealizada();
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getPapel() {
		return this.papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTipoOperacao() {
		return this.tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getPrecoUnitario() {
		return this.precoUnitario;
	}

	public void setPrecoUnitario(String precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public String getDespesa() {
		return this.despesa;
	}

	public void setDespesa(String despesa) {
		this.despesa = despesa;
	}

	public String getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getStopLoss() {
		return this.stopLoss;
	}

	public void setStopLoss(String stopLoss) {
		this.stopLoss = stopLoss;
	}

	public String getPorcentagemEntrada() {
		return this.porcentagemEntrada;
	}

	public void setPorcentagemEntrada(String porcentagemEntrada) {
		this.porcentagemEntrada = porcentagemEntrada;
	}

	public boolean isAvaliacaoEntradaRealizada() {
		return this.avaliacaoEntradaRealizada;
	}

	public void setAvaliacaoEntradaRealizada(boolean avaliacaoEntradaRealizada) {
		this.avaliacaoEntradaRealizada = avaliacaoEntradaRealizada;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
