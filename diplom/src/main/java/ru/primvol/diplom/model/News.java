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
	
	public News(String nameOfNews, int idAgent, byte[] photoBlob, String textOfNews) {
		this.nameOfNews = nameOfNews;
		this.idAgent = idAgent;
		this.photoBlob = photoBlob;
		this.textOfNews = textOfNews;
	}
	
	public void changeName(String nameOfNews) {
		this.nameOfNews = nameOfNews;
	}
	
	public void changeIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	
	public void changePhoto(byte[] photoBlob) {
		this.photoBlob = photoBlob;
	}
	
	public void changeText(String textOfNews) {
		this.textOfNews = textOfNews;
	}
}
