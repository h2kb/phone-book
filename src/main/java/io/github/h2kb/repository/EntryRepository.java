package io.github.h2kb.repository;

import io.github.h2kb.entity.Entry;
import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<Entry, Integer> {
}
