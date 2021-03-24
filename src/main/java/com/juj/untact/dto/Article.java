package com.juj.untact.dto;

public class Article {
	private int id;
	private String Date;
	private String title;
	private String body;
	
	public Article(int i, String Date, String title, String Body) {
		this.id = i;
		this.Date = Date;
		this.title = title;
		this.body = Body;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", Date=" + Date + ", title=" + title + ", body=" + body + "]";
	}

}
