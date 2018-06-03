package ru.primvol.diplom.collection;

import java.util.List;

import ru.primvol.diplom.model.ListVol;

public class ListVolCollection {
	private List<ListVol> listVol;
	
	public ListVolCollection() {
		
	}
	
	public void setListVol(List<ListVol> listVol) {
		this.listVol = listVol;
	}
	
	public List<ListVol> getListVol() {
		return listVol;
	}
}
