package ru.primvol.diplom.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.primvol.diplom.model.ListVol;

public interface ListVolRepository extends CrudRepository<ListVol, Long> {
	List<ListVol> findByIdEvent(long idEvent);
	List<ListVol> findByIdEventAndIdVol(long idEvent, long idVol);
	List<ListVol> findByIdEventAndStatus(long idEvent, int status);
}
