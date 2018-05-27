package ru.primvol.diplom.repo;

import org.springframework.data.repository.CrudRepository;

import ru.primvol.diplom.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
