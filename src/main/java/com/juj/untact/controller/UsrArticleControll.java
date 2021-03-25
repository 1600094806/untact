package com.juj.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juj.untact.dto.Article;
import com.juj.untact.util.Util;

@Controller
public class UsrArticleControll {
	
	private List<Article> articles;
	private int articlesLastId;
	
	public UsrArticleControll() {
		articlesLastId = 0;
		articles = new ArrayList<>();
		articles.add(new Article(++articlesLastId,"2020-12-12 12:12:12" ,"제목 1","내용 1"));
		articles.add(new Article(++articlesLastId,"2020-12-12 12:12:12" ,"제목 2","내용 2"));
	}
	
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		return articles.get(id-1);
	}
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Map<String, Object> doAdd(String title ,String body) {
		String time1 = Util.getNowDateStr();
		
		articles.add(new Article(++articlesLastId,time1 ,title,body));
		
		Map<String,Object> rs = new HashMap<>();
		rs.put("resultCode", "S-1");
		rs.put("msg", "성공");
		rs.put("id", articlesLastId);
		return rs;
	}
	


	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public Map<String, Object> doDelete(int id) {
		Map<String,Object> rs = new HashMap<>();
		if(deleteArticle(id)) {
			rs.put("resultCode", "S-1");
			rs.put("msg", "삭제 성공");
			rs.put("id" , id);
		} else {
			rs.put("resultCode", "F-1");
			rs.put("msg", "삭제 실패");
			rs.put("id" , id);
		}
		return rs;
	}

	public boolean deleteArticle(int id) {
		for(Article a:articles) {
			if(a.getId() == id) {
				articles.remove(a);
				return true;
			}
		}
		return false;
		
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Map<String, Object> doModify(int id,String title,String body) {
		Map<String,Object> rs = new HashMap<>();
		Article article = null;
		for(Article a:articles) {
			if(a.getId() == id) {
				article = a;
				break;
			}
		}
		if(article == null) {
			rs.put("resultCode", "F-1");
			rs.put("msg", "Don't exist");
			return rs;
		} else {
			article.setBody(body);
			article.setTitle(title);
			rs.put("resultCode", "S-1");
			rs.put("msg", "Modify Success!");
			rs.put("id" , article.getId());
			rs.put("title" , article.getTitle());
			rs.put("body" , article.getBody());
		}
		
		
		return rs;
	}
}
