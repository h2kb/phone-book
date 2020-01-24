package io.github.h2kb.service;

import io.github.h2kb.entity.User;
import javassist.NotFoundException;

public interface UserService {

    User addUser(String firstName, String lastName, String login);
    User getUser(String userId) throws NotFoundException;
    User editUserFirstName(String userId, String firstName) throws NotFoundException;
    User editUserLastName(String userId, String lastName) throws NotFoundException;
    User deleteUser(String userId) throws NotFoundException;
    // getAllUsers;
}
