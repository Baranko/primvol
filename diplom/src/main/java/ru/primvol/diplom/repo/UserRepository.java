package ru.primvol.diplom.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.primvol.diplom.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByEmail(String email);
}
