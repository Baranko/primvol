package ru.primvol.diplom.collection;

import java.util.List;

import ru.primvol.diplom.model.News;

public class NewsCollection {
	
private List<News> News;
	
	public NewsCollection() {}
	

	public List getNews() {
		return News;
	}

	public void setNews(List<News> News) {
		this.News = News;
	}

}
