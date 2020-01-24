package io.github.h2kb.service;

import io.github.h2kb.entity.Entry;
import javassist.NotFoundException;

public interface EntryService extends SimpleService {

    Entry getEntry(String entryId) throws NotFoundException;
}
