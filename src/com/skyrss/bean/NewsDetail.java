package com.skyrss.bean;

import java.util.List;


public class NewsDetail extends News  {
	public String title;
	public String link;
	public List<NewsItem> items;
	public NewsImage image;
	public String description;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<NewsItem> getItems() {
		return items;
	}
	public void setItems(List<NewsItem> items) {
		this.items = items;
	}
	public NewsDetail(String title, String link, List<NewsItem> items) {
		super();
		this.title = title;
		this.link = link;
		this.items = items;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public NewsDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "NewsDetils [title=" + title + ", link=" + link + ", items="
				+ items + "]";
	}
	
	public static class NewsImage {
		public String url;
		public String title;
		public String link;
	}
	
	public static class NewsItem extends Item {
		public String title;
		public String link;
		public String image;
		public String description;
		public String pubDate;
		public String creator;
		public String author;
		public String encoded;
	}
}
