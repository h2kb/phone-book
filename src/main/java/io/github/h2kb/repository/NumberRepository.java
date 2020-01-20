package io.github.h2kb.repository;

import io.github.h2kb.entity.Number;
import org.springframework.data.repository.CrudRepository;

public interface NumberRepository extends CrudRepository<Number, Integer> {
}
