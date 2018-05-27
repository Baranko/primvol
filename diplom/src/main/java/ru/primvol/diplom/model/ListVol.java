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
	private int idVol;
	
	@Column(name = "id_event")
	private int idEvent;
	
	@Column(name = "status")
	private int status;
	
	protected ListVol() {
		
	}
	
	public ListVol(int idVol, int idEvent) {
		this.idVol = idVol;
		this.idEvent = idEvent;
		status = 1;
	}
}