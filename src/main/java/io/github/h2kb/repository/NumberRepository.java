package io.github.h2kb.repository;

import io.github.h2kb.entity.Entry;
import io.github.h2kb.entity.Number;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NumberRepository extends CrudRepository<Number, Integer> {

    Optional<Number> findByEntry(Entry entry);
}
