package br.cs.web.json;

import br.cs.entity.Empresa;
import br.cs.entity.FeedMessage;
import br.cs.entity.Fundamento;
import br.cs.entity.Nota;
import br.cs.entity.OperacaoEntrada;
import br.cs.entity.Papel;
import br.cs.entity.RendaFixa;
import br.cs.entity.SiteFeed;
import br.cs.web.json.feed.JsonFeedMessage;
import br.cs.web.json.feed.JsonSiteFeed;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
	public static List<JsonRendaVariavel> getListJsonRendaVariavel(List<OperacaoEntrada> findAllOperacaoAtiva) {
		List<JsonRendaVariavel> lista = new ArrayList<JsonRendaVariavel>();
		for (OperacaoEntrada o : findAllOperacaoAtiva) {
			lista.add(new JsonRendaVariavel(o));
		}
		return lista;
	}

	public static List<JsonRendaFixa> getListJsonRendaFixa(List<RendaFixa> findByDataSaidaIsNull) {
		List<JsonRendaFixa> lista = new ArrayList<JsonRendaFixa>();
		for (RendaFixa r : findByDataSaidaIsNull) {
			lista.add(new JsonRendaFixa(r));
		}
		return lista;
	}

	public static List<JsonNota> getListJsonNota(List<Nota> findAllByAtivo) {
		List<JsonNota> lista = new ArrayList<JsonNota> ();
		for (Nota n : findAllByAtivo) {
			lista.add(new JsonNota(n));
		}
		return lista;
	}

	public static List<JsonNota> getListJsonNota(Nota nota) {
		List<Nota> notas = new ArrayList<Nota>();
		notas.add(nota);
		return getListJsonNota(notas);
	}

	public static List<JsonEmpresa> getListJsonEmpersa(List<Empresa> empresas) {
		List<JsonEmpresa> result = new ArrayList<JsonEmpresa> ();
		for (Empresa empresa : empresas) {
			result.add(new JsonEmpresa(empresa));
		}
		return result;
	}

	public static JsonEmpresa getJsonEmpresa(Empresa empresa) {
		JsonEmpresa json = new JsonEmpresa(empresa);
		return json;
	}

	public static List<JsonPapel> getListJsonPapeis(List<Papel> papeis) {
		List<JsonPapel> result = new ArrayList<JsonPapel>();
		for (Papel p : papeis) {
			result.add(new JsonPapel(p));
		}
		return result;
	}

	public static List<JsonFeedMessage> getListJsonFeedMessage(List<FeedMessage> feeds) {
		List<JsonFeedMessage> result = new ArrayList<JsonFeedMessage>();
		for (FeedMessage message : feeds) {
			result.add(new JsonFeedMessage(message));
		}
		return result;
	}

	public static List<JsonSiteFeed> getListJsonSiteFeed(List<SiteFeed> sites) {
		List<JsonSiteFeed> result = new ArrayList<JsonSiteFeed>();
		for (SiteFeed feed : sites) {
			result.add(new JsonSiteFeed(feed));
		}
		return result;
	}

	public static JsonFundamento getJsonFundamento(Fundamento fundamento) {
		JsonFundamento json = new JsonFundamento(fundamento);
		return json;
	}
}
