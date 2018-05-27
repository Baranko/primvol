package ru.primvol.diplom.repo;

import org.springframework.data.repository.CrudRepository;

import ru.primvol.diplom.model.News;

public interface NewsRepository extends CrudRepository<News,Long> {

}
