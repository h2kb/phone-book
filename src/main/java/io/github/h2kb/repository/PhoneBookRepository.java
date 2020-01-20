package io.github.h2kb.repository;

import io.github.h2kb.entity.PhoneBook;
import io.github.h2kb.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PhoneBookRepository extends CrudRepository<PhoneBook, Integer> {

    Optional<PhoneBook> findByOwner(User user);
}
