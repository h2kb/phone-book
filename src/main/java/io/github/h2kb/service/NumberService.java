package io.github.h2kb.service;

import io.github.h2kb.entity.Number;
import io.github.h2kb.entity.enums.NumberType;
import javassist.NotFoundException;

public interface NumberService extends SimpleService {

    Number addNumber(String entryId, NumberType numberType, String number) throws NotFoundException;

    Number editNumber(String numberId, String number) throws NotFoundException;

    Number deleteNumber(String numberId) throws NotFoundException;
}
