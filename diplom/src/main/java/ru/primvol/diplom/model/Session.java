package ru.primvol.diplom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "key_of_session")
	private String keyOfSession;
	
	@Column(name = "date_of_session")
	private Date dateOfSession;
	
	@Column(name = "id_user")
	private int idUser;
	
	protected Session() {
		
	}
	
	public Session(Date dateOfSession, int idUser) {
		this.dateOfSession = dateOfSession;
		this.idUser = idUser;
		//keyOfSession
	}
}
