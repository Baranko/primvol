package ru.primvol.diplom.repo;

import org.springframework.data.repository.CrudRepository;

import ru.primvol.diplom.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Long> {

}
