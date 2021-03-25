package com.juj.untact.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juj.untact.dto.Article;
import com.juj.untact.dto.ResultData;
import com.juj.untact.service.ArticleService;
import com.juj.untact.util.Util;

@Controller
public class UsrArticleControll {
	@Autowired
	private ArticleService articleservice;
	
	
	
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		Article article = articleservice.getArticle(id);
		return article;
	}
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList(String searchKeywordType,String searchKeyword) {
		if(searchKeywordType != null ) {
			searchKeywordType = searchKeywordType.trim();
		}
		
		if(searchKeywordType == null || searchKeywordType.length() == 0) {
			searchKeywordType = "titleAndTITLE";
		}
		
		if(searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}
		
		if(searchKeyword!=null) {
			searchKeyword = searchKeyword.trim();
		}
		
		return articleservice.getArticles(searchKeywordType,searchKeyword);
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title ,String body) {
		if(title == null) {
			return new ResultData("resultCode","F-1","이유","타이틀오류");
		}
		if(body == null) {
			return new ResultData("resultCode","F-1","이유","내용오류");

		}
		return articleservice.addArticle(title, body);
	}
	


	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		if(articleservice.getArticle(id)==null) {
			return new ResultData("F-1","msg", "삭제 실패","id" , id);
		}
		return articleservice.deleteArticle(id);
	}

	
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id,String title,String body) {
		if(articleservice.getArticle(id) == null) {
			return new ResultData("resultCode", "F-1","msg", "Don't exist");
		} else {
			return articleservice.modifyArticle(id,title,body,Util.getNowDateStr());
		}
	}
}
