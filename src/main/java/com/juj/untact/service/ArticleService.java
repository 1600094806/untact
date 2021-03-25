package com.juj.untact.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juj.untact.dto.Article;
import com.juj.untact.dto.ResultData;
import com.juj.untact.util.Util;

@Service
public class ArticleService {
	private List<Article> articles;
	private int articlesLastId;
	
	public ArticleService() {
		articlesLastId = 0;
		articles = new ArrayList<>();
		String time = Util.getNowDateStr();
		articles.add(new Article(++articlesLastId,time,time ,"제목 1","내용 1"));
		articles.add(new Article(++articlesLastId,time,time ,"제목 2","내용 2"));
	}

	public Article getArticle(int id) {
		for(Article a:articles) {
			if(a.getId() == id) {
				return a;
			}
		}
		return null;
	}

	public List<Article> getList() {
		return articles;
	}

	public ResultData add(String title, String body) {
		int id = ++articlesLastId;
		String time1 = Util.getNowDateStr();
		articles.add(new Article(id,time1,time1 ,title,body));
		
		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}
	
	public ResultData deleteArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				return new ResultData("S-1", "삭제하였습니다.", "id", id);
			}
		}

		return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.", "id", id);
	}

	public ResultData modify(int id,String title, String body, String nowDateStr) {
		Article article = getArticle(id);
		article.setTitle(title);
		article.setBody(body);
		article.setUpdateDate(nowDateStr);
		return new ResultData("S-1", "게시물을 수정하였습니다.", "id", id);
	}
}
