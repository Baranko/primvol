package ru.primvol.diplom.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.primvol.diplom.model.Dates;

public interface DatesRepository extends CrudRepository<Dates, Long> {
	
}
