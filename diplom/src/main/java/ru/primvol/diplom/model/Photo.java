package ru.primvol.diplom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "photos")
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "id_news")
	private int idNews;
	
	@Column(name = "photo") 
	@Type(type="org.hibernate.type.BinaryType") 
	private byte[] photoBlob; //сложно думай
	
	protected Photo() {
		
	}
	
	public Photo(int idNews, byte[] photoBlob) {
		this.idNews = idNews;
		this.photoBlob = photoBlob;
	}
}
