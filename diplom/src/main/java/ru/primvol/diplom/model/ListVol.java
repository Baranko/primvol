package ru.primvol.diplom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "list_vol")
public class ListVol {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "id_vol")
	private long idVol;
	
	@Column(name = "id_event")
	private long idEvent;
	
	@Column(name = "status") //1-обычный, 2 - брать, 3 - не брать, 4 - в резерв
	private int status;
	
	protected ListVol() {
		
	}
	
	public ListVol(long idVol, long idEvent) {
		this.idVol = idVol;
		this.idEvent = idEvent;
		status = 1;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setIdVol(long idVol) {
		this.idVol = idVol;
	}
	
	public long getIdVol() {
		return idVol;
	}
	
	public void setIdEvent(long idEvent) {
		this.idEvent = idEvent;
	}
	
	public long getIdEvent() {
		return idEvent;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
}