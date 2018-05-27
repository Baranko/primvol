package ru.primvol.diplom.repo;

import org.springframework.data.repository.CrudRepository;

import ru.primvol.diplom.model.Session;

public interface SessionRepository extends CrudRepository<Session, Long> {

}
