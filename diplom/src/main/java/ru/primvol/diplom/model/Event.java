package ru.primvol.diplom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name_of_event")
	private String nameOfEvent;
	
	@Column(name = "number_of_vol")
	private int numberOfVol;
	
	@Column(name = "id_coord")
	private int idCoord;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "description")
	private String description;
	
	protected Event() {
		
	}
	
	public Event(String nameOfEvent, int numberOfVol, int idCoord, String description) {
		this.nameOfEvent = nameOfEvent;
		this.numberOfVol = numberOfVol;
		this.idCoord = idCoord;
		this.description = description;
		this.status = 1;
	}
	
	public void changeName(String nameOfEvent) {
		this.nameOfEvent = nameOfEvent;
	}
	
	public void changeNumber(int numberOfVol) {
		this.numberOfVol = numberOfVol;
	}
	public void changeDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return nameOfEvent;
	}
}
