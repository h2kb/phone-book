package io.github.h2kb.service.impl;

import io.github.h2kb.entity.Entry;
import io.github.h2kb.entity.Number;
import io.github.h2kb.entity.enums.NumberType;
import io.github.h2kb.repository.EntryRepository;
import io.github.h2kb.repository.NumberRepository;
import io.github.h2kb.service.NumberService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NumberServiceImpl implements NumberService {

    @Autowired
    private NumberRepository numberRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Override
    public Number addNumber(String entryId, NumberType numberType, String PhoneNumber) throws NotFoundException {
        Entry entry;
        Optional<Entry> optionalEntry = entryRepository.findById(Integer.parseInt(entryId));

        if (optionalEntry.isPresent()) {
            entry = optionalEntry.get();
        } else {
            throw new NotFoundException(NOT_FOUND);
        }

        Number number = new Number(entry, PhoneNumber, numberType);
        numberRepository.save(number);
        return number;
    }

    @Override
    public Number editPhoneNumber(String numberId, String phoneNumber) throws NotFoundException {
        Number number;
        Optional<Number> optionalNumber = numberRepository.findById(Integer.parseInt(numberId));

        if (optionalNumber.isPresent()) {
            number = optionalNumber.get();
        } else {
            throw new NotFoundException(NOT_FOUND);
        }

        number.setNumber(phoneNumber);
        numberRepository.save(number);
        return number;
    }

    @Override
    public Number deleteNumber(String numberId) throws NotFoundException {
        Number number;
        Optional<Number> optionalNumber = numberRepository.findById(Integer.parseInt(numberId));

        if (optionalNumber.isPresent()) {
            number = optionalNumber.get();
        } else {
            throw new NotFoundException(NOT_FOUND);
        }

        numberRepository.delete(number);
        return number;
    }
}
