package io.github.h2kb.service;

import io.github.h2kb.entity.Entry;
import io.github.h2kb.entity.enums.EntryType;
import javassist.NotFoundException;

public interface EntryService extends SimpleService {

    Entry addEntry(String ownerId, EntryType entryType, String entryName) throws NotFoundException;

    Entry editEntryName(String entryId, String entryName) throws NotFoundException;

    Entry getEntry(String entryId) throws NotFoundException;

    Entry deleteEntry(String entryId) throws NotFoundException;
}
