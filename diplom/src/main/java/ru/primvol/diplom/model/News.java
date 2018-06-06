package ru.primvol.diplom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "news")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name_of_news")
	private String nameOfNews;
	
	@Column(name = "id_event")
	private int idEvent;
	
	@Column(name = "id_agent")
	private int idAgent;
	
	@Column(name = "main_photo")
	@Type(type="org.hibernate.type.BinaryType") 
	private byte[] photoBlob; //сложно думай
	
	@Column(name = "text_of_news")
	private String textOfNews;
	
	protected News() {
		
	}
	
	public News(String nameOfNews, int idAgent, String textOfNews) {
		this.nameOfNews = nameOfNews;
		this.idAgent = idAgent;
		this.textOfNews = textOfNews;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	
	public void setNameOfNews(String nameOfNews) {
		this.nameOfNews = nameOfNews;
	}
	
	public String getNameOfNews() {
		return nameOfNews;
	}
	
	public void setIdAgent(int idAgent) {
		this.idAgent = idAgent;
	}
	
	public int getIdAgent() {
		return idAgent;
	}
	
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	
	public int getIdEvent() {
		return idEvent;
	}
	
	public void changePhoto(byte[] photoBlob) {
		this.photoBlob = photoBlob;
	}
	
	public void setTextOfNews(String textOfNews) {
		this.textOfNews = textOfNews;
	}
	
	public String getTextOfNews() {
		return textOfNews;
	}
}
