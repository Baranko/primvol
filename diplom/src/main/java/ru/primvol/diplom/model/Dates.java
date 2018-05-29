package ru.primvol.diplom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="dates")
public class Dates {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name ="id_event")
	private long idEvent;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date_of")
	private Date dateOf;
	
	@Column(name = "time_begin")
	private String timeBegin;
	
	@Column(name = "time_end")
	private String timeEnd;
	
	protected Dates() {
		
	}
	
	public Dates(long idEvent, Date dateOf, String timeBegin, String timeEnd) {
		this.idEvent = idEvent;
		this.dateOf = dateOf;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	public void setIdEvent(long idEvent) {
		this.idEvent = idEvent;
	}
	
	public long getIdEvent() {
		return idEvent;
	}
	
	public void setDateOf(Date dateOf) {
		this.dateOf = dateOf;
	}
	
	public Date getDateOf() {
		return dateOf;
	}
	
	public void setTimeBegin(String timeBegin) {
		this.timeBegin = timeBegin;
	}
	
	public String getTimeBegin() {
		return timeBegin;
	}
	
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	public String getTimeEnd() {
		return timeEnd;
	}
}
