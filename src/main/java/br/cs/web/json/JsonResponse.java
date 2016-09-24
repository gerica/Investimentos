package br.cs.web.json;

import java.util.List;

public class JsonResponse {
	private String status;
	private Object message;
	private Object result;
	private Object objeto;
	private Object controller;
	private List<?> list;

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResult() {
		return this.result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getMessage() {
		return this.message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public List<?> getList() {
		return this.list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Object getObjeto() {
		return this.objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	public Object getController() {
		return this.controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}
}
