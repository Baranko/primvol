package ru.primvol.diplom.repo;

import org.springframework.data.repository.CrudRepository;

import ru.primvol.diplom.model.UserGroup;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

}
