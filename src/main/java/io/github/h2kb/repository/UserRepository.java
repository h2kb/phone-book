package io.github.h2kb.repository;

import io.github.h2kb.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
