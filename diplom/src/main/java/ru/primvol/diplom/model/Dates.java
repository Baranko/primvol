package ru.primvol.diplom.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="dates")
public class Dates {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name ="id_event")
	private int idEvent;
	
	@Column(name = "date_of")
	private Date dateOf;
	
	@Column(name = "time_begin")
	private LocalDateTime timeBegin;
	
	@Column(name = "time_end")
	private LocalDateTime timeEnd;
	
	protected Dates() {
		
	}
	
	public Dates(int idEvent, Date dateOf, LocalDateTime timeBegin, LocalDateTime timeEnd) {
		this.idEvent = idEvent;
		this.dateOf = dateOf;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
	}
}
