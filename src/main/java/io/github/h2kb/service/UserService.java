package io.github.h2kb.service;

import io.github.h2kb.dto.UserDto;
import io.github.h2kb.entity.PhoneBook;
import io.github.h2kb.entity.User;
import javassist.NotFoundException;

public interface UserService extends CommonService {

    User addUser(String firstName, String lastName, String login);

    UserDto getUser(String userId) throws NotFoundException;

    User editUserFirstName(String userId, String firstName) throws NotFoundException;

    User editUserLastName(String userId, String lastName) throws NotFoundException;

    User deleteUser(String userId) throws NotFoundException;

    PhoneBook getUserPhoneBook(String userId) throws NotFoundException;

    Iterable<UserDto> getAllUsers();
}
