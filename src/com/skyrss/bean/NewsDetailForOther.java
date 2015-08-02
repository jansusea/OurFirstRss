package com.skyrss.bean;

import java.util.ArrayList;

public class NewsDetailForOther extends News {

	public String title;
	public ArrayList<NewsEntry> entries;
	
	
	public static class NewsEntry extends Item {
		public String id;
		public String title;
		public String author;
		public String published;
		public String content;
	}
	
}
