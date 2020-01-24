package io.github.h2kb.service.impl;

import io.github.h2kb.entity.Entry;
import io.github.h2kb.repository.EntryRepository;
import io.github.h2kb.service.EntryService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService {

    private EntryRepository entryRepository;

    @Override
    public Entry getEntry(String entryId) throws NotFoundException {
        Entry entry;
        Optional<Entry> optionalEntry = entryRepository.findById(Integer.parseInt(entryId));

        if (optionalEntry.isPresent()) {
            entry = optionalEntry.get();
        } else {
            throw new NotFoundException(NOT_FOUND);
        }

        return entry;
    }
}
