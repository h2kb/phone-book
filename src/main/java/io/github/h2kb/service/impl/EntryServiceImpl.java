package io.github.h2kb.service.impl;

import io.github.h2kb.entity.Entry;
import io.github.h2kb.entity.PhoneBook;
import io.github.h2kb.entity.User;
import io.github.h2kb.entity.enums.EntryType;
import io.github.h2kb.repository.EntryRepository;
import io.github.h2kb.repository.PhoneBookRepository;
import io.github.h2kb.repository.UserRepository;
import io.github.h2kb.service.EntryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Override
    public Entry addEntry(String ownerId, EntryType entryType, String entryName) throws NotFoundException {
        User user;
        PhoneBook phoneBook = new PhoneBook();
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(ownerId));

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new NotFoundException(NOT_FOUND);
        }

        Optional<PhoneBook> optionalPhoneBook = phoneBookRepository.findByOwner(user);

        if (optionalPhoneBook.isPresent()) {
            phoneBook = optionalPhoneBook.get();
        }

        Entry entry = new Entry(phoneBook, entryName, entryType);
        entryRepository.save(entry);

        return entry;
    }

    @Override
    public Entry editEntryName(String entryId, String entryName) throws NotFoundException {
        Entry entry;
        Optional<Entry> optionalEntry = entryRepository.findById(Integer.parseInt(entryId));

        if (optionalEntry.isPresent()) {
            entry = optionalEntry.get();
        } else {
            throw new NotFoundException(NOT_FOUND);
        }

        entry.setName(entryName);
        entryRepository.save(entry);

        return entry;
    }

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
