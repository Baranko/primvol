package ru.primvol.diplom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import ru.primvol.diplom.repo.ListVolRepository;

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
	private long idCoord;
	
	@Column(name = "place")
	private String place;
	
	@Column(name = "status") //0 - неактивное, 1 - активное, 2 - набор закрыт и список не сформирован, 3 - набор закрыт и список сформирован, 4 - прошло
	private int status;
	
	@Column(name = "description")
	private String description;
	
	protected Event() {
		
	}
	
	public Event(String nameOfEvent, int numberOfVol, int idCoord, String place, String description) {
		this.nameOfEvent = nameOfEvent;
		this.numberOfVol = numberOfVol;
		this.idCoord = idCoord;
		this.place = place;
		this.description = description;
		this.status = 1;
	}
	
	public Event(String nameOfEvent, String place, String description) {
		this.nameOfEvent = nameOfEvent;
		this.place = place;
		this.description = description;
		this.status = 1;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setNameOfEvent(String nameOfEvent) {
		this.nameOfEvent = nameOfEvent;
	}
	
	public String getNameOfEvent() {
		return nameOfEvent;
	}
	
	public void setNumberOfVol(int numberOfVol) {
		this.numberOfVol = numberOfVol;
	}
	
	public int getNumberOfVol()  {
		return numberOfVol;
	}
	
	public void setIdCoord(long id) {
		this.idCoord = id;
	}
	
	public long getIdCoord() {
		return idCoord;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return nameOfEvent;
	}
}
