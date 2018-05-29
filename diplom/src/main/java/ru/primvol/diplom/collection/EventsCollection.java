package ru.primvol.diplom.collection;

import java.util.List;


import ru.primvol.diplom.model.Event;

public class EventsCollection {

private List<Event> events;
	
	public EventsCollection() {}
	

	public List getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
