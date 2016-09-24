package br.cs.web.json;

import java.util.List;

public abstract class ResultDataTableJson {
	private int iTotalRecords;
	private int iTotalDisplayRecords;
	private String sEcho;
	private String sColumns;
	private List<?> aaData;

	public int getiTotalRecords() {
		return this.iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return this.iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return this.sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return this.sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<?> getAaData() {
		return this.aaData;
	}

	public void setAaData(List<?> aaData) {
		this.aaData = aaData;
	}
}
