package com.skyrss.bean;

import android.R.integer;

public class SourceBean {
	
	private int id;
	private String name;
	private String url;
	private boolean ischoosed;
	@Override
	public String toString() {
		return "SourceBean [id=" + id + ", name=" + name + ", url=" + url
				+ ", ischoosed=" + ischoosed + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isIschoosed() {
		return ischoosed;
	}
	public void setIschoosed(boolean ischoosed) {
		this.ischoosed = ischoosed;
	}
	public SourceBean(int id, String name, String url, boolean ischoosed) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.ischoosed = ischoosed;
	}
	
	
	

}
