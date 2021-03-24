package com.juj.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juj.untact.dto.Article;

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
	public Map<String, Object> doAdd(String regDate,String title ,String body) {
		articles.add(new Article(++articlesLastId,regDate ,title,body));
		
		Map<String,Object> rs = new HashMap<>();
		rs.put("resultCode", "S-1");
		rs.put("msg", "성공");
		rs.put("id", articlesLastId);
		return rs;
	}
}
